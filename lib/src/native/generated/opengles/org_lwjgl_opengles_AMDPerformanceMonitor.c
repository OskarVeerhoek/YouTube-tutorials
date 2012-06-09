/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorGroupsAMDPROC) (GLint * numGroups, GLsizei groupsSize, GLuint * groups);
typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorCountersAMDPROC) (GLuint group, GLint * numCounters, GLint * maxActiveCounters, GLsizei countersSize, GLuint * counters);
typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorGroupStringAMDPROC) (GLuint group, GLsizei bufSize, GLsizei * length, GLchar * groupString);
typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorCounterStringAMDPROC) (GLuint group, GLuint counter, GLsizei bufSize, GLsizei * length, GLchar * counterString);
typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorCounterInfoAMDPROC) (GLuint group, GLuint counter, GLenum pname, GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glGenPerfMonitorsAMDPROC) (GLsizei n, GLuint * monitors);
typedef GL_APICALL void (GL_APIENTRY *glDeletePerfMonitorsAMDPROC) (GLsizei n, GLuint * monitors);
typedef GL_APICALL void (GL_APIENTRY *glSelectPerfMonitorCountersAMDPROC) (GLuint monitor, GLboolean enable, GLuint group, GLint numCounters, GLuint * counterList);
typedef GL_APICALL void (GL_APIENTRY *glBeginPerfMonitorAMDPROC) (GLuint monitor);
typedef GL_APICALL void (GL_APIENTRY *glEndPerfMonitorAMDPROC) (GLuint monitor);
typedef GL_APICALL void (GL_APIENTRY *glGetPerfMonitorCounterDataAMDPROC) (GLuint monitor, GLenum pname, GLsizei dataSize, GLuint * data, GLint * bytesWritten);

