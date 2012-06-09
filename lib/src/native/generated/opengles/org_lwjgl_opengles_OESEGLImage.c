/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glEGLImageTargetTexture2DOESPROC) (GLenum target, GLeglImageOES image);
typedef GL_APICALL void (GL_APIENTRY *glEGLImageTargetRenderbufferStorageOESPROC) (GLenum target, GLeglImageOES image);

static glEGLImageTargetTexture2DOESPROC glEGLImageTargetTexture2DOES;
static glEGLImageTargetRenderbufferStorageOESPROC glEGLImageTargetRenderbufferStorageOES;

static void JNICALL Java_org_lwjgl_opengles_OESEGLImage_nglEGLImageTargetTexture2DOES(JNIEnv *env, jclass clazz, jint target, jlong image) {
	glEGLImageTargetTexture2DOES(target, (GLeglImageOES)(intptr_t)image);
}

static void JNICALL Java_org_lwjgl_opengles_OESEGLImage_nglEGLImageTargetRenderbufferStorageOES(JNIEnv *env, jclass clazz, jint target, jlong image) {
	glEGLImageTargetRenderbufferStorageOES(target, (GLeglImageOES)(intptr_t)image);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESEGLImage_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglEGLImageTargetTexture2DOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESEGLImage_nglEGLImageTargetTexture2DOES, "glEGLImageTargetTexture2DOES", (void *)&glEGLImageTargetTexture2DOES, false},
		{"nglEGLImageTargetRenderbufferStorageOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESEGLImage_nglEGLImageTargetRenderbufferStorageOES, "glEGLImageTargetRenderbufferStorageOES", (void *)&glEGLImageTargetRenderbufferStorageOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
