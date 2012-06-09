package episode_29;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
//import static org.lwjgl.opengl.EXTFramebufferObject.*;
// if you're version of OpenGL is 3.0 or higher, try
import static org.lwjgl.opengl.GL30.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import utility.Camera;
import utility.Face;
import utility.Model;
import utility.OBJLoader;
import utility.ShaderLoader;

/**
 * This program shows the use of shadow mapping.
 * 
 * @author Oskar Veerhoek
 */
public class ShadowingDemo {

	private static Camera cam;
	private static Vector3f light = new Vector3f(-2.19f, 1.36f, 11.45f);
	private static Model model;
	
	private static int fbo;
	private static int depthTexture;
	private static int texture;
	private static int depthProgram;
	private static int depthMatrix;
	private static int depth_VertexPosition_modelspace;
	private static int vboVertexHandle;
	private static int vboNormalHandle;
	private static int bunnyDisplayList;
	private static int diffuseLocation;
	private static int depthMVPLocation;

	public static final String MODEL_LOCATION = "res/bunny.obj";
	public static final String VERTEX_SHADER_SHADOW_MAP_LOCATION = "res/shadow_mapping.vert";
	public static final String FRAGMENT_SHADER_SHADOW_MAP_LOCATION = "res/shadow_mapping.frag";

	public static void main(String[] args) {
		setUpDisplay();
		setUpVBOs();
		setUpCamera();
		setUpShaders();
		setUpShadowMap();
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

	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		cam.applyModelviewMatrix(true);
		glLight(GL_LIGHT0, GL_POSITION,
				asFloatBuffer(light.getX(), light.getY(), light.getZ(), 1));
		drawObjects();
	}

	private static void drawObjects() {
		glUseProgram(depthProgram);
		glUniform1f(diffuseLocation, 1.0f);
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(3, GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
		glNormalPointer(GL_FLOAT, 0, 0L);
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		glColor3f(0.4f, 0.27f, 0.17f);
		glMaterialf(GL_FRONT, GL_SHININESS, 10f);
		glDrawArrays(GL_TRIANGLES, 0, model.faces.size() * 3);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glUseProgram(0);
	}

	private static void setUpShadowMap() {
		fbo = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, fbo);
		depthTexture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, depthTexture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT16, 1024, 1024, 0,
				GL_DEPTH_COMPONENT, GL_FLOAT, (ByteBuffer) null);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT,
				depthTexture, 0, 1); // TODO: Verify!
		glDrawBuffers(GL_DEPTH_ATTACHMENT);
		if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
			cleanUp();
		FloatBuffer depthProjectionMatrixBuffer = reserveData(16);
		FloatBuffer depthModelViewMatrixBuffer = reserveData(16);
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glOrtho(-10, 10, -10, 10, -10, 20);
		glGetFloat(GL_PROJECTION_MATRIX, depthProjectionMatrixBuffer);
		glPopMatrix();
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glTranslatef(-light.getX(), -light.getY(), -light.getZ());
		glGetFloat(GL_MODELVIEW_MATRIX, depthModelViewMatrixBuffer);
		glPopMatrix();
		Matrix4f depthProjectionMatrix = new Matrix4f(), depthMVP = new Matrix4f(), depthModelViewMatrix = new Matrix4f();
		depthModelViewMatrix.load(depthModelViewMatrixBuffer);
		depthProjectionMatrix.load(depthProjectionMatrixBuffer);
		Matrix4f.mul(depthProjectionMatrix, depthModelViewMatrix, depthMVP);
		FloatBuffer depthMVPBuffer = reserveData(16);
		depthMVP.store(depthMVPBuffer);
		glUseProgram(depthProgram);
		glUniformMatrix4(depthMVPLocation, false, depthMVPBuffer);
		glUseProgram(0);
	}

	private static void setUpLighting() {
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[] { 0.05f,
				0.05f, 0.05f, 1f }));
		glLight(GL_LIGHT0, GL_POSITION,
				asFloatBuffer(new float[] { 0, 0, 0, 1 }));
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_COLOR_MATERIAL);
		glDepthFunc(GL_LESS);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
	}

	private static void setUpVBOs() {
		vboVertexHandle = glGenBuffers();
		vboNormalHandle = glGenBuffers();
		model = null;
		try {
			model = OBJLoader.loadModel(new File(MODEL_LOCATION));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			cleanUp();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			cleanUp();
			System.exit(1);
		}
		FloatBuffer vertices = reserveData(model.faces.size() * 9);
		FloatBuffer normals = reserveData(model.faces.size() * 9);
		for (Face face : model.faces) {
			vertices.put(asFloats(model.vertices.get((int) face.vertex.x - 1)));
			vertices.put(asFloats(model.vertices.get((int) face.vertex.y - 1)));
			vertices.put(asFloats(model.vertices.get((int) face.vertex.z - 1)));
			normals.put(asFloats(model.normals.get((int) face.normal.x - 1)));
			normals.put(asFloats(model.normals.get((int) face.normal.y - 1)));
			normals.put(asFloats(model.normals.get((int) face.normal.z - 1)));
		}
		vertices.flip();
		normals.flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	private static float[] asFloats(Vector3f v) {
		return new float[] { v.x, v.y, v.z };
	}

	private static FloatBuffer reserveData(int size) {
		FloatBuffer data = BufferUtils.createFloatBuffer(size);
		return data;
	}

	private static void setUpShaders() {
		depthProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_SHADOW_MAP_LOCATION,
				FRAGMENT_SHADER_SHADOW_MAP_LOCATION);
		depthMVPLocation = glGetUniformLocation(depthProgram, "depthMVP");
		depth_VertexPosition_modelspace = glGetAttribLocation(depthProgram, "vertexPosition_modelview");
//		diffuseLocation = glGetUniformLocation(shaderProgram,
//				"diffuseIntensityModifier");
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

	private static void checkInput() {
		cam.processMouse(1, 80, -80);
		cam.processKeyboard(16, 0.003f, 0.003f, 0.003f);
		if (Mouse.isButtonDown(0))
			Mouse.setGrabbed(true);
		else if (Mouse.isButtonDown(1))
			Mouse.setGrabbed(false);
	}

	private static void cleanUp() {
		glDeleteTextures(texture);
		glDeleteProgram(depthProgram);
		glDeleteLists(bunnyDisplayList, 1);
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboNormalHandle);
		glDeleteFramebuffers(fbo);
		Display.destroy();
		System.exit(0);
	}

	private static FloatBuffer asFloatBuffer(float... values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
}

// Vertex shader:
/**
#version 330

//layout(location = 0) in vec3 vertexPosition_modelspace;

uniform mat4 depthMVP;

void main() {
	gl_Position = depthMVP * gl_Vertex;
}
**/
// Fragment shader:
/**
#version 330
 
// Ouput data
layout(location = 0) out float fragmentdepth;
 
void main(){
	fragmentdepth = gl_FragCoord.z;
}
**/
