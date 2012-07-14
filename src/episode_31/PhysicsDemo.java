package episode_31;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;

/**
 * 30 pixels = 1 metre
 *
 * @author Oskar Veerhoek
 */
public class PhysicsDemo {

    private static final String WINDOW_TITLE = "Physics in 2D!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static World world = new World(new Vec2(0, -9.8f), false);
    private static Set<Body> bodies = new HashSet<Body>();

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        for (Body body : bodies) {
            if (body.getType() == BodyType.DYNAMIC) {
                glPushMatrix();
                Vec2 bodyPosition = body.getPosition().mul(30);
                glTranslatef(bodyPosition.x, bodyPosition.y, 0);
                glRotated(Math.toDegrees(body.getAngle()), 0, 0, 1);
                glRectf(-0.75f * 30, -0.75f * 30, 0.75f * 30, 0.75f * 30);
                glPopMatrix();
            }
        }
    }

    private static void logic() {
        world.step(1 / 60f, 8, 3);
    }

    private static void input() {
        for (Body body : bodies) {
            if (body.getType() == BodyType.DYNAMIC) {
                if (Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
                    body.applyAngularImpulse(+0.01f);
                } else if (Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
                    body.applyAngularImpulse(-0.01f);
                }
                if (Mouse.isButtonDown(0)) {
                    Vec2 mousePosition = new Vec2(Mouse.getX(), Mouse.getY()).mul(0.5f).mul(1 / 30f);
                    Vec2 bodyPosition = body.getPosition();
                    Vec2 force = mousePosition.sub(bodyPosition);
                    body.applyForce(force, body.getPosition());
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 320, 0, 240, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpObjects() {
        BodyDef boxDef = new BodyDef();
        boxDef.position.set(320 / 30 / 2, 240 / 30 / 2);
        boxDef.type = BodyType.DYNAMIC;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.75f, 0.75f);
        Body box = world.createBody(boxDef);
        FixtureDef boxFixture = new FixtureDef();
        boxFixture.density = 0.1f;
        boxFixture.shape = boxShape;
        box.createFixture(boxFixture);
        bodies.add(box);

        BodyDef groundDef = new BodyDef();
        groundDef.position.set(0, 0);
        groundDef.type = BodyType.STATIC;
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1000, 0);
        Body ground = world.createBody(groundDef);
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.density = 1;
        groundFixture.restitution = 0.3f;
        groundFixture.shape = groundShape;
        ground.createFixture(groundFixture);
        bodies.add(ground);
    }

    private static void setUpStates() {
//        glEnable(GL_DEPTH_TEST);
//        glEnable(GL_LIGHTING);
//        glEnable(GL_BLEND);
//        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    private static void update() {
        Display.update();
        Display.sync(60);
    }

    private static void enterGameLoop() {
        while (!Display.isCloseRequested()) {
            render();
            logic();
            input();
            update();
        }
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpObjects();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }

}
