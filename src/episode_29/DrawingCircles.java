package episode_29;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Graphics2D;
import java.awt.SplashScreen;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DrawingCircles {

	private static void glCircle3i(int x, int y, int radius) {
		float angle;
		glPushMatrix();
		glLoadIdentity();
		glScaled(0.7, 0.7, 0.7);
		boolean textureEnabled = glIsEnabled(GL_TEXTURE_2D);
		if (textureEnabled)
			glDisable(GL_TEXTURE_2D);
		glColor3f(0, 1, 0);
		glLineWidth(1);
		glBegin(GL_LINE_LOOP);
		for (int i = 0; i < 100; i++) {
			angle = (float) (i * 2 * Math.PI / 100);
			glVertex2d(x + (Math.cos(angle) * radius), y
					+ (Math.sin(angle) * radius));
		}
		glEnd();
		if (textureEnabled)
			glEnable(GL_TEXTURE_2D);
		glPopMatrix();
	}

	private static void render() {
		glCircle3i(0, 0, 1);
	}

	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640, 640));
			Display.setTitle("Drawing Circles");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		glMatrixMode(GL_PROJECTION);
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			render();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}

}
