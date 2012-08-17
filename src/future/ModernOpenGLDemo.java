package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import utility.BufferTools;
import utility.ShaderLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Using core profile modern OpenGL only!
 *
 * @author Oskar Veerhoek
 */
public class ModernOpenGLDemo {

    private static final String WINDOW_TITLE = "Modern OpenGL!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static final String VERTEX_SHADER_LOCATION = "res/shaders/core_basic_shader.vert";
    private static final String FRAGMENT_SHADER_LOCATION = "res/shaders/core_basic_shader.frag";

    private static int vbo;
    private static int vao;
    private static int shaderProgram;
    private static int vertexLocation;
    private static int colorLocation;
    private static int smoothnessLocation;
    private static int mousePositionLocation;
    private static int modelviewLocation;
    private static int projectionLocation;
    private static float smoothness = 20;
    private static Matrix4f modelviewMatrix = new Matrix4f();
    private static Matrix4f projectionMatrix = new Matrix4f();

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT

        glBindVertexArray(vao);
        glUseProgram(shaderProgram);

        glUniformMatrix4(modelviewLocation, false, BufferTools.asFlippedFloatBuffer(modelviewMatrix));
        glUniformMatrix4(projectionLocation, false, BufferTools.asFlippedFloatBuffer(projectionMatrix));
        glUniform1f(smoothnessLocation, smoothness);
        glUniform2f(mousePositionLocation, Mouse.getX(), Mouse.getY());

//        for (int i = 0; i < 10000; i++)
        glDrawArrays(GL_TRIANGLES, 0, 3);

        glUseProgram(0);
        glBindVertexArray(0);
    }

    private static void logic() {
    }

    private static void input() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
            smoothness *= 0.993;
//            smoothness -= 0.16;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
            smoothness /= 0.993;
//            smoothness += 0.16;
        }
        if (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_LEFT:
                        modelviewMatrix.translate(new Vector3f(-0.1f, 0, 0));
                        break;
                    case Keyboard.KEY_RIGHT:
                        modelviewMatrix.translate(new Vector3f(+0.1f, 0, 0));
                        break;
                    case Keyboard.KEY_UP:
                        modelviewMatrix.translate(new Vector3f(0, 0.1f, 0));
                        break;
                    case Keyboard.KEY_DOWN:
                        modelviewMatrix.translate(new Vector3f(0, -0.1f, 0));
                        break;
                    case Keyboard.KEY_W:
                        modelviewMatrix.translate(new Vector3f(0, 0, -0.1f));
                        break;
                    case Keyboard.KEY_S:
                        modelviewMatrix.translate(new Vector3f(0, 0, 0.1f));
                        break;
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteProgram(shaderProgram);
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
//        modelviewMatrix.translate(new Vector3f(0, 0, 0));
//        computePerspective(projectionMatrix, 60f, (float) Display.getWidth() / (float) Display.getHeight(), 0.3f, 200f);
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
        vertexLocation = glGetAttribLocation(shaderProgram, "position");
        colorLocation = glGetAttribLocation(shaderProgram, "color");
        smoothnessLocation = glGetUniformLocation(shaderProgram, "smoothness");
        mousePositionLocation = glGetUniformLocation(shaderProgram, "mousePosition");
        modelviewLocation = glGetUniformLocation(shaderProgram, "modelview");
        projectionLocation = glGetUniformLocation(shaderProgram, "projection");
    }

    private static void setUpVBOs() {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        System.out.println(vertexLocation + ", " + colorLocation);
        glBindVertexArray(vao);
        glEnableVertexAttribArray(vertexLocation);
        glEnableVertexAttribArray(colorLocation);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferTools.asFlippedFloatBuffer(0.0f, 1f, 1f, -1f, -1f, -1f, 1, 0, 0, 0, 1, 0, 0, 0, 1), GL_STATIC_DRAW);
        glVertexAttribPointer(vertexLocation, 2, GL_FLOAT, false, 0, 0L);
        glVertexAttribPointer(colorLocation, 3, GL_FLOAT, false, 0, 2 * 3 * 4);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private static void setUpStates() {
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
            Display.create(new PixelFormat(), new ContextAttribs(3, 2).withProfileCore(true));
            System.out.println(glGetString(GL_VERSION));
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

//    private static void computePerspective(Matrix4f projectionMatrix, float fov, float aspectRatio, float zNear, float zFar) {
//        float ymax = (float) (zNear * Math.tan(Math.toRadians(fov)));
//        float ymin = -ymax;
//        float xmax = ymax * aspectRatio;
//        float xmin = ymin * aspectRatio;
//
//        float width = xmax * xmin;
//        float height = ymax * ymin;
//
//        float depth = zFar - zNear;
//        float q = (-zFar + zNear) / depth;
//        float qn = -2 * (zFar * zNear) / depth;
//
//        float w = 2 * zNear / width;
//        w = w / aspectRatio;
//        float h = 2 * zNear / height;
//
//        projectionMatrix.m00 = w;
//        projectionMatrix.m01 = 0;
//        projectionMatrix.m02 = 0;
//        projectionMatrix.m03 = 0;
//
//        projectionMatrix.m10 = 0;
//        projectionMatrix.m11 = h;
//        projectionMatrix.m12 = 0;
//        projectionMatrix.m13 = 0;
//
//        projectionMatrix.m20 = 0;
//        projectionMatrix.m21 = 0;
//        projectionMatrix.m22 = q;
//        projectionMatrix.m23 = -1;
//
//        projectionMatrix.m30 = 0;
//        projectionMatrix.m31 = 0;
//        projectionMatrix.m32 = qn;
//        projectionMatrix.m33 = 0;
//    }

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
