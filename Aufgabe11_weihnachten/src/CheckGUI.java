import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class CheckGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4107104088491425760L;

	//private ICheckClient client;
	
	public GameCanvas c;

	public CheckGUI(String name){
		super("JCheck");
		
		
		//client = checkClient;
		

		JMenuBar menu = new JMenuBar();
		JMenuItem game = new JMenuItem("Game");
		menu.add(game);

		c = new GameCanvas();
		c.setBackground(Color.black);



		this.setJMenuBar(menu);
		this.getContentPane().add(c);
		pack();
		this.setVisible(true);
		
	}

	




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public void waitForOpponent() {
		// TODO Auto-generated method stub
		this.setTitle("JCheck: waiting for Opponent....");
		
		
		
		
		
	}
	
	

}


