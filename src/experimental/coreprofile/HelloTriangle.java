package experimental.coreprofile;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import rosick.GLWindow;
import rosick.framework.IOUtils;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * I. The Basics Chapter 1. Hello, Triangle!
 * http://www.arcsynthesis.org/gltut/Basics/Tutorial%2001.html
 *
 * @author integeruser
 */
public class HelloTriangle extends GLWindow {

    public static void main(String[] args) {
        try {
            new HelloTriangle().start(Display.getAvailableDisplayModes()[0].getWidth(), Display.getAvailableDisplayModes()[0].getHeight());
        } catch (LWJGLException ex) {
            Logger.getLogger(HelloTriangle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    private int theProgram;
    private int positionBufferObject;
    private int vao;
    private final float vertexPositions[] = {
        0.75f, 0.75f, 0.0f,
        0.75f, -0.75f, 0.0f,
        -0.75f, -0.75f, 0.0f,};
    private final String strVertexShader =
            "#version 150 \n"
            + "\n"
     //       + "layout(location = 0) in vec4 position;\n"
            + "in vec4 position;\n"
            + "void main()\n"
            + "{\n"
            + "    gl_Position = position;\n"
            + "}";
    private final String strFragmentShader =
            "#version 150\n"
            + "\n"
            + "out vec4 outputColor;\n"
            + "void main()\n"
            + "{\n"
            + "   outputColor = vec4(0.804f, 0.522f, 0.247f, 1.0f);\n"
            + "}";

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    @Override
    protected void init() {
        initializeProgram();
        initializeVertexBuffer();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        System.out.println(GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));
    }

    private void initializeProgram() {
        int vertexShader = createShader(GL_VERTEX_SHADER, strVertexShader);
        int fragmentShader = createShader(GL_FRAGMENT_SHADER, strFragmentShader);

        ArrayList<Integer> shaderList = new ArrayList<Integer>();
        shaderList.add(vertexShader);
        shaderList.add(fragmentShader);

        theProgram = createProgram(shaderList);

        for (Integer shader : shaderList) {
            glDeleteShader(shader);
        }
    }

    private void initializeVertexBuffer() {
        FloatBuffer positionBuffer = IOUtils.allocFloats(vertexPositions);

        positionBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
        glBufferData(GL_ARRAY_BUFFER, positionBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    protected void display() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(theProgram);

        glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glDisableVertexAttribArray(0);
        glUseProgram(0);
    }

    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    private int createShader(int shaderType, String strShaderFile) {
        int shader = glCreateShader(shaderType);

        if (shader != 0) {
            glShaderSource(shader, strShaderFile);
            glCompileShader(shader);
        }

        return shader;
    }

    private int createProgram(ArrayList<Integer> shaderList) {
        int program = glCreateProgram();

        if (program != 0) {
            for (Integer shader : shaderList) {
                glAttachShader(program, shader);
            }

            glLinkProgram(program);
            glValidateProgram(program);

            for (Integer shader : shaderList) {
                glDetachShader(program, shader);
            }
        }

        return program;
    }
}