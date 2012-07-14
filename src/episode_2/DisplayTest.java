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
            // Sets the width of the display to 640 and the height to 480
            Display.setDisplayMode(new DisplayMode(640, 480));
            // Sets the title of the display to "Episode 2 - Display"
            Display.setTitle("Episode 2 – Display");
            // Creates and shows the display
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // While we aren't pressing the red button on the display
        while (!Display.isCloseRequested()) {
            // Update the contents of the display and check for input
            Display.update();
            // Wait until we reach 60 frames-per-second
            Display.sync(60);
        }
        // Destroy the display and render it invisible
        Display.destroy();
        System.exit(0);
    }
}
