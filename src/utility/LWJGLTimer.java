package utility;

/**
 * For frame-rate independent movement
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
