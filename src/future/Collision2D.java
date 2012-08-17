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
import utility.BufferTools;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * 2D Collision with circles and rectangles.
 *
 * @author Oskar Veerhoek
 */
public class Collision2D {

    private static int[] circlePosition = {300, 300};
    private static float[] circleColour = {0, 1, 0};
    private static int circleRadius = 80;
    private static int[] rectangleBounds = {100, 100, 150, 150};
    private static float[] rectangleColour = {0, 1, 0};
    private static int[] triangleBounds = {600, 200, 450, 200, 450, 350};
    private static float[] triangleColour = {0, 1, 0};

    private static final String WINDOW_TITLE = "2D Collision!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);

        glBegin(GL_TRIANGLE_FAN);
        glColor3d(0.9, 0.9, 0.9);
        glVertex2f(640 / 2f, 480 / 2f);
        glColor3d(0.8, 0.8, 0.8);
        glVertex2f(0, 480);
        glVertex2f(0, 0);
        glVertex2f(640, 0);
        glVertex2f(640, 480);
        glVertex2f(0, 480);
        glEnd();

        glColor3f(circleColour[0], circleColour[1], circleColour[2]);
        glCircle3i(circlePosition[0], circlePosition[1], circleRadius);
        glColor3f(rectangleColour[0], rectangleColour[1], rectangleColour[2]);
        glRectf(rectangleBounds[0], rectangleBounds[1], rectangleBounds[2], rectangleBounds[3]);
        glColor3f(triangleColour[0], triangleColour[1], triangleColour[2]);
        glBegin(GL_TRIANGLES);
        glVertex2f(triangleBounds[0], triangleBounds[1]);
        glVertex2f(triangleBounds[2], triangleBounds[3]);
        glVertex2f(triangleBounds[4], triangleBounds[5]);
        glEnd();
    }

    private static boolean triangleContainsPoint(int[] triangleBounds, int[] pointLocation) {
        FloatBuffer buffer = BufferTools.reserveData(3);
        glReadPixels(pointLocation[0], 480 - pointLocation[1] - 1, 1, 1, GL_RGB, GL_FLOAT, buffer);
        System.out.println(buffer.get(0) + ", " + buffer.get(1) + ", " + buffer.get(2));
        if (buffer.get(0) > 0.9f && buffer.get(2) < 0.1f && buffer.get(1) < 0.1f) {
            return true;
        } else if (buffer.get(1) > 0.9f && buffer.get(0) < 0.1f && buffer.get(2) < 0.1f) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean rectangleContainsPoint(int[] rectangleBounds, int[] pointLocation) {
        if (pointLocation[0] > rectangleBounds[0] && pointLocation[0] < rectangleBounds[2] && pointLocation[1] > rectangleBounds[1] && pointLocation[1] < rectangleBounds[3])
            return true;
        else
            return false;
    }

    private static boolean circleContainsPoint(int[] circlePosition, int circleRadius, int[] pointLocation) {
        float dx = pointLocation[0] - circlePosition[0];
        float dy = pointLocation[1] - circlePosition[1];
        float distance_squared = dx * dx + dy * dy;
        float radius_squared = circleRadius * circleRadius;
        if (distance_squared < radius_squared)
            return true;
        else
            return false;
    }

    private static void logic() {
        int[] mousePosition = {Mouse.getX(), 480 - Mouse.getY() - 1};
        if (circleContainsPoint(circlePosition, circleRadius, mousePosition)) {
            circleColour[0] = 1;
            circleColour[1] = 0;
        } else {
            circleColour[0] = 0;
            circleColour[1] = 1;
        }
        if (rectangleContainsPoint(rectangleBounds, mousePosition)) {
            rectangleColour[0] = 1;
            rectangleColour[1] = 0;
        } else {
            rectangleColour[0] = 0;
            rectangleColour[1] = 1;
        }
        if (triangleContainsPoint(triangleBounds, mousePosition)) {
            triangleColour[0] = 1;
            triangleColour[1] = 0;
        } else {
            triangleColour[0] = 0;
            triangleColour[1] = 1;
        }
    }

    private static void input() {
        // Add input handling code here
    }

    private static void cleanUp(boolean asCrash) {
        // Add cleaning code here
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpStates() {
        glEnable(GL_POLYGON_SMOOTH);
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_POINT_SMOOTH);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    private static void glCircle3i(int x, int y, int radius) {
        float angle;
        glPushMatrix();
        glLoadIdentity();
        boolean textureEnabled = glIsEnabled(GL_TEXTURE_2D);
        if (textureEnabled)
            glDisable(GL_TEXTURE_2D);
        glLineWidth(1);
        glBegin(GL_POLYGON);
        for (int i = 0; i < 100; i++) {
            angle = (float) (i * 2 * Math.PI / 100);
            glVertex2d(x + (Math.cos(angle) * radius), y
                    + (Math.sin(angle) * radius));
        }
        glEnd();
        if (textureEnabled)
            glEnable(GL_TEXTURE_2D);
        glPopMatrix();
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
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
