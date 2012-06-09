/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEVertexProgramEvaluators {

	/**
	 *  Accepted by the &lt;pname&gt; parameter of EnableVertexAttribAPPLE,
	 *  DisableVertexAttribAPPLE, and IsVertexAttribEnabledAPPLE.
	 */
	public static final int GL_VERTEX_ATTRIB_MAP1_APPLE = 0x8A00,
		GL_VERTEX_ATTRIB_MAP2_APPLE = 0x8A01;

	/**
	 *  Accepted by the &lt;pname&gt; parameter of GetVertexAttribdvARB,
	 *  GetVertexAttribfvARB, and GetVertexAttribivARB.
	 */
	public static final int GL_VERTEX_ATTRIB_MAP1_SIZE_APPLE = 0x8A02,
		GL_VERTEX_ATTRIB_MAP1_COEFF_APPLE = 0x8A03,
		GL_VERTEX_ATTRIB_MAP1_ORDER_APPLE = 0x8A04,
		GL_VERTEX_ATTRIB_MAP1_DOMAIN_APPLE = 0x8A05,
		GL_VERTEX_ATTRIB_MAP2_SIZE_APPLE = 0x8A06,
		GL_VERTEX_ATTRIB_MAP2_COEFF_APPLE = 0x8A07,
		GL_VERTEX_ATTRIB_MAP2_ORDER_APPLE = 0x8A08,
		GL_VERTEX_ATTRIB_MAP2_DOMAIN_APPLE = 0x8A09;

	private APPLEVertexProgramEvaluators() {}

	public static void glEnableVertexAttribAPPLE(int index, int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glEnableVertexAttribAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVertexAttribAPPLE(index, pname, function_pointer);
	}
	static native void nglEnableVertexAttribAPPLE(int index, int pname, long function_pointer);

	public static void glDisableVertexAttribAPPLE(int index, int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDisableVertexAttribAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVertexAttribAPPLE(index, pname, function_pointer);
	}
	static native void nglDisableVertexAttribAPPLE(int index, int pname, long function_pointer);

	public static boolean glIsVertexAttribEnabledAPPLE(int index, int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glIsVertexAttribEnabledAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsVertexAttribEnabledAPPLE(index, pname, function_pointer);
		return __result;
	}
	static native boolean nglIsVertexAttribEnabledAPPLE(int index, int pname, long function_pointer);

	public static void glMapVertexAttrib1dAPPLE(int index, int size, double u1, double u2, int stride, int order, DoubleBuffer points) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glMapVertexAttrib1dAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(points);
		nglMapVertexAttrib1dAPPLE(index, size, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
	}
	static native void nglMapVertexAttrib1dAPPLE(int index, int size, double u1, double u2, int stride, int order, long points, long function_pointer);

	public static void glMapVertexAttrib1fAPPLE(int index, int size, float u1, float u2, int stride, int order, FloatBuffer points) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glMapVertexAttrib1fAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(points);
		nglMapVertexAttrib1fAPPLE(index, size, u1, u2, stride, order, MemoryUtil.getAddress(points), function_pointer);
	}
	static native void nglMapVertexAttrib1fAPPLE(int index, int size, float u1, float u2, int stride, int order, long points, long function_pointer);

	public static void glMapVertexAttrib2dAPPLE(int index, int size, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glMapVertexAttrib2dAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(points);
		nglMapVertexAttrib2dAPPLE(index, size, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
	}
	static native void nglMapVertexAttrib2dAPPLE(int index, int size, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, long points, long function_pointer);

	public static void glMapVertexAttrib2fAPPLE(int index, int size, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glMapVertexAttrib2fAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(points);
		nglMapVertexAttrib2fAPPLE(index, size, u1, u2, ustride, uorder, v1, v2, vstride, vorder, MemoryUtil.getAddress(points), function_pointer);
	}
	static native void nglMapVertexAttrib2fAPPLE(int index, int size, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, long points, long function_pointer);
}
