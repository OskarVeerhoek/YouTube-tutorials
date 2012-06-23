package future;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Oskar Veerhoek
 */
public class JBox2DDemo {

    private static final String WINDOW_TITLE = "Physics in 2D!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static World world = new World(new Vec2(0, -10), true);
    private static BodyDef bodyDef = new BodyDef();
    private static PolygonShape shape = new PolygonShape();
    private static FixtureDef fixtureDef = new FixtureDef();
    private static Body body;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glLoadIdentity();
        glTranslatef(body.getPosition().x, body.getPosition().y, 0);
        glRotatef(body.getAngle()-90, 0, 0, 1);
//        glRectf(body.getPosition().x - 2.5f, body.getPosition().y - 2.5f, body.getPosition().x + 2.5f, body.getPosition().y + 2.5f);
        glRectf(-2.5f, -2.5f, 2.5f, 2.5f);
    }

    private static void logic() {
        world.step(1f / 60f, 6, 3);
    }

    private static void input() {
        Vec2 force = new Vec2();
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            force.x = -1000;
        } else if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            force.x = +1000;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            force.y = +1000;
        } else if (!Keyboard.isKeyDown(Keyboard.KEY_UP) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            force.y = -1000;
        } else {
            force.x = +0;
        }
        body.applyForce(force, body.getPosition());
        if (Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
            body.setTransform(body.getPosition(), body.getAngle() +1);
        } else if (!Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
            body.setTransform(body.getPosition(), body.getAngle() -1);
        }
    }

    private static void cleanUp(boolean asCrash) {
        // Add cleaning code here
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 64, 0, 48, -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpJBOX2D() {
        bodyDef.position.set(0, 10);
        bodyDef.type = BodyType.DYNAMIC;
//        shape.m_radius = 5;
        shape.set(new Vec2[]{new Vec2(-2.5f, -2.5f), new Vec2(-2.5f, 2.5f), new Vec2(2.5f, 2.5f), new Vec2(2.5f, -2.5f)}, 4);
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        BodyDef groundBodyDefinition = new BodyDef();
        groundBodyDefinition.position.set(0, 0);
        groundBodyDefinition.type = BodyType.STATIC;
        Body groundBody = world.createBody(groundBodyDefinition);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1000, 0);
        groundBody.createFixture(groundBox, 0);
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
        setUpMatrices();
        setUpJBOX2D();
        enterGameLoop();
        cleanUp(false);
    }
}