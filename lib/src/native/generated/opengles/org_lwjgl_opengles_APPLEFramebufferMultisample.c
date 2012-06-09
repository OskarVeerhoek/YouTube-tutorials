/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStorageMultisampleAPPLEPROC) (GLenum target, GLsizei samples, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glResolveMultisampleFramebufferAPPLEPROC) ();

static glRenderbufferStorageMultisampleAPPLEPROC glRenderbufferStorageMultisampleAPPLE;
static glResolveMultisampleFramebufferAPPLEPROC glResolveMultisampleFramebufferAPPLE;

static void JNICALL Java_org_lwjgl_opengles_APPLEFramebufferMultisample_nglRenderbufferStorageMultisampleAPPLE(JNIEnv *env, jclass clazz, jint target, jint samples, jint internalformat, jint width, jint height) {
	glRenderbufferStorageMultisampleAPPLE(target, samples, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_APPLEFramebufferMultisample_nglResolveMultisampleFramebufferAPPLE(JNIEnv *env, jclass clazz) {
	glResolveMultisampleFramebufferAPPLE();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_APPLEFramebufferMultisample_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglRenderbufferStorageMultisampleAPPLE", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_APPLEFramebufferMultisample_nglRenderbufferStorageMultisampleAPPLE, "glRenderbufferStorageMultisampleAPPLE", (void *)&glRenderbufferStorageMultisampleAPPLE, false},
		{"nglResolveMultisampleFramebufferAPPLE", "()V", (void *)&Java_org_lwjgl_opengles_APPLEFramebufferMultisample_nglResolveMultisampleFramebufferAPPLE, "glResolveMultisampleFramebufferAPPLE", (void *)&glResolveMultisampleFramebufferAPPLE, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
