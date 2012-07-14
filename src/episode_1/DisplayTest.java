package episode_1;

/**
 * All the files in this repository may be compiled, ran, modified, and distributed only if you clearly declare that Oskar Veerhoek is the authour of the source files in question.
 **/

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * If this application shows a blank and responsive window
 * and doesn't throw any errors, you know you have installed lwjgl correctly.
 *
 * @author Oskar
 */
public class DisplayTest {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Episode 1 – Display Test");
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
