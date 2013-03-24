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

package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/** NOT DONE YET */
public class ParticleSystem {

    private static long timeStarted = -1;
    private static Particle[] particles = new Particle[100];
    private static boolean resetParticle = false;

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
        setUpTimer();
        setUpParticles();
        while (!Display.isCloseRequested()) {
            input();
            logic();
            render();
            refresh();
        }
        shutdown();
        System.exit(0);
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Particle System");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
    }

    private static void setUpStates() {
        glPointSize(1.5f);
    }

    private static void setUpTimer() {
        timeStarted = System.nanoTime();
    }

    private static void setUpParticles() {
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(new Vector2f(), new Vector2f((float) (Math.random() - 0.5f) / 20,
                    (float) Math.random() / 20), new Vector3f(1, 1, 1));
        }
    }

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    resetParticle = true;
                }
            }
        }
    }

    private static void logic() {
        if (resetParticle) {
            setUpParticles();
            timeStarted = System.nanoTime();
            resetParticle = false;
        } else {
            double timePassedInSeconds = (System.nanoTime() - timeStarted) / 1000000000d;
            if (timePassedInSeconds > 2) {
                resetParticle = true;
            }
            for (int i = 0; i < particles.length; i++) {
                particles[i].colour.scale((float) ((10 - timePassedInSeconds) / 10));
                particles[i].colour.x = particles[i].position.length();
                particles[i].position.translate(particles[i].velocity.x, particles[i].velocity.y);
                particles[i].velocity.translate(0, -0.001f);
            }
        }
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glBegin(GL_POINTS);
        for (Particle particle : particles) {
            glColor3f(particle.colour.x, particle.colour.y, particle.colour.z);
            glVertex2f(particle.position.x, particle.position.y);
        }
        glEnd();
    }

    private static void refresh() {
        Display.sync(60);
        Display.update();
    }

    private static void shutdown() {
        Display.destroy();
    }
}

class Particle {

    Vector2f position = new Vector2f(0, 0);
    Vector2f velocity = new Vector2f(0, 0);
    Vector3f colour = new Vector3f(1, 1, 1);

    public Particle(Vector2f position, Vector2f velocity, Vector3f colour) {
        this.position = position;
        this.velocity = velocity;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", colour=" + colour +
                '}';
    }
}
