/*
 * Copyright (c) 2012, Oskar Veerhoek
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

    public static void main(String[] args) {
        // Create a Display with 640 width, 480 height, and title "Side Scroller"
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Side Scroller");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
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
}
