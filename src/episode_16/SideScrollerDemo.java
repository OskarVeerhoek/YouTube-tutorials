package episode_16;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Show-cases a side-scroller application for 2D games using glPushMatrix and
 * glPopMatrix
 *
 * @author Oskar
 */
public class SideScrollerDemo {

    public SideScrollerDemo() {
        // Create a Display with 640 width, 480 height, and title "Side Scroller"
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Side Scroller");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Set-up an orthographic presentation where (0, 0) is the upper-left corner and (640, 480) is the bottom right one.
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);

        // Translation along the x-axis
        float translate_x = 0;

        // Enter the main render loop
        while (!Display.isCloseRequested()) {
            // Clear the screen
            glClear(GL_COLOR_BUFFER_BIT);

            // Put another matrix, a clone of the current one, on the matrix stack.
            glPushMatrix();

            // Push the screen to the left or to the right, depending on translate_x.
            glTranslatef(translate_x, 0, 0);

            // If we're pressing the space-bar and the mouse is inside the window, increase/decrease our
            // translate_x by the dynamic x movement of the mouse.
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)
                    && Mouse.getX() > 0 && Mouse.getX() < 639) {
                translate_x += Mouse.getDX();
            }

            // Retrieve the "true" coordinates of the mouse.
            float mousex = Mouse.getX() - translate_x;
            float mousey = 480 - Mouse.getY() - 1;

            System.out.println("Mouse: x " + mousex + ", y " + mousey);

            // Do some OpenGL rendering (code from SimpleOGLRenderer.java).
            glBegin(GL_QUADS);
            glVertex2i(400, 400); // Upper-left
            glVertex2i(450, 400); // Upper-right
            glVertex2i(450, 450); // Bottom-right
            glVertex2i(400, 450); // Bottom-left
            glEnd();

            glBegin(GL_LINES);
            glVertex2i(100, 100);
            glVertex2i(200, 200);
            glEnd();

            // Dispose of the translations on the matrix.
            glPopMatrix();

            // Make sure the display stays responsive and wait until we reach 60fps.
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
        new SideScrollerDemo();
    }
}
