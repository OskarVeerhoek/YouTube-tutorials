/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glFramebufferTextureLayerEXTPROC) (GLenum target, GLenum attachment, GLuint texture, GLint level, GLint layer);

static glFramebufferTextureLayerEXTPROC glFramebufferTextureLayerEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTTextureArray_nglFramebufferTextureLayerEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint texture, jint level, jint layer) {
	glFramebufferTextureLayerEXT(target, attachment, texture, level, layer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTTextureArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglFramebufferTextureLayerEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_EXTTextureArray_nglFramebufferTextureLayerEXT, "glFramebufferTextureLayerEXT", (void *)&glFramebufferTextureLayerEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
