package nl.craned.boloball.grid;

public class SquareData {
	SquareType type;
	String loc;
	static final int BG = 0;
	static final int BL = 1;
	static final int PSQ = 2;
	static final int LA = 3;
	static final int RA = 4;
	static final int TP = 5;
	static final int B1 = 6;
	static final int B2 = 7;
	
	
	SquareData(int n){
		switch(n){
		case BG: type = SquareType.BACKGROUND;
				loc = "locationBG";
				break;
		case BL: type = SquareType.BLOCK;
				loc = "locationBL";
				break;
		case PSQ: type = SquareType.POINT_SQUARE;
				loc = "locationPSQ";
				break;
		case LA: type = SquareType.LEFT_ARROW;
				loc = "locationLA";
				break;
		case RA: type = SquareType.RIGHT_ARROW;
				loc = "locationRA";
				break;
		case TP: type = SquareType.TELEPORT;
				loc = "locationTP";
				break;
		case B1: type = SquareType.BALL_ONE;
				loc = "locationB1";
				break;
		case B2: type = SquareType.BALL_TWO;
				loc = "locationB2";
				break;
		}	
	}
	
	
	
	public String toString(){
		return loc;
	}
}