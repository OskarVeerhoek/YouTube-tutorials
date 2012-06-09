/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStorageMultisampleEXTPROC) (GLenum target, GLsizei samples, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferTexture2DMultisampleEXTPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level, GLsizei samples);

static glRenderbufferStorageMultisampleEXTPROC glRenderbufferStorageMultisampleEXT;
static glFramebufferTexture2DMultisampleEXTPROC glFramebufferTexture2DMultisampleEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTMultisampledRenderToTexture_nglRenderbufferStorageMultisampleEXT(JNIEnv *env, jclass clazz, jint target, jint samples, jint internalformat, jint width, jint height) {
	glRenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_EXTMultisampledRenderToTexture_nglFramebufferTexture2DMultisampleEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint samples) {
	glFramebufferTexture2DMultisampleEXT(target, attachment, textarget, texture, level, samples);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTMultisampledRenderToTexture_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglRenderbufferStorageMultisampleEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_EXTMultisampledRenderToTexture_nglRenderbufferStorageMultisampleEXT, "glRenderbufferStorageMultisampleEXT", (void *)&glRenderbufferStorageMultisampleEXT, false},
		{"nglFramebufferTexture2DMultisampleEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_EXTMultisampledRenderToTexture_nglFramebufferTexture2DMultisampleEXT, "glFramebufferTexture2DMultisampleEXT", (void *)&glFramebufferTexture2DMultisampleEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
