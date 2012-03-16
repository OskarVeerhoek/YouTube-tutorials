package episode_22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.openal.AL;
import static org.lwjgl.openal.AL10.*;
import org.lwjgl.util.WaveData;

public class OpenALDemo {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("OpenAL Demo");
            Display.create();
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        BigDecimal var = new BigDecimal(0.00000000001);
        WaveData data = WaveData.create(new FileInputStream("res/thump.wav"));
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