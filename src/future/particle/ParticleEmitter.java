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

package future.particle;

import org.lwjgl.util.vector.Vector3f;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;

/** The source of particles. */
public class ParticleEmitter {

    private static int lastId = 0;
    /** The location of the particle emitter in world coordinates. */
    private final Vector3f position;
    /** The amount of particles generated per millisecond (or equivalent time unit). */
    private final float particleSpawningRate;
    /** The lifetime of a particle, starting from when it was generated in milliseconds (or equivalent time unit). */
    private final float particleLifeTime;
    /**
     * The degree of randomness in the generation of the particle lifetime. 0 is no randomness. 0.5 is 50% possible
     * deviancy from the central value, and 1 is 100% possible deviance from the central value.
     */
    private final float particleLifeTimeFactor;
    /** The initial velocity of the particle. */
    private final Vector3f particleInitialVelocity;
    /**
     * The degree of randomness in the generation of the initial velocity. 0 is no randomness. 0.5 is 50% possible
     * deviancy from the central value, and 1 is 100% possible deviance from the central value.
     */
    private final float particleInitialVelocityFactor;
    private final Particle[] particles = new Particle[1024];
    private final Set<Integer> particlesToBeRemoved = new HashSet<Integer>();
    private float previousAmountOfParticlesToGenerate;
    private int amountOfParticles = 0;

    public ParticleEmitter(Vector3f position, float particleSpawningRate, float particleLifeTime,
                           Vector3f particleInitialVelocity) {
        this.position = position;
        this.particleSpawningRate = particleSpawningRate;
        this.particleLifeTime = particleLifeTime;
        this.particleInitialVelocity = particleInitialVelocity;
        this.particleInitialVelocityFactor = 0.2f;
        this.particleLifeTimeFactor = 0.2f;
    }

    public void initialise() {

    }

    /**
     * Update the particle emitter. This does not render anything.
     *
     * @param timePassed the time passed since the last time this method was called in milliseconds
     */
    public void update(float timePassed) {
        float amountOfParticlesToGenerate = previousAmountOfParticlesToGenerate + particleSpawningRate * timePassed;
        if (amountOfParticlesToGenerate < 1) {
            previousAmountOfParticlesToGenerate += amountOfParticlesToGenerate;
            return;
        }
        while (amountOfParticlesToGenerate > 0) {
            //            Particle newParticle = new Particle();
            //            newParticle.position = new Vector3f(0, 0, 0);
            //            newParticle.colour = new Vector3f(1, 0, 0);
            //            newParticle.velocity = new Vector3f(0.1f, 0.1f, 0);
            //            newParticle.expireTime = 1000;
            Particle newParticle = new Particle(new Vector3f(), new Vector3f(1, 1, 1), particleInitialVelocity,
                    particleLifeTime);
            //            Particle newParticle = new Particle(new Vector3f(), new Vector3f(1, 1, 1),
            // particleInitialVelocity.translate((float) (Math.random() - 0.5f) / 50, 0, 0), particleLifeTime);
            particles[newParticle.id] = newParticle;
            amountOfParticles++;
            amountOfParticlesToGenerate--;
        }
        for (int i = 0; i < particles.length; i++) {
            if (particles[i] != null) {
                particles[i].position.translate(particles[i].velocity.x, particles[i].velocity.y,
                        particles[i].velocity.z);
                particles[i].velocity.translate(0, -0.001f, 0);
                particles[i].expireTime -= timePassed;
                if (particles[i].expireTime < 0 || particles[i].position.y < -1 || particles[i].position.x < -1 ||
                        particles[i].position.x > 1) {
                    particlesToBeRemoved.add(particles[i].id);
                }
            }
        }
        for (int i : particlesToBeRemoved) {
            particles[i] = null;
            amountOfParticles--;
        }
        particlesToBeRemoved.clear();
    }

    public void draw() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glPushMatrix();
        glTranslatef(position.x, position.y, position.z);
        glBegin(GL_POINTS);
        for (Particle particle : particles) {
            if (particle != null) {
                glColor3f(particle.colour.x, particle.colour.y, particle.colour.z);
                glVertex3f(particle.position.x, particle.position.y, particle.position.z);
            }
        }
        glEnd();
        glPopMatrix();
        glPopAttrib();
    }

    private static class Particle {

        public int id;
        /** The location of the particle in object space. */
        public Vector3f position;
        /** The colour of the particle. */
        public Vector3f colour;
        /** The velocity of the particle. */
        public Vector3f velocity;
        /** The time left until the particle expires in milliseconds. */
        public float expireTime;

        private Particle() {
            if (lastId > 512) {
                lastId = 0;
            }
        }

        private Particle(Vector3f position, Vector3f colour, Vector3f velocity, float expireTime) {
            this.position = position;
            this.colour = colour;
            this.velocity = velocity;
            this.expireTime = expireTime;
            this.id = lastId++;
            if (lastId > 512) {
                lastId = 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Particle particle = (Particle) o;

            if (id == particle.id) {
                return true;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = position.hashCode();
            result = 31 * result + colour.hashCode();
            result = 31 * result + velocity.hashCode();
            result = 31 * result + (expireTime != +0.0f ? Float.floatToIntBits(expireTime) : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Particle{" +
                    "position=" + position +
                    ", colour=" + colour +
                    ", velocity=" + velocity +
                    ", expireTime=" + expireTime +
                    ", id=" + id +
                    '}';
        }
    }
}
