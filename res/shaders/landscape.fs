#version 120

uniform sampler2D lookup;

varying float varyingHeight;

void main() {
    gl_FragColor = texture2D(lookup, vec2(varyingHeight, 0.0));
}