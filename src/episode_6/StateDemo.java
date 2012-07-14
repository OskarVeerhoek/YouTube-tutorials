package episode_6;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Shows the use of different game states.
 *
 * @author Oskar
 */
public class StateDemo {

    private static enum State {

        INTRO, MAIN_MENU, GAME;
    }

    private State state = State.INTRO;

    public StateDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("State Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        while (!Display.isCloseRequested()) {

            checkInput();
            glClear(GL_COLOR_BUFFER_BIT);
            render();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    private void render() {
        switch (state) {
            case INTRO:
                glColor3f(1.0f, 0f, 0f);
                glRectf(0, 0, 640, 480);
                break;
            case GAME:
                glColor3f(0f, 1.0f, 0f);
                glRectf(0, 0, 640, 480);
                break;
            case MAIN_MENU:
                glColor3f(0f, 0f, 1.0f);
                glRectf(0, 0, 640, 480);
                break;
        }
    }

    private void checkInput() {
        switch (state) {
            case INTRO:
                if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                    state = State.MAIN_MENU;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    Display.destroy();
                    System.exit(0);
                }
                break;
            case GAME:
                if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                    state = State.MAIN_MENU;
                }
                break;
            case MAIN_MENU:
                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    state = State.GAME;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    state = State.INTRO;
                }
                break;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new StateDemo();
    }
}
