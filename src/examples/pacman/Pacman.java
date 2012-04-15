package examples.pacman;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Oskar
 */
public class Pacman {

    private static volatile int pacmanX = 0, pacmanY = 0;
    private static boolean moving = false;
    private static Direction pacmanDirection = Direction.IDLE;
    private static volatile List<Orb> orbs = new ArrayList<Orb>(16);
    private static volatile boolean running = true;
    private static final int height = 10, width = 10;
    private static int countdown = (int) ((width + height) * 0.75);
    private static int score = 0;
    private static Random random = new Random();

    private static enum Direction {

        UP, DOWN, LEFT, RIGHT, IDLE
    };

    private static class Orb {

        public float x;
        public float y;

        public Orb(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(800, 800));
//            Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
            Display.setTitle("PACMAN CLASSIC");
            Display.create();
        } catch (LWJGLException e) {
            Display.destroy();
            System.exit(1);
        }
        Mouse.setGrabbed(true);

        for (int i = 0; i < 4; i++) {
            orbs.add(new Orb(random.nextInt(width - 1), random.nextInt(height - 1)));
        }

        int orbTEX = glGenTextures();
        {
            InputStream in = null;
            try {
//                in = new FileInputStream("src/examples/player.png");
                in = new FileInputStream("res/orb.png");
                PNGDecoder decoder = new PNGDecoder(in);
                ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
                decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
                buffer.flip();
                in.close();
                glBindTexture(GL_TEXTURE_2D, orbTEX);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
                glBindTexture(GL_TEXTURE_2D, 0);
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to find the texture files.");
                Display.destroy();
                System.exit(1);
            } catch (IOException ex) {
                System.err.println("Failed to load the texture files.");
                Display.destroy();
                System.exit(1);
            }
        }

        int pacmanIdleTEX = glGenTextures();
        {
            InputStream in = null;
            try {
//                in = new FileInputStream("src/examples/player.png");
                in = new FileInputStream("res/player_idle.png");
                PNGDecoder decoder = new PNGDecoder(in);
                ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
                decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
                buffer.flip();
                in.close();
                glBindTexture(GL_TEXTURE_2D, pacmanIdleTEX);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
                glBindTexture(GL_TEXTURE_2D, 0);
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to find the texture files.");
                Display.destroy();
                System.exit(1);
            } catch (IOException ex) {
                System.err.println("Failed to load the texture files.");
                Display.destroy();
                System.exit(1);
            }
        }

        int pacmanTEX = glGenTextures();
        {
            InputStream in = null;
            try {
//                in = new FileInputStream("src/examples/player.png");
                in = new FileInputStream("res/player.png");
                PNGDecoder decoder = new PNGDecoder(in);
                ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
                decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
                buffer.flip();
                in.close();
                glBindTexture(GL_TEXTURE_2D, pacmanTEX);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
                glBindTexture(GL_TEXTURE_2D, 0);
            } catch (FileNotFoundException ex) {
                System.err.println("Failed to find the texture files.");
                Display.destroy();
                System.exit(1);
            } catch (IOException ex) {
                System.err.println("Failed to load the texture files.");
                Display.destroy();
                System.exit(1);
            }
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, 0, height, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_TEXTURE_2D);
        glClearColor(1, 1, 1, 1);

