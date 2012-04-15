package examples.models;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class ModelDemo {

    private static Model m;
    private static int vboData;
    private static int vboIndices;
    private static int displayList;

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (!setUpDisplay()) {
            System.err.println("Display not set up correctly.");
            cleanup();
        }
        if (!loadModel()) {
            System.err.println("Model not loaded and/or created properly.");
            cleanup();
        }
        while (!Display.isCloseRequested()) {
            loop();
        }
        cleanup();
    }

    private static void cleanup() {
        glDeleteLists(displayList, 1);
        glDeleteBuffers(vboData);
        glDeleteBuffers(vboIndices);
        Display.destroy();
        System.exit(0);
    }

    private static void loop() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glBindBuffer(GL_ARRAY_BUFFER, vboData);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glNormalPointer(GL_FLOAT, 0, (long) m.verticesSize);

        glDrawArrays(GL_TRIANGLES, 0, 10000);
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndices);
//        glDrawElements(GL_TRIANGLES, 30000, GL_INT, 0L);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

//        glCallList(displayList);

        Display.update();
        Display.sync(60);
    }

    private static boolean loadModel() {
        try {
            m = OBJLoader.loadModel(new File("src/episode_24/bunny.obj"));
            vboData = m.createDataVBO();
            vboIndices = m.createIndexVBO();
            displayList = m.createDisplayList();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.setTitle("Model Demo");
            Display.create();
        } catch (LWJGLException e) {
            return false;
        }
        glMatrixMode(GL_PROJECTION);
        GLU.gluPerspective(60, 1280f / 720f, 0.3f, 100f);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_ARRAY_BUFFER_BINDING);
        return true;
    }
}
