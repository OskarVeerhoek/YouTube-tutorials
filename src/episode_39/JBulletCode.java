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

package episode_39;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import utility.EulerCamera;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;

/**
 * Simulates 3D physics using the JBullet library. Use WSAD and the mouse to look around. Press the left mouse button
 * to attract the green control ball. Press 'g' to create additional red balls. Press 'f' to reset the location of the
 * control ball.
 * <p/>
 * Useful reading:
 * - Broad-Phase and Narrow-Phase Rendering: http://ianqvist.blogspot.nl/2010/07/broad-and-narrow-phase-collision.html
 * - Bullet User Manual: http://www.google.nl/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&ved=0CCoQFjAA&url=http%3A%2F%2Fbullet.googlecode.com%2Fsvn%2Ftrunk%2FBullet_User_Manual.pdf&ei=9LfKUY3HE4a20QXxhICgDw&usg=AFQjCNH54pT4TcXxDThj_G-7VL1zhk6UAg
 *
 * @author Oskar Veerhoek
 */
public class JBulletCode {

    private static final String WINDOW_TITLE = "JBullet Demo";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static final Transform DEFAULT_BALL_TRANSFORM = new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 35, 0), 1.0f));
    private static EulerCamera camera = new EulerCamera.Builder()
            .setFieldOfView(300)
            .setNearClippingPane(0.3f)
            .setFarClippingPane(500)
            .setPosition(0, 25, 15)
            .build();
    private static DynamicsWorld dynamicsWorld;
    private static Set<RigidBody> balls = new HashSet<RigidBody>();
    private static RigidBody controlBall;
    private static boolean applyForce = false;
    private static boolean createNewShape = false;
    private static boolean resetControlBall = false;
    private static Sphere sphere = new Sphere();

    private static void setUpPhysics() {
        BroadphaseInterface broadphase = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();
        dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        dynamicsWorld.setGravity(new Vector3f(0, -10 /* m/s2 */, 0));
        CollisionShape groundShape = new StaticPlaneShape(new Vector3f(0, 1, 0), 0.25f /* m */);
        CollisionShape ballShape = new SphereShape(3.0f);
        MotionState groundMotionState = new DefaultMotionState(new Transform(new Matrix4f(
                new Quat4f(0, 0, 0, 1),
                new Vector3f(0, 0, 0), 1.0f)));
        RigidBodyConstructionInfo groundBodyConstructionInfo = new RigidBodyConstructionInfo(0, groundMotionState, groundShape, new Vector3f(0, 0, 0));
        groundBodyConstructionInfo.restitution = 0.25f;
        RigidBody groundRigidBody = new RigidBody(groundBodyConstructionInfo);
        dynamicsWorld.addRigidBody(groundRigidBody);
        MotionState ballMotionState = new DefaultMotionState(DEFAULT_BALL_TRANSFORM);
        Vector3f ballInertia = new Vector3f(0, 0, 0);
        ballShape.calculateLocalInertia(2.5f, ballInertia);
        RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f, ballMotionState, ballShape, ballInertia);
        ballConstructionInfo.restitution = 0.5f;
        ballConstructionInfo.angularDamping = 0.95f;
        controlBall = new RigidBody(ballConstructionInfo);
        controlBall.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        balls.add(controlBall);
        dynamicsWorld.addRigidBody(controlBall);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        for (RigidBody body : balls) {
            glPushMatrix();
            Vector3f ballPosition = body.getWorldTransform(new Transform()).origin;
            glTranslatef(ballPosition.x, ballPosition.y, ballPosition.z);
            sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
            if (body.equals(controlBall)) {
                glColor4f(0, 1, 0, 1);
            } else {
                glColor4f(1, 0, 0, 1);
            }
            sphere.draw(3.0f, 30, 30);
            glPopMatrix();
        }
        glBegin(GL_QUADS);
        glColor4f(0.6f, 0.6f, 0.6f, 1);
        glVertex3f(-50, 0, -50);
        glColor4f(0.85f, 0.85f, 0.85f, 1);
        glVertex3f(-50, 0, 50);
        glColor4f(0.75f, 0.75f, 0.75f, 1);
        glVertex3f(50, 0, 50);
        glColor4f(0.5f, 0.5f, 0.5f, 1);
        glVertex3f(50, 0, -50);
        glEnd();
    }

    private static void logic() {
        glLoadIdentity();
        camera.applyTranslations();
        dynamicsWorld.stepSimulation(1.0f / 60.0f);
        Set<RigidBody> ballsToBeRemoved = new HashSet<RigidBody>();
        for (RigidBody body : balls) {
            Vector3f position = body.getMotionState().getWorldTransform(new Transform()).origin;
            if (!body.equals(controlBall) && (position.x < -50 || position.x > 50 || position.z < -50 || position.z > 50)) {
                ballsToBeRemoved.add(body);
            }
        }
        for (RigidBody body : ballsToBeRemoved) {
            balls.remove(body);
            dynamicsWorld.removeRigidBody(body);
        }
        if (applyForce) {
            Transform controlBallTransform = new Transform();
            controlBall.getMotionState().getWorldTransform(controlBallTransform);
            Vector3f controlBallLocation = controlBallTransform.origin;
            Vector3f cameraPosition = new Vector3f(camera.x(), camera.y(), camera.z());
            Vector3f force = new Vector3f();
            force.sub(cameraPosition, controlBallLocation);
            controlBall.activate(true);
            controlBall.applyCentralForce(force);
        }
        if (createNewShape) {
            CollisionShape shape = new SphereShape(3.0f);
            DefaultMotionState motionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(camera.x(), 35, camera.z()), 1)));
            Vector3f inertia = new Vector3f();
            shape.calculateLocalInertia(1.0f, inertia);
            RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1.0f, motionState, shape, inertia);
            constructionInfo.restitution = 0.75f;
            RigidBody body = new RigidBody(constructionInfo);
            dynamicsWorld.addRigidBody(body);
            balls.add(body);
            createNewShape = false;
        }
        if (resetControlBall) {
            controlBall.setCenterOfMassTransform(DEFAULT_BALL_TRANSFORM);
            controlBall.setAngularVelocity(new Vector3f(0, 0, 0));
            controlBall.setLinearVelocity(new Vector3f(0, 0, 0));
            resetControlBall = false;
        }
    }

    private static void input() {
        if (Mouse.isGrabbed()) {
            camera.processMouse(1.0f);
        }
        camera.processKeyboard(1000.0f / 60.0f, 4);
        if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1) && Mouse.isGrabbed()) {
            Mouse.setGrabbed(false);
        } else if (Mouse.isButtonDown(0) && Mouse.isGrabbed()) {
            applyForce = true;
        } else {
            applyForce = false;
        }
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_G:
                        createNewShape = true;
                        break;
                    case Keyboard.KEY_F:
                        resetControlBall = true;
                        break;
                }
            }
        }
    }

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    private static void setUpMatrices() {
        camera.setAspectRatio((float) Display.getWidth() / (float) Display.getHeight());
        camera.setFieldOfView(65);
        camera.applyPerspectiveMatrix();
    }

    private static void setUpStates() {
        camera.applyOptimalStates();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glLineWidth(2);
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
            Display.setVSyncEnabled(true);
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
        setUpPhysics();
        enterGameLoop();
        cleanUp(false);
    }

    private static class PhysicsSphere {
        private final Vector4f colour;
        private final float radius;
        private final int slices;
        private final int stacks;
        private final RigidBody physicsBody;
        private final DynamicsWorld physicsWorld;
        private org.lwjgl.util.glu.Sphere renderSphere;

        PhysicsSphere(Vector4f colour, Vector3f position, DynamicsWorld physicsWorld, float mass, float radius, int slices, int stacks) {
            this.colour = colour;
            this.radius = radius;
            this.slices = slices;
            this.stacks = stacks;
            MotionState motion = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), position, 1.0f)));
            CollisionShape shape = new SphereShape(radius);
            this.physicsBody = new RigidBody(mass, motion, shape);
            this.physicsWorld = physicsWorld;
            this.physicsWorld.addRigidBody(physicsBody);
            this.renderSphere = new org.lwjgl.util.glu.Sphere();
        }

        void destroy() {
            this.physicsWorld.removeRigidBody(this.physicsBody);
        }

        DynamicsWorld getPhysicsWorld() {
            return physicsWorld;
        }

        RigidBody getPhysicsBody() {
            return physicsBody;
        }

        Vector3f getPosition() {
            return physicsBody.getCenterOfMassPosition(new Vector3f());
        }

        void draw() {
            glPushMatrix();
            Vector3f position = getPosition();
            glTranslatef(position.x, position.y, position.z);
            glColor4f(colour.x, colour.y, colour.z, colour.w);
            renderSphere.draw(radius, slices, stacks);
            glPopMatrix();
        }

        Vector4f getColour() {
            return new Vector4f(colour);
        }

        float getRadius() {
            return radius;
        }
    }

}
