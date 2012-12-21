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
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;
import utility.BufferTools;
import utility.EulerCamera;
import utility.ShaderLoader;

import static org.lwjgl.opengl.GL11.*;

/**
 * Still in crude draft phase.
 *
 * @author Oskar Veerhoek
 */
public class TerrainDemo {

    private static final String WINDOW_TITLE = "Terrain!";
    private static final int[] WINDOW_DIMENSIONS = {1280, 720};
    private static final float ASPECT_RATIO = (float) WINDOW_DIMENSIONS[0] / (float) WINDOW_DIMENSIONS[1];
    private static final EulerCamera camera = new EulerCamera.Builder()
            .setAspectRatio(ASPECT_RATIO)
            .setFieldOfView(60)
            .build();
    private static int shaderProgram;

    private static void render() {
        glLoadIdentity();
        camera.applyTranslations();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        GL20.glUseProgram(shaderProgram);
        glScalef(3, 3, 3);
        glBegin(GL_TRIANGLES);
        glNormal3f(0, 0, 1);
        glVertex3f(-1, +1, 0);
        glNormal3f(0, 0, 1);
        glVertex3f(+1, -1, 0);
        glNormal3f(0, 0, 1);
        glVertex3f(+1, +1, 0);
        glEnd();
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
//        camera.setRoll(camera.roll() + 0.01f);
//        Mouse.setGrabbed(true);
        if (Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if (Mouse.isButtonDown(1))
            Mouse.setGrabbed(false);
        if (Mouse.isGrabbed())
            camera.processMouse(1, 80, -80);
        camera.processKeyboard(16, 1);
    }

    private static void cleanUp(boolean asCrash) {
        System.err.println(GLU.gluErrorString(glGetError()));
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        camera.applyPerspectiveMatrix();
    }

    private static void setUpStates() {
        camera.applyOptimalStates();
        glEnable(GL_LIGHTING);
        glDisable(GL_LIGHT0);
        glEnable(GL_LIGHT0);
        glEnable(GL_NORMALIZE);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(
                new float[]{1, 1, 1, 1f}));
        glLight(GL_LIGHT0, GL_POSITION,
                BufferTools.asFlippedFloatBuffer(new float[]{camera.x(), camera.y(), camera.z(), 1}));
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            // TODO: Remove: only for debugging
            setUpStates();
            render();
            logic();
            input();
            update();
        }
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setVSyncEnabled(true);
            Display.setTitle(WINDOW_TITLE);
            Display.create(new PixelFormat().withSamples(8));
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair("res/shaders/vertex_phong_lighting.vs", "res/shaders/vertex_phong_lighting.fs");
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpMatrices();
        setUpShaders();
        enterGameLoop();
        cleanUp(false);
    }

}
