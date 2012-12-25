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
import org.lwjgl.util.vector.Vector3f;
import utility.BufferTools;
import utility.EulerCamera;
import utility.ShaderLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private static int heightmapDisplayList;
    /**
     * [] is z
     * [][] is x
     */
    private static float[][] heights = new float[200][200];
    private static Vector3f[][] normals = new Vector3f[heights.length][heights[0].length];

    private static void render() {
        glLoadIdentity();
        camera.applyTranslations();
        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{camera.x(), camera.y(), camera.z(), 1}));
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glCallList(heightmapDisplayList);
    }

    private static void logic() {

    }

    private static void input() {
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
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_NORMALIZE);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    private static void setUpLighting() {
        glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
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

    private static void setUpHeightmap() {
        try {
            BufferedImage heightmapImage = ImageIO.read(new File("res/images/heightmap.bmp"));
            int pixel = (heightmapImage.getRGB(0, 0) >> 16) & 0xff;
            for (int z = 0; z < heights.length; z++) {
                for (int x = 0; x < heights[z].length; x++) {
                    heights[z][x] = ((heightmapImage.getRGB(z, x) >> 16) & 0xff);
                }
            }
            // http://stackoverflow.com/questions/2499545/getting-greyscale-pixel-value-from-rgb-colourspace-in-java-using-bufferedimage
            for (int z = 0; z < heights.length; z++) {
                for (int x = 0; x < heights[z].length; x++) {
                    // http://www.videotutorialsrock.com/opengl_tutorial/terrain/text.php
                    Vector3f sum = new Vector3f(0, 0, 0);
                    Vector3f out = new Vector3f(0, 0, 0);
                    if (z > 0) {
                        out = new Vector3f(0, heights[z - 1][x], -1);
                    }
                    Vector3f in = new Vector3f(0, 0, 0);
                    if (z < 1 - heights.length) {
                        in = new Vector3f(0, heights[z + 1][x] - heights[z][x], 1);
                    }
                    Vector3f left = new Vector3f(0, 0, 0);
                    if (x > 0) {
                        left = new Vector3f(-1, heights[z][x - 1] - heights[z][x], 0);
                    }
                    Vector3f right = new Vector3f(0, 0, 0);
                    if (x < heights[z].length - 1) {
                        right = new Vector3f(1, heights[z][x + 1], 0);
                    }
                    if (x > 0 && z > 0) {
                        Vector3f outcrossleft = new Vector3f();
                        Vector3f.cross(out, left, outcrossleft);
                        if (outcrossleft.length() != 0)
                            Vector3f.add((Vector3f) outcrossleft.normalise(), sum, sum);
                    }
                    if (x > 0 && z < heights.length - 1) {
                        Vector3f leftcrossin = new Vector3f();
                        Vector3f.cross(left, in, leftcrossin);
                        if (leftcrossin.length() != 0)
                            Vector3f.add((Vector3f) leftcrossin.normalise(), sum, sum);
                    }
                    if (x < heights[z].length - 1 && z < 1 - heights.length) {
                        Vector3f incrossright = new Vector3f();
                        Vector3f.cross(in, right, incrossright);
                        if (incrossright.length() != 0)
                            Vector3f.add((Vector3f) incrossright.normalise(), sum, sum);
                    }
                    if (x < heights[z].length - 1 && z > 0) {
                        Vector3f rightcrossout = new Vector3f();
                        Vector3f.cross(right, out, rightcrossout);
                        if (rightcrossout.length() != 0)
                            Vector3f.add((Vector3f) rightcrossout.normalise(), sum, sum);
                    }
                    normals[z][x] = sum;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        heightmapDisplayList = glGenLists(1);
        glNewList(heightmapDisplayList, GL_COMPILE);
        glScalef(0.1f, 0.03f, 0.1f);
        for (int z = 0; z < heights.length - 1; z++) {
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < heights[z].length; x++) {
                glColor3f(heights[z][x]/255, heights[z][x]/255, heights[z][x]/255);
                glNormal3f(normals[z][x].x, normals[z][x].y, normals[z][x].z);
                glVertex3f(x, heights[z][x], z);
                glColor3f(heights[z + 1][x] / 255, heights[z + 1][x] / 255, heights[z + 1][x] / 255);
                glNormal3f(normals[z + 1][x].x, normals[z + 1][x].y, normals[z + 1][x].z);
                glVertex3f(x, heights[z+1][x], z+1);
            }
            glEnd();
        }
        glEndList();
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setVSyncEnabled(true);
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
        setUpLighting();
        setUpHeightmap();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
