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
    /**
     * [] is z
     * [][] is x
     */
    private static float[][] heights = new float[200][200];

    private static void render() {
        glLoadIdentity();
        camera.applyTranslations();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glScalef(0.1f, 0.01f, 0.1f);
        for (int z = 0; z < heights.length - 1; z++) {
        glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < heights[z].length; x++) {
                glColor3f(heights[z][x]/255, heights[z][x]/255, heights[z][x]/255);
                glVertex3f(x, heights[z][x], z);
                glColor3f(heights[z + 1][x] / 255, heights[z + 1][x] / 255, heights[z + 1][x] / 255);
                glVertex3f(x, heights[z+1][x], z+1);
            }
        glEnd();
        }
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
            System.out.println(pixel);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        setUpHeightmap();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
