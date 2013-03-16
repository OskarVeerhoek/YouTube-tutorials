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
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.ImagingTools;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;

/**
 * NOT DONE YET Shows 2D animations.
 *
 * @author Oskar Veerhoek
 */
public class Animations2D {

    private static final String WINDOW_TITLE = "Animations!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static int animationTexture;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, animationTexture);
        glBegin(GL_TRIANGLES);
        glTexCoord2f(0, 0);
        glVertex2f(-1, -1);
        glTexCoord2f(480, 0);
        glVertex2f(1, -1);
        glTexCoord2f(480, 60);
        glVertex2f(1, 1);
        glEnd();
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        // Add input handling code here
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteTextures(animationTexture);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpTextures() {
        animationTexture = ImagingTools.glLoadLinearPNG("res/images/animations.png");
    }

    private static void drawFrame(int frameNumber, int x, int y, int x2, int y2) {
        int height = 60;
        int width = 60;
        int x1 = frameNumber * width;
        int y1 = 0;
        //int x2 = frameNumber * width + width;
        //int y2 = height;
        glBegin(GL_QUADS);

        glEnd();
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
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
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpTextures();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }
}