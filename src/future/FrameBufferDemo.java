package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utility.BufferTools;
import utility.LWJGLTimer;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;


/**
 * Shows the use of a frame buffer.
 *
 * @author Oskar Veerhoek
 */
public class FrameBufferDemo {

    private static final String WINDOW_TITLE = "Frame Buffers!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static int fbo;
    private static int rbo;
    private static LWJGLTimer timer = new LWJGLTimer(true);
    private static boolean useReadPixels = true;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glRectf(-0.5f, -0.5f, 0.5f, 0.5f);
        if (useReadPixels) {
            FloatBuffer colorAtMouse = BufferTools.reserveData(16);
            int mouse_x = Mouse.getX();
            int mouse_y = Mouse.getY();
            for (int i = 0; i < 50; i++) {
                glReadPixels(mouse_x, mouse_y, 1, 1, GL_RGB, GL_FLOAT, colorAtMouse);
                if (colorAtMouse.get(0) + colorAtMouse.get(1) + colorAtMouse.get(2) > 0.5f) {
                    glColor3d(0.8, 0.8, 0);
                } else {
                    glColor3f(1, 1, 1);
                }
            }
        } else {
            int mouse_x = Mouse.getX();
            int mouse_y = Mouse.getY();
            for (int i = 0; i < 50; i++) {
                if (mouse_x > 160 && mouse_x < 480 && mouse_y > 120 && mouse_y < 360)
                    glColor3d(0.8, 0.8, 0);
                else
                    glColor3f(1, 1, 1);
            }
        }
    }

    private static void logic() {
        timer.update();
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_N:
                        useReadPixels = false;
                        break;
                    case Keyboard.KEY_M:
                        useReadPixels = true;
                        break;
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteFramebuffers(fbo);
        glDeleteRenderbuffers(rbo);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpFramebuffer() {
        fbo = glGenFramebuffers();
        rbo = glGenRenderbuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, fbo);
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpTimer() {
        timer.initialize();
    }

    private static void setUpStates() {
//        glEnable(GL_DEPTH_TEST);
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
        setUpFramebuffer();
        setUpMatrices();
        setUpTimer();
        enterGameLoop();
        cleanUp(false);
    }

}
