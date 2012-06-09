/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glEGLImageTargetTexture2DOESPROC) (GLenum target, GLeglImageOES image);

static glEGLImageTargetTexture2DOESPROC glEGLImageTargetTexture2DOES;

static void JNICALL Java_org_lwjgl_opengles_OESEGLImageExternal_nglEGLImageTargetTexture2DOES(JNIEnv *env, jclass clazz, jint target, jlong image) {
	glEGLImageTargetTexture2DOES(target, (GLeglImageOES)(intptr_t)image);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESEGLImageExternal_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglEGLImageTargetTexture2DOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESEGLImageExternal_nglEGLImageTargetTexture2DOES, "glEGLImageTargetTexture2DOES", (void *)&glEGLImageTargetTexture2DOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
