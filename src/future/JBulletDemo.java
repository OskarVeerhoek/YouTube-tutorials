package future;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Oskar Veerhoek
 */
public class JBulletDemo {

    private static final String WINDOW_TITLE = "JBullet Physics!";
    private static final int[] WINDOW_DIMENSIONS = {640, 480};

    private static BroadphaseInterface broadphaseInterface = new DbvtBroadphase();
    private static DefaultCollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
    private static CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
    private static SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();
    private static DiscreteDynamicsWorld dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphaseInterface, solver, collisionConfiguration);

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT); // | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT
        // Add rendering code here
    }

    private static void logic() {
        // Add logic code here
    }

    private static void input() {
        // Add input handling code here
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
//        glEnable(GL_DEPTH_TEST);
//        glEnable(GL_LIGHTING);
//        glEnable(GL_BLEND);
//        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    private static void setUpJBullet() {
        dynamicsWorld.setGravity(new Vector3f(0, -10, 0));
        dynamicsWorld.getDispatchInfo().allowedCcdPenetration = 0f;
        CollisionShape groundShape = new BoxShape(new Vector3f(100f, 50f, 0f));
        Transform groundTransform = new Transform();
        groundTransform.setIdentity();
        groundTransform.origin.set(0f, -56f, 0f);
        float mass = 0f;
        Vector3f localInertia = new Vector3f(0, 0, 0);
        DefaultMotionState motionState = new DefaultMotionState(groundTransform);
        RigidBodyConstructionInfo rigidBodyConstructionInfo = new RigidBodyConstructionInfo(mass, motionState, groundShape, localInertia);
        RigidBody body = new RigidBody(rigidBodyConstructionInfo);
        dynamicsWorld.addRigidBody(body);
        BoxShape shape = new BoxShape(new Vector3f(2, 2, 0));
        shape.calculateLocalInertia(mass, localInertia);

        Transform startTransform = new Transform();
        startTransform.setIdentity();
        startTransform.origin.set(0, 0, 0);

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