package bowling;

public class Player {
	String name;
	int Score;

	Player(String name) throws Exception{
		if(!confirmPlayerName(name)) {
			throw new Exception();
		}
		this.name = name;
	}
	
	public boolean confirmPlayerName (String playerName) {
		if(playerName.length() != 3) {
			return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return Score;
	}
	
	
}
