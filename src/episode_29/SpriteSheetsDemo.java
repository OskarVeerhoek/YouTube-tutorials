package episode_29;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.ImagingTools;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBTextureRectangle.*;

/**
 * Displays the use of sprite sheets.
 * @author Oskar Veerhoek
 */
public class SpriteSheetsDemo {
    private static int spritesheet;
    private static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
    private static Sprite currentSprite;
    private static final String SPRITESHEET_IMAGE_LOCATION = "res/spritesheet.png";
    private static final String SPRITESHEET_XML_LOCATION = "res/spritesheet.xml";
    public static void main(String[] args) {
        setUpDisplay();
        setUpSpriteSheet();
        setUpStates();
        gameLoop();
        cleanUp(false);
    }

    private static void setUpSpriteSheet() {
        spritesheet = ImagingTools.glLoadTextureLinear(SPRITESHEET_IMAGE_LOCATION);
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(new File(SPRITESHEET_XML_LOCATION));
            Element root = document.getRootElement();
            for (Object spriteObject : root.getChildren()) {
                Element spriteElement = (Element) spriteObject;
                String name = spriteElement.getAttribute("n").getValue();
                int x = spriteElement.getAttribute("x").getIntValue();
                int y = spriteElement.getAttribute("y").getIntValue();
                int w = spriteElement.getAttribute("w").getIntValue();
                int h = spriteElement.getAttribute("h").getIntValue();
                Sprite sprite = new Sprite(name, x, y, w, h);
                spriteMap.put(sprite.getName(), sprite);
            }
        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            cleanUp(true);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            cleanUp(true);
        }
    }
    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 640));
            Display.setTitle("Sprite Sheets");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }
    private static void setUpStates() {
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
        currentSprite = spriteMap.get("air");
    }
    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }
    private static void gameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            logic();
            update();
        }
    }
    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);

        while(Keyboard.next()) {
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
                        currentSprite = spriteMap.get("air");
                        break;
                }
            }
        }

        int x = currentSprite.getX();
        int y = currentSprite.getY();
        int x2 = currentSprite.getX() + currentSprite.getWidth();
        int y2 = currentSprite.getY() + currentSprite.getHeight();

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, spritesheet);

        glBegin(GL_QUADS);
        glTexCoord2f(x, y);
        glVertex2f(-1, 1);
        glTexCoord2f(x2, y);
        glVertex2f(1, 1);
        glTexCoord2f(x2, y2);
        glVertex2f(1, -1);
        glTexCoord2f(x, y2);
        glVertex2f(-1, -1);
        glEnd();

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
    }
    private static void logic() { }
    private static void update() {
        Display.update();
        Display.sync(60);
    }
    private static void cleanUp(boolean asCrash) {
        glDeleteTextures(spritesheet);
        Display.destroy();
        System.exit((asCrash) ? 1 : 0);
    }
}
