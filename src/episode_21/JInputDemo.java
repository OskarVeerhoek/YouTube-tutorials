package episode_21;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Oskar
 */
public class JInputDemo {
    
    private static volatile boolean running = true;

    public static void main(String[] args) {
        
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException ex) {
            System.err.println("Display wasn't able to be created.");
            Display.destroy();
            System.exit(1);
        }
        
        Controller joystick = null;
        
        for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
            if (c.getType() == Controller.Type.STICK) {
                joystick = c;
                System.out.println(joystick.getName());
            }
        }
        
        if (joystick == null) {
            System.err.println("No joystick was found.");
            Display.destroy();
            System.exit(1);
        }
        
        for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
            System.out.println(c.getName());
        }
        
        float dx = 0;
        
        while (running) {
            joystick.poll();
            for (Component c : joystick.getComponents()) {
                
                if (c.getName().equals("x")) {
                    dx += c.getPollData();
                } else if (c.getName().equals("pov")) {
                    if (c.getPollData() == 0.75) {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                
            }
            running = !Display.isCloseRequested();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
        
    }
}
