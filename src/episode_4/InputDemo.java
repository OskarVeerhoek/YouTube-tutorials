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

package episode_4;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * TODO: Investigate necessity of Thread.sleep() in Box class, thanks Fabien!
 * Shows how to use input coupled with threads to achieve cool results.
 *
 * @author Oskar
 */
public class InputDemo {

    private static List<Box> shapes = new ArrayList<Box>(16);
    private static boolean somethingIsSelected = false;
    private static boolean randomColorCooldown = false;

    private static synchronized void setRandomColorCooldown(boolean value) {
        randomColorCooldown = value;
    }

    private static synchronized boolean getRandomColorCooldown() {
        return randomColorCooldown;
    }

    public static void main(String args[]) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Input Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        shapes.add(new Box(15, 15));
        shapes.add(new Box(100, 150));
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            while (Keyboard.next()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) {
                    shapes.add(new Box(15, 15));
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Display.destroy();
                System.exit(0);
            }
            for (final Box box : shapes) {
                if (Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 480 - Mouse.getY()) && !somethingIsSelected) {
                    somethingIsSelected = true;
                    box.selected = true;
                }
                if (Mouse.isButtonDown(2) && box.inBounds(Mouse.getX(), 480 - Mouse.getY()) && !somethingIsSelected) {
                    if (getRandomColorCooldown() == false) {
                        setRandomColorCooldown(true);
                    } else {
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                if (getRandomColorCooldown() == true) {
                                    try {
                                        System.out.println("HEY!");
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    box.randomizeColors();
                                    randomColorCooldown = false;
                                }

                            }
                        }).start();
                        //try {
                        //    Thread.sleep(500);
                        //} catch (InterruptedException e) {
                        //    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        //}
                        //randomColorCooldown = false;
                    }
                }
                if (Mouse.isButtonDown(1)) {
                    box.selected = false;
                    somethingIsSelected = false;
                }

                if (box.selected) {
                    box.update(Mouse.getDX(), -Mouse.getDY());
                }

                box.draw();
            }

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }


    private static class Box {

        public int x, y;
        public boolean selected = false;
        private float colorRed, colorBlue, colorGreen;

        Box(int x, int y) {
            this.x = x;
            this.y = y;

            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }

        boolean inBounds(int mousex, int mousey) {
            if (mousex > x && mousex < x + 50 && mousey > y && mousey < y + 50) {
                return true;
            } else {
                return false;
            }
        }

        void update(int dx, int dy) {
            x += dx;
            y += dy;
        }

        void randomizeColors() {
            Random randomGenerator = new Random();
            colorRed = randomGenerator.nextFloat();
            colorBlue = randomGenerator.nextFloat();
            colorGreen = randomGenerator.nextFloat();
        }

        void draw() {
            glColor3f(colorRed, colorGreen, colorBlue);
            glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + 50, y);
            glVertex2f(x + 50, y + 50);
            glVertex2f(x, y + 50);
            glEnd();
        }
    }
}
