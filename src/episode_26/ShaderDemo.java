package episode_26;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

import utility.Camera;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Uses the Phong lighting model to display a tasty chocolate bunny.
 * @author Oskar Veerhoek
 */
public class ShaderDemo {
	private static int shaderProgram, vertexShader, fragmentShader, bunny, diffuseModifierUniform;

	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setVSyncEnabled(true);
			Display.setTitle("Shader Demo");
			Display.create();
		} catch (LWJGLException e) {
			System.err.println("The display wasn't initialized correctly. :(");
			Display.destroy();
			System.exit(1);
		}

		Camera cam = new Camera((float) Display.getWidth()
				/ (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
		cam.setFov(60);
		cam.applyProjectionMatrix();

		setUpShaders();
		setUpLighting();
		setUpDisplayLists();

		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			cam.applyModelviewMatrix(true);
			
			glUseProgram(shaderProgram);
			glCallList(bunny);
			glUseProgram(0);

			cam.processMouse(1, 80, -80);
			cam.processKeyboard(16, 0.003f, 0.003f, 0.003f);

			if (Mouse.isButtonDown(0))
				Mouse.setGrabbed(true);
			else if (Mouse.isButtonDown(1))
				Mouse.setGrabbed(false);

			Display.update();
			Display.sync(60);
		}
		glDeleteProgram(shaderProgram);
		Display.destroy();
		System.exit(0);
	}

	private static void setUpDisplayLists() {
		bunny = glGenLists(1);
		glNewList(bunny, GL_COMPILE);
		{
			Model m = null;
			try {
				m = OBJLoader.loadModel(new File("src/episode_26/bunny.obj"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				Display.destroy();
				System.exit(1);
			}

			glColor3f(0.4f, 0.27f, 0.17f);
			glMaterialf(GL_FRONT, GL_SHININESS, 128.0f);

			glBegin(GL_TRIANGLES);
			for (Face face : m.faces) {
				Vector3f n1 = m.normals.get((int) face.normal.x - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				Vector3f n2 = m.normals.get((int) face.normal.y - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				Vector3f n3 = m.normals.get((int) face.normal.z - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
				glVertex3f(v3.x, v3.y, v3.z);
			}
			glEnd();

			glColor3f(1, 1, 1);
		}
		glEndList();
	}

	private static FloatBuffer asFloatBuffer(float... values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}

	private static void setUpLighting() {
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[] {0.05f, 0.05f, 0.05f, 1f}));
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[] { 0,0,0, 1 }));
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
	}

	private static void setUpShaders() {
		shaderProgram = glCreateProgram();
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/episode_26/specular.vert"));
			String line;
			while ((line = reader.readLine()) != null) {
				vertexShaderSource.append(line).append('\n');
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Vertex shader wasn't loaded properly.");
			Display.destroy();
			System.exit(1);
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/episode_26/specular.frag"));
			String line;
			while ((line = reader.readLine()) != null) {
				fragmentShaderSource.append(line).append('\n');
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Fragment shader wasn't loaded properly.");
			Display.destroy();
			System.exit(1);
		}
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if (glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err
					.println("Vertex shader wasn't able to be compiled correctly.");
		}
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err
					.println("Fragment shader wasn't able to be compiled correctly.");
		}
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
		diffuseModifierUniform = glGetUniformLocation(shaderProgram, "diffuseIntensityModifier");
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
	}
}
