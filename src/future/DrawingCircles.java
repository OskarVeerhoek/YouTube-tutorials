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
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * Draws a circle. Then I got carried away, and this happened. Try it!
 *
 * @author Oskar Veerhoek
 */
public class DrawingCircles {

    /**
     * Draws a circle at [0 0 0] with the given radius.
     *
     * @param radius the radius of the circle
     */
    private static void drawCirclef(float radius) {
        glBegin(GL_TRIANGLE_FAN);
        for (float angle = 0; angle < 360; angle += 0.5) {
            glVertex2d(Math.sin(angle) * radius, Math.cos(angle) * radius);
        }
        glEnd();
    }

    /**
     * Draws a circle at [0 0 0] with the given radius and precision.
     *
     * @param radius    the radius of the circle
     * @param precision the precision of the circle (1.0 is normal, lower is higher precision)
     */
    private static void drawCircleWithPrecisionf(float radius, float precision) {
        glBegin(GL_TRIANGLE_FAN);
        for (float angle = 0; angle < 360; angle += precision) {
            glVertex2d(Math.sin(angle) * radius, Math.cos(angle) * radius);
        }
        glEnd();
    }

    private static float step = 3;
    private static float speed = 0.0001f;

    private static void render() {
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        String error = GLU.gluErrorString(glGetError());
        if (!error.equals("No error")) {
            System.err.println(error);
        }
        if (step < 0.001f) {
            step = 180;
        } else {
            step -= speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            speed += 0.0000001f;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            speed -= 0.0000001f;
        }
        drawCircleWithPrecisionf(1, step);
    }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(500, 500));
            Display.setVSyncEnabled(true);
            Display.setTitle("Drawing Circles");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
            }
            render();
            Display.update();
            Display.sync(60);
        }
        System.err.println(GLU.gluErrorString(glGetError()));
        Display.destroy();
        System.exit(0);
    }

}
