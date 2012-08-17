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

package utility;

import org.lwjgl.opengl.Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;


public class ShaderLoader {
    public static int loadShaderPair(String vertexShaderLocation, String fragmentShaderLocation) {
        int shaderProgram = glCreateProgram();
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        StringBuilder vertexShaderSource = new StringBuilder();
        StringBuilder fragmentShaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    vertexShaderLocation));
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
            BufferedReader reader = new BufferedReader(new FileReader(
                    fragmentShaderLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                fragmentShaderSource.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Fragment shader wasn't loaded properly.");
            Display.destroy();
            System.exit(1);
        }
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        if (glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err
                    .println("Vertex shader wasn't able to be compiled correctly. Error log:");
            System.err.println(glGetShaderInfoLog(vertexShader, 1024));
        }
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err
                    .println("Fragment shader wasn't able to be compiled correctly. Error log:");
            System.err.println(glGetShaderInfoLog(fragmentShader, 1024));
        }
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        return shaderProgram;
    }
}
