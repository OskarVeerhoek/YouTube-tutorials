/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGenQueriesEXTPROC) (GLsizei n, GLuint * ids);
typedef GL_APICALL void (GL_APIENTRY *glDeleteQueriesEXTPROC) (GLsizei n, GLuint * ids);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsQueryEXTPROC) (GLuint id);
typedef GL_APICALL void (GL_APIENTRY *glBeginQueryEXTPROC) (GLenum target, GLuint id);
typedef GL_APICALL void (GL_APIENTRY *glEndQueryEXTPROC) (GLenum target);
typedef GL_APICALL void (GL_APIENTRY *glGetQueryivEXTPROC) (GLenum target, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetQueryObjectuivEXTPROC) (GLuint id, GLenum pname, GLint * params);

static glGenQueriesEXTPROC glGenQueriesEXT;
static glDeleteQueriesEXTPROC glDeleteQueriesEXT;
static glIsQueryEXTPROC glIsQueryEXT;
static glBeginQueryEXTPROC glBeginQueryEXT;
static glEndQueryEXTPROC glEndQueryEXT;
static glGetQueryivEXTPROC glGetQueryivEXT;
static glGetQueryObjectuivEXTPROC glGetQueryObjectuivEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGenQueriesEXT(JNIEnv *env, jclass clazz, jint n, jlong ids) {
	GLuint *ids_address = (GLuint *)(intptr_t)ids;
	glGenQueriesEXT(n, ids_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglDeleteQueriesEXT(JNIEnv *env, jclass clazz, jint n, jlong ids) {
	GLuint *ids_address = (GLuint *)(intptr_t)ids;
	glDeleteQueriesEXT(n, ids_address);
}

static jboolean JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglIsQueryEXT(JNIEnv *env, jclass clazz, jint id) {
	GLboolean __result = glIsQueryEXT(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglBeginQueryEXT(JNIEnv *env, jclass clazz, jint target, jint id) {
	glBeginQueryEXT(target, id);
}

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglEndQueryEXT(JNIEnv *env, jclass clazz, jint target) {
	glEndQueryEXT(target);
}

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGetQueryivEXT(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetQueryivEXT(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGetQueryObjectuivEXT(JNIEnv *env, jclass clazz, jint id, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetQueryObjectuivEXT(id, pname, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGenQueriesEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGenQueriesEXT, "glGenQueriesEXT", (void *)&glGenQueriesEXT, false},
		{"nglDeleteQueriesEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglDeleteQueriesEXT, "glDeleteQueriesEXT", (void *)&glDeleteQueriesEXT, false},
		{"nglIsQueryEXT", "(I)Z", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglIsQueryEXT, "glIsQueryEXT", (void *)&glIsQueryEXT, false},
		{"nglBeginQueryEXT", "(II)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglBeginQueryEXT, "glBeginQueryEXT", (void *)&glBeginQueryEXT, false},
		{"nglEndQueryEXT", "(I)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglEndQueryEXT, "glEndQueryEXT", (void *)&glEndQueryEXT, false},
		{"nglGetQueryivEXT", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGetQueryivEXT, "glGetQueryivEXT", (void *)&glGetQueryivEXT, false},
		{"nglGetQueryObjectuivEXT", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_EXTOcclusionQueryBoolean_nglGetQueryObjectuivEXT, "glGetQueryObjectuivEXT", (void *)&glGetQueryObjectuivEXT, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
