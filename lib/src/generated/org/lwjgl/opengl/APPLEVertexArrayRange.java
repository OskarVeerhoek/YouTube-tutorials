/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEVertexArrayRange {

	/**
	 *  Accepted by the &lt;cap&gt; parameter of EnableClientState, DisableClientState,
	 *  and IsEnabled:
	 */
	public static final int GL_VERTEX_ARRAY_RANGE_APPLE = 0x851D;

	/**
	 *  Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 *  and GetDoublev:
	 */
	public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_APPLE = 0x851E,
		GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_APPLE = 0x8520;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetPointerv: 
	 */
	public static final int GL_VERTEX_ARRAY_RANGE_POINTER_APPLE = 0x8521;

	/**
	 *  Accepted by the &lt;pname&gt; parameter of VertexArrayParameteriAPPLE,
	 *  GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	 */
	public static final int GL_VERTEX_ARRAY_STORAGE_HINT_APPLE = 0x851F;

	/**
	 * Accepted by the &lt;param&gt; parameter of VertexArrayParameteriAPPLE: 
	 */
	public static final int GL_STORAGE_CACHED_APPLE = 0x85BE,
		GL_STORAGE_SHARED_APPLE = 0x85BF;

	/**
	 * Accepted by the &lt;object&gt; parameter of TestObjectAPPLE and FinishObjectAPPLE: 
	 */
	public static final int GL_DRAW_PIXELS_APPLE = 0x8A0A,
		GL_FENCE_APPLE = 0x8A0B;

	private APPLEVertexArrayRange() {}

	public static void glVertexArrayRangeAPPLE(ByteBuffer pointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glVertexArrayRangeAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pointer);
		nglVertexArrayRangeAPPLE(pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
	}
	static native void nglVertexArrayRangeAPPLE(int pointer_length, long pointer, long function_pointer);

	public static void glFlushVertexArrayRangeAPPLE(ByteBuffer pointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glFlushVertexArrayRangeAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pointer);
		nglFlushVertexArrayRangeAPPLE(pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
	}
	static native void nglFlushVertexArrayRangeAPPLE(int pointer_length, long pointer, long function_pointer);

	public static void glVertexArrayParameteriAPPLE(int pname, int param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glVertexArrayParameteriAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexArrayParameteriAPPLE(pname, param, function_pointer);
	}
	static native void nglVertexArrayParameteriAPPLE(int pname, int param, long function_pointer);
}
