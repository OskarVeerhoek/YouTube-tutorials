import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBShadowAmbient.GL_TEXTURE_COMPARE_FAIL_VALUE_ARB;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Demonstrates how to set up shadow mapping.
 *
 * @author Sam K.
 * @author Daniel W.
 */
public class ShadowMapping {
    private boolean ambientShadowsAvailable = false;
    private boolean showShadowMap = false;

    private float factor = 4.0F;
    private float testVar = 0;

    public static int WIDTH = 1024;
    public static int HEIGHT = 512;

    private int shadowWidth = 1024;
    private int shadowHeight = 512;
    private int shadowTextureID;

    public static int maxTextureSize;

    private FloatBuffer ambientLight = BufferUtils.createFloatBuffer(4);
    private FloatBuffer diffuseLight = BufferUtils.createFloatBuffer(4);
    private FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
    private FloatBuffer tempBuffer = BufferUtils.createFloatBuffer(4);

    private FloatBuffer cameraPos = BufferUtils.createFloatBuffer(4);
    private double cameraZoom = 0.3F;


    Matrix4f textureMatrix = new Matrix4f();
    Sphere s = new Sphere();

    public ShadowMapping() {
        setupDisplay();
        setupFloatBuffers();
        render();
        Display.destroy();
        System.exit(0);
    }

    public void setupDisplay() {
        try {
            //Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("Shadow Mapping Demo");
            Display.setFullscreen(true);
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Couldn't set up the display");
            System.exit(0);
        }
    }

    private void renderObjects(boolean drawGround) {
        if (drawGround) {
            glColor3f(0.0F, 0.0F, 0.9F);
            glNormal3f(0.0F, 1.0F, 0.0F);
            glBegin(GL_QUADS);
            glVertex3f(-100.0F, -25.0F, -100.0F);
            glVertex3f(-100.0F, -25.0F, 100.0F);
            glVertex3f(100.0F, -25.0F, 100.0F);
            glVertex3f(100.0F, -25.0F, -100.0F);
            glEnd();
        }

        glColor3f(1.0F, 0.0F + testVar / 10, 0.0F);
        s.draw(12.0F, 20, 20);

        glColor3f(0.0F, 1.0F, 0.0F);
        glPushMatrix();
        glTranslatef(-60.0F, 0.0F + testVar, 0.0F);
        s.draw(14.0F, 20, 20);
        glPopMatrix();

        glColor3f(1.0F, 1.0F, 0.0F);
        glPushMatrix();
        glTranslatef(-60.0F + testVar, 0.0F, -24.0F);
        s.draw(15.0F, 20, 20);
        glPopMatrix();

        glColor3f(1.0F, 0.0F, 1.0F);
        glPushMatrix();
        glTranslatef(0.0F, 0.0F, 60.0F + testVar);
        s.draw(16.0F + testVar, 20, 20);
        glPopMatrix();

        glColor3f(0.0F, 1.0F, 1.0F);
        glPushMatrix();
        glTranslatef(0.0F, 0.0F, -60.0F);
        s.draw(22.0F, 20 + (int) testVar, 20);
        glPopMatrix();
    }

