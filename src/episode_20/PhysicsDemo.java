package episode_20;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.vecmath.Vector2f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Shows some basic shape drawing.
 *
 * @author Oskar
 */
public class PhysicsDemo {

    public PhysicsDemo() {
        try {
            Display.setDisplayMode(Display.getAvailableDisplayModes()[0]);
            Display.setTitle("Physics Demo");
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 1280, 0, 720, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        final float g = 0.35f;
        float gravity = g;
        final int vbo = glGenBuffers();
        boolean isGoingUp = false;
        Vector2f pos = new Vector2f(640 / 2, 640);
        FloatBuffer vertexData = BufferUtils.createFloatBuffer(8);
        vertexData.put(new float[]{
                    pos.x, pos.y, //1
                    pos.x + 30, pos.y, //2
                    pos.x, pos.y + 30, //3
                    pos.x + 30, pos.y + 30}); //4
        vertexData.flip();
        ShortBuffer indices = BufferUtils.createShortBuffer(6);
        indices.put(new short[]{2, 0, 1, 1, 3, 2});
        indices.flip();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_DYNAMIC_COPY);
        glEnableClientState(GL_VERTEX_ARRAY);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

//            if (gravity > 3f) {
//                gravity = 3f;
//            }

            if (isGoingUp) {
                gravity += g;
                pos.y += gravity;
            } else {
                if (pos.y + (gravity - g) <= 0) {
                    gravity -= pos.y;
                    gravity *= -1;
                    pos.y = 0;
                    gravity *= 0.3f;
                    pos.y += gravity;
                } else {
                    gravity -= g;
                    pos.y += gravity;
                }
            }

//            System.out.println(pos);

            vertexData.clear();
            vertexData.put(new float[]{
                        pos.x, pos.y,               //1
                        pos.x + 30, pos.y,          //2
                        pos.x, pos.y + 30,          //3
                        pos.x + 30, pos.y + 30});   //4
            vertexData.flip();

            glBufferData(GL_ARRAY_BUFFER, vertexData, GL_DYNAMIC_COPY);

            glVertexPointer(2, GL_FLOAT, 0, 0L);
//            glDrawArrays(GL_TRIANGLES, 0, 6);
            glDrawElements(GL_TRIANGLES, indices);
            
            int mouseWheel = (int) ((float) Mouse.getDWheel() / 120f);
            System.out.println(mouseWheel);
            if (mouseWheel != 0) {
                glScalef(mouseWheel, mouseWheel, 1);
            }
            
            while (Keyboard.next()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                    glLoadIdentity();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                    pos.y = 450;
                }
            }

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new PhysicsDemo();
        System.exit(0);
    }
}
