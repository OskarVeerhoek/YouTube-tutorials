package episode_29;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

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
import org.lwjgl.util.vector.Vector3f;

import utility.Camera;
import utility.Face;
import utility.Model;
import utility.OBJLoader;
import utility.ShaderLoader;

public class ShadowMappingDemo {

	private static Camera cam;
	private static Camera light;
	private static int texture;
	private static int shaderProgram;
	private static int vboVertexHandle;
	private static int vboNormalHandle;
	private static int bunnyDisplayList;
	private static int shadowMapLocation;
	private static int fbo;
	private static int depthTexture;

	private static Model model;

	public static final String BUNNY_MODEL_LOCATION = "res/bunny.obj";
	public static final String VERTEX_SHADER_LOCATION = "res/shadow_mapping.vert";
	public static final String FRAGMENT_SHADER_LOCATION = "res/shadow_mapping.frag";

	public static void main(String[] args) {
		setUpDisplay();
		setUpVBOs();
		setUpCamera();
		setUpTextures();
		setUpShaders();
		glEnable(GL_DEPTH_TEST);
		glClearColor(0, 0, 0, 1.0f);
		glEnable(GL_CULL_FACE);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
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

	private static void setUpTextures() {
		int shadowMapWidth = Display.getWidth() * 2; // SHADOW_MAP_RATIO
		int shadowMapHeight = Display.getHeight() * 2; // SHADOW_MAP_RATIO
		int FBOStatus;
		depthTexture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, depthTexture);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, shadowMapWidth,
				shadowMapHeight, 0, GL_DEPTH_COMPONENT, GL_UNSIGNED_BYTE,
				(ByteBuffer) null);
		glBindTexture(GL_TEXTURE_2D, 0);
		fbo = glGenFramebuffersEXT();
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fbo);
		glDrawBuffer(GL_NONE);
		glReadBuffer(GL_NONE);
		glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, GL_DEPTH_ATTACHMENT_EXT,
				GL_TEXTURE_2D, depthTexture, 0);
		FBOStatus = glCheckFramebufferStatusEXT(GL_FRAMEBUFFER_EXT);
		if (FBOStatus != GL_FRAMEBUFFER_COMPLETE_EXT)
			System.out
					.println("GL_FRAMEBUFFER_COMPLETE_EXT failed, CANNOT use FBO.");
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
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
		glDeleteProgram(shaderProgram);
		glDeleteLists(bunnyDisplayList, 1);
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboNormalHandle);
		Display.destroy();
	}

	private static void render() {
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fbo);
		glUseProgram(0);
		// 2 = SHADOW_MAP_RATIO
		glViewport(0, 0, Display.getWidth() * 2, Display.getHeight() * 2);
		glClear(GL_DEPTH_BUFFER_BIT);
		glColorMask(false, false, false, false);
		light.applyPerspectiveMatrix();
		light.applyModelviewMatrix(true);
		glCullFace(GL_FRONT);
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		glColor3f(0.4f, 0.27f, 0.17f);
		glMaterialf(GL_FRONT, GL_SHININESS, 10f);
		glDrawArrays(GL_TRIANGLES, 0, model.faces.size() * 3);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
		FloatBuffer modelView = reserveData(16);
		glGetFloat(GL_MODELVIEW_MATRIX, modelView);
		FloatBuffer projection = reserveData(16);
		glGetFloat(GL_MODELVIEW_MATRIX, projection);
		FloatBuffer bias = asFloatBuffer(0.5f, 0f, 0f, 0f, 0f, 0.5f, 0f, 0f,
				0f, 0f, 0.5f, 0f, 0.5f, 0.5f, 0.5f, 1.0f);
		glMatrixMode(GL_TEXTURE);
		glActiveTexture(GL_TEXTURE7);
		glLoadIdentity();
		glLoadMatrix(bias);
		glMultMatrix(projection);
		glMultMatrix(modelView);
		glMatrixMode(GL_MODELVIEW);
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glColorMask(true, true, true, true);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glUseProgram(shaderProgram);
		glUniform1i(shadowMapLocation, 7);
		glActiveTexture(GL_TEXTURE7);
		glBindTexture(GL_TEXTURE_2D, depthTexture);
		cam.applyPerspectiveMatrix();
		cam.applyModelviewMatrix(true);
		glCullFace(GL_BACK);
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		glColor3f(0.4f, 0.27f, 0.17f);
		glMaterialf(GL_FRONT, GL_SHININESS, 10f);
		glDrawArrays(GL_TRIANGLES, 0, model.faces.size() * 3);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
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
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
	}

	private static void setUpVBOs() {
		vboVertexHandle = glGenBuffers();
		vboNormalHandle = glGenBuffers();
		model = null;
		try {
			model = OBJLoader.loadModel(new File(BUNNY_MODEL_LOCATION));
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
		glVertexPointer(3, GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glNormalPointer(GL_FLOAT, 0, 0L);
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
		shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION,
				FRAGMENT_SHADER_LOCATION);
		shadowMapLocation = glGetUniformLocation(shaderProgram, "ShadowMap");
	}

	private static void setUpCamera() {
		cam = new Camera((float) Display.getWidth()
				/ (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
		cam.setFov(70);
		cam.applyPerspectiveMatrix();
		light = new Camera((float) Display.getWidth()
				/ (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
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
