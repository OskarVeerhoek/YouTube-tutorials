/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glReadBufferNVPROC) (GLenum mode);

static glReadBufferNVPROC glReadBufferNV;

static void JNICALL Java_org_lwjgl_opengles_NVReadBuffer_nglReadBufferNV(JNIEnv *env, jclass clazz, jint mode) {
	glReadBufferNV(mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVReadBuffer_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglReadBufferNV", "(I)V", (void *)&Java_org_lwjgl_opengles_NVReadBuffer_nglReadBufferNV, "glReadBufferNV", (void *)&glReadBufferNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
