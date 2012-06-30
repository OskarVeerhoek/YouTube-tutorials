#version 150 core

smooth in vec4 theColor;

uniform float smoothness;
uniform vec2 mousePosition;

out vec4 outputColor;

void main() {
    outputColor = theColor;
    //outputColor.rgb = vec3(ivec3(outputColor * smoothness)) / smoothness;
    //outputColor.r = float(int(outputColor.r * smoothness)) / smoothness;
    //outputColor.g = float(int(outputColor.g * smoothness)) / smoothness;
    //outputColor.b = float(int(outputColor.b * smoothness)) / smoothness;

   // float distanceToMouse = distance(gl_FragCoord.xy, mousePosition) ;

   // if (distanceToMouse > 100) {
   //     discard;
   // }

    //vec3 brightnessModifier = outputColor.rgb + 0.5;
    //brightnessModifier *= 1;
    //outputColor.rgb *= brightnessModifier;
}