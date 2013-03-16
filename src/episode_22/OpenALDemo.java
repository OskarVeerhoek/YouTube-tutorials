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

package episode_22;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.lwjgl.openal.AL10.*;

public class OpenALDemo {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("OpenAL Demo");
            Display.create();
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            AL.destroy();
            System.exit(1);
        }
        WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res" + File.separatorChar +
                "sounds" + File.separatorChar + "thump.wav")));
        int buffer = alGenBuffers();
        alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        int source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);
        while (!Display.isCloseRequested()) {
            while (Keyboard.next()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    alSourcePlay(source);
                }
            }
            Display.update();
            Display.sync(60);
        }
        alDeleteBuffers(buffer);
        AL.destroy();
        Display.destroy();
        System.exit(0);
    }
}