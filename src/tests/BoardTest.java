package tests;

import Tet2.Board;
import Tet2.GameWindow;
import Tet2.Shapes;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;

    Shapes shape;

    @org.junit.Before
    public void setUp() {
        GameWindow gw = new GameWindow();
        shape = new Shapes();
        board = new Board(gw);


    }

    @Test
    public void testSWidth() {
        assertTrue(board.sWidth() == 0);

    }

    @Test
    public void testSHeight() {
        assertTrue(board.sHeight() == 0);

    }

    @Test
    public void testShapeAt() {
        assertNotNull(board.shapeAt(10, 10));
    }

    @Test
    public void testMove() {
        assertNotNull(board.move(shape, 10, 10));
    }

    @Test
    public void testGetIsStarted() {
        assertTrue(board.getIsStarted());

    }

    @Test
    public void testGetScore() {
        assertTrue(board.getScore() == 0);


    }

    @Test
    public void testGetStatusbar(){
        assertNotNull(board.getStatusbar());
    }
}