import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	public static final int menu = 0;
	public static final int play = 1;
	public static final int finish = 2;
	public static int height, width;
	public static float leftBound, rightBound, upperBound;
	public static int brickRows, brickCols;
	public static boolean win;
	
	public Game(String gameName) {
		super(gameName);
		this.addState(new MenuScreen(menu));	//This class extends BasicGameState.
		this.addState(new Play(play));
		this.addState(new Finish(finish));
		win = false;
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		height = (int) screenSize.getHeight();
		width = (int) screenSize.getWidth();
		brickRows = 20;
		brickCols = 10;
		leftBound = (float) width/brickRows;
		rightBound = width - leftBound;
		upperBound = (float) height/brickRows + 5;		//+5 just to have some space under the title.
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(finish).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new Game("Breakout!"));
			agc.setDisplayMode(width, height, true);
			agc.start();
		} catch (SlickException e) { e.printStackTrace(); }
	}
}
