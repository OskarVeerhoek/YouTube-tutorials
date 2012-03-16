varying vec4 vertColor;
uniform sampler2D texture;
uniform float useTexture;

varying float diffuse_value;

void main(){
	//gl_FragColor = mix(gl_Color * diffuse_value, texture2D(texture, gl_TexCoord[0].st), useTexture);
	gl_FragColor = gl_Color * diffuse_value;
}
