package future;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;

/**
 * A 2D program that gives the illusion of depth.
 *
 * @author Oskar Veerhoek
 */
public class Background2D {

    private static int staticBackgroundTextureID;
    private static int staticBackgroundTextureDisplayList;
    private static int directBackgroundTextureID;
    private static int directBackgroundTextureDisplayList;
    private static int semiBackgroundTextureID;
    private static int semiBackgroundTextureDisplayList;
    private static float speed = 1.5f;
    private static float cameraPosition[] = {0, 0};

    private static final String DIRECT_BACKGROUND_LOCATION = "res/direct_background.png";
    private static final String SEMI_BACKGROUND_LOCATION = "res/semi_background.png";
    private static final String STATIC_BACKGROUND_LOCATION = "res/static_background.png";
    private static final String WINDOW_TITLE = "2D Backgrounds!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, staticBackgroundTextureID);
        glCallList(staticBackgroundTextureDisplayList);
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, semiBackgroundTextureID);
        glPushMatrix();
        glTranslatef(-cameraPosition[0] * 0.334f, 0, 0);
        glCallList(semiBackgroundTextureDisplayList);
        glPopMatrix();
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, directBackgroundTextureID);
        glPushMatrix();
        glTranslatef(-cameraPosition[0], -cameraPosition[1], 0);
        glCallList(directBackgroundTextureDisplayList);
        glPopMatrix();
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);

    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            if (cameraPosition[0] + speed + 640 < 2560)
                cameraPosition[0] += speed;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            if (cameraPosition[0] - speed > 0)
                cameraPosition[0] -= speed;
        }
    }

    private static void cleanUp(boolean asCrash) {
        glDeleteLists(staticBackgroundTextureDisplayList, 1);
        glDeleteLists(directBackgroundTextureDisplayList, 1);
        glDeleteLists(semiBackgroundTextureDisplayList, 1);
        glDeleteTextures(semiBackgroundTextureID);
        glDeleteTextures(directBackgroundTextureID);
        glDeleteTextures(staticBackgroundTextureID);
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpTextures() {
        staticBackgroundTextureID = glLoadTextureLinear(STATIC_BACKGROUND_LOCATION);
        staticBackgroundTextureDisplayList = glGenLists(1);
        glNewList(staticBackgroundTextureDisplayList, GL_COMPILE);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(0, 0);
        glTexCoord2f(0, 480);
        glVertex2i(0, 480);
        glTexCoord2f(640, 480);
        glVertex2i(640, 480);
        glTexCoord2f(640, 0);
        glVertex2i(640, 0);
        glEnd();
        glEndList();
        directBackgroundTextureID = glLoadTextureLinear(DIRECT_BACKGROUND_LOCATION);
        directBackgroundTextureDisplayList = glGenLists(1);
        glNewList(directBackgroundTextureDisplayList, GL_COMPILE);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(0, 0);
        glTexCoord2f(0, 480);
        glVertex2i(0, 480);
        glTexCoord2f(2560, 480);
        glVertex2i(2560, 480);
        glTexCoord2f(2560, 0);
        glVertex2i(2560, 0);
        glEnd();
        glEndList();
        semiBackgroundTextureID = glLoadTextureLinear(SEMI_BACKGROUND_LOCATION);
        semiBackgroundTextureDisplayList = glGenLists(1);
        glNewList(semiBackgroundTextureDisplayList, GL_COMPILE);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2i(0, 0);
        glTexCoord2f(0, 480);
        glVertex2i(0, 480);
        glTexCoord2f(1280, 480);
        glVertex2i(1280, 480);
        glTexCoord2f(1280, 0);
        glVertex2i(1280, 0);
        glEnd();
        glEndList();
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
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
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpTextures();
        setUpStates();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

    private static int glLoadTextureLinear(String location) {
        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, texture);
        {
            InputStream in = null;
            try {
                in = new FileInputStream(location);
                PNGDecoder decoder = new PNGDecoder(in);
                ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
                decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
                buffer.flip();
                in.close();
                glBindTexture(GL_TEXTURE_RECTANGLE_ARB, texture);
                glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexImage2D(GL_TEXTURE_RECTANGLE_ARB, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
                glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to find the texture file.");
                cleanUp(true);
            } catch (IOException ex) {
                System.err.println("Failed to load the texture files.");
                cleanUp(true);
            }
        }
        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
        return texture;
    }

}

