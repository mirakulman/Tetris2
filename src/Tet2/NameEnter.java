
package Tet2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Az eredmenyt beolvaso ablak */
public class NameEnter extends JFrame{
	//JFrame jf;
	JLabel jl;
	JPanel jp;
	JTextField namef;
	public JButton jb;
	String Namen;

	/** Az eredmenyt beolvaso ablak megjelenitese */
	public void init () {
		setVisible(true);
		setLocationRelativeTo(null);
	}


	/** Az eredmenyt beolvaso ablakra a megfelelo swing eszkozok felrakasa*/
	public NameEnter()  {

		//jf=new JFrame();
		jl=new JLabel("Enter your name!");
		jp= new JPanel();
		namef = new JTextField(10);
		jb = new JButton("ok");

		jp.add(jl);
		jp.add(namef);
		add(jp);
		setTitle("Name");

		setSize(200,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.add(jb);

	}

	/** A nevet atadni kepes fuggveny*/
	public String getName(){
		Namen=namef.getText();
		return Namen;
	}


	/** Az eredmeny kiirasa fajlba*/
	public void fileWrite(int Score)throws IOException {

		if (Board.State == Board.STATE.Name) {

			BufferedReader br = new BufferedReader(new FileReader("Scores.txt"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();




			String str = everything +  Namen + ": " + Integer.toString(Board.Score) +"\n"  ;


			BufferedWriter writer = new BufferedWriter(new FileWriter("Scores.txt"));
			writer.write(str);

			writer.close();
		}

		Board.Score = 0;

	}

}

