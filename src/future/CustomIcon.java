package future;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Shows how to add a custom icon to the program.
 */
public class CustomIcon {
    public static void main(String[] args) {
        setUpDisplay();
        while (!Display.isCloseRequested()) {
            Display.sync(60);
            Display.update();
        }
        Display.destroy();
        System.exit(0);
    }

    private static void setUpDisplay() {
        Display.setTitle("Custom Icon");
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            PNGDecoder imageDecoder = new PNGDecoder(new FileInputStream("res/images/logo128.png"));
            if (!imageDecoder.hasAlpha() && imageDecoder.getHeight() != 128 && imageDecoder.getHeight() != 128) {
                System.err.println("Icon does not have transparency info and cannot serve as an icon for the application.");
            }
            Display.create();
            ByteBuffer imageData = BufferUtils.createByteBuffer(4 * imageDecoder.getWidth() * imageDecoder.getHeight());
            imageDecoder.decode(imageData, imageDecoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            imageData.flip();
            System.out.println(Display.setIcon(new ByteBuffer[]{imageData}));
        } catch (LWJGLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         On Windows you should supply at least one 16x16 icon and one 32x32.
         Linux (and similar platforms) expect one 32x32 icon.
         Mac OS X should be supplied one 128x128 icon
         */
        // 16x16, 32x32, 32x32, 128x128
    }
}
