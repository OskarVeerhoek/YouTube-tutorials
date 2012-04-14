package episode_23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Oskar
 */
public class ShaderDemo {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Shader Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }
        int shaderProgram = glCreateProgram();
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        StringBuilder vertexShaderSource = new StringBuilder();
        StringBuilder fragmentShaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/episode_23/shader.vert"));
            String line;
            while ((line = reader.readLine()) != null) {
                vertexShaderSource.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Vertex shader wasn't loaded properly.");
            Display.destroy();
            System.exit(1);
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/episode_23/shader.frag"));
            String line;
            while ((line = reader.readLine()) != null) {
                fragmentShaderSource.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Fragment shader wasn't loaded properly.");
            Display.destroy();
            System.exit(1);
        }
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        if (glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Vertex shader wasn't able to be compiled correctly.");
        }
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment shader wasn't able to be compiled correctly.");
        }
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);
        while (!Display.isCloseRequested()) {
            glUseProgram(shaderProgram);
            glBegin(GL_TRIANGLES);
            glColor3f(1, 0, 0);
            glVertex2f(-0.5f, -0.5f);
            glColor3f(0, 1, 0);
            glVertex2f(0.5f, -0.5f);
            glColor3f(0, 0, 1);
            glVertex2f(0, 0.5f);
            glEnd();
            glUseProgram(0);
            Display.update();
            Display.sync(60);
        }
        glDeleteProgram(shaderProgram);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        Display.destroy();
        System.exit(0);
    }
}