static glGetPerfMonitorGroupsAMDPROC glGetPerfMonitorGroupsAMD;
static glGetPerfMonitorCountersAMDPROC glGetPerfMonitorCountersAMD;
static glGetPerfMonitorGroupStringAMDPROC glGetPerfMonitorGroupStringAMD;
static glGetPerfMonitorCounterStringAMDPROC glGetPerfMonitorCounterStringAMD;
static glGetPerfMonitorCounterInfoAMDPROC glGetPerfMonitorCounterInfoAMD;
static glGenPerfMonitorsAMDPROC glGenPerfMonitorsAMD;
static glDeletePerfMonitorsAMDPROC glDeletePerfMonitorsAMD;
static glSelectPerfMonitorCountersAMDPROC glSelectPerfMonitorCountersAMD;
static glBeginPerfMonitorAMDPROC glBeginPerfMonitorAMD;
static glEndPerfMonitorAMDPROC glEndPerfMonitorAMD;
static glGetPerfMonitorCounterDataAMDPROC glGetPerfMonitorCounterDataAMD;

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorGroupsAMD(JNIEnv *env, jclass clazz, jlong numGroups, jint groupsSize, jlong groups) {
	GLint *numGroups_address = (GLint *)(intptr_t)numGroups;
	GLuint *groups_address = (GLuint *)(intptr_t)groups;
	glGetPerfMonitorGroupsAMD(numGroups_address, groupsSize, groups_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCountersAMD(JNIEnv *env, jclass clazz, jint group, jlong numCounters, jlong maxActiveCounters, jint countersSize, jlong counters) {
	GLint *numCounters_address = (GLint *)(intptr_t)numCounters;
	GLint *maxActiveCounters_address = (GLint *)(intptr_t)maxActiveCounters;
	GLuint *counters_address = (GLuint *)(intptr_t)counters;
	glGetPerfMonitorCountersAMD(group, numCounters_address, maxActiveCounters_address, countersSize, counters_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorGroupStringAMD(JNIEnv *env, jclass clazz, jint group, jint bufSize, jlong length, jlong groupString) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *groupString_address = (GLchar *)(intptr_t)groupString;
	glGetPerfMonitorGroupStringAMD(group, bufSize, length_address, groupString_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterStringAMD(JNIEnv *env, jclass clazz, jint group, jint counter, jint bufSize, jlong length, jlong counterString) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *counterString_address = (GLchar *)(intptr_t)counterString;
	glGetPerfMonitorCounterStringAMD(group, counter, bufSize, length_address, counterString_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterInfoAMD(JNIEnv *env, jclass clazz, jint group, jint counter, jint pname, jlong data) {
	GLvoid *data_address = (GLvoid *)(intptr_t)data;
	glGetPerfMonitorCounterInfoAMD(group, counter, pname, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGenPerfMonitorsAMD(JNIEnv *env, jclass clazz, jint n, jlong monitors) {
	GLuint *monitors_address = (GLuint *)(intptr_t)monitors;
	glGenPerfMonitorsAMD(n, monitors_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglDeletePerfMonitorsAMD(JNIEnv *env, jclass clazz, jint n, jlong monitors) {
	GLuint *monitors_address = (GLuint *)(intptr_t)monitors;
	glDeletePerfMonitorsAMD(n, monitors_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglSelectPerfMonitorCountersAMD(JNIEnv *env, jclass clazz, jint monitor, jboolean enable, jint group, jint numCounters, jlong counterList) {
	GLuint *counterList_address = (GLuint *)(intptr_t)counterList;
	glSelectPerfMonitorCountersAMD(monitor, enable, group, numCounters, counterList_address);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglBeginPerfMonitorAMD(JNIEnv *env, jclass clazz, jint monitor) {
	glBeginPerfMonitorAMD(monitor);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglEndPerfMonitorAMD(JNIEnv *env, jclass clazz, jint monitor) {
	glEndPerfMonitorAMD(monitor);
}

static void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterDataAMD(JNIEnv *env, jclass clazz, jint monitor, jint pname, jint dataSize, jlong data, jlong bytesWritten) {
	GLuint *data_address = (GLuint *)(intptr_t)data;
	GLint *bytesWritten_address = (GLint *)(intptr_t)bytesWritten;
	glGetPerfMonitorCounterDataAMD(monitor, pname, dataSize, data_address, bytesWritten_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_AMDPerformanceMonitor_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetPerfMonitorGroupsAMD", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorGroupsAMD, "glGetPerfMonitorGroupsAMD", (void *)&glGetPerfMonitorGroupsAMD, false},
		{"nglGetPerfMonitorCountersAMD", "(IJJIJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCountersAMD, "glGetPerfMonitorCountersAMD", (void *)&glGetPerfMonitorCountersAMD, false},
		{"nglGetPerfMonitorGroupStringAMD", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorGroupStringAMD, "glGetPerfMonitorGroupStringAMD", (void *)&glGetPerfMonitorGroupStringAMD, false},
		{"nglGetPerfMonitorCounterStringAMD", "(IIIJJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterStringAMD, "glGetPerfMonitorCounterStringAMD", (void *)&glGetPerfMonitorCounterStringAMD, false},
		{"nglGetPerfMonitorCounterInfoAMD", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterInfoAMD, "glGetPerfMonitorCounterInfoAMD", (void *)&glGetPerfMonitorCounterInfoAMD, false},
		{"nglGenPerfMonitorsAMD", "(IJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGenPerfMonitorsAMD, "glGenPerfMonitorsAMD", (void *)&glGenPerfMonitorsAMD, false},
		{"nglDeletePerfMonitorsAMD", "(IJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglDeletePerfMonitorsAMD, "glDeletePerfMonitorsAMD", (void *)&glDeletePerfMonitorsAMD, false},
		{"nglSelectPerfMonitorCountersAMD", "(IZIIJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglSelectPerfMonitorCountersAMD, "glSelectPerfMonitorCountersAMD", (void *)&glSelectPerfMonitorCountersAMD, false},
		{"nglBeginPerfMonitorAMD", "(I)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglBeginPerfMonitorAMD, "glBeginPerfMonitorAMD", (void *)&glBeginPerfMonitorAMD, false},
		{"nglEndPerfMonitorAMD", "(I)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglEndPerfMonitorAMD, "glEndPerfMonitorAMD", (void *)&glEndPerfMonitorAMD, false},
		{"nglGetPerfMonitorCounterDataAMD", "(IIIJJ)V", (void *)&Java_org_lwjgl_opengles_AMDPerformanceMonitor_nglGetPerfMonitorCounterDataAMD, "glGetPerfMonitorCounterDataAMD", (void *)&glGetPerfMonitorCounterDataAMD, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
