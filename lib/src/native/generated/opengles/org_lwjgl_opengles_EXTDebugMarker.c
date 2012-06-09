/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glInsertEventMarkerEXTPROC) (GLsizei length, const GLchar * marker);
typedef GL_APICALL void (GL_APIENTRY *glPushGroupMarkerEXTPROC) (GLsizei length, const GLchar * marker);
typedef GL_APICALL void (GL_APIENTRY *glPopGroupMarkerEXTPROC) ();

static glInsertEventMarkerEXTPROC glInsertEventMarkerEXT;
static glPushGroupMarkerEXTPROC glPushGroupMarkerEXT;
static glPopGroupMarkerEXTPROC glPopGroupMarkerEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTDebugMarker_nglInsertEventMarkerEXT(JNIEnv *env, jclass clazz, jint length, jlong marker) {
	const GLchar *marker_address = (const GLchar *)(intptr_t)marker;
	glInsertEventMarkerEXT(length, marker_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTDebugMarker_nglPushGroupMarkerEXT(JNIEnv *env, jclass clazz, jint length, jlong marker) {
	const GLchar *marker_address = (const GLchar *)(intptr_t)marker;
	glPushGroupMarkerEXT(length, marker_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTDebugMarker_nglPopGroupMarkerEXT(JNIEnv *env, jclass clazz) {
	glPopGroupMarkerEXT();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTDebugMarker_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglInsertEventMarkerEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTDebugMarker_nglInsertEventMarkerEXT, "glInsertEventMarkerEXT", (void *)&glInsertEventMarkerEXT, false},
		{"nglPushGroupMarkerEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTDebugMarker_nglPushGroupMarkerEXT, "glPushGroupMarkerEXT", (void *)&glPushGroupMarkerEXT, false},
		{"nglPopGroupMarkerEXT", "()V", (void *)&Java_org_lwjgl_opengles_EXTDebugMarker_nglPopGroupMarkerEXT, "glPopGroupMarkerEXT", (void *)&glPopGroupMarkerEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
