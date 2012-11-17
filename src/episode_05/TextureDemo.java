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

package episode_05;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Nothing special, just a texture.
 *
 * @author Oskar Veerhoek
 */
public class TextureDemo {

    private static Texture wood;

    public static void main(String args[]) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Texture Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        try {
            // Load the wood texture from "res/images/wood.png"
            wood = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/wood.png")));
        } catch (IOException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            wood.bind();
            glBegin(GL_TRIANGLES);
            glTexCoord2f(1, 0);
            glVertex2i(450, 10);
            glTexCoord2f(0, 0);
            glVertex2i(10, 10);
            glTexCoord2f(0, 1);
            glVertex2i(10, 450);
            glTexCoord2f(0, 1);
            glVertex2i(10, 450);
            glTexCoord2f(1, 1);
            glVertex2i(450, 450);
            glTexCoord2f(1, 0);
            glVertex2i(450, 10);
            glEnd();
//            glBegin(GL_QUADS);
//            glTexCoord2f(0, 0);
//            glVertex2i(400, 400); // Upper-left
//            glTexCoord2f(1, 0);
//            glVertex2i(450, 400); // Upper-right
//            glTexCoord2f(1, 1);
//            glVertex2i(450, 450); // Bottom-right
//            glTexCoord2f(0, 1);
//            glVertex2i(400, 450); // Bottom-left
//            glEnd();
            Display.update();
            Display.sync(60);
        }
        // Release the resources of the wood texture
        wood.release();
        Display.destroy();
        System.exit(0);
    }
}
