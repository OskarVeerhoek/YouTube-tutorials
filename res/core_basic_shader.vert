#version 150 core

layout(location = 0) in vec4 position;
layout(location = 1) in vec4 color;

uniform mat4 modelview;
uniform mat4 projection;
uniform float smoothness;
uniform vec2 mousePosition;

smooth out vec4 theColor;

void main() {
    theColor = color;
    gl_Position = modelview * projection * position;
}