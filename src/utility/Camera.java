package utility;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * A camera set in 3D perspective.
 * @author Oskar Veerhoek, author of TheCodingUniverse (www.youtube.com/thecodinguniverse)
 */
public final class Camera {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;
    private float fov = 90;
    private float aspectRatio;
    private float zNear = 0.3f;
    private float zFar = 100f;

    /**
     * Creates a new camera with the given aspect ratio. 
     * It's located at [0 0 0] with the orientation [0 0 0]. It has a zNear of 0.3, a zFar of 100.0, and an fov of 90.
     * @param aspectRatio the aspect ratio (width/height) of the camera
     */
    public Camera(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x the first location coordinate
     * @param y the second location coordinate
     * @param z the third location coordinate
     */
    public Camera(float aspectRatio, double x, double y, double z) {
        this.aspectRatio = aspectRatio;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    /**
     * Creates a new camera with the given aspect ratio and location.
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x the first location coordinate
     * @param y the second location coordinate
     * @param z the third location coordinate
     */
    public Camera(float aspectRatio, float x, float y, float z) {
        this.aspectRatio = aspectRatio;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new camera with the given aspect ratio, location, and orientation.
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x the first location coordinate
     * @param y the second location coordinate
     * @param z the third location coordinate
     * @param pitch the pitch (rotation on the x-axis)
     * @param yaw the yaw (rotation on the y-axis)
     * @param roll the roll (rotation on the z-axis)
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
     * @param aspectRatio the aspect ratio (width/height) of the camera
     * @param x the first location coordinate
     * @param y the second location coordinate
     * @param z the third location coordinate
     * @param pitch the pitch (rotation on the x-axis)
     * @param yaw the yaw (rotation on the y-axis)
     * @param roll the roll (rotation on the z-axis)
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
     * @param mouseSpeed the speed (sensitity) of the mouse
     * @param maxLookUp the maximum angle at which you can look up 
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
     * @param delta the elapsed time since the last frame update in seconds
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
    
    public void moveAlongAxis(float magnitude, float x, float y, float z) {
    	this.x += x * magnitude;
    	this.y += y * magnitude;
    	this.z += z * magnitude;
    	System.out.println(this.x + ", " + this.y + ", " + this.z);
    }
    
    public void setPosition(float x, float y, float z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }

    public void applyOrthographicMatrix() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-Display.getWidth() / 50f, Display.getWidth() / 50f, -Display.getHeight() / 50f, Display.getHeight() / 50f, 0, 10000);
        glMatrixMode(GL_MODELVIEW);
    }

    public void applyPerspectiveMatrix() {
    	glMatrixMode(GL_PROJECTION);
    	glLoadIdentity();
    	GLU.gluPerspective(fov, aspectRatio, zNear, zFar);
    	glMatrixMode(GL_MODELVIEW);
    }
    
    public void applyModelviewMatrix(boolean resetMatrix) {
    	if (resetMatrix) glLoadIdentity();
    	glRotatef(pitch, 1, 0, 0);
    	glRotatef(yaw, 0, 1, 0);
    	glRotatef(roll, 0, 0, 1);
    	glTranslatef(-x, -y, -z);
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

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getzNear() {
		return zNear;
	}
	
	public void setzNear(float zNear) {
		this.zNear = zNear;
	}

	public float getzFar() {
		return zFar;
	}

	public void setzFar(float zFar) {
		this.zFar = zFar;
	}

	@Override
	public String toString() {
		return "Camera [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch
				+ ", yaw=" + yaw + ", roll=" + roll + ", fov=" + fov
				+ ", aspectRatio=" + aspectRatio + ", zNear=" + zNear
				+ ", zFar=" + zFar + "]";
	}

   
}
