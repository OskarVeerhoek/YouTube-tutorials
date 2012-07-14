package episode_2;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Creating a simple display.
 *
 * @author Oskar
 */
public class DisplayTest {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Episode 2 – Display Test");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display couldn't be initialized.");
            System.exit(1);
        }

        while (!Display.isCloseRequested()) {
            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }
}
