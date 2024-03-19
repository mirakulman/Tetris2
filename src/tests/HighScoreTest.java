package tests;

import Tet2.HighScore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HighScoreTest {

    @Test
    public void testGetHS() throws IOException {
        assertNotNull(new HighScore().getHS());
        }
}