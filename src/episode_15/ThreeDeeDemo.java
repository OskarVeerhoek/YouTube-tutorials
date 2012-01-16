package episode_15;

import java.util.Random;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Shows a space-like particle simulation.
 *
 * @author Oskar
 */
public class ThreeDeeDemo {

    public ThreeDeeDemo() {
        // Initialization code Display
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Three Dee Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        //

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // Create a new perspective with 30 degree angle (field of view), 640 / 480 aspect ratio, 0.001f zNear, 100 zFar
        // Note: 	+x is to the right
        //     		+y is to the top
        //			+z is to the camera
        gluPerspective((float) 30, 640f / 480f, 0.001f, 100);
        glMatrixMode(GL_MODELVIEW);
        //

        glEnable(GL_DEPTH_TEST);

        // Initialization code random points
        Point[] points = new Point[10000];
        Random random = new Random();
        // Iterate of every array index
        for (int i = 0; i < points.length; i++) {
            // Set the point at the array index to 
            // x = random between -50 and +50
            // y = random between -50 and +50
            // z = random between  0  and -200
            points[i] = new Point((random.nextFloat() - 0.5f) * 100f, (random.nextFloat() - 0.5f) * 100f, random.nextInt(200) - 200);
        }
        // The speed in which the "camera" travels
        float speed = 0.0f;
        //

        while (!Display.isCloseRequested()) {
            // Render

            // Clear the screen of its filthy contents
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Push the screen inwards at the amount of speed
            glTranslatef(0, 0, speed);

            // Begin drawing points
            glBegin(GL_POINTS);
            // Iterate of every point
            for (Point p : points) {
                // Draw the point at its coordinates
                glVertex3f(p.x, p.y, p.z);
            }
            // Stop drawing points
            glEnd();


            // If we're pressing the "up" key increase our speed
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                speed += 0.01f;
            }
            // If we're pressing the "down" key decrease our speed
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                speed -= 0.01f;
            }
            // Iterate over keyboard input events
            while (Keyboard.next()) {
                // If we're pressing the "space-bar" key reset our speed to zero
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    speed = 0f;
                }
                // If we're pressing the "c" key reset our speed to zero and reset our position
                if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                    speed = 0;
                    glLoadIdentity();
                }
            }

            // Update the display
            Display.update();
            // Wait until the frame-rate is 60fps
            Display.sync(60);
        }

        // Dispose of the display
        Display.destroy();
        // Exit the JVM (for some reason this lingers on Macintosh)
        System.exit(0);
    }

    private static class Point {

        float x, y, z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ThreeDeeDemo();
    }
}
