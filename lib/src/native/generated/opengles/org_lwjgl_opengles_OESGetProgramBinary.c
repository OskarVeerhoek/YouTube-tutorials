/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGetProgramBinaryOESPROC) (GLuint program, GLsizei bufSize, GLsizei * length, GLenum * binaryFormat, GLvoid * binary);
typedef GL_APICALL void (GL_APIENTRY *glProgramBinaryOESPROC) (GLuint program, GLenum binaryFormat, const GLvoid * binary, GLint length);

static glGetProgramBinaryOESPROC glGetProgramBinaryOES;
static glProgramBinaryOESPROC glProgramBinaryOES;

static void JNICALL Java_org_lwjgl_opengles_OESGetProgramBinary_nglGetProgramBinaryOES(JNIEnv *env, jclass clazz, jint program, jint bufSize, jlong length, jlong binaryFormat, jlong binary) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLenum *binaryFormat_address = (GLenum *)(intptr_t)binaryFormat;
	GLvoid *binary_address = (GLvoid *)(intptr_t)binary;
	glGetProgramBinaryOES(program, bufSize, length_address, binaryFormat_address, binary_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESGetProgramBinary_nglProgramBinaryOES(JNIEnv *env, jclass clazz, jint program, jint binaryFormat, jlong binary, jint length) {
	const GLvoid *binary_address = (const GLvoid *)(intptr_t)binary;
	glProgramBinaryOES(program, binaryFormat, binary_address, length);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESGetProgramBinary_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetProgramBinaryOES", "(IIJJJ)V", (void *)&Java_org_lwjgl_opengles_OESGetProgramBinary_nglGetProgramBinaryOES, "glGetProgramBinaryOES", (void *)&glGetProgramBinaryOES, false},
		{"nglProgramBinaryOES", "(IIJI)V", (void *)&Java_org_lwjgl_opengles_OESGetProgramBinary_nglProgramBinaryOES, "glProgramBinaryOES", (void *)&glProgramBinaryOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
