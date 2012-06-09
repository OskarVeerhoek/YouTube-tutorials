/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glExtGetShadersQCOMPROC) (GLuint * shaders, GLint maxShaders, GLint * numShaders);
typedef GL_APICALL void (GL_APIENTRY *glExtGetProgramsQCOMPROC) (GLuint * programs, GLint maxPrograms, GLint * numPrograms);
typedef GL_APICALL GLboolean (GL_APIENTRY *glExtIsProgramBinaryQCOMPROC) (GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glExtGetProgramBinarySourceQCOMPROC) (GLuint program, GLenum shadertype, GLchar * source, GLint * length);

static glExtGetShadersQCOMPROC glExtGetShadersQCOM;
static glExtGetProgramsQCOMPROC glExtGetProgramsQCOM;
static glExtIsProgramBinaryQCOMPROC glExtIsProgramBinaryQCOM;
static glExtGetProgramBinarySourceQCOMPROC glExtGetProgramBinarySourceQCOM;

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetShadersQCOM(JNIEnv *env, jclass clazz, jlong shaders, jint maxShaders, jlong numShaders) {
	GLuint *shaders_address = (GLuint *)(intptr_t)shaders;
	GLint *numShaders_address = (GLint *)(intptr_t)numShaders;
	glExtGetShadersQCOM(shaders_address, maxShaders, numShaders_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetProgramsQCOM(JNIEnv *env, jclass clazz, jlong programs, jint maxPrograms, jlong numPrograms) {
	GLuint *programs_address = (GLuint *)(intptr_t)programs;
	GLint *numPrograms_address = (GLint *)(intptr_t)numPrograms;
	glExtGetProgramsQCOM(programs_address, maxPrograms, numPrograms_address);
}

static jboolean JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtIsProgramBinaryQCOM(JNIEnv *env, jclass clazz, jint program) {
	GLboolean __result = glExtIsProgramBinaryQCOM(program);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetProgramBinarySourceQCOM(JNIEnv *env, jclass clazz, jint program, jint shadertype, jlong source, jlong length) {
	GLchar *source_address = (GLchar *)(intptr_t)source;
	GLint *length_address = (GLint *)(intptr_t)length;
	glExtGetProgramBinarySourceQCOM(program, shadertype, source_address, length_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_QCOMExtendedGet2_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglExtGetShadersQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetShadersQCOM, "glExtGetShadersQCOM", (void *)&glExtGetShadersQCOM, false},
		{"nglExtGetProgramsQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetProgramsQCOM, "glExtGetProgramsQCOM", (void *)&glExtGetProgramsQCOM, false},
		{"nglExtIsProgramBinaryQCOM", "(I)Z", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtIsProgramBinaryQCOM, "glExtIsProgramBinaryQCOM", (void *)&glExtIsProgramBinaryQCOM, false},
		{"nglExtGetProgramBinarySourceQCOM", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_QCOMExtendedGet2_nglExtGetProgramBinarySourceQCOM, "glExtGetProgramBinarySourceQCOM", (void *)&glExtGetProgramBinarySourceQCOM, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
