import java.awt.BorderLayout;
import java.awt.Point;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;


public class CheckClient extends UnicastRemoteObject implements ICheckClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300526021859814877L;
	
	


	private CheckGUI gui ;
	private ICheckerServer server;
	private  int status;


	protected CheckClient() throws RemoteException {
		super();
		gui = new CheckGUI("JCheck");

		// TODO Auto-generated constructor stub


		connect();
	}


	private void connect(){


		try {
			Registry reg = LocateRegistry.getRegistry();
			server = (ICheckerServer) reg.lookup("CheckerServer");


			server.searchGame(this);




		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@Override
	public void receiveMessage(String string) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(string);
	}



	@Override
	public void waitForOpponent() throws RemoteException {
		// TODO Auto-generated method stub

		gui.waitForOpponent();

	}

	public static void main(String[] args) {
		try {
			new CheckClient();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	public void sendCmdToServer(Command command) {
		// TODO Auto-generated method stub
		try {
			server.receiveCommand(command);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void setSpielsteine(Vector<Point> player1,Vector<Point>player2)
			throws RemoteException {
		// TODO Auto-generated method stub

		gui.c.updateSpielsteine(player1,player2);
		
		if (gui.c.getMouseListeners().length == 0){
			gui.c.addMouseListener(new ClickListener(this,gui));
		}

	}


	@Override
	public void setSpieler(int spieler) throws RemoteException {
		// TODO Auto-generated method stub
		if (spieler ==1){
			this.status =PLAYER1;
			gui.setTitle("JCheck : Rot");
			gui.c.setMeineSpielsteine(1);
		}
		if (spieler==2){
			this.status = PLAYER2;
			gui.setTitle("JCheck: Blau");
			gui.c.setMeineSpielsteine(2);
		}
	}


	@Override
	public int getStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return status;
	}


	@Override
	public void receiveReihenfolge(String string) throws RemoteException {
		// TODO Auto-generated method stub
		if(status == 1)gui.setTitle("JCheck: Rot: "+ string);
		else {
			gui.setTitle("JCheck: Blau: "+ string);
		}
	}


	@Override
	public void receivePopUp(String string) throws RemoteException {
		// TODO Auto-generated method stub
		//default title and icon
		//JOptionPane.showMessageDialog(null,string,"Fehler",JOptionPane.ERROR_MESSAGE);
		JDialog d= new JDialog(gui,"Fehler",false);
		d.getContentPane().setLayout(new BorderLayout());
		d.getContentPane().add(BorderLayout.CENTER,new JLabel(string,JLabel.CENTER));
		d.pack();
		d.setVisible(true);
		
		//d.show();
	}
	
	


}
