package com.example.stickherog.test;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.stickherog.core.GameApplication;

public class GameApplicationTest {

    @Test
    public void testWidth() {
        GameApplication app = new GameApplication();
        assertEquals(800, app.getFrameWidth());
    }

    @Test
    public void testHeight() {
        GameApplication app = new GameApplication();
        assertEquals(600, app.getFrameHeight());
    }
}