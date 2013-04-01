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

package episode_37;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class BitmapFonts {

    /** The string that is rendered on-screen. */
    private static final StringBuilder renderString = new StringBuilder("Enter your text");
    /** The texture object for the bitmap font. */
    private static int fontTexture;

    public static void main(String[] args) {
        setUpDisplay();
        try {
            setUpTextures();
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp(true);
        }
        setUpStates();
        enterGameLoop();
        cleanUp(false);
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(new int[]{640, 480}[0], new int[]{640, 480}[1]));
            Display.setTitle("Bitmap Fonts");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    private static void setUpTextures() throws IOException {
        // Create a new texture for the bitmap font.
        fontTexture = glGenTextures();
        // Bind the texture object to the GL_TEXTURE_2D target, specifying that it will be a 2D texture.
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        // Use TWL's utility classes to load the png file.
        PNGDecoder decoder = new PNGDecoder(new FileInputStream("res/images/font.png"));
        ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
        decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        buffer.flip();
        // Load the previously loaded texture data into the texture object.
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
                buffer);
        // Unbind the texture.
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private static void setUpStates() {
        glClearColor(0, 0.3f, 0, 1);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            input();
            update();
        }
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        renderString(renderString.toString(), fontTexture, 16, -0.9f, 0, 0.3f, 0.225f);
    }

    /**
     * Renders text using a font bitmap.
     *
     * @param string the string to render
     * @param textureObject the texture object containing the font glyphs
     * @param gridSize the dimensions of the bitmap grid (e.g. 16 -> 16x16 grid; 8 -> 8x8 grid)
     * @param x the x-coordinate of the bottom-left corner of where the string starts rendering
     * @param y the y-coordinate of the bottom-left corner of where the string starts rendering
     * @param characterWidth the width of the character
     * @param characterHeight the height of the character
     */
    private static void renderString(String string, int textureObject, int gridSize, float x, float y,
                                     float characterWidth, float characterHeight) {
        glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureObject);
        // Enable linear texture filtering for smoothed results.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        // Enable additive blending. This means that the colours will be added to already existing colours in the
        // frame buffer. In practice, this makes the black parts of the texture become invisible.
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        // Store the current model-view matrix.
        glPushMatrix();
        // Offset all subsequent (at least up until 'glPopMatrix') vertex coordinates.
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        // Iterate over all the characters in the string.
        for (int i = 0; i < string.length(); i++) {
            // Get the ASCII-code of the character by type-casting to integer.
            int asciiCode = (int) string.charAt(i);
            // There are 16 cells in a texture, and a texture coordinate ranges from 0.0 to 1.0.
            final float cellSize = 1.0f / gridSize;
            // The cell's x-coordinate is the greatest integer smaller than remainder of the ASCII-code divided by the
            // amount of cells on the x-axis, times the cell size.
            float cellX = ((int) asciiCode % gridSize) * cellSize;
            // The cell's y-coordinate is the greatest integer smaller than the ASCII-code divided by the amount of
            // cells on the y-axis.
            float cellY = ((int) asciiCode / gridSize) * cellSize;
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * characterWidth / 3, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y + characterHeight);
            glTexCoord2f(cellX, cellY);
            glVertex2f(i * characterWidth / 3, y + characterHeight);
        }
        glEnd();
        glPopMatrix();
        glPopAttrib();
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                // Reset the string if we press escape.
                if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                    renderString.setLength(0);
                }
                // Append the pressed key to the string if the key isn't the back key or the shift key.
                if (Keyboard.getEventKey() != Keyboard.KEY_BACK) {
                    if (Keyboard.getEventKey() != Keyboard.KEY_LSHIFT) {
                        renderString.append(Keyboard.getEventCharacter());
                        //                        renderString.append((char) Keyboard.getEventCharacter() - 1);
                    }
                    // If the key is the back key, shorten the string by one character.
                } else if (renderString.length() > 0) {
                    renderString.setLength(renderString.length() - 1);
                }
            }
        }
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteTextures(fontTexture);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }
}