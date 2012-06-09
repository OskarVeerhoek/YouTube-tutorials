package episode_29;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.Camera;

import static org.lwjgl.opengl.GL11.*;

/**
 * Uses height maps.
 * @author Oskar Veerhoek
**/
public class HeightMapsDemo {

    private static final String WINDOW_TITLE = "Height Maps!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static Camera camera = new Camera((float) WINDOW_DIMENSIONS[0] / (float) WINDOW_DIMENSIONS[1]);
    private static final int heightMap[][] = {
        { 0, 0, 1, 1, 2 },
        { 0, 1, 2, 2, 3 },
        { 0, 1, 3, 2, 3 },
        { 0, 1, 2, 1, 2 },
        { 0, 0, 1, 1, 1 } };

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        camera.applyModelviewMatrix(true);
        glBegin(GL_TRIANGLE_STRIP);
        for (int x = 0; x < heightMap.length; x++) {
            for (int y = 0; y < heightMap[0].length; y++) {
                glVertex3f(x, y, heightMap[x][y]);
            }
        }
        glEnd();
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        camera.processMouse(1, 80, -80);
        camera.processKeyboard(16, 0.003f, 0.003f, 0.003f);
        if (Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if (Mouse.isButtonDown(1))
            Mouse.setGrabbed(false);
    }

    private static void cleanUp(boolean asCrash) {
        // Add cleaning code here
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        camera.applyPerspectiveMatrix();
    }

    private static void setUpTextures() {

    }

    private static void setUpStates() {
        glEnable(GL_DEPTH_TEST);
//        glEnable(GL_LIGHTING);
//        glEnable(GL_BLEND);
//        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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
