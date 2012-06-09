/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glStartTilingQCOMPROC) (GLuint x, GLuint y, GLuint width, GLuint height, GLbitfield preserveMask);
typedef GL_APICALL void (GL_APIENTRY *glEndTilingQCOMPROC) (GLbitfield preserveMask);

static glStartTilingQCOMPROC glStartTilingQCOM;
static glEndTilingQCOMPROC glEndTilingQCOM;

static void JNICALL Java_org_lwjgl_opengles_QCOMTiledRendering_nglStartTilingQCOM(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint preserveMask) {
	glStartTilingQCOM(x, y, width, height, preserveMask);
}

static void JNICALL Java_org_lwjgl_opengles_QCOMTiledRendering_nglEndTilingQCOM(JNIEnv *env, jclass clazz, jint preserveMask) {
	glEndTilingQCOM(preserveMask);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_QCOMTiledRendering_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglStartTilingQCOM", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_QCOMTiledRendering_nglStartTilingQCOM, "glStartTilingQCOM", (void *)&glStartTilingQCOM, false},
		{"nglEndTilingQCOM", "(I)V", (void *)&Java_org_lwjgl_opengles_QCOMTiledRendering_nglEndTilingQCOM, "glEndTilingQCOM", (void *)&glEndTilingQCOM, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
