package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.ImagingTools;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;

/**
 * Put description here.
 *
 * @author Oskar Veerhoek
 */
public class MainMenuDemo {

    private static final String WINDOW_TITLE = "Main Menu";
    private static final int[] WINDOW_DIMENSIONS = {1280, 720};

    private static Map<String, Integer> textures = new HashMap<String, Integer>();

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, textures.get("background"));
        glBegin(GL_QUADS);
        glTexCoord2d(0, 720);
        glVertex2d(-1, -1);
        glTexCoord2d(1280, 720);
        glVertex2d(+1, -1);
        glTexCoord2d(1280, 0);
        glVertex2d(+1, +1);
        glTexCoord2d(0, 0);
        glVertex2d(-1, +1);
        glEnd();
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        // Add input handling code here
    }

    private static void cleanUp(boolean asCrash) {
        // Add cleaning code here
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpTextures() {
        textures.put("background", ImagingTools.glLoadTextureLinear("res/images/background.png"));
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
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
        setUpStates();
        setUpTextures();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
