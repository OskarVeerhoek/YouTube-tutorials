#version 330 core

smooth in vec4 theColor;

out vec4 outputColor;

void main() {
    //outputColor = vec4(0.5, 1, 1, 1);
    outputColor = theColor;
}