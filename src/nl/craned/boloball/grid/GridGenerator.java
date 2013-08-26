package nl.craned.boloball.grid;
import java.util.Date;
import java.util.Random;

public final class GridGenerator {

	private static final int HEIGHT = 4;
	private static final int WIDTH = 5;
	private static final int TP_BLOCK = 5;
	private static final int POINT = 8;
	private static final int BLOCK = 20;
	private static final int L_ARROW = 40;
	private static final int R_ARROW = 40;
	private static long seed;
	private static Random randGen;

    public static Grid emptyGrid() {
        return new Grid(WIDTH, HEIGHT);
    }

    public static Grid generateGrid(long seed) {
        return new Grid(WIDTH, HEIGHT);
    }
}
