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

package episode_18;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.nio.FloatBuffer;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Shows a space-like particle simulation with advanced rendering techniques.
 *
 * @author Oskar
 */
public class AdvancedRendering {

    private static long lastFrame;

    /**
     * All the different render modes
     */
    private static enum RenderMode {
        IMMEDIATE, DISPLAY_LISTS, VERTEX_ARRAYS, VERTEX_BUFFER_OBJECTS;
    }

    private static RenderMode mode;

    public static float getDelta() {
        long time = getTime();
        float delta = (float) (time - lastFrame);
        lastFrame = time;
        return delta;
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    // The speed in which the "camera" travels
    static float speed = 0.0f;
    //

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Three Dee Demo");
            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        // I set the zFar to 1000000 so we can see all the points. (benchmarking purposes)
        gluPerspective((float) 30, Display.getWidth() / Display.getHeight(), 0.001f, 1000000000000L);
        glMatrixMode(GL_MODELVIEW);
        // I enable this to tell OpenGL to re-check the validity of the 3D drawing.
        glEnable(GL_DEPTH_TEST);

        Point[] points = new Point[3000000];
        Random random = new Random();
        for (int i = 0; i < points.length; i++) {
            // I altered the zFar variable to adapt to the points.length.
            // Points, no matter how much, now appear evenly distributed along the screen
            points[i] = new Point((random.nextFloat() - 0.5f) * 100f, (random.nextFloat() - 0.5f) * 100f, random.nextInt(points.length / 50) - points.length / 50);
        }

        /**
         * Display lists
         */

        // Generate a handle to a display list 
        int displayList = glGenLists(1);
        // Create the display list using the displayList handle
        // All subsequent calls for rendering will be stored in the display list
        glNewList(displayList, GL_COMPILE);
        glBegin(GL_POINTS);
        for (Point p : points) {
            glVertex3f(p.x, p.y, p.z);
        }
        glEnd();
        // Stop storing calls in the display list and compile
        glEndList();

        /**
         * Vertex Arrays and Vertex Buffer Objects
         */

        // Create a new FloatBuffer (complex array of floats) with the capacity
        // of the length of the points * 3 (because we have 3 vertices per point)
        FloatBuffer vertexArray = BufferUtils.createFloatBuffer(points.length * 3);
        // Iterate over all the points and store them in the FloatBuffer
        for (Point p : points) {
            vertexArray.put(new float[]{p.x, p.y, p.z});
        }
        // Make the buffer read-able for OpenGL
        vertexArray.flip();

        // Create the handle for the VBO
        int vertexBufferObject = glGenBuffers();
        // Bind the VBO for usage (in this case: storing information)
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        // Store all the contents of the FloatBuffer in the VBO. 
        glBufferData(GL_ARRAY_BUFFER, vertexArray, GL_STATIC_DRAW);
        // Unbind the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        lastFrame = getTime();

        System.out.println("Render mode set to Immediate Mode.");
        mode = RenderMode.IMMEDIATE;

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // I divided by 16 because you get a delta of 16 at 60fps.
            // If it's 60fps, the delta will be 1.0f, and all input calls
            // will be the same as before.
            float delta = getDelta() / 16f;

            glTranslatef(0, 0, speed * delta);

            switch (mode) {
                case DISPLAY_LISTS:
                    // Draw our display list
                    glCallList(displayList);
                    break;
                case VERTEX_BUFFER_OBJECTS:
                    // Enable Vertex Arrays (VBOs)
                    glEnableClientState(GL_VERTEX_ARRAY);
                    // Tell OpenGL to use our VBO
                    glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
                    // Tell OpenGL to find the data in the bound VBO with 3 co
                    // mponents (xyz) and with the type float.
                    glVertexPointer(3, GL_FLOAT, 0, 0L);
                    // Tell OpenGL to draw the data supplied by the pointer method
                    // as points.length amount of points
                    glDrawArrays(GL_POINTS, 0, points.length);
                    // Unbind the VBO
                    glBindBuffer(GL_ARRAY_BUFFER, 0);
                    // Disable Vertex Arrays (VBOs)
                    glDisableClientState(GL_VERTEX_ARRAY);
                    break;
                case VERTEX_ARRAYS:
                    // Enable Vertex Arrays 
                    glEnableClientState(GL_VERTEX_ARRAY);
                    // Tell OpenGL to find the data in the vertexArray buffer
                    // with 3 components (xyz)
                    glVertexPointer(3, 0, vertexArray);
                    // Tell OpenGL to draw the data supplied by the pointer method
                    // as points.length amount of points
                    glDrawArrays(GL_POINTS, 0, points.length);
                    // Disable Vertex Arrays 
                    glDisableClientState(GL_VERTEX_ARRAY);
                    break;
                case IMMEDIATE:
                    glBegin(GL_POINTS);
                    for (Point p : points) {
                        glVertex3f(p.x, p.y, p.z);
                    }
                    glEnd();
                    break;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                speed += 0.01f * delta;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                speed -= 0.01f * delta;
            }
            while (Keyboard.next()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    speed = 0f;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
                    System.out.println("Render mode set to Vertex Buffer Objects.");
                    mode = RenderMode.VERTEX_BUFFER_OBJECTS;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
                    System.out.println("Render mode set to Vertex Arrays.");
                    mode = RenderMode.VERTEX_ARRAYS;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
                    System.out.println("Render mode set to Display Lists.");
                    mode = RenderMode.DISPLAY_LISTS;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
                    System.out.println("Render mode set to Immediate Mode.");
                    mode = RenderMode.IMMEDIATE;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                    speed = 0;
                    glLoadIdentity();
                }

            }

            Display.update();
            Display.sync(30);
        }

        // Delete our display list
        glDeleteLists(displayList, 1);
        // Delete our VBO
        glDeleteBuffers(vertexBufferObject);

        Display.destroy();
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
}
