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

package episode_3;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

// Terminology:
// - Vertex: a point in either 2D or 3D space
// - Primitive: a simple shape consisting of one or more vertices

/**
 * Shows some basic shape drawing.
 *
 * @author Oskar
 */
public class SimpleOGLRenderer {

    public static void main(String[] args) {
        try {
            // Sets the width of the display to 640 and the height to 480
            Display.setDisplayMode(new DisplayMode(640, 480));
            // Sets the title of the display to "Episode 2 - Display"
            Display.setTitle("Episode 3 - OpenGL Rendering");
            // Creates and shows the display
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        // Enter the state that is required for modify the projection. Note that, in contrary to Java2D, the vertex
        // coordinate system does not have to be equal to the window coordinate space. The invocation to glOrtho creates
        // a 2D vertex coordinate system like this:
        // Upper-Left:  (0,0)   Upper-Right:  (640,0)
        // Bottom-Left: (0,480) Bottom-Right: (640,480)
        // If you skip the glOrtho method invocation, the default 2D projection coordinate space will be like this:
        // Upper-Left:  (-1,+1) Upper-Right:  (+1,+1)
        // Bottom-Left: (-1,-1) Bottom-Right: (+1,-1)
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        // While we aren't pressing the red button on the display
        while (!Display.isCloseRequested()) {
            // Clear the 2D contents of the window.
            glClear(GL_COLOR_BUFFER_BIT);
            // ">>" denotes a possibly modified piece of OpenGL documentation (http://www.opengl.org/sdk/docs/man/)
            // >> glBegin and glEnd delimit the vertices that define a primitive or a group of like primitives.
            // >> glBegin accepts a single argument that specifies how the vertices are interpreted.
            // All upcoming vertex calls will be taken as points of a quadrilateral until glEnd is called. Since
            // this primitive requires four vertices, we will have to call glVertex four times.
            glBegin(GL_QUADS);
            // >> glVertex commands are used within glBegin/glEnd pairs to specify point, line, and polygon vertices.
            // >> glColor sets the current colour. (All subsequent calls to glVertex will be assigned this colour)
            // >> The number after 'glVertex'/'glColor' indicates the amount of components. (xyzw/rgba)
            // >> The character after the number indicates the type of arguments.
            // >>      (for 'glVertex' = d: Double, f: Float, i: Integer)
            // >>      (for 'glColor'  = d: Double, f: Float, b: Signed Byte, ub: Unsigned Byte)
            glColor3f(1.0f, 0.0f, 0.0f);                    // Pure Green
            glVertex2i(0, 0);                               // Upper-left
            glColor3b((byte) 0, (byte) 127, (byte) 0);      // Pure Red
            glVertex2d(640.0, 0.0);                         // Upper-right
            glColor3ub((byte) 255, (byte) 255, (byte) 255); // White
            glVertex2f(640.0f, 480.0f);                     // Bottom-right
            glColor3d(0.0d, 0.0d, 1.0d);                    // Pure Blue
            glVertex2i(0, 480);                             // Bottom-left
            // If we put another four calls to glVertex2i here, a second quadrilateral will be drawn.
            glEnd();
            // Update the contents of the display and check for input.
            Display.update();
            // Wait until we reach 60 frames-per-second.
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }
}
