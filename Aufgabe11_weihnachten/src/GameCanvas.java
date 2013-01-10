import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;


public class GameCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1961054622791932900L;
	public static final int FIELD_LENGTH = 50;
	private boolean[][] field = new boolean[8][8];
	private Vector<Point>player1_Spielsteine;
	


	private Vector<Point>player2_Spielsteine;
	private Vector<Point> meine_Spielsteine;
	
	
	

	public GameCanvas(){
		super();
		
		this.setSize(field.length*FIELD_LENGTH,field.length*FIELD_LENGTH);
	}

	
	
	public void paint(Graphics g ){

		super.paint(g);
		for (int y=0;y<field.length;y++){
			for(int x =0;x<field[y].length;x++){
				if (y%2 == 0){
					if(x%2 !=0 ){
						g.setColor(Color.gray);

					}
					else {
						g.setColor(Color.white);

					}
				}else{
					if(x%2 ==0 ){
						g.setColor(Color.gray);
					}
					else{
						g.setColor(Color.white);
					}
				}

				
				g.fillRect(x*FIELD_LENGTH,y*FIELD_LENGTH,FIELD_LENGTH , FIELD_LENGTH);
				if(field[y][x] ){
					g.setColor(Color.black);
					g.fillOval(x*FIELD_LENGTH+10, y*FIELD_LENGTH+10, 30, 30);
				}

			}
		}
		if(player1_Spielsteine != null){
			g.setColor(Color.red);
			for(Point e : player1_Spielsteine){
				g.fillOval(e.x*FIELD_LENGTH+10, e.y*FIELD_LENGTH+10, 30, 30);
			}
		}
		if(player2_Spielsteine != null){
			g.setColor(Color.blue);
			for(Point e : player2_Spielsteine){
				g.fillOval(e.x*FIELD_LENGTH+10, e.y*FIELD_LENGTH+10, 30, 30);
			}
		}
		
		
		
		

	}



	public void updateSpielsteine(Vector<Point> player1_Spielsteine,Vector<Point> player2_Spielsteine) {
		// TODO Auto-generated method stub
		this.player1_Spielsteine = player1_Spielsteine;
		this.player2_Spielsteine = player2_Spielsteine;
		
		
		this.repaint();
	}



	public void setMeineSpielsteine(int i) {
		// TODO Auto-generated method stub
		if(meine_Spielsteine ==null){
			if(i==1){
				meine_Spielsteine = player1_Spielsteine;
			}else if(i==2){
				meine_Spielsteine= player2_Spielsteine;
			}
		}
	}



	public Vector<Point> getMeineSpielsteine() {
		// TODO Auto-generated method stub
		//return meine_Spielsteine;
		return meine_Spielsteine;
	}
	public  Vector<Point> getPlayer1_Spielsteine() {
		return player1_Spielsteine;
	}



	public Vector<Point> getPlayer2_Spielsteine() {
		return player2_Spielsteine;
	}


}
