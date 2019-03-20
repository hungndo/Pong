import java.util.ArrayList;

public class Block {
	private static final int LENGTH=20, ROWS=10 ,COLUMNS=24; //These are not real number of rows and columns
	ArrayList<Integer> x=new ArrayList<Integer>();
	ArrayList<Integer> y=new ArrayList<Integer>();
	private int totalBlock=0;
	static ArrayList<Boolean> exist=new ArrayList<Boolean>();
	
	Block(){
		for(int j=10;j<ROWS*LENGTH;j+=LENGTH+1) {
			for(int i=10;i<COLUMNS*LENGTH;i+=LENGTH+1) {
				x.add(i);
				y.add(j);
				exist.add(true);
				totalBlock++;
			}
		}
	}	
	
	public int getLENGTH() {
		return LENGTH;
	}


	public int getROWS() {
		return ROWS;
	}


	public int getCOLUMNS() {
		return COLUMNS;
	}


	public int getTotalBlock() {
		return totalBlock;
	}


	public boolean getExist(int index) {
		return exist.get(index);
	}


	public int getX(int index) {
		return x.get(index);
	}


	public int getY(int index) {
		return y.get(index);
	}

	public static int  findXCordinate(int x) {
		while(x%20!=0) {
			x++;
		}
		return x;
	}
	public static int findYCoordinate(int x) {
		while(x%20!=0) {
			x++;
		}
		return x;
	}
	///////////fefhweirr73290r
	public static void destroyBlock(int i,int j) {
		exist.set(Math.abs(Screen.getCritMap(i, j))-1,false);
		deleteCrit(i,j);
	}
	/*CritMap needs to be like this 
	---++++---
	---++++---
	---++++---
	*/
	public void setCrit(int xPos,int yPos,int critNumberHorizontal,int critNumberVertical) {
		for(int j=yPos;j<yPos+LENGTH;j++) {
			for(int i=xPos,u=xPos+LENGTH-3;i<xPos+3;i++,u++) {
				Screen.setCritMap(i, j, critNumberHorizontal);
				Screen.setCritMap(u, j, critNumberHorizontal);
			}
		}
		for(int j=yPos;j<yPos+LENGTH;j++) { 
			for(int i=xPos+3;i<xPos+LENGTH-3;i++) {
				Screen.setCritMap(i, j, critNumberVertical);
			}
		}
	}
	public static void deleteCrit(int i,int j) {
		if(0<=i&&i<=500&&j<=500&&j>=0)
		if(Screen.getCritMap(i, j)!=0) {
			Screen.setCritMap(i,j,0);
			deleteCrit(i+1,j);
			deleteCrit(i+1,j+1);
			deleteCrit(i,j+1);
			deleteCrit(i-1,j);
			deleteCrit(i-1,j-1);
			deleteCrit(i,j-1);
			deleteCrit(i+1,j-1);
			deleteCrit(i-1,j+1);
		}
		else
			return;
	}
}
