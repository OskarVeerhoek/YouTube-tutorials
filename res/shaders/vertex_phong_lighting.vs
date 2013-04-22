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

// Per-vertex Phong lighting model

// Varying is set by the vertex program and read by the fragment
// program.
// Uniform is set by the Java code and read by the vertex and fragment
// program. 
// Attribute is set by the Java code and read by the vertex program. 

varying vec3 varyingColour;

// Object Space: Vertices you specify with glVertex(Pointer)
// Eye Space: Vertices transformed by the modelview matrix 
// 							(affected by glTranslate/glRotate)
// Clip Space: Vertices transformed by the projection matrix
//							(affected by gluPerspective/glOrtho)

// Diffuse lighting: light is reflected in every direction evenly
// To calculate the intensity of the reflected lighting we need the cosine 
// of the angle between the light and the surface normal. We could also use 
// diffuse attributes of the material and of the light.
// Specular lighting: 

void main() {
	// Retrieves the position of the vertex in eye space by 
	// multiplying the vertex in object space with the 
	// modelview matrix and stores it in a 3D vertex.
	vec3 vertexPosition = (gl_ModelViewMatrix * gl_Vertex).xyz;
	// Retrieves the direction of the light and stores it in a 
	// normalized 3D vector (normalized = length of 1).
	vec3 lightDirection = normalize(gl_LightSource[0].position.xyz - vertexPosition);
	// Retrieves the surface normal by multiplying the normal
	// by the normal matrix. If you don't use non-uniform scaling
	// operations you could also do: '= gl_Normal.xyz;'.
	vec3 surfaceNormal  = (gl_NormalMatrix * gl_Normal).xyz;
	
	// Retrieves the intensity of the diffuse light by taking the dot-product of 
	// the surface normal and the light direction vectors and stores the value in a scalar. 
	// If the value is lower than 0, the light is messed up and we don't want
	// to show it.
	float diffuseLightIntensity = max(0, dot(surfaceNormal, lightDirection));
	// Sets the colour (which is passed to the fragment program) to the concatenation
	// of the material colour and the diffuse light intensity.
	varyingColour.rgb = diffuseLightIntensity * gl_FrontMaterial.diffuse.rgb;
	// Adds ambient colour to the colour so even the darkest part equals ambientColour.
	varyingColour += gl_LightModel.ambient.rgb;
	
	// Calculates the direction of the reflectionDirection by using the method reflect, which takes 
	// the normalized direction from the light source to the surface as the 1st parameter,
	// and the normalized surface normal as the second. Since lightDirection points to
	// the direction of the light and not the surface, we need to negate it in order for
	// the returned vector to be valid. 
	vec3 reflectionDirection = normalize(reflect(-lightDirection, surfaceNormal));
	// Stores the dot-product of the surface normal and the direction of the reflection
	// in a scalar. Also checks if the value is negative. If so, the scalar is set to 0.0.
	float specular = max(0.0, dot(surfaceNormal, reflectionDirection));
	if (diffuseLightIntensity != 0) {
		// Enhances the specular scalar value by raising it to the exponent of the shininess.
		float fspecular = pow(specular, gl_FrontMaterial.shininess);
		// Adds the specular value to the colour.
		varyingColour.rgb += vec3(fspecular, fspecular, fspecular);
	}
	
	// Retrieves the position of the vertex in clip space by multiplying it by the modelview-
	// projection matrix and stores it in the built-in output variable gl_Position.
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}