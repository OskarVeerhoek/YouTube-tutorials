/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glUseProgramStagesEXTPROC) (GLuint pipeline, GLbitfield stages, GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glActiveShaderProgramEXTPROC) (GLuint pipeline, GLuint program);
typedef GL_APICALL GLuint (GL_APIENTRY *glCreateShaderProgramvEXTPROC) (GLenum type, GLsizei count, const GLchar ** string);
typedef GL_APICALL void (GL_APIENTRY *glBindProgramPipelineEXTPROC) (GLuint pipeline);
typedef GL_APICALL void (GL_APIENTRY *glDeleteProgramPipelinesEXTPROC) (GLsizei n, const GLuint * pipelines);
typedef GL_APICALL void (GL_APIENTRY *glGenProgramPipelinesEXTPROC) (GLsizei n, GLuint * pipelines);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsProgramPipelineEXTPROC) (GLuint pipeline);
typedef GL_APICALL void (GL_APIENTRY *glProgramParameteriEXTPROC) (GLuint program, GLenum pname, GLint value);
typedef GL_APICALL void (GL_APIENTRY *glGetProgramPipelineivEXTPROC) (GLuint pipeline, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform1iEXTPROC) (GLuint program, GLint location, GLint v0);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform2iEXTPROC) (GLuint program, GLint location, GLint v0, GLint v1);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform3iEXTPROC) (GLuint program, GLint location, GLint v0, GLint v1, GLint v2);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform4iEXTPROC) (GLuint program, GLint location, GLint v0, GLint v1, GLint v2, GLint v3);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform1fEXTPROC) (GLuint program, GLint location, GLfloat v0);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform2fEXTPROC) (GLuint program, GLint location, GLfloat v0, GLfloat v1);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform3fEXTPROC) (GLuint program, GLint location, GLfloat v0, GLfloat v1, GLfloat v2);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform4fEXTPROC) (GLuint program, GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform1ivEXTPROC) (GLuint program, GLint location, GLsizei count, const GLint * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform2ivEXTPROC) (GLuint program, GLint location, GLsizei count, const GLint * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform3ivEXTPROC) (GLuint program, GLint location, GLsizei count, const GLint * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform4ivEXTPROC) (GLuint program, GLint location, GLsizei count, const GLint * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform1fvEXTPROC) (GLuint program, GLint location, GLsizei count, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform2fvEXTPROC) (GLuint program, GLint location, GLsizei count, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform3fvEXTPROC) (GLuint program, GLint location, GLsizei count, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniform4fvEXTPROC) (GLuint program, GLint location, GLsizei count, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniformMatrix2fvEXTPROC) (GLuint program, GLint location, GLsizei count, GLboolean transpose, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniformMatrix3fvEXTPROC) (GLuint program, GLint location, GLsizei count, GLboolean transpose, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glProgramUniformMatrix4fvEXTPROC) (GLuint program, GLint location, GLsizei count, GLboolean transpose, const GLfloat * value);
typedef GL_APICALL void (GL_APIENTRY *glValidateProgramPipelineEXTPROC) (GLuint pipeline);
typedef GL_APICALL void (GL_APIENTRY *glGetProgramPipelineInfoLogEXTPROC) (GLuint pipeline, GLsizei bufSize, GLsizei * length, GLchar * infoLog);

