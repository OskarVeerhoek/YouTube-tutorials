/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL GLenum (GL_APIENTRY *glGetGraphicsResetStatusEXTPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glReadnPixelsEXTPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLsizei bufSize, GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glGetnUniformfvEXTPROC) (GLuint program, GLint location, GLsizei bufSize, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetnUniformivEXTPROC) (GLuint program, GLint location, GLsizei bufSize, GLint * params);

static glGetGraphicsResetStatusEXTPROC glGetGraphicsResetStatusEXT;
static glReadnPixelsEXTPROC glReadnPixelsEXT;
static glGetnUniformfvEXTPROC glGetnUniformfvEXT;
static glGetnUniformivEXTPROC glGetnUniformivEXT;

static jint JNICALL Java_org_lwjgl_opengles_EXTRobustness_nglGetGraphicsResetStatusEXT(JNIEnv *env, jclass clazz) {
	GLenum __result = glGetGraphicsResetStatusEXT();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_EXTRobustness_nglReadnPixelsEXT(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint format, jint type, jint bufSize, jlong data) {
	GLvoid *data_address = (GLvoid *)(intptr_t)data;
	glReadnPixelsEXT(x, y, width, height, format, type, bufSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTRobustness_nglGetnUniformfvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint bufSize, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetnUniformfvEXT(program, location, bufSize, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTRobustness_nglGetnUniformivEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint bufSize, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetnUniformivEXT(program, location, bufSize, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTRobustness_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetGraphicsResetStatusEXT", "()I", (void *)&Java_org_lwjgl_opengles_EXTRobustness_nglGetGraphicsResetStatusEXT, "glGetGraphicsResetStatusEXT", (void *)&glGetGraphicsResetStatusEXT, false},
		{"nglReadnPixelsEXT", "(IIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTRobustness_nglReadnPixelsEXT, "glReadnPixelsEXT", (void *)&glReadnPixelsEXT, false},
		{"nglGetnUniformfvEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTRobustness_nglGetnUniformfvEXT, "glGetnUniformfvEXT", (void *)&glGetnUniformfvEXT, false},
		{"nglGetnUniformivEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTRobustness_nglGetnUniformivEXT, "glGetnUniformivEXT", (void *)&glGetnUniformivEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
