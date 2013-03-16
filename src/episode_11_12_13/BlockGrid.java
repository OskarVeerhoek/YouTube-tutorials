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

class BlockGrid {

    private final Block[][] blocks = new Block[BLOCKS_WIDTH][BLOCKS_HEIGHT];

    public BlockGrid() {
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
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
                blocks[x][y] = new Block(BlockType.valueOf(e.getAttributeValue("type")), x * BLOCK_SIZE,
                        y * BLOCK_SIZE);
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
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT; y++) {
                Element block = new Element("block");
                block.setAttribute("x", String.valueOf((int) (blocks[x][y].getX() / BLOCK_SIZE)));
                block.setAttribute("y", String.valueOf((int) (blocks[x][y].getY() / BLOCK_SIZE)));
                block.setAttribute("type", String.valueOf(blocks[x][y].getType()));
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
        for (int x = 0; x < BLOCKS_WIDTH; x++) {
            for (int y = 0; y < BLOCKS_HEIGHT; y++) {
                blocks[x][y] = new Block(BlockType.AIR, x * BLOCK_SIZE, y * BLOCK_SIZE);
            }
        }
    }
}