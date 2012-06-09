/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glTexStorage1DEXTPROC) (GLenum target, GLsizei levels, GLenum internalformat, GLsizei width);
typedef GL_APICALL void (GL_APIENTRY *glTexStorage2DEXTPROC) (GLenum target, GLsizei levels, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glTexStorage3DEXTPROC) (GLenum target, GLsizei levels, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth);
typedef GL_APICALL void (GL_APIENTRY *glTextureStorage1DEXTPROC) (GLuint texture, GLenum target, GLsizei levels, GLenum internalformat, GLsizei width);
typedef GL_APICALL void (GL_APIENTRY *glTextureStorage2DEXTPROC) (GLuint texture, GLenum target, GLsizei levels, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glTextureStorage3DEXTPROC) (GLuint texture, GLenum target, GLsizei levels, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth);

static glTexStorage1DEXTPROC glTexStorage1DEXT;
static glTexStorage2DEXTPROC glTexStorage2DEXT;
static glTexStorage3DEXTPROC glTexStorage3DEXT;
static glTextureStorage1DEXTPROC glTextureStorage1DEXT;
static glTextureStorage2DEXTPROC glTextureStorage2DEXT;
static glTextureStorage3DEXTPROC glTextureStorage3DEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage1DEXT(JNIEnv *env, jclass clazz, jint target, jint levels, jint internalformat, jint width) {
	glTexStorage1DEXT(target, levels, internalformat, width);
}

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage2DEXT(JNIEnv *env, jclass clazz, jint target, jint levels, jint internalformat, jint width, jint height) {
	glTexStorage2DEXT(target, levels, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage3DEXT(JNIEnv *env, jclass clazz, jint target, jint levels, jint internalformat, jint width, jint height, jint depth) {
	glTexStorage3DEXT(target, levels, internalformat, width, height, depth);
}

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage1DEXT(JNIEnv *env, jclass clazz, jint texture, jint target, jint levels, jint internalformat, jint width) {
	glTextureStorage1DEXT(texture, target, levels, internalformat, width);
}

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage2DEXT(JNIEnv *env, jclass clazz, jint texture, jint target, jint levels, jint internalformat, jint width, jint height) {
	glTextureStorage2DEXT(texture, target, levels, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage3DEXT(JNIEnv *env, jclass clazz, jint texture, jint target, jint levels, jint internalformat, jint width, jint height, jint depth) {
	glTextureStorage3DEXT(texture, target, levels, internalformat, width, height, depth);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTTextureStorage_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglTexStorage1DEXT", "(IIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage1DEXT, "glTexStorage1DEXT", (void *)&glTexStorage1DEXT, false},
		{"nglTexStorage2DEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage2DEXT, "glTexStorage2DEXT", (void *)&glTexStorage2DEXT, false},
		{"nglTexStorage3DEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTexStorage3DEXT, "glTexStorage3DEXT", (void *)&glTexStorage3DEXT, false},
		{"nglTextureStorage1DEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage1DEXT, "glTextureStorage1DEXT", (void *)&glTextureStorage1DEXT, false},
		{"nglTextureStorage2DEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage2DEXT, "glTextureStorage2DEXT", (void *)&glTextureStorage2DEXT, false},
		{"nglTextureStorage3DEXT", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureStorage_nglTextureStorage3DEXT, "glTextureStorage3DEXT", (void *)&glTextureStorage3DEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
