package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.Camera;
import utility.LWJGLTimer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Shows skyboxes.
 *
 * @author Oskar Veerhoek
 */
public class SkyboxDemo {

    private static final String WINDOW_TITLE = "Skybox Demo";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static final String SKYBOX_TEXTURE_LOCATION = "res/images/skybox.png";

    private static int skyboxTextureID;
    private static Camera camera = new Camera((float) WINDOW_DIMENSIONS[0] / (float) WINDOW_DIMENSIONS[1]);
    private static LWJGLTimer timer = new LWJGLTimer(false);

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        camera.applyTranslations();
    }

    private static void logic() {
        timer.update();
    }

    private static void input() {
        float delta = (float) timer.getElapsedTime();
        camera.processKeyboard(delta, 0.003f, 0.003f, 0.003f);
        camera.processMouse(delta / 16, 80, -80);
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

    private static void setUpTextures() {

    }

    private static void setUpMatrices() {
        camera.applyPerspectiveMatrix();
    }

    private static void setUpTimer() {
        timer.initialize();
    }

    private static void setUpStates() {
        glEnable(GL_DEPTH_TEST);
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
        setUpTimer();
        enterGameLoop();
        cleanUp(false);
    }

}
