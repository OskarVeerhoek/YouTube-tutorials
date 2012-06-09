/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBlendEquationOESPROC) (GLenum mode);

static glBlendEquationOESPROC glBlendEquationOES;

static void JNICALL Java_org_lwjgl_opengles_OESBlendSubtract_nglBlendEquationOES(JNIEnv *env, jclass clazz, jint mode) {
	glBlendEquationOES(mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESBlendSubtract_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBlendEquationOES", "(I)V", (void *)&Java_org_lwjgl_opengles_OESBlendSubtract_nglBlendEquationOES, "glBlendEquationOES", (void *)&glBlendEquationOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
