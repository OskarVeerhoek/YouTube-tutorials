/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGetTexImageNVPROC) (GLenum target, GLint level, GLenum format, GLenum type, GLvoid * img);
typedef GL_APICALL void (GL_APIENTRY *glGetCompressedTexImageNVPROC) (GLenum target, GLint level, GLvoid * img);
typedef GL_APICALL void (GL_APIENTRY *glGetTexLevelParameterfvNVPROC) (GLenum target, GLint level, GLenum pname, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetTexLevelParameterivNVPROC) (GLenum target, GLint level, GLenum pname, GLint * params);

static glGetTexImageNVPROC glGetTexImageNV;
static glGetCompressedTexImageNVPROC glGetCompressedTexImageNV;
static glGetTexLevelParameterfvNVPROC glGetTexLevelParameterfvNV;
static glGetTexLevelParameterivNVPROC glGetTexLevelParameterivNV;

static void JNICALL Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexImageNV(JNIEnv *env, jclass clazz, jint target, jint level, jint format, jint type, jlong img) {
	GLvoid *img_address = (GLvoid *)(intptr_t)img;
	glGetTexImageNV(target, level, format, type, img_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVGetTexImage_nglGetCompressedTexImageNV(JNIEnv *env, jclass clazz, jint target, jint level, jlong img) {
	GLvoid *img_address = (GLvoid *)(intptr_t)img;
	glGetCompressedTexImageNV(target, level, img_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexLevelParameterfvNV(JNIEnv *env, jclass clazz, jint target, jint level, jint pname, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetTexLevelParameterfvNV(target, level, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexLevelParameterivNV(JNIEnv *env, jclass clazz, jint target, jint level, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetTexLevelParameterivNV(target, level, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVGetTexImage_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetTexImageNV", "(IIIIJ)V", (void *)&Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexImageNV, "glGetTexImageNV", (void *)&glGetTexImageNV, false},
		{"nglGetCompressedTexImageNV", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_NVGetTexImage_nglGetCompressedTexImageNV, "glGetCompressedTexImageNV", (void *)&glGetCompressedTexImageNV, false},
		{"nglGetTexLevelParameterfvNV", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexLevelParameterfvNV, "glGetTexLevelParameterfvNV", (void *)&glGetTexLevelParameterfvNV, false},
		{"nglGetTexLevelParameterivNV", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_NVGetTexImage_nglGetTexLevelParameterivNV, "glGetTexLevelParameterivNV", (void *)&glGetTexLevelParameterivNV, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