static glUseProgramStagesEXTPROC glUseProgramStagesEXT;
static glActiveShaderProgramEXTPROC glActiveShaderProgramEXT;
static glCreateShaderProgramvEXTPROC glCreateShaderProgramvEXT;
static glBindProgramPipelineEXTPROC glBindProgramPipelineEXT;
static glDeleteProgramPipelinesEXTPROC glDeleteProgramPipelinesEXT;
static glGenProgramPipelinesEXTPROC glGenProgramPipelinesEXT;
static glIsProgramPipelineEXTPROC glIsProgramPipelineEXT;
static glProgramParameteriEXTPROC glProgramParameteriEXT;
static glGetProgramPipelineivEXTPROC glGetProgramPipelineivEXT;
static glProgramUniform1iEXTPROC glProgramUniform1iEXT;
static glProgramUniform2iEXTPROC glProgramUniform2iEXT;
static glProgramUniform3iEXTPROC glProgramUniform3iEXT;
static glProgramUniform4iEXTPROC glProgramUniform4iEXT;
static glProgramUniform1fEXTPROC glProgramUniform1fEXT;
static glProgramUniform2fEXTPROC glProgramUniform2fEXT;
static glProgramUniform3fEXTPROC glProgramUniform3fEXT;
static glProgramUniform4fEXTPROC glProgramUniform4fEXT;
static glProgramUniform1ivEXTPROC glProgramUniform1ivEXT;
static glProgramUniform2ivEXTPROC glProgramUniform2ivEXT;
static glProgramUniform3ivEXTPROC glProgramUniform3ivEXT;
static glProgramUniform4ivEXTPROC glProgramUniform4ivEXT;
static glProgramUniform1fvEXTPROC glProgramUniform1fvEXT;
static glProgramUniform2fvEXTPROC glProgramUniform2fvEXT;
static glProgramUniform3fvEXTPROC glProgramUniform3fvEXT;
static glProgramUniform4fvEXTPROC glProgramUniform4fvEXT;
static glProgramUniformMatrix2fvEXTPROC glProgramUniformMatrix2fvEXT;
static glProgramUniformMatrix3fvEXTPROC glProgramUniformMatrix3fvEXT;
static glProgramUniformMatrix4fvEXTPROC glProgramUniformMatrix4fvEXT;
static glValidateProgramPipelineEXTPROC glValidateProgramPipelineEXT;
static glGetProgramPipelineInfoLogEXTPROC glGetProgramPipelineInfoLogEXT;

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglUseProgramStagesEXT(JNIEnv *env, jclass clazz, jint pipeline, jint stages, jint program) {
	glUseProgramStagesEXT(pipeline, stages, program);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglActiveShaderProgramEXT(JNIEnv *env, jclass clazz, jint pipeline, jint program) {
	glActiveShaderProgramEXT(pipeline, program);
}

static jint JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT(JNIEnv *env, jclass clazz, jint type, jint count, jlong string) {
	const GLchar *string_address = (const GLchar *)(intptr_t)string;
	GLuint __result = glCreateShaderProgramvEXT(type, count, (const GLchar **)&string_address);
	return __result;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT2(JNIEnv *env, jclass clazz, jint type, jint count, jlong strings) {
	const GLchar *strings_address = (const GLchar *)(intptr_t)strings;
	unsigned int _str_i;
	GLchar *_str_address;
	GLchar **strings_str = (GLchar **) malloc(count * sizeof(GLchar *));
	GLuint __result;
	_str_i = 0;
	_str_address = (GLchar *)strings_address;
	while ( _str_i < count ) {
		strings_str[_str_i++] = _str_address;
		_str_address += strlen(_str_address) + 1;
	}
	__result = glCreateShaderProgramvEXT(type, count, (const GLchar **)&strings_str);
	free(strings_str);
	return __result;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT3(JNIEnv *env, jclass clazz, jint type, jint count, jobjectArray strings) {
	unsigned int _ptr_i;
	jobject _ptr_object;
	GLchar **strings_ptr = (GLchar **) malloc(count * sizeof(GLchar *));
	GLuint __result;
	_ptr_i = 0;
	while ( _ptr_i < count ) {
		_ptr_object = (*env)->GetObjectArrayElement(env, strings, _ptr_i);
		strings_ptr[_ptr_i++] = (GLchar *)(intptr_t)getPointerWrapperAddress(env, _ptr_object);
	}
	__result = glCreateShaderProgramvEXT(type, count, (const GLchar **)strings_ptr);
	free(strings_ptr);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglBindProgramPipelineEXT(JNIEnv *env, jclass clazz, jint pipeline) {
	glBindProgramPipelineEXT(pipeline);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglDeleteProgramPipelinesEXT(JNIEnv *env, jclass clazz, jint n, jlong pipelines) {
	const GLuint *pipelines_address = (const GLuint *)(intptr_t)pipelines;
	glDeleteProgramPipelinesEXT(n, pipelines_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGenProgramPipelinesEXT(JNIEnv *env, jclass clazz, jint n, jlong pipelines) {
	GLuint *pipelines_address = (GLuint *)(intptr_t)pipelines;
	glGenProgramPipelinesEXT(n, pipelines_address);
}

static jboolean JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglIsProgramPipelineEXT(JNIEnv *env, jclass clazz, jint pipeline) {
	GLboolean __result = glIsProgramPipelineEXT(pipeline);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramParameteriEXT(JNIEnv *env, jclass clazz, jint program, jint pname, jint value) {
	glProgramParameteriEXT(program, pname, value);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGetProgramPipelineivEXT(JNIEnv *env, jclass clazz, jint pipeline, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetProgramPipelineivEXT(pipeline, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1iEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint v0) {
	glProgramUniform1iEXT(program, location, v0);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2iEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint v0, jint v1) {
	glProgramUniform2iEXT(program, location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3iEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint v0, jint v1, jint v2) {
	glProgramUniform3iEXT(program, location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4iEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint v0, jint v1, jint v2, jint v3) {
	glProgramUniform4iEXT(program, location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1fEXT(JNIEnv *env, jclass clazz, jint program, jint location, jfloat v0) {
	glProgramUniform1fEXT(program, location, v0);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2fEXT(JNIEnv *env, jclass clazz, jint program, jint location, jfloat v0, jfloat v1) {
	glProgramUniform2fEXT(program, location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3fEXT(JNIEnv *env, jclass clazz, jint program, jint location, jfloat v0, jfloat v1, jfloat v2) {
	glProgramUniform3fEXT(program, location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4fEXT(JNIEnv *env, jclass clazz, jint program, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3) {
	glProgramUniform4fEXT(program, location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1ivEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLint *value_address = (const GLint *)(intptr_t)value;
	glProgramUniform1ivEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2ivEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLint *value_address = (const GLint *)(intptr_t)value;
	glProgramUniform2ivEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3ivEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLint *value_address = (const GLint *)(intptr_t)value;
	glProgramUniform3ivEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4ivEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLint *value_address = (const GLint *)(intptr_t)value;
	glProgramUniform4ivEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniform1fvEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniform2fvEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniform3fvEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniform4fvEXT(program, location, count, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix2fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jboolean transpose, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniformMatrix2fvEXT(program, location, count, transpose, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix3fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jboolean transpose, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniformMatrix3fvEXT(program, location, count, transpose, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix4fvEXT(JNIEnv *env, jclass clazz, jint program, jint location, jint count, jboolean transpose, jlong value) {
	const GLfloat *value_address = (const GLfloat *)(intptr_t)value;
	glProgramUniformMatrix4fvEXT(program, location, count, transpose, value_address);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglValidateProgramPipelineEXT(JNIEnv *env, jclass clazz, jint pipeline) {
	glValidateProgramPipelineEXT(pipeline);
}

static void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGetProgramPipelineInfoLogEXT(JNIEnv *env, jclass clazz, jint pipeline, jint bufSize, jlong length, jlong infoLog) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *infoLog_address = (GLchar *)(intptr_t)infoLog;
	glGetProgramPipelineInfoLogEXT(pipeline, bufSize, length_address, infoLog_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EXTSeparateShaderObjects_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglUseProgramStagesEXT", "(III)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglUseProgramStagesEXT, "glUseProgramStagesEXT", (void *)&glUseProgramStagesEXT, false},
		{"nglActiveShaderProgramEXT", "(II)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglActiveShaderProgramEXT, "glActiveShaderProgramEXT", (void *)&glActiveShaderProgramEXT, false},
		{"nglCreateShaderProgramvEXT", "(IIJ)I", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT, "glCreateShaderProgramvEXT", (void *)&glCreateShaderProgramvEXT, false},
		{"nglCreateShaderProgramvEXT2", "(IIJ)I", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT2, "glCreateShaderProgramvEXT", (void *)&glCreateShaderProgramvEXT, false},
		{"nglCreateShaderProgramvEXT3", "(II[Ljava/nio/ByteBuffer;)I", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglCreateShaderProgramvEXT3, "glCreateShaderProgramvEXT", (void *)&glCreateShaderProgramvEXT, false},
		{"nglBindProgramPipelineEXT", "(I)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglBindProgramPipelineEXT, "glBindProgramPipelineEXT", (void *)&glBindProgramPipelineEXT, false},
		{"nglDeleteProgramPipelinesEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglDeleteProgramPipelinesEXT, "glDeleteProgramPipelinesEXT", (void *)&glDeleteProgramPipelinesEXT, false},
		{"nglGenProgramPipelinesEXT", "(IJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGenProgramPipelinesEXT, "glGenProgramPipelinesEXT", (void *)&glGenProgramPipelinesEXT, false},
		{"nglIsProgramPipelineEXT", "(I)Z", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglIsProgramPipelineEXT, "glIsProgramPipelineEXT", (void *)&glIsProgramPipelineEXT, false},
		{"nglProgramParameteriEXT", "(III)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramParameteriEXT, "glProgramParameteriEXT", (void *)&glProgramParameteriEXT, false},
		{"nglGetProgramPipelineivEXT", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGetProgramPipelineivEXT, "glGetProgramPipelineivEXT", (void *)&glGetProgramPipelineivEXT, false},
		{"nglProgramUniform1iEXT", "(III)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1iEXT, "glProgramUniform1iEXT", (void *)&glProgramUniform1iEXT, false},
		{"nglProgramUniform2iEXT", "(IIII)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2iEXT, "glProgramUniform2iEXT", (void *)&glProgramUniform2iEXT, false},
		{"nglProgramUniform3iEXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3iEXT, "glProgramUniform3iEXT", (void *)&glProgramUniform3iEXT, false},
		{"nglProgramUniform4iEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4iEXT, "glProgramUniform4iEXT", (void *)&glProgramUniform4iEXT, false},
		{"nglProgramUniform1fEXT", "(IIF)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1fEXT, "glProgramUniform1fEXT", (void *)&glProgramUniform1fEXT, false},
		{"nglProgramUniform2fEXT", "(IIFF)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2fEXT, "glProgramUniform2fEXT", (void *)&glProgramUniform2fEXT, false},
		{"nglProgramUniform3fEXT", "(IIFFF)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3fEXT, "glProgramUniform3fEXT", (void *)&glProgramUniform3fEXT, false},
		{"nglProgramUniform4fEXT", "(IIFFFF)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4fEXT, "glProgramUniform4fEXT", (void *)&glProgramUniform4fEXT, false},
		{"nglProgramUniform1ivEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1ivEXT, "glProgramUniform1ivEXT", (void *)&glProgramUniform1ivEXT, false},
		{"nglProgramUniform2ivEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2ivEXT, "glProgramUniform2ivEXT", (void *)&glProgramUniform2ivEXT, false},
		{"nglProgramUniform3ivEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3ivEXT, "glProgramUniform3ivEXT", (void *)&glProgramUniform3ivEXT, false},
		{"nglProgramUniform4ivEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4ivEXT, "glProgramUniform4ivEXT", (void *)&glProgramUniform4ivEXT, false},
		{"nglProgramUniform1fvEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform1fvEXT, "glProgramUniform1fvEXT", (void *)&glProgramUniform1fvEXT, false},
		{"nglProgramUniform2fvEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform2fvEXT, "glProgramUniform2fvEXT", (void *)&glProgramUniform2fvEXT, false},
		{"nglProgramUniform3fvEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform3fvEXT, "glProgramUniform3fvEXT", (void *)&glProgramUniform3fvEXT, false},
		{"nglProgramUniform4fvEXT", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniform4fvEXT, "glProgramUniform4fvEXT", (void *)&glProgramUniform4fvEXT, false},
		{"nglProgramUniformMatrix2fvEXT", "(IIIZJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix2fvEXT, "glProgramUniformMatrix2fvEXT", (void *)&glProgramUniformMatrix2fvEXT, false},
		{"nglProgramUniformMatrix3fvEXT", "(IIIZJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix3fvEXT, "glProgramUniformMatrix3fvEXT", (void *)&glProgramUniformMatrix3fvEXT, false},
		{"nglProgramUniformMatrix4fvEXT", "(IIIZJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglProgramUniformMatrix4fvEXT, "glProgramUniformMatrix4fvEXT", (void *)&glProgramUniformMatrix4fvEXT, false},
		{"nglValidateProgramPipelineEXT", "(I)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglValidateProgramPipelineEXT, "glValidateProgramPipelineEXT", (void *)&glValidateProgramPipelineEXT, false},
		{"nglGetProgramPipelineInfoLogEXT", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_EXTSeparateShaderObjects_nglGetProgramPipelineInfoLogEXT, "glGetProgramPipelineInfoLogEXT", (void *)&glGetProgramPipelineInfoLogEXT, false},

	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
