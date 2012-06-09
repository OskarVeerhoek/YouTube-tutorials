/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class AMDPerformanceMonitor {

	/**
	 * Accepted by the &lt;pame&gt; parameter of GetPerfMonitorCounterInfoAMD 
	 */
	public static final int GL_COUNTER_TYPE_AMD = 0x8BC0,
		GL_COUNTER_RANGE_AMD = 0x8BC1;

	/**
	 *  Returned as a valid value in &lt;data&gt; parameter of
	 *  GetPerfMonitorCounterInfoAMD if &lt;pname&gt; = COUNTER_TYPE_AMD
	 */
	public static final int GL_UNSIGNED_INT = 0x1405,
		GL_FLOAT = 0x1406,
		GL_UNSIGNED_INT64_AMD = 0x8BC2,
		GL_PERCENTAGE_AMD = 0x8BC3;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetPerfMonitorCounterDataAMD 
	 */
	public static final int GL_PERFMON_RESULT_AVAILABLE_AMD = 0x8BC4,
		GL_PERFMON_RESULT_SIZE_AMD = 0x8BC5,
		GL_PERFMON_RESULT_AMD = 0x8BC6;

	private AMDPerformanceMonitor() {}

	public static void glGetPerfMonitorGroupsAMD(IntBuffer numGroups, IntBuffer groups) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorGroupsAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (numGroups != null)
			BufferChecks.checkBuffer(numGroups, 1);
		BufferChecks.checkDirect(groups);
		nglGetPerfMonitorGroupsAMD(MemoryUtil.getAddressSafe(numGroups), groups.remaining(), MemoryUtil.getAddress(groups), function_pointer);
	}
	static native void nglGetPerfMonitorGroupsAMD(long numGroups, int groups_groupsSize, long groups, long function_pointer);

	public static void glGetPerfMonitorCountersAMD(int group, IntBuffer numCounters, IntBuffer maxActiveCounters, IntBuffer counters) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCountersAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(numCounters, 1);
		BufferChecks.checkBuffer(maxActiveCounters, 1);
		if (counters != null)
			BufferChecks.checkDirect(counters);
		nglGetPerfMonitorCountersAMD(group, MemoryUtil.getAddress(numCounters), MemoryUtil.getAddress(maxActiveCounters), (counters == null ? 0 : counters.remaining()), MemoryUtil.getAddressSafe(counters), function_pointer);
	}
	static native void nglGetPerfMonitorCountersAMD(int group, long numCounters, long maxActiveCounters, int counters_countersSize, long counters, long function_pointer);

	public static void glGetPerfMonitorGroupStringAMD(int group, IntBuffer length, ByteBuffer groupString) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		if (groupString != null)
			BufferChecks.checkDirect(groupString);
		nglGetPerfMonitorGroupStringAMD(group, (groupString == null ? 0 : groupString.remaining()), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(groupString), function_pointer);
	}
	static native void nglGetPerfMonitorGroupStringAMD(int group, int groupString_bufSize, long length, long groupString, long function_pointer);

	/** Overloads glGetPerfMonitorGroupStringAMD. */
	public static String glGetPerfMonitorGroupStringAMD(int group, int bufSize) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer groupString_length = APIUtil.getLengths(caps);
		ByteBuffer groupString = APIUtil.getBufferByte(caps, bufSize);
		nglGetPerfMonitorGroupStringAMD(group, bufSize, MemoryUtil.getAddress0(groupString_length), MemoryUtil.getAddress(groupString), function_pointer);
		groupString.limit(groupString_length.get(0));
		return APIUtil.getString(caps, groupString);
	}

	public static void glGetPerfMonitorCounterStringAMD(int group, int counter, IntBuffer length, ByteBuffer counterString) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		if (counterString != null)
			BufferChecks.checkDirect(counterString);
		nglGetPerfMonitorCounterStringAMD(group, counter, (counterString == null ? 0 : counterString.remaining()), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(counterString), function_pointer);
	}
	static native void nglGetPerfMonitorCounterStringAMD(int group, int counter, int counterString_bufSize, long length, long counterString, long function_pointer);

	/** Overloads glGetPerfMonitorCounterStringAMD. */
	public static String glGetPerfMonitorCounterStringAMD(int group, int counter, int bufSize) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer counterString_length = APIUtil.getLengths(caps);
		ByteBuffer counterString = APIUtil.getBufferByte(caps, bufSize);
		nglGetPerfMonitorCounterStringAMD(group, counter, bufSize, MemoryUtil.getAddress0(counterString_length), MemoryUtil.getAddress(counterString), function_pointer);
		counterString.limit(counterString_length.get(0));
		return APIUtil.getString(caps, counterString);
	}

	public static void glGetPerfMonitorCounterInfoAMD(int group, int counter, int pname, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCounterInfoAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(data, 16);
		nglGetPerfMonitorCounterInfoAMD(group, counter, pname, MemoryUtil.getAddress(data), function_pointer);
	}
	static native void nglGetPerfMonitorCounterInfoAMD(int group, int counter, int pname, long data, long function_pointer);

	public static void glGenPerfMonitorsAMD(IntBuffer monitors) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenPerfMonitorsAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(monitors);
		nglGenPerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
	}
	static native void nglGenPerfMonitorsAMD(int monitors_n, long monitors, long function_pointer);

	/** Overloads glGenPerfMonitorsAMD. */
	public static int glGenPerfMonitorsAMD() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGenPerfMonitorsAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer monitors = APIUtil.getBufferInt(caps);
		nglGenPerfMonitorsAMD(1, MemoryUtil.getAddress(monitors), function_pointer);
		return monitors.get(0);
	}

	public static void glDeletePerfMonitorsAMD(IntBuffer monitors) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeletePerfMonitorsAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(monitors);
		nglDeletePerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
	}
	static native void nglDeletePerfMonitorsAMD(int monitors_n, long monitors, long function_pointer);

	/** Overloads glDeletePerfMonitorsAMD. */
	public static void glDeletePerfMonitorsAMD(int monitor) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glDeletePerfMonitorsAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeletePerfMonitorsAMD(1, APIUtil.getInt(caps, monitor), function_pointer);
	}

	public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, IntBuffer counterList) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glSelectPerfMonitorCountersAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(counterList);
		nglSelectPerfMonitorCountersAMD(monitor, enable, group, counterList.remaining(), MemoryUtil.getAddress(counterList), function_pointer);
	}
	static native void nglSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, int counterList_numCounters, long counterList, long function_pointer);

	/** Overloads glSelectPerfMonitorCountersAMD. */
	public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, int counter) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glSelectPerfMonitorCountersAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSelectPerfMonitorCountersAMD(monitor, enable, group, 1, APIUtil.getInt(caps, counter), function_pointer);
	}

	public static void glBeginPerfMonitorAMD(int monitor) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBeginPerfMonitorAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBeginPerfMonitorAMD(monitor, function_pointer);
	}
	static native void nglBeginPerfMonitorAMD(int monitor, long function_pointer);

	public static void glEndPerfMonitorAMD(int monitor) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glEndPerfMonitorAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEndPerfMonitorAMD(monitor, function_pointer);
	}
	static native void nglEndPerfMonitorAMD(int monitor, long function_pointer);

	public static void glGetPerfMonitorCounterDataAMD(int monitor, int pname, IntBuffer data, IntBuffer bytesWritten) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(data);
		if (bytesWritten != null)
			BufferChecks.checkBuffer(bytesWritten, 1);
		nglGetPerfMonitorCounterDataAMD(monitor, pname, data.remaining(), MemoryUtil.getAddress(data), MemoryUtil.getAddressSafe(bytesWritten), function_pointer);
	}
	static native void nglGetPerfMonitorCounterDataAMD(int monitor, int pname, int data_dataSize, long data, long bytesWritten, long function_pointer);

	/** Overloads glGetPerfMonitorCounterDataAMD. */
	public static int glGetPerfMonitorCounterDataAMD(int monitor, int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
		BufferChecks.checkFunctionAddress(function_pointer);
		IntBuffer data = APIUtil.getBufferInt(caps);
		nglGetPerfMonitorCounterDataAMD(monitor, pname, 4, MemoryUtil.getAddress(data), 0L, function_pointer);
		return data.get(0);
	}
}
