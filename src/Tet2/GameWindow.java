package Tet2;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** A jatekot tartalmazo ablakot letrehozo osztaly. */
public class GameWindow extends JFrame {

    private JLabel statusbar;

    /** A jatek ablakanak konstruktora */
    public GameWindow() {
        init();
    }

    public JLabel getSB() {
        return statusbar;
    }

    /** Az ablak beallitasait vegzi el*/
    private void init(){
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board (this);
        add(board);
        board.start();
        setTitle("Tetris");
        setSize(210, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    /** A fo fuggveny, elinditja a jatekot az osztaly letrehozasaval */
    public static void main(String[] args){
        GameWindow game = new GameWindow();
        game.setVisible(true);
    }
}

