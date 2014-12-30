import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class MenuScreen extends BasicGameState {
	private int index;
	private Image title;
	OptionButton op1, op2;
	private	Input input;
	
	public MenuScreen(int index) {
		this.index = index;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		input = gc.getInput();
		title = new Image("res/Images/Title.png");
		Image img = new Image("res/Images/Option1.png");
		op1 = new OptionButton(img, Game.width/2 - img.getWidth()/2, Game.height/2 - img.getHeight());
		img = new Image("res/Images/Option2.png");
		op2 = new OptionButton(img, Game.width/2 - img.getWidth()/2, Game.height/2);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(title, Game.width/2 - title.getWidth()/2, 0);
		g.drawImage(op1.getImg(), op1.getX(), op1.getY());
		g.drawImage(op2.getImg(), op2.getX(), op2.getY());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		int mouseY = Game.height - Mouse.getY(), mouseX = Mouse.getX();
		
		if(mouseY > op1.getY() && mouseY < op1.getY() + op1.getImg().getHeight()) {		//Checking if user clicks option 1. Actually, this is just to check for mouse hover...
			if(mouseX > op1.getX() && mouseX < op1.getX() + op1.getImg().getWidth()) {
				if(input.isMouseButtonDown(0)) {		//This is for the actual clicking thing.
					sbg.enterState(Game.play);
				}
			}
		}
		
		if(mouseY > op2.getY() && mouseY < op2.getY() + op2.getImg().getHeight()) {		//Option 2.
			if(mouseX > op2.getX() && mouseX < op2.getX() + op2.getImg().getWidth()) {
				if(input.isMouseButtonDown(0)) {
					System.exit(0);
				}
			}
		}
	}

	@Override
	public int getID() {
		return index;
	}
	
}
