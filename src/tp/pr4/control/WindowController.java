package tp.pr4.control;																																							

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameType;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.logic.ReadOnlyBoard;
import tp.pr4.views.window.MainWindow;
 
public class WindowController extends Controller {
	static java.util.Scanner in;
	
	public WindowController(GameTypeFactory factory, Game g) {
		super(factory,g, in);
	}
	
	private void changeGame(GameType gameType, int dimX, int dimY) {
		if (gameType == gameType.Gravity)
			Resources.setGravityDimX(dimX); 
			Resources.setGravityDimY(dimY);
		changeG(gameType, dimX, dimY);	
	}
	
	public void makeMove(int col, int row, Counter turn) {
		boolean valid;
			
		try {
			Move mov = getGameTypeFactory().createMove(col, row, turn);
			valid = game.executeMove(mov);
			if (valid) this.changePlayer();
		}
		catch (InvalidMove e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void randomMove(Counter player) {
		getGameTypeFactory().createRandomPlayer().getMove(getGame().getBoard(), player);
	}
	
	// Quit the application.
	public void requestQuit() {
		game.closeGame();
	}
	
	public void reset() {
		initGame();	// Reset players, current player and 
		game.resetGame(); // Notify the window that the reset is done
	}
	
	public void run() {
		game.createWindow();
	}
	
	public void undo() {
		boolean undo = game.undo();
		if (undo) changePlayer(); // Change Current player 
	}
	
}
