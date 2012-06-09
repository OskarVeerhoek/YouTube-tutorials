/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBlendEquationSeparateOESPROC) (GLenum modeRGB, GLenum modeAlpha);

static glBlendEquationSeparateOESPROC glBlendEquationSeparateOES;

static void JNICALL Java_org_lwjgl_opengles_OESBlendEquationSeparate_nglBlendEquationSeparateOES(JNIEnv *env, jclass clazz, jint modeRGB, jint modeAlpha) {
	glBlendEquationSeparateOES(modeRGB, modeAlpha);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESBlendEquationSeparate_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBlendEquationSeparateOES", "(II)V", (void *)&Java_org_lwjgl_opengles_OESBlendEquationSeparate_nglBlendEquationSeparateOES, "glBlendEquationSeparateOES", (void *)&glBlendEquationSeparateOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
