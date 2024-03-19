package Tet2;

import java.awt.Graphics;

import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;







/** Az eredmeny elmentesehez megjeleno ablak */
public class Name {
	NameEnter ne;


	/** Az eredmeny elmento ablak konstruktora */
	Name(){
		ne= new NameEnter();

	}



	/** Az eredmenyt elmento ablak kirajzolasa */
	public void render(Graphics g) {



		Font fnt0 = new Font ("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("Tetris", 20, 50 );

		Font fnt1 = new Font ("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.drawString("Your Score is:", 10, 90 );


		Font fnt3 = new Font ("arial", Font.BOLD, 20);
		g.setFont(fnt3);
		g.setColor(Color.black);
		g.drawString(Integer.toString(Board.Score), 150, 90 );

		Font fnt2 = new Font ("arial", Font.BOLD, 20);
		g.setFont(fnt2);
		g.setColor(Color.black);
		g.drawString("Enter your name!", 10, 120 );



		ne.init();
		ne.jb.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										ne.getName();
										try {ne.fileWrite(Board.Score);} catch (IOException exc){}

										ne.setVisible(false);
										Board.State = Board.STATE.Menu;


									}

								}
		)     ;




	}


}
