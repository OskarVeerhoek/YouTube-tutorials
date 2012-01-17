#version 120

uniform sampler2D texture0;

varying vec4 color;
varying vec2 texCoord0;

void main(void) {
	gl_FragColor = color * texture2D(texture0, texCoord0.st);	
}
