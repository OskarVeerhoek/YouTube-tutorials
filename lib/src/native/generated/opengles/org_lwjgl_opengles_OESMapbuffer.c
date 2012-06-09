/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGetBufferPointervOESPROC) (GLenum target, GLenum pname, GLvoid ** pointer);
typedef GL_APICALL GLvoid * (GL_APIENTRY *glMapBufferOESPROC) (GLenum target, GLenum access);
typedef GL_APICALL GLboolean (GL_APIENTRY *glUnmapBufferOESPROC) (GLenum target);

static glGetBufferPointervOESPROC glGetBufferPointervOES;
static glMapBufferOESPROC glMapBufferOES;
static glUnmapBufferOESPROC glUnmapBufferOES;

static jobject JNICALL Java_org_lwjgl_opengles_OESMapbuffer_nglGetBufferPointervOES(JNIEnv *env, jclass clazz, jint target, jint pname, jlong result_size, jobject old_buffer) {
	GLvoid * __result;
	glGetBufferPointervOES(target, pname, &__result);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

static jobject JNICALL Java_org_lwjgl_opengles_OESMapbuffer_nglMapBufferOES(JNIEnv *env, jclass clazz, jint target, jint access, jlong result_size, jobject old_buffer) {
	GLvoid * __result = glMapBufferOES(target, access);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

static jboolean JNICALL Java_org_lwjgl_opengles_OESMapbuffer_nglUnmapBufferOES(JNIEnv *env, jclass clazz, jint target) {
	GLboolean __result = glUnmapBufferOES(target);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_OESMapbuffer_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetBufferPointervOES", "(IIJLjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengles_OESMapbuffer_nglGetBufferPointervOES, "glGetBufferPointervOES", (void *)&glGetBufferPointervOES, false},
		{"nglMapBufferOES", "(IIJLjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengles_OESMapbuffer_nglMapBufferOES, "glMapBufferOES", (void *)&glMapBufferOES, false},
		{"nglUnmapBufferOES", "(I)Z", (void *)&Java_org_lwjgl_opengles_OESMapbuffer_nglUnmapBufferOES, "glUnmapBufferOES", (void *)&glUnmapBufferOES, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
