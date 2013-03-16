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

package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderLoader {

    /**
     * Loads a shader program from two source files.
     *
     * @param vertexShaderLocation the location of the file containing the vertex shader source
     * @param fragmentShaderLocation the location of the file containing the fragment shader source
     *
     * @return the shader program or -1 if the loading or compiling failed
     */
    public static int loadShaderPair(String vertexShaderLocation, String fragmentShaderLocation) {
        int shaderProgram = glCreateProgram();
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        StringBuilder vertexShaderSource = new StringBuilder();
        StringBuilder fragmentShaderSource = new StringBuilder();
        BufferedReader vertexShaderFileReader = null;
        try {
            vertexShaderFileReader = new BufferedReader(new FileReader(vertexShaderLocation));
            String line;
            while ((line = vertexShaderFileReader.readLine()) != null) {
                vertexShaderSource.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (vertexShaderFileReader != null) {
                try {
                    vertexShaderFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BufferedReader fragmentShaderFileReader = null;
        try {
            fragmentShaderFileReader = new BufferedReader(new FileReader(fragmentShaderLocation));
            String line;
            while ((line = fragmentShaderFileReader.readLine()) != null) {
                fragmentShaderSource.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (fragmentShaderFileReader != null) {
                try {
                    fragmentShaderFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Vertex shader wasn't able to be compiled correctly. Error log:");
            System.err.println(glGetShaderInfoLog(vertexShader, 1024));
            return -1;
        }
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment shader wasn't able to be compiled correctly. Error log:");
            System.err.println(glGetShaderInfoLog(fragmentShader, 1024));
        }
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        if (glGetProgrami(shaderProgram, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Shader program wasn't linked correctly.");
            System.err.println(glGetProgramInfoLog(shaderProgram, 1024));
            return -1;
        }
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        return shaderProgram;
    }
}