    private void generateShadowMap() {
        float lightToSceneDistance, nearPlane, fieldOfView;
        FloatBuffer lightModelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer lightProjection = BufferUtils.createFloatBuffer(16);
        Matrix4f lightProjectionTemp = new Matrix4f();
        Matrix4f lightModelViewTemp = new Matrix4f();

        float sceneBoundingRadius = 95.0F;

        lightToSceneDistance = (float) Math.sqrt(lightPos.get(0) * lightPos.get(0) +
                lightPos.get(1) * lightPos.get(1) +
                lightPos.get(2) * lightPos.get(2));

        nearPlane = lightToSceneDistance - sceneBoundingRadius;

        fieldOfView = (float) Math.toDegrees(2.0F * Math.atan(sceneBoundingRadius / lightToSceneDistance));

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fieldOfView, 1.0F, nearPlane, nearPlane + (2.0F * sceneBoundingRadius));
        glGetFloat(GL_PROJECTION_MATRIX, lightProjection);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(lightPos.get(0), lightPos.get(1), lightPos.get(2), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
        glGetFloat(GL_MODELVIEW_MATRIX, lightModelView);
        glViewport(0, 0, shadowWidth, shadowHeight);

        glClear(GL_DEPTH_BUFFER_BIT);

        //Speed up things by not rendering lighting etc.
        glShadeModel(GL_FLAT);
        glDisable(GL_LIGHTING);
        glDisable(GL_COLOR_MATERIAL);
        glDisable(GL_NORMALIZE);
        glColorMask(false, false, true, false);

        glEnable(GL_POLYGON_OFFSET_FILL);

        renderObjects(false);

        glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, 0, 0, shadowWidth, shadowHeight, 0);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_NORMALIZE);
        glColorMask(true, true, true, true);
        glDisable(GL_POLYGON_OFFSET_FILL);

        lightProjectionTemp.load(lightProjection);
        lightModelViewTemp.load(lightModelView);
        lightProjection.flip();
        lightModelView.flip();

        Matrix4f tempMatrix = new Matrix4f();
        tempMatrix.setIdentity();
        tempMatrix.translate(new Vector3f(0.5F, 0.5F, 0.5F));
        tempMatrix.scale(new Vector3f(0.5F, 0.5F, 0.5F));
        Matrix4f.mul(tempMatrix, lightProjectionTemp, textureMatrix);
        Matrix4f.mul(textureMatrix, lightModelViewTemp, tempMatrix);
        Matrix4f.transpose(tempMatrix, textureMatrix);
    }

    private void render() {
        setupOpenGL();
        while (!Display.isCloseRequested()) {
            handleInput();

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            if (WIDTH > HEIGHT) {
                double ar = (double) WIDTH / (double) HEIGHT;
                glFrustum(-ar * cameraZoom, ar * cameraZoom, -cameraZoom, cameraZoom, 1.0F, 1000.0F);
            } else {
                double ar = (double) HEIGHT / (double) WIDTH;
                glFrustum(-cameraZoom, cameraZoom, -ar * cameraZoom, ar * cameraZoom, 1.0F, 1000.0F);
            }

            glMatrixMode(GL_MODELVIEW);

            //TODO Add cam.applyModelViewMatrix here

            glViewport(0, 0, WIDTH, HEIGHT);

            glLight(GL_LIGHT0, GL_POSITION, lightPos);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            if (showShadowMap) {
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                glMatrixMode(GL_TEXTURE);
                glPushMatrix();
                glLoadIdentity();
                glEnable(GL_TEXTURE_2D);
                glDisable(GL_LIGHTING);
                glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_NONE);

                glBegin(GL_QUADS);
                glTexCoord2f(0.0F, 0.0F);
                glVertex2f(-1.0F, -1.0F);
                glTexCoord2f(1.0F, 0.0F);
                glVertex2f(((float) shadowWidth / (float) WIDTH) * 2.0F - 1.0F, -1.0F);
                glTexCoord2f(1.0F, 1.0F);
                glVertex2f(((float) shadowWidth / (float) WIDTH) * 2.0F - 1.0F, ((float) shadowHeight / (float) HEIGHT) * 2.0F - 1.0F);
                glTexCoord2f(0.0F, 1.0F);
                glVertex2f(-1.0F, ((float) shadowHeight / (float) HEIGHT) * 2.0F - 1.0F);
                glEnd();

                glDisable(GL_TEXTURE_2D);
                glEnable(GL_LIGHTING);
                glPopMatrix();
                glMatrixMode(GL_PROJECTION);
                gluPerspective(45.0F, 1.0F, 1.0F, 1000.0F);
                glMatrixMode(GL_MODELVIEW);

            } else {

                if (!ambientShadowsAvailable) {

                    FloatBuffer lowAmbient = BufferUtils.createFloatBuffer(4);
                    lowAmbient.put(new float[]{0.1F, 0.1F, 0.1F, 1.0F});
                    lowAmbient.flip();

                    FloatBuffer lowDiffuse = BufferUtils.createFloatBuffer(4);
                    lowDiffuse.put(new float[]{0.35F, 0.35F, 0.35F, 1.0F});
                    lowDiffuse.flip();

                    glLight(GL_LIGHT0, GL_AMBIENT, lowAmbient);
                    glLight(GL_LIGHT0, GL_DIFFUSE, lowDiffuse);

                    renderObjects(true);

                    glAlphaFunc(GL_GREATER, 0.9F);
                    glEnable(GL_ALPHA_TEST);
                }

                glLight(GL_LIGHT0, GL_AMBIENT, ambientLight);
                glLight(GL_LIGHT0, GL_DIFFUSE, diffuseLight);

                glEnable(GL_TEXTURE_2D);
                glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_COMPARE_R_TO_TEXTURE);

                glEnable(GL_TEXTURE_GEN_S);
                glEnable(GL_TEXTURE_GEN_T);
                glEnable(GL_TEXTURE_GEN_R);
                glEnable(GL_TEXTURE_GEN_Q);

                tempBuffer.put(0, textureMatrix.m00);
                tempBuffer.put(1, textureMatrix.m01);
                tempBuffer.put(2, textureMatrix.m02);
                tempBuffer.put(3, textureMatrix.m03);

                glTexGen(GL_S, GL_EYE_PLANE, tempBuffer);

                tempBuffer.put(0, textureMatrix.m10);
                tempBuffer.put(1, textureMatrix.m11);
                tempBuffer.put(2, textureMatrix.m12);
                tempBuffer.put(3, textureMatrix.m13);

                glTexGen(GL_T, GL_EYE_PLANE, tempBuffer);

                tempBuffer.put(0, textureMatrix.m20);
                tempBuffer.put(1, textureMatrix.m21);
                tempBuffer.put(2, textureMatrix.m22);
                tempBuffer.put(3, textureMatrix.m23);

                glTexGen(GL_R, GL_EYE_PLANE, tempBuffer);

                tempBuffer.put(0, textureMatrix.m30);
                tempBuffer.put(1, textureMatrix.m31);
                tempBuffer.put(2, textureMatrix.m32);
                tempBuffer.put(3, textureMatrix.m33);

                glTexGen(GL_Q, GL_EYE_PLANE, tempBuffer);

                renderObjects(true);

                glDisable(GL_ALPHA_TEST);
                glDisable(GL_TEXTURE_2D);
                glDisable(GL_TEXTURE_GEN_S);
                glDisable(GL_TEXTURE_GEN_T);
                glDisable(GL_TEXTURE_GEN_R);
                glDisable(GL_TEXTURE_GEN_Q);
            }

            if (glGetError() != GL_NO_ERROR) {
                System.out.println("An OpenGL error occurred");
            }

            Display.update();
            Display.sync(60);
        }
    }

    private void setupOpenGL() {
        checkCapabilities();
        setUpCamera();

        WIDTH = Display.getWidth();
        HEIGHT = Display.getHeight();

        maxTextureSize = glGetInteger(GL_MAX_TEXTURE_SIZE);

        glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glPolygonOffset(factor, 0.0F);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_NORMALIZE);
        glEnable(GL_LIGHT0);

        shadowTextureID = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, shadowTextureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_DEPTH_TEXTURE_MODE, GL_INTENSITY);

        if (ambientShadowsAvailable) {
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FAIL_VALUE_ARB, 0.5F);
        }

        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);

        generateShadowMap();
    }

    private void checkCapabilities() {
        if (!GLContext.getCapabilities().OpenGL14 && GLContext.getCapabilities().GL_ARB_shadow) {
            System.out.println("Can't create shadows. Requires OpenGL 1.4 or the GL_ARB_shadow extension");
            System.exit(0);
        }

        if (GLContext.getCapabilities().GL_ARB_shadow_ambient) {
            ambientShadowsAvailable = true;
        } else {
            System.err.println("GL_ARB_shadow_ambient extension not available.");
        }
    }

    private void handleInput() {
        if (Keyboard.isKeyDown(Keyboard.KEY_F) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            factor--;
            glPolygonOffset(factor, 0.0F);
            generateShadowMap();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            factor++;
            glPolygonOffset(factor, 0.0F);
            generateShadowMap();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            testVar -= 0.1F;
            generateShadowMap();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            testVar += 0.1F;
            generateShadowMap();
        }

        //TODO Add cam.handleMouse
        //TODO Add cam.handleKeyboard
    }

    private void setupFloatBuffers() {
        ambientLight.put(new float[]{0.2F, 0.2F, 0.2F, 1.0F});
        ambientLight.flip();

        diffuseLight.put(new float[]{0.7F, 0.7F, 0.7F, 1.0F});
        diffuseLight.flip();

        cameraPos.put(new float[]{100.0F, 150.0F, 200.0F, 1.0F});
        cameraPos.flip();

        lightPos.put(new float[]{100.0F, 300.0F, 100.0F, 1.0F});
        lightPos.flip();
    }

    private void setUpCamera() {

    }

    public static void main(String args[]) {
        new ShadowMapping();
    }
}