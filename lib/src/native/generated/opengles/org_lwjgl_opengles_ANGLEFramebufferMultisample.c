/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStorageMultisampleANGLEPROC) (GLenum target, GLsizei samples, GLenum internalformat, GLsizei width, GLsizei height);

static glRenderbufferStorageMultisampleANGLEPROC glRenderbufferStorageMultisampleANGLE;

static void JNICALL Java_org_lwjgl_opengles_ANGLEFramebufferMultisample_nglRenderbufferStorageMultisampleANGLE(JNIEnv *env, jclass clazz, jint target, jint samples, jint internalformat, jint width, jint height) {
	glRenderbufferStorageMultisampleANGLE(target, samples, internalformat, width, height);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_ANGLEFramebufferMultisample_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglRenderbufferStorageMultisampleANGLE", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_ANGLEFramebufferMultisample_nglRenderbufferStorageMultisampleANGLE, "glRenderbufferStorageMultisampleANGLE", (void *)&glRenderbufferStorageMultisampleANGLE, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
