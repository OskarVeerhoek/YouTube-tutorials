package experimental.coreprofile;

import experimental.coreprofile.glm.Glm;
import experimental.coreprofile.glm.Mat4;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Oskar
 */
public class HelloTriangleCustom {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(Display.getAvailableDisplayModes()[0]);
            Display.create(new PixelFormat(), new ContextAttribs(3, 2));
        } catch (LWJGLException ex) {
            Logger.getLogger(HelloTriangleCustom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Mat4 projectionMatrix = new Mat4();
        Mat4 modelMatrix = new Mat4();
        Mat4 viewMatrix = new Mat4();
        
        while (!Display.isCloseRequested()) {
            
        }
        
    }
}
