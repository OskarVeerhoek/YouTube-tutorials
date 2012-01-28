package experimental.coreprofile;

import java.io.BufferedReader;
import java.io.FileReader;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Oskar
 */
public class ShaderUtil {

    public static int createVertexShader(String file) {
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        String vertexCode = "";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                vertexCode += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        glShaderSource(vertexShader, vertexCode);
        glCompileShader(vertexShader);
        return vertexShader;
    }
    public static int createFragmentShader(String file) {
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        String vertexCode = "";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                vertexCode += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        glShaderSource(fragmentShader, vertexCode);
        glCompileShader(fragmentShader);
        return fragmentShader;
    }
}
