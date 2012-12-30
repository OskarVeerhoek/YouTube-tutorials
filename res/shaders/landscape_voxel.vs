#version 120

varying float varyingHeight;

void main() {
    varyingHeight = gl_Vertex.y / 255.0;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}