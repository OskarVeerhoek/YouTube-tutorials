package episode_7;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Shows framerate-independant movement
 * @author Oskar
 */
public class TimerDemo {

    private long lastFrame;

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }

    public TimerDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Timer Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        int x = 100;
        int y = 100;
        int dx = 1;
        int dy = 1;

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        lastFrame = getTime();

        while (!Display.isCloseRequested()) {
            // Render

            glClear(GL_COLOR_BUFFER_BIT);

            int delta = getDelta();
            x += delta * dx * 0.1;
            y += delta * dy * 0.1;

            glRecti(x, y, x + 30, y + 30);

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TimerDemo();
    }
}
