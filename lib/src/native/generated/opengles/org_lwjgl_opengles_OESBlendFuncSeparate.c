/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBlendFuncSeparateOESPROC) (GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha);

static glBlendFuncSeparateOESPROC glBlendFuncSeparateOES;

static void JNICALL Java_org_lwjgl_opengles_OESBlendFuncSeparate_nglBlendFuncSeparateOES(JNIEnv *env, jclass clazz, jint sfactorRGB, jint dfactorRGB, jint sfactorAlpha, jint dfactorAlpha) {
	glBlendFuncSeparateOES(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESBlendFuncSeparate_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBlendFuncSeparateOES", "(IIII)V", (void *)&Java_org_lwjgl_opengles_OESBlendFuncSeparate_nglBlendFuncSeparateOES, "glBlendFuncSeparateOES", (void *)&glBlendFuncSeparateOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
