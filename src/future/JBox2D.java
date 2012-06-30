package future;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * 20 pixels = 1 metre
 * @author Oskar Veerhoek
 */
public class JBox2D {

    private static final String WINDOW_TITLE = "JBox 2D!";
    private static final int[] WINDOW_DIMENSIONS = {1280, 720};

    private static World world = new World(new Vec2(0, -3f), true);
    private static List<Body> bodies = new ArrayList<Body>();

    private static Vec2 worldToProjection(Vec2 worldCoordinate) {
        return worldCoordinate.mul(20);
    }

    private static float worldToProjection(float worldCoordinate) {
        return worldCoordinate * 20;
    }

    private static Vec2 projectionToWorld(Vec2 projectionCoordinate) {
        return projectionCoordinate.mul(0.05f);
    }

    private static float projectionToWorld(float projectionCoordinate) {
        return projectionCoordinate * 0.05f;
    }

    private static Vec2 windowToProjection(Vec2 windowCoordinate) {
        return windowCoordinate.mul(WINDOW_DIMENSIONS[0] / 320);
    }

    private static float windowToProjection(float windowCoordinate) {
        return windowCoordinate *WINDOW_DIMENSIONS[0] / 320;
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        for (int i = 0; i < world.getBodyCount(); i++) {
            for ( Body body : bodies )
            {
                if (body.getType() == BodyType.DYNAMIC) {
                    glPushMatrix();
                    glTranslatef(worldToProjection(body.getPosition().x), worldToProjection(body.getPosition().y), 0);
                    glRotatef((float) Math.toDegrees(body.getAngle()), 0, 0, 1);
                    glRectf(-15, -15, 15, 15);
                    glPopMatrix();
                }
            }
        }
    }

    private static void logic() {
        world.step(1 / 60f, 600, 600);
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_C:
                        PolygonShape boxShape = new PolygonShape();
                        boxShape.setAsBox(0.75f, 0.75f);

                        BodyDef boxDef = new BodyDef();
                        boxDef.type = BodyType.DYNAMIC;
                        boxDef.linearDamping = 0f;
                        boxDef.position.set(5, 3.5f);
                        boxDef.allowSleep = false;
                        boxDef.position.set(projectionToWorld(new Vec2(Mouse.getX()/4, Mouse.getY()/4)));
                        boxDef.allowSleep = false;

                        FixtureDef boxFixture = new FixtureDef();
                        boxFixture.friction = 2;
                        boxFixture.restitution = 0.2f;
                        boxFixture.density = 5;
                        boxFixture.shape = boxShape;

                        Body box = world.createBody(boxDef);
                        box.createFixture(boxFixture);
                        bodies.add(box);
                        break;
                }
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
            bodies.get(0).applyAngularImpulse(+0.1f);
        } else if (!Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
            bodies.get(0).applyAngularImpulse(-0.1f);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            bodies.get(0).applyLinearImpulse(new Vec2(0, +1), bodies.get(0).getPosition());
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            bodies.get(0).applyLinearImpulse(new Vec2(0, -1), bodies.get(0).getPosition());
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            bodies.get(0).applyLinearImpulse(new Vec2(-1, 0), bodies.get(0).getPosition());
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            bodies.get(0).applyLinearImpulse(new Vec2(+1, 0), bodies.get(0).getPosition());
        }
    }

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 320, 0, 180, -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpObjects() {
        // Create box

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.75f, 0.75f);

        BodyDef boxDef = new BodyDef();
        boxDef.type = BodyType.DYNAMIC;
        boxDef.linearDamping = 0f;
        boxDef.position.set(5, 3.5f);
        boxDef.allowSleep = false;

        FixtureDef boxFixture = new FixtureDef();
        boxFixture.friction = 2;
        boxFixture.restitution = 0.2f;
        boxFixture.density = 5;
        boxFixture.shape = boxShape;

        Body box = world.createBody(boxDef);
        box.createFixture(boxFixture);
        bodies.add(box);

        // Create ground

        BodyDef groundBodyDef = new BodyDef();

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBoxShape = new PolygonShape();

        FixtureDef groundBoxFixtureDef = new FixtureDef();
        groundBoxFixtureDef.friction = 4.5f;
        groundBoxFixtureDef.restitution = 0.2f;
        groundBoxFixtureDef.shape = groundBoxShape;

        groundBoxShape.setAsBox(1000, 0);
        groundBody.createFixture(groundBoxFixtureDef);

        // Create left wall

        BodyDef leftWallBodyDef = new BodyDef();

        Body leftWallBody = world.createBody(leftWallBodyDef);

        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 1000);

        FixtureDef leftWallFixtureDef = new FixtureDef();
        leftWallFixtureDef.friction = 0.3f;
        leftWallFixtureDef.restitution = 0.5f;
        leftWallFixtureDef.shape = leftWallShape;

        leftWallBody.createFixture(leftWallFixtureDef);

        // Create right wall

        BodyDef rightWallBodyDef = new BodyDef();
        rightWallBodyDef.position.set(projectionToWorld(new Vec2(320, 0)));

        Body rightWallBody = world.createBody(rightWallBodyDef);

        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 1000);

        FixtureDef rightWallFixtureDef = new FixtureDef();
        rightWallFixtureDef.friction = 0.3f;
        rightWallFixtureDef.restitution = 0.5f;
        rightWallFixtureDef.shape = rightWallShape;

        rightWallBody.createFixture(rightWallFixtureDef);

        // Create top wall

        BodyDef topWallBodyDef = new BodyDef();
        topWallBodyDef.position.set(projectionToWorld(new Vec2(0, 180)));

        Body topWallBody = world.createBody(topWallBodyDef);

        PolygonShape topWallShape = new PolygonShape();
        topWallShape.setAsBox(1000, 0);

        FixtureDef topWallFixtureDef = new FixtureDef();
        topWallFixtureDef.friction = 0.3f;
        topWallFixtureDef.restitution = 0.5f;
        topWallFixtureDef.shape = topWallShape;

        topWallBody.createFixture(topWallFixtureDef);
    }

    private static void setUpStates() {
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_POLYGON_SMOOTH);
        glEnable(GL_POINT_SMOOTH);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLineWidth(3);
    }

    private static void update() {
        Display.update();
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
            Display.setVSyncEnabled(true);
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