/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBlendEquationEXTPROC) (GLenum mode);

static glBlendEquationEXTPROC glBlendEquationEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTBlendMinmax_nglBlendEquationEXT(JNIEnv *env, jclass clazz, jint mode) {
	glBlendEquationEXT(mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTBlendMinmax_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBlendEquationEXT", "(I)V", (void *)&Java_org_lwjgl_opengles_EXTBlendMinmax_nglBlendEquationEXT, "glBlendEquationEXT", (void *)&glBlendEquationEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
