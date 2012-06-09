/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glTexImage3DOESPROC) (GLenum target, GLint level, GLenum internalFormat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum format, GLenum type, const GLvoid * pixels);
typedef GL_APICALL void (GL_APIENTRY *glTexSubImage3DOESPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, const GLvoid * pixels);
typedef GL_APICALL void (GL_APIENTRY *glCopyTexSubImage3DOESPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glCompressedTexImage3DOESPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glCompressedTexSubImage3DOESPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferTexture3DOESPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level, GLint zoffset);

static glTexImage3DOESPROC glTexImage3DOES;
static glTexSubImage3DOESPROC glTexSubImage3DOES;
static glCopyTexSubImage3DOESPROC glCopyTexSubImage3DOES;
static glCompressedTexImage3DOESPROC glCompressedTexImage3DOES;
static glCompressedTexSubImage3DOESPROC glCompressedTexSubImage3DOES;
static glFramebufferTexture3DOESPROC glFramebufferTexture3DOES;

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglTexImage3DOES(JNIEnv *env, jclass clazz, jint target, jint level, jint internalFormat, jint width, jint height, jint depth, jint border, jint format, jint type, jlong pixels) {
	const GLvoid *pixels_address = (const GLvoid *)(intptr_t)pixels;
	glTexImage3DOES(target, level, internalFormat, width, height, depth, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglTexSubImage3DOES(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jlong pixels) {
	const GLvoid *pixels_address = (const GLvoid *)(intptr_t)pixels;
	glTexSubImage3DOES(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglCopyTexSubImage3DOES(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height) {
	glCopyTexSubImage3DOES(target, level, xoffset, yoffset, zoffset, x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglCompressedTexImage3DOES(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imageSize, jlong data) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glCompressedTexImage3DOES(target, level, internalformat, width, height, depth, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglCompressedTexSubImage3DOES(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imageSize, jlong data) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glCompressedTexSubImage3DOES(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESTexture3D_nglFramebufferTexture3DOES(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level, jint zoffset) {
	glFramebufferTexture3DOES(target, attachment, textarget, texture, level, zoffset);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESTexture3D_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglTexImage3DOES", "(IIIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglTexImage3DOES, "glTexImage3DOES", (void *)&glTexImage3DOES, false},
		{"nglTexSubImage3DOES", "(IIIIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglTexSubImage3DOES, "glTexSubImage3DOES", (void *)&glTexSubImage3DOES, false},
		{"nglCopyTexSubImage3DOES", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglCopyTexSubImage3DOES, "glCopyTexSubImage3DOES", (void *)&glCopyTexSubImage3DOES, false},
		{"nglCompressedTexImage3DOES", "(IIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglCompressedTexImage3DOES, "glCompressedTexImage3DOES", (void *)&glCompressedTexImage3DOES, false},
		{"nglCompressedTexSubImage3DOES", "(IIIIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglCompressedTexSubImage3DOES, "glCompressedTexSubImage3DOES", (void *)&glCompressedTexSubImage3DOES, false},
		{"nglFramebufferTexture3DOES", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_OESTexture3D_nglFramebufferTexture3DOES, "glFramebufferTexture3DOES", (void *)&glFramebufferTexture3DOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
