/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDNameGenDelete {

	/**
	 * Accepted as the &lt;identifier&gt; parameter of GenNamesAMD and DeleteNamesAMD: 
	 */
	public static final int GL_DATA_BUFFER_AMD = 0x9151,
		GL_PERFORMANCE_MONITOR_AMD = 0x9152,
		GL_QUERY_OBJECT_AMD = 0x9153,
		GL_VERTEX_ARRAY_OBJECT_AMD = 0x9154,
		GL_SAMPLER_OBJECT_AMD = 0x9155;

	private AMDNameGenDelete() {}

	public static void glGenNamesAMD(int identifier, IntBuffer names) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenNamesAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(names);
		nglGenNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
	}
	static native void nglGenNamesAMD(int identifier, int names_num, long names, long function_pointer);

	/** Overloads glGenNamesAMD. */
	public static int glGenNamesAMD(int identifier) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenNamesAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer names = APIUtil.getBufferInt(caps);
		nglGenNamesAMD(identifier, 1, MemoryUtil.getAddress(names), function_pointer);
		return names.get(0);
	}

	public static void glDeleteNamesAMD(int identifier, IntBuffer names) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteNamesAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(names);
		nglDeleteNamesAMD(identifier, names.remaining(), MemoryUtil.getAddress(names), function_pointer);
	}
	static native void nglDeleteNamesAMD(int identifier, int names_num, long names, long function_pointer);

	/** Overloads glDeleteNamesAMD. */
	public static void glDeleteNamesAMD(int identifier, int name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteNamesAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteNamesAMD(identifier, 1, APIUtil.getInt(caps, name), function_pointer);
	}

	public static boolean glIsNameAMD(int identifier, int name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glIsNameAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsNameAMD(identifier, name, function_pointer);
		return __result;
	}
	static native boolean nglIsNameAMD(int identifier, int name, long function_pointer);
}
