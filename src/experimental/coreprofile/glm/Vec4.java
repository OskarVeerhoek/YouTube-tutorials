package experimental.coreprofile.glm;

/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and
 * license terms.
 *
 * @author integeruser
 */
public class Vec4 {

    public float x, y, z, w;

    public Vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4(Vec3 vec3, float w) {
        x = vec3.x;
        y = vec3.y;
        z = vec3.z;
        this.w = w;
    }
}