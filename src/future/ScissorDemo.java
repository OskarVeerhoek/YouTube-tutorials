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

package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Shows how to use OpenGL scissoring to render a specific part of the viewport. Press the left mouse button to drag the
 * bottom-left corner of the scissor box. Press the right mouse button to drag the upper-right corner of the scissor
 * box.
 *
 * @author Oskar Veerhoek
 */
public class ScissorDemo {

    private static final String WINDOW_TITLE = "Scissoring!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    /**
     * The dimensions of the scissor box. [0] is the left x-coordinate of the scissor box [1] is the bottom y-coordinate
     * of the scissor box [2] is the right x-coordinate of the scissor box [3] is the right y-coordinate of the scissor
     * box
     */
    private static final int[] scissorBox = {0, 0, WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]};
    /**
     * The location of the mouse cursor relative to the application window. [0] is the x-coordinate of the mouse cursor
     * (0 is left) [1] is the y-coordinate of the mouse cursor (0 is bottom)
     */
    private static final int[] mouseLocation = {0, 0};
    /**
     * The mouse button which is being pressed. -1 stands for no mouse button 0 stands for the left mouse button 1
     * stands for the right mouse button
     */
    private static int mouseButtonPressed = -1;

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        enterGameLoop();
        cleanUp(false);
    }

    private static void render() {
        // Fills the entire window (or scissor box) with black.
        glClear(GL_COLOR_BUFFER_BIT);
        /**
         * The left x-coordinate of the scissor box.
         */
        int scissorX = scissorBox[0];
        /**
         * The bottom y-coordinate of the scissor box.
         */
        int scissorY = scissorBox[1];
        /**
         * The width of the scissor box. May be negative.
         */
        int scissorWidth = scissorBox[2] - scissorBox[0];
        /**
         * The height of the scissor box. May be negative.
         */
        int scissorHeight = scissorBox[3] - scissorBox[1];
        // If scissorWidth and scissorHeight are not negative ...
        /* If the width and height of the scissor box are negative. */
        boolean scissorInverted = false;
        if (scissorWidth >= 0 && scissorHeight >= 0) {
            // Specify the scissor box.
            glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
            // Fill the entire window with white. Due to scissoring
            // only the an area that is equal to the scissor box will
            // be drawn.
            glRectf(-1, -1, 1, 1);
            // Since scissorWidth and scissorHeight are not negative,
            // set scissorInverted to false.
            scissorInverted = false;
        } else {
            // Since scissorWidth and scissorHeight are negative,
            // set scissorInverted to false.
            scissorInverted = true;
        }
        glScissor(0, 0, WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]);
        glPushAttrib(GL_CURRENT_BIT);
        if (scissorInverted) {
            glColor3f(1, 0, 0);
        } else {
            glColor3f(0, 1, 0);
        }
        glBegin(GL_POINTS);
        glVertex2f(scissorBox[0] / (WINDOW_DIMENSIONS[0] / 2f) - 1, scissorBox[1] / (WINDOW_DIMENSIONS[1] / 2f) - 1);
        glVertex2f(scissorBox[2] / (WINDOW_DIMENSIONS[0] / 2f) - 1, scissorBox[3] / (WINDOW_DIMENSIONS[1] / 2f) - 1);
        glEnd();
        glPopAttrib();
    }

    private static void logic() {
        if (mouseButtonPressed == 0) {
            scissorBox[0] = mouseLocation[0];
            scissorBox[1] = mouseLocation[1];
        } else if (mouseButtonPressed == 1) {
            scissorBox[2] = mouseLocation[0];
            scissorBox[3] = mouseLocation[1];
        }
    }

    private static void input() {
        mouseLocation[0] = Mouse.getX();
        mouseLocation[1] = Mouse.getY();
        if (Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) {
            mouseButtonPressed = 0;
        } else if (Mouse.isButtonDown(1) && !Mouse.isButtonDown(0)) {
            mouseButtonPressed = 1;
        } else {
            mouseButtonPressed = -1;
        }
    }

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpStates() {
        glEnable(GL_SCISSOR_TEST);
        glPointSize(25);
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            logic();
            input();
            update();
        }
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setVSyncEnabled(true);
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }
}
