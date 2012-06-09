package episode_29;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import utility.BufferTools;
import utility.ShaderLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * Using core profile modern OpenGL only!
 * @author Oskar Veerhoek
 */
public class ModernOpenGLDemo {

    private static final String WINDOW_TITLE = "Modern OpenGL!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static final String VERTEX_SHADER_LOCATION = "res/core_basic_shader.vert";
    private static final String FRAGMENT_SHADER_LOCATION = "res/core_basic_shader.frag";

    private static int vbo;
    private static int vao;
    private static int shaderProgram;
    private static int vertexLocation;
    private static int colorLocation;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glBindVertexArray(vao);
        glUseProgram(shaderProgram);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glUseProgram(0);
        glBindVertexArray(0);
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        // Add input handling code here
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteProgram(shaderProgram);
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        // Add code for the initialization of the projection matrix here
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
        vertexLocation = glGetAttribLocation(shaderProgram, "position");
        colorLocation = glGetAttribLocation(shaderProgram, "color");
        glUseProgram(shaderProgram);
    }

    private static void setUpVBOs() {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        System.out.println(vertexLocation + ", " + colorLocation);
        glBindVertexArray(vao);
        glEnableVertexAttribArray(vertexLocation);
        glEnableVertexAttribArray(colorLocation);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferTools.asFlippedFloatBuffer(0.0f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 1, 0, 0, 0, 1, 0, 0, 0, 1), GL_STATIC_DRAW);
        glVertexAttribPointer(vertexLocation, 2, GL_FLOAT, false, 0, 0L);
        glVertexAttribPointer(colorLocation, 3, GL_FLOAT, false, 0, 2 * 3 * 4);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
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
            Display.create(new PixelFormat(), new ContextAttribs(3, 2));
            System.out.println(glGetString(GL_VERSION));
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpMatrices();
        setUpShaders();
        setUpVBOs();
        enterGameLoop();
        cleanUp(false);
    }

}
