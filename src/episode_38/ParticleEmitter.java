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

package episode_38;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/** The source of particles. */
public class ParticleEmitter {

    private static Random randomGenerator = new Random();
    private final List<Particle> particles;
    private Vector3f location;
    private float spawningRate;
    private int particleLifeTime;
    private Vector3f gravity;
    private Vector3f initialVelocity;
    private boolean enable3D;
    private float velocityModifier;

    public ParticleEmitter() {
        this(new Vector3f(0, 0, 0), 3, 300, new Vector3f(0, -0.0003f, 0), false, new Vector3f(-0.5f, 0, -0.5f), 1.0f);
    }

    /**
     * @param location the location of the particle emitter
     * @param spawningRate the amount of particles generated every call to 'ParticleEmitter.update()'
     * @param particleLifeTime the life time of the particle in calls to 'ParticleEmitter.update()'
     * @param gravity the gravity acceleration applied to all the particles each call to 'ParticleEmitter.update()'
     * @param enable3D whether 3D particle generation is enabled
     * @param initialVelocity the base initial velocity
     * @param velocityModifier the particle velocity modifier
     */
    public ParticleEmitter(Vector3f location, float spawningRate, int particleLifeTime, Vector3f gravity,
                           boolean enable3D, Vector3f initialVelocity, float velocityModifier) {
        this.location = location;
        this.spawningRate = spawningRate;
        this.particleLifeTime = particleLifeTime;
        this.gravity = gravity;
        this.enable3D = enable3D;
        this.particles = new ArrayList<Particle>((int) spawningRate * particleLifeTime);
        this.initialVelocity = initialVelocity;
        this.velocityModifier = velocityModifier;
    }

    public float getVelocityModifier() {
        return velocityModifier;
    }

    public void setVelocityModifier(float velocityModifier) {
        this.velocityModifier = velocityModifier;
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public float getSpawningRate() {
        return spawningRate;
    }

    public void setSpawningRate(float spawningRate) {
        this.spawningRate = spawningRate;
    }

    public Vector3f getGravity() {
        return gravity;
    }

    public void setGravity(Vector3f gravity) {
        this.gravity = gravity;
    }

    public int getParticleLifeTime() {
        return particleLifeTime;
    }

    public void setParticleLifeTime(int particleLifeTime) {
        this.particleLifeTime = particleLifeTime;
    }

    public Vector3f getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(Vector3f initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public boolean isEnable3D() {
        return enable3D;
    }

    public void setEnable3D(boolean enable3D) {
        this.enable3D = enable3D;
    }

    private Particle generateNewParticle(int dx, int dy) {
        Vector3f particleLocation = new Vector3f(location);
        Vector3f particleVelocity = new Vector3f();
        float randomX = (float) randomGenerator.nextDouble() - 0.5f;
        float randomY = (float) randomGenerator.nextDouble() - 0.5f;
        float randomZ = 0;
        if (enable3D) {
            randomZ = (float) randomGenerator.nextDouble() - 0.5f;
        }
        particleVelocity.x = (randomX + initialVelocity.x + dx / 10) / 120;
        particleVelocity.y = (randomY + initialVelocity.y + dy / 10) / 120;
        if (enable3D) {
            particleVelocity.z = (randomZ + initialVelocity.z) / 120;
        }
        particleVelocity.scale(velocityModifier);
        return new Particle(particleLocation, particleVelocity, particleLifeTime);
    }

    /** Update the particle emitter. This does not render anything. */
    public void update() {
        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);
            particle.update(gravity);
            if (particle.isDestroyed()) {
                particles.remove(i);
                i--;
            }
        }
        if (enable3D) {
            if (!Mouse.isButtonDown(0)) {
                for (int i = 0; i < spawningRate; i++) {
                    particles.add(generateNewParticle(0, 0));
                }
            }
        } else {
            float mouseX = ((float) Mouse.getX() / Display.getWidth() - 0.5f) * 2;
            float mouseY = ((float) Mouse.getY() / Display.getHeight() - 0.5f) * 2;
            if (Mouse.isButtonDown(0)) {
                location.set(mouseX, mouseY);
                int dx = Mouse.getDX();
                int dy = Mouse.getDY();
                for (int i = 0; i < spawningRate; i++) {
                    particles.add(generateNewParticle(dx, dy));
                }
            }
        }
    }

    public void draw() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glBegin(GL_POINTS);
        for (Particle particle : particles) {
            float colour = (float) particle.expireTime / particleLifeTime;
            glColor4f(colour, 0.2f * colour, 0.2f * colour, colour);
            glVertex3f(particle.position.x, particle.position.y, particle.position.z);
        }
        glEnd();
        glPopAttrib();
    }

    private static class Particle {

        public Vector3f position;
        public Vector3f velocity;
        public int expireTime;

        private Particle(Vector3f position, Vector3f velocity, int expireTime) {
            this.position = position;
            this.velocity = velocity;
            this.expireTime = expireTime;
        }

        public boolean isDestroyed() {
            return expireTime <= 0;
        }

        public void update(Vector3f gravity) {
            position.translate(velocity.x, velocity.y, velocity.z);
            velocity.translate(gravity.x, gravity.y, gravity.z);
            expireTime -= 1;
        }

        @Override
        public String toString() {
            return "Particle{" +
                    "position=" + position +
                    ", velocity=" + velocity +
                    ", expireTime=" + expireTime +
                    '}';
        }
    }
}
