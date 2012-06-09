/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGenFencesNVPROC) (GLsizei n, GLuint * fences);
typedef GL_APICALL void (GL_APIENTRY *glDeleteFencesNVPROC) (GLsizei n, const GLuint * fences);
typedef GL_APICALL void (GL_APIENTRY *glSetFenceNVPROC) (GLuint fence, GLenum condition);
typedef GL_APICALL GLboolean (GL_APIENTRY *glTestFenceNVPROC) (GLuint fence);
typedef GL_APICALL void (GL_APIENTRY *glFinishFenceNVPROC) (GLuint fence);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsFenceNVPROC) (GLuint fence);
typedef GL_APICALL void (GL_APIENTRY *glGetFenceivNVPROC) (GLuint fence, GLenum pname, GLint * params);

static glGenFencesNVPROC glGenFencesNV;
static glDeleteFencesNVPROC glDeleteFencesNV;
static glSetFenceNVPROC glSetFenceNV;
static glTestFenceNVPROC glTestFenceNV;
static glFinishFenceNVPROC glFinishFenceNV;
static glIsFenceNVPROC glIsFenceNV;
static glGetFenceivNVPROC glGetFenceivNV;

static void JNICALL Java_org_lwjgl_opengles_NVFence_nglGenFencesNV(JNIEnv *env, jclass clazz, jint n, jlong fences) {
	GLuint *fences_address = (GLuint *)(intptr_t)fences;
	glGenFencesNV(n, fences_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVFence_nglDeleteFencesNV(JNIEnv *env, jclass clazz, jint n, jlong fences) {
	const GLuint *fences_address = (const GLuint *)(intptr_t)fences;
	glDeleteFencesNV(n, fences_address);
}

static void JNICALL Java_org_lwjgl_opengles_NVFence_nglSetFenceNV(JNIEnv *env, jclass clazz, jint fence, jint condition) {
	glSetFenceNV(fence, condition);
}

static jboolean JNICALL Java_org_lwjgl_opengles_NVFence_nglTestFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	GLboolean __result = glTestFenceNV(fence);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_NVFence_nglFinishFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	glFinishFenceNV(fence);
}

static jboolean JNICALL Java_org_lwjgl_opengles_NVFence_nglIsFenceNV(JNIEnv *env, jclass clazz, jint fence) {
	GLboolean __result = glIsFenceNV(fence);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_NVFence_nglGetFenceivNV(JNIEnv *env, jclass clazz, jint fence, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetFenceivNV(fence, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVFence_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGenFencesNV", "(IJ)V", (void *)&Java_org_lwjgl_opengles_NVFence_nglGenFencesNV, "glGenFencesNV", (void *)&glGenFencesNV, false},
		{"nglDeleteFencesNV", "(IJ)V", (void *)&Java_org_lwjgl_opengles_NVFence_nglDeleteFencesNV, "glDeleteFencesNV", (void *)&glDeleteFencesNV, false},
		{"nglSetFenceNV", "(II)V", (void *)&Java_org_lwjgl_opengles_NVFence_nglSetFenceNV, "glSetFenceNV", (void *)&glSetFenceNV, false},
		{"nglTestFenceNV", "(I)Z", (void *)&Java_org_lwjgl_opengles_NVFence_nglTestFenceNV, "glTestFenceNV", (void *)&glTestFenceNV, false},
		{"nglFinishFenceNV", "(I)V", (void *)&Java_org_lwjgl_opengles_NVFence_nglFinishFenceNV, "glFinishFenceNV", (void *)&glFinishFenceNV, false},
		{"nglIsFenceNV", "(I)Z", (void *)&Java_org_lwjgl_opengles_NVFence_nglIsFenceNV, "glIsFenceNV", (void *)&glIsFenceNV, false},
		{"nglGetFenceivNV", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_NVFence_nglGetFenceivNV, "glGetFenceivNV", (void *)&glGetFenceivNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
