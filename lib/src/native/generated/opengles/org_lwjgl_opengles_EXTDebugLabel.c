/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glLabelObjectEXTPROC) (GLenum type, GLuint object, GLsizei length, const GLchar * label);
typedef GL_APICALL void (GL_APIENTRY *glGetObjectLabelEXTPROC) (GLenum type, GLuint object, GLsizei bufSize, GLsizei * length, GLchar * label);

static glLabelObjectEXTPROC glLabelObjectEXT;
static glGetObjectLabelEXTPROC glGetObjectLabelEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTDebugLabel_nglLabelObjectEXT(JNIEnv *env, jclass clazz, jint type, jint object, jint length, jlong label) {
	const GLchar *label_address = (const GLchar *)(intptr_t)label;
	glLabelObjectEXT(type, object, length, label_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTDebugLabel_nglGetObjectLabelEXT(JNIEnv *env, jclass clazz, jint type, jint object, jint bufSize, jlong length, jlong label) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *label_address = (GLchar *)(intptr_t)label;
	glGetObjectLabelEXT(type, object, bufSize, length_address, label_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTDebugLabel_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglLabelObjectEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTDebugLabel_nglLabelObjectEXT, "glLabelObjectEXT", (void *)&glLabelObjectEXT, false},
		{"nglGetObjectLabelEXT", "(IIIJJ)V", (void *)&Java_org_lwjgl_opengles_EXTDebugLabel_nglGetObjectLabelEXT, "glGetObjectLabelEXT", (void *)&glGetObjectLabelEXT, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
