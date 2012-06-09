/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class APPLEObjectPurgeable {

	/**
	 *  Accepted by the &lt;option&gt; parameter of ObjectPurgeable, and returned
	 *  by ObjectPurgeable:
	 */
	public static final int GL_RELEASED_APPLE = 0x8A19,
		GL_VOLATILE_APPLE = 0x8A1A;

	/**
	 *  Accepted by the &lt;option&gt; parameters of ObjectUnpurgeable, and
	 *  returned by ObjectUnpurgeable:
	 */
	public static final int GL_RETAINED_APPLE = 0x8A1B,
		GL_UNDEFINED_APPLE = 0x8A1C;

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetObjectParameteriv: 
	 */
	public static final int GL_PURGEABLE_APPLE = 0x8A1D;

	/**
	 *  Accepted by the &lt;objectType&gt; parameters of ObjectPurgeableAPPLE,
	 *  ObjectUnpurgeableAPPLE and GetObjectParameteriv:
	 */
	public static final int GL_BUFFER_OBJECT_APPLE = 0x85B3;

	private APPLEObjectPurgeable() {}

	public static int glObjectPurgeableAPPLE(int objectType, int name, int option) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glObjectPurgeableAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglObjectPurgeableAPPLE(objectType, name, option, function_pointer);
		return __result;
	}
	static native int nglObjectPurgeableAPPLE(int objectType, int name, int option, long function_pointer);

	public static int glObjectUnpurgeableAPPLE(int objectType, int name, int option) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glObjectUnpurgeableAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglObjectUnpurgeableAPPLE(objectType, name, option, function_pointer);
		return __result;
	}
	static native int nglObjectUnpurgeableAPPLE(int objectType, int name, int option, long function_pointer);

	public static void glGetObjectParameterAPPLE(int objectType, int name, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetObjectParameterivAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 1);
		nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
	}
	static native void nglGetObjectParameterivAPPLE(int objectType, int name, int pname, long params, long function_pointer);

	/** Overloads glGetObjectParameterivAPPLE. */
	public static int glGetObjectParameterAPPLE(int objectType, int name, int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetObjectParameterivAPPLE;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer params = APIUtil.getBufferInt(caps);
		nglGetObjectParameterivAPPLE(objectType, name, pname, MemoryUtil.getAddress(params), function_pointer);
		return params.get(0);
	}
}
