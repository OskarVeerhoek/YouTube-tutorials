/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glDrawBuffersARBPROC) (GLsizei size, const GLenum * buffers);

static glDrawBuffersARBPROC glDrawBuffersARB;

static void JNICALL Java_org_lwjgl_opengles_ARBDrawBuffers_nglDrawBuffersARB(JNIEnv *env, jclass clazz, jint size, jlong buffers) {
	const GLenum *buffers_address = (const GLenum *)(intptr_t)buffers;
	glDrawBuffersARB(size, buffers_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_ARBDrawBuffers_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDrawBuffersARB", "(IJ)V", (void *)&Java_org_lwjgl_opengles_ARBDrawBuffers_nglDrawBuffersARB, "glDrawBuffersARB", (void *)&glDrawBuffersARB, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
