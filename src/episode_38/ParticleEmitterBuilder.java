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

import org.lwjgl.util.vector.Vector3f;

public class ParticleEmitterBuilder {

    private Vector3f location = new Vector3f(0, 0, 0);
    private float spawningRate = 3;
    private int particleLifeTime = 300;
    private Vector3f gravity = new Vector3f(0, -0.0003f, 0);
    private boolean enable3D = false;
    private Vector3f initialVelocity = new Vector3f(0, 0, 0);
    private float velocityModifier = 1.0f;

    /**
     * Sets the location of the particle emitter.
     *
     * @param location the location of the particle emitter
     */
    public ParticleEmitterBuilder setLocation(Vector3f location) {
        this.location = location;
        return this;
    }

    /**
     * Set the modifier of the particle velocity. 2.0 makes the initial velocity of the particles twice as large.
     *
     * @param velocityModifier the particle velocity modifier
     */
    public ParticleEmitterBuilder setVelocityModifier(float velocityModifier) {
        this.velocityModifier = velocityModifier;
        return this;
    }

    /**
     * Enables or disable 3D particle generation. If 3D generation is enabled, the z-coordinate of particles is
     * modifiable.
     *
     * @param enable3D whether 3D particle generation is enabled
     */
    public ParticleEmitterBuilder setEnable3D(boolean enable3D) {
        this.enable3D = enable3D;
        return this;
    }

    /**
     * Sets the spawning rate of the particle emitter, i.e. the amount of particles generated every call to
     * 'ParticleEmitter.update()'.
     *
     * @param spawningRate the spawning rate of the particle emitter
     */
    public ParticleEmitterBuilder setSpawningRate(float spawningRate) {
        this.spawningRate = spawningRate;
        return this;
    }

    /**
     * Sets the particle life time in amount of calls to 'ParticleEmitter.update()'. For example, if it is set to 300,
     * then the particle will dissapear amount 'ParticleEmitter.update()' has been called 300 times.
     *
     * @param particleLifeTime the life time of the particle
     */
    public ParticleEmitterBuilder setParticleLifeTime(int particleLifeTime) {
        this.particleLifeTime = particleLifeTime;
        return this;
    }

    /**
     * Sets the gravity acceleration applied to all the particles each call to 'ParticleEmitter.update()'.
     *
     * @param gravity the gravity acceleration
     */
    public ParticleEmitterBuilder setGravity(Vector3f gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * Sets the initial velocity which is taken as base value for the initial velocity for all particles. Note that the
     * actual velocity is randomised.
     *
     * @param initialVelocity the base initial velocity
     */
    public ParticleEmitterBuilder setInitialVelocity(Vector3f initialVelocity) {
        this.initialVelocity = initialVelocity;
        return this;
    }

    public ParticleEmitter createParticleEmitter() {
        return new ParticleEmitter(location, spawningRate, particleLifeTime, gravity, enable3D, initialVelocity,
                velocityModifier);
    }
}