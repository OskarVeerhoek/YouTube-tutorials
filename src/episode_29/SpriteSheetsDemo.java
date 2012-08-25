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

package episode_29;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.ImagingTools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;

/**
 * Shows how to use sprite sheets.
 *
 * @author Oskar Veerhoek
 */
public class SpriteSheetsDemo {

    private static final String WINDOW_TITLE = "Sprite Sheets Demo";
    private static final int[] WINDOW_DIMENSIONS = {480, 480};

    private static int spritesheet;
    private static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
    private static Sprite currentSprite;
    private static final String SPRITESHEET_IMAGE_LOCATION = "res/images/spritesheet.png";
    private static final String SPRITESHEET_XML_LOCATION = "res/images/spritesheet.xml";

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, spritesheet);

        int x = currentSprite.getX();
        int y = currentSprite.getY();
        int x2 = currentSprite.getX() + currentSprite.getWidth();
        int y2 = currentSprite.getY() + currentSprite.getHeight();

        glBegin(GL_QUADS);
        glTexCoord2f(x, y);
        glVertex2f(-1, 1);
        glTexCoord2f(x, y2);
        glVertex2f(-1, -1);
        glTexCoord2f(x2, y2);
        glVertex2f(1, -1);
        glTexCoord2f(x2, y);
        glVertex2f(1, 1);
        glEnd();

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_1:
                        currentSprite = spriteMap.get("stone");
                        break;
                    case Keyboard.KEY_2:
                        currentSprite = spriteMap.get("dirt");
                        break;
                    case Keyboard.KEY_3:
                        currentSprite = spriteMap.get("grass");
                        break;
                    case Keyboard.KEY_4:
                        currentSprite = spriteMap.get("dirt");
                        break;
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteTextures(spritesheet);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(1, -1, 1, -1, -1f, 1f);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpSpriteSheets() {
        spritesheet = ImagingTools.glLoadLinearPNG(SPRITESHEET_IMAGE_LOCATION);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(new File(SPRITESHEET_XML_LOCATION));
            Element root = document.getRootElement();
            for (Object spriteObject : root.getChildren()) {
                Element spriteElement = (Element) spriteObject;
                String name = spriteElement.getAttributeValue("n");
                int x = spriteElement.getAttribute("x").getIntValue();
                int y = spriteElement.getAttribute("y").getIntValue();
                int w = spriteElement.getAttribute("w").getIntValue();
                int h = spriteElement.getAttribute("h").getIntValue();
                Sprite sprite = new Sprite(name, x, y, w, h);
                spriteMap.put(sprite.getName(), sprite);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
            cleanUp(true);
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        currentSprite = spriteMap.get("air");
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
        setUpSpriteSheets();
        setUpStates();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
