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
public class JBulletDemo {

    private static final String WINDOW_TITLE = "JBullet Demo";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};
    private static final Transform DEFAULT_BALL_TRANSFORM = new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 35, 0), 1.0f));
    /**
     * The container for the JBullet physics world. This represents the collision data and motion data, as well as the
     * algorithms for collision detection and reaction.
     */
    private static DiscreteDynamicsWorld dynamicsWorld;
    /**
     * The spherical rigid bodies that fly around. Red is non-controllable, green is controllable.
     */
    private static Set<RigidBody> balls = new HashSet<RigidBody>();
    /**
     * The red spherical rigid body that can be pulled towards the camera by pressing the left mouse button.
     */
    private static RigidBody controlBall;
    private static EulerCamera camera = new EulerCamera.Builder()
            .setFieldOfView(300)
            .setNearClippingPane(0.3f)
            .setFarClippingPane(500)
            .setPosition(0, 25, 15)
            .build();
    /**
     * Whether to pull the control ball towards the camera.
     */
    private static boolean applyForce = false;
    /**
     * Whether to generate a new ball.
     */
    private static boolean createNewShape = false;
    /**
     * Whether to reset the position of the control ball.
     */
    private static boolean resetControlBall = false;
    /**
     * A LWJGL helper object that draws a sphere.
     */
    private static Sphere sphere = new Sphere();

    private static void setUpPhysics() {
        /**
         * The object that will roughly find out whether bodies are colliding.
         */
        BroadphaseInterface broadphase = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        /**
         * The object that will accurately find out whether, when, how, and where bodies are colliding.
         */
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        /**
         * The object that will determine what to do after collision.
         */
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();
        // Initialise the JBullet world.
        dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        // Set the gravity to 10 metres per second squared (m/s^2). Gravity affects all bodies with a mass larger than
        // zero.
        dynamicsWorld.setGravity(new Vector3f(0, -10, 0));
        // Initialise 'groundShape' to a static plane shape on the origin facing upwards ([0, 1, 0] is the normal).
        // 0.25 metres is an added buffer between the ground and potential colliding bodies, used to prevent the bodies
        // from partially going through the floor. It is also possible to think of this as the plane being lifted 0.25m.
        CollisionShape groundShape = new StaticPlaneShape(new Vector3f(0, 1, 0), 0.25f);
        // Initialise 'ballShape' to a sphere with a radius of 3 metres.
        CollisionShape ballShape = new SphereShape(3);
        // Initialise 'groundMotionState' to a motion state that simply assigns the origin [0, 0, 0] as the origin of
        // the ground.
        MotionState groundMotionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 0, 0), 1.0f)));
        // Initialise 'groundBodyConstructionInfo' to a value that contains the mass, the motion state, the shape, and the inertia (= resistance to change).
        RigidBodyConstructionInfo groundBodyConstructionInfo = new RigidBodyConstructionInfo(0, groundMotionState, groundShape, new Vector3f(0, 0, 0));
        // Set the restitution, also known as the bounciness or spring, to 0.25. The restitution may range from 0.0
        // not bouncy) to 1.0 (extremely bouncy).
        groundBodyConstructionInfo.restitution = 0.25f;
        // Initialise 'groundRigidBody', the final variable representing the ground, to a rigid body with the previously
        // assigned construction information.
        RigidBody groundRigidBody = new RigidBody(groundBodyConstructionInfo);
        // Add the ground to the JBullet world.
        dynamicsWorld.addRigidBody(groundRigidBody);
        // Initialise 'ballMotion' to a motion state that assigns a specified location to the ball.
        MotionState ballMotion = new DefaultMotionState(new Transform(DEFAULT_BALL_TRANSFORM));
        // Calculate the ball's inertia (resistance to movement) using its mass (2.5 kilograms).
        Vector3f ballInertia = new Vector3f(0, 0, 0);
        ballShape.calculateLocalInertia(2.5f, ballInertia);
        // Composes the ball's construction info of its mass, its motion state, its shape, and its inertia.
        RigidBodyConstructionInfo ballConstructionInfo = new RigidBodyConstructionInfo(2.5f, ballMotion, ballShape, ballInertia);
        // Set the restitution, also known as the bounciness or spring, to 0.5. The restitution may range from 0.0
        // not bouncy) to 1.0 (extremely bouncy).
        ballConstructionInfo.restitution = 0.5f;
        ballConstructionInfo.angularDamping = 0.95f;
        // Initialise 'controlBall', the final variable representing the controlled ball, to a rigid body with the
        // previously assigned construction information.
        controlBall = new RigidBody(ballConstructionInfo);
        // Disable 'sleeping' for the controllable ball.
        controlBall.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        // Add the control ball to the set of balls (more may be added by pressing 'g').
        balls.add(controlBall);
        // Add the control ball to the JBullet world.
        dynamicsWorld.addRigidBody(controlBall);
    }

    private static void render() {
        // Clear the contents of the window.
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // For every physics ball ...
        for (RigidBody body : balls) {
            // Push the model-view matrix.
            glPushMatrix();
            // Retrieve the ball's position from the JBullet body object.
            Vector3f ballPosition = body.getWorldTransform(new Transform()).origin;
            // Translate the model-view to the ball's position.
            glTranslatef(ballPosition.x, ballPosition.y, ballPosition.z);
            // Draw the controllable ball green and the uncontrollable balls red.
            // Set the draw style of the sphere drawing object to GLU_SILHOUETTE.
            // LWJGL JavaDoc: "The legal values are as follows: GLU.FILL: Quadrics are rendered with polygon primitives.
            // The polygons are drawn in a counterclockwise fashion with respect to their normals (as defined with
            // glu.quadricOrientation). GLU.LINE: Quadrics are rendered as a set of lines. GLU.SILHOUETTE:
            // Quadrics are rendered as a set of lines, except that edges separating coplanar faces will not be drawn.
            // GLU.POINT: Quadrics are rendered as a set of points."
            sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
            if (body.equals(controlBall)) {
                glColor4f(0, 1, 0, 1);
            } else {
                glColor4f(1, 0, 0, 1);
            }
            // Draw the sphere.
            // LWJGL JavaDoc: "draws a sphere of the given radius centered around the origin. The sphere is subdivided
            // around the z axis into slices and along the z axis into stacks (similar to lines of longitude and
            // latitude). If the orientation is set to GLU.OUTSIDE (with glu.quadricOrientation), then any normals
            // generated point away from the center of the sphere. Otherwise, they point toward the center of the sphere.
            // If texturing is turned on (with glu.quadricTexture), then texture coordinates are generated so that t
            // ranges from 0.0 at z=-radius to 1.0 at z=radius (t increases linearly along longitudinal lines), and s
            // ranges from 0.0 at the +y axis, to 0.25 at the +x axis, to 0.5 at the -y axis, to 0.75 at the -x axis,
            // and back to 1.0 at the +y axis."
            sphere.draw(3, 30, 30);
            glPopMatrix();
        }
        {
            // Draw the floor.
            glBegin(GL_QUADS);
            glColor4f(0.6f, 0.6f, 0.6f, 1);
            glVertex4f(-50, 0, -50, 1);
            glColor4f(0.85f, 0.85f, 0.85f, 1);
            glVertex4f(-50, 0, +50, 1);
            glColor4f(0.75f, 0.75f, 0.75f, 1);
            glVertex4f(+50, 0, +50, 1);
            glColor4f(0.5f, 0.5f, 0.5f, 1);
            glVertex4f(+50, 0, -50, 1);
            glEnd();
        }
    }

    private static void logic() {
        // Reset the model-view matrix.
        glLoadIdentity();
        // Apply the camera's position and orientation to the model-view matrix.
        camera.applyTranslations();
        // Runs the JBullet physics simulation for the specified time in seconds.
        dynamicsWorld.stepSimulation(1 / 60.0f);
        // Create a set of bodies that are to be removed.
        Set<RigidBody> bodiesToBeRemoved = new HashSet<RigidBody>();
        // For every physics ball ...
        for (RigidBody body : balls) {
            // Retrieve the ball's position from the JBullet body object.
            Vector3f position = body.getMotionState().getWorldTransform(new Transform()).origin;
            // If the ball is non-controllable and it is outside the world borders, add it to the set of bodies
            // that are to be removed.
            if (!body.equals(controlBall) && (position.x < -50 || position.x > 50 || position.z < -50 || position.z > 50)) {
                bodiesToBeRemoved.add(body);
            }
        }
        // For every physics ball that is to be removed ...
        for (RigidBody body : bodiesToBeRemoved) {
            // Remove it from the JBullet world.
            dynamicsWorld.removeRigidBody(body);
            // Remove it from the list of balls.
            balls.remove(body);
        }
        // If the attraction between the green ball and the camera is enabled ...
        if (applyForce) {
            // Retrieve the controllable ball's location.
            Transform controlBallTransform = new Transform();
            controlBall.getMotionState().getWorldTransform(controlBallTransform);
            Vector3f controlBallLocation = controlBallTransform.origin;
            Vector3f cameraPosition = new Vector3f(camera.x(), camera.y(), camera.z());
            // Calculate the force that is applied to the controllable ball as following:
            //  force = cameraPosition - controlBallLocation
            Vector3f force = new Vector3f();
            force.sub(cameraPosition, controlBallLocation);
            // Wake the controllable ball if it is sleeping.
            controlBall.activate(true);
            // Apply the force to the controllable ball.
            controlBall.applyCentralForce(force);
        }
        // If a new shape should be created ...
        if (createNewShape) {
            // Create the collision shape (sphere with radius of 3 metres).
            CollisionShape shape = new SphereShape(3);
            // Create the motion state (x and z are the same as the camera's).
            DefaultMotionState motionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(camera.x(), 35, camera.z()), 1.0f)));
            // Calculate the inertia (resistance to movement) using the ball's mass of 1 kilogram.
            Vector3f inertia = new Vector3f(0, 0, 0);
            shape.calculateLocalInertia(1.0f, inertia);
            RigidBodyConstructionInfo constructionInfo = new RigidBodyConstructionInfo(1, motionState, shape, inertia);
            constructionInfo.restitution = 0.75f;
            RigidBody rigidBody = new RigidBody(constructionInfo);
            balls.add(rigidBody);
            dynamicsWorld.addRigidBody(rigidBody);
            createNewShape = false;
        }
        // If the controllable ball's position and orientation should be reset ...
        if (resetControlBall) {
            // Set the position of the ball to (0, 50, 0).
            controlBall.setCenterOfMassTransform(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 50, 0), 1.0f)));
            // Reset the angular velocity (spinning movement) of the ball.
            controlBall.setAngularVelocity(new Vector3f(0, 0, 0));
            // Reset the linear velocity (x,y,z movement) of the ball.
            controlBall.setLinearVelocity(new Vector3f(0, 0, 0));
            resetControlBall = false;
        }
    }

    private static void input() {
        if (Mouse.isGrabbed()) {
            camera.processMouse(1.0f, 80, -80);
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
                if (Keyboard.getEventKey() == Keyboard.KEY_G) {
                    createNewShape = true;
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_F) {
                    resetControlBall = true;
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
