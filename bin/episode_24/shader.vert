attribute vec4 gl_Color;
varying vec4 vertColor;
uniform sampler2D texture;
uniform float useTexture;

varying float diffuse_value;

void main(){
	gl_TexCoord[0] = gl_MultiTexCoord0;
    vec3 vertex_normal = normalize(gl_NormalMatrix * gl_Normal);
	vec3 vertex_light_position = gl_LightSource[0].position.xyz;
	diffuse_value = max(dot(vertex_normal, vertex_light_position), 0.0);
	vertColor = gl_Color;
	gl_FrontColor = gl_Color;
	gl_Position = ftransform();
}