/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDVertexShaderTessellator {

	/**
	 * Returned by the &lt;type&gt; parameter of GetActiveUniform: 
	 */
	public static final int GL_SAMPLER_BUFFER_AMD = 0x9001,
		GL_INT_SAMPLER_BUFFER_AMD = 0x9002,
		GL_UNSIGNED_INT_SAMPLER_BUFFER_AMD = 0x9003;

	/**
	 * Accepted by TessellationModeAMD 
	 */
	public static final int GL_DISCRETE_AMD = 0x9006,
		GL_CONTINUOUS_AMD = 0x9007;

	/**
	 * Accepted by GetIntegerv 
	 */
	public static final int GL_TESSELLATION_MODE_AMD = 0x9004;

	/**
	 * Accepted by GetFloatv 
	 */
	public static final int GL_TESSELLATION_FACTOR_AMD = 0x9005;

	private AMDVertexShaderTessellator() {}

	public static void glTessellationFactorAMD(float factor) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glTessellationFactorAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglTessellationFactorAMD(factor, function_pointer);
	}
	static native void nglTessellationFactorAMD(float factor, long function_pointer);

	public static void glTessellationModeAMD(int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glTessellationModeAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglTessellationModeAMD(mode, function_pointer);
	}
	static native void nglTessellationModeAMD(int mode, long function_pointer);
}
