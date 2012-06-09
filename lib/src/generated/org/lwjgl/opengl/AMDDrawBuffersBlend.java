/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDDrawBuffersBlend {

	private AMDDrawBuffersBlend() {}

	public static void glBlendFuncIndexedAMD(int buf, int src, int dst) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendFuncIndexedAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendFuncIndexedAMD(buf, src, dst, function_pointer);
	}
	static native void nglBlendFuncIndexedAMD(int buf, int src, int dst, long function_pointer);

	public static void glBlendFuncSeparateIndexedAMD(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendFuncSeparateIndexedAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendFuncSeparateIndexedAMD(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
	}
	static native void nglBlendFuncSeparateIndexedAMD(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha, long function_pointer);

	public static void glBlendEquationIndexedAMD(int buf, int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendEquationIndexedAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationIndexedAMD(buf, mode, function_pointer);
	}
	static native void nglBlendEquationIndexedAMD(int buf, int mode, long function_pointer);

	public static void glBlendEquationSeparateIndexedAMD(int buf, int modeRGB, int modeAlpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendEquationSeparateIndexedAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationSeparateIndexedAMD(buf, modeRGB, modeAlpha, function_pointer);
	}
	static native void nglBlendEquationSeparateIndexedAMD(int buf, int modeRGB, int modeAlpha, long function_pointer);
}
