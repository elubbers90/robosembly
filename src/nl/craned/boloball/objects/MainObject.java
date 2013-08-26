package nl.craned.boloball.objects;


public class MainObject extends Object{
	MainObjectType type;
	String loc;
	static final int H = 0;
	static final int A = 1;
	static final int B = 2;
	static final int L = 3;
	static final int BO = 4;
	static final int BA = 5;
	static final int SW = 6;
	
	
	MainObject(int n){
		switch(n){
		case H: type = MainObjectType.HEAD;
				loc = "locationH";
				break;
		case A: type = MainObjectType.ARM;
				loc = "locationA";
				break;
		case B: type = MainObjectType.BODY;
				loc = "locationPSQ";
				break;
		case L: type = MainObjectType.LEG;
				loc = "locationLA";
				break;
		case BO: type = MainObjectType.BOMB;
				loc = "locationRA";
				break;
		case BA: type = MainObjectType.BALL;
				loc = "locationTP";
				break;
		case SW: type = MainObjectType.SWITCH;
				loc = "locationB1";
				break;
		}	
	}
	
	
	
	public String toString(){
		return loc;
	}

}
