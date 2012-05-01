#version 120

// Per-vertex Phong lighting model

// Author: Oskar Veerhoek, www.youtube.com/thecodinguniverse

varying vec3 color;

uniform float diffuseIntensityModifier;

void main() {
	// Turns the varying color into a 4D color and stores in the built-in output gl_FragColor.
    gl_FragColor = vec4(color, 1);
}