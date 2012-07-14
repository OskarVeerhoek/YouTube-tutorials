package future;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import utility.Camera;
import utility.Model;
import utility.OBJLoader;
import utility.ShaderLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Shows the stretching and resizing of 3D objects.
 */
public class StretchingObjects {

    private static Camera cam;
    private static int texture;
    private static int shaderProgram;
    private static int vboVertexHandle;
    private static int vboNormalHandle;
    private static int bunnyDisplayList;
    private static int diffuseLocation;
    private static float[] scaleFactor = {1f, 1f, 1f};

    private static Model model;

    public static final String MODEL_LOCATION = "res/bunny.obj";
    public static final String VERTEX_SHADER_LOCATION = "res/specular_lighting.vert";
    public static final String FRAGMENT_SHADER_LOCATION = "res/specular_lighting.frag";

    public static void main(String[] args) {
        setUpDisplay();
        setUpVBOs();
        setUpCamera();
        setUpShaders();
        setUpLighting();
        while (!Display.isCloseRequested()) {
            render();
            checkInput();
            Display.update();
            Display.sync(60);
        }
        cleanUp();
        System.exit(0);
    }

    private static void checkInput() {
        cam.processMouse(1, 80, -80);
        cam.processKeyboard(16, 0.003f, 0.003f, 0.003f);
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_ADD:
                        scaleFactor[0] += 0.1f;
                        scaleFactor[1] += 0.1f;
                        scaleFactor[2] += 0.1f;
                        break;
                    case Keyboard.KEY_MINUS:
                        scaleFactor[0] -= 0.1f;
                        scaleFactor[1] -= 0.1f;
                        scaleFactor[2] -= 0.1f;
                        break;
                }
            }
        }
        if (Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if (Mouse.isButtonDown(1))
            Mouse.setGrabbed(false);
    }

    private static void cleanUp() {
        glDeleteTextures(texture);
        glDeleteProgram(shaderProgram);
        glDeleteLists(bunnyDisplayList, 1);
        glDeleteBuffers(vboVertexHandle);
        glDeleteBuffers(vboNormalHandle);
        Display.destroy();
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        cam.applyModelviewMatrix(true);

        glUseProgram(shaderProgram);
        glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(cam.getX(), cam.getY(), cam.getZ(), 1));
        glUniform1f(diffuseLocation, 1.0f);
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
        glNormalPointer(GL_FLOAT, 0, 0L);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glColor3f(0.4f, 0.27f, 0.17f);
        glMaterialf(GL_FRONT, GL_SHININESS, 10f);
        glPushMatrix();
        glScalef(scaleFactor[0], scaleFactor[1], scaleFactor[2]);
        glDrawArrays(GL_TRIANGLES, 0, model.faces.size() * 3);
        glPopMatrix();
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
    }

    private static void setUpLighting() {
        glShadeModel(GL_SMOOTH);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f,
                0.05f, 0.05f, 1f}));
        glLight(GL_LIGHT0, GL_POSITION,
                asFloatBuffer(new float[]{0, 0, 0, 1}));
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_NORMALIZE);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    private static void setUpVBOs() {
        int[] vbos;
        try {
            model = OBJLoader.loadModel(new File(MODEL_LOCATION));
            vbos = OBJLoader.createVBO(model);
            vboVertexHandle = vbos[0];
            vboNormalHandle = vbos[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        }
    }

    @SuppressWarnings("unused")
    private static float[] asFloats(Vector3f v) {
        return new float[]{v.x, v.y, v.z};
    }

    @SuppressWarnings("unused")
    private static FloatBuffer reserveData(int size) {
        FloatBuffer data = BufferUtils.createFloatBuffer(size);
        return data;
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
        diffuseLocation = glGetUniformLocation(shaderProgram,
                "diffuseIntensityModifier");
    }

    private static void setUpCamera() {
        cam = new Camera((float) Display.getWidth()
                / (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
        cam.setFov(70);
        cam.applyPerspectiveMatrix();
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.setTitle("VBO Model Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }
    }

    private static FloatBuffer asFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
