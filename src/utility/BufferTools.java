package utility;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class BufferTools {
	public static FloatBuffer asFloatBuffer(float... values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		return buffer;
	}
	public static FloatBuffer reserveData(int amountOfIndices) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(amountOfIndices);
		return buffer;
	}
	public static FloatBuffer asFlippedFloatBuffer(float... values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
}
