import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;



public class CheckerServer extends UnicastRemoteObject implements ICheckerServer{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2251502521587129772L;
	Vector<ICheckClient> waiting_users =  new Vector<ICheckClient>();
	Hashtable<ICheckClient, Game > games = new Hashtable<ICheckClient, Game>();

	protected CheckerServer() throws RemoteException {
		
		super();
		// TODO Auto-generated constructor stub
		System.out.println("jfsldkjf");
	}

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		CheckerServer server= new CheckerServer();

		Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		reg.rebind("CheckerServer", server);

	}



	@Override
	public void searchGame(ICheckClient user) throws RemoteException {
		// TODO Auto-generated method stub
		Enumeration<ICheckClient> enumm = waiting_users.elements();
		boolean gameFound = false;
		ICheckClient tmp = null;
		//Vector<ICheckClient> hosts = new Vector<ICheckClient>();
		synchronized (waiting_users){
			while(enumm.hasMoreElements() && !gameFound){

				try{
					tmp = enumm.nextElement();
					tmp.getStatus();
					Game game = new Game(tmp,user);
					games.put(tmp, game);
					games.put(user, game);

					gameFound= true;
					waiting_users.remove(tmp);

					//hosts.add(tmp);

				}catch(Exception e){
					//e.printStackTrace();
					System.out.println("fehler");
					if (tmp != null)games.remove(tmp);
				}
			}
			if(!gameFound){
				waiting_users.add(user);

				System.out.println(user + " wartet");
				//("spiel erstellt");
				//user.waitForOpponent();

			}

		}

	}

	@Override
	public synchronized void receiveCommand(Command cmd) throws RemoteException {
		// TODO Auto-generated method stub

		Game tmp_game = games.get(cmd.getClient());
		if(tmp_game.isNextPlayer(cmd.getClient().getStatus())){
			if (tmp_game.execCommand(cmd)){

				tmp_game.pushUpdate();

				if(!tmp_game.isFinished()){
					if (!tmp_game.possibleJump(cmd) ){

						tmp_game.setNextPlayer();
					}
					else {
						if(tmp_game.getJumpLenght(cmd.getClient().getStatus(), cmd.get_point_start(), cmd.get_point_end())!=2){
							tmp_game.setNextPlayer();
						}
					}
				}else{
					cmd.getClient().receivePopUp("hello");
					
				}

			}
			else{
				cmd.getClient().receivePopUp("Ungueltiger Zug");
				System.out.println("gfhfg");
			}
		}
		else {
			cmd.getClient().receivePopUp("Du bist nich an der Reihe");
		}

	}



}

