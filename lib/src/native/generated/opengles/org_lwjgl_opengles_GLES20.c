/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GL_APICALL void (GL_APIENTRY *glActiveTexturePROC) (GLenum texture);
typedef GL_APICALL void (GL_APIENTRY *glAttachShaderPROC) (GLuint program, GLuint shader);
typedef GL_APICALL void (GL_APIENTRY *glBindAttribLocationPROC) (GLuint program, GLuint index, const GLchar * name);
typedef GL_APICALL void (GL_APIENTRY *glBindBufferPROC) (GLenum target, GLuint buffer);
typedef GL_APICALL void (GL_APIENTRY *glBindFramebufferPROC) (GLenum target, GLuint framebuffer);
typedef GL_APICALL void (GL_APIENTRY *glBindRenderbufferPROC) (GLenum target, GLuint renderbuffer);
typedef GL_APICALL void (GL_APIENTRY *glBindTexturePROC) (GLenum target, GLuint texture);
typedef GL_APICALL void (GL_APIENTRY *glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef GL_APICALL void (GL_APIENTRY *glBlendEquationPROC) (GLenum mode);
typedef GL_APICALL void (GL_APIENTRY *glBlendEquationSeparatePROC) (GLenum modeRGB, GLenum modeAlpha);
typedef GL_APICALL void (GL_APIENTRY *glBlendFuncPROC) (GLenum sfactor, GLenum dfactor);
typedef GL_APICALL void (GL_APIENTRY *glBlendFuncSeparatePROC) (GLenum srcRGB, GLenum dstRGB, GLenum srcAlpha, GLenum dstAlpha);
typedef GL_APICALL void (GL_APIENTRY *glBufferDataPROC) (GLenum target, GLsizeiptr size, const GLvoid * data, GLenum usage);
typedef GL_APICALL void (GL_APIENTRY *glBufferSubDataPROC) (GLenum target, GLintptr offset, GLsizeiptr size, const GLvoid * data);
typedef GL_APICALL GLenum (GL_APIENTRY *glCheckFramebufferStatusPROC) (GLenum target);
typedef GL_APICALL void (GL_APIENTRY *glClearPROC) (GLbitfield mask);
typedef GL_APICALL void (GL_APIENTRY *glClearColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef GL_APICALL void (GL_APIENTRY *glClearDepthfPROC) (GLclampf depth);
typedef GL_APICALL void (GL_APIENTRY *glClearStencilPROC) (GLint s);
typedef GL_APICALL void (GL_APIENTRY *glColorMaskPROC) (GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha);
typedef GL_APICALL void (GL_APIENTRY *glCompileShaderPROC) (GLuint shader);
typedef GL_APICALL void (GL_APIENTRY *glCompressedTexImage2DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glCompressedTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid * data);
typedef GL_APICALL void (GL_APIENTRY *glCopyTexImage2DPROC) (GLenum target, GLint level, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border);
typedef GL_APICALL void (GL_APIENTRY *glCopyTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef GL_APICALL GLuint (GL_APIENTRY *glCreateProgramPROC) ();
typedef GL_APICALL GLuint (GL_APIENTRY *glCreateShaderPROC) (GLenum type);
typedef GL_APICALL void (GL_APIENTRY *glCullFacePROC) (GLenum mode);
typedef GL_APICALL void (GL_APIENTRY *glDeleteBuffersPROC) (GLsizei n, const GLuint * buffers);
typedef GL_APICALL void (GL_APIENTRY *glDeleteFramebuffersPROC) (GLint n, const GLuint * framebuffers);
typedef GL_APICALL void (GL_APIENTRY *glDeleteProgramPROC) (GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glDeleteRenderbuffersPROC) (GLint n, const GLuint * renderbuffers);
typedef GL_APICALL void (GL_APIENTRY *glDeleteShaderPROC) (GLuint shader);
typedef GL_APICALL void (GL_APIENTRY *glDeleteTexturesPROC) (GLsizei n, const GLuint * textures);
typedef GL_APICALL void (GL_APIENTRY *glDepthFuncPROC) (GLenum func);
typedef GL_APICALL void (GL_APIENTRY *glDepthMaskPROC) (GLboolean flag);
typedef GL_APICALL void (GL_APIENTRY *glDepthRangefPROC) (GLclampf zNear, GLclampf zFar);
typedef GL_APICALL void (GL_APIENTRY *glDetachShaderPROC) (GLuint program, GLuint shader);
typedef GL_APICALL void (GL_APIENTRY *glDisablePROC) (GLenum cap);
typedef GL_APICALL void (GL_APIENTRY *glDisableVertexAttribArrayPROC) (GLuint index);
typedef GL_APICALL void (GL_APIENTRY *glDrawArraysPROC) (GLenum mode, GLint first, GLsizei count);
typedef GL_APICALL void (GL_APIENTRY *glDrawElementsPROC) (GLenum mode, GLsizei count, GLenum type, const GLvoid * indices);
typedef GL_APICALL void (GL_APIENTRY *glEnablePROC) (GLenum cap);
typedef GL_APICALL void (GL_APIENTRY *glEnableVertexAttribArrayPROC) (GLuint index);
typedef GL_APICALL void (GL_APIENTRY *glFinishPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glFlushPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glFramebufferRenderbufferPROC) (GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer);
typedef GL_APICALL void (GL_APIENTRY *glFramebufferTexture2DPROC) (GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level);
typedef GL_APICALL void (GL_APIENTRY *glFrontFacePROC) (GLenum mode);
typedef GL_APICALL void (GL_APIENTRY *glGenBuffersPROC) (GLsizei n, GLuint * buffers);
typedef GL_APICALL void (GL_APIENTRY *glGenerateMipmapPROC) (GLenum target);
typedef GL_APICALL void (GL_APIENTRY *glGenFramebuffersPROC) (GLint n, GLuint * framebuffers);
typedef GL_APICALL void (GL_APIENTRY *glGenRenderbuffersPROC) (GLint n, GLuint * renderbuffers);
typedef GL_APICALL void (GL_APIENTRY *glGenTexturesPROC) (GLsizei n, GLuint * textures);
typedef GL_APICALL void (GL_APIENTRY *glGetActiveAttribPROC) (GLuint program, GLuint index, GLsizei bufsize, GLsizei * length, GLint * size, GLenum * type, GLchar * name);
typedef GL_APICALL void (GL_APIENTRY *glGetActiveUniformPROC) (GLuint program, GLuint index, GLsizei bufsize, GLsizei * length, GLsizei * size, GLenum * type, GLchar * name);
typedef GL_APICALL void (GL_APIENTRY *glGetAttachedShadersPROC) (GLuint program, GLsizei maxCount, GLsizei * count, GLuint * shaders);
typedef GL_APICALL GLint (GL_APIENTRY *glGetAttribLocationPROC) (GLuint program, const GLchar * name);
typedef GL_APICALL void (GL_APIENTRY *glGetBooleanvPROC) (GLenum pname, GLboolean * params);
typedef GL_APICALL void (GL_APIENTRY *glGetBufferParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef GL_APICALL GLenum (GL_APIENTRY *glGetErrorPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glGetFloatvPROC) (GLenum pname, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetFramebufferAttachmentParameterivPROC) (GLenum target, GLenum attachment, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetIntegervPROC) (GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetProgramivPROC) (GLuint program, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetProgramInfoLogPROC) (GLuint program, GLsizei bufsize, GLsizei * length, GLchar * infoLog);
typedef GL_APICALL void (GL_APIENTRY *glGetRenderbufferParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetShaderivPROC) (GLuint shader, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetShaderInfoLogPROC) (GLuint shader, GLsizei bufsize, GLsizei * length, GLchar * infoLog);
typedef GL_APICALL void (GL_APIENTRY *glGetShaderPrecisionFormatPROC) (GLenum shadertype, GLenum precisiontype, GLint * range, GLint * precision);
typedef GL_APICALL void (GL_APIENTRY *glGetShaderSourcePROC) (GLuint shader, GLsizei bufsize, GLsizei * length, GLchar * source);
typedef GL_APICALL const GLubyte * (GL_APIENTRY *glGetStringPROC) (GLenum name);
typedef GL_APICALL void (GL_APIENTRY *glGetTexParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetTexParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetUniformfvPROC) (GLuint program, GLint location, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetUniformivPROC) (GLuint program, GLint location, GLint * params);
typedef GL_APICALL GLint (GL_APIENTRY *glGetUniformLocationPROC) (GLuint program, const GLchar * name);
typedef GL_APICALL void (GL_APIENTRY *glGetVertexAttribfvPROC) (GLuint index, GLenum pname, GLfloat * params);
typedef GL_APICALL void (GL_APIENTRY *glGetVertexAttribivPROC) (GLuint index, GLenum pname, GLint * params);
typedef GL_APICALL void (GL_APIENTRY *glGetVertexAttribPointervPROC) (GLuint index, GLenum pname, GLvoid ** pointer);
typedef GL_APICALL void (GL_APIENTRY *glHintPROC) (GLenum target, GLenum mode);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsBufferPROC) (GLuint buffer);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsEnabledPROC) (GLenum cap);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsFramebufferPROC) (GLuint framebuffer);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsProgramPROC) (GLuint program);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsRenderbufferPROC) (GLuint renderbuffer);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsShaderPROC) (GLuint shader);
typedef GL_APICALL GLboolean (GL_APIENTRY *glIsTexturePROC) (GLuint texture);
typedef GL_APICALL void (GL_APIENTRY *glLineWidthPROC) (GLfloat width);
typedef GL_APICALL void (GL_APIENTRY *glLinkProgramPROC) (GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glPixelStoreiPROC) (GLenum pname, GLint param);
typedef GL_APICALL void (GL_APIENTRY *glPolygonOffsetPROC) (GLfloat factor, GLfloat units);
typedef GL_APICALL void (GL_APIENTRY *glReadPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid * pixels);
typedef GL_APICALL void (GL_APIENTRY *glReleaseShaderCompilerPROC) ();
typedef GL_APICALL void (GL_APIENTRY *glRenderbufferStoragePROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glSampleCoveragePROC) (GLclampf value, GLboolean invert);
typedef GL_APICALL void (GL_APIENTRY *glScissorPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef GL_APICALL void (GL_APIENTRY *glShaderBinaryPROC) (GLsizei n, const GLuint * shaders, GLenum binaryformat, const GLvoid * binary, GLsizei length);
typedef GL_APICALL void (GL_APIENTRY *glShaderSourcePROC) (GLuint shader, GLsizei count, const GLchar ** string, const GLint* length);
typedef GL_APICALL void (GL_APIENTRY *glStencilFuncPROC) (GLenum func, GLint ref, GLuint mask);
typedef GL_APICALL void (GL_APIENTRY *glStencilFuncSeparatePROC) (GLenum face, GLenum func, GLint ref, GLuint mask);
typedef GL_APICALL void (GL_APIENTRY *glStencilMaskPROC) (GLuint mask);
typedef GL_APICALL void (GL_APIENTRY *glStencilMaskSeparatePROC) (GLenum face, GLuint mask);
typedef GL_APICALL void (GL_APIENTRY *glStencilOpPROC) (GLenum fail, GLenum zfail, GLenum zpass);
typedef GL_APICALL void (GL_APIENTRY *glStencilOpSeparatePROC) (GLenum face, GLenum fail, GLenum zfail, GLenum zpass);
typedef GL_APICALL void (GL_APIENTRY *glTexImage2DPROC) (GLenum target, GLint level, GLint internalformat, GLint width, GLint height, GLint border, GLenum format, GLenum type, const GLvoid * pixels);
typedef GL_APICALL void (GL_APIENTRY *glTexParameterfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef GL_APICALL void (GL_APIENTRY *glTexParameterfvPROC) (GLenum target, GLenum pname, const GLfloat * param);
typedef GL_APICALL void (GL_APIENTRY *glTexParameteriPROC) (GLenum target, GLenum pname, GLint param);
typedef GL_APICALL void (GL_APIENTRY *glTexParameterivPROC) (GLenum target, GLenum pname, const GLint * param);
typedef GL_APICALL void (GL_APIENTRY *glTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid * pixels);
typedef GL_APICALL void (GL_APIENTRY *glUniform1fPROC) (GLint location, GLfloat x);
typedef GL_APICALL void (GL_APIENTRY *glUniform1fvPROC) (GLint location, GLsizei count, const GLfloat * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform1iPROC) (GLint location, GLint x);
typedef GL_APICALL void (GL_APIENTRY *glUniform1ivPROC) (GLint location, GLsizei count, const GLint * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform2fPROC) (GLint location, GLfloat x, GLfloat y);
typedef GL_APICALL void (GL_APIENTRY *glUniform2fvPROC) (GLint location, GLsizei count, const GLfloat * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform2iPROC) (GLint location, GLint x, GLint y);
typedef GL_APICALL void (GL_APIENTRY *glUniform2ivPROC) (GLint location, GLsizei count, const GLint * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform3fPROC) (GLint location, GLfloat x, GLfloat y, GLfloat z);
typedef GL_APICALL void (GL_APIENTRY *glUniform3fvPROC) (GLint location, GLsizei count, const GLfloat * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform3iPROC) (GLint location, GLint x, GLint y, GLint z);
typedef GL_APICALL void (GL_APIENTRY *glUniform3ivPROC) (GLint location, GLsizei count, const GLint * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform4fPROC) (GLint location, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef GL_APICALL void (GL_APIENTRY *glUniform4fvPROC) (GLint location, GLsizei count, const GLfloat * v);
typedef GL_APICALL void (GL_APIENTRY *glUniform4iPROC) (GLint location, GLint x, GLint y, GLint z, GLint w);
typedef GL_APICALL void (GL_APIENTRY *glUniform4ivPROC) (GLint location, GLsizei count, const GLint * v);
typedef GL_APICALL void (GL_APIENTRY *glUniformMatrix2fvPROC) (GLint location, GLsizei count, GLboolean transpose, const GLfloat * matrices);
typedef GL_APICALL void (GL_APIENTRY *glUniformMatrix3fvPROC) (GLint location, GLsizei count, GLboolean transpose, const GLfloat * matrices);
typedef GL_APICALL void (GL_APIENTRY *glUniformMatrix4fvPROC) (GLint location, GLsizei count, GLboolean transpose, const GLfloat * matrices);
typedef GL_APICALL void (GL_APIENTRY *glUseProgramPROC) (GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glValidateProgramPROC) (GLuint program);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib1fPROC) (GLuint indx, GLfloat x);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib1fvPROC) (GLuint indx, const GLfloat * values);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib2fPROC) (GLuint indx, GLfloat x, GLfloat y);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib2fvPROC) (GLuint indx, const GLfloat * values);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib3fPROC) (GLuint indx, GLfloat x, GLfloat y, GLfloat z);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib3fvPROC) (GLuint indx, const GLfloat * values);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib4fPROC) (GLuint indx, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttrib4fvPROC) (GLuint indx, const GLfloat * values);
typedef GL_APICALL void (GL_APIENTRY *glVertexAttribPointerPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid * buffer);
typedef GL_APICALL void (GL_APIENTRY *glViewportPROC) (GLint x, GLint y, GLsizei width, GLsizei height);

