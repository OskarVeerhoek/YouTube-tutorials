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

package future;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import utility.EulerCamera;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBShadowAmbient.GL_TEXTURE_COMPARE_FAIL_VALUE_ARB;
import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Shows how to get shadows working in OpenGL. Ported from the OpenGLSuperBible.
 *
 * @author Sam K.
 * @author Daniel W.
 */
public class ShadowMappingFBO {
    // The amount of polygon offset to use
    private static float factor = 4.0F;

    public static int maxTextureSize;

    private static int shadowWidth = 640;
    private static int shadowHeight = 480;

    private static int frameBuffer;
    private static int renderBuffer;

    private static FloatBuffer ambientLight = BufferUtils.createFloatBuffer(4);
    private static FloatBuffer diffuseLight = BufferUtils.createFloatBuffer(4);
    private static FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
    private static FloatBuffer tempBuffer = BufferUtils.createFloatBuffer(4);

    private static Matrix4f textureMatrix = new Matrix4f();
    private static Sphere sphere = new Sphere();
    private static EulerCamera camera;

    public static void main(String[] args) {
        setUpDisplay();
        setUpCamera();
        setUpBufferValues();
        setUpOpenGL();
        while (!Display.isCloseRequested()) {
            render();
            input();
            Display.update();
            Display.sync(60);
        }
        cleanUp();
        System.exit(0);
    }

    private static void setUpCamera() {
        camera = new EulerCamera((float) Display.getWidth() / (float) Display.getHeight(), 100.0F, 50.0F, 200.0F);
    }

