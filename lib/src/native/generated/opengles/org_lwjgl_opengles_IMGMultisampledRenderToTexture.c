/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStorageMultisampleIMGPROC) (GLenum target, GLsizei samples, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferTexture2DMultisampleIMGPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level, GLsizei samples);

static glRenderbufferStorageMultisampleIMGPROC glRenderbufferStorageMultisampleIMG;
static glFramebufferTexture2DMultisampleIMGPROC glFramebufferTexture2DMultisampleIMG;

static void JNICALL Java_org_lwjgl_opengles_IMGMultisampledRenderToTexture_nglRenderbufferStorageMultisampleIMG(JNIEnv *env, jclass clazz, jint target, jint samples, jint internalformat, jint width, jint height) {
	glRenderbufferStorageMultisampleIMG(target, samples, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_IMGMultisampledRenderToTexture_nglFramebufferTexture2DMultisampleIMG(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint samples) {
	glFramebufferTexture2DMultisampleIMG(target, attachment, textarget, texture, level, samples);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_IMGMultisampledRenderToTexture_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglRenderbufferStorageMultisampleIMG", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_IMGMultisampledRenderToTexture_nglRenderbufferStorageMultisampleIMG, "glRenderbufferStorageMultisampleIMG", (void *)&glRenderbufferStorageMultisampleIMG, false},
		{"nglFramebufferTexture2DMultisampleIMG", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_IMGMultisampledRenderToTexture_nglFramebufferTexture2DMultisampleIMG, "glFramebufferTexture2DMultisampleIMG", (void *)&glFramebufferTexture2DMultisampleIMG, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