        int pacmanDL = glGenLists(1);
        glNewList(pacmanDL, GL_COMPILE);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 1);
        glTexCoord2f(1, 0);
        glVertex2f(1, 1);
        glTexCoord2f(1, 1);
        glVertex2f(1, 0);
        glTexCoord2f(0, 1);
        glVertex2f(0, 0);
        glEnd();
        glEndList();

        Timer pacmanMovementTimer = new Timer("PACMAN MOVEMENT TIMER");
        pacmanMovementTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                int lastX = pacmanX;
                int lastY = pacmanY;
                if (pacmanDirection == Direction.IDLE || !moving) {
                    return;
                } else if (pacmanDirection == Direction.RIGHT) {
                    if (pacmanX != width - 1) {
                        pacmanX += 1;
                    } else {
                        moving = false;
                    }
                } else if (pacmanDirection == Direction.LEFT) {
                    if (pacmanX != 0) {
                        pacmanX -= 1;
                    } else {
                        moving = false;
                    }
                } else if (pacmanDirection == Direction.UP) {
                    if (pacmanY != height - 1) {
                        pacmanY += 1;
                    } else {
                        moving = false;
                    }
                } else if (pacmanDirection == Direction.DOWN) {
                    if (pacmanY != 0) {
                        pacmanY -= 1;
                    } else {
                        moving = false;
                    }
                }
                List<Orb> newOrbs = new ArrayList<Orb>();
                for (Orb orb : orbs) {
                    if (pacmanX == orb.x && pacmanY == orb.y) {
                        if (lastX < orb.x && lastY == orb.y) {
                            orb.x += 1;
                        } else if (lastX > orb.x && lastY == orb.y) {
                            orb.x -= 1;
                        } else if (lastY < orb.y && lastX == orb.x) {
                            orb.y += 1;
                        } else if (lastY > orb.y && lastX == orb.x) {
                            orb.y -= 1;
                        }
                        if (orb.x == width || orb.x == -1 || orb.y == -1 || orb.y == height) {
                            Toolkit.getDefaultToolkit().beep();
                            score += 1;
//                            System.out.println("Score: " + score + ".");
                            countdown = (int) ((width + height) * 0.75);
                            orb.x = random.nextInt(width - 1);
                            orb.y = random.nextInt(height - 1);
//                            newOrbs.add(new Orb(random.nextInt(width - 1), random.nextInt(height - 1)));
                        }
                    }
                }
                countdown -= 1;
//                System.out.println(countdown + "..");
                if (countdown == 0) {
                    System.out.println("Your score: " + score + " points.");
                    orbs.clear();
                    for (int i = 0; i < 4; i++) {
                        orbs.add(new Orb(5, 5));
                    }
                    pacmanX = pacmanY = 0;
                    pacmanDirection = pacmanDirection.IDLE;
                    moving = false;
                    countdown = (int) ((width + height) * 0.75);
                }
//                orbs.addAll(newOrbs);
            }
        }, 0, 200);

        while (running) {
            // drawing
            glClear(GL_COLOR_BUFFER_BIT);
            glBindTexture(GL_TEXTURE_2D, orbTEX);
            for (Orb orb : orbs) {
                glPushMatrix();
                glTranslatef(orb.x, orb.y, 0);
                glCallList(pacmanDL);
                glPopMatrix();
            }
            glPushMatrix();
            glBindTexture(GL_TEXTURE_2D, pacmanTEX);
            glTranslatef(pacmanX, pacmanY, 0);
            if (pacmanDirection == Direction.UP) {
                glRotatef(90, 0, 0, 1);
                glTranslatef(0, -1, 0);
            } else if (pacmanDirection == Direction.DOWN) {
                glRotatef(-90, 0, 0, 1);
                glTranslatef(-1, 0, 0);
            } else if (pacmanDirection == Direction.LEFT) {
                glRotatef(180, 0, 0, 1);
                glTranslatef(-1, -1, 0);
            } else if (pacmanDirection == Direction.RIGHT) {
            } else {
                glBindTexture(GL_TEXTURE_2D, pacmanIdleTEX);
            }
            glCallList(pacmanDL);
            glBindTexture(GL_TEXTURE_2D, 0);
            glPopMatrix();

            // input
            boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W);
            boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
            boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
            boolean keyStop = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
            if (keyUp && !keyDown && !keyLeft && !keyRight) {
                pacmanDirection = Direction.UP;
                moving = true;
            } else if (keyRight && !keyLeft && !keyDown && !keyUp) {
                pacmanDirection = Direction.RIGHT;
                moving = true;
            } else if (keyLeft && !keyRight && !keyDown && !keyUp) {
                pacmanDirection = Direction.LEFT;
                moving = true;
            } else if (keyDown && !keyUp && !keyLeft && !keyRight) {
                pacmanDirection = Direction.DOWN;
                moving = true;
            } else if (keyStop) {
                moving = false;
//                pacmanDirection = Direction.IDLE;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                running = false;
            }
            // display 
            if (Display.isCloseRequested()) {
                running = false;
            }
            Display.update();
            Display.sync(60);
        }
        glDeleteLists(pacmanDL, 1);
        glDeleteTextures(pacmanTEX);
        glDeleteTextures(pacmanIdleTEX);
        glDeleteTextures(orbTEX);
        Display.destroy();
        System.exit(0);
    }
}
