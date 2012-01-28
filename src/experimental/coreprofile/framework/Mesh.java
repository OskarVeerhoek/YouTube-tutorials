package experimental.coreprofile.framework;

import static org.lwjgl.opengl.GL11.GL_BYTE;
import static org.lwjgl.opengl.GL11.GL_DOUBLE;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_SHORT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_SHORT;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.GL_HALF_FLOAT;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glVertexAttribIPointer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.lwjgl.BufferUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * * * *
 * TEMPORARY CLASS !!
 */
public class Mesh {

    int oAttribArraysBuffer = 0;
    int oIndexBuffer = 0;
    int oVAO = 0;
    ArrayList<RenderCmd> primitives = new ArrayList<RenderCmd>();

    public Mesh(String filePath) {
        ArrayList<Attribute> attribs = new ArrayList<Attribute>(16);
        ArrayList<IndexData> indexData = new ArrayList<IndexData>();
        //ArrayList<NamedVAO> namedVaoList = new ArrayList<>();


        //crea il parser e ci associa il file di input
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(ClassLoader.class.getResourceAsStream(filePath));
            //doc = dBuilder.parse(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Element meshElement = doc.getDocumentElement();
        //per ogni nodo "attribute"
        NodeList attrs = meshElement.getElementsByTagName("attribute");
        for (int i = 0; i < attrs.getLength(); i++) {
            //crea un Attribute e lo aggiunge a attribs
            attribs.add(new Attribute(attrs.item(i)));
        }

        //per ogni nodo "vao"
        NodeList vaos = meshElement.getElementsByTagName("vao");
        for (int i = 0; i < vaos.getLength(); i++) {
            //crea un NamedVAO con ProcessVAO e lo aggiunge a namedVaoList
            //TODO per ora non serve implementarlo
            System.out.println(vaos.item(i));
        }

        //per i restanti nodi
        //TODO cercare anche i nodi array
        NodeList cmds = meshElement.getElementsByTagName("indices");
        for (int i = 0; i < cmds.getLength(); i++) {
            //aggiunge a primitives il risultato di ProcessRenderCmd
            primitives.add(new RenderCmd(cmds.item(i)));
            if (cmds.item(i).getNodeName() == "indices") {
                //se il nodo � di tipo "indices" aggiunge a indexData il risultato di IndexData
                indexData.add(new IndexData(cmds.item(i)));
            }
        }

        //calcola la lunghezza del buffer controllando che tutti gli array di attributi abbiano la stessa lunghezza
        int iAttrbBufferSize = 0;
        ArrayList<Integer> attribStartLocs = new ArrayList<Integer>(attribs.size());
        int iNumElements = 0;
        for (int i = 0; i < attribs.size(); i++) {
            iAttrbBufferSize = iAttrbBufferSize % 16 != 0 ? (iAttrbBufferSize + (16 - iAttrbBufferSize % 16)) : iAttrbBufferSize;
            attribStartLocs.add(iAttrbBufferSize);
            Attribute attrib = attribs.get(i);

            iAttrbBufferSize += attrib.calcByteSize();

            if (iNumElements != 0) {
                if (iNumElements != attrib.numElements()) {
                    throw new RuntimeException("Some of the attribute arrays have different element counts.");
                }
            } else {
                iNumElements = attrib.numElements();
            }
        }

        //crea e binda il VAO
        oVAO = glGenVertexArrays();
        glBindVertexArray(oVAO);

        //Crea i buffer object
        oAttribArraysBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, oAttribArraysBuffer);
        glBufferData(GL_ARRAY_BUFFER, iAttrbBufferSize, GL_STATIC_DRAW);

        //Inserisce i dati degli attributi nel buffer object
        for (int i = 0; i < attribs.size(); i++) {
            Attribute attrib = attribs.get(i);
            attrib.fillBoundBufferObject(attribStartLocs.get(i));
            attrib.setupAttributeArray(attribStartLocs.get(i));
        }

