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
