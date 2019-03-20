
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Pong extends JFrame{
	Pong(){
		setSize(Screen.getSCREEN_WIDTH()+17,Screen.getSCREEN_HEIGHT()+48);
		setTitle("Pong");
		add(new Screen());
	}
	public static void main(String[] args) {
		JFrame frame = new Pong();
		frame.setVisible(true);

	}

}
