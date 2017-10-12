package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void 이름입려예외처리() {
		Player player;
		try {
			player = new Player("DGS");
			assertEquals("DGS", player.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
