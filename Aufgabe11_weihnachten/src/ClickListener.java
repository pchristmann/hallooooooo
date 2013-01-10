import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ClickListener implements MouseListener {

	private ICheckClient client;
	private CheckGUI gui;
	private int tmp_x,tmp_y;
	private Point tmp_point_start,tmp_point_end;
	private int count=0;
	public ClickListener(CheckClient client ,CheckGUI gui){
		this.client = client;
		this.gui = gui;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("x: "+e.getX()+ " /y: "+e.getY() );

		if(count ==0){
			tmp_x = getField(e.getX());
			tmp_y = getField(e.getY());
			tmp_point_start = new Point(tmp_x,tmp_y);
			//System.out.println("x: "+tmp_x+ " /y: "+tmp_y+ ":::"+ tmp_point_start);
			//System.out.println(gui.c.getMeineSpielsteine());
			if(gui.c.getPlayer1_Spielsteine().contains(tmp_point_start)|| gui.c.getPlayer2_Spielsteine().contains(tmp_point_start))
				count ++;
			
		}else if(count ==1){
			tmp_point_end = new Point(getField(e.getX()),getField(e.getY()));
			
			((CheckClient)client).sendCmdToServer(new Command(client,tmp_point_start,tmp_point_end));
			/*
			if ((tmp_point_start.x != tmp_point_end.x )){
				try {
					if( ((tmp_point_end.y == (tmp_point_start.y -1))&& (client.getStatus() == ICheckClient.PLAYER1))||
							((tmp_point_end.y == (tmp_point_start.y +1))&& (client.getStatus() == ICheckClient.PLAYER2))	){

						
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}*/
			count = 0;
		}



	}

	private int getField(int cord){
		return cord/GameCanvas.FIELD_LENGTH;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
