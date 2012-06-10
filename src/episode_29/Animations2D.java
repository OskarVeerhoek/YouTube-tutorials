package episode_29;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import utility.ImagingTools;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBTextureRectangle.*;

/**
 * Shows 2D animations.
 * @author Oskar Veerhoek
 */
public class Animations2D {

    private static final String WINDOW_TITLE = "Animations!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static int animationTexture;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, animationTexture);
        glBegin(GL_TRIANGLES);
        glTexCoord2f(0, 0);
        glVertex2f(-1, -1);
        glTexCoord2f(480, 0);
        glVertex2f(1, -1);
        glTexCoord2f(480, 60);
        glVertex2f(1, 1);
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
        glDeleteTextures(animationTexture);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpTextures() {
        animationTexture = ImagingTools.glLoadTextureLinear("res/animations.png");
    }

    private static void drawFrame(int frameNumber, int x, int y, int x2, int y2) {
        int height = 60;
        int width = 60;
        int x1 = frameNumber * width;
        int y1 = 0;
        //int x2 = frameNumber * width + width;
        //int y2 = height;
        glBegin(GL_QUADS);

        glEnd();
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