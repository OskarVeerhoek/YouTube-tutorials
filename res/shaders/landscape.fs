#version 120

uniform sampler2D lookup;

varying float varyingHeight;

void main() {
//    if (varyingHeight < 0.25) {
//        gl_FragColor = vec4(0, 0, 1, 1);
//    } else if (varyingHeight < 0.28) {
//        gl_FragColor = vec4(1, 1, 0, 1);
//    } else if (varyingHeight < 0.48) {
//        gl_FragColor = vec4(0, 1 - varyingHeight, 0, 1);
//    } else if (varyingHeight < 0.9) {
//        gl_FragColor = vec4(varyingHeight * 1.2, varyingHeight * 1.2, varyingHeight * 1.2, 1);
//    } else {
//        gl_FragColor = vec4(1, 1, 1, 1);
//    }
    gl_FragColor = texture2D(lookup, vec2(varyingHeight, 0.0));
}