    /**
     * Sets up a display.
     */
    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.setTitle("Shadow Mapping Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Couldn't set up the display");
            Display.destroy();
            System.exit(1);
        }
    }

    /**
     * This is where anything you want rendered into your world should go.
     *
     * @param drawGround
     */
    private static void renderObjects(boolean drawGround) {
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

        glColor3f(1.0F, 0.0F / 10, 0.0F);
        sphere.draw(12.0F, 50, 50);

        glColor3f(0.0F, 1.0F, 0.0F);
        glPushMatrix();
        glTranslatef(-60.0F, 0.0F, 0.0F);
        sphere.draw(14.0F, 50, 50);
        glPopMatrix();

        glColor3f(1.0F, 1.0F, 0.0F);
        glPushMatrix();
        glTranslatef(-60.0F, 0.0F, -24.0F);
        sphere.draw(15.0F, 50, 50);
        glPopMatrix();

        glColor3f(1.0F, 0.0F, 1.0F);
        glPushMatrix();
        glTranslatef(0.0F, 0.0F, 60.0F);
        sphere.draw(16.0F, 50, 50);
        glPopMatrix();

        glColor3f(0.0F, 1.0F, 1.0F);
        glPushMatrix();
        glTranslatef(0.0F, 0.0F, -60.0F);
        sphere.draw(22.0F, 50, 50);
        glPopMatrix();
    }

    /**
     * Generate the shadow map.
     */
    private static void generateShadowMap() {
        float lightToSceneDistance, nearPlane, fieldOfView;
        FloatBuffer lightModelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer lightProjection = BufferUtils.createFloatBuffer(16);
        Matrix4f lightProjectionTemp = new Matrix4f();
        Matrix4f lightModelViewTemp = new Matrix4f();

        float sceneBoundingRadius = 95.0F;

        lightToSceneDistance = (float) Math.sqrt(lightPosition
                .get(0)
                * lightPosition
                .get(0) + lightPosition
                .get(1) * lightPosition
                .get(1)
                + lightPosition
                .get(2) * lightPosition
                .get(2));

        nearPlane = lightToSceneDistance - sceneBoundingRadius;

        fieldOfView = (float) Math.toDegrees(2.0F * Math
                .atan(sceneBoundingRadius / lightToSceneDistance));

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fieldOfView, 1.0F, nearPlane, nearPlane
                + (2.0F * sceneBoundingRadius));
        glGetFloat(GL_PROJECTION_MATRIX, lightProjection);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(lightPosition
                .get(0), lightPosition
                .get(1), lightPosition
                .get(2), 0.0F,
                0.0F, 0.0F, 0.0F, 1.0F, 0.0F);
        glGetFloat(GL_MODELVIEW_MATRIX, lightModelView);
        glViewport(0, 0, shadowWidth, shadowHeight);

        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, frameBuffer);

        glClear(GL_DEPTH_BUFFER_BIT);

        // Set rendering states to the minimum required, for speed.
        glShadeModel(GL_FLAT);
        glDisable(GL_LIGHTING);
        glDisable(GL_COLOR_MATERIAL);
        glDisable(GL_NORMALIZE);
        glColorMask(false, false, false, false);

        glEnable(GL_POLYGON_OFFSET_FILL);

        renderObjects(false);

        glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, 0, 0,
                shadowWidth, shadowHeight, 0);

        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);

        // Setup the rendering states.
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

    /**
     * Render the scene, and then update.
     */
    private static void render() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(40, (float) Display.getWidth() / (float) Display.getHeight(), 1.0F, 1000.0F);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        camera.applyTranslations();

        glViewport(0, 0, Display.getWidth(), Display.getHeight());

        glLight(GL_LIGHT0, GL_POSITION, lightPosition
        );
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glLight(GL_LIGHT0, GL_AMBIENT, ambientLight);
        glLight(GL_LIGHT0, GL_DIFFUSE, diffuseLight);

        glEnable(GL_TEXTURE_2D);
        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE,
                GL_COMPARE_R_TO_TEXTURE);

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


        if (glGetError() != GL_NO_ERROR) {
            System.out.println("An OpenGL error occurred");
        }
    }

    /**
     * Sets up the OpenGL states.
     */
    public static void setUpOpenGL() {
        int maxRenderbufferSize = glGetInteger(GL_MAX_RENDERBUFFER_SIZE_EXT);

        if (!GLContext.getCapabilities().OpenGL14
                && !GLContext.getCapabilities().GL_ARB_shadow) {
            System.out
                    .println("Can't create shadows at all. Requires OpenGL 1.4 or the GL_ARB_shadow extension");
            Display.destroy();
            System.exit(0);
        }

        if (!GLContext.getCapabilities().GL_ARB_shadow_ambient) {
            System.err
                    .println("GL_ARB_shadow_ambient extension not availible.");
            Display.destroy();
            System.exit(0);
        }

        if (GLContext.getCapabilities().OpenGL20
                || GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            System.out.println("Higher quality shadows are availible.");
        }

        maxTextureSize = glGetInteger(GL_MAX_TEXTURE_SIZE);

        System.out.println("Maximum texture size: " + maxTextureSize);
        System.out.println("Maximum renderbuffer size: " + maxRenderbufferSize);

        /*
           * Check to see if the maximum texture size is bigger than 2048.
           * Performance drops too much if it much bigger than that.
           */
        if (maxTextureSize > 512) {
            maxTextureSize = 512;
            if (maxRenderbufferSize < maxTextureSize) {
                maxTextureSize = maxRenderbufferSize;
            }
        }

        shadowWidth = maxTextureSize;
        shadowHeight = maxTextureSize;

        glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glPolygonOffset(factor, 0.0F);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_NORMALIZE);
        glEnable(GL_LIGHT0);

        // Setup some texture states
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_DEPTH_TEXTURE_MODE, GL_INTENSITY);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FAIL_VALUE_ARB,
                0.5F);

        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);

        frameBuffer = glGenFramebuffersEXT();
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, frameBuffer);

        renderBuffer = glGenRenderbuffersEXT();
        glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, renderBuffer);

        glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_COMPONENT32,
                maxTextureSize, maxTextureSize);

        glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,
                GL_DEPTH_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT,
                renderBuffer);

        glDrawBuffer(GL_NONE);
        glReadBuffer(GL_NONE);

        int FBOStatus = glCheckFramebufferStatusEXT(GL_FRAMEBUFFER_EXT);
        if (FBOStatus != GL_FRAMEBUFFER_COMPLETE_EXT) {
            System.out.println("Framebuffer error!");
        }

        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
        generateShadowMap();
    }

    /**
     * Handles the keyboard and mouse input.
     */
    public static void input() {
        if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
            factor--;
            glPolygonOffset(factor, 0.0F);
            generateShadowMap();
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            factor++;
            glPolygonOffset(factor, 10);
            generateShadowMap();
        }
        camera.processMouse(1.0f, 80, -80);
        camera.processKeyboard(16.0f, 0.15f, 0.15f, 0.15f);
        if (Mouse.isButtonDown(0))
            Mouse.setGrabbed(true);
        else if (Mouse.isButtonDown(1))
            Mouse.setGrabbed(false);
    }

    /**
     * Cleanup after the program.
     */
    private static void cleanUp() {
        glDeleteFramebuffersEXT(frameBuffer);
        glDeleteRenderbuffersEXT(renderBuffer);
        Display.destroy();
    }

    /**
     * Sets up the FloatBuffers to be used later on.
     */
    private static void setUpBufferValues() {
        ambientLight.put(new float[]{0.2F, 0.2F, 0.2F, 1.0F});
        ambientLight.flip();

        diffuseLight.put(new float[]{0.7F, 0.7F, 0.7F, 1.0F});
        diffuseLight.flip();

        lightPosition
                .put(new float[]{100.0F, 300.0F, 100.0F, 1.0F});
        lightPosition
                .flip();
    }
}
