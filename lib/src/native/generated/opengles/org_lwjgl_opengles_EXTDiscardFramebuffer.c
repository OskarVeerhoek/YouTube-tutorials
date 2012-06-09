/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glDiscardFramebufferEXTPROC) (GLenum target, GLsizei numAttachments, const GLenum * attachments);

static glDiscardFramebufferEXTPROC glDiscardFramebufferEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTDiscardFramebuffer_nglDiscardFramebufferEXT(JNIEnv *env, jclass clazz, jint target, jint numAttachments, jlong attachments) {
	const GLenum *attachments_address = (const GLenum *)(intptr_t)attachments;
	glDiscardFramebufferEXT(target, numAttachments, attachments_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTDiscardFramebuffer_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglDiscardFramebufferEXT", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_EXTDiscardFramebuffer_nglDiscardFramebufferEXT, "glDiscardFramebufferEXT", (void *)&glDiscardFramebufferEXT, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
