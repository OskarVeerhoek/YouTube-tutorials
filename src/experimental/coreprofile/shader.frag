#version 150 core
in vec3 pass_Color;
out vec3 out_Color;

void main(void) {
    out_Color = vec4(pass_Color, 1.0);
}