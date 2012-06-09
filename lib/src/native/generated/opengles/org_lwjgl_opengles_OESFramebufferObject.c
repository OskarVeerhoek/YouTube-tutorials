/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL GLboolean (GL_APIENTRY *glIsRenderbufferOESPROC) (GLuint renderbuffer);
typedef GL_APICALL void (GL_APIENTRY *glBindRenderbufferOESPROC) (GLenum target, GLuint renderbuffer);
typedef GL_APICALL void (GL_APIENTRY *glDeleteRenderbuffersOESPROC) (GLint n, const GLuint * renderbuffers);
typedef GL_APICALL void (GL_APIENTRY *glGenRenderbuffersOESPROC) (GLint n, GLuint * renderbuffers);
typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStorageOESPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glGetRenderbufferParameterivOESPROC) (GLenum target, GLenum pname, GLint * params);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsFramebufferOESPROC) (GLuint framebuffer);
typedef GL_APICALL void (GL_APIENTRY *glBindFramebufferOESPROC) (GLenum target, GLuint framebuffer);
typedef GL_APICALL void (GL_APIENTRY *glDeleteFramebuffersOESPROC) (GLint n, const GLuint * framebuffers);
typedef GL_APICALL void (GL_APIENTRY *glGenFramebuffersOESPROC) (GLint n, GLuint * framebuffers);
typedef GL_APICALL GLenum (GL_APIENTRY *glCheckFramebufferStatusOESPROC) (GLenum target);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferTexture2DOESPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferRenderbufferOESPROC) (GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer);
typedef GL_APICALL void (GL_APIENTRY *glGetFramebufferAttachmentParameterivOESPROC) (GLenum target, GLenum attachment, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGenerateMipmapOESPROC) (GLenum target);

static glIsRenderbufferOESPROC glIsRenderbufferOES;
static glBindRenderbufferOESPROC glBindRenderbufferOES;
static glDeleteRenderbuffersOESPROC glDeleteRenderbuffersOES;
static glGenRenderbuffersOESPROC glGenRenderbuffersOES;
static glRenderbufferStorageOESPROC glRenderbufferStorageOES;
static glGetRenderbufferParameterivOESPROC glGetRenderbufferParameterivOES;
static glIsFramebufferOESPROC glIsFramebufferOES;
static glBindFramebufferOESPROC glBindFramebufferOES;
static glDeleteFramebuffersOESPROC glDeleteFramebuffersOES;
static glGenFramebuffersOESPROC glGenFramebuffersOES;
static glCheckFramebufferStatusOESPROC glCheckFramebufferStatusOES;
static glFramebufferTexture2DOESPROC glFramebufferTexture2DOES;
static glFramebufferRenderbufferOESPROC glFramebufferRenderbufferOES;
static glGetFramebufferAttachmentParameterivOESPROC glGetFramebufferAttachmentParameterivOES;
static glGenerateMipmapOESPROC glGenerateMipmapOES;

