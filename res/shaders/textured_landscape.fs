#version 120

uniform sampler2D lookup;
uniform sampler2D terrain;

varying float varyingHeight;
varying vec3 varyingTextureCoordinate;

void main() {
    gl_FragColor = mix(texture2D(terrain, vec2(varyingTextureCoordinate), texture2D(lookup, vec2(varyingHeight, 0.0)));
}