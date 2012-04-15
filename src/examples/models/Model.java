package examples.models;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Oskar
 */
public class Model {

    public FloatBuffer vertices;
    public float[] verticesArray;
    public int verticesSize;
    public FloatBuffer normals;
    public float[] normalsArray;
    public int[] vertexIndicesArray, normalIndicesArray;
    public IntBuffer vertexIndices;
    public IntBuffer normalIndices;
    public int normalsSize;
    public int vertexIndicesSize;
    public int normalIndicesSize;
    
    public int createIndexVBO() {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, vertexIndicesSize + normalIndicesSize, GL_STATIC_DRAW);
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0L, vertexIndices);
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, vertexIndicesSize, normalIndices);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        return vbo;
    }

    public int createDisplayList() {
        int dl = glGenLists(1);
        glNewList(dl, GL_COMPILE);
        glBegin(GL_TRIANGLES);
        for (int i = 0; i < vertexIndicesArray.length; i += 3) {
            glNormal3f(normalsArray[i], normalsArray[i + 1], normalsArray[i + 2]);
            glVertex3f(verticesArray[i], verticesArray[i + 1], verticesArray[i + 2]);
        }
        glEnd();
        glEndList();
        return dl;
    }

    public int createDataVBO() {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, verticesSize, GL_STATIC_DRAW);
        glBufferSubData(GL_ARRAY_BUFFER, 0L, vertices);
        glBufferSubData(GL_ARRAY_BUFFER, verticesSize, normals);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return vbo;
    }

    public Model(int amountofvertices, int amountofnormals, int amountofvertexindices, int amountofnormalindices) {
        verticesSize = amountofvertices * 3;
        vertices = BufferUtils.createFloatBuffer(amountofvertices * 3);
        verticesArray = new float[amountofvertices * 3];
        normalsSize = amountofnormals * 3;
        normals = BufferUtils.createFloatBuffer(amountofnormals * 3);
        normalsArray = new float[amountofnormals * 3];
        vertexIndicesSize = amountofvertexindices;
        vertexIndices = BufferUtils.createIntBuffer(amountofvertexindices);
        vertexIndicesArray = new int[amountofvertexindices];
        normalIndicesSize = amountofnormalindices;
        normalIndices = BufferUtils.createIntBuffer(amountofnormalindices);
        normalIndicesArray = new int[amountofnormalindices];
    }
}
