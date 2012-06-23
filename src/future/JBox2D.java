package future;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * 30 pixels = 1 metre
 * @author Oskar Veerhoek
 */
public class JBox2D {

    private static final String WINDOW_TITLE = "JBox 2D!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static World world = new World(new Vec2(0, -30f), true);
    private static Body body;
    private static final int RATIO_PIXELS_TO_METRES = 30;

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glLoadIdentity();
        glTranslatef(body.getPosition().x, body.getPosition().y, 0);
        glRectf(-10, -10, 10, 10);
    }

    private static void logic() {
        world.step(1 / 60f, 6, 3);
    }

    private static void input() {
        body.setTransform(body.getPosition(), body.getAngle() + 0.1f);
    }

    private static void cleanUp(boolean asCrash) {
        // Add cleaning code here
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 0, 480, -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpStates() {
        // Create box

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(10, 10);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(50, 70);
        bodyDef.allowSleep = false;

        body = world.createBody(bodyDef);
        body.createFixture(polygonShape, 5);

        System.out.println(polygonShape.testPoint(new Transform(new Vec2(0, 0), new Mat22(0, 0, 0, 0)), new Vec2(-200, 0)));

        // Create ground

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.shape = groundBox;

        groundBox.setAsBox(1000, 0);
        groundBody.createFixture(fixtureDef);
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
        enterGameLoop();
        cleanUp(false);
    }
}