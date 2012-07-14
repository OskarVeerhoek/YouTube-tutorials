package episode_17;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Showcases the use of 'smooth' transitions (non-linear), using the Math.sin()
 * function.
 *
 * @author Oskar
 */
public class SmoothTransitions {

    private static enum State {

        /**
         * The state where the applications waits for user input and draws
         * nothing. If enter is pressed the state is set to fading
         */
        INTRO,
        /**
         * The state where the actual fading in happens. A blue rectangle slowly
         * appears. When the opacity is at 100%, the state is set to main.
         */
        FADING,
        /**
         * The state where the rectangle is shown without transparency. If the
         * user presses enter the state is set to intro.
         */
        MAIN;
    }

    /**
     * Default state is intro.
     */
    private State state = State.INTRO;

    public SmoothTransitions() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Smooth Transitions");
            // Remove screen tearing
            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Creation of the display failed ;(");
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // (0, 0) is center, (1, 1) is the upper-right, (-1, -1) is the bottom-left
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        // Enable trancluency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        /**
         * Amount of fade (in degrees, ranging from 0 to 90)
         */
        float fade = 0f;

        while (!Display.isCloseRequested()) {
            // Clear the screen
            glClear(GL_COLOR_BUFFER_BIT);

            switch (state) {
                case FADING:
                    // Increment the fade by 0.5 if it's not already 90 (which is the max)
                    if (fade < 90) {
                        // A higher number means faster fading, and vice versa.
                        fade += 1.5f;
                        // If our fade is 90 or is larger than 90, reset it, 
                        // draw a fully opaque blue rectangle, and set the state to MAIN
                    } else {
                        fade = 0;
                        glColor3f(0.5f, 0.5f, 1f);
                        glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                        state = State.MAIN;
                        System.out.println("State changed: " + state);
                        break;
                    }
                    // Set the colour to blue and the opacity to the sin(fade)
                    // Because the Math.sin() function takes radians instead of
                    // degrees, we'll have to convert fade to degrees using
                    // Math.toRadians(fade)
                    glColor4f(0.5f, 0.5f, 1f, (float) Math.sin(Math.toRadians(fade)));
                    // Draw the rectangle
                    glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                    break;
                case INTRO:
                    // Do nothing
                    break;
                case MAIN:
                    // Draw a fully opaque blue rectangle
                    glColor3f(0.5f, 0.5f, 1f);
                    glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
                    break;
            }

            while (Keyboard.next()) {
                // If we've pressed enter, enter the switch statement
                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    // Sets the state to:
                    // fading -> main
                    // main   -> intro
                    // intro  -> fading
                    switch (state) {
                        case FADING:
                            fade = 0;
                            state = State.MAIN;
                            System.out.println("State changed: " + state);
                            break;
                        case INTRO:
                            state = State.FADING;
                            System.out.println("State changed: " + state);
                            break;
                        case MAIN:
                            state = State.INTRO;
                            System.out.println("State changed: " + state);
                            break;
                    }
                }
            }

            // Update the display
            Display.update();
            // Synchronize to 60 fps
            Display.sync(60);
        }

        // Release the display system resources
        Display.destroy();
        // Exit the JVM
        System.exit(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new SmoothTransitions();
    }
}
