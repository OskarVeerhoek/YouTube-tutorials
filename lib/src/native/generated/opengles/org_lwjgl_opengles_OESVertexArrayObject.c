/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glBindVertexArrayOESPROC) (GLuint array);
typedef GL_APICALL void (GL_APIENTRY *glDeleteVertexArraysOESPROC) (GLsizei n, const GLuint * arrays);
typedef GL_APICALL void (GL_APIENTRY *glGenVertexArraysOESPROC) (GLsizei n, GLuint * arrays);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsVertexArrayOESPROC) (GLuint array);

static glBindVertexArrayOESPROC glBindVertexArrayOES;
static glDeleteVertexArraysOESPROC glDeleteVertexArraysOES;
static glGenVertexArraysOESPROC glGenVertexArraysOES;
static glIsVertexArrayOESPROC glIsVertexArrayOES;

static void JNICALL Java_org_lwjgl_opengles_OESVertexArrayObject_nglBindVertexArrayOES(JNIEnv *env, jclass clazz, jint array) {
	glBindVertexArrayOES(array);
}

static void JNICALL Java_org_lwjgl_opengles_OESVertexArrayObject_nglDeleteVertexArraysOES(JNIEnv *env, jclass clazz, jint n, jlong arrays) {
	const GLuint *arrays_address = (const GLuint *)(intptr_t)arrays;
	glDeleteVertexArraysOES(n, arrays_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESVertexArrayObject_nglGenVertexArraysOES(JNIEnv *env, jclass clazz, jint n, jlong arrays) {
	GLuint *arrays_address = (GLuint *)(intptr_t)arrays;
	glGenVertexArraysOES(n, arrays_address);
}

static jboolean JNICALL Java_org_lwjgl_opengles_OESVertexArrayObject_nglIsVertexArrayOES(JNIEnv *env, jclass clazz, jint array) {
	GLboolean __result = glIsVertexArrayOES(array);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESVertexArrayObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglBindVertexArrayOES", "(I)V", (void *)&Java_org_lwjgl_opengles_OESVertexArrayObject_nglBindVertexArrayOES, "glBindVertexArrayOES", (void *)&glBindVertexArrayOES, false},
		{"nglDeleteVertexArraysOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESVertexArrayObject_nglDeleteVertexArraysOES, "glDeleteVertexArraysOES", (void *)&glDeleteVertexArraysOES, false},
		{"nglGenVertexArraysOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESVertexArrayObject_nglGenVertexArraysOES, "glGenVertexArraysOES", (void *)&glGenVertexArraysOES, false},
		{"nglIsVertexArrayOES", "(I)Z", (void *)&Java_org_lwjgl_opengles_OESVertexArrayObject_nglIsVertexArrayOES, "glIsVertexArrayOES", (void *)&glIsVertexArrayOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
