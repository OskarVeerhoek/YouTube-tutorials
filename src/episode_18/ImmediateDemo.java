package episode_18;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Renders a colored triangle using immediate mode.
 * @author Oskar
 */
public class ImmediateDemo {

    public ImmediateDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Immediate Mode Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

            glBegin(GL_TRIANGLES);
            glColor3f(1, 0, 0);
            glVertex2f(-0.5f, -0.5f);
            glColor3f(0, 1, 0);
            glVertex2f(0.5f, -0.5f);
            glColor3f(0, 0, 1);
            glVertex2f(0.5f, 0.5f);
            glEnd();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new ImmediateDemo();
    }
}