package future;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Shows a custom launcher application that automatically downloads the required jar files.
 */
public class CustomLauncher {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://downloads.sourceforge.net/project/java-game-lib/Official%20Releases/LWJGL%202.8.3/lwjgl-2.8.3.zip?r=&ts=1338645929&use_mirror=garr");
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("url.html");
        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
    }
}
