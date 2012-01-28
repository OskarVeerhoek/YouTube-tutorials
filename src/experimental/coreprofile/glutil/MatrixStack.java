package experimental.coreprofile.glutil;

import experimental.coreprofile.glm.Glm;
import experimental.coreprofile.glm.Mat4;
import experimental.coreprofile.glm.Vec3;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * @author integeruser, xire-
 */
public class MatrixStack {

    private Mat4 currentMatrix;
    private float matrices[];
    private int firstIndexUsable;

    public MatrixStack() {
        matrices = new float[1600];													// 100 matrices		
        currentMatrix = new Mat4(1);

        firstIndexUsable = 0;
    }

    public void push() {
        if (firstIndexUsable == matrices.length) {
            // Double the size of matrices[]
            float temp[] = new float[matrices.length * 2];
            System.arraycopy(matrices, 0, temp, 0, matrices.length);
            matrices = temp;
        }

        // Store the currentMatrix in the buffer
        System.arraycopy(currentMatrix.matrix, 0, matrices, firstIndexUsable, 16);
        firstIndexUsable += 16;
    }

    public void pop() {
        // Pop the last matrix pushed in the buffer and set it as currentMatrix
        firstIndexUsable -= 16;
        System.arraycopy(matrices, firstIndexUsable, currentMatrix.matrix, 0, 16);
    }

    public Mat4 top() {
        return currentMatrix;
    }

    public void clear() {
        currentMatrix.clear(1);

        firstIndexUsable = 0;
    }

    public void clear(Mat4 mat) {
        currentMatrix = new Mat4(mat);

        firstIndexUsable = 0;
    }

    public void rotateX(float fAngDeg) {
        currentMatrix.mul(Mat4.getRotateX(fAngDeg));
    }

    public void rotateY(float fAngDeg) {
        currentMatrix.mul(Mat4.getRotateY(fAngDeg));
    }

    public void rotateZ(float fAngDeg) {
        currentMatrix.mul(Mat4.getRotateZ(fAngDeg));
    }

    public void scale(float x, float y, float z) {
        Mat4 mat = new Mat4();

        mat.clear(0);
        mat.put(0, x);
        mat.put(5, y);
        mat.put(10, z);
        mat.put(15, 1);

        currentMatrix.mul(mat);
    }

    public void scale(Vec3 vec) {
        scale(vec.x, vec.y, vec.z);
    }

    public void translate(float x, float y, float z) {
        Mat4 mat = new Mat4();

        mat.clear(1);
        mat.put(12, x);
        mat.put(13, y);
        mat.put(14, z);

        currentMatrix.mul(mat);
    }

    public void translate(Vec3 vec) {
        translate(vec.x, vec.y, vec.z);
    }

    public void perspective(float fovy, float aspect, float zNear, float zFar) {
        currentMatrix.mul(Glm.perspective(fovy, aspect, zNear, zFar));
    }
}