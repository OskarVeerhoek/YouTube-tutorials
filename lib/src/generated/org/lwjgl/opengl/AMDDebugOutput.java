/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDDebugOutput {

	/**
	 * Tokens accepted by GetIntegerv: 
	 */
	public static final int GL_MAX_DEBUG_MESSAGE_LENGTH_AMD = 0x9143,
		GL_MAX_DEBUG_LOGGED_MESSAGES_AMD = 0x9144,
		GL_DEBUG_LOGGED_MESSAGES_AMD = 0x9145;

	/**
	 *  Tokens accepted by DebugMessageEnableAMD, GetDebugMessageLogAMD,
	 *  DebugMessageInsertAMD, and DEBUGPROCAMD callback function
	 *  for &lt;severity&gt;:
	 */
	public static final int GL_DEBUG_SEVERITY_HIGH_AMD = 0x9146,
		GL_DEBUG_SEVERITY_MEDIUM_AMD = 0x9147,
		GL_DEBUG_SEVERITY_LOW_AMD = 0x9148;

	/**
	 *  Tokens accepted by DebugMessageEnableAMD, GetDebugMessageLogAMD,
	 *  and DEBUGPROCAMD callback function for &lt;category&gt;:
	 */
	public static final int GL_DEBUG_CATEGORY_API_ERROR_AMD = 0x9149,
		GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 0x914A,
		GL_DEBUG_CATEGORY_DEPRECATION_AMD = 0x914B,
		GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 0x914C,
		GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 0x914D,
		GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 0x914E,
		GL_DEBUG_CATEGORY_APPLICATION_AMD = 0x914F,
		GL_DEBUG_CATEGORY_OTHER_AMD = 0x9150;

	private AMDDebugOutput() {}

	public static void glDebugMessageEnableAMD(int category, int severity, IntBuffer ids, boolean enabled) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDebugMessageEnableAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (ids != null)
			BufferChecks.checkDirect(ids);
		nglDebugMessageEnableAMD(category, severity, (ids == null ? 0 : ids.remaining()), MemoryUtil.getAddressSafe(ids), enabled, function_pointer);
	}
	static native void nglDebugMessageEnableAMD(int category, int severity, int ids_count, long ids, boolean enabled, long function_pointer);

	public static void glDebugMessageInsertAMD(int category, int severity, int id, ByteBuffer buf) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDebugMessageInsertAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(buf);
		nglDebugMessageInsertAMD(category, severity, id, buf.remaining(), MemoryUtil.getAddress(buf), function_pointer);
	}
	static native void nglDebugMessageInsertAMD(int category, int severity, int id, int buf_length, long buf, long function_pointer);

	/** Overloads glDebugMessageInsertAMD. */
	public static void glDebugMessageInsertAMD(int category, int severity, int id, CharSequence buf) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDebugMessageInsertAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDebugMessageInsertAMD(category, severity, id, buf.length(), APIUtil.getBuffer(caps, buf), function_pointer);
	}

	/**
	 *  The {@code AMDDebugOutputCallback.Handler} implementation passed to this method will be used for
	 *  AMD_debug_output messages. If callback is null, any previously registered handler for the current
	 *  thread will be unregistered and stop receiving messages.
	 * <p>
	 *  @param callback  the callback function to use
	 */
	public static void glDebugMessageCallbackAMD(AMDDebugOutputCallback callback) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDebugMessageCallbackAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		long userParam = callback == null ? 0 : CallbackUtil.createGlobalRef(callback.getHandler());
		CallbackUtil.registerContextCallbackAMD(userParam);
		nglDebugMessageCallbackAMD(callback == null ? 0 : callback.getPointer(), userParam, function_pointer);
	}
	static native void nglDebugMessageCallbackAMD(long callback, long userParam, long function_pointer);

	public static int glGetDebugMessageLogAMD(int count, IntBuffer categories, IntBuffer severities, IntBuffer ids, IntBuffer lengths, ByteBuffer messageLog) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetDebugMessageLogAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (categories != null)
			BufferChecks.checkBuffer(categories, count);
		if (severities != null)
			BufferChecks.checkBuffer(severities, count);
		if (ids != null)
			BufferChecks.checkBuffer(ids, count);
		if (lengths != null)
			BufferChecks.checkBuffer(lengths, count);
		if (messageLog != null)
			BufferChecks.checkDirect(messageLog);
		int __result = nglGetDebugMessageLogAMD(count, (messageLog == null ? 0 : messageLog.remaining()), MemoryUtil.getAddressSafe(categories), MemoryUtil.getAddressSafe(severities), MemoryUtil.getAddressSafe(ids), MemoryUtil.getAddressSafe(lengths), MemoryUtil.getAddressSafe(messageLog), function_pointer);
		return __result;
	}
	static native int nglGetDebugMessageLogAMD(int count, int messageLog_logSize, long categories, long severities, long ids, long lengths, long messageLog, long function_pointer);
}
