package episode_11_12_13;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static episode_11_12_13.World.*;

public class BlockGrid {

    private Block[][] blocks = new Block[BLOCKS_WIDTH][BLOCKS_HEIGHT];

    public BlockGrid(File loadFile) {
    }

    public BlockGrid() {
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y
                        * BLOCK_SIZE);
            }
        }
    }

    public void load(File loadFile) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(loadFile);
            Element root = document.getRootElement();
            for (Object block : root.getChildren()) {
                Element e = (Element) block;
                int x = Integer.parseInt(e.getAttributeValue("x"));
                int y = Integer.parseInt(e.getAttributeValue("y"));
                blocks[x][y] = new Block(BlockType.valueOf(e.getAttributeValue("type")), x * BLOCK_SIZE, y
                        * BLOCK_SIZE);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save(File saveFile) {
        Document document = new Document();
        Element root = new Element("blocks");
        document.setRootElement(root);
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                Element block = new Element("block");
                block.setAttribute("x", String.valueOf((int) (blocks[x][y].getX() / BLOCK_SIZE)));
                block.setAttribute("y", String.valueOf((int) (blocks[x][y].getY() / BLOCK_SIZE)));
                block.setAttribute("type",
                        String.valueOf(blocks[x][y].getType()));
                root.addContent(block);
            }
        }
        XMLOutputter output = new XMLOutputter();
        try {
            output.output(document, new FileOutputStream(saveFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAt(int x, int y, BlockType b) {
        blocks[x][y] = new Block(b, x * BLOCK_SIZE, y * BLOCK_SIZE);
    }

    public Block getAt(int x, int y) {
        return blocks[x][y];
    }

    public void draw() {
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y].draw();
            }
        }
    }

    public void clear() {
        for (int x = 0; x < BLOCKS_WIDTH - 1; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT - 1; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
            }
        }
    }
}