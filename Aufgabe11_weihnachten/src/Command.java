import java.awt.Point;
import java.io.Serializable;
import java.rmi.RemoteException;


public class Command implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7287383297925708386L;
	private ICheckClient client;
	private Point point_start;
	private Point point_end;

	public Command(ICheckClient client, Point tmp_point_start,
			Point tmp_point_end) {
		// TODO Auto-generated constructor stub
		this.client  = client;
		this.point_start = tmp_point_start;
		this.point_end = tmp_point_end;
	}

	public  ICheckClient getClient() {
		return client;
	}

	public  Point get_point_start() {
		return point_start;
	}

	public Point get_point_end() {
		return point_end;
	}
	public String toString(){
		try {
			return"Player: "+ client.getStatus() + " from: "+ point_start.x +"/"+point_start.y + " to: "+ point_end.x + "/"+ point_end.y ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
