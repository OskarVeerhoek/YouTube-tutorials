/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBlitFramebufferANGLEPROC) (GLint srcX0, GLint srcY0, GLint srcX1, GLint srcY1, GLint dstX0, GLint dstY0, GLint dstX1, GLint dstY1, GLbitfield mask, GLenum filter);

static glBlitFramebufferANGLEPROC glBlitFramebufferANGLE;

static void JNICALL Java_org_lwjgl_opengles_ANGLEFramebufferBlit_nglBlitFramebufferANGLE(JNIEnv *env, jclass clazz, jint srcX0, jint srcY0, jint srcX1, jint srcY1, jint dstX0, jint dstY0, jint dstX1, jint dstY1, jint mask, jint filter) {
	glBlitFramebufferANGLE(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_ANGLEFramebufferBlit_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBlitFramebufferANGLE", "(IIIIIIIIII)V", (void *)&Java_org_lwjgl_opengles_ANGLEFramebufferBlit_nglBlitFramebufferANGLE, "glBlitFramebufferANGLE", (void *)&glBlitFramebufferANGLE, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
