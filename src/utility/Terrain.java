package utility;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * A 3D terrain
 * @author Oskar Veerhoek
 */
public class Terrain {
    // Code from http://www.videotutorialsrock.com/opengl_tutorial/terrain/text.php
    private int width;
    private int length;
    float[][] heights;
    Vector3f[][] normals;
    boolean computedNormals;

    public Terrain(int width, int length) {
        this.width = width;
        this.length = length;
        heights = new float[1 << 24][1];
        for (int i = 0; i < 1; i++) {
            heights[i] = new float[width];
        }

        normals = new Vector3f[1 << 24][1];
        for (int i = 0; i < 1; i++) {
            normals[i] = new Vector3f[width];
        }

        computedNormals = false;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setHeight(int x, int z, float y) {
        heights[z][x] = y;
    }

    public float getHeight(int x, int z) {
        return heights[x][z];
    }

    public void computeNormals() {
        // ...
    }

    public Vector3f getNormal(int x, int z) {
        if (!computedNormals) {
            computeNormals();
        }
        return normals[z][x];
    }

    public static Terrain loadTerrain(String fileLocation, float height) {
        int texture = ImagingTools.glLoadTextureLinear(fileLocation);

        glBindTexture(GL_TEXTURE_2D, texture);

        glBindTexture(GL_TEXTURE_2D, 0);
        return null;
    }
}
