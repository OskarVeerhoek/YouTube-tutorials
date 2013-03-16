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

package episode_34;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.BufferTools;
import utility.EulerCamera;
import utility.OBJLoader;
import utility.ShaderLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Uses the Phong lighting model to display a tasty chocolate bunny.
 *
 * @author Oskar Veerhoek
 */
public class PerPixelLighting {

    private static int shaderProgram, bunny;

    private static final String MODEL_LOCATION = "res/models/bunny.obj";
    private static final String VERTEX_SHADER_LOCATION = "res/shaders/pixel_phong_lighting.vs";
    private static final String FRAGMENT_SHADER_LOCATION = "res/shaders/pixel_phong_lighting.fs";

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.setTitle("Shader Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }

        EulerCamera cam = new EulerCamera((float) Display.getWidth() / (float) Display.getHeight(), -2.19f, 1.36f,
                11.45f);
        cam.setFieldOfView(60);
        cam.applyPerspectiveMatrix();

        setUpShaders();
        setUpLighting();
        setUpDisplayLists();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glLoadIdentity();
            cam.applyTranslations();

            glUseProgram(shaderProgram);
            glCallList(bunny);
            glUseProgram(0);

            cam.processMouse(1, 80, -80);
            cam.processKeyboard(16, 1, 1, 1);

            if (Mouse.isButtonDown(0)) {
                Mouse.setGrabbed(true);
            } else if (Mouse.isButtonDown(1)) {
                Mouse.setGrabbed(false);
            }

            Display.update();
            Display.sync(60);
        }
        glDeleteProgram(shaderProgram);
        glDeleteLists(bunny, 1);
        Display.destroy();
        System.exit(0);
    }

    private static void setUpDisplayLists() {
        try {
            bunny = OBJLoader.createDisplayList(OBJLoader.loadModel(new File(MODEL_LOCATION)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            glDeleteProgram(shaderProgram);
            glDeleteLists(bunny, 1);
            Display.destroy();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            glDeleteProgram(shaderProgram);
            glDeleteLists(bunny, 1);
            Display.destroy();
            System.exit(1);
        }
    }

    private static void setUpLighting() {
        glShadeModel(GL_SMOOTH);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
    }
}
