import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ICheckerServer extends Remote{
	
	public void searchGame(ICheckClient user) throws RemoteException;
	public void receiveCommand(Command cmd) throws RemoteException;
	
	
	

}
