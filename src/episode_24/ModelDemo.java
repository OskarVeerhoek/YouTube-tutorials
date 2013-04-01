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

package episode_24;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import utility.Camera;
import utility.EulerCamera;
import utility.Model;
import utility.OBJLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Loads the Stanford bunny .obj file and draws it.
 *
 * @author Oskar Veerhoek
 */
public class ModelDemo {

    private static Camera camera;
    private static int bunnyDisplayList;

    private static final String MODEL_LOCATION = "res/models/bunny.obj";

    public static void main(String[] args) {
        setUpDisplay();
        setUpDisplayLists();
        setUpCamera();
        while (!Display.isCloseRequested()) {
            render();
            checkInput();
            Display.update();
            Display.sync(60);
        }
        cleanUp();
        System.exit(0);
    }

    private static void setUpDisplayLists() {
        bunnyDisplayList = glGenLists(1);
        glNewList(bunnyDisplayList, GL_COMPILE);
        {
            Model m = null;
            try {
                m = OBJLoader.loadModel(new File(MODEL_LOCATION));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Display.destroy();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                Display.destroy();
                System.exit(1);
            }
            glBegin(GL_TRIANGLES);
            for (Model.Face face : m.getFaces()) {
                Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
            glEnd();
        }
        glEndList();
    }

    private static void checkInput() {
        camera.processMouse(1, 80, -80);
        camera.processKeyboard(16, 1, 1, 1);
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
    }

    private static void cleanUp() {
        glDeleteLists(bunnyDisplayList, 1);
        Display.destroy();
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        camera.applyTranslations();
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glCallList(bunnyDisplayList);
    }

    private static void setUpCamera() {
        camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
                .setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
        camera.applyOptimalStates();
        camera.applyPerspectiveMatrix();
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.setTitle("Happy Easter!");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }
    }
}