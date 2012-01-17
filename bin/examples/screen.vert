#version 120

uniform mat4 projection_matrix;
uniform mat4 modelview_matrix;
uniform mat3 normal_matrix;

uniform vec4 material_ambient;
uniform vec4 material_diffuse;
uniform vec4 material_specular;
uniform vec4 material_emissive;
uniform float material_shininess;

struct light {
	vec4 position;
	vec4 diffuse;
	vec4 specular;
	vec4 ambient;
};

uniform light light0;

attribute vec3 a_Vertex;
attribute vec2 a_TexCoord0;
attribute vec3 a_Normal;

varying vec4 color;
varying vec2 texCoord0;

void main(void) 
{
	//Transform the normal into eye-space using the normal matrix
	vec3 N = normalize(normal_matrix * a_Normal);	

	//Calculate the light direction, in a directional light the position is actually a direction vector
	//We transform the position into eyespace before normalizing the vector
	vec3 L = normalize(modelview_matrix * light0.position).xyz;

	//We calculate the angle between the normal and the light direction
	float NdotL = max(dot(N, L), 0.0);

	//The ambient color is fixed, so we add this as the initial color and 
	//then build on this with the other lighting contributions
	vec4 finalColor = material_ambient * light0.ambient;

	//Do the standard vertex transformation into eye space
	vec4 pos = modelview_matrix * vec4(a_Vertex, 1.0);	

	//Because we are in eye space (everything is relative to the camera)
	//The eye vector is simply the negated position.
	vec3 E = -pos.xyz;

	//If the surface normal is facing towards the light at all
	if (NdotL > 0.0) 
	{
		//Add the diffuse color using Lambertian Reflection
		finalColor += material_diffuse * light0.diffuse * NdotL;

		//Calculate the half vector and make it unit length
		vec3 HV = normalize(L + E);

		//Find the angle between the normal and the half vector
		float NdotHV = max(dot(N, HV), 0.0);
		
		//Calculate the specular using Blinn-Phong
		finalColor += material_specular * light0.specular * pow(NdotHV, material_shininess);	
	}

	//Output the final color, texture coordinate and position
	color = finalColor;
	texCoord0 = a_TexCoord0;
	gl_Position = projection_matrix * pos;	
}
