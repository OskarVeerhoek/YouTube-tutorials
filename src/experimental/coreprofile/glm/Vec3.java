package experimental.coreprofile.glm;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * @author integeruser
 */
public class Vec3 {

    public float x, y, z;

    public Vec3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec3 vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;
    }

    public Vec3 add(Vec3 vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;

        return this;
    }

    public Vec3 sub(Vec3 vec) {
        x -= vec.x;
        y -= vec.y;
        z -= vec.z;

        return this;
    }

    public Vec3 mul(Vec3 vec) {
        x *= vec.x;
        y *= vec.y;
        z *= vec.z;

        return this;
    }

    public Vec3 scale(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;

        return this;
    }

    public Vec3 negate() {
        x = -x;
        y = -y;
        z = -z;

        return this;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vec3 vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;
    }

    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y + ", Z: " + z;
    }

    public static Vec3 add(Vec3 a, Vec3 b) {
        Vec3 vec = new Vec3(a);

        return vec.add(b);
    }

    public static Vec3 sub(Vec3 a, Vec3 b) {
        Vec3 vec = new Vec3(a);

        return vec.sub(b);
    }

    public static Vec3 mul(Vec3 a, Vec3 b) {
        Vec3 vec = new Vec3(a);

        return vec.mul(b);
    }

    public static Vec3 scale(Vec3 a, float scalar) {
        Vec3 vec = new Vec3(a);

        return vec.scale(scalar);
    }

    public static Vec3 negate(Vec3 a) {
        Vec3 vec = new Vec3(a);

        return vec.negate();
    }
}