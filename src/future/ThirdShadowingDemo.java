package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import utility.OBJLoader;
import utility.ShaderLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static utility.BufferTools.asFloatBuffer;
import static utility.BufferTools.reserveData;

public class ThirdShadowingDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.setTitle("Shadow Mapping - Finally!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        glEnable(GL_CULL_FACE);
        int depthProgramID = ShaderLoader.loadShaderPair(
                "res/shaders/DepthRTT.vertexshader", "res/shaders/DepthRTT.fragmentshader");
        int depthMatrixID = glGetUniformLocation(depthProgramID, "depthMVP");
        int depth_vertexPosition_modelspaceID = glGetAttribLocation(
                depthProgramID, "vertexPosition_modelspace");
        // int textureID = glGetUniformLocation(depthProgramID,
        // "myTextureSampler");
        int modelDisplayList = 0;
        try {
            modelDisplayList = OBJLoader.createDisplayList(OBJLoader
                    .loadModel(new File("res/models/bunny.obj")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            glDeleteLists(modelDisplayList, 1);
            Display.destroy();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            glDeleteLists(modelDisplayList, 1);
            Display.destroy();
            System.exit(1);
        }
        int FramebufferName = glGenFramebuffersEXT();
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, FramebufferName);
        int depthTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, depthTexture);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT16, 1024, 1024, 0,
                GL_DEPTH_COMPONENT, GL_FLOAT, 0);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FUNC, GL_LEQUAL);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE,
                GL_COMPARE_R_TO_TEXTURE);
        glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, GL_DEPTH_ATTACHMENT_EXT,
                depthTexture, 0, 0);
        glDrawBuffer(GL_NONE);
        if (glCheckFramebufferStatusEXT(GL_FRAMEBUFFER_EXT) != GL_FRAMEBUFFER_COMPLETE_EXT) {
            System.err.println("Could not initialize the framebuffer.");
            glDeleteLists(modelDisplayList, 1);
            Display.destroy();
            System.exit(1);
        }
        int quad_vertexbuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, quad_vertexbuffer);
        glBufferData(
                GL_ARRAY_BUFFER,
                asFloatBuffer(-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f,
                        1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f,
                        1.0f, 0.0f), GL_STATIC_DRAW);
        int quad_programID = ShaderLoader.loadShaderPair("res/shaders/Passthrough.vertexshader", "res/shaders/Passthrough.fragmentshader");
        int texID = glGetUniformLocation(quad_programID, "renderedTexture");
        int timeID = glGetUniformLocation(quad_programID, "time");
        int programID = ShaderLoader.loadShaderPair("res/shaders/StandardShading.vertexshader", "ShadowMapping.fragmentshader");
        int matrixID = glGetUniformLocation(programID, "MVP");
        int viewMatrixID = glGetUniformLocation(programID, "V");
        int modelMatrixID = glGetUniformLocation(programID, "M");
        int depthBiasID = glGetUniformLocation(programID, "DepthBiasMVP");
        int shadowMapID = glGetUniformLocation(programID, "shadowMap");
        int lightInvDirID = glGetUniformLocation(programID, "LightInvDirection_worldspace");
//		int[] vbos = OBJLoader.createVBO(OBJLoader.loadModel(new File("res/models/bunny.obj")));
//		int vertexbuffer = vbos[0];

        while (!Display.isCloseRequested()) {
            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, FramebufferName);
            glViewport(0, 0, 1024, 1024);
            glEnable(GL_CULL_FACE);
            glCullFace(GL_FRONT);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glUseProgram(depthProgramID);
            Vector3f lightInvDir = new Vector3f(0.5f, 2, 2);
            Matrix4f depthProjectionMatrix = new Matrix4f();
            depthProjectionMatrix.store(asFloatBuffer(0.1071f, 0, 0, 0, 0, 0.1428f, 0, 0, 0, 0, 0.0671f, 0.0667f, 0, 0, -0.2665f, 0.3333f));
            Matrix4f depthMVP = new Matrix4f(depthProjectionMatrix);
            FloatBuffer depthMVPBuffer = reserveData(16);
            depthMVP.store(depthMVPBuffer);
            glUniformMatrix4(depthMatrixID, false, depthMVPBuffer);
            glEnableVertexAttribArray(0);
//			glBindBuffer(GL_ARRAY_BUFFER, vertex)
            Display.update();
            Display.sync(60);
        }
        glDeleteLists(modelDisplayList, 1);
        Display.destroy();
        System.exit(0);
    }

}
