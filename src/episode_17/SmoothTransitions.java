/*
 * Copyright (c) 2013, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package episode_17;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Showcases the use of 'smooth' transitions (non-linear), using the Math.sin() function.
 *
 * @author Oskar
 */
public class SmoothTransitions {

    private static enum State {

        /**
         * The state where the applications waits for user input and draws nothing. If enter is pressed the state is set
         * to fading
         */
        INTRO,
        /**
         * The state where the actual fading in happens. A blue rectangle slowly appears. When the opacity is at 100%,
         * the state is set to main.
         */
        FADING,
        /**
         * The state where the rectangle is shown without transparency. If the user presses enter the state is set to
         * intro.
         */
        MAIN
    }

    /** Default state is intro. */
    private static State state = State.INTRO;

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Smooth Transitions");
            // Remove screen tearing
            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // (0, 0) is center, (1, 1) is the upper-right, (-1, -1) is the bottom-left
        glOrtho(1, -1, 1, -1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        // Enable translucency
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
}
