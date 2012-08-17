/*
 * Copyright (c) 2012, Oskar Veerhoek
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

package utility;

/**
 * For frame-rate independent movement
 *
 * @author Oskar Veerhoek
 */
public class LWJGLTimer {
    private long lastTime; // nanoseconds
    private long lastPrintTime; // nanoseconds
    private final boolean printTime;
    private double elapsedTime;

    /**
     * Creates a timer.
     */
    public LWJGLTimer() {
        printTime = false;
    }

    /**
     * Creates a timer.
     *
     * @param printTime if the timer prints out the elapsed time every second
     */
    public LWJGLTimer(boolean printTime) {
        this.printTime = printTime;
    }

    /**
     * Initializes the timer. Call this just before entering the game loop.
     */
    public void initialize() {
        lastTime = lastPrintTime = System.nanoTime();
    }

    /**
     * @return the elapsed time since the the next to last update call
     */
    public double getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Updates the timer. Call this once every iteration of the game loop.
     *
     * @return the elapsed time in milliseconds
     */
    public double update() {
        long elapsedTime = System.nanoTime() - lastTime;
        if (printTime) {
            long elapsedPrintTime = System.nanoTime() - lastPrintTime;
            if (elapsedPrintTime / (double) 1000000 > 1000) {
                System.out.println("Elapsed time (ms) : " + (elapsedTime / (double) 1000000));
                lastPrintTime = System.nanoTime();
            }
        }
        lastTime = System.nanoTime();
        this.elapsedTime = elapsedTime / (double) 1000000;
        return this.elapsedTime;
    }

}
