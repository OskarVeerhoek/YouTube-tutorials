/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glExtGetTexturesQCOMPROC) (GLuint * textures, GLint maxTextures, GLint * numTextures);
typedef GL_APICALL void (GL_APIENTRY *glExtGetBuffersQCOMPROC) (GLuint * buffers, GLint maxBuffers, GLint * numBuffers);
typedef GL_APICALL void (GL_APIENTRY *glExtGetRenderbuffersQCOMPROC) (GLuint * renderbuffers, GLint maxRenderbuffers, GLint * numRenderbuffers);
typedef GL_APICALL void (GL_APIENTRY *glExtGetFramebuffersQCOMPROC) (GLuint * framebuffers, GLint maxFramebuffers, GLint * numFramebuffers);
typedef GL_APICALL void (GL_APIENTRY *glExtGetTexLevelParameterivQCOMPROC) (GLuint texture, GLenum face, GLint level, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glExtTexObjectStateOverrideiQCOMPROC) (GLenum target, GLenum pname, GLint param);
typedef GL_APICALL void (GL_APIENTRY *glExtGetTexSubImageQCOMPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, GLvoid * texels);
typedef GL_APICALL void (GL_APIENTRY *glExtGetBufferPointervQCOMPROC) (GLenum target, GLvoid ** params);

static glExtGetTexturesQCOMPROC glExtGetTexturesQCOM;
static glExtGetBuffersQCOMPROC glExtGetBuffersQCOM;
static glExtGetRenderbuffersQCOMPROC glExtGetRenderbuffersQCOM;
static glExtGetFramebuffersQCOMPROC glExtGetFramebuffersQCOM;
static glExtGetTexLevelParameterivQCOMPROC glExtGetTexLevelParameterivQCOM;
static glExtTexObjectStateOverrideiQCOMPROC glExtTexObjectStateOverrideiQCOM;
static glExtGetTexSubImageQCOMPROC glExtGetTexSubImageQCOM;
static glExtGetBufferPointervQCOMPROC glExtGetBufferPointervQCOM;

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexturesQCOM(JNIEnv *env, jclass clazz, jlong textures, jint maxTextures, jlong numTextures) {
	GLuint *textures_address = (GLuint *)(intptr_t)textures;
	GLint *numTextures_address = (GLint *)(intptr_t)numTextures;
	glExtGetTexturesQCOM(textures_address, maxTextures, numTextures_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetBuffersQCOM(JNIEnv *env, jclass clazz, jlong buffers, jint maxBuffers, jlong numBuffers) {
	GLuint *buffers_address = (GLuint *)(intptr_t)buffers;
	GLint *numBuffers_address = (GLint *)(intptr_t)numBuffers;
	glExtGetBuffersQCOM(buffers_address, maxBuffers, numBuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetRenderbuffersQCOM(JNIEnv *env, jclass clazz, jlong renderbuffers, jint maxRenderbuffers, jlong numRenderbuffers) {
	GLuint *renderbuffers_address = (GLuint *)(intptr_t)renderbuffers;
	GLint *numRenderbuffers_address = (GLint *)(intptr_t)numRenderbuffers;
	glExtGetRenderbuffersQCOM(renderbuffers_address, maxRenderbuffers, numRenderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetFramebuffersQCOM(JNIEnv *env, jclass clazz, jlong framebuffers, jint maxFramebuffers, jlong numFramebuffers) {
	GLuint *framebuffers_address = (GLuint *)(intptr_t)framebuffers;
	GLint *numFramebuffers_address = (GLint *)(intptr_t)numFramebuffers;
	glExtGetFramebuffersQCOM(framebuffers_address, maxFramebuffers, numFramebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexLevelParameterivQCOM(JNIEnv *env, jclass clazz, jint texture, jint face, jint level, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glExtGetTexLevelParameterivQCOM(texture, face, level, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtTexObjectStateOverrideiQCOM(JNIEnv *env, jclass clazz, jint target, jint pname, jint param) {
	glExtTexObjectStateOverrideiQCOM(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexSubImageQCOM(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jlong texels) {
	GLvoid *texels_address = (GLvoid *)(intptr_t)texels;
	glExtGetTexSubImageQCOM(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, texels_address);
}

static jobject JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetBufferPointervQCOM(JNIEnv *env, jclass clazz, jint target, jlong result_size) {
	GLvoid * __result;
	glExtGetBufferPointervQCOM(target, &__result);
	return safeNewBuffer(env, __result, result_size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglExtGetTexturesQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexturesQCOM, "glExtGetTexturesQCOM", (void *)&glExtGetTexturesQCOM, false},
		{"nglExtGetBuffersQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetBuffersQCOM, "glExtGetBuffersQCOM", (void *)&glExtGetBuffersQCOM, false},
		{"nglExtGetRenderbuffersQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetRenderbuffersQCOM, "glExtGetRenderbuffersQCOM", (void *)&glExtGetRenderbuffersQCOM, false},
		{"nglExtGetFramebuffersQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetFramebuffersQCOM, "glExtGetFramebuffersQCOM", (void *)&glExtGetFramebuffersQCOM, false},
		{"nglExtGetTexLevelParameterivQCOM", "(IIIIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexLevelParameterivQCOM, "glExtGetTexLevelParameterivQCOM", (void *)&glExtGetTexLevelParameterivQCOM, false},
		{"nglExtTexObjectStateOverrideiQCOM", "(III)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtTexObjectStateOverrideiQCOM, "glExtTexObjectStateOverrideiQCOM", (void *)&glExtTexObjectStateOverrideiQCOM, false},
		{"nglExtGetTexSubImageQCOM", "(IIIIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetTexSubImageQCOM, "glExtGetTexSubImageQCOM", (void *)&glExtGetTexSubImageQCOM, false},
		{"nglExtGetBufferPointervQCOM", "(IJ)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet_nglExtGetBufferPointervQCOM, "glExtGetBufferPointervQCOM", (void *)&glExtGetBufferPointervQCOM, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
