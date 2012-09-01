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

package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Put description here.
 *
 * @author Oskar Veerhoek
 */
public class ScissorDemo {

    private static final String WINDOW_TITLE = "Scissoring!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static int scissorBox[] = {0, 0, WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]};
    private static int mouseLocation[] = {0, 0};
    private static int mouseButtonPressed = -1;
    private static boolean scissorInverted = false;

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        enterGameLoop();
        cleanUp(false);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        int scissorX = scissorBox[0];
        int scissorY = scissorBox[1];
        int scissorWidth = scissorBox[2] - scissorBox[0];
        int scissorHeight = scissorBox[3] - scissorBox[1];
        if (scissorWidth > 0 && scissorHeight > 0) {
            glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
            glRectf(-1, -1, 1, 1);
            scissorInverted = false;
        } else {
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
        glVertex2f(scissorBox[0] / (WINDOW_DIMENSIONS[0]/2f) - 1, scissorBox[1] / (WINDOW_DIMENSIONS[1]/2f) - 1);
        glVertex2f(scissorBox[2] / (WINDOW_DIMENSIONS[0]/2f) - 1, scissorBox[3] / (WINDOW_DIMENSIONS[1]/2f) - 1);
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
