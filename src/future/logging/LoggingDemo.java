/*
 * Copyright (c) 2012, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package future.logging;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 * NOT DONE YET
 * Shows logging functionality using the java.util.logging classes.
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