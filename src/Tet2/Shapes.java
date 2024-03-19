package Tet2;


import java.util.Random;


/** A formakat es akcioikat tarolo osztaly  */
public class Shapes {
    enum Tetromino {No, I, J, L, O, S, T, Z};
    private Tetromino piece;
    private int scoord[][];

    /** A formakat es akcioikat tarolo osztaly konstruktora */
    public Shapes() {
        scoord = new int[4][2];
        setShape(Tetromino.No);
    }

    /** A formakat lehetseges alakjat tarolo fuggveny */
    public void setShape(Tetromino s) {
        int temp[][];
        switch (s) {
            case I:
                temp = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
                break;
            case J:
                temp = new int[][]{{1, -1}, {0, -1}, {0, 0}, {0, 1}};
                break;
            case L:
                temp = new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}};
                break;
            case O:
                temp = new int[][]{{0, 0}, {-1, 0}, {0, 1}, {-1, 1}};
                break;
            case S:
                temp = new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}};
                break;
            case T:
                temp = new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, 1}};
                break;
            case Z:
                temp = new int[][]{{0, -1}, {0, 0}, {-1, 0}, {-1, 1}};
                break;
            default:
                temp = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
                break;

        }
        scoord = temp;
        piece = s;
    }

    /** A forma X koordinatajat beallito fuggveny */
    private void setX(int ind, int x) {
        scoord[ind] [0] = x;
    }

    /** A forma Y koordinatajat beallito fuggveny */
    private void setY(int ind, int y) {
        scoord[ind] [1] = y;
    }
    /** A forma X koordinatajat atado fuggveny */
    public int x(int ind) {
        return scoord[ind] [0];
    }
    /** A forma Y koordinatajat atado fuggveny */
    public int y(int ind) {
        return scoord[ind] [1];
    }

    /** Veletlenul egy format kivalaszto fuggveny */
    public void setRandomT() {

        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7+1;
        Tetromino[] values = Tetromino.values();
        setShape(values[x]);
    }

    /** A format jobbra forgato fuggveny */
    public Shapes rotateR(){
        if(piece==Tetromino.O){
            return this;
        }
        else{
            Shapes uj=new Shapes();
            uj.piece=piece;
            for (int i=0;i<4;i++){
                uj.setX(i,y(i));
                uj.setY(i,-x(i));
            }
            return uj;
        }
    }

    /** A format balra forgato fuggveny */
    public Shapes rotateL(){
        if(piece==Tetromino.O){
            return this;
        }
        else{
            Shapes uj=new Shapes();
            uj.piece=piece;
            for (int i=0;i<4;i++){
                uj.setX(i,-y(i));
                uj.setY(i,x(i));
            }
            return uj;
        }
    }


    /** A forma legkisabb X koordinatajaval visszatero fuggveny */
    public int minX(){
        int mini=scoord[0][0];

        for(int i=0;i<4;i++){
            mini=Math.min(mini,scoord[i][0]);
        }

        return mini;
    }

    /** A forma legkisabb Y koordinatajaval visszatero fuggveny */
    public int minY(){
        int mini=scoord[0][0];

        for(int i=0;i<4;i++){
            mini=Math.min(mini,scoord[i][1]);
        }

        return mini;
    }

    /** Megaadja melyik format valasztottuk */
    public Tetromino getShape()  { return piece; }



}

