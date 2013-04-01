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

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA. User: TheCodingUniverse Date: 29/03/2013 Time: 13:34 To change this template use File |
 * Settings | File Templates.
 */
public class ParticleDemo {

    private static ParticleEmitter particleEmitter = new ParticleEmitter(new Vector3f(), 0.001f, 100, new Vector3f(0,
            0.1f, 0));

    public static void main(String[] args) {
        setUpDisplay();
        setUpStates();
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

    private static void input() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    //                    resetParticle = true;
                }
            }
        }
    }

    private static void logic() {
        particleEmitter.update(16);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        particleEmitter.draw();
    }

    private static void refresh() {
        Display.sync(60);
        Display.update();
    }

    private static void shutdown() {
        Display.destroy();
    }
}

