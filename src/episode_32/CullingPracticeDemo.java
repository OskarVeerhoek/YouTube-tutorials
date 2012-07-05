package episode_32;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Put description here.
 *
 * @author Oskar Veerhoek
 */
public class CullingPracticeDemo {

    private static final String WINDOW_TITLE = "Culling!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glBegin(GL_POINTS);
        glVertex2d(-0.75, +0.75);
        glVertex2d(+0.75, -0.75);
        glEnd();
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

    private static void setUpStates() {
        glEnable(GL_CULL_FACE);
        glCullFace(GL_FRONT);
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
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
