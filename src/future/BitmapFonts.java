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
import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glTexImage2D;


public class BitmapFonts {

    private static final String WINDOW_TITLE = "Bitmap Fonts";
    private static final int[] WINDOW_DIMENSIONS = {1024, 512};

    private static int fontTexture;
    private static char[][] renderString = new char[5][25];
    private static int header = 0;
    private static int line = 0;

    static {
        for (int lineNumber = 0; lineNumber < renderString.length; lineNumber++) {
            for (int i = 0; i < 15; i++) {
                renderString[lineNumber][i] = ' ';
            }
        }
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        renderStringAt(new String(renderString[0]), 60, 450, 980, 480);
        renderStringAt(new String(renderString[1]), 60, 420, 980, 450);
        renderStringAt(new String(renderString[2]), 60, 390, 980, 420);
        renderStringAt(new String(renderString[3]), 60, 360, 980, 390);
        renderStringAt(new String(renderString[4]), 60, 330, 980, 360);
    }

    private static void renderStringAt(String string, int x, int y, int x2, int y2) {
        // TODO: Remove unnecessary glBegin/glEnd
        int length = (x2 - x) / string.length();
        int height = y2 - y;
        glPushMatrix();
        glTranslatef(x, y, 0);
        for (int i = 0; i < string.length(); i++) {
            renderCharacterAt(string.charAt(i), length * i - length, 0, length * i, height);
        }
        glPopMatrix();
    }

    private static void renderCharacterAt(char character, int x, int y, int x2, int y2) {
        int asciiCode = (int) character;
        final float cellSize = 1.0f / 16.0f;
        float cellX = asciiCode % 16 * cellSize;
        float cellY = asciiCode / 16 * cellSize;
        glBegin(GL_QUADS);
        glTexCoord2f(cellX, cellY + cellSize);
        glVertex2f(x, y);
        glTexCoord2f(cellX + cellSize, cellY + cellSize);
        glVertex2f(x2, y);
        glTexCoord2f(cellX + cellSize, cellY);
        glVertex2f(x2, y2);
        glTexCoord2f(cellX, cellY);
        glVertex2f(x, y2);
        glEnd();
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() != Keyboard.KEY_BACK) {
                    renderString[line][header++] = Keyboard.getEventCharacter();
                } else {
                    renderString[line][header] = ' ';
                    header = header -1 < 0 ? header : header - 1;
                }
                if (header == renderString[line].length) {
                    header = 0;
                    line = line == renderString.length - 1 ? 0 : line + 1;
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteTextures(fontTexture);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 1024, 0, 512, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpTextures() throws IOException {
        fontTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        PNGDecoder decoder = new PNGDecoder(new FileInputStream("res/images/bitmapfont.png"));
        ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
        decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        buffer.flip();
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_2D);
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

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        try {
            setUpTextures();
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp(true);
        }
        setUpMatrices();
        setUpStates();
        enterGameLoop();
        cleanUp(false);
    }
}