        //TODO implementare parte che riempie i vari VAOs

        glBindVertexArray(0);

        //Calcola la lunghezza dell'index buffer
        int iIndexBufferSize = 0;
        ArrayList<Integer> indexStartLocs = new ArrayList<Integer>(indexData.size());
        for (int i = 0; i < indexData.size(); i++) {
            iIndexBufferSize = iIndexBufferSize % 16 != 0 ? (iIndexBufferSize + (16 - iIndexBufferSize % 16)) : iIndexBufferSize;

            indexStartLocs.add(iIndexBufferSize);
            IndexData currData = indexData.get(i);

            iIndexBufferSize += currData.calcByteSize();
        }

        //Crea l'index buffer object
        if (iIndexBufferSize > 0) {
            glBindVertexArray(oVAO);

            oIndexBuffer = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, oIndexBuffer);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, iIndexBufferSize, GL_STATIC_DRAW);

            //gli inserisce i dati
            for (int i = 0; i < indexData.size(); i++) {
                IndexData currData = indexData.get(i);
                currData.fillBoundBufferObject(indexStartLocs.get(i));
            }

            //crea i RenderCmd
            int iCurrIndexed = 0;
            for (int i = 0; i < primitives.size(); i++) {
                RenderCmd prim = primitives.get(i);
                if (prim.bIsIndexedCmd) {
                    prim.start = indexStartLocs.get(iCurrIndexed);
                    prim.elemCount = indexData.get(iCurrIndexed).getDataNumElem();
                    prim.eIndexDataType = indexData.get(iCurrIndexed).pAttribType.eGLType;
                    iCurrIndexed++;
                }
            }

            //TODO parte con i named vaos

            glBindVertexArray(0);
        }
    }

    public void render() {
        if (oVAO == 0) {
            return;
        }

        glBindVertexArray(oVAO);

        for (RenderCmd cmd : primitives) {
            cmd.render();
        }

        glBindVertexArray(0);
    }

    public void render(String strMeshName) {
        //TODO da fare con i named VAOs
    }

    public void deleteObjects() {
        glDeleteBuffers(oAttribArraysBuffer);
        glDeleteBuffers(oIndexBuffer);
        glDeleteVertexArrays(oVAO);

        //TODO cancella i named VAOs

    }

    private static class Attribute {

        int iAttribIx = 0xFFFFFFFF;
        AttribType pAttribType = null;
        int iSize = -1;
        boolean bIsIntegral = false;
        Buffer dataArray;

        public Attribute(Node attributeNode) {
            NamedNodeMap attrs = attributeNode.getAttributes();

            {
                //ricava l'index
                Node indexNode = attrs.getNamedItem("index");
                if (indexNode == null) {
                    throw new RuntimeException("Missing 'index' attribute in an 'attribute' element.");
                }
                int index = Integer.parseInt(indexNode.getNodeValue());
                if (!((0 <= index) && (index < 16))) {
                    throw new RuntimeException("Attribute index must be between 0 and 16.");
                }
                iAttribIx = index;
            }

            {
                //ricava il size
                Node sizeNode = attrs.getNamedItem("size");
                if (sizeNode == null) {
                    throw new RuntimeException("Missing 'size' attribute in an 'attribute' element.");
                }
                int size = Integer.parseInt(sizeNode.getNodeValue());
                if (!((1 <= size) && (size < 5))) {
                    throw new RuntimeException("Attribute size must be between 1 and 4.");
                }
                iSize = size;
            }

            {
                //ricava il type
                String strType;
                Node typeNode = attrs.getNamedItem("type");
                if (typeNode == null) {
                    throw new RuntimeException("Missing 'type' attribute in an 'attribute' element.");
                }
                strType = typeNode.getNodeValue();
                pAttribType = AttribType.get(strType);
            }

            {
                //ricava l' integral
                Node integralNode = attrs.getNamedItem("integral");
                if (integralNode == null) {
                    bIsIntegral = false;
                } else {
                    String strIntegral = integralNode.getNodeValue();
                    if (strIntegral.equals("true")) {
                        bIsIntegral = true;
                    } else if (strIntegral == "false") {
                        bIsIntegral = false;
                    } else {
                        throw new RuntimeException("Incorrect 'integral' value for the 'attribute'.");
                    }

                    //l'attributo non pu� essere integral e normalized o floating point allo stesso tempo
                    if (pAttribType.bNormalized) {
                        throw new RuntimeException("Attribute cannot be both 'integral' and a normalized 'type'.");
                    }

                    if (pAttribType.eGLType == GL_FLOAT || pAttribType.eGLType == GL_HALF_FLOAT || pAttribType.eGLType == GL_DOUBLE) {
                        throw new RuntimeException("Attribute cannot be both 'integral' and a floating-point 'type'.");
                    }
                }
            }

            //legge il testo contenente i dati, fa il parse e mette i dati 
            {
                String strData = attributeNode.getChildNodes().item(0).getNodeValue();
                dataArray = pAttribType.parse(strData);
            }
        }

        private int getDataNumElem() {
            return dataArray.limit();
        }

        public int numElements() {
            return getDataNumElem() / iSize;
        }

        public int calcByteSize() {
            return getDataNumElem() * pAttribType.iNumBytes;
        }

        public void fillBoundBufferObject(int iOffset) {
            pAttribType.writeToBuffer(GL_ARRAY_BUFFER, dataArray, iOffset);
        }

        public void setupAttributeArray(int iOffset) {
            glEnableVertexAttribArray(iAttribIx);
            if (bIsIntegral) {
                glVertexAttribIPointer(iAttribIx, iSize, pAttribType.eGLType, 0, iOffset);
            } else {
                glVertexAttribPointer(iAttribIx, iSize, pAttribType.eGLType, pAttribType.bNormalized, 0, iOffset);
            }
        }
    }

    private static class AttribType {

        boolean bNormalized;
        int eGLType;
        int iNumBytes;
        private ParseFunc parse;
        private WriteFunc write;

        private AttribType(boolean bNormalized, int eGLType, int iNumBytes, ParseFunc parse, WriteFunc write) {
            this.bNormalized = bNormalized;
            this.eGLType = eGLType;
            this.iNumBytes = iNumBytes;
            this.parse = parse;
            this.write = write;
        }

        public Buffer parse(String strData) {
            return parse.parse(strData);
        }

        public void writeToBuffer(int eBuffer, Buffer theData, int iOffset) {
            write.writeToBuffer(eBuffer, theData, iOffset);
        }
        //varie funzioni di parse
        private static ParseFunc parseFloats = new ParseFunc() {

            public Buffer parse(String strToParse) {
                Scanner scn = new Scanner(strToParse);
                scn.useDelimiter("\\s+");
                ArrayList<Float> array = new ArrayList<Float>();

                while (scn.hasNext()) {
                    array.add(Float.parseFloat(scn.next()));
                }

                FloatBuffer buff = BufferUtils.createFloatBuffer(array.size());
                for (Float data : array) {
                    buff.put(data);
                }
                buff.flip();
                return buff;
            }
        };
        private static ParseFunc parseInts = new ParseFunc() {

            public Buffer parse(String strToParse) {
                Scanner scn = new Scanner(strToParse);
                scn.useDelimiter("\\s+");
                ArrayList<Integer> array = new ArrayList<Integer>();

                while (scn.hasNext()) {
                    array.add((int) Long.parseLong(scn.next()));
                }

                IntBuffer buff = BufferUtils.createIntBuffer(array.size());
                for (Integer data : array) {
                    buff.put(data);
                }
                buff.flip();
                return buff;
            }
        };
        private static ParseFunc parseShorts = new ParseFunc() {

            public Buffer parse(String strToParse) {
                Scanner scn = new Scanner(strToParse);
                scn.useDelimiter("\\s+");
                ArrayList<Short> array = new ArrayList<Short>();

                while (scn.hasNext()) {
                    array.add((short) Integer.parseInt(scn.next()));
                }

                ShortBuffer buff = BufferUtils.createShortBuffer(array.size());
                for (Short data : array) {
                    buff.put(data);
                }
                buff.flip();
                return buff;
            }
        };
        private static ParseFunc parseBytes = new ParseFunc() {

            public Buffer parse(String strToParse) {
                Scanner scn = new Scanner(strToParse);
                scn.useDelimiter("\\s+");
                ArrayList<Byte> array = new ArrayList<Byte>();

                while (scn.hasNext()) {
                    array.add((byte) Short.parseShort(scn.next()));
                }

                ByteBuffer buff = BufferUtils.createByteBuffer(array.size());
                for (Byte data : array) {
                    buff.put(data);
                }
                buff.flip();
                return buff;
            }
        };
        //varie funzioni di write
        private static WriteFunc writeFloats = new WriteFunc() {

            public void writeToBuffer(int eBuffer, Buffer theData, int iOffset) {
                glBufferSubData(eBuffer, iOffset, (FloatBuffer) theData);
            }
        };
        private static WriteFunc writeInts = new WriteFunc() {

            public void writeToBuffer(int eBuffer, Buffer theData, int iOffset) {
                glBufferSubData(eBuffer, iOffset, (IntBuffer) theData);
            }
        };
        private static WriteFunc writeShorts = new WriteFunc() {

            public void writeToBuffer(int eBuffer, Buffer theData, int iOffset) {
                glBufferSubData(eBuffer, iOffset, (ShortBuffer) theData);
            }
        };
        private static WriteFunc writeBytes = new WriteFunc() {

            public void writeToBuffer(int eBuffer, Buffer theData, int iOffset) {
                glBufferSubData(eBuffer, iOffset, (ByteBuffer) theData);
            }
        };
        private static final Map<String, AttribType> allAttribType = new HashMap<String, AttribType>();
        private static final Map<String, Integer> allPrimitiveType = new HashMap<String, Integer>();

        static {
            allAttribType.put("float", new AttribType(false, GL_FLOAT, Float.SIZE / 8, parseFloats, writeFloats));
            //{"half",		false,	GL_HALF_FLOAT,		sizeof(GLhalfARB),	ParseFloats,	WriteFloats},
            allAttribType.put("int", new AttribType(false, GL_INT, Integer.SIZE / 8, parseInts, writeInts));
            allAttribType.put("uint", new AttribType(false, GL_UNSIGNED_INT, Integer.SIZE / 8, parseInts, writeInts));
            //{"norm-int",	true,	GL_INT,				sizeof(GLint),		ParseInts,		WriteInts},
            //{"norm-uint",	true,	GL_UNSIGNED_INT,	sizeof(GLuint),		ParseUInts,		WriteUInts},
            allAttribType.put("short", new AttribType(false, GL_SHORT, Short.SIZE / 8, parseShorts, writeShorts));
            allAttribType.put("ushort", new AttribType(false, GL_UNSIGNED_SHORT, Short.SIZE / 8, parseShorts, writeShorts));
            //{"norm-short",	true,	GL_SHORT,			sizeof(GLshort),	ParseShorts,	WriteShorts},
            //{"norm-ushort",	true,	GL_UNSIGNED_SHORT,	sizeof(GLushort),	ParseUShorts,	WriteUShorts},
            allAttribType.put("byte", new AttribType(false, GL_BYTE, Byte.SIZE / 8, parseBytes, writeBytes));
            allAttribType.put("ubyte", new AttribType(false, GL_UNSIGNED_BYTE, Byte.SIZE / 8, parseBytes, writeBytes));
            //{"norm-byte",	true,	GL_BYTE,			sizeof(GLbyte),		ParseBytes,		WriteBytes},
            //{"norm-ubyte",	true,	GL_UNSIGNED_BYTE,	sizeof(GLubyte),	ParseUBytes,	WriteUBytes},

            allPrimitiveType.put("triangles", GL_TRIANGLES);
            allPrimitiveType.put("tri-strip", GL_TRIANGLE_STRIP);
            allPrimitiveType.put("tri-fan", GL_TRIANGLE_FAN);
            allPrimitiveType.put("lines", GL_LINES);
            allPrimitiveType.put("line-strip", GL_LINE_STRIP);
            allPrimitiveType.put("line-loop", GL_LINE_LOOP);
            allPrimitiveType.put("points", GL_POINTS);
        }

        public static AttribType get(String type) {
            AttribType attType = allAttribType.get(type);
            if (attType == null) {
                throw new RuntimeException("Unknown 'type' field.");
            }
            return attType;
        }
    }

    public abstract static class ParseFunc {

        abstract public Buffer parse(String strToParse);
    }

    public abstract static class WriteFunc {

        abstract public void writeToBuffer(int eBuffer, Buffer theData, int iOffset);
    }

    private static class RenderCmd {

        boolean bIsIndexedCmd;
        int ePrimType;
        int start;
        int elemCount;
        int eIndexDataType;	//Only if bIsIndexedCmd is true.

        public RenderCmd(Node cmdNode) {
            NamedNodeMap attrs = cmdNode.getAttributes();

            {
                //ricava cmd
                Node cmdAttr = attrs.getNamedItem("cmd");
                if (cmdAttr == null) {
                    throw new RuntimeException("Missing 'cmd' attribute in an 'arrays' or 'indices' element.");
                }
                String strCmd = cmdAttr.getNodeValue();
                Integer primitive = AttribType.allPrimitiveType.get(strCmd);
                if (primitive == null) {
                    throw new RuntimeException("Unknown 'cmd' field.");
                }
                ePrimType = primitive;
            }

            if (cmdNode.getNodeName().equals("indices")) {
                bIsIndexedCmd = true;
            } else if (cmdNode.getNodeName().equals("arrays")) {
                bIsIndexedCmd = false;
                //TODO implementare se usato
            } else {
                throw new RuntimeException("Bad element. Must be 'indices' or 'arrays'.");
            }
        }

        public void render() {
            if (bIsIndexedCmd) {
                glDrawElements(ePrimType, elemCount, eIndexDataType, start);
            } else {
                glDrawArrays(ePrimType, start, elemCount);
            }
        }
    }

    private static class IndexData {

        AttribType pAttribType;
        Buffer dataArray;

        public IndexData(Node indexElem) {
            NamedNodeMap attrs = indexElem.getAttributes();
            //controlla che type sia valido
            {
                Node typeAttr = attrs.getNamedItem("type");
                if (typeAttr == null) {
                    throw new RuntimeException("Missing 'type' attribute in an 'index' element.");
                }
                String strType = typeAttr.getNodeValue();
                if (!(strType.equals("uint") || strType.equals("ushort") || strType.equals("ubyte"))) {
                    throw new RuntimeException("Improper 'type' attribute value on 'index' element.");
                }

                pAttribType = AttribType.get(strType);
            }

            //legge gli indici
            {
                String strIndex = indexElem.getChildNodes().item(0).getNodeValue();
                dataArray = pAttribType.parse(strIndex);
                if (dataArray.limit() == 0) {
                    throw new RuntimeException("The index element must have an array of values.");
                }
            }
        }

        public void fillBoundBufferObject(int iOffset) {
            pAttribType.writeToBuffer(GL_ELEMENT_ARRAY_BUFFER, dataArray, iOffset);
        }

        public int getDataNumElem() {
            return dataArray.limit();
        }

        public int calcByteSize() {
            return getDataNumElem() * pAttribType.iNumBytes;
        }
    }
}