static jboolean JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglIsRenderbufferOES(JNIEnv *env, jclass clazz, jint renderbuffer) {
	GLboolean __result = glIsRenderbufferOES(renderbuffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglBindRenderbufferOES(JNIEnv *env, jclass clazz, jint target, jint renderbuffer) {
	glBindRenderbufferOES(target, renderbuffer);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglDeleteRenderbuffersOES(JNIEnv *env, jclass clazz, jint n, jlong renderbuffers) {
	const GLuint *renderbuffers_address = (const GLuint *)(intptr_t)renderbuffers;
	glDeleteRenderbuffersOES(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglGenRenderbuffersOES(JNIEnv *env, jclass clazz, jint n, jlong renderbuffers) {
	GLuint *renderbuffers_address = (GLuint *)(intptr_t)renderbuffers;
	glGenRenderbuffersOES(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglRenderbufferStorageOES(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height) {
	glRenderbufferStorageOES(target, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglGetRenderbufferParameterivOES(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetRenderbufferParameterivOES(target, pname, params_address);
}

static jboolean JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglIsFramebufferOES(JNIEnv *env, jclass clazz, jint framebuffer) {
	GLboolean __result = glIsFramebufferOES(framebuffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglBindFramebufferOES(JNIEnv *env, jclass clazz, jint target, jint framebuffer) {
	glBindFramebufferOES(target, framebuffer);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglDeleteFramebuffersOES(JNIEnv *env, jclass clazz, jint n, jlong framebuffers) {
	const GLuint *framebuffers_address = (const GLuint *)(intptr_t)framebuffers;
	glDeleteFramebuffersOES(n, framebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglGenFramebuffersOES(JNIEnv *env, jclass clazz, jint n, jlong framebuffers) {
	GLuint *framebuffers_address = (GLuint *)(intptr_t)framebuffers;
	glGenFramebuffersOES(n, framebuffers_address);
}

static jint JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglCheckFramebufferStatusOES(JNIEnv *env, jclass clazz, jint target) {
	GLenum __result = glCheckFramebufferStatusOES(target);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglFramebufferTexture2DOES(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level) {
	glFramebufferTexture2DOES(target, attachment, textarget, texture, level);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglFramebufferRenderbufferOES(JNIEnv *env, jclass clazz, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer) {
	glFramebufferRenderbufferOES(target, attachment, renderbuffertarget, renderbuffer);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglGetFramebufferAttachmentParameterivOES(JNIEnv *env, jclass clazz, jint target, jint attachment, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetFramebufferAttachmentParameterivOES(target, attachment, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_nglGenerateMipmapOES(JNIEnv *env, jclass clazz, jint target) {
	glGenerateMipmapOES(target);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESFramebufferObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglIsRenderbufferOES", "(I)Z", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglIsRenderbufferOES, "glIsRenderbufferOES", (void *)&glIsRenderbufferOES, false},
		{"nglBindRenderbufferOES", "(II)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglBindRenderbufferOES, "glBindRenderbufferOES", (void *)&glBindRenderbufferOES, false},
		{"nglDeleteRenderbuffersOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglDeleteRenderbuffersOES, "glDeleteRenderbuffersOES", (void *)&glDeleteRenderbuffersOES, false},
		{"nglGenRenderbuffersOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglGenRenderbuffersOES, "glGenRenderbuffersOES", (void *)&glGenRenderbuffersOES, false},
		{"nglRenderbufferStorageOES", "(IIII)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglRenderbufferStorageOES, "glRenderbufferStorageOES", (void *)&glRenderbufferStorageOES, false},
		{"nglGetRenderbufferParameterivOES", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglGetRenderbufferParameterivOES, "glGetRenderbufferParameterivOES", (void *)&glGetRenderbufferParameterivOES, false},
		{"nglIsFramebufferOES", "(I)Z", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglIsFramebufferOES, "glIsFramebufferOES", (void *)&glIsFramebufferOES, false},
		{"nglBindFramebufferOES", "(II)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglBindFramebufferOES, "glBindFramebufferOES", (void *)&glBindFramebufferOES, false},
		{"nglDeleteFramebuffersOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglDeleteFramebuffersOES, "glDeleteFramebuffersOES", (void *)&glDeleteFramebuffersOES, false},
		{"nglGenFramebuffersOES", "(IJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglGenFramebuffersOES, "glGenFramebuffersOES", (void *)&glGenFramebuffersOES, false},
		{"nglCheckFramebufferStatusOES", "(I)I", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglCheckFramebufferStatusOES, "glCheckFramebufferStatusOES", (void *)&glCheckFramebufferStatusOES, false},
		{"nglFramebufferTexture2DOES", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglFramebufferTexture2DOES, "glFramebufferTexture2DOES", (void *)&glFramebufferTexture2DOES, false},
		{"nglFramebufferRenderbufferOES", "(IIII)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglFramebufferRenderbufferOES, "glFramebufferRenderbufferOES", (void *)&glFramebufferRenderbufferOES, false},
		{"nglGetFramebufferAttachmentParameterivOES", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglGetFramebufferAttachmentParameterivOES, "glGetFramebufferAttachmentParameterivOES", (void *)&glGetFramebufferAttachmentParameterivOES, false},
		{"nglGenerateMipmapOES", "(I)V", (void *)&Java_org_lwjgl_opengles_OESFramebufferObject_nglGenerateMipmapOES, "glGenerateMipmapOES", (void *)&glGenerateMipmapOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
