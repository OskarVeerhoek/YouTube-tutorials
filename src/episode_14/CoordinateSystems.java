package episode_14;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Explains the use of glOrtho
 *
 * @author Oskar
 */
public class CoordinateSystems {

    public CoordinateSystems() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Coordinate Systems");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        //glOrtho(0, 640, 480, 0, 1, -1);
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        while (!Display.isCloseRequested()) {
            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            glPointSize(10f);

            glBegin(GL_TRIANGLES);
            glVertex2f(-1, -1);
            glVertex2f(1, -1);
            glVertex2f(1, 1);
            glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new CoordinateSystems();
    }
}
