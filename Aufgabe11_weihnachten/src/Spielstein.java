import java.awt.Point;


public class Spielstein{
	
	private int x,y;
	public Spielstein(int y,int x){
		this.y = y;
		this.x = y;
	}
	public Point getLocation() {
		return new Point(x,y);
	}
	
	
	
	

}
