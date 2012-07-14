package episode_5;

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
        }
        try {
            // Load the wood texture from "res/wood.png"
            wood = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/wood.png")));
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
