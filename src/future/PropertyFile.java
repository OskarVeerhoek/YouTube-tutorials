package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 * Put description here.
 *
 * @author Oskar Veerhoek
 */
public class PropertyFile {

    private static String WINDOW_TITLE = "";
    private static int[] WINDOW_DIMENSIONS = new int[2];

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            update();
        }
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setTitle(WINDOW_TITLE);
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        loadProperties();
        setUpDisplay();
        enterGameLoop();
        cleanUp(false);
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = PropertyFile.class.getResourceAsStream("settings.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WINDOW_DIMENSIONS[0] = Integer.parseInt(properties.getProperty("width", "640"));
        WINDOW_DIMENSIONS[1] = Integer.parseInt(properties.getProperty("height", "480"));
        WINDOW_TITLE = properties.getProperty("title", "Default Title");
    }

}
