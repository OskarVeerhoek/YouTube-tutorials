/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glMultiDrawArraysEXTPROC) (GLenum mode, GLint * first, GLsizei * count, GLsizei primcount);

static glMultiDrawArraysEXTPROC glMultiDrawArraysEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTMultiDrawArrays_nglMultiDrawArraysEXT(JNIEnv *env, jclass clazz, jint mode, jlong first, jlong count, jint primcount) {
	GLint *first_address = (GLint *)(intptr_t)first;
	GLsizei *count_address = (GLsizei *)(intptr_t)count;
	glMultiDrawArraysEXT(mode, first_address, count_address, primcount);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTMultiDrawArrays_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglMultiDrawArraysEXT", "(IJJI)V", (void *)&Java_org_lwjgl_opengles_EXTMultiDrawArrays_nglMultiDrawArraysEXT, "glMultiDrawArraysEXT", (void *)&glMultiDrawArraysEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
