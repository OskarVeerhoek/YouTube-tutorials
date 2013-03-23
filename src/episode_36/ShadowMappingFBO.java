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

package episode_36;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import utility.BufferTools;
import utility.EulerCamera;
import utility.OBJLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBFramebufferObject.*;
import static org.lwjgl.opengl.ARBShadowAmbient.GL_TEXTURE_COMPARE_FAIL_VALUE_ARB;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * Shows how to get shadows working in OpenGL. Ported from the OpenGLSuperBible. Some code was modified by Oskar
 * Veerhoek.
 *
 * @author Sam K.
 * @author Daniel W.
 */
public class ShadowMappingFBO {

    /** The position of the omnidirectional shadow-casting light. */
    private static final FloatBuffer lightPosition = BufferTools.asFlippedFloatBuffer(200, 250, 200, 1);
    private static final FloatBuffer textureBuffer = BufferUtils.createFloatBuffer(16);
    private static final Matrix4f depthModelViewProjection = new Matrix4f();
    private static final DisplayMode DISPLAY_MODE = new DisplayMode(800, 600);
    private static final EulerCamera camera = new EulerCamera.Builder().setAspectRatio((float) DISPLAY_MODE.getWidth
            () / DISPLAY_MODE.getHeight()).setPosition(23, 34, 87).setRotation(22, 341,
            0).setNearClippingPane(2).setFarClippingPane(300).setFieldOfView(60).build();
    /**
     * The width of the depth texture that is known as the shadow map. The higher the width, the more detailed the
     * shadows.
     */
    private static int shadowMapWidth;
    /**
     * The height of the depth texture that is known as the shadow map. The higher the height, the more detailed the
     * shadows.
     */
    private static int shadowMapHeight;
    /**
     * The frame buffer holding the shadow map. This frame buffer is an off-screen rendering location to which we will
     * draw the shadow map.
     */
    private static int frameBuffer;
    /** The render buffer holding the shadow map in the form of a depth texture. */
    private static int renderBuffer;
    /** The display list that holds the Stanford bunny model. */
    private static int bunnyDisplayList;

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpFrameBufferObject();
        setUpCamera();
        setUpModel();
        drawShadowMap();
        while (!Display.isCloseRequested()) {
            render();
            logic();
            input();
            Display.update();
            Display.sync(60);
        }
        cleanUp();
        System.exit(0);
    }

    /** Sets up a display. */
    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(DISPLAY_MODE);
            Display.setVSyncEnabled(true);
            Display.setTitle("Shadow Mapping Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Couldn't set up the display");
            Display.destroy();
            System.exit(1);
        }
        if (!GLContext.getCapabilities().OpenGL14 && !GLContext.getCapabilities().GL_ARB_shadow) {
            System.out.println("Can't create shadows at all. Requires OpenGL 1.4 or the GL_ARB_shadow extension");
            Display.destroy();
            System.exit(1);
        }
        if (!GLContext.getCapabilities().GL_ARB_shadow_ambient) {
            System.err.println("GL_ARB_shadow_ambient extension not available.");
            Display.destroy();
            System.exit(1);
        }
    }

    private static void setUpStates() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_NORMALIZE);
        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glPolygonOffset(2.5F, 0.0F);
        glClearColor(0, 0.75f, 1, 1);
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(0, 0, 0, 1));
        glLight(GL_LIGHT0, GL_AMBIENT, BufferTools.asFlippedFloatBuffer(0, 0, 0, 1));
        glLight(GL_LIGHT0, GL_DIFFUSE, BufferTools.asFlippedFloatBuffer(1.7F, 1.7F, 1.7F, 1));
        // Clamp texture coordinates (e.g.: (2,0) becomes (1,0)) because we only want one shadow.
        // Use 'TO_EDGE' to prevent the texture borders to affect the shadow map through linear texture filtering.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE); // [x,y,z,w] -> [s,t,r,q]
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        // Enable bilinear texture filtering. This means that the colour will be
        // a 'weighted value of the four texture elements that are closest to the center of the pixel being textured'
        // (from OpenGL 2.1 References Pages).
        // The alternative to linear texture filtering is nearest-neighbour texture filtering. Here the colour of
        // the closest texel to the given texture coordinate (texture pixel) is taken. This, while being fast, may
        // produce a pixelated image.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        // State that the texture holds nondescript 'intensity' data.
        glTexParameteri(GL_TEXTURE_2D, GL_DEPTH_TEXTURE_MODE, GL_INTENSITY);
        // If the intensity of a given texel is lower than 0.5f, then the texture should not be sampled. In practice,
        // the higher the value, the less of the shadow is visible, and the other way around.
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FAIL_VALUE_ARB, 0.5F);
        // Set the automatic texture coordinate generation mode to eye linear. The texture coordinate is calculated
        // with the inverse of the model-view matrix and a plane that we will specify later on.
        glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_R, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
        glTexGeni(GL_Q, GL_TEXTURE_GEN_MODE, GL_EYE_LINEAR);
    }

    /** Sets up the OpenGL states. */
    private static void setUpFrameBufferObject() {
        final int MAX_RENDERBUFFER_SIZE = glGetInteger(GL_MAX_RENDERBUFFER_SIZE);
        final int MAX_TEXTURE_SIZE = glGetInteger(GL_MAX_TEXTURE_SIZE);
        /**
         * Cap the maximum shadow map size at 1024x1024 pixels or at the maximum render buffer size. If you have a good
         * graphics card, feel free to increase this value. The program will lag
         * if I record and run the program at the same time with higher values.
         */
        if (MAX_TEXTURE_SIZE > 1024) {
            if (MAX_RENDERBUFFER_SIZE < MAX_TEXTURE_SIZE) {
                shadowMapWidth = shadowMapHeight = MAX_RENDERBUFFER_SIZE;
            } else {
                shadowMapWidth = shadowMapHeight = 1024;
            }
        } else {
            shadowMapWidth = shadowMapHeight = MAX_TEXTURE_SIZE;
        }
        // Generate and bind a frame buffer.
        frameBuffer = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
        // Generate and bind a render buffer.
        renderBuffer = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
        // Set the internal storage format of the render buffer to a depth component of 32 bits (4 bytes).
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT32, shadowMapWidth, shadowMapHeight);
        // Attach the render buffer to the frame buffer as a depth attachment. This means that, if the frame buffer is
        // bound, any depth texture values will be copied to the render buffer object.
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer);
        // OpenGL shall make no amendment to the colour or multisample buffer.
        glDrawBuffer(GL_NONE);
        // Disable the colour buffer for pixel read operations (such as glReadPixels or glCopyTexImage2D).
        glReadBuffer(GL_NONE);
        // Check for frame buffer errors.
        int FBOStatus = glCheckFramebufferStatus(GL_FRAMEBUFFER);
        if (FBOStatus != GL_FRAMEBUFFER_COMPLETE) {
            System.err.println("Framebuffer error: " + gluErrorString(glGetError()));
        }
        // Bind the default frame buffer, which is used for ordinary drawing.
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    private static void setUpCamera() {
        camera.applyPerspectiveMatrix();
        camera.applyOptimalStates();
    }

    private static void setUpModel() {
        try {
            bunnyDisplayList = OBJLoader.createDisplayList(OBJLoader.loadModel(new File("res/models/bunny.obj")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            cleanUp();
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp();
        }
    }

    private static void render() {
        // Reset any changes made to the model-view matrix.
        glLoadIdentity();
        // Apply the camera position and orientation to the model-view matrix.
        camera.applyTranslations();
        // Clear the screen.
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // Store the current attribute state.
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        {
            generateTextureCoordinates();
            drawGround();
            drawScene();
            drawShadowMap();
        }
        // Restore the previous attribute state.
        glPopAttrib();
    }

    private static void generateTextureCoordinates() {
        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        // Compare the texture coordinate 'r' (the distance from the light to the surface of the object) to the
        // value in the depth buffer.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_COMPARE_R_TO_TEXTURE);
        // Enable 's' texture coordinate generation.
        glEnable(GL_TEXTURE_GEN_S);
        // Enable 't' texture coordinate generation.
        glEnable(GL_TEXTURE_GEN_T);
        // Enable 'r' texture coordinate generation.
        glEnable(GL_TEXTURE_GEN_R);
        // Enable 'q' texture coordinate generation.
        glEnable(GL_TEXTURE_GEN_Q);
        textureBuffer.clear();
        textureBuffer.put(0, depthModelViewProjection.m00);
        textureBuffer.put(1, depthModelViewProjection.m01);
        textureBuffer.put(2, depthModelViewProjection.m02);
        textureBuffer.put(3, depthModelViewProjection.m03);

        glTexGen(GL_S, GL_EYE_PLANE, textureBuffer);

        textureBuffer.put(0, depthModelViewProjection.m10);
        textureBuffer.put(1, depthModelViewProjection.m11);
        textureBuffer.put(2, depthModelViewProjection.m12);
        textureBuffer.put(3, depthModelViewProjection.m13);

        glTexGen(GL_T, GL_EYE_PLANE, textureBuffer);

        textureBuffer.put(0, depthModelViewProjection.m20);
        textureBuffer.put(1, depthModelViewProjection.m21);
        textureBuffer.put(2, depthModelViewProjection.m22);
        textureBuffer.put(3, depthModelViewProjection.m23);

        glTexGen(GL_R, GL_EYE_PLANE, textureBuffer);

        textureBuffer.put(0, depthModelViewProjection.m30);
        textureBuffer.put(1, depthModelViewProjection.m31);
        textureBuffer.put(2, depthModelViewProjection.m32);
        textureBuffer.put(3, depthModelViewProjection.m33);

        glTexGen(GL_Q, GL_EYE_PLANE, textureBuffer);
    }

    private static void drawGround() {
        glPushAttrib(GL_LIGHTING_BIT);
        {
            glDisable(GL_LIGHTING);
            glBegin(GL_QUADS);
            glColor3f(0.3F, 0.6F, 0.3F);
            glVertex3f(-120, -19, -120);
            glVertex3f(-120, -19, +120);
            glVertex3f(+120, -19, +120);
            glVertex3f(+120, -19, -120);
            glEnd();
        }
        glPopAttrib();
    }

    /** Generate the shadow map. */
    private static void drawShadowMap() {
        /**
         * The model-view matrix of the light.
         */
        FloatBuffer lightModelView = BufferUtils.createFloatBuffer(16);
        /**
         * The projection matrix of the light.
         */
        FloatBuffer lightProjection = BufferUtils.createFloatBuffer(16);
        Matrix4f lightProjectionTemp = new Matrix4f();
        Matrix4f lightModelViewTemp = new Matrix4f();
        /**
         * The radius that encompasses all the objects that cast shadows in the scene. There should
         * be no object farther away than 50 units from [0, 0, 0] in any direction.
         * If an object exceeds the radius, the object may cast shadows wrongly.
         */
        float sceneBoundingRadius = 50;
        /**
         * The distance from the light to the scene, assuming that the scene is located
         * at [0, 0, 0]. Using the Pythagorean theorem, the distance is calculated by taking the square-root of the
         * sum of each of the components of the light position squared.
         */
        float lightToSceneDistance = (float) Math.sqrt(lightPosition.get(0) * lightPosition.get(0) +
                lightPosition.get(1) * lightPosition.get(1) +
                lightPosition.get(2) * lightPosition.get(2));
        /**
         * The distance to the object that is nearest to the camera. This excludes objects that do not cast shadows.
         * This will be used as the zNear parameter in gluPerspective.
         */
        float nearPlane = lightToSceneDistance - sceneBoundingRadius;
        if (nearPlane < 0) {
            System.err.println("Camera is too close to scene. A valid shadow map cannot be generated.");
        }
        /**
         * The field-of-view of the shadow frustum in degrees. Formula taken from the OpenGL SuperBible.
         */
        float fieldOfView = (float) Math.toDegrees(2.0F * Math.atan(sceneBoundingRadius / lightToSceneDistance));
        glMatrixMode(GL_PROJECTION);
        // Store the current projection matrix.
        glPushMatrix();
        glLoadIdentity();
        // Generate the 'shadow frustum', a perspective projection matrix that shows all the objects in the scene.
        gluPerspective(fieldOfView, 1, nearPlane, nearPlane + sceneBoundingRadius * 2);
        // Store the shadow frustum in 'lightProjection'.
        glGetFloat(GL_PROJECTION_MATRIX, lightProjection);
        glMatrixMode(GL_MODELVIEW);
        // Store the current model-view matrix.
        glPushMatrix();
        glLoadIdentity();
        // Have the 'shadow camera' look toward [0, 0, 0] and be location at the light's position.
        gluLookAt(lightPosition.get(0), lightPosition.get(1), lightPosition.get(2), 0, 0, 0, 0, 1, 0);
        glGetFloat(GL_MODELVIEW_MATRIX, lightModelView);
        // Set the view port to the shadow map dimensions so no part of the shadow is cut off.
        glViewport(0, 0, shadowMapWidth, shadowMapHeight);
        // Bind the extra frame buffer in which to store the shadow map in the form a depth texture.
        glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
        // Clear only the depth buffer bit. Clearing the colour buffer is unnecessary, because it is disabled (we
        // only need depth components).
        glClear(GL_DEPTH_BUFFER_BIT);
        // Store the current attribute state.
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        {
            // Disable smooth shading, because the shading in a shadow map is irrelevant. It only matters where the
            // shape
            // vertices are positioned, and not what colour they have.
            glShadeModel(GL_FLAT);
            // Enabling all these lighting states is unnecessary for reasons listed above.
            glDisable(GL_LIGHTING);
            glDisable(GL_COLOR_MATERIAL);
            glDisable(GL_NORMALIZE);
            // Disable the writing of the red, green, blue, and alpha colour components,
            // because we only need the depth component.
            glColorMask(false, false, false, false);
            // An offset is given to every depth value of every polygon fragment to prevent a visual quirk called
            // 'shadow
            // acne'.
            glEnable(GL_POLYGON_OFFSET_FILL);
            // Draw the objects that cast shadows.
            drawScene();
            /**
             * Copy the pixels of the shadow map to the frame buffer object depth attachment.
             *  int target -> GL_TEXTURE_2D
             *  int level  -> 0, has to do with mip-mapping, which is not applicable to shadow maps
             *  int internalformat -> GL_DEPTH_COMPONENT
             *  int x, y -> 0, 0
             *  int width, height -> shadowMapWidth, shadowMapHeight
             *  int border -> 0
             */
            glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, 0, 0, shadowMapWidth, shadowMapHeight, 0);
            // Restore the previous model-view matrix.
            glPopMatrix();
            glMatrixMode(GL_PROJECTION);
            // Restore the previous projection matrix.
            glPopMatrix();
            glMatrixMode(GL_MODELVIEW);
            glBindFramebuffer(GL_FRAMEBUFFER, 0);
        }// Restore the previous attribute state.
        glPopAttrib();
        // Restore the view port.
        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        lightProjectionTemp.load(lightProjection);
        lightModelViewTemp.load(lightModelView);
        lightProjection.flip();
        lightModelView.flip();
        depthModelViewProjection.setIdentity();
        // [-1,1] -> [-0.5,0.5] -> [0,1]
        depthModelViewProjection.translate(new Vector3f(0.5F, 0.5F, 0.5F));
        depthModelViewProjection.scale(new Vector3f(0.5F, 0.5F, 0.5F));
        // Multiply the texture matrix by the projection and model-view matrices of the light.
        Matrix4f.mul(depthModelViewProjection, lightProjectionTemp, depthModelViewProjection);
        Matrix4f.mul(depthModelViewProjection, lightModelViewTemp, depthModelViewProjection);
        // Transpose the texture matrix.
        Matrix4f.transpose(depthModelViewProjection, depthModelViewProjection);
    }

    /** This is where anything you want rendered into your world should go. */
    private static void drawScene() {
        glPushMatrix();
        glScalef(5, 5, 5);
        glTranslatef(0, -2, 0);
        glCallList(bunnyDisplayList);
        glPopMatrix();
    }

    private static void logic() {
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }

    /** Handles the keyboard and mouse input. */
    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                    lightPosition.flip();
                    lightPosition.clear();
                    lightPosition.put(new float[]{camera.x(), camera.y(), camera.z(), 1});
                    lightPosition.flip();
                }
            }
        }
        if (Mouse.isGrabbed()) {
            camera.processMouse(1.0f, 80, -80);
        }
        camera.processKeyboard(16.0f, 10);
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
    }

    /** Cleanup after the program. */
    private static void cleanUp() {
        glDeleteFramebuffers(frameBuffer);
        glDeleteLists(bunnyDisplayList, 1);
        glDeleteRenderbuffers(renderBuffer);
        Display.destroy();
    }
}