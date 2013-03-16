/*
 * Copyright (c) 2013, Oskar Veerhoek
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

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * [Oskar]<br> Thanks to http://www.youtube.com/user/theanprocks/ : To download the files from the official servers,
 * have a look at http://pastebin.com/DZLDVGYh Shows a custom launcher application that automatically downloads the
 * required jar files.<br> [/Oskar]<br> <br> [Ryan]<br> Updated to check if the libraries and natives are missing, then
 * only downloads the ones that are missing.<br> Methods are defined in alphabetical order for easier code
 * navigation.<br> [/Ryan]
 *
 * @author Oskar Veerhoek
 * @author Ryan Porterfield a.k.a. ShadowHawk54
 * @version 2.1.5
 */
public class CustomLauncher {

    /** Base folder for all external libraries */
    private static final File libraryFolder = new File("download");
    /** The download folder of my personal web-site where I'm hosting all the jars and natives. */
    private static final String baseURL = "http://ryanporterfield.com/downloads/";
    /** A list of the libraries that are are missing */
    final private Map<String, Integer> missing = new HashMap<String, Integer>();
    /** Amount of bytes downloaded. */
    private int totalDownloaded;
    /** Total size <em>in bytes</em> that needs to be downloaded */
    private int totalDownloadSize;
    /** A complete list of all external libraries names and file sizes. */
    private Map<String, Integer> dependencies;
    /** An Operating System variable */
    private OS os;

    /**
     * Main method. Self explanatory.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CustomLauncher launcher = new CustomLauncher();
        launcher.populateDependencies();
        launcher.populateNatives();
        launcher.check();

        if (!launcher.missing.isEmpty()) {
            System.out.println("Missing " + (launcher.totalDownloadSize / 1024) + "KiB");
            System.out.println("Starting Download");
            launcher.downloadMissing();
        } else {
            System.out.println("Nothing missing!");
        }
    }

    /** Constructor initializes the instance variables */
    private CustomLauncher() {
        if (!libraryFolder.exists() && !libraryFolder.mkdirs()) {
            throw new RuntimeException("Cannot create required folders.");
        }
        totalDownloadSize = 0;
        dependencies = new HashMap<String, Integer>();
        checkOS();
    }

    private void check() {
        File nativeFolder = new File(libraryFolder, "/native");
        if (!nativeFolder.exists() && !nativeFolder.mkdirs()) {
            throw new RuntimeException("Cannot create folders!");
        }
        for (String libraryName : dependencies.keySet()) {
            File libraryFile = new File(libraryFolder, libraryName);
            if (!libraryFile.exists()) {
                int fileSize = dependencies.get(libraryName);
                missing.put(libraryName, fileSize);
                totalDownloadSize = totalDownloadSize + fileSize;
            }
        }
    }

    /**
     * Set the os variable by parsing the result of <em>System.getProperty("os.name")</em> for operating system
     * dependent natives.
     *
     * @see CustomLauncher#os
     */
    void checkOS() {
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

    void download(String source, String destination, int size) {
        // ten percent of the total download size
        int percent = totalDownloadSize / 10;
        File ofile = new File(libraryFolder, destination);
        System.out.printf("\nDownloading\n\t%s\nTo\n\t%s\n", source, destination);
        try {
            if (!ofile.createNewFile()) {
                throw new IOException("Can't create " + ofile.getName());
            }

            int inChar = 0;
            URL url = new URL(source);
            InputStream input = url.openStream();
            FileOutputStream fos = new FileOutputStream(ofile);

            for (int i = 0; i < size && inChar != -1; i++) {
                inChar = input.read();
                fos.write(inChar);
                totalDownloaded++;
                if (totalDownloaded % percent == 0) {
                    int quantized = (totalDownloaded / percent) * 10;
                    System.out.println(quantized + "% complete");
                }
            }

            input.close();
            fos.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void downloadMissing() {
        for (String lib : missing.keySet()) {
            String url = baseURL, file = lib;
            if (!lib.endsWith(".jar")) {
                url = url + os.url;
                file = "native/" + lib;
            }
            download(url + lib, file, missing.get(lib));
        }
    }

    /**
     * Puts all the dependency jars and their file-sizes into the dependencies map.
     *
     * @see CustomLauncher#dependencies
     */
    private void populateDependencies() {
        dependencies.put("jbox2d-library-2.1.2.2.jar", 229860);
        dependencies.put("jbullet.jar", 531624);
        dependencies.put("jdom-1.1.2.jar", 152526);
        dependencies.put("jinput.jar", 214859);
        dependencies.put("lwjgl_util.jar", 173342);
        dependencies.put("lwjgl.jar", 941293);
        dependencies.put("PNGDecoder.jar", 10166);
        dependencies.put("slf4j-api-1.6.6.jar", 26176);
        dependencies.put("slf4j-simple-1.6.6.jar", 10336);
        dependencies.put("slick-util.jar", 95908);
        dependencies.put("slick.jar", 643277);
    }

    /** Grabs the list of natives from the OS enum and puts them into the natives map. */
    private void populateNatives() {
        for (String nLib : os.natives.keySet()) {
            dependencies.put(nLib, os.natives.get(nLib));
        }
    }

    enum OS {
        LINUX("native/linux/", linux()), OSX("native/macosx/", osx()), SOlARIS("native/solaris/", solaris()),
        WINDOWS("native/windows/", windows());

        final public String url;
        final public Map<String, Integer> natives;

        OS(String url, Map<String, Integer> natives) {
            this.url = url;
            this.natives = natives;
        }

        static public Map<String, Integer> linux() {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("libjinput-linux.so", 13824);
            map.put("libjinput-linux64.so", 14512);
            map.put("liblwjgl.so", 374744);
            map.put("liblwjgl64.so", 437176);
            map.put("libopenal.so", 353148);
            map.put("libopenal64.so", 383860);
            return map;
        }

        static public Map<String, Integer> osx() {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("libjinput-osx.jnilib", 64608);
            map.put("liblwjgl.jnilib", 1123272);
            map.put("openal.dylib", 378384);
            return map;
        }

        static public Map<String, Integer> solaris() {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("liblwjgl.so", 345460);
            map.put("liblwjgl64.so", 539008);
            map.put("libopenal.so", 328344);
            map.put("libopenal64.so", 372640);
            return map;
        }

        static public Map<String, Integer> windows() {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("jinput-dx8.dll", 61952);
            map.put("jinput-dx8_64.dll", 65024);
            map.put("jinput-raw.dll", 59392);
            map.put("jinput-raw_64.dll", 62464);
            map.put("lwjgl.dll", 290304);
            map.put("lwjgl_64.dll", 301056);
            map.put("OpenAL32.dll", 184320);
            map.put("OpenAL64.dll", 376320);
            return map;
        }
    }
}
