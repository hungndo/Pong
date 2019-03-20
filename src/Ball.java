import java.util.Random;

public class Ball {
	private int angle,ballX,ballY;
	private Random rand=new Random();
	Ball() {
		angle=rand.nextInt(50)+25;
		ballX=rand.nextInt(450)+1;
		ballY=250;
	}
	
	public int getBallX() {
		return ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public void move() {
		double tmpX=ballX+Math.cos(Math.toRadians(angle))*5,tmpY=ballY-Math.sin(Math.toRadians(angle))*5;
		if(tmpX>=Screen.getSCREEN_WIDTH())
			ballX=500;
		else if(tmpX<=0)
			ballX=0;
		else
			ballX=(int)tmpX;
		////
		if(tmpY>=Screen.getSCREEN_HEIGHT())
			ballY=500;
		else if(tmpY<=0)
			ballY=0;
		else
			ballY=(int)tmpY;
	}
	public void decideAngle() {
		int a=Screen.getCritMap(ballX, ballY);
		if(a>0) {
			angle=0-angle;
			//if ball hits a block
			if(a<10000)
				Block.destroyBlock(ballX, ballY);
		}
		else if(a<0){
			angle=(180-angle)%360;
			//if ball hits a block
			if(a>-10000)
				Block.destroyBlock(ballX, ballY);
		}
	}
	
	
}
