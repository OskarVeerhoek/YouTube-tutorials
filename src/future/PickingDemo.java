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
import utility.BufferTools;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluErrorString;

/**
 * Showcases OpenGL_ picking. Press the left mouse button over either one of the triangles and look at the console
 * output.
 */
public class PickingDemo {

    private static final String WINDOW_TITLE = "Picking Demo";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static final FloatBuffer pickingTriangleColour = BufferTools.asFloatBuffer(1, 0, 0);
    private static final FloatBuffer pickingOtherTriangleColour = BufferTools.asFloatBuffer(0, 1, 0);
    private static final FloatBuffer realTriangleColour = BufferTools.asFloatBuffer(1, 0, 0);
    private static final FloatBuffer realOtherTriangleColour = BufferTools.asFloatBuffer(0, 0, 1);

    /** The frame-buffer object that will contain our custom render buffer */
    private static int frameBuffer;
    /** The render-buffer that will store the picking rendering */
    private static int renderBuffer;

    private static void cleanUp(boolean asCrash) {
        glDeleteFramebuffers(frameBuffer);
        glDeleteRenderbuffers(renderBuffer);
        System.err.println(gluErrorString(glGetError()));
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void render() {
        // Draw the shapes with their picking colours to our custom frame-buffer.
        glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
        glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
        // Set the background colour to black so it won't interfere with the picking.
        glClearColor(0, 0, 0, 0);
        // Draw the triangle with their colours that are used for picking.
        glBegin(GL_TRIANGLES);
        glColor3f(pickingTriangleColour.get(0), pickingTriangleColour.get(1), pickingTriangleColour.get(2));
        glVertex2f(-1, -1);
        glVertex2f(0, -1);
        glVertex2f(-1, +1);
        glColor3f(pickingOtherTriangleColour.get(0), pickingOtherTriangleColour.get(1),
                pickingOtherTriangleColour.get(2));
        glVertex2f(1, -1);
        glVertex2f(0, -1);
        glVertex2f(1, 1);
        glEnd();
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        // Draw the triangles with their real colours.
        glClear(GL_COLOR_BUFFER_BIT);
        glBegin(GL_TRIANGLES);
        glColor3f(realTriangleColour.get(0), realTriangleColour.get(1), realTriangleColour.get(2));
        glVertex2f(-1, -1);
        glVertex2f(0, -1);
        glVertex2f(-1, +1);
        glColor3f(realOtherTriangleColour.get(0), realOtherTriangleColour.get(1), realOtherTriangleColour.get(2));
        glVertex2f(1, -1);
        glVertex2f(0, -1);
        glVertex2f(1, 1);
        glEnd();
        Display.update();
    }

    private static void update() {
        while (Mouse.next()) {
            // If the left mouse button has been pressed ...
            if (Mouse.isButtonDown(0) && Mouse.getEventButtonState()) {
                glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
                glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
                FloatBuffer pixels = BufferTools.reserveData(3);
                // Read the pixel colour at the mouse coordinates and store the information (red-green-blue) in
                // "pixels".
                glReadPixels(Mouse.getX(), Mouse.getY(), 1, 1, GL_RGB, GL_FLOAT, pixels);
                // If the pixel colour equals the triangle's picking colour ...
                if (BufferTools.bufferEquals(pixels, pickingTriangleColour, 3)) {
                    System.out.println("Left Triangle!");
                    // If the pixel colour equals the other triangle's picking colour ...
                } else if (BufferTools.bufferEquals(pixels, pickingOtherTriangleColour, 3)) {
                    System.out.println("Right Triangle!");
                    // If the pixel colour equals neither of the triangles' picking colours.
                } else {
                    System.out.println("No Triangle!");
                }
                glBindRenderbuffer(GL_RENDERBUFFER, 0);
                glBindFramebuffer(GL_FRAMEBUFFER, 0);
            }
        }
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            update();
        }
    }

    private static void setUpFBOs() {
        frameBuffer = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
        renderBuffer = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
        System.err.println(gluErrorString(glGetError()));
        glRenderbufferStorage(GL_RENDERBUFFER, GL_RGB, Display.getWidth(), Display.getHeight());
        System.err.println(gluErrorString(glGetError()));
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_RENDERBUFFER, renderBuffer);
        System.err.println(gluErrorString(glGetError()));
        final int frameBufferError = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if (frameBufferError != GL_FRAMEBUFFER_COMPLETE) {
            System.err.println("Frame Buffer is not complete: " + frameBufferError);
        } else {
            System.err.println("Frame Buffer is complete.");
        }
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
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
        setUpFBOs();
        render();
        enterGameLoop();
        cleanUp(false);
    }
}