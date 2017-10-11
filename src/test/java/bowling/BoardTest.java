package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
Board board = new Board();

Game game = new Game();

	@Test
	public void 초기출력_테스트() {
		game.init();
		
		board.showBoard(game);
	}

}
