package nl.craned.boloball.objects;


public class SideObject extends Object{
	SideObjectType type;
	String loc;
	static final int H = 0;
	static final int A = 1;
	static final int B = 2;
	static final int L = 3;
	static final int BO = 4;
	static final int BA = 5;
	static final int SW = 6;
	
	
	SideObject(int n){
		switch(n){
		case H: type = SideObjectType.HEAD;
				loc = "locationH";
				break;
		case A: type = SideObjectType.ARM;
				loc = "locationA";
				break;
		case B: type = SideObjectType.BODY;
				loc = "locationPSQ";
				break;
		case L: type = SideObjectType.LEG;
				loc = "locationLA";
				break;
		case BO: type = SideObjectType.BOMB;
				loc = "locationRA";
				break;
		case BA: type = SideObjectType.BALL;
				loc = "locationTP";
				break;
		case SW: type = SideObjectType.SWITCH;
				loc = "locationB1";
				break;
		}	
	}
	
	
	
	public String toString(){
		return loc;
	}

}
