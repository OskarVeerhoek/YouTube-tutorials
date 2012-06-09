/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDSamplePositions {

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetFloatv: 
	 */
	public static final int GL_SUBSAMPLE_DISTANCE_AMD = 0x883F;

	private AMDSamplePositions() {}

	public static void glSetMultisampleAMD(int pname, int index, FloatBuffer val) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glSetMultisamplefvAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(val, 2);
		nglSetMultisamplefvAMD(pname, index, MemoryUtil.getAddress(val), function_pointer);
	}
	static native void nglSetMultisamplefvAMD(int pname, int index, long val, long function_pointer);
}
