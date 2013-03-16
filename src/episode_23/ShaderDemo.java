/*
 * Copyright (c) 2013, Oskar Veerhoek
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

package episode_23;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/** @author Oskar */
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/episode_23/shader.vs"));
            String line;
            while ((line = reader.readLine()) != null) {
                vertexShaderSource.append(line).append('\n');
            }
        } catch (IOException e) {
            System.err.println("Vertex shader wasn't loaded properly.");
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader(new FileReader("src/episode_23/shader.fs"));
            String line;
            while ((line = reader2.readLine()) != null) {
                fragmentShaderSource.append(line).append('\n');
            }
        } catch (IOException e) {
            System.err.println("Fragment shader wasn't loaded properly.");
            Display.destroy();
            System.exit(1);
        } finally {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Vertex shader wasn't able to be compiled correctly.");
        }
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
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
