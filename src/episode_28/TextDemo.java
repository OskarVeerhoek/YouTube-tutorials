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

package episode_28;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import utility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class TextDemo {

    private static UnicodeFont font;
    private static DecimalFormat formatter = new DecimalFormat("#.##");
    private static Model model;

    private static EulerCamera cam;
    private static int shaderProgram;
    private static int vboVertexHandle;
    private static int vboNormalHandle;

    private static FloatBuffer perspectiveProjectionMatrix = BufferTools.reserveData(16);
    private static FloatBuffer orthographicProjectionMatrix = BufferTools.reserveData(16);

    public static final String MODEL_LOCATION = "res/models/bunny.obj";
    public static final String VERTEX_SHADER_LOCATION = "res/shaders/vertex_phong_lighting.vs";
    public static final String FRAGMENT_SHADER_LOCATION = "res/shaders/vertex_phong_lighting.fs";
    public static final int DISPLAY_WIDTH = 640;
    public static final int DISPLAY_HEIGHT = 480;

    public static void main(String[] args) {
        setUpDisplay();
        setUpFonts();
        setUpVBOs();
        setUpCamera();
        setUpShaders();
        setUpLighting();
        while (!Display.isCloseRequested()) {
            render();
            checkInput();
            Display.update();
        }
        cleanUp();
        System.exit(0);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        cam.applyTranslations();
        glUseProgram(shaderProgram);
        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(cam.x(), cam.y(), cam.z(), 1));
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
        glNormalPointer(GL_FLOAT, 0, 0L);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glColor3f(0.4f, 0.27f, 0.17f);
        glMaterialf(GL_FRONT, GL_SHININESS, 10f);
        glDrawArrays(GL_TRIANGLES, 0, model.faces.size() * 3);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glUseProgram(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glMatrixMode(GL_PROJECTION);
        glLoadMatrix(orthographicProjectionMatrix);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        glDisable(GL_LIGHTING);
        font.drawString(10, 10, "EulerCamera: [x=" + formatter.format(cam.x()) +
                ",y=" + formatter.format(cam.y()) + ",z=" + formatter.format(cam.z()) + "]");
        glEnable(GL_LIGHTING);
        glPopMatrix();
        glMatrixMode(GL_PROJECTION);
        glLoadMatrix(perspectiveProjectionMatrix);
        glMatrixMode(GL_MODELVIEW);
    }

    @SuppressWarnings("unchecked")
    private static void setUpFonts() {
        java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        try {
            font.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
            cleanUp();
        }
    }

    private static void setUpLighting() {
        glShadeModel(GL_SMOOTH);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{0.05f,
                0.05f, 0.05f, 1f}));
        glLight(GL_LIGHT0, GL_POSITION,
                BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    private static void setUpVBOs() {
        vboVertexHandle = glGenBuffers();
        vboNormalHandle = glGenBuffers();
        model = null;
        try {
            model = OBJLoader.loadModel(new File(MODEL_LOCATION));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        }
        FloatBuffer vertices = BufferTools.reserveData(model.faces.size() * 9);
        FloatBuffer normals = BufferTools.reserveData(model.faces.size() * 9);
        for (Face face : model.faces) {
            vertices.put(BufferTools.asFloats(model.vertices.get((int) face.vertex.x - 1)));
            vertices.put(BufferTools.asFloats(model.vertices.get((int) face.vertex.y - 1)));
            vertices.put(BufferTools.asFloats(model.vertices.get((int) face.vertex.z - 1)));
            normals.put(BufferTools.asFloats(model.normals.get((int) face.normal.x - 1)));
            normals.put(BufferTools.asFloats(model.normals.get((int) face.normal.y - 1)));
            normals.put(BufferTools.asFloats(model.normals.get((int) face.normal.z - 1)));
        }
        vertices.flip();
        normals.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
    }

    private static void setUpCamera() {
        cam = new EulerCamera((float) Display.getWidth()
                / (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
        cam.setFieldOfView(70);
        cam.applyPerspectiveMatrix();
        glGetFloat(GL_PROJECTION_MATRIX, perspectiveProjectionMatrix);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glGetFloat(GL_PROJECTION_MATRIX, orthographicProjectionMatrix);
        glLoadMatrix(perspectiveProjectionMatrix);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
            Display.setVSyncEnabled(true);
            Display.setTitle("Dynamic Text Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }
    }

    private static void checkInput() {
        cam.processMouse(1, 80, -80);
        cam.processKeyboard(16, 1, 1, 1);
        if (Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if (Mouse.isButtonDown(1))
            Mouse.setGrabbed(false);
    }

    private static void cleanUp() {
        glDeleteProgram(shaderProgram);
        glDeleteBuffers(vboVertexHandle);
        glDeleteBuffers(vboNormalHandle);
        Display.destroy();
        System.exit(0);
    }
}
