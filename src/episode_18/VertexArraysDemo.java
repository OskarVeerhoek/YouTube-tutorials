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

package episode_18;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Renders a colored triangle using Vertex Arrays
 *
 * @author Oskar
 */
public class VertexArraysDemo {

    private VertexArraysDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Vertex Arrays Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(1, -1, 1, -1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        final int amountOfVertices = 3;
        final int vertexSize = 2;
        final int colorSize = 3;

        FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        vertexData.put(new float[]{-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f});
        vertexData.flip();

        FloatBuffer colorData = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
        colorData.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
        colorData.flip();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

            glEnableClientState(GL_VERTEX_ARRAY);
            glEnableClientState(GL_COLOR_ARRAY);

            glVertexPointer(vertexSize, 0, vertexData);
            glColorPointer(colorSize, 0, colorData);

            glDrawArrays(GL_TRIANGLES, 0, amountOfVertices);

            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new VertexArraysDemo();
    }
}