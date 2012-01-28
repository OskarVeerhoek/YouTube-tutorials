package experimental.coreprofile;

import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Oskar
 */
public class MatrixUtil {
    public static Matrix4f ortho2D(float left, float right, float bottom, float top) {
        final float zNear = -1f;
        final float zFar = 1f;
        
        float tx = -(right + left) / (right - left);
        float ty = -(top + bottom) / (top - bottom);
        float tz = -(zFar + zNear) / (zFar - zNear);
        
        Matrix4f m = new Matrix4f();
        
        m.m00 = (2f / (right - bottom));
        m.m01 = 0;
        m.m02 = 0;
        m.m03 = 0;
        
        m.m10 = 0;
        m.m11 = (2f / (top - bottom));
        m.m12 = 0;
        m.m13 = 0;
        
        m.m20 = 0;
        m.m21 = 0;
        m.m22 = 0;
        m.m23 = (-2f / (zFar - zNear));
        
        m.m30 = tx;
        m.m31 = ty;
        m.m32 = tz;
        m.m33 = 1f;
        
        return m;
    }
}
