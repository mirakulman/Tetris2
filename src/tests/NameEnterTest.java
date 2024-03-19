package tests;

import Tet2.HighScore;
import Tet2.NameEnter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NameEnterTest {
    NameEnter ne;

    @org.junit.Before
    public void setUp() {
        ne = new NameEnter();

    }

    @Test
    public void testGetName(){
        assertNotNull(ne.getName());
    }

    @Test
    public void testFileWrite() throws IOException {
        ne.fileWrite(0);
    }
}




