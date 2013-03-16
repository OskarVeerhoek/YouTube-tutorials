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

package utility;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Checks whether the EulerCamera class works.
 *
 * @author Oskar Veerhoek
 */
public class CameraTest {

    public static void main(String args[]) throws LWJGLException {
        Display.setVSyncEnabled(true);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setResizable(true);
        Display.create();
        EulerCamera camera = new EulerCamera.Builder().setAspectRatio(640f / 480f).setFieldOfView(60).build();
        camera.applyPerspectiveMatrix();
        camera.applyOptimalStates();
        Mouse.setGrabbed(true);
        while (!Display.isCloseRequested()) {
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            camera.setAspectRatio((float) Display.getWidth() / Display.getHeight());
            if (Display.wasResized()) {
                camera.applyPerspectiveMatrix();
            }
            while (Keyboard.next()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    Mouse.setGrabbed(false);
                }
            }
            System.out.println(camera);
            GL11.glLoadIdentity();
            camera.applyTranslations();
            if (Mouse.isGrabbed()) {
                camera.processMouse();
                camera.processKeyboard(16);
            }
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            GL11.glRectf(-1, -1, 1, 1);
            Display.sync(60);
            Display.update();
        }
        Display.destroy();
        System.exit(0);
    }
}
