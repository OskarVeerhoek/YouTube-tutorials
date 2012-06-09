/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEFlushBufferRange {

	/**
	 *  Accepted by the &lt;pname&gt; parameter of BufferParameteriAPPLE and
	 *  GetBufferParameteriv:
	 */
	public static final int GL_BUFFER_SERIALIZED_MODIFY_APPLE = 0x8A12,
		GL_BUFFER_FLUSHING_UNMAP_APPLE = 0x8A13;

	private APPLEFlushBufferRange() {}

	public static void glBufferParameteriAPPLE(int target, int pname, int param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBufferParameteriAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBufferParameteriAPPLE(target, pname, param, function_pointer);
	}
	static native void nglBufferParameteriAPPLE(int target, int pname, int param, long function_pointer);

	public static void glFlushMappedBufferRangeAPPLE(int target, long offset, long size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glFlushMappedBufferRangeAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFlushMappedBufferRangeAPPLE(target, offset, size, function_pointer);
	}
	static native void nglFlushMappedBufferRangeAPPLE(int target, long offset, long size, long function_pointer);
}
