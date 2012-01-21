package episode_18;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Renders a colored triangle using Vertex Arrays
 * @author Oskar
 */
public class VertexArraysDemo {

    public VertexArraysDemo() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Vertex Arrays Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(1, 1, 1, 1, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        final int amountOfVertices = 3;
        final int vertexSize = 2;
        final int colorSize = 3;
        
        FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        vertexData.put(new float[]{-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f});
        vertexData.flip();
        
        FloatBuffer colorData = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
        colorData.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
        colorData.flip();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

            glEnableClientState(GL_VERTEX_ARRAY);
            glEnableClientState(GL_COLOR_ARRAY);
            
            glVertexPointer(vertexSize, 0, vertexData);
            glColorPointer(colorSize, 0, colorData);
            
            glDrawArrays(GL_TRIANGLES, 0, amountOfVertices);
            
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new VertexArraysDemo();
    }
}