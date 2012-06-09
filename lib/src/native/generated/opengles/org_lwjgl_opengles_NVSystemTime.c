/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL EGLuint64NV (GL_APIENTRY *glGetSystemTimeFrequencyNVPROC) ();
typedef GL_APICALL EGLuint64NV (GL_APIENTRY *glGetSystemTimeNVPROC) ();

static glGetSystemTimeFrequencyNVPROC glGetSystemTimeFrequencyNV;
static glGetSystemTimeNVPROC glGetSystemTimeNV;

static jlong JNICALL Java_org_lwjgl_opengles_NVSystemTime_nglGetSystemTimeFrequencyNV(JNIEnv *env, jclass clazz) {
	EGLuint64NV __result = glGetSystemTimeFrequencyNV();
	return __result;
}

static jlong JNICALL Java_org_lwjgl_opengles_NVSystemTime_nglGetSystemTimeNV(JNIEnv *env, jclass clazz) {
	EGLuint64NV __result = glGetSystemTimeNV();
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVSystemTime_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetSystemTimeFrequencyNV", "()J", (void *)&Java_org_lwjgl_opengles_NVSystemTime_nglGetSystemTimeFrequencyNV, "glGetSystemTimeFrequencyNV", (void *)&glGetSystemTimeFrequencyNV, false},
		{"nglGetSystemTimeNV", "()J", (void *)&Java_org_lwjgl_opengles_NVSystemTime_nglGetSystemTimeNV, "glGetSystemTimeNV", (void *)&glGetSystemTimeNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
