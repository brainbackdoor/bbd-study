package bbd.chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {
	
	@Test
	public void initialize() {
		Position pos = new Position("a8");
		assertEquals(0,pos.xPos);
		assertEquals(7,pos.yPos);
		
	}

}
