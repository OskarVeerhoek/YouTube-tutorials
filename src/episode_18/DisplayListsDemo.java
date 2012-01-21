package episode_18;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Renders a colored triangle using display lists.
 * @author Oskar
 */
public class DisplayListsDemo {

    public DisplayListsDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Display Lists Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        int displayListHandle = glGenLists(1);

        glNewList(displayListHandle, GL_COMPILE);
        glBegin(GL_TRIANGLES);
        glColor3f(1, 0, 0);
        glVertex2f(-0.5f, -0.5f);
        glColor3f(0, 1, 0);
        glVertex2f(0.5f, -0.5f);
        glColor3f(0, 0, 1);
        glVertex2f(0.5f, 0.5f);
        glEnd();
        glEndList();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

            glCallList(displayListHandle);

            Display.update();
            Display.sync(60);
        }

        glDeleteLists(displayListHandle, 1);
        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new DisplayListsDemo();
    }
}