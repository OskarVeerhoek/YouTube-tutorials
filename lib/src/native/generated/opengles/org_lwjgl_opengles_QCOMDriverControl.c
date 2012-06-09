/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glGetDriverControlsQCOMPROC) (GLint * num, GLsizei size, GLuint * driverControls);
typedef GL_APICALL void (GL_APIENTRY *glGetDriverControlStringQCOMPROC) (GLuint driverControl, GLsizei bufSize, GLsizei * length, GLchar * driverControlString);
typedef GL_APICALL void (GL_APIENTRY *glEnableDriverControlQCOMPROC) (GLuint driverControl);
typedef GL_APICALL void (GL_APIENTRY *glDisableDriverControlQCOMPROC) (GLuint driverControl);

static glGetDriverControlsQCOMPROC glGetDriverControlsQCOM;
static glGetDriverControlStringQCOMPROC glGetDriverControlStringQCOM;
static glEnableDriverControlQCOMPROC glEnableDriverControlQCOM;
static glDisableDriverControlQCOMPROC glDisableDriverControlQCOM;

static void JNICALL Java_org_lwjgl_opengles_QCOMDriverControl_nglGetDriverControlsQCOM(JNIEnv *env, jclass clazz, jlong num, jint size, jlong driverControls) {
	GLint *num_address = (GLint *)(intptr_t)num;
	GLuint *driverControls_address = (GLuint *)(intptr_t)driverControls;
	glGetDriverControlsQCOM(num_address, size, driverControls_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMDriverControl_nglGetDriverControlStringQCOM(JNIEnv *env, jclass clazz, jint driverControl, jint bufSize, jlong length, jlong driverControlString) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *driverControlString_address = (GLchar *)(intptr_t)driverControlString;
	glGetDriverControlStringQCOM(driverControl, bufSize, length_address, driverControlString_address);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMDriverControl_nglEnableDriverControlQCOM(JNIEnv *env, jclass clazz, jint driverControl) {
	glEnableDriverControlQCOM(driverControl);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMDriverControl_nglDisableDriverControlQCOM(JNIEnv *env, jclass clazz, jint driverControl) {
	glDisableDriverControlQCOM(driverControl);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_QCOMDriverControl_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetDriverControlsQCOM", "(JIJ)V", (void *)&Java_org_lwjgl_opengles_QCOMDriverControl_nglGetDriverControlsQCOM, "glGetDriverControlsQCOM", (void *)&glGetDriverControlsQCOM, false},
		{"nglGetDriverControlStringQCOM", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_QCOMDriverControl_nglGetDriverControlStringQCOM, "glGetDriverControlStringQCOM", (void *)&glGetDriverControlStringQCOM, false},
		{"nglEnableDriverControlQCOM", "(I)V", (void *)&Java_org_lwjgl_opengles_QCOMDriverControl_nglEnableDriverControlQCOM, "glEnableDriverControlQCOM", (void *)&glEnableDriverControlQCOM, false},
		{"nglDisableDriverControlQCOM", "(I)V", (void *)&Java_org_lwjgl_opengles_QCOMDriverControl_nglDisableDriverControlQCOM, "glDisableDriverControlQCOM", (void *)&glDisableDriverControlQCOM, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
