package future.logging;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Oskar Veerhoek
 */
public class LoggingDemo {

    private static final String WINDOW_TITLE = "Logging Demo";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static Logger logger = Logger.getLogger(LoggingDemo.class.getName());

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        // Add rendering code here
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        // Add input handling code here
    }

    private static void cleanUp(boolean asCrash) {
        logger.fine("Cleaning Up Resources");
        // Add cleaning code here
        logger.finer("Destroying Display");
        Display.destroy();
        logger.info("Quitting JVM");
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpLogger() {
        logger.setLevel(Level.FINER);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        Set<Handler> handlersToDestroy = new HashSet<Handler>();
        for (Handler h : logger.getHandlers()) {
            handlersToDestroy.add(h);
        }
        for (Handler h : handlersToDestroy) {
            logger.removeHandler(h);
        }
        logger.addHandler(ch);
        for (Handler h : logger.getHandlers()) {
            System.out.println(h.toString());
        }
    }

    private static void setUpStates() { 
        logger.fine("Setting Up States");
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
        logger.fine("Setting Up Display");
        try {
            logger.finer("Setting Display Mode");
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            logger.finer("Setting Display Title");
            Display.setTitle(WINDOW_TITLE);
            logger.finer("Creating Display");
            Display.create();
        } catch (LWJGLException e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            logger.severe(error.toString());
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpLogger();
        logger.info("Initializing Program");
        setUpDisplay();
        setUpStates();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }
}