static glActiveTexturePROC glActiveTexture;
static glAttachShaderPROC glAttachShader;
static glBindAttribLocationPROC glBindAttribLocation;
static glBindBufferPROC glBindBuffer;
static glBindFramebufferPROC glBindFramebuffer;
static glBindRenderbufferPROC glBindRenderbuffer;
static glBindTexturePROC glBindTexture;
static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;
static glBlendEquationSeparatePROC glBlendEquationSeparate;
static glBlendFuncPROC glBlendFunc;
static glBlendFuncSeparatePROC glBlendFuncSeparate;
static glBufferDataPROC glBufferData;
static glBufferSubDataPROC glBufferSubData;
static glCheckFramebufferStatusPROC glCheckFramebufferStatus;
static glClearPROC glClear;
static glClearColorPROC glClearColor;
static glClearDepthfPROC glClearDepthf;
static glClearStencilPROC glClearStencil;
static glColorMaskPROC glColorMask;
static glCompileShaderPROC glCompileShader;
static glCompressedTexImage2DPROC glCompressedTexImage2D;
static glCompressedTexSubImage2DPROC glCompressedTexSubImage2D;
static glCopyTexImage2DPROC glCopyTexImage2D;
static glCopyTexSubImage2DPROC glCopyTexSubImage2D;
static glCreateProgramPROC glCreateProgram;
static glCreateShaderPROC glCreateShader;
static glCullFacePROC glCullFace;
static glDeleteBuffersPROC glDeleteBuffers;
static glDeleteFramebuffersPROC glDeleteFramebuffers;
static glDeleteProgramPROC glDeleteProgram;
static glDeleteRenderbuffersPROC glDeleteRenderbuffers;
static glDeleteShaderPROC glDeleteShader;
static glDeleteTexturesPROC glDeleteTextures;
static glDepthFuncPROC glDepthFunc;
static glDepthMaskPROC glDepthMask;
static glDepthRangefPROC glDepthRangef;
static glDetachShaderPROC glDetachShader;
static glDisablePROC glDisable;
static glDisableVertexAttribArrayPROC glDisableVertexAttribArray;
static glDrawArraysPROC glDrawArrays;
static glDrawElementsPROC glDrawElements;
static glEnablePROC glEnable;
static glEnableVertexAttribArrayPROC glEnableVertexAttribArray;
static glFinishPROC glFinish;
static glFlushPROC glFlush;
static glFramebufferRenderbufferPROC glFramebufferRenderbuffer;
static glFramebufferTexture2DPROC glFramebufferTexture2D;
static glFrontFacePROC glFrontFace;
static glGenBuffersPROC glGenBuffers;
static glGenerateMipmapPROC glGenerateMipmap;
static glGenFramebuffersPROC glGenFramebuffers;
static glGenRenderbuffersPROC glGenRenderbuffers;
static glGenTexturesPROC glGenTextures;
static glGetActiveAttribPROC glGetActiveAttrib;
static glGetActiveUniformPROC glGetActiveUniform;
static glGetAttachedShadersPROC glGetAttachedShaders;
static glGetAttribLocationPROC glGetAttribLocation;
static glGetBooleanvPROC glGetBooleanv;
static glGetBufferParameterivPROC glGetBufferParameteriv;
static glGetErrorPROC glGetError;
static glGetFloatvPROC glGetFloatv;
static glGetFramebufferAttachmentParameterivPROC glGetFramebufferAttachmentParameteriv;
static glGetIntegervPROC glGetIntegerv;
static glGetProgramivPROC glGetProgramiv;
static glGetProgramInfoLogPROC glGetProgramInfoLog;
static glGetRenderbufferParameterivPROC glGetRenderbufferParameteriv;
static glGetShaderivPROC glGetShaderiv;
static glGetShaderInfoLogPROC glGetShaderInfoLog;
static glGetShaderPrecisionFormatPROC glGetShaderPrecisionFormat;
static glGetShaderSourcePROC glGetShaderSource;
static glGetStringPROC glGetString;
static glGetTexParameterfvPROC glGetTexParameterfv;
static glGetTexParameterivPROC glGetTexParameteriv;
static glGetUniformfvPROC glGetUniformfv;
static glGetUniformivPROC glGetUniformiv;
static glGetUniformLocationPROC glGetUniformLocation;
static glGetVertexAttribfvPROC glGetVertexAttribfv;
static glGetVertexAttribivPROC glGetVertexAttribiv;
static glGetVertexAttribPointervPROC glGetVertexAttribPointerv;
static glHintPROC glHint;
static glIsBufferPROC glIsBuffer;
static glIsEnabledPROC glIsEnabled;
static glIsFramebufferPROC glIsFramebuffer;
static glIsProgramPROC glIsProgram;
static glIsRenderbufferPROC glIsRenderbuffer;
static glIsShaderPROC glIsShader;
static glIsTexturePROC glIsTexture;
static glLineWidthPROC glLineWidth;
static glLinkProgramPROC glLinkProgram;
static glPixelStoreiPROC glPixelStorei;
static glPolygonOffsetPROC glPolygonOffset;
static glReadPixelsPROC glReadPixels;
static glReleaseShaderCompilerPROC glReleaseShaderCompiler;
static glRenderbufferStoragePROC glRenderbufferStorage;
static glSampleCoveragePROC glSampleCoverage;
static glScissorPROC glScissor;
static glShaderBinaryPROC glShaderBinary;
static glShaderSourcePROC glShaderSource;
static glStencilFuncPROC glStencilFunc;
static glStencilFuncSeparatePROC glStencilFuncSeparate;
static glStencilMaskPROC glStencilMask;
static glStencilMaskSeparatePROC glStencilMaskSeparate;
static glStencilOpPROC glStencilOp;
static glStencilOpSeparatePROC glStencilOpSeparate;
static glTexImage2DPROC glTexImage2D;
static glTexParameterfPROC glTexParameterf;
static glTexParameterfvPROC glTexParameterfv;
static glTexParameteriPROC glTexParameteri;
static glTexParameterivPROC glTexParameteriv;
static glTexSubImage2DPROC glTexSubImage2D;
static glUniform1fPROC glUniform1f;
static glUniform1fvPROC glUniform1fv;
static glUniform1iPROC glUniform1i;
static glUniform1ivPROC glUniform1iv;
static glUniform2fPROC glUniform2f;
static glUniform2fvPROC glUniform2fv;
static glUniform2iPROC glUniform2i;
static glUniform2ivPROC glUniform2iv;
static glUniform3fPROC glUniform3f;
static glUniform3fvPROC glUniform3fv;
static glUniform3iPROC glUniform3i;
static glUniform3ivPROC glUniform3iv;
static glUniform4fPROC glUniform4f;
static glUniform4fvPROC glUniform4fv;
static glUniform4iPROC glUniform4i;
static glUniform4ivPROC glUniform4iv;
static glUniformMatrix2fvPROC glUniformMatrix2fv;
static glUniformMatrix3fvPROC glUniformMatrix3fv;
static glUniformMatrix4fvPROC glUniformMatrix4fv;
static glUseProgramPROC glUseProgram;
static glValidateProgramPROC glValidateProgram;
static glVertexAttrib1fPROC glVertexAttrib1f;
static glVertexAttrib1fvPROC glVertexAttrib1fv;
static glVertexAttrib2fPROC glVertexAttrib2f;
static glVertexAttrib2fvPROC glVertexAttrib2fv;
static glVertexAttrib3fPROC glVertexAttrib3f;
static glVertexAttrib3fvPROC glVertexAttrib3fv;
static glVertexAttrib4fPROC glVertexAttrib4f;
static glVertexAttrib4fvPROC glVertexAttrib4fv;
static glVertexAttribPointerPROC glVertexAttribPointer;
static glViewportPROC glViewport;

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglActiveTexture(JNIEnv *env, jclass clazz, jint texture) {
	glActiveTexture(texture);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglAttachShader(JNIEnv *env, jclass clazz, jint program, jint shader) {
	glAttachShader(program, shader);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBindAttribLocation(JNIEnv *env, jclass clazz, jint program, jint index, jlong name) {
	const GLchar *name_address = (const GLchar *)(intptr_t)name;
	glBindAttribLocation(program, index, name_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBindBuffer(JNIEnv *env, jclass clazz, jint target, jint buffer) {
	glBindBuffer(target, buffer);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBindFramebuffer(JNIEnv *env, jclass clazz, jint target, jint framebuffer) {
	glBindFramebuffer(target, framebuffer);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBindRenderbuffer(JNIEnv *env, jclass clazz, jint target, jint renderbuffer) {
	glBindRenderbuffer(target, renderbuffer);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBindTexture(JNIEnv *env, jclass clazz, jint target, jint texture) {
	glBindTexture(target, texture);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBlendColor(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glBlendColor(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBlendEquation(JNIEnv *env, jclass clazz, jint mode) {
	glBlendEquation(mode);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBlendEquationSeparate(JNIEnv *env, jclass clazz, jint modeRGB, jint modeAlpha) {
	glBlendEquationSeparate(modeRGB, modeAlpha);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBlendFunc(JNIEnv *env, jclass clazz, jint sfactor, jint dfactor) {
	glBlendFunc(sfactor, dfactor);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBlendFuncSeparate(JNIEnv *env, jclass clazz, jint srcRGB, jint dstRGB, jint srcAlpha, jint dstAlpha) {
	glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBufferData(JNIEnv *env, jclass clazz, jint target, jlong size, jlong data, jint usage) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glBufferData(target, size, data_address, usage);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglBufferSubData(JNIEnv *env, jclass clazz, jint target, jlong offset, jlong size, jlong data) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glBufferSubData(target, offset, size, data_address);
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglCheckFramebufferStatus(JNIEnv *env, jclass clazz, jint target) {
	GLenum __result = glCheckFramebufferStatus(target);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglClear(JNIEnv *env, jclass clazz, jint mask) {
	glClear(mask);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglClearColor(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glClearColor(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglClearDepthf(JNIEnv *env, jclass clazz, jfloat depth) {
	glClearDepthf(depth);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglClearStencil(JNIEnv *env, jclass clazz, jint s) {
	glClearStencil(s);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglColorMask(JNIEnv *env, jclass clazz, jboolean red, jboolean green, jboolean blue, jboolean alpha) {
	glColorMask(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCompileShader(JNIEnv *env, jclass clazz, jint shader) {
	glCompileShader(shader);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCompressedTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imageSize, jlong data) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCompressedTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imageSize, jlong data) {
	const GLvoid *data_address = (const GLvoid *)(intptr_t)data;
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCopyTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint x, jint y, jint width, jint height, jint border) {
	glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCopyTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint x, jint y, jint width, jint height) {
	glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglCreateProgram(JNIEnv *env, jclass clazz) {
	GLuint __result = glCreateProgram();
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglCreateShader(JNIEnv *env, jclass clazz, jint type) {
	GLuint __result = glCreateShader(type);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglCullFace(JNIEnv *env, jclass clazz, jint mode) {
	glCullFace(mode);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteBuffers(JNIEnv *env, jclass clazz, jint n, jlong buffers) {
	const GLuint *buffers_address = (const GLuint *)(intptr_t)buffers;
	glDeleteBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteFramebuffers(JNIEnv *env, jclass clazz, jint n, jlong framebuffers) {
	const GLuint *framebuffers_address = (const GLuint *)(intptr_t)framebuffers;
	glDeleteFramebuffers(n, framebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteProgram(JNIEnv *env, jclass clazz, jint program) {
	glDeleteProgram(program);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteRenderbuffers(JNIEnv *env, jclass clazz, jint n, jlong renderbuffers) {
	const GLuint *renderbuffers_address = (const GLuint *)(intptr_t)renderbuffers;
	glDeleteRenderbuffers(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteShader(JNIEnv *env, jclass clazz, jint shader) {
	glDeleteShader(shader);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDeleteTextures(JNIEnv *env, jclass clazz, jint n, jlong textures) {
	const GLuint *textures_address = (const GLuint *)(intptr_t)textures;
	glDeleteTextures(n, textures_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDepthFunc(JNIEnv *env, jclass clazz, jint func) {
	glDepthFunc(func);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDepthMask(JNIEnv *env, jclass clazz, jboolean flag) {
	glDepthMask(flag);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDepthRangef(JNIEnv *env, jclass clazz, jfloat zNear, jfloat zFar) {
	glDepthRangef(zNear, zFar);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDetachShader(JNIEnv *env, jclass clazz, jint program, jint shader) {
	glDetachShader(program, shader);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDisable(JNIEnv *env, jclass clazz, jint cap) {
	glDisable(cap);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDisableVertexAttribArray(JNIEnv *env, jclass clazz, jint index) {
	glDisableVertexAttribArray(index);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDrawArrays(JNIEnv *env, jclass clazz, jint mode, jint first, jint count) {
	glDrawArrays(mode, first, count);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDrawElements(JNIEnv *env, jclass clazz, jint mode, jint count, jint type, jlong indices) {
	const GLvoid *indices_address = (const GLvoid *)(intptr_t)indices;
	glDrawElements(mode, count, type, indices_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglDrawElementsBO(JNIEnv *env, jclass clazz, jint mode, jint count, jint type, jlong indices_buffer_offset) {
	const GLvoid *indices_address = (const GLvoid *)(intptr_t)offsetToPointer(indices_buffer_offset);
	glDrawElements(mode, count, type, indices_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglEnable(JNIEnv *env, jclass clazz, jint cap) {
	glEnable(cap);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglEnableVertexAttribArray(JNIEnv *env, jclass clazz, jint index) {
	glEnableVertexAttribArray(index);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglFinish(JNIEnv *env, jclass clazz) {
	glFinish();
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglFlush(JNIEnv *env, jclass clazz) {
	glFlush();
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglFramebufferRenderbuffer(JNIEnv *env, jclass clazz, jint target, jint attachment, jint renderbuffertarget, jint renderbuffer) {
	glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglFramebufferTexture2D(JNIEnv *env, jclass clazz, jint target, jint attachment, jint textarget, jint texture, jint level) {
	glFramebufferTexture2D(target, attachment, textarget, texture, level);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglFrontFace(JNIEnv *env, jclass clazz, jint mode) {
	glFrontFace(mode);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGenBuffers(JNIEnv *env, jclass clazz, jint n, jlong buffers) {
	GLuint *buffers_address = (GLuint *)(intptr_t)buffers;
	glGenBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGenerateMipmap(JNIEnv *env, jclass clazz, jint target) {
	glGenerateMipmap(target);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGenFramebuffers(JNIEnv *env, jclass clazz, jint n, jlong framebuffers) {
	GLuint *framebuffers_address = (GLuint *)(intptr_t)framebuffers;
	glGenFramebuffers(n, framebuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGenRenderbuffers(JNIEnv *env, jclass clazz, jint n, jlong renderbuffers) {
	GLuint *renderbuffers_address = (GLuint *)(intptr_t)renderbuffers;
	glGenRenderbuffers(n, renderbuffers_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGenTextures(JNIEnv *env, jclass clazz, jint n, jlong textures) {
	GLuint *textures_address = (GLuint *)(intptr_t)textures;
	glGenTextures(n, textures_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetActiveAttrib(JNIEnv *env, jclass clazz, jint program, jint index, jint bufsize, jlong length, jlong size, jlong type, jlong name) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLint *size_address = (GLint *)(intptr_t)size;
	GLenum *type_address = (GLenum *)(intptr_t)type;
	GLchar *name_address = (GLchar *)(intptr_t)name;
	glGetActiveAttrib(program, index, bufsize, length_address, size_address, type_address, name_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetActiveUniform(JNIEnv *env, jclass clazz, jint program, jint index, jint bufsize, jlong length, jlong size, jlong type, jlong name) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLsizei *size_address = (GLsizei *)(intptr_t)size;
	GLenum *type_address = (GLenum *)(intptr_t)type;
	GLchar *name_address = (GLchar *)(intptr_t)name;
	glGetActiveUniform(program, index, bufsize, length_address, size_address, type_address, name_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetAttachedShaders(JNIEnv *env, jclass clazz, jint program, jint maxCount, jlong count, jlong shaders) {
	GLsizei *count_address = (GLsizei *)(intptr_t)count;
	GLuint *shaders_address = (GLuint *)(intptr_t)shaders;
	glGetAttachedShaders(program, maxCount, count_address, shaders_address);
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglGetAttribLocation(JNIEnv *env, jclass clazz, jint program, jlong name) {
	const GLchar *name_address = (const GLchar *)(intptr_t)name;
	GLint __result = glGetAttribLocation(program, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetBooleanv(JNIEnv *env, jclass clazz, jint pname, jlong params) {
	GLboolean *params_address = (GLboolean *)(intptr_t)params;
	glGetBooleanv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetBufferParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetBufferParameteriv(target, pname, params_address);
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglGetError(JNIEnv *env, jclass clazz) {
	GLenum __result = glGetError();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetFloatv(JNIEnv *env, jclass clazz, jint pname, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetFloatv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetFramebufferAttachmentParameteriv(JNIEnv *env, jclass clazz, jint target, jint attachment, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetFramebufferAttachmentParameteriv(target, attachment, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetIntegerv(JNIEnv *env, jclass clazz, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetIntegerv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetProgramiv(JNIEnv *env, jclass clazz, jint program, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetProgramiv(program, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetProgramInfoLog(JNIEnv *env, jclass clazz, jint program, jint bufsize, jlong length, jlong infoLog) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *infoLog_address = (GLchar *)(intptr_t)infoLog;
	glGetProgramInfoLog(program, bufsize, length_address, infoLog_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetRenderbufferParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetRenderbufferParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetShaderiv(JNIEnv *env, jclass clazz, jint shader, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetShaderiv(shader, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetShaderInfoLog(JNIEnv *env, jclass clazz, jint shader, jint bufsize, jlong length, jlong infoLog) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *infoLog_address = (GLchar *)(intptr_t)infoLog;
	glGetShaderInfoLog(shader, bufsize, length_address, infoLog_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetShaderPrecisionFormat(JNIEnv *env, jclass clazz, jint shadertype, jint precisiontype, jlong range, jlong precision) {
	GLint *range_address = (GLint *)(intptr_t)range;
	GLint *precision_address = (GLint *)(intptr_t)precision;
	glGetShaderPrecisionFormat(shadertype, precisiontype, range_address, precision_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetShaderSource(JNIEnv *env, jclass clazz, jint shader, jint bufsize, jlong length, jlong source) {
	GLsizei *length_address = (GLsizei *)(intptr_t)length;
	GLchar *source_address = (GLchar *)(intptr_t)source;
	glGetShaderSource(shader, bufsize, length_address, source_address);
}

static jobject JNICALL Java_org_lwjgl_opengles_GLES20_nglGetString(JNIEnv *env, jclass clazz, jint name) {
	const GLubyte * __result = glGetString(name);
	return NewStringNativeUnsigned(env, __result);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetTexParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetTexParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetTexParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetTexParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetUniformfv(JNIEnv *env, jclass clazz, jint program, jint location, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetUniformfv(program, location, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetUniformiv(JNIEnv *env, jclass clazz, jint program, jint location, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetUniformiv(program, location, params_address);
}

static jint JNICALL Java_org_lwjgl_opengles_GLES20_nglGetUniformLocation(JNIEnv *env, jclass clazz, jint program, jlong name) {
	const GLchar *name_address = (const GLchar *)(intptr_t)name;
	GLint __result = glGetUniformLocation(program, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribfv(JNIEnv *env, jclass clazz, jint index, jint pname, jlong params) {
	GLfloat *params_address = (GLfloat *)(intptr_t)params;
	glGetVertexAttribfv(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribiv(JNIEnv *env, jclass clazz, jint index, jint pname, jlong params) {
	GLint *params_address = (GLint *)(intptr_t)params;
	glGetVertexAttribiv(index, pname, params_address);
}

static jobject JNICALL Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribPointerv(JNIEnv *env, jclass clazz, jint index, jint pname, jlong result_size) {
	GLvoid * __result;
	glGetVertexAttribPointerv(index, pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglHint(JNIEnv *env, jclass clazz, jint target, jint mode) {
	glHint(target, mode);
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsBuffer(JNIEnv *env, jclass clazz, jint buffer) {
	GLboolean __result = glIsBuffer(buffer);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsEnabled(JNIEnv *env, jclass clazz, jint cap) {
	GLboolean __result = glIsEnabled(cap);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsFramebuffer(JNIEnv *env, jclass clazz, jint framebuffer) {
	GLboolean __result = glIsFramebuffer(framebuffer);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsProgram(JNIEnv *env, jclass clazz, jint program) {
	GLboolean __result = glIsProgram(program);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsRenderbuffer(JNIEnv *env, jclass clazz, jint renderbuffer) {
	GLboolean __result = glIsRenderbuffer(renderbuffer);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsShader(JNIEnv *env, jclass clazz, jint shader) {
	GLboolean __result = glIsShader(shader);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_opengles_GLES20_nglIsTexture(JNIEnv *env, jclass clazz, jint texture) {
	GLboolean __result = glIsTexture(texture);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglLineWidth(JNIEnv *env, jclass clazz, jfloat width) {
	glLineWidth(width);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglLinkProgram(JNIEnv *env, jclass clazz, jint program) {
	glLinkProgram(program);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglPixelStorei(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPixelStorei(pname, param);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglPolygonOffset(JNIEnv *env, jclass clazz, jfloat factor, jfloat units) {
	glPolygonOffset(factor, units);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglReadPixels(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint format, jint type, jlong pixels) {
	GLvoid *pixels_address = (GLvoid *)(intptr_t)pixels;
	glReadPixels(x, y, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglReleaseShaderCompiler(JNIEnv *env, jclass clazz) {
	glReleaseShaderCompiler();
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglRenderbufferStorage(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height) {
	glRenderbufferStorage(target, internalformat, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglSampleCoverage(JNIEnv *env, jclass clazz, jfloat value, jboolean invert) {
	glSampleCoverage(value, invert);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglScissor(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height) {
	glScissor(x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglShaderBinary(JNIEnv *env, jclass clazz, jint n, jlong shaders, jint binaryformat, jlong binary, jint length) {
	const GLuint *shaders_address = (const GLuint *)(intptr_t)shaders;
	const GLvoid *binary_address = (const GLvoid *)(intptr_t)binary;
	glShaderBinary(n, shaders_address, binaryformat, binary_address, length);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglShaderSource(JNIEnv *env, jclass clazz, jint shader, jint count, jlong string, jint length) {
	const GLchar *string_address = (const GLchar *)(intptr_t)string;
	glShaderSource(shader, count, (const GLchar **)&string_address, (const GLint*)&length);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_GLES20_nglShaderSource3(JNIEnv *env, jclass clazz, jint shader, jint count, jlong strings, jlong length) {
	const GLchar *strings_address = (const GLchar *)(intptr_t)strings;
	unsigned int _str_i;
	GLchar *_str_address;
	GLchar **strings_str = (GLchar **) malloc(count * sizeof(GLchar *));
	const GLint *length_address = (const GLint *)(intptr_t)length;
	_str_i = 0;
	_str_address = (GLchar *)strings_address;
	while ( _str_i < count ) {
		strings_str[_str_i] = _str_address;
		_str_address += length_address[_str_i++];
	}
	glShaderSource(shader, count, (const GLchar **)strings_str, length_address);
	free(strings_str);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilFunc(JNIEnv *env, jclass clazz, jint func, jint ref, jint mask) {
	glStencilFunc(func, ref, mask);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilFuncSeparate(JNIEnv *env, jclass clazz, jint face, jint func, jint ref, jint mask) {
	glStencilFuncSeparate(face, func, ref, mask);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilMask(JNIEnv *env, jclass clazz, jint mask) {
	glStencilMask(mask);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilMaskSeparate(JNIEnv *env, jclass clazz, jint face, jint mask) {
	glStencilMaskSeparate(face, mask);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilOp(JNIEnv *env, jclass clazz, jint fail, jint zfail, jint zpass) {
	glStencilOp(fail, zfail, zpass);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglStencilOpSeparate(JNIEnv *env, jclass clazz, jint face, jint fail, jint zfail, jint zpass) {
	glStencilOpSeparate(face, fail, zfail, zpass);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint format, jint type, jlong pixels) {
	const GLvoid *pixels_address = (const GLvoid *)(intptr_t)pixels;
	glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexParameterf(JNIEnv *env, jclass clazz, jint target, jint pname, jfloat param) {
	glTexParameterf(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong param) {
	const GLfloat *param_address = (const GLfloat *)(intptr_t)param;
	glTexParameterfv(target, pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexParameteri(JNIEnv *env, jclass clazz, jint target, jint pname, jint param) {
	glTexParameteri(target, pname, param);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jlong param) {
	const GLint *param_address = (const GLint *)(intptr_t)param;
	glTexParameteriv(target, pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglTexSubImage2D(JNIEnv *env, jclass clazz, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint type, jlong pixels) {
	const GLvoid *pixels_address = (const GLvoid *)(intptr_t)pixels;
	glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform1f(JNIEnv *env, jclass clazz, jint location, jfloat x) {
	glUniform1f(location, x);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform1fv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLfloat *v_address = (const GLfloat *)(intptr_t)v;
	glUniform1fv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform1i(JNIEnv *env, jclass clazz, jint location, jint x) {
	glUniform1i(location, x);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform1iv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLint *v_address = (const GLint *)(intptr_t)v;
	glUniform1iv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform2f(JNIEnv *env, jclass clazz, jint location, jfloat x, jfloat y) {
	glUniform2f(location, x, y);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform2fv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLfloat *v_address = (const GLfloat *)(intptr_t)v;
	glUniform2fv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform2i(JNIEnv *env, jclass clazz, jint location, jint x, jint y) {
	glUniform2i(location, x, y);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform2iv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLint *v_address = (const GLint *)(intptr_t)v;
	glUniform2iv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform3f(JNIEnv *env, jclass clazz, jint location, jfloat x, jfloat y, jfloat z) {
	glUniform3f(location, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform3fv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLfloat *v_address = (const GLfloat *)(intptr_t)v;
	glUniform3fv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform3i(JNIEnv *env, jclass clazz, jint location, jint x, jint y, jint z) {
	glUniform3i(location, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform3iv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLint *v_address = (const GLint *)(intptr_t)v;
	glUniform3iv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform4f(JNIEnv *env, jclass clazz, jint location, jfloat x, jfloat y, jfloat z, jfloat w) {
	glUniform4f(location, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform4fv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLfloat *v_address = (const GLfloat *)(intptr_t)v;
	glUniform4fv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform4i(JNIEnv *env, jclass clazz, jint location, jint x, jint y, jint z, jint w) {
	glUniform4i(location, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniform4iv(JNIEnv *env, jclass clazz, jint location, jint count, jlong v) {
	const GLint *v_address = (const GLint *)(intptr_t)v;
	glUniform4iv(location, count, v_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniformMatrix2fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jlong matrices) {
	const GLfloat *matrices_address = (const GLfloat *)(intptr_t)matrices;
	glUniformMatrix2fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniformMatrix3fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jlong matrices) {
	const GLfloat *matrices_address = (const GLfloat *)(intptr_t)matrices;
	glUniformMatrix3fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUniformMatrix4fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jlong matrices) {
	const GLfloat *matrices_address = (const GLfloat *)(intptr_t)matrices;
	glUniformMatrix4fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglUseProgram(JNIEnv *env, jclass clazz, jint program) {
	glUseProgram(program);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglValidateProgram(JNIEnv *env, jclass clazz, jint program) {
	glValidateProgram(program);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib1f(JNIEnv *env, jclass clazz, jint indx, jfloat x) {
	glVertexAttrib1f(indx, x);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib1fv(JNIEnv *env, jclass clazz, jint indx, jlong values) {
	const GLfloat *values_address = (const GLfloat *)(intptr_t)values;
	glVertexAttrib1fv(indx, values_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib2f(JNIEnv *env, jclass clazz, jint indx, jfloat x, jfloat y) {
	glVertexAttrib2f(indx, x, y);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib2fv(JNIEnv *env, jclass clazz, jint indx, jlong values) {
	const GLfloat *values_address = (const GLfloat *)(intptr_t)values;
	glVertexAttrib2fv(indx, values_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib3f(JNIEnv *env, jclass clazz, jint indx, jfloat x, jfloat y, jfloat z) {
	glVertexAttrib3f(indx, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib3fv(JNIEnv *env, jclass clazz, jint indx, jlong values) {
	const GLfloat *values_address = (const GLfloat *)(intptr_t)values;
	glVertexAttrib3fv(indx, values_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib4f(JNIEnv *env, jclass clazz, jint indx, jfloat x, jfloat y, jfloat z, jfloat w) {
	glVertexAttrib4f(indx, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttrib4fv(JNIEnv *env, jclass clazz, jint indx, jlong values) {
	const GLfloat *values_address = (const GLfloat *)(intptr_t)values;
	glVertexAttrib4fv(indx, values_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttribPointer(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jlong buffer) {
	const GLvoid *buffer_address = (const GLvoid *)(intptr_t)buffer;
	glVertexAttribPointer(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglVertexAttribPointerBO(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jlong buffer_buffer_offset) {
	const GLvoid *buffer_address = (const GLvoid *)(intptr_t)offsetToPointer(buffer_buffer_offset);
	glVertexAttribPointer(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengles_GLES20_nglViewport(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height) {
	glViewport(x, y, width, height);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_GLES20_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglActiveTexture", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglActiveTexture, "glActiveTexture", (void *)&glActiveTexture, false},
		{"nglAttachShader", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglAttachShader, "glAttachShader", (void *)&glAttachShader, false},
		{"nglBindAttribLocation", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBindAttribLocation, "glBindAttribLocation", (void *)&glBindAttribLocation, false},
		{"nglBindBuffer", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBindBuffer, "glBindBuffer", (void *)&glBindBuffer, false},
		{"nglBindFramebuffer", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBindFramebuffer, "glBindFramebuffer", (void *)&glBindFramebuffer, false},
		{"nglBindRenderbuffer", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBindRenderbuffer, "glBindRenderbuffer", (void *)&glBindRenderbuffer, false},
		{"nglBindTexture", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBindTexture, "glBindTexture", (void *)&glBindTexture, false},
		{"nglBlendColor", "(FFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBlendColor, "glBlendColor", (void *)&glBlendColor, false},
		{"nglBlendEquation", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBlendEquation, "glBlendEquation", (void *)&glBlendEquation, false},
		{"nglBlendEquationSeparate", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBlendEquationSeparate, "glBlendEquationSeparate", (void *)&glBlendEquationSeparate, false},
		{"nglBlendFunc", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBlendFunc, "glBlendFunc", (void *)&glBlendFunc, false},
		{"nglBlendFuncSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBlendFuncSeparate, "glBlendFuncSeparate", (void *)&glBlendFuncSeparate, false},
		{"nglBufferData", "(IJJI)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBufferData, "glBufferData", (void *)&glBufferData, false},
		{"nglBufferSubData", "(IJJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglBufferSubData, "glBufferSubData", (void *)&glBufferSubData, false},
		{"nglCheckFramebufferStatus", "(I)I", (void *)&Java_org_lwjgl_opengles_GLES20_nglCheckFramebufferStatus, "glCheckFramebufferStatus", (void *)&glCheckFramebufferStatus, false},
		{"nglClear", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglClear, "glClear", (void *)&glClear, false},
		{"nglClearColor", "(FFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglClearColor, "glClearColor", (void *)&glClearColor, false},
		{"nglClearDepthf", "(F)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglClearDepthf, "glClearDepthf", (void *)&glClearDepthf, false},
		{"nglClearStencil", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglClearStencil, "glClearStencil", (void *)&glClearStencil, false},
		{"nglColorMask", "(ZZZZ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglColorMask, "glColorMask", (void *)&glColorMask, false},
		{"nglCompileShader", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCompileShader, "glCompileShader", (void *)&glCompileShader, false},
		{"nglCompressedTexImage2D", "(IIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCompressedTexImage2D, "glCompressedTexImage2D", (void *)&glCompressedTexImage2D, false},
		{"nglCompressedTexSubImage2D", "(IIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCompressedTexSubImage2D, "glCompressedTexSubImage2D", (void *)&glCompressedTexSubImage2D, false},
		{"nglCopyTexImage2D", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCopyTexImage2D, "glCopyTexImage2D", (void *)&glCopyTexImage2D, false},
		{"nglCopyTexSubImage2D", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCopyTexSubImage2D, "glCopyTexSubImage2D", (void *)&glCopyTexSubImage2D, false},
		{"nglCreateProgram", "()I", (void *)&Java_org_lwjgl_opengles_GLES20_nglCreateProgram, "glCreateProgram", (void *)&glCreateProgram, false},
		{"nglCreateShader", "(I)I", (void *)&Java_org_lwjgl_opengles_GLES20_nglCreateShader, "glCreateShader", (void *)&glCreateShader, false},
		{"nglCullFace", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglCullFace, "glCullFace", (void *)&glCullFace, false},
		{"nglDeleteBuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteBuffers, "glDeleteBuffers", (void *)&glDeleteBuffers, false},
		{"nglDeleteFramebuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteFramebuffers, "glDeleteFramebuffers", (void *)&glDeleteFramebuffers, false},
		{"nglDeleteProgram", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteProgram, "glDeleteProgram", (void *)&glDeleteProgram, false},
		{"nglDeleteRenderbuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteRenderbuffers, "glDeleteRenderbuffers", (void *)&glDeleteRenderbuffers, false},
		{"nglDeleteShader", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteShader, "glDeleteShader", (void *)&glDeleteShader, false},
		{"nglDeleteTextures", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDeleteTextures, "glDeleteTextures", (void *)&glDeleteTextures, false},
		{"nglDepthFunc", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDepthFunc, "glDepthFunc", (void *)&glDepthFunc, false},
		{"nglDepthMask", "(Z)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDepthMask, "glDepthMask", (void *)&glDepthMask, false},
		{"nglDepthRangef", "(FF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDepthRangef, "glDepthRangef", (void *)&glDepthRangef, false},
		{"nglDetachShader", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDetachShader, "glDetachShader", (void *)&glDetachShader, false},
		{"nglDisable", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDisable, "glDisable", (void *)&glDisable, false},
		{"nglDisableVertexAttribArray", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDisableVertexAttribArray, "glDisableVertexAttribArray", (void *)&glDisableVertexAttribArray, false},
		{"nglDrawArrays", "(III)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDrawArrays, "glDrawArrays", (void *)&glDrawArrays, false},
		{"nglDrawElements", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDrawElements, "glDrawElements", (void *)&glDrawElements, false},
		{"nglDrawElementsBO", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglDrawElementsBO, "glDrawElements", (void *)&glDrawElements, false},
		{"nglEnable", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglEnable, "glEnable", (void *)&glEnable, false},
		{"nglEnableVertexAttribArray", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglEnableVertexAttribArray, "glEnableVertexAttribArray", (void *)&glEnableVertexAttribArray, false},
		{"nglFinish", "()V", (void *)&Java_org_lwjgl_opengles_GLES20_nglFinish, "glFinish", (void *)&glFinish, false},
		{"nglFlush", "()V", (void *)&Java_org_lwjgl_opengles_GLES20_nglFlush, "glFlush", (void *)&glFlush, false},
		{"nglFramebufferRenderbuffer", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglFramebufferRenderbuffer, "glFramebufferRenderbuffer", (void *)&glFramebufferRenderbuffer, false},
		{"nglFramebufferTexture2D", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglFramebufferTexture2D, "glFramebufferTexture2D", (void *)&glFramebufferTexture2D, false},
		{"nglFrontFace", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglFrontFace, "glFrontFace", (void *)&glFrontFace, false},
		{"nglGenBuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGenBuffers, "glGenBuffers", (void *)&glGenBuffers, false},
		{"nglGenerateMipmap", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGenerateMipmap, "glGenerateMipmap", (void *)&glGenerateMipmap, false},
		{"nglGenFramebuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGenFramebuffers, "glGenFramebuffers", (void *)&glGenFramebuffers, false},
		{"nglGenRenderbuffers", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGenRenderbuffers, "glGenRenderbuffers", (void *)&glGenRenderbuffers, false},
		{"nglGenTextures", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGenTextures, "glGenTextures", (void *)&glGenTextures, false},
		{"nglGetActiveAttrib", "(IIIJJJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetActiveAttrib, "glGetActiveAttrib", (void *)&glGetActiveAttrib, false},
		{"nglGetActiveUniform", "(IIIJJJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetActiveUniform, "glGetActiveUniform", (void *)&glGetActiveUniform, false},
		{"nglGetAttachedShaders", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetAttachedShaders, "glGetAttachedShaders", (void *)&glGetAttachedShaders, false},
		{"nglGetAttribLocation", "(IJ)I", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetAttribLocation, "glGetAttribLocation", (void *)&glGetAttribLocation, false},
		{"nglGetBooleanv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetBooleanv, "glGetBooleanv", (void *)&glGetBooleanv, false},
		{"nglGetBufferParameteriv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetBufferParameteriv, "glGetBufferParameteriv", (void *)&glGetBufferParameteriv, false},
		{"nglGetError", "()I", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetError, "glGetError", (void *)&glGetError, false},
		{"nglGetFloatv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetFloatv, "glGetFloatv", (void *)&glGetFloatv, false},
		{"nglGetFramebufferAttachmentParameteriv", "(IIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetFramebufferAttachmentParameteriv, "glGetFramebufferAttachmentParameteriv", (void *)&glGetFramebufferAttachmentParameteriv, false},
		{"nglGetIntegerv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetIntegerv, "glGetIntegerv", (void *)&glGetIntegerv, false},
		{"nglGetProgramiv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetProgramiv, "glGetProgramiv", (void *)&glGetProgramiv, false},
		{"nglGetProgramInfoLog", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetProgramInfoLog, "glGetProgramInfoLog", (void *)&glGetProgramInfoLog, false},
		{"nglGetRenderbufferParameteriv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetRenderbufferParameteriv, "glGetRenderbufferParameteriv", (void *)&glGetRenderbufferParameteriv, false},
		{"nglGetShaderiv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetShaderiv, "glGetShaderiv", (void *)&glGetShaderiv, false},
		{"nglGetShaderInfoLog", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetShaderInfoLog, "glGetShaderInfoLog", (void *)&glGetShaderInfoLog, false},
		{"nglGetShaderPrecisionFormat", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetShaderPrecisionFormat, "glGetShaderPrecisionFormat", (void *)&glGetShaderPrecisionFormat, false},
		{"nglGetShaderSource", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetShaderSource, "glGetShaderSource", (void *)&glGetShaderSource, false},
		{"nglGetString", "(I)Ljava/lang/String;", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetString, "glGetString", (void *)&glGetString, false},
		{"nglGetTexParameterfv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetTexParameterfv, "glGetTexParameterfv", (void *)&glGetTexParameterfv, false},
		{"nglGetTexParameteriv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetTexParameteriv, "glGetTexParameteriv", (void *)&glGetTexParameteriv, false},
		{"nglGetUniformfv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetUniformfv, "glGetUniformfv", (void *)&glGetUniformfv, false},
		{"nglGetUniformiv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetUniformiv, "glGetUniformiv", (void *)&glGetUniformiv, false},
		{"nglGetUniformLocation", "(IJ)I", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetUniformLocation, "glGetUniformLocation", (void *)&glGetUniformLocation, false},
		{"nglGetVertexAttribfv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribfv, "glGetVertexAttribfv", (void *)&glGetVertexAttribfv, false},
		{"nglGetVertexAttribiv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribiv, "glGetVertexAttribiv", (void *)&glGetVertexAttribiv, false},
		{"nglGetVertexAttribPointerv", "(IIJ)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengles_GLES20_nglGetVertexAttribPointerv, "glGetVertexAttribPointerv", (void *)&glGetVertexAttribPointerv, false},
		{"nglHint", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglHint, "glHint", (void *)&glHint, false},
		{"nglIsBuffer", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsBuffer, "glIsBuffer", (void *)&glIsBuffer, false},
		{"nglIsEnabled", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsEnabled, "glIsEnabled", (void *)&glIsEnabled, false},
		{"nglIsFramebuffer", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsFramebuffer, "glIsFramebuffer", (void *)&glIsFramebuffer, false},
		{"nglIsProgram", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsProgram, "glIsProgram", (void *)&glIsProgram, false},
		{"nglIsRenderbuffer", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsRenderbuffer, "glIsRenderbuffer", (void *)&glIsRenderbuffer, false},
		{"nglIsShader", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsShader, "glIsShader", (void *)&glIsShader, false},
		{"nglIsTexture", "(I)Z", (void *)&Java_org_lwjgl_opengles_GLES20_nglIsTexture, "glIsTexture", (void *)&glIsTexture, false},
		{"nglLineWidth", "(F)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglLineWidth, "glLineWidth", (void *)&glLineWidth, false},
		{"nglLinkProgram", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglLinkProgram, "glLinkProgram", (void *)&glLinkProgram, false},
		{"nglPixelStorei", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglPixelStorei, "glPixelStorei", (void *)&glPixelStorei, false},
		{"nglPolygonOffset", "(FF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglPolygonOffset, "glPolygonOffset", (void *)&glPolygonOffset, false},
		{"nglReadPixels", "(IIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglReadPixels, "glReadPixels", (void *)&glReadPixels, false},
		{"nglReleaseShaderCompiler", "()V", (void *)&Java_org_lwjgl_opengles_GLES20_nglReleaseShaderCompiler, "glReleaseShaderCompiler", (void *)&glReleaseShaderCompiler, false},
		{"nglRenderbufferStorage", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglRenderbufferStorage, "glRenderbufferStorage", (void *)&glRenderbufferStorage, false},
		{"nglSampleCoverage", "(FZ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglSampleCoverage, "glSampleCoverage", (void *)&glSampleCoverage, false},
		{"nglScissor", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglScissor, "glScissor", (void *)&glScissor, false},
		{"nglShaderBinary", "(IJIJI)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglShaderBinary, "glShaderBinary", (void *)&glShaderBinary, false},
		{"nglShaderSource", "(IIJI)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglShaderSource, "glShaderSource", (void *)&glShaderSource, false},
		{"nglShaderSource3", "(IIJJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglShaderSource3, "glShaderSource", (void *)&glShaderSource, false},
		{"nglStencilFunc", "(III)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilFunc, "glStencilFunc", (void *)&glStencilFunc, false},
		{"nglStencilFuncSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilFuncSeparate, "glStencilFuncSeparate", (void *)&glStencilFuncSeparate, false},
		{"nglStencilMask", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilMask, "glStencilMask", (void *)&glStencilMask, false},
		{"nglStencilMaskSeparate", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilMaskSeparate, "glStencilMaskSeparate", (void *)&glStencilMaskSeparate, false},
		{"nglStencilOp", "(III)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilOp, "glStencilOp", (void *)&glStencilOp, false},
		{"nglStencilOpSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglStencilOpSeparate, "glStencilOpSeparate", (void *)&glStencilOpSeparate, false},
		{"nglTexImage2D", "(IIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexImage2D, "glTexImage2D", (void *)&glTexImage2D, false},
		{"nglTexParameterf", "(IIF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexParameterf, "glTexParameterf", (void *)&glTexParameterf, false},
		{"nglTexParameterfv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexParameterfv, "glTexParameterfv", (void *)&glTexParameterfv, false},
		{"nglTexParameteri", "(III)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexParameteri, "glTexParameteri", (void *)&glTexParameteri, false},
		{"nglTexParameteriv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexParameteriv, "glTexParameteriv", (void *)&glTexParameteriv, false},
		{"nglTexSubImage2D", "(IIIIIIIIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglTexSubImage2D, "glTexSubImage2D", (void *)&glTexSubImage2D, false},
		{"nglUniform1f", "(IF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform1f, "glUniform1f", (void *)&glUniform1f, false},
		{"nglUniform1fv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform1fv, "glUniform1fv", (void *)&glUniform1fv, false},
		{"nglUniform1i", "(II)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform1i, "glUniform1i", (void *)&glUniform1i, false},
		{"nglUniform1iv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform1iv, "glUniform1iv", (void *)&glUniform1iv, false},
		{"nglUniform2f", "(IFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform2f, "glUniform2f", (void *)&glUniform2f, false},
		{"nglUniform2fv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform2fv, "glUniform2fv", (void *)&glUniform2fv, false},
		{"nglUniform2i", "(III)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform2i, "glUniform2i", (void *)&glUniform2i, false},
		{"nglUniform2iv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform2iv, "glUniform2iv", (void *)&glUniform2iv, false},
		{"nglUniform3f", "(IFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform3f, "glUniform3f", (void *)&glUniform3f, false},
		{"nglUniform3fv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform3fv, "glUniform3fv", (void *)&glUniform3fv, false},
		{"nglUniform3i", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform3i, "glUniform3i", (void *)&glUniform3i, false},
		{"nglUniform3iv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform3iv, "glUniform3iv", (void *)&glUniform3iv, false},
		{"nglUniform4f", "(IFFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform4f, "glUniform4f", (void *)&glUniform4f, false},
		{"nglUniform4fv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform4fv, "glUniform4fv", (void *)&glUniform4fv, false},
		{"nglUniform4i", "(IIIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform4i, "glUniform4i", (void *)&glUniform4i, false},
		{"nglUniform4iv", "(IIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniform4iv, "glUniform4iv", (void *)&glUniform4iv, false},
		{"nglUniformMatrix2fv", "(IIZJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniformMatrix2fv, "glUniformMatrix2fv", (void *)&glUniformMatrix2fv, false},
		{"nglUniformMatrix3fv", "(IIZJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniformMatrix3fv, "glUniformMatrix3fv", (void *)&glUniformMatrix3fv, false},
		{"nglUniformMatrix4fv", "(IIZJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUniformMatrix4fv, "glUniformMatrix4fv", (void *)&glUniformMatrix4fv, false},
		{"nglUseProgram", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglUseProgram, "glUseProgram", (void *)&glUseProgram, false},
		{"nglValidateProgram", "(I)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglValidateProgram, "glValidateProgram", (void *)&glValidateProgram, false},
		{"nglVertexAttrib1f", "(IF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib1f, "glVertexAttrib1f", (void *)&glVertexAttrib1f, false},
		{"nglVertexAttrib1fv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib1fv, "glVertexAttrib1fv", (void *)&glVertexAttrib1fv, false},
		{"nglVertexAttrib2f", "(IFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib2f, "glVertexAttrib2f", (void *)&glVertexAttrib2f, false},
		{"nglVertexAttrib2fv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib2fv, "glVertexAttrib2fv", (void *)&glVertexAttrib2fv, false},
		{"nglVertexAttrib3f", "(IFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib3f, "glVertexAttrib3f", (void *)&glVertexAttrib3f, false},
		{"nglVertexAttrib3fv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib3fv, "glVertexAttrib3fv", (void *)&glVertexAttrib3fv, false},
		{"nglVertexAttrib4f", "(IFFFF)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib4f, "glVertexAttrib4f", (void *)&glVertexAttrib4f, false},
		{"nglVertexAttrib4fv", "(IJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttrib4fv, "glVertexAttrib4fv", (void *)&glVertexAttrib4fv, false},
		{"nglVertexAttribPointer", "(IIIZIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttribPointer, "glVertexAttribPointer", (void *)&glVertexAttribPointer, false},
		{"nglVertexAttribPointerBO", "(IIIZIJ)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglVertexAttribPointerBO, "glVertexAttribPointer", (void *)&glVertexAttribPointer, false},
		{"nglViewport", "(IIII)V", (void *)&Java_org_lwjgl_opengles_GLES20_nglViewport, "glViewport", (void *)&glViewport, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
