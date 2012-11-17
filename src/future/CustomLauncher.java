/*
 * Copyright (c) 2012, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package future;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO Reduce code redundancy. I'm to tired now.
 * 
 * [Oskar]<br>
 * Shows a custom launcher application that automatically downloads the required
 * jar files.<br>
 * [/Oskar]<br>
 * <br>
 * 
 * [Ryan]<br>
 * Updated to check if the libraries and natives are missing, then only
 * downloads the ones that are missing.<br>
 * Methods are defined in alphabetical order for easier code navigation.<br>
 * [/Ryan]
 * 
 * @author Oskar Veerhoek
 * @author Ryan Porterfield a.k.a. ShadowHawk54
 * @version 2.0
 */
public class CustomLauncher {
	/**
	 * Base folder for all external libraries
	 */
	static final File LIB_FOLDER = new File("download");
	/**
	 * A list of which libraries are missing
	 */
	static final Map<String, Integer> missingLibraries = new HashMap<>();
	/**
	 * A list of which natives are missing.<br>
	 * Must be kept separate because natives are stored in /native/[os] and must
	 * be downloaded into /native and therefore must be handled separately.
	 */
	static final Map<String, Integer> missingNatives = new HashMap<>();
	/**
	 * The download folder of my personal web-site where I'm hosting all the
	 * jars and natives.
	 */
	static final String BASE_URL = "http://ryanporterfield.com/downloads/";

	/**
	 * Total size <em>in bytes</em> that needs to be downloaded
	 */
	private int totalDownloadSize;
	/**
	 * A complete list of all external libraries names and file sizes.
	 */
	private Map<String, Integer> dependencies;
	/**
	 * A complete list of all native libraries, names and file sizes.
	 */
	private Map<String, Integer> natives;
	/**
	 * An Operating System variable
	 */
	private OS os;

	/**
	 * Main method. Self explanetory.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		CustomLauncher launcher = new CustomLauncher();
		launcher.checkDependencies();

		if (!missingLibraries.isEmpty() || !missingNatives.isEmpty()) {
			System.out.println("Missing " + (launcher.totalDownloadSize / 1024)
					+ "KiB");
			System.out.println("Starting Download");
			launcher.downloadLibraries();
			launcher.downloadNatives();
		} else {
			System.out.println("Nothing missing!");
		}
	}

	/**
	 * Constructor initializes the instance variables
	 */
	public CustomLauncher() {
		if (!LIB_FOLDER.exists() && !LIB_FOLDER.mkdirs()) {
			throw new RuntimeException("Can not create required folders.");
		}
		totalDownloadSize = 0;
		dependencies = new HashMap<>();
		natives = new HashMap<>();
		checkOS();
	}

	/**
	 * Checks to see which dependencies are missing by populating the
	 * <code>missing</code> map
	 * 
	 * @see CustomLauncher#missingLibraries
	 */
	public void checkDependencies() {
		populateDependencies();
		populateNatives();
		checkLibraries();
		checkNatives();
	}

