package Tet2;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Az eger interakcioit figyelo osztaly a menuhoz. */
public class MauseIn implements MouseListener{




	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void mousePressed(MouseEvent e) {
		int mx =e.getX();
		int my= e.getY();
		if (mx>=40&&mx<=150)   {
			if (my>=150&&my<=200) {
				Board.State = Board.STATE.Play;
				Board.isStarted = true;
				Board.statusbar.setText("0");


			}
		}
		if (mx>=40&&mx<=150)   {
			if (my>=250&&my<=300) {
				Menu.hs.init();



			}
		}
      /*
      public Rectangle ngButton = new Rectangle(40, 150,110, 50);
      public Rectangle hsButton = new Rectangle(40, 250,110, 50);

      */

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
