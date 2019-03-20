import java.util.ArrayList;

public class Paddle {
	private final int PADDLE_WIDTH=50,PADDLE_HEIGHT=10,PADDLE_SPEED=10;
	private ArrayList<Integer> paddleX= new ArrayList<Integer>(PADDLE_WIDTH);
	private int paddleY;
	
	Paddle(){
		paddleY=455;
		for(int i=0;i<PADDLE_WIDTH;i++) {
			paddleX.add(220+i);
		}
	}
	public void setCrit() {
		for(int i=0;i<PADDLE_WIDTH;i++) {
			Screen.setCritMap(220+i,paddleY,10000);
		}
	}
	
	public int getPADDLE_WIDTH() {
		return PADDLE_WIDTH;
	}

	public int getPaddleX(int index) {
		return paddleX.get(index);
	}

	public int getPaddleY() {
		return paddleY;
	}
// upgrade able
	public void moveLeft() {
		if(paddleX.get(0)!=0) {
			for(int i=0;i<PADDLE_WIDTH;i++) {
				int tmpX=paddleX.get(i);
				paddleX.set(i,tmpX-PADDLE_SPEED);
				for(int y=paddleY;y<paddleY+PADDLE_HEIGHT;y++) {
					Screen.setCritMap(tmpX, y,0);
					Screen.setCritMap(tmpX-PADDLE_SPEED, y,10000);
				}	
			}
		}
	}
	public void moveRight() {
		if(paddleX.get(PADDLE_WIDTH-1)<Screen.getSCREEN_WIDTH()-1) {
			for(int i=PADDLE_WIDTH-1;i>=0;i--) {
				int tmpX=paddleX.get(i);
				paddleX.set(i,tmpX+PADDLE_SPEED);
				for(int y=paddleY;y<paddleY+PADDLE_HEIGHT;y++) {
					Screen.setCritMap(tmpX, y,0);
					Screen.setCritMap(tmpX+PADDLE_SPEED, y,10000);
				}
			}
		}
	}
}
