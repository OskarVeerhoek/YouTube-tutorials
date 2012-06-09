/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glFramebufferVertexAttribArrayNVPROC) (GLenum target, GLenum attachment, GLenum buffertarget, GLuint bufferobject, GLint size, GLenum type, GLboolean normalized, GLintptr offset, GLsizeiptr width, GLsizeiptr height, GLsizei stride);

static glFramebufferVertexAttribArrayNVPROC glFramebufferVertexAttribArrayNV;

static void JNICALL Java_org_lwjgl_opengles_NVFramebufferVertexAttribArray_nglFramebufferVertexAttribArrayNV(JNIEnv *env, jclass clazz, jint target, jint attachment, jint buffertarget, jint bufferobject, jint size, jint type, jboolean normalized, jlong offset, jlong width, jlong height, jint stride) {
	glFramebufferVertexAttribArrayNV(target, attachment, buffertarget, bufferobject, size, type, normalized, offset, width, height, stride);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVFramebufferVertexAttribArray_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglFramebufferVertexAttribArrayNV", "(IIIIIIZJJJI)V", (void *)&Java_org_lwjgl_opengles_NVFramebufferVertexAttribArray_nglFramebufferVertexAttribArrayNV, "glFramebufferVertexAttribArrayNV", (void *)&glFramebufferVertexAttribArrayNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
