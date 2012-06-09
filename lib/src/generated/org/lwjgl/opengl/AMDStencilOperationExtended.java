/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDStencilOperationExtended {

	/**
	 *  Accepted by the &lt;sfail&gt;, &lt;dpfail&gt; and &lt;dppass&gt; parameters of StencilOp
	 *  and StencilOpSeparate:
	 */
	public static final int GL_SET_AMD = 0x874A,
		GL_AND = 0x1501,
		GL_XOR = 0x1506,
		GL_OR = 0x1507,
		GL_NOR = 0x1508,
		GL_EQUIV = 0x1509,
		GL_NAND = 0x150E,
		GL_REPLACE_VALUE_AMD = 0x874B;

	/**
	 *  Accepted by the &lt;param&gt; parameter of GetIntegerv, GetFloatv, GetBooleanv
	 *  GetDoublev and GetInteger64v:
	 */
	public static final int GL_STENCIL_OP_VALUE_AMD = 0x874C,
		GL_STENCIL_BACK_OP_VALUE_AMD = 0x874D;

	private AMDStencilOperationExtended() {}

	public static void glStencilOpValueAMD(int face, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glStencilOpValueAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilOpValueAMD(face, value, function_pointer);
	}
	static native void nglStencilOpValueAMD(int face, int value, long function_pointer);
}
