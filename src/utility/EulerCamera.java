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
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import static java.lang.Math.*;
import static org.lwjgl.opengl.ARBDepthClamp.GL_DEPTH_CLAMP;
import static org.lwjgl.opengl.GL11.*;

/**
 * A camera set in 3D perspective. The camera uses Euler angles internally, so a gimble lock could occur.
 * // TODO: http://www.youtube.com/watch?v=aV3gKoS_DAg&lc=qh6Nrm78F36_ANdW5-rIfk8b8RPDqVfQLNVQRiwKRT4&feature=inbox
 *
 * @author Oskar Veerhoek
 */
public final class EulerCamera {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;
    private float fov = 90;
    private final float aspectRatio;
    private final float zNear;
    private final float zFar;

    public EulerCamera(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.pitch = builder.pitch;
        this.yaw = builder.yaw;
        this.roll = builder.roll;
        this.aspectRatio = builder.aspectRatio;
        this.zNear = builder.zNear;
        this.zFar = builder.zFar;
        this.fov = builder.fov;
    }

    /**
     * Creates a new camera with the given aspect ratio.
     * It's located at [0 0 0] with the orientation [0 0 0]. It has a zNear of 0.3, a zFar of 100.0, and an fov of 90.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     */
    public EulerCamera(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        this.zNear = 0.3f;
        this.zFar = 100;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     */
    public EulerCamera(float aspectRatio, double x, double y, double z) {
        this.aspectRatio = aspectRatio;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.zNear = 0.3f;
        this.zFar = 100;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     */
    public EulerCamera(float aspectRatio, float x, float y, float z) {
        this(aspectRatio);
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
    public EulerCamera(float aspectRatio, double x, double y, double z, double pitch, double yaw, double roll) {
        this(aspectRatio, x, y, z);
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
    public EulerCamera(float aspectRatio, float x, float y, float z, float pitch, float yaw, float roll) {
        this(aspectRatio, x, y, z);
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }
    /**
     * Creates a new camera with the given aspect ratio, location, zNear, zFar and orientation.
     *
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x           the first location coordinate
     * @param y           the second location coordinate
     * @param z           the third location coordinate
     * @param pitch       the pitch (rotation on the x-axis)
     * @param yaw         the yaw (rotation on the y-axis)
     * @param roll        the roll (rotation on the z-axis)
     */
    public EulerCamera(float aspectRatio, float x, float y, float z, float pitch, float yaw, float roll, float zNear, float zFar) {
        this.aspectRatio = aspectRatio;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    /**
     * Processes mouse input and converts it in to camera movement using the mouseSpeed value.
     *
     * @param mouseSpeed  the speed (sensitivity) of the mouse, 1.0 should suffice
     */
    public void processMouse(float mouseSpeed) {
        final float maxLookUp = 90;
        final float maxLookDown = -90;
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
     * Processes mouse input and converts it in to camera movement using the mouseSpeed value.
     *
     * @param mouseSpeed  the speed (sensitivity) of the mouse, 1.0 should suffice
     * @param maxLookUp   the maximum angle in degrees at which you can look up
     * @param maxLookDown the maximum angle in degrees at which you can look down
     */
    public void processMouse(float mouseSpeed, float maxLookUp, float maxLookDown) {
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
     * @param delta  the elapsed time since the last frame update in milliseconds
     * @param speed the speed of the movement on the three axes (normal = 1.0)
     */
    public void processKeyboard(float delta, float speed) {
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if (keyUp && keyRight && !keyLeft && !keyDown) {
            moveFromLook(speed * delta * 0.003f, 0, -speed * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            moveFromLook(-speed * delta * 0.003f, 0, -speed * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            moveFromLook(0, 0, -speed * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            moveFromLook(-speed * delta * 0.003f, 0, speed * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            moveFromLook(speed * delta * 0.003f, 0, speed * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            moveFromLook(0, 0, speed * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            moveFromLook(-speed * delta * 0.003f, 0, 0);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            moveFromLook(speed * delta * 0.003f, 0, 0);
        }
        if (flyUp && !flyDown) {
            y += speed * delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            y -= speed * delta * 0.003f;
        }
    }

    /**
     * @param delta  the elapsed time since the last frame update in milliseconds
     * @param speedX the speed of the movement on the x-axis (normal = 1.0)
     * @param speedY the speed of the movement on the y-axis (normal = 1.0)
     * @param speedZ the speed of the movement on the z-axis (normal = 1.0)
     */
    public void processKeyboard(float delta, float speedX, float speedY, float speedZ) {
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
        boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
        boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

        if (keyUp && keyRight && !keyLeft && !keyDown) {
            moveFromLook(speedX * delta * 0.003f, 0, -speedZ * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            moveFromLook(-speedX * delta * 0.003f, 0, -speedZ * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            moveFromLook(0, 0, -speedZ * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            moveFromLook(-speedX * delta * 0.003f, 0, speedZ * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            moveFromLook(speedX * delta * 0.003f, 0, speedZ * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            moveFromLook(0, 0, speedZ * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            moveFromLook(-speedX * delta * 0.003f, 0, 0);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            moveFromLook(speedX * delta * 0.003f, 0, 0);
        }
        if (flyUp && !flyDown) {
            y += speedY * delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            y -= speedY * delta * 0.003f;
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
        this.z += dx * (float) cos(toRadians(yaw - 90)) + dz * cos(toRadians(yaw));
        this.x -= dx * (float) sin(toRadians(yaw - 90)) + dz * sin(toRadians(yaw));
        this.y += dy * (float) sin(toRadians(pitch - 90)) + dz * sin(toRadians(pitch));
        //float hypotenuseX = dx;
        //float adjacentX = hypotenuseX * (float) Math.cos(Math.toRadians(yaw - 90));
        //float oppositeX = (float) Math.sin(Math.toRadians(yaw - 90)) * hypotenuseX;
        //this.z += adjacentX;
        //this.x -= oppositeX;
        //
        //this.y += dy;
        //
        //float hypotenuseZ = dz;
        //float adjacentZ = hypotenuseZ * (float) Math.cos(Math.toRadians(yaw));
        //float oppositeZ = (float) Math.sin(Math.toRadians(yaw)) * hypotenuseZ;
        //this.z += adjacentZ;
        //this.x -= oppositeZ;
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
        // TODO: Add aspect ratio handling for glOrtho
        glOrtho(-1, 1, -1, 1, 0, 10000);
        glMatrixMode(previousMatrixMode);
    }

    /**
     * Enables or disables the following OpenGL states:
     *  Enable GL_DEPTH_CLAMP if ARB_depth_clamp is supported
     */
    public void applyOptimalStates() {
        if (GLContext.getCapabilities().GL_ARB_depth_clamp) {
            glEnable(GL_DEPTH_CLAMP);
        }
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
     * Sets the rotation of the camera in euler angles.
     *
     * @param pitch the rotation around the x-axis in degrees
     * @param yaw   the rotation around the y-axis in degrees
     * @param roll  the rotation around the z-axis in degrees
     */
    public void setRotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
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

    /**
     * Sets the field of view angle in degrees in the y direction.
     *
     * @param fov the field of view angle in degrees in the y direction
     */
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
        return "EulerCamera [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch
                + ", yaw=" + yaw + ", roll=" + roll + ", fov=" + fov
                + ", aspectRatio=" + aspectRatio + ", zNear=" + zNear
                + ", zFar=" + zFar + "]";
    }

    public static class Builder {
        // Required
        private final float aspectRatio;
        // Optional
        private float x = 0,
                 y = 0,
                 z = 0,
                 pitch = 0,
                 yaw = 0,
                 roll = 0;
        private float zNear = 0.3f;
        private float zFar = 100;
        private float fov = 90;
        public Builder(float aspectRatio) {
            this.aspectRatio = aspectRatio;
        }
        public Builder setzNear(float zNear) {
            this.zNear = zNear;
            return this;
        }
        public Builder setzFar(float zFar) {
            this.zFar = zFar;
            return this;
        }
        public Builder setFov(float fov) {
            this.fov = fov;
            return this;
        }
        public Builder setPosition(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }
        public Builder setRotation(float pitch, float yaw, float roll) {
            this.pitch = pitch;
            this.yaw = yaw;
            this.roll = roll;
            return this;
        }
        public EulerCamera build() {
            return new EulerCamera(this);
        }
    }

}
