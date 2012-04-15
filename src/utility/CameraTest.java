package utility;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class CameraTest {


    private static class Point {

        float x, y, z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(1280, 720));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Mouse.setGrabbed(true);
		
		Camera cam = new Camera(1280f/720f);
//		cam.setYaw(45);
		cam.applyProjectionMatrix();

		Point[] points = new Point[3000000];
        Random random = new Random();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point((random.nextFloat() - 0.5f) * 100f, (random.nextFloat() - 0.5f) * 100f, random.nextInt(points.length / 50) - points.length / 50);
        }
        int displayList = glGenLists(1);
        glNewList(displayList, GL_COMPILE);
        glBegin(GL_POINTS);
        for (Point p : points) {
            glVertex3f(p.x, p.y, p.z);
        }
        glEnd();
        glEndList();
        
        while (!Display.isCloseRequested()) {
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	cam.applyModelviewMatrix(true);
        	
        	glCallList(displayList);
        	
//        	cam.moveAlongAxis(1, 1, 0, 0);
        	
        	cam.processMouse(1, 80, -80);
        	
        	cam.processKeyboard(16, 0.003f, 0.003f, 0.003f);
        	
        	Display.update();
        	Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
	}

}
