package ludo;
import java.util.Random;

public class Player {
	
	String username;
	String tokenColor;
	int tokenOnBoard;
	int tokenInBase;
	
	public Player(String username, String tokenColor, int tokenOnBoard, int tokenInBase) {
		this.username = username;
		this.tokenColor = tokenColor;
		this.tokenOnBoard = tokenOnBoard;
		this.tokenInBase = tokenInBase;
				
	}
	
	public int diceRoll() {
		Random generator = new Random();
		int result = generator.nextInt(1 , 6);
		return result;
	}
	
}
