package Tet2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** A menut megjelenito osztaly */
public class Menu {
	public static HighScore hs;
	public Rectangle ngButton = new Rectangle(40, 150,110, 50);
	public Rectangle hsButton = new Rectangle(40, 250,110, 50);

	public Menu(){
		hs = new HighScore();
	}


	/** A menut kirajzolo fuggveny */
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font ("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("Tetris", 20, 100 );


		Font fnt1 = new Font ("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.drawString("New Game", 45, 190 );
		Font fnt2 = new Font ("arial", Font.BOLD, 20);
		g.setFont(fnt2);
		g.setColor(Color.black);
		g.drawString("High Score", 43, 290 );

		hs.jb.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {

										hs.setVisible(false);



									}

								}
		);









	}

}
