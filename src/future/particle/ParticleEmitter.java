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

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/** The source of particles. */
public class ParticleEmitter {

    /** The location of the particle emitter in world coordinates. */
    private final Vector3f location;
    /** The amount of particles generated per frame update. */
    private final float spawningRate;
    /** The lifetime of a particle in frame updates. */
    private final int particleLifeTime;
    private final List<Particle> particles = new ArrayList<Particle>();

    public ParticleEmitter(Vector3f location, float spawningRate, int particleLifeTime) {
        this.location = location;
        System.out.println(location);
        this.spawningRate = spawningRate;
        this.particleLifeTime = particleLifeTime;
    }

    private Particle generateNewParticle(int dx, int dy) {
        return new Particle(new Vector3f(location), new Vector3f(
                (float) (Math.random() - 0.5f + dx/10) / 120, (float) (Math.random() -0.3f + dy/10) / 60, 0), particleLifeTime);
    }

    /**
     * Update the particle emitter. This does not render anything.
     */
    public void update() {
        float mouseX = (Mouse.getX() / 640f - 0.5f) * 2;
        float mouseY = (Mouse.getY() / 480f - 0.5f) * 2;
        if (Mouse.isButtonDown(0)) {
            location.setX(mouseX);
            location.setY(mouseY);
            int dx = Mouse.getDX();
            int dy = Mouse.getDY();
            for (int i = 0; i < spawningRate; i++) {
                particles.add(generateNewParticle(dx, dy));
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);
            particle.update();
            if (particle.isDestroyed()) {
                particles.remove(i);
                i--;
            }
        }
    }

    public void draw() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glBegin(GL_POINTS);
        for (Particle particle : particles) {
            float colour = (float) particle.expireTime / particleLifeTime;
            glColor3f(colour, 0.2f * colour, 0.2f * colour);
            glVertex3f(particle.position.x, particle.position.y, particle.position.z);
        }
        glEnd();
        glPopAttrib();
    }

    private static class Particle {

        /** The location of the particle in object space. */
        public Vector3f position;
        /** The velocity of the particle. */
        public Vector3f velocity;
        /** The time left until the particle expires in milliseconds. */
        public int expireTime;

        private Particle(Vector3f position, Vector3f velocity, int expireTime) {
            this.position = position;
            this.velocity = velocity;
            this.expireTime = expireTime;
        }

        public boolean isDestroyed() {
            return expireTime == 0;
        }

        public void update() {
            position.translate(velocity.x, velocity.y, velocity.z);
            velocity.translate(0, -0.0001f, 0);
            expireTime -= 1;
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

            if (Float.compare(particle.expireTime, expireTime) != 0) {
                return false;
            }
            if (!position.equals(particle.position)) {
                return false;
            }
            if (!velocity.equals(particle.velocity)) {
                return false;
            }

            return true;
        }

        @Override
        public String toString() {
            return "Particle{" +
                    "position=" + position +
                    ", velocity=" + velocity +
                    ", expireTime=" + expireTime +
                    '}';
        }

        @Override
        public int hashCode() {
            int result = position.hashCode();
            result += 31 * result + velocity.hashCode();
            result += 31 * result + (expireTime != +0.0f ? Float.floatToIntBits(expireTime) : 0);
            return result;
        }
    }
}
