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

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import utility.EulerCamera;
import utility.ShaderLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * A 3D voxel terrain loaded from a heightmap and a lookup texture. Press 'L' to reload the shader and texture files.
 * Press 'P' to switch between normal and wireframe mode. Click here for an image:
 * https://twitter.com/i/#!/CodingUniverse/media/slideshow?url=pic.twitter.com%2FDgMdZ5jm.
 *
 * @author Oskar Veerhoek
 */
public class VoxelTerrain {

    private static final String WINDOW_TITLE = "Voxel Terrain!";
    private static final int[] WINDOW_DIMENSIONS = {1280, 720};
    private static final float ASPECT_RATIO = (float) WINDOW_DIMENSIONS[0] / (float) WINDOW_DIMENSIONS[1];
    private static final EulerCamera camera = new EulerCamera.Builder().setPosition(-5.4f, 19.2f,
            33.2f).setRotation(30, 61, 0).setAspectRatio(ASPECT_RATIO).setFieldOfView(60).build();
    /** The shader program that will use the lookup texture and the heightmap's vertex data to draw the terrain. */
    private static int shaderProgram;
    /** The texture that will be used to find out which colours correspond to which heights. */
    private static int lookupTexture;
    /** The display list that will contain the heightmap's vertex data. */
    private static int heightmapDisplayList;
    /**
     * The points of the height. The first dimension represents the z-coordinate. The second dimension represents the
     * x-coordinate. The float value represents the height.
     */
    private static float[][] data;

    private static void render() {
        glLoadIdentity();
        camera.applyTranslations();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        //        glCallList(heightmapDisplayList);
        glScalef(0.2f, 0.06f, 0.2f);
        for (int z = 0; z < data.length - 1; z++) {
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < data[z].length; x++) {
                float y = data[z][x];
                glVertex3f(x, y, z);
                glVertex3f(x, y, z + 1);
            }
            glEnd();
        }
        for (int z = 1; z < data.length - 1; z++) {
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < data[z].length; x++) {
                float y = data[z][x];
                glVertex3f(x, y, z);
                glVertex3f(x, data[z - 1][x], z);
            }
            glEnd();
        }
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_L) {
                    // Reload the shaders and the heightmap data.
                    glUseProgram(0);
                    glDeleteProgram(shaderProgram);
                    glBindTexture(GL_TEXTURE_2D, 0);
                    glDeleteTextures(lookupTexture);
                    setUpShaders();
                    setUpHeightmap();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_P) {
                    // Switch between normal mode and wireframe mode.
                    int polygonMode = glGetInteger(GL_POLYGON_MODE);
                    if (polygonMode == GL_LINE) {
                        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
                    } else if (polygonMode == GL_FILL) {
                        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
                    }
                }
            }
        }
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
        if (Mouse.isGrabbed()) {
            camera.processMouse(1, 80, -80);
        }
        camera.processKeyboard(16, 1);
    }

    private static void cleanUp(boolean asCrash) {
        glUseProgram(0);
        glDeleteProgram(shaderProgram);
        glDeleteLists(heightmapDisplayList, 1);
        glBindTexture(GL_TEXTURE_2D, 0);
        glDeleteTextures(lookupTexture);
        System.err.println(GLU.gluErrorString(glGetError()));
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        camera.applyPerspectiveMatrix();
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair("res/shaders/landscape_voxel.vs", "res/shaders/landscape_voxel.fs");
        glUseProgram(shaderProgram);
    }

    private static void setUpStates() {
        camera.applyOptimalStates();
        glEnable(GL_DEPTH_TEST);
        // Set the blue sky background.
        glClearColor(0, 0.75f, 1, 1);
        //        glEnable(GL_CULL_FACE);
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            input();
            update();
        }
    }

    private static void setUpHeightmap() {
        try {
            BufferedImage heightmapImage = ImageIO.read(new File("res/images/heightmap.bmp"));
            data = new float[heightmapImage.getWidth()][heightmapImage.getHeight()];
            for (int z = 0; z < data.length; z++) {
                for (int x = 0; x < data[z].length; x++) {
                    data[z][x] = ((heightmapImage.getRGB(z, x) >> 16) & 0xff);
                }
            }
            FileInputStream heightmapinfoInputStream = new FileInputStream("res/images/heightmap_lookup.png");
            PNGDecoder decoder = new PNGDecoder(heightmapinfoInputStream);
            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            heightmapinfoInputStream.close();
            buffer.flip();
            glBindTexture(GL_TEXTURE_2D, lookupTexture);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA,
                    GL_UNSIGNED_BYTE, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        glActiveTexture(GL_TEXTURE0);
        // Use GL_LINEAR for smooth terrain.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        heightmapDisplayList = glGenLists(1);
        glNewList(heightmapDisplayList, GL_COMPILE);
        glScalef(0.2f, 0.06f, 0.2f);
        for (int z = 0; z < data.length - 1; z++) {
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < data[z].length; x++) {
                float y = data[z][x];
                glVertex3f(x, y, z);
                glVertex3f(x, y, z + 1);
                //                glVertex3f(x, data[z][x], z);
                //                glVertex3f(x, data[z + 1][x], z + 1);
            }
            glEnd();
        }
        for (int z = 1; z < data.length - 1; z++) {
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < data[z].length; x++) {
                float y = data[z][x];
                glVertex3f(x, y, z);
                glVertex3f(x, data[z - 1][x], z);
                //                glVertex3f(x, data[z][x], z);
                //                glVertex3f(x, data[z + 1][x], z + 1);
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
        setUpHeightmap();
        setUpShaders();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }
}