	private void checkLibraries() {
		for (String lib : dependencies.keySet()) {
			File flib = new File(LIB_FOLDER, lib);

			try {
				if (!flib.exists()) {
					int fileSize = dependencies.get(lib);
					missingLibraries.put(lib, fileSize);
					totalDownloadSize = totalDownloadSize + fileSize;
					if (!flib.createNewFile()) {
						throw new IOException("Can't create " + flib.getName());
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void checkNatives() {
		File nativeFolder = new File(LIB_FOLDER, "native/");
		if (!nativeFolder.exists() && !nativeFolder.mkdirs()) {
			throw new RuntimeException("Can not create required folders.");
		}

		for (String lib : natives.keySet()) {
			File flib = new File(nativeFolder, lib);

			try {
				if (!flib.exists()) {
					int fileSize = natives.get(lib);
					missingNatives.put(lib, fileSize);
					totalDownloadSize = totalDownloadSize + fileSize;
					if (!flib.createNewFile()) {
						throw new IOException("Can't create " + flib.getName());
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Set the os variable by parsing the result of
	 * <em>System.getProperty("os.name")</em> for operating system dependent
	 * natives.
	 * 
	 * @see CustomLauncher#os
	 */
	public void checkOS() {
		String opSys = System.getProperty("os.name").toLowerCase();

		if (opSys.contains("win")) {
			os = OS.WINDOWS;
		} else if (opSys.contains("mac")) {
			os = OS.OSX;
		} else if (opSys.contains("solaris")) {
			os = OS.SOlARIS;
		} else if (opSys.contains("sunos")) {
			os = OS.SOlARIS;
		} else if (opSys.contains("linux")) {
			os = OS.LINUX;
		} else if (opSys.contains("unix")) {
			os = OS.LINUX;
		} else {
			// Unknown Operating System
			System.exit(1);
		}
	}

	public void downloadLibraries() {
		try {
			for (String lib : missingLibraries.keySet()) {
				File ofile = new File(LIB_FOLDER, lib);
				URL url = new URL(BASE_URL + lib);
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(ofile);

				fos.getChannel()
						.transferFrom(rbc, 0, missingLibraries.get(lib));
				fos.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("File problems.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadNatives() {
		File nativeFolder = new File(LIB_FOLDER, "native");
		String nativeURL = BASE_URL + os.url;

		try {
			for (String lib : missingNatives.keySet()) {
				File ofile = new File(nativeFolder, lib);
				URL url = new URL(nativeURL + lib);
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(ofile);

				fos.getChannel().transferFrom(rbc, 0, missingNatives.get(lib));
				fos.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("File problems.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void populateDependencies() {
		dependencies.put("jbox2d-library-2.1.22.jar", 229860);
		dependencies.put("jbullet.jar", 531624);
		dependencies.put("jdom-1.1.2.jar", 152526);
		dependencies.put("jinput.jar", 214859);
		dependencies.put("lwjgl_util.jar", 173342);
		dependencies.put("lwjgl.jar", 941293);
		dependencies.put("PNGDecoder.jar", 10166);
		dependencies.put("slj4j-api-1.66.jar", 26176);
		dependencies.put("slf4j-simple-1.66.jar", 10336);
		dependencies.put("slick-util.jar", 95908);
		dependencies.put("slick.jar", 643277);
	}

	private void populateNatives() {
		for (String nLib : os.natives.keySet()) {
			natives.put(nLib, os.natives.get(nLib));
		}
	}

	enum OS {
		LINUX("native/linux/", linux()), OSX("native/macosx/", osx()), SOlARIS(
				"native/solaris/", solaris()), WINDOWS("native/windows/",
				windows());

		final public String url;
		final public Map<String, Integer> natives;

		OS(String url, Map<String, Integer> natives) {
			this.url = url;
			this.natives = natives;
		}

		static public Map<String, Integer> linux() {
			Map<String, Integer> map = new HashMap<>();
			map.put("libjinput-linux.so", 13824);
			map.put("libjinput-linux64.so", 14512);
			map.put("liblwjgl.so", 374744);
			map.put("liblwjgl64.so", 437176);
			map.put("libopenal.so", 353148);
			map.put("libopenal64.so", 383860);
			return map;
		}

		static public Map<String, Integer> osx() {
			Map<String, Integer> map = new HashMap<>();
			map.put("libjinput-osx.jnilib", 0);
			map.put("liblwjgl.jnilib", 0);
			map.put("openal.dylib", 0);
			return map;
		}

		static public Map<String, Integer> solaris() {
			Map<String, Integer> map = new HashMap<>();
			map.put("liblwjgl.so", 0);
			map.put("liblwjgl64.so", 0);
			map.put("libopenal.so", 0);
			map.put("libopenal64.so", 0);
			return map;
		}

		static public Map<String, Integer> windows() {
			Map<String, Integer> map = new HashMap<>();
			map.put("jinput-dx8.dll", 0);
			map.put("jinput-dx8_64.dll", 0);
			map.put("jinput-raw.dll", 0);
			map.put("jinput-raw_64.dll", 0);
			map.put("lwjgl.dll", 0);
			map.put("lwjgl_64.dll", 0);
			map.put("OpenAL32.dll", 0);
			map.put("OpenAL64.dll", 0);
			return map;
		}
	}
}
