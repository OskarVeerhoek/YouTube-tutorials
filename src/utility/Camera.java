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

package utility;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * A camera set in 3D perspective. The camera uses Euler angles internally, so a gimble lock could occur.
 *
 * @author Oskar Veerhoek
 */
public final class Camera {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;
    private float fov = 90;
    private final float aspectRatio;
    private final float zNear = 0.3f;
    private final float zFar = 100f;

    /**
     * Creates a new camera with the given aspect ratio.
     * It's located at [0 0 0] with the orientation [0 0 0]. It has a zNear of 0.3, a zFar of 100.0, and an fov of 90.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     */
    public Camera(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     */
    public Camera(float aspectRatio, double x, double y, double z) {
        this.aspectRatio = aspectRatio;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     */
    public Camera(float aspectRatio, float x, float y, float z) {
        this.aspectRatio = aspectRatio;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new camera with the given aspect ratio, location, and orientation.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     * @param pitch       the pitch (rotation on the x-axis)
     * @param yaw         the yaw (rotation on the y-axis)
     * @param roll        the roll (rotation on the z-axis)
     */
    public Camera(float aspectRatio, double x, double y, double z, double pitch, double yaw, double roll) {
        this.aspectRatio = aspectRatio;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.pitch = (float) pitch;
        this.yaw = (float) yaw;
        this.roll = (float) roll;
    }

    /**
     * Creates a new camera with the given aspect ratio, location, and orientation.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     * @param pitch       the pitch (rotation on the x-axis)
     * @param yaw         the yaw (rotation on the y-axis)
     * @param roll        the roll (rotation on the z-axis)
     */
    public Camera(float aspectRatio, float x, float y, float z, float pitch, float yaw, float roll) {
        this.aspectRatio = aspectRatio;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    /**
     * Processes mouse input and converts it in to camera movement using the mouseSpeed value.
     *
     * @param mouseSpeed  the speed (sensitivity) of the mouse
     * @param maxLookUp   the maximum angle at which you can look up
     * @param maxLookDown the maximum angle at which you can look down
     */
    public void processMouse(float mouseSpeed, float maxLookUp, float maxLookDown) {
        if (!Mouse.isGrabbed()) return;
        float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
        float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
        if (yaw + mouseDX >= 360) {
            yaw = yaw + mouseDX - 360;
        } else if (yaw + mouseDX < 0) {
            yaw = 360 - yaw + mouseDX;
        } else {
            yaw += mouseDX;
        }
        if (pitch - mouseDY >= maxLookDown
                && pitch - mouseDY <= maxLookUp) {
            pitch += -mouseDY;
        } else if (pitch - mouseDY < maxLookDown) {
            pitch = maxLookDown;
        } else if (pitch - mouseDY > maxLookUp) {
            pitch = maxLookUp;
        }
    }

    /**
     * @param delta  the elapsed time since the last frame update in seconds
     * @param speedX the speed of the movement on the x-axis (normal = 0.003)
     * @param speedY the speed of the movement on the y-axis (normal = 0.003)
     * @param speedZ the speed of the movement on the z-axis (normal = 0.003)
     */
    public void processKeyboard(float delta, float speedX, float speedY, float speedZ) {
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if (keyUp && keyRight && !keyLeft && !keyDown) {
            moveFromLook(speedX * delta, 0, -speedZ * delta);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            moveFromLook(-speedX * delta, 0, -speedZ * delta);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            moveFromLook(0, 0, -speedZ * delta);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            moveFromLook(-speedX * delta, 0, speedZ * delta);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            moveFromLook(speedX * delta, 0, speedZ * delta);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            moveFromLook(0, 0, speedZ * delta);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            moveFromLook(-speedX * delta, 0, 0);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            moveFromLook(speedX * delta, 0, 0);
        }
        if (flyUp && !flyDown) {
            y += speedY * delta;
        }
        if (flyDown && !flyUp) {
            y -= speedY * delta;
        }
    }

    /**
     * Move in the direction you're looking. That is, this method assumes a new coordinate system where the axis you're
     * looking down is the z-axis, the axis to your left is the x-axis, and the upward axis is the y-axis.
     *
     * @param dx the movement along the x-axis
     * @param dy the movement along the y-axis
     * @param dz the movement along the z-axis
     */
    public void moveFromLook(float dx, float dy, float dz) {
        float nX = this.x;
        float nY = this.y;
        float nZ = this.z;

        float hypotenuseX = dx;
        float adjacentX = hypotenuseX * (float) Math.cos(Math.toRadians(yaw - 90));
        float oppositeX = (float) Math.sin(Math.toRadians(yaw - 90)) * hypotenuseX;
        nZ += adjacentX;
        nX -= oppositeX;

        nY += dy;

        float hypotenuseZ = dz;
        float adjacentZ = hypotenuseZ * (float) Math.cos(Math.toRadians(yaw));
        float oppositeZ = (float) Math.sin(Math.toRadians(yaw)) * hypotenuseZ;
        nZ += adjacentZ;
        nX -= oppositeZ;

        this.x = nX;
        this.y = nY;
        this.z = nZ;
    }

    /**
     * Move along the x, y, and z axes with the given distance.
     *
     * @param distance the distance you will travel
     * @param x        the distance you will travel along the x-axis
     * @param y        the distance you will travel along the y-axis
     * @param z        the distance you will travel along the z-axis
     */
    public void moveAlongAxis(float distance, float x, float y, float z) {
        this.x += x * distance;
        this.y += y * distance;
        this.z += z * distance;
    }

    /**
     * Sets the position of the camera.
     *
     * @param x the x-coordinate of the camera
     * @param y the y-coordinate of the camera
     * @param z the z-coordinate of the camera
     */
    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Applies an orthographic projection matrix. The matrix mode will be returned it its previous value.
     * GL_MODELVIEW.
     */
    public void applyOrthographicMatrix() {
        int previousMatrixMode = glGetInteger(GL_MATRIX_MODE);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1, 1, -1, 1, 0, 10000);
        glMatrixMode(previousMatrixMode);
    }

    /**
     * Replaces the projection matrix by the one generated by the camera. The matrix mode will be returned it its previous value.
     * to GL_MODELVIEW.
     */
    public void applyPerspectiveMatrix() {
        int previousMatrixMode = glGetInteger(GL_MATRIX_MODE);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, zNear, zFar);
        glMatrixMode(previousMatrixMode);
    }

    /**
     * Applies the camera translations along the three axes to the model-view matrix.
     */
    public void applyTranslations() {
        int previousMatrixMode = glGetInteger(GL_MATRIX_MODE);
        glMatrixMode(GL_MODELVIEW);
        glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glRotatef(roll, 0, 0, 1);
        glTranslatef(-x, -y, -z);
        glMatrixMode(previousMatrixMode);
    }

    /**
     * Applies a translation of [x y z] to the camera.
     *
     * @param x the x-coordinate of the translation vector
     * @param y the y-coordinate of the translation vector
     * @param z the z-coordinate of the translation vector
     */
    public void translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public float getzNear() {
        return zNear;
    }

    public float getzFar() {
        return zFar;
    }

    @Override
    public String toString() {
        return "Camera [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch
                + ", yaw=" + yaw + ", roll=" + roll + ", fov=" + fov
                + ", aspectRatio=" + aspectRatio + ", zNear=" + zNear
                + ", zFar=" + zFar + "]";
    }


}
