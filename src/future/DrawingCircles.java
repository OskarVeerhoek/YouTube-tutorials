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
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class DrawingCircles {

    private static void glCircle3f(float x, float y, float radius) {
        glBegin(GL_TRIANGLE_FAN);
        glVertex2f(x, y);
        for (float angle = 0; angle < 360; angle += 0.5) {
            glVertex2d(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius);
        }
        glEnd();
//		float angle;
//		glPushMatrix();
//		glLoadIdentity();
//		boolean textureEnabled = glIsEnabled(GL_TEXTURE_2D);
//		if (textureEnabled)
//			glDisable(GL_TEXTURE_2D);
//		glColor3f(0, 1, 0);
//		glLineWidth(1);
//		glBegin(GL_LINE_LOOP);
//		for (int i = 0; i < 100; i++) {
//			angle = (float) (i * 2 * Math.PI / 100);
//			glVertex2d(x + (Math.cos(angle) * radius), y
//					+ (Math.sin(angle) * radius));
//		}
//		glEnd();
//		if (textureEnabled)
//			glEnable(GL_TEXTURE_2D);
//		glPopMatrix();
    }

    private static void render() {
        glCircle3f(0, 0, 1);
    }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 640));
            Display.setTitle("Drawing Circles");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
//        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            render();
            Display.update();
//			Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }

}
