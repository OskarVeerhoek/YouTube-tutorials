/*
 * Copyright (c) 2012, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

#version 120

attribute vec4 attributeVertex;
attribute vec3 attributeNormal;

uniform vec4 uniformColour;
uniform mat4 uniformModelViewProjectionMatrix;

// The colour we're going to pass to the fragment shader.
varying vec4 varyingColour;
// The normal we're going to pass to the fragment shader.
varying vec3 varyingNormal;
// The vertex we're going to pass to the fragment shader.
varying vec4 varyingVertex;

void main() {
    // Pass the vertex colour attribute to the fragment shader.
    // This value will be interpolated automatically by OpenGL
    // if GL_SHADE_MODEL is GL_SMOOTH. (that's the default)
    varyingColour = attributeColour;
    // Pass the vertex normal attribute to the fragment shader.
    // This value will be interpolated automatically by OpenGL
    // if GL_SHADE_MODEL is GL_SMOOTH. (that's the default)
    varyingNormal = attributeNormal;
    // Pass the vertex position attribute to the fragment shader.
    // This value will be interpolated automatically by OpenGL
    // if GL_SHADE_MODEL is GL_SMOOTH. (that's the default)
    varyingVertex = attributeVertex;
    // Send the vertex position, modified by glTranslate/glRotate/glScale
    // and glOrtho/glFrustum/gluPerspective to primitive assembly.
    gl_Position = uniformModelViewProjectionMatrix * attributeVertex;
}