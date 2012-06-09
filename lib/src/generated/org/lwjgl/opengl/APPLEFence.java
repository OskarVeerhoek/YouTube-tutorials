/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEFence {

	/**
	 * Accepted by the &lt;object&gt; parameter of TestObjectAPPLE and FinishObjectAPPLE: 
	 */
	public static final int GL_DRAW_PIXELS_APPLE = 0x8A0A,
		GL_FENCE_APPLE = 0x8A0B;

	private APPLEFence() {}

	public static void glGenFencesAPPLE(IntBuffer fences) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenFencesAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(fences);
		nglGenFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
	}
	static native void nglGenFencesAPPLE(int fences_n, long fences, long function_pointer);

	/** Overloads glGenFencesAPPLE. */
	public static int glGenFencesAPPLE() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenFencesAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer fences = APIUtil.getBufferInt(caps);
		nglGenFencesAPPLE(1, MemoryUtil.getAddress(fences), function_pointer);
		return fences.get(0);
	}

	public static void glDeleteFencesAPPLE(IntBuffer fences) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteFencesAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(fences);
		nglDeleteFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
	}
	static native void nglDeleteFencesAPPLE(int fences_n, long fences, long function_pointer);

	/** Overloads glDeleteFencesAPPLE. */
	public static void glDeleteFencesAPPLE(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeleteFencesAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteFencesAPPLE(1, APIUtil.getInt(caps, fence), function_pointer);
	}

	public static void glSetFenceAPPLE(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glSetFenceAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSetFenceAPPLE(fence, function_pointer);
	}
	static native void nglSetFenceAPPLE(int fence, long function_pointer);

	public static boolean glIsFenceAPPLE(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glIsFenceAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsFenceAPPLE(fence, function_pointer);
		return __result;
	}
	static native boolean nglIsFenceAPPLE(int fence, long function_pointer);

	public static boolean glTestFenceAPPLE(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glTestFenceAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglTestFenceAPPLE(fence, function_pointer);
		return __result;
	}
	static native boolean nglTestFenceAPPLE(int fence, long function_pointer);

	public static void glFinishFenceAPPLE(int fence) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glFinishFenceAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFinishFenceAPPLE(fence, function_pointer);
	}
	static native void nglFinishFenceAPPLE(int fence, long function_pointer);

	public static boolean glTestObjectAPPLE(int object, int name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glTestObjectAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglTestObjectAPPLE(object, name, function_pointer);
		return __result;
	}
	static native boolean nglTestObjectAPPLE(int object, int name, long function_pointer);

	public static void glFinishObjectAPPLE(int object, int name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glFinishObjectAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFinishObjectAPPLE(object, name, function_pointer);
	}
	static native void nglFinishObjectAPPLE(int object, int name, long function_pointer);
}
