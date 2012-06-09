/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEVertexArrayObject {

	/**
	 *  Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 *  and GetDoublev:
	 */
	public static final int GL_VERTEX_ARRAY_BINDING_APPLE = 0x85B5;

	private APPLEVertexArrayObject() {}

	public static void glBindVertexArrayAPPLE(int array) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBindVertexArrayAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindVertexArrayAPPLE(array, function_pointer);
	}
	static native void nglBindVertexArrayAPPLE(int array, long function_pointer);

	public static void glDeleteVertexArraysAPPLE(IntBuffer arrays) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteVertexArraysAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(arrays);
		nglDeleteVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
	}
	static native void nglDeleteVertexArraysAPPLE(int arrays_n, long arrays, long function_pointer);

	/** Overloads glDeleteVertexArraysAPPLE. */
	public static void glDeleteVertexArraysAPPLE(int array) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteVertexArraysAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteVertexArraysAPPLE(1, APIUtil.getInt(caps, array), function_pointer);
	}

	public static void glGenVertexArraysAPPLE(IntBuffer arrays) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenVertexArraysAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(arrays);
		nglGenVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
	}
	static native void nglGenVertexArraysAPPLE(int arrays_n, long arrays, long function_pointer);

	/** Overloads glGenVertexArraysAPPLE. */
	public static int glGenVertexArraysAPPLE() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenVertexArraysAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer arrays = APIUtil.getBufferInt(caps);
		nglGenVertexArraysAPPLE(1, MemoryUtil.getAddress(arrays), function_pointer);
		return arrays.get(0);
	}

	public static boolean glIsVertexArrayAPPLE(int array) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glIsVertexArrayAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsVertexArrayAPPLE(array, function_pointer);
		return __result;
	}
	static native boolean nglIsVertexArrayAPPLE(int array, long function_pointer);
}
