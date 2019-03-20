import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen extends JPanel implements ActionListener{
	private static final int SCREEN_WIDTH=500,SCREEN_HEIGHT=500;
	private static int[][] critMap= new int[SCREEN_WIDTH+1][SCREEN_HEIGHT+1];
	Paddle paddle= new Paddle();
	Ball ball=new Ball();
	Block block=new Block();
	Ball ball2=new Ball();

	public static int getCritMap(int x,int y) {
		return critMap[x][y];
	}
	
	public static void setCritMap(int x,int y,int value) {
		critMap[x][y] = value;
	}

	public static int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}
	public static int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}
	Screen(){
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new Keys());
		initialize();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		ball.move();
		ball.decideAngle();
		ball2.move();
		ball2.decideAngle();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw (Graphics g) {
		//draw paddle
		g.setColor(Color.WHITE);
		for(int i=0;i<paddle.getPADDLE_WIDTH();i++) {
			g.fillRect(paddle.getPaddleX(i), paddle.getPaddleY(), 1, 10);
		}
		//draw ball
		g.fillOval(ball.getBallX(), ball.getBallY(), 10, 10);
		g.fillOval(ball2.getBallX(), ball2.getBallY(), 10, 10);
		//draw Blocks
		int t=block.getTotalBlock();
		for(int i=0;i<t;i++) {
			if(block.getExist(i)) {
					g.fillRect(block.getX(i), block.getY(i), block.getLENGTH(), block.getLENGTH());
			}
		}
	}
	private void initialize() {
		//set critical value
		//10000 = ball bounces vertically when it hits
		//positive = same above but destroy a block
		//-10000 = ball bounces horizontally when it hits
		//negative = same above but destroy a block
		//... = for end game
		//... = changes to random angle
		//0 = ball keeps going straight
		for(int i=0;i<=SCREEN_WIDTH;i++) {
			critMap[i][0]=10000;
			critMap[i][SCREEN_HEIGHT]=10000;
		}
		for(int i=1;i<SCREEN_HEIGHT;i++) {
			critMap[0][i]=-10000;
			critMap[SCREEN_WIDTH][i]=-10000;
		}
		for(int i=1;i<SCREEN_WIDTH;i++) {
			for(int y=1;y<SCREEN_HEIGHT;y++) {
				critMap[i][y]=0;
			}
		}
		//Blocks
		int t=block.getTotalBlock();
		//System.out.print(block.getX(30));
		for(int i=0;i<t;i++) {
			block.setCrit(block.getX(i), block.getY(i),-i-1,i+1);
		}
		//Paddle
		paddle.setCrit();
		Timer time=new Timer(15,this);
		time.start();
	}
	private class Keys extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent evt) {
			switch(evt.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				paddle.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				paddle.moveRight();
				break;
			}
		}
	}

}
