package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.Sphere;
import utility.Camera;
import utility.LWJGLTimer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Simulating shadows using the stencil buffer
 *
 * @author Oskar Veerhoek
 */
public class StencilShadowingDemo {

    private static final String WINDOW_TITLE = "Stencil Shadowing!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static Camera camera;
    private static LWJGLTimer timer;
    private static Sphere sphere = new Sphere();

    private static void bench() {
//        glColor3f(0, 0, 1);
//        glBegin(GL_QUADS);
//        glVertex3f(-50, -2, -50);
//        glVertex3f(50, -2, -50);
//        glVertex3f(50, -2, 50);
//        glVertex3f(-50, -2, 50);
//        glEnd();
        glPushMatrix();
        glColor4d(1, 1, 1, 0.7);
        glScalef(4, 2, 4);
        glBegin(GL_QUADS);
        glVertex3d(-1, -1, 1);
        glVertex3d(-1, 1, -0.5);
        glVertex3d(1, 1, -0.5);
        glVertex3f(1, -1, 1);
        glEnd();
        glPopMatrix();
    }

    private static void square() {
//        glColor3f(1, 0, 0);
//        sphere.draw(2, 10, 10);
        glPushMatrix();
        glTranslated(0, 2.5, 0);
        glScalef(2, 2, 2);
        glBegin(GL_QUADS);
        glVertex3f(-1, -1, 0);
        glVertex3f(-1, 1, 0);
        glVertex3f(1, 1, 0);
        glVertex3f(1, -1, 0);
        glEnd();
        glPopMatrix();
    }

    private static void render() {
        glLoadIdentity();
        camera.applyTranslations();
        glClearStencil(0);
        glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glColorMask(false, false, false, false);
        glDepthMask(false);
        glEnable(GL_STENCIL_TEST);
        glStencilFunc(GL_ALWAYS, 1, 0xFFFFFFFF);
        glStencilOp(GL_REPLACE, GL_REPLACE, GL_REPLACE);
        bench();
        glColorMask(true, true, true, true);
        glDepthMask(true);
        glStencilFunc(GL_EQUAL, 1, 0xFFFFFFFF);
        glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();
        glScalef(1, -1, 1);
        glTranslatef(0, 2, 0);
        glRotatef(50, 0, 1, 0);
        glColor3f(1, 1, 1);
        square();
        glPopMatrix();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_STENCIL_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        bench();
        glDisable(GL_BLEND);
        square();
    }

    private static void logic() {
        timer.update();
    }

    private static void input() {
        camera.processMouse(1, 80, -80);
        camera.processKeyboard((float) timer.getElapsedTime(), 0.003f, 0.003f, 0.003f);
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

    private static void setUpStates() {
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glShadeModel(GL_SMOOTH);
        glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
//        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
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

    private static void setUpCamera() {
        camera = new Camera((float) Display.getWidth() / (float) Display.getHeight());
        camera.setFov(40);
    }

    private static void setUpTimer() {
        timer = new LWJGLTimer(true);
        timer.initialize();
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpCamera();
        setUpStates();
        setUpMatrices();
        setUpTimer();
        enterGameLoop();
        cleanUp(false);
    }
}