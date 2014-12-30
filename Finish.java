import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Finish extends BasicGameState {
	private int index;
	private Image title;
	private Input input;
	private String message;
	
	public Finish(int index) {
		this.index = index;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		title = new Image("res/Images/Title.png");
		input = gc.getInput();
		message = "Press ESC to quit.";
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(title, Game.width/2 - title.getWidth()/2, 0);
		if(Game.win) {
			g.drawString("Congratulations! You won!!\n" + message, Game.width/2 - 50, Game.height/2);
		}
		else {
			g.drawString("You lost. Better luck next time!\n" + message, Game.width/2 - 50, Game.height/2);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return index;
	}
	
}
