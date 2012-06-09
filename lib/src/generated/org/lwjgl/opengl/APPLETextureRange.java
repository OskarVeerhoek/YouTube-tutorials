/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLETextureRange {

	/**
	 *  Accepted by the <pname> parameters of TexParameteri, TexParameterf,
	 *  TexParameteriv, TexParameterfv, GetTexParameteriv, and
	 *  GetTexParameterfv:
	 */
	public static final int GL_TEXTURE_STORAGE_HINT_APPLE = 0x85BC;

	/**
	 *  Accepted by the <param> parameters of TexParameteri, TexParameterf,
	 *  TexParameteriv, and TexParameterfv:
	 */
	public static final int GL_STORAGE_PRIVATE_APPLE = 0x85BD,
		GL_STORAGE_CACHED_APPLE = 0x85BE,
		GL_STORAGE_SHARED_APPLE = 0x85BF;

	/**
	 *  Accepted by the <pname> parameters of GetTexParameteriv and
	 *  GetTexParameterfv:
	 */
	public static final int GL_TEXTURE_RANGE_LENGTH_APPLE = 0x85B7;

	/**
	 * Accepted by the <pname> parameters of GetTexParameterPointerv: 
	 */
	public static final int GL_TEXTURE_RANGE_POINTER_APPLE = 0x85B8;

	private APPLETextureRange() {}

	public static void glTextureRangeAPPLE(int target, ByteBuffer pointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glTextureRangeAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pointer);
		nglTextureRangeAPPLE(target, pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
	}
	static native void nglTextureRangeAPPLE(int target, int pointer_length, long pointer, long function_pointer);

	public static Buffer glGetTexParameterPointervAPPLE(int target, int pname, long result_size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetTexParameterPointervAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		Buffer __result = nglGetTexParameterPointervAPPLE(target, pname, result_size, function_pointer);
		return __result;
	}
	static native Buffer nglGetTexParameterPointervAPPLE(int target, int pname, long result_size, long function_pointer);
}
