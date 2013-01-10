import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface ICheckClient extends Remote {

	static final int PLAYER1 = 1;
	static final int PLAYER2 = 2;
	void receiveMessage(String string)throws RemoteException;

	

	void waitForOpponent() throws RemoteException;
	
	void setSpielsteine(Vector<Point> player1,Vector<Point> player2) throws RemoteException;
	
	void setSpieler(int spieler) throws RemoteException;
	
	int getStatus() throws RemoteException;



	void receiveReihenfolge(String string) throws RemoteException;



	void receivePopUp(String string) throws RemoteException;

}
