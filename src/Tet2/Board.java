
package Tet2;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;


import Tet2.Shapes.Tetromino;

/**A jatekot iranyito fo osztaly*/
public class Board     extends JPanel
        implements ActionListener {

    private final int Bwidth= 11;
    private final int Bheigth = 19;
    private final int TIME = 400;

    private Timer timer;
    private boolean isFallEnd = false;
    public static boolean isStarted = false;
    private boolean isPause = false;
    public static int Score = 0;
    private int curX = 0;
    private int curY = 0;
    public static JLabel statusbar;

    private Shapes CurrentP;
    private Tetromino[] board;
    private Menu menu;
    private Name name;

    /** A jatek aktualis allapotat tarolja*/
    public static enum STATE{
        Menu, Play, Name
    }
    public static STATE State =STATE.Menu;

    /** A tabla konstruktora*/
    public Board(GameWindow parent) {
        initB(parent);
    }


    /** A tablara letrehozza es felteszi az olyan elemeket mint JFrame JLabel,
     *  valamint meghivja a Timer oszalyt, es felveszi a Listener-ket*/
    private void initB(GameWindow parent) {
        setFocusable(true);
        CurrentP = new Shapes();
        name = new Name();
        timer = new Timer(TIME, this);
        menu = new Menu();

        timer.start();

        statusbar =  parent.getSB();


        board = new Tetromino[Bwidth* Bheigth];
        addKeyListener(new TAdapter());
        addMouseListener(new MauseIn());
        clearBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallEnd) {
            isFallEnd = false;
            newPiece();
        } else {
            lineDown();
        }
    }
    public int sWidth() { return (int) getSize().getWidth() / Bwidth; }
    public int sHeight(){ return (int) getSize().getHeight() / Bheigth; }
    public Tetromino shapeAt(int x, int y) { return board[(y * Bwidth) + x]; }
    public boolean getIsStarted(){
        return isStarted;
    }

    public int  getScore() {
        return Score;

    }

    /**
     * @return statusbar
     */
    public JLabel getStatusbar(){
        return statusbar;
    }

    /** A jatekot elindito foggveny*/
    public void start()  {


        if (isPause)
            return;

        isStarted = true;
        isFallEnd = false;
        Score = 0;

        clearBoard();

        newPiece();
        timer.start();


    }

    /** A szunetet kezelo fuggveny */
    private void pause()  {

        if (!isStarted)
            return;

        isPause = !isPause;

        if (isPause) {

            timer.stop();
            statusbar.setText("paused");
        } else {

            timer.start();
            statusbar.setText(String.valueOf(Score));
        }

        repaint();
    }

    /** Kirajzolja a palyat, vagy meghivja a megfelelo ablakot kirajzolo osztalyt */
    private void myPaint (Graphics g) {

        Dimension size = getSize();
        int Bhead = (int) size.getHeight() - Bheigth * sHeight();


        if (State == STATE.Play) {
            for (int i =1 ; i< Bheigth;i++ ) {
                g.drawLine(0, Bhead+(i)*sHeight(), Bwidth*sWidth() ,   Bhead+(i)*sHeight())
                ;}
            for (int i =0 ; i<= Bwidth;i++ ) {
                g.drawLine( (i)*sWidth() , Bhead+sHeight(), (i)*sWidth() , (Bheigth+2)*sHeight())
                ;}
        }
        else if (State == STATE.Menu)  {

            menu.render(g);




        } else{
            name.render(g);
        }


        for (int i = 0; i < Bheigth; ++i) {

            for (int j = 0; j < Bwidth; ++j) {

                Tetromino shape = shapeAt(j, Bheigth - i - 1);

                if (shape != Tetromino.No)
                    drawSquare(g, 0 + j * sWidth() ,
                            Bhead + i * sHeight(), shape);
            }
        }

        if (CurrentP.getShape() != Tetromino.No) {

            for (int i = 0; i < 4; ++i) {

                int x = curX + CurrentP.x(i);
                int y = curY - CurrentP.y(i);
                drawSquare(g, 0 + x * sWidth() ,
                        Bhead + (Bheigth - y - 1) * sHeight(),
                        CurrentP.getShape());
            }
        }
    }

    /** A palya es a formak kirajzolasa */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        myPaint(g);


    }

    /** A tablarol leveszi a rajta levo formakat */
    private void clearBoard() {

        for (int i = 0; i < Bheigth * Bwidth; ++i)
            board[i] = Tetromino.No;
    }

    /** Az esest felgyorsitani kepes fuggveny */
    private void lineDown()  {

        if (!move(CurrentP, curX, curY - 1))
            pieceDropped();
    }


    /** Figyeli hogy egy forma a palya aljara ert-e es a teljes sorokat. */
    private void pieceDropped() {

        for (int i = 0; i < 4; ++i) {

            int x = curX + CurrentP.x(i);
            int y = curY - CurrentP.y(i);
            board[(y * Bwidth) + x] = CurrentP.getShape();
        }

        removeFullLine();

        if (!isFallEnd)
            newPiece();
    }


    /** Uj format helyez a jatekba, ha a jateknak vege, a megfelelo helyre allapotot allitja be. */
    private void newPiece()  {
        if (State == STATE.Play)  {

            CurrentP.setRandomT();
            curX = Bwidth/ 2 ;
            curY = Bheigth - 1 + CurrentP.minY();

            if (!move(CurrentP, curX, curY)) {


                CurrentP.setShape(Tetromino.No);
                System.out.print(Score);
                //timer.stop();
                isStarted = false;
                clearBoard();
                State = STATE.Menu;
                if (Score==0) {
                    State = STATE.Menu;
                    statusbar.setText("Game Over, you collected nothing!");
                }
                else     {
                    State = STATE.Name;
                    statusbar.setText("Good Job!");



                }



            }
        }

    }


    /** A formakat figyeli.  */
    public boolean move(Shapes newPiece, int newX, int newY) {

        for (int i = 0; i < 4; ++i) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= Bwidth|| y < 0 || y >= Bheigth)
                return false;

            if (shapeAt(x, y) != Tetromino.No)
                return false;
        }

        CurrentP = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }


    /** Teljes sort es sorokat eltavolito fuggveny */
    private void removeFullLine() {

        int iFL = 0;

        for (int i = Bheigth - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < Bwidth; ++j) {
                if (shapeAt(j, i) == Tetromino.No) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++iFL;
                for (int k = i; k < Bheigth - 1; ++k) {
                    for (int j = 0; j < Bwidth; ++j)
                        board[(k * Bwidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (iFL > 0) {

            Score += iFL;
            statusbar.setText(String.valueOf(Score));
            isFallEnd = true;
            CurrentP.setShape(Tetromino.No);
            repaint();
        }
    }


    /** Kirajzolja a formakat a megfelelo eredeti szinukkel */
    private void drawSquare(Graphics g, int x, int y, Tetromino shape)  {





        Color colors[] = { new Color(0, 0, 0), new Color(0, 240, 240), new  Color(0, 0, 240), new Color(240, 160, 0),
                new Color(240, 240, 0), new Color(0, 240,0), new Color(160, 0, 240), new Color(240, 0, 0)


        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, sWidth() - 2, sHeight()- 2);



        g.setColor(color.darker());
        g.drawLine(x, y + sHeight()- 2, x, y);
        g.drawLine(x, y, x + sWidth() - 2, y);
        g.setColor(color.brighter());
        g.drawLine(x + 2, y + sHeight()- 2, x + sWidth() - 2, y + sHeight()- 2);
        g.drawLine(x + sWidth() - 2, y + sHeight()- 2,x + sWidth() - 2, y + 2);

    }


    /** Figyeli a billenytuzetet, es a megfelelo gombokhoz hozzarendeli a fuggvenyt. */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {




            if (!isStarted || CurrentP.getShape() == Tetromino.No) {
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode=='S') {
                State =STATE.Play;
            }




            if (keycode == 'P') {
                pause();
                return;
            }

            if (isPause)
                return;




            switch (keycode) {

                case KeyEvent.VK_SPACE:
                    lineDown();
                    break;

                case KeyEvent.VK_DOWN:
                    move(CurrentP.rotateR(), curX, curY);
                    break;

                case KeyEvent.VK_UP:
                    move(CurrentP.rotateL(), curX, curY);
                    break;

                case KeyEvent.VK_LEFT:
                    move(CurrentP, curX - 1, curY);
                    break;

                case KeyEvent.VK_RIGHT:
                    move(CurrentP, curX + 1, curY);
                    break;



            }
        }
    }
}
