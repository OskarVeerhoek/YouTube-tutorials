package episode_3;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Shows some basic shape drawing.
 *
 * @author Oskar
 */
public class SimpleOGLRenderer {

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Hello, LWJGL!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            // Start rendering rectangles
            glBegin(GL_QUADS);
            glVertex2i(400, 400); // Upper-left
            glVertex2i(450, 400); // Upper-right
            glVertex2i(450, 450); // Bottom-right
            glVertex2i(400, 450); // Bottom-left
            // Stop rendering rectangles
            glEnd();
            // Start rendering lines
            glBegin(GL_LINES);
            glVertex2i(100, 100);
            glVertex2i(200, 200);
            // Stop rendering lines
            glEnd();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
}
