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

package episode_11_12_13;

import org.lwjgl.input.Mouse;

import static org.lwjgl.opengl.GL11.*;

public class SelectionBox {

    private float x, y;
    private float size;
    private BlockType selection;
    private Block temp_selection;

    public SelectionBox(float x, float y, float size, BlockType selection) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.selection = selection;
        this.temp_selection = new Block(selection, -1, -1);
    }

    public boolean isMouseHover() {
        int mouse_x = Mouse.getX();
        int mouse_y = 480 - Mouse.getY() - 1;
        return mouse_x >= x && mouse_y >= y && mouse_x <= x + size && mouse_y <= y + size;
    }

    public void draw() {
        glLoadIdentity();
        temp_selection.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(size, 0);
        glTexCoord2f(1, 1);
        glVertex2f(size, size);
        glTexCoord2f(0, 1);
        glVertex2f(0, size);
        glEnd();
        glLoadIdentity();
        glEnd();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BlockType getSelection() {
        return selection;
    }

    public void setSelection(BlockType selection) {
        this.selection = selection;
    }
}
