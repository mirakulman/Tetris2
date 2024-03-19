package Tet2;



import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.awt.BorderLayout;




/** Az eredmenyeket fajlbol megjelenito osztaly */
public class HighScore extends JFrame {
	String highscore;
	JButton jb;
	JLabel jl;
	JPanel jp;
	JTextArea jta;

	/** Az ablak tulajdonsagait allitja be */
	public void init () {
		setVisible(true);
		setLocationRelativeTo(null);
	}


	/** Letrehozza es elhelyezi a megfelelo swing elemeket */
	public HighScore() {

		try { highscore = getHS();} catch (IOException exc){}


		jb = new JButton("Back");
		jl=new JLabel("Scores");

		jp= new JPanel();
		jta = new JTextArea(highscore);



		jp.add(jl, BorderLayout.NORTH);

		add(jp);
		jp.add(jta);
		jp.add(jb);

		setTitle("HighScore");

		setSize(200,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	/** Fajlbol beolvassa az eredmenyeket */
	public String getHS() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Scores.txt"));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String everything = sb.toString();


		return everything;
	}




}
