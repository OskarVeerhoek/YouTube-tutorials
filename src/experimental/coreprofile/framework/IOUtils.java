package rosick.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;


/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and license terms.
 * 
 * @author integeruser
 */
public class IOUtils {

	public static ShortBuffer allocShorts(short[] shorts) {
		ShortBuffer sb = BufferUtils.createShortBuffer(shorts.length);
		sb.put(shorts);
		sb.flip();
        
    	return sb;
    }

	public static FloatBuffer allocFloats(float[] floats) {
		FloatBuffer fb = BufferUtils.createFloatBuffer(floats.length);
		fb.put(floats);
		fb.flip();
        
    	return fb;
    }
  	
    
	public static String loadFileAsString(String filepath) {
        StringBuilder text = new StringBuilder();
        
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream(filepath)));
        	String line;
        	
        	while ((line = reader.readLine()) != null) {
        		text.append(line).append("\n");
        	}
        	
        	reader.close();
        } catch (Exception e){
        	System.err.println("Fail reading " + filepath + ": " + e.getMessage());
        }
        
        return text.toString();
	}
}
