/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL GLuint (GL_APIENTRY *glCreatePathNVPROC) (GLenum datatype, GLsizei numCommands, const GLubyte * commands);
typedef GL_APICALL void (GL_APIENTRY *glDeletePathNVPROC) (GLuint path);
typedef GL_APICALL void (GL_APIENTRY *glPathVerticesNVPROC) (GLuint path, const GLvoid * vertices);
typedef GL_APICALL void (GL_APIENTRY *glPathParameterfNVPROC) (GLuint path, GLenum paramType, GLfloat param);
typedef GL_APICALL void (GL_APIENTRY *glPathParameteriNVPROC) (GLuint path, GLenum paramType, GLint param);
typedef GL_APICALL GLuint (GL_APIENTRY *glCreatePathProgramNVPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glPathMatrixNVPROC) (GLenum target, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glDrawPathNVPROC) (GLuint path, GLenum mode);
typedef GL_APICALL GLuint (GL_APIENTRY *glCreatePathbufferNVPROC) (GLsizei capacity);
typedef GL_APICALL void (GL_APIENTRY *glDeletePathbufferNVPROC) (GLuint buffer);
typedef GL_APICALL void (GL_APIENTRY *glPathbufferPathNVPROC) (GLuint buffer, GLint index, GLuint path);
typedef GL_APICALL void (GL_APIENTRY *glPathbufferPositionNVPROC) (GLuint buffer, GLint index, GLfloat x, GLfloat y);
typedef GL_APICALL void (GL_APIENTRY *glDrawPathbufferNVPROC) (GLuint buffer, GLenum mode);

static glCreatePathNVPROC glCreatePathNV;
static glDeletePathNVPROC glDeletePathNV;
static glPathVerticesNVPROC glPathVerticesNV;
static glPathParameterfNVPROC glPathParameterfNV;
static glPathParameteriNVPROC glPathParameteriNV;
static glCreatePathProgramNVPROC glCreatePathProgramNV;
static glPathMatrixNVPROC glPathMatrixNV;
static glDrawPathNVPROC glDrawPathNV;
static glCreatePathbufferNVPROC glCreatePathbufferNV;
static glDeletePathbufferNVPROC glDeletePathbufferNV;
static glPathbufferPathNVPROC glPathbufferPathNV;
static glPathbufferPositionNVPROC glPathbufferPositionNV;
static glDrawPathbufferNVPROC glDrawPathbufferNV;

static jint JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathNV(JNIEnv *env, jclass clazz, jint datatype, jint numCommands, jlong commands) {
	const GLubyte *commands_address = (const GLubyte *)(intptr_t)commands;
	GLuint __result = glCreatePathNV(datatype, numCommands, commands_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglDeletePathNV(JNIEnv *env, jclass clazz, jint path) {
	glDeletePathNV(path);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathVerticesNV(JNIEnv *env, jclass clazz, jint path, jlong vertices) {
	const GLvoid *vertices_address = (const GLvoid *)(intptr_t)vertices;
	glPathVerticesNV(path, vertices_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathParameterfNV(JNIEnv *env, jclass clazz, jint path, jint paramType, jfloat param) {
	glPathParameterfNV(path, paramType, param);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathParameteriNV(JNIEnv *env, jclass clazz, jint path, jint paramType, jint param) {
	glPathParameteriNV(path, paramType, param);
}

static jint JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathProgramNV(JNIEnv *env, jclass clazz) {
	GLuint __result = glCreatePathProgramNV();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathMatrixNV(JNIEnv *env, jclass clazz, jint target, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glPathMatrixNV(target, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglDrawPathNV(JNIEnv *env, jclass clazz, jint path, jint mode) {
	glDrawPathNV(path, mode);
}

static jint JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathbufferNV(JNIEnv *env, jclass clazz, jint capacity) {
	GLuint __result = glCreatePathbufferNV(capacity);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglDeletePathbufferNV(JNIEnv *env, jclass clazz, jint buffer) {
	glDeletePathbufferNV(buffer);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathbufferPathNV(JNIEnv *env, jclass clazz, jint buffer, jint index, jint path) {
	glPathbufferPathNV(buffer, index, path);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglPathbufferPositionNV(JNIEnv *env, jclass clazz, jint buffer, jint index, jfloat x, jfloat y) {
	glPathbufferPositionNV(buffer, index, x, y);
}

static void JNICALL Java_org_lwjgl_opengles_NVDrawPath_nglDrawPathbufferNV(JNIEnv *env, jclass clazz, jint buffer, jint mode) {
	glDrawPathbufferNV(buffer, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVDrawPath_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglCreatePathNV", "(IIJ)I", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathNV, "glCreatePathNV", (void *)&glCreatePathNV, false},
		{"nglDeletePathNV", "(I)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglDeletePathNV, "glDeletePathNV", (void *)&glDeletePathNV, false},
		{"nglPathVerticesNV", "(IJ)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathVerticesNV, "glPathVerticesNV", (void *)&glPathVerticesNV, false},
		{"nglPathParameterfNV", "(IIF)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathParameterfNV, "glPathParameterfNV", (void *)&glPathParameterfNV, false},
		{"nglPathParameteriNV", "(III)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathParameteriNV, "glPathParameteriNV", (void *)&glPathParameteriNV, false},
		{"nglCreatePathProgramNV", "()I", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathProgramNV, "glCreatePathProgramNV", (void *)&glCreatePathProgramNV, false},
		{"nglPathMatrixNV", "(IJ)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathMatrixNV, "glPathMatrixNV", (void *)&glPathMatrixNV, false},
		{"nglDrawPathNV", "(II)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglDrawPathNV, "glDrawPathNV", (void *)&glDrawPathNV, false},
		{"nglCreatePathbufferNV", "(I)I", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglCreatePathbufferNV, "glCreatePathbufferNV", (void *)&glCreatePathbufferNV, false},
		{"nglDeletePathbufferNV", "(I)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglDeletePathbufferNV, "glDeletePathbufferNV", (void *)&glDeletePathbufferNV, false},
		{"nglPathbufferPathNV", "(III)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathbufferPathNV, "glPathbufferPathNV", (void *)&glPathbufferPathNV, false},
		{"nglPathbufferPositionNV", "(IIFF)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglPathbufferPositionNV, "glPathbufferPositionNV", (void *)&glPathbufferPositionNV, false},
		{"nglDrawPathbufferNV", "(II)V", (void *)&Java_org_lwjgl_opengles_NVDrawPath_nglDrawPathbufferNV, "glDrawPathbufferNV", (void *)&glDrawPathbufferNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
