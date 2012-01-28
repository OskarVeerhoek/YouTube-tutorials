package experimental.coreprofile.glm;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * @author integeruser
 */
public class Glm {

    public static float clamp(float x, float minVal, float maxVal) {
        return Math.min(Math.max(x, minVal), maxVal);
    }

    public static float length(Vec3 a) {
        return (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
    }

    public static Vec3 normalize(Vec3 a) {
        Vec3 vec = new Vec3();

        float magnitude = length(a);
        vec.x = a.x / magnitude;
        vec.y = a.y / magnitude;
        vec.z = a.z / magnitude;

        return vec;
    }

    public static float dot(Vec3 a, Vec3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vec3 cross(Vec3 a, Vec3 b) {
        Vec3 vec = new Vec3();

        vec.x = a.y * b.z - a.z * b.y;
        vec.y = a.z * b.x - a.x * b.z;
        vec.z = a.x * b.y - a.y * b.x;

        return vec;
    }

    public static Mat4 transpose(Mat4 mat) {
        float source[] = mat.matrix;
        float destination[] = new float[16];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                destination[j + i * 4] = source[i + j * 4];
            }
        }

        return new Mat4(destination);
    }

    public static Mat4 perspective(float fovy, float aspect, float zNear, float zFar) {
        float range = (float) (Math.tan(Math.toRadians(fovy / 2.0f)) * zNear);
        float left = -range * aspect;
        float right = range * aspect;
        float bottom = -range;
        float top = range;

        Mat4 mat = new Mat4(0.0f);

        mat.matrix[0] = (2.0f * zNear) / (right - left);
        mat.matrix[5] = (2.0f * zNear) / (top - bottom);
        mat.matrix[10] = -(zFar + zNear) / (zFar - zNear);
        mat.matrix[11] = -1.0f;
        mat.matrix[14] = -(2.0f * zFar * zNear) / (zFar - zNear);

        return mat;
    }
}
