package examples.models;

import java.io.*;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Oskar
 */
public class OBJLoader {

    public static Model loadModel(File f) throws FileNotFoundException,
            IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model m = new Model(10000000, 10000000, 10000000, 1000000);
        String line;
        int vertexCount = 0;
        int normalCount = 0;
        int vertexIndexCount = 0;
        int normalIndexCount = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.verticesArray[vertexCount * 3] = x;
                m.verticesArray[vertexCount * 3 + 1] = y;
                m.verticesArray[vertexCount * 3 + 2] = z;
                vertexCount += 1;
                m.vertices.put(new float[]{x, y, z});
            } else if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.normalsArray[normalCount * 3 ] = x;
                m.normalsArray[normalCount * 3 + 1] = y;
                m.normalsArray[normalCount * 3 + 2] = z;
                normalCount += 1;
                m.normals.put(new float[]{x, y, z});
            } else if (line.startsWith("f ")) {
                int[] vertexIndices = {
                    Integer.valueOf(line.split(" ")[1].split("/")[0]),
                    Integer.valueOf(line.split(" ")[2].split("/")[0]),
                    Integer.valueOf(line.split(" ")[3].split("/")[0])};
                int[] normalIndices = {
                    Integer.valueOf(line.split(" ")[1].split("/")[2]),
                    Integer.valueOf(line.split(" ")[2].split("/")[2]),
                    Integer.valueOf(line.split(" ")[3].split("/")[2])};
                m.vertexIndicesArray[vertexIndexCount] = vertexIndices[0];
                m.vertexIndicesArray[vertexIndexCount + 1] = vertexIndices[1];
                m.vertexIndicesArray[vertexIndexCount + 2] = vertexIndices[2];
                vertexIndexCount += 3;
                m.normalIndicesArray[normalIndexCount] = normalIndices[0];
                m.normalIndicesArray[normalIndexCount + 1] = normalIndices[1];
                m.normalIndicesArray[normalIndexCount + 2] = normalIndices[2];
                normalIndexCount += 3;
                m.vertexIndices.put(vertexIndices);
                m.normalIndices.put(normalIndices);
            }
        }
        m.vertices.flip();
        m.normals.flip();
        m.vertexIndices.flip();
        m.normalIndices.flip();
        reader.close();
        return m;
    }
}
