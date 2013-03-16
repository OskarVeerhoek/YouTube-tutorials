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

package episode_35;

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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * A 3D terrain loaded from a height-map and a lookup texture. Press 'L' to reload the shader and texture files. Press
 * 'P' to switch between normal, point, and wire-frame mode. Press 'F' to flatten the terrain. Click here for an image:
 * https://twitter.com/i/#!/CodingUniverse/media/slideshow?url=pic.twitter.com%2FDgMdZ5jm.
 *
 * @author Oskar Veerhoek
 */
public class TerrainDemo {

    private static final String WINDOW_TITLE = "Terrain!";
    private static final int[] WINDOW_DIMENSIONS = {1200, 650};
    private static final float ASPECT_RATIO = (float) WINDOW_DIMENSIONS[0] / (float) WINDOW_DIMENSIONS[1];
    private static final EulerCamera camera = new EulerCamera.Builder().setPosition(-5.4f, 19.2f,
            33.2f).setRotation(30, 61, 0).setAspectRatio(ASPECT_RATIO).setFieldOfView(60).build();
    /** The shader program that will use the lookup texture and the height-map's vertex data to draw the terrain. */
    private static int shaderProgram;
    /** The texture that will be used to find out which colours correspond to which heights. */
    private static int lookupTexture;
    /** The display list that will contain the height-map's vertex data. */
    private static int heightmapDisplayList;
    /**
     * The points of the height. The first dimension represents the z-coordinate. The second dimension represents the
     * x-coordinate. The float value represents the height.
     */
    private static float[][] data;
    /** Whether the terrain should vary in height or be displayed on a grid. */
    private static boolean flatten = false;

    private static void render() {
        // Clear the pixels on the screen and clear the contents of the depth buffer (3D contents of the scene)
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // Reset any translations the camera made last frame update
        glLoadIdentity();
        // Apply the camera position and orientation to the scene
        camera.applyTranslations();
        if (flatten) {
            glScalef(1, 0, 1);
        }
        // Render the heightmap using the shaders that are being used
        glCallList(heightmapDisplayList);
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                    flatten = !flatten;
                }
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
                    // Switch between normal mode, point mode, and wire-frame mode.
                    int polygonMode = glGetInteger(GL_POLYGON_MODE);
                    if (polygonMode == GL_LINE) {
                        glPolygonMode(GL_FRONT, GL_FILL);
                    } else if (polygonMode == GL_FILL) {
                        glPolygonMode(GL_FRONT, GL_POINT);
                    } else if (polygonMode == GL_POINT) {
                        glPolygonMode(GL_FRONT, GL_LINE);
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

    private static void setUpHeightmap() {
        try {
            // Load the heightmap-image from its resource file
            BufferedImage heightmapImage = ImageIO.read(new File("res/images/heightmap.bmp"));
            // Initialise the data array, which holds the heights of the heightmap-vertices, with the correct dimensions
            data = new float[heightmapImage.getWidth()][heightmapImage.getHeight()];
            // Lazily initialise the convenience class for extracting the separate red, green, blue, or alpha channels
            // an int in the default RGB color model and default sRGB colourspace.
            Color colour;
            // Iterate over the pixels in the image on the x-axis
            for (int z = 0; z < data.length; z++) {
                // Iterate over the pixels in the image on the y-axis
                for (int x = 0; x < data[z].length; x++) {
                    // Retrieve the colour at the current x-location and y-location in the image
                    colour = new Color(heightmapImage.getRGB(z, x));
                    // Store the value of the red channel as the height of a heightmap-vertex in 'data'. The choice for
                    // the red channel is arbitrary, since the heightmap-image itself only has white, gray, and black.
                    data[z][x] = colour.getRed();
                }
            }
            // Create an input stream for the 'lookup texture', a texture that will used by the fragment shader to
            // determine which colour matches which height on the heightmap
            FileInputStream heightmapLookupInputStream = new FileInputStream("res/images/heightmap_lookup.png");
            // Create a class that will give us information about the image file (width and height) and give us the
            // texture data in an OpenGL-friendly manner
            PNGDecoder decoder = new PNGDecoder(heightmapLookupInputStream);
            // Create a ByteBuffer in which to store the contents of the texture. Its size is the width multiplied by
            // the height and 4, which stands for the amount of bytes a float is in Java.
            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
            // 'Decode' the texture and store its data in the buffer we just created
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            // Make the contents of the ByteBuffer readable to OpenGL (and unreadable to us)
            buffer.flip();
            // Close the input stream for the heightmap 'lookup texture'
            heightmapLookupInputStream.close();
            // Generate a texture handle for the 'lookup texture'
            lookupTexture = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, lookupTexture);
            // Hand the texture data to OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA,
                    GL_UNSIGNED_BYTE, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Use the GL_NEAREST texture filter so that the sampled texel (texture pixel) is not smoothed out. Usually
        // using GL_NEAREST will make the textured shape appear pixelated, but in this case using the alternative,
        // GL_LINEAR, will make the sharp transitions between height-colours ugly.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        // Generate a display list handle for the display list that will store the heightmap vertex data
        heightmapDisplayList = glGenLists(1);
        // TODO: Add alternative VBO rendering for pseudo-compatibility with version 3 and higher.
        glNewList(heightmapDisplayList, GL_COMPILE);
        // Scale back the display list so that its proportions are acceptable.
        glScalef(0.2f, 0.06f, 0.2f);
        // Iterate over the 'strips' of heightmap data.
        for (int z = 0; z < data.length - 1; z++) {
            // Render a triangle strip for each 'strip'.
            glBegin(GL_TRIANGLE_STRIP);
            for (int x = 0; x < data[z].length; x++) {
                // Take a vertex from the current strip
                glVertex3f(x, data[z][x], z);
                // Take a vertex from the next strip
                glVertex3f(x, data[z + 1][x], z + 1);
            }
            glEnd();
        }
        glEndList();
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair("res/shaders/landscape.vs", "res/shaders/landscape.fs");
        glUseProgram(shaderProgram);
        // The following call is redundant because the default value is already 0, but illustrates how you would use
        // multiple textures
        glUniform1i(glGetUniformLocation(shaderProgram, "lookup"), 0);
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

    private static void setUpStates() {
        camera.applyOptimalStates();
        glPointSize(2);
        // Enable the sorting of shapes from far to near
        glEnable(GL_DEPTH_TEST);
        // Set the background to a blue sky colour
        glClearColor(0, 0.75f, 1, 1);
        // Remove the back (bottom) faces of shapes for performance
        glEnable(GL_CULL_FACE);
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
