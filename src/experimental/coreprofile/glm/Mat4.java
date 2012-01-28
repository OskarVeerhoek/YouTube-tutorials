package experimental.coreprofile.glm;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * @author xire-
 */
public class Mat4 {

    private static final float fDegToRad = 3.14159f * 2.0f / 360.0f;
    public float[] matrix;

    public Mat4() {
        matrix = new float[16];
    }

    public Mat4(float diagonal) {
        matrix = new float[16];
        matrix[0] = diagonal;
        matrix[5] = diagonal;
        matrix[10] = diagonal;
        matrix[15] = diagonal;
    }

    public Mat4(float matrix[]) {
        this.matrix = new float[16];
        System.arraycopy(matrix, 0, this.matrix, 0, 16);
    }

    public Mat4(Mat4 mat) {
        matrix = new float[16];
        System.arraycopy(mat.matrix, 0, matrix, 0, 16);
    }
    
    public FloatBuffer asFloatBuffer() {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        buffer.put(matrix);
        buffer.flip();
        return buffer;
    }

    public void mul(Mat4 mat) {
        float[] res = new float[16];
        float[] m1 = matrix;
        float[] m2 = mat.matrix;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                float sum = 0;
                for (int k = 0; k < 4; k++) {
                    float a = m1[r + 4 * k];
                    float b = m2[4 * c + k];
                    sum += a * b;
                }
                res[r + 4 * c] = sum;
            }
        }

        System.arraycopy(res, 0, matrix, 0, 16);
    }

    public void put(int index, float val) {
        matrix[index] = val;
    }

    public void putColumn(int columnIndex, Vec4 vec4) {
        int offset = (columnIndex * 4);

        matrix[offset] = vec4.x;
        matrix[offset + 1] = vec4.y;
        matrix[offset + 2] = vec4.z;
        matrix[offset + 3] = vec4.w;
    }

    public void clear(float diagonal) {
        for (int i = 0; i < 16; i++) {
            matrix[i] = 0.0f;
        }

        matrix[0] = diagonal;
        matrix[5] = diagonal;
        matrix[10] = diagonal;
        matrix[15] = diagonal;
    }

    public void clear(float[] array) {
        System.arraycopy(array, 0, matrix, 0, 16);
    }

    public void clear(Mat4 mat) {
        clear(mat.matrix);
    }

    public FloatBuffer fillBuffer(FloatBuffer buffer) {
        buffer.put(matrix);
        buffer.flip();

        return buffer;
    }

    @Override
    public String toString() {
        String ris = "";

        for (int i = 0; i < 4; i++) {
            ris += matrix[i * 4] + " " + matrix[i * 4 + 1] + " " + matrix[i * 4 + 2] + " " + matrix[i * 4 + 3] + "\n";
        }

        return ris;
    }

    public static Mat4 getRotateX(float angDeg) {
        float fAngRad = degToRad(angDeg);
        float fCos = (float) Math.cos(fAngRad);
        float fSin = (float) Math.sin(fAngRad);

        Mat4 mat = new Mat4();

        // X column
        mat.put(0, 1); 															// x
        mat.put(1, 0); 															// y
        mat.put(2, 0); 															// z

        // Y column
        mat.put(4, 0); 															// x
        mat.put(5, fCos); 														// y
        mat.put(6, fSin); 														// z

        // Z column
        mat.put(8, 0); 															// x
        mat.put(9, -fSin); 														// y
        mat.put(10, fCos); 														// z

        // Last
        mat.put(15, 1);

        return mat;
    }

    public static Mat4 getRotateY(float angDeg) {
        float fAngRad = degToRad(angDeg);
        float fCos = (float) Math.cos(fAngRad);
        float fSin = (float) Math.sin(fAngRad);

        Mat4 mat = new Mat4();

        // X column
        mat.put(0, fCos);
        mat.put(1, 0);
        mat.put(2, -fSin);

        // Y column
        mat.put(4, 0);
        mat.put(5, 1);
        mat.put(6, 0);

        // Z column
        mat.put(8, fSin);
        mat.put(9, 0);
        mat.put(10, fCos);

        // Last
        mat.put(15, 1);

        return mat;
    }

    public static Mat4 getRotateZ(float angDeg) {
        float fAngRad = degToRad(angDeg);
        float fCos = (float) Math.cos(fAngRad);
        float fSin = (float) Math.sin(fAngRad);

        Mat4 mat = new Mat4();

        // X column
        mat.put(0, fCos);
        mat.put(1, fSin);
        mat.put(2, 0);

        // Y column
        mat.put(4, -fSin);
        mat.put(5, fCos);
        mat.put(6, 0);

        // Z column
        mat.put(8, 0);
        mat.put(9, 0);
        mat.put(10, 1);

        // Last
        mat.put(15, 1);

        return mat;
    }

    private static float degToRad(float fAngDeg) {
        return fAngDeg * fDegToRad;
    }
}