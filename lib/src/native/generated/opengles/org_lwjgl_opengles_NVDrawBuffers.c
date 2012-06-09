/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glDrawBuffersNVPROC) (GLsizei n, const GLenum * bufs);

static glDrawBuffersNVPROC glDrawBuffersNV;

static void JNICALL Java_org_lwjgl_opengles_NVDrawBuffers_nglDrawBuffersNV(JNIEnv *env, jclass clazz, jint n, jlong bufs) {
	const GLenum *bufs_address = (const GLenum *)(intptr_t)bufs;
	glDrawBuffersNV(n, bufs_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVDrawBuffers_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawBuffersNV", "(IJ)V", (void *)&Java_org_lwjgl_opengles_NVDrawBuffers_nglDrawBuffersNV, "glDrawBuffersNV", (void *)&glDrawBuffersNV, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
