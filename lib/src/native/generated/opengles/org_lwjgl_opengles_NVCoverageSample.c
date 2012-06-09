/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glCoverageMaskNVPROC) (GLboolean mask);
typedef GL_APICALL void (GL_APIENTRY *glCoverageOperationNVPROC) (GLenum operation);

static glCoverageMaskNVPROC glCoverageMaskNV;
static glCoverageOperationNVPROC glCoverageOperationNV;

static void JNICALL Java_org_lwjgl_opengles_NVCoverageSample_nglCoverageMaskNV(JNIEnv *env, jclass clazz, jboolean mask) {
	glCoverageMaskNV(mask);
}

static void JNICALL Java_org_lwjgl_opengles_NVCoverageSample_nglCoverageOperationNV(JNIEnv *env, jclass clazz, jint operation) {
	glCoverageOperationNV(operation);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_NVCoverageSample_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglCoverageMaskNV", "(Z)V", (void *)&Java_org_lwjgl_opengles_NVCoverageSample_nglCoverageMaskNV, "glCoverageMaskNV", (void *)&glCoverageMaskNV, false},
		{"nglCoverageOperationNV", "(I)V", (void *)&Java_org_lwjgl_opengles_NVCoverageSample_nglCoverageOperationNV, "glCoverageOperationNV", (void *)&glCoverageOperationNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
