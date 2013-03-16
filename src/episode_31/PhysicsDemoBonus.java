/*
 * Copyright (c) 2013, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

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
public class PhysicsDemoBonus {

    private static final String WINDOW_TITLE = "Physics in 2D!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static final World world = new World(new Vec2(0, -9.8f), false);
    private static final Set<Body> bodies = new HashSet<Body>();

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
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_C:
                        Vec2 bodyPosition = new Vec2(Mouse.getX(), Mouse.getY()).mul(0.5f).mul(1 / 30f);
                        BodyDef boxDef = new BodyDef();
                        boxDef.position.set(bodyPosition);
                        boxDef.type = BodyType.DYNAMIC;
                        PolygonShape boxShape = new PolygonShape();
                        boxShape.setAsBox(0.75f, 0.75f);
                        Body box = world.createBody(boxDef);
                        FixtureDef boxFixture = new FixtureDef();
                        boxFixture.density = 0.1f;
                        boxFixture.shape = boxShape;
                        box.createFixture(boxFixture);
                        bodies.add(box);
                        break;
                }
            }
        }
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
        boxDef.position.set(320 / 30 / 2f, 240 / 30 / 2f);
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

        BodyDef leftWallDef = new BodyDef();
        leftWallDef.position.set(0, 0);
        leftWallDef.type = BodyType.STATIC;
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 1000);
        Body leftWall = world.createBody(leftWallDef);
        FixtureDef leftWallFixture = new FixtureDef();
        leftWallFixture.density = 1;
        leftWallFixture.restitution = 0.3f;
        leftWallFixture.shape = leftWallShape;
        leftWall.createFixture(leftWallFixture);

        BodyDef rightWallDef = new BodyDef();
        rightWallDef.position.set(320f / 30, 0);
        rightWallDef.type = BodyType.STATIC;
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 1000);
        Body rightWall = world.createBody(rightWallDef);
        FixtureDef rightWallFixture = new FixtureDef();
        rightWallFixture.density = 1;
        rightWallFixture.restitution = 0.3f;
        rightWallFixture.shape = rightWallShape;
        rightWall.createFixture(rightWallFixture);

        BodyDef topWallDef = new BodyDef();
        topWallDef.position.set(0, 240f / 30);
        topWallDef.type = BodyType.STATIC;
        PolygonShape topWallShape = new PolygonShape();
        topWallShape.setAsBox(1000, 0);
        Body topWall = world.createBody(topWallDef);
        FixtureDef topWallFixture = new FixtureDef();
        topWallFixture.density = 1;
        topWallFixture.restitution = 0.3f;
        topWallFixture.shape = topWallShape;
        topWall.createFixture(topWallFixture);
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
        setUpObjects();
        setUpMatrices();
        enterGameLoop();
        cleanUp(false);
    }
}
