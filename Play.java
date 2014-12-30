import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;

public class Play extends BasicGameState {
	private int index, lives;
	private Image title;
	private Paddle paddle;
	private Ball ball;
	private Input input;
	private ArrayList<Brick> bricks;
	private boolean paused;
	
	public Play(int index) {
		this.index = index;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		title = new Image("res/Images/Title.png");
		paused = false;
		lives = 3;
		
		paddle = new Paddle();
		ball = new Ball(paddle);
		input = gc.getInput();
		
		bricks = new ArrayList<Brick>();
		float yPos = Game.upperBound + 0.5f;		//0.5 offset so that the bricks don't cover up the upper line.
		
		for(int i = 0; i < Game.brickCols; i++) {
			float xPos = Game.leftBound + 0.5f;		//o.5 offset so that the bricks don't cover up the left line.
			
			for(int j = 0; j < Game.brickRows; j++) {
				bricks.add(new Brick(xPos, yPos));
				xPos += bricks.get(0).getWidth();		//get(0) as all the bricks have the same width.
			}
			yPos += bricks.get(0).getHeight();			//get(0) as all the bricks have the same height.
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawBounds(g);
		paddle.draw(g);
		ball.draw(g);
		
		for(Brick brick: bricks) {
			brick.draw(g);
		}
		
		if(paused) {
			g.setColor(Color.red);
			g.drawString("Resume (R)\nQuit (Q)", Game.width/2, Game.height/2);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			paused = true;
		}
		
		if(paused) {
			if(input.isKeyDown(Input.KEY_R)) {
				paused = false;
			}
			else if(input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
		else {
			paddle.move(input);
			ball.move(input, paddle);
			checkPaddleHit();
			checkBrickHit();
		
			if(bricks.size() == 0) {
				Game.win = true;
				sbg.enterState(Game.finish);
			}
			else if(lives == 0) {
				sbg.enterState(Game.finish);
			}
		}
	}

	@Override
	public int getID() {
		return index;
	}
	
	public void drawBounds(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(title, Game.width/2 - title.getWidth()/2, 0);
		g.drawLine(Game.leftBound, Game.upperBound, Game.rightBound, Game.upperBound);
		g.drawLine(Game.leftBound, Game.upperBound, Game.leftBound, Game.height);
		g.drawLine(Game.rightBound, Game.upperBound, Game.rightBound, Game.height);
		g.drawString("Lives: " + lives, Game.width - Game.leftBound - 20, 10);
	}
	
	public void checkPaddleHit() {
		if(ball.getX() > paddle.getX() && ball.getX() < paddle.getX() + paddle.getWidth()) {
			if(ball.getY() + ball.getDiameter()/2 > paddle.getY()) {
				ball.invertY();
				ball.setY(paddle.getY() - ball.getDiameter());		//Prevents ball from sticking to the paddle sometimes.
				return;
			}
		}
/*		else {
			float xDiag = ball.getX() + ball.getDiameter()/2 * (float) Math.sin(45);
			float yDiag = ball.getY() + ball.getDiameter()/2 * (float) Math.sin(45);
			
			if(xDiag > paddle.getX() && xDiag < paddle.getX() + paddle.getWidth()) {
				if(yDiag > paddle.getY()) {
					ball.invertX();
					ball.invertY();
					ball.setX(xDiag);
					ball.setY(yDiag);
				}
			}
		}
*/		
		if(ball.getY() > paddle.getY()) {		//If ball misses the paddle
			ball.reset(paddle);
			lives--;
		}
	}
	
	public void checkBrickHit() {
		ArrayList<Brick> bricksToRemove = new ArrayList<Brick>();
		
		for(Brick b: bricks) {
			if(ball.getX() > b.getX() && ball.getX() < b.getX() + b.getWidth()) {	//For top and bottom hits.
				if(ball.getY() + ball.getDiameter()/2 > b.getY() && ball.getY() + ball.getDiameter()/2 < b.getY() + b.getHeight()) {	//if ball hits top of brick
					ball.invertY();
					ball.setY(b.getY() - ball.getDiameter());
					bricksToRemove.add(b);
					break;
				}
				else if(ball.getY() - ball.getDiameter()/2 < b.getY() + b.getHeight() && ball.getY() - ball.getDiameter()/2 > b.getY()) {	//if ball hits bottom of brick
					ball.invertY();
					ball.setY(b.getY() + b.getHeight() + ball.getDiameter());
					bricksToRemove.add(b);
					break;
				}
			}
			else if(ball.getY() > b.getY() && ball.getY() < b.getY() + b.getHeight()) {		//For side hits.
				if(ball.getX() + ball.getDiameter()/2 > b.getX() && ball.getX() + ball.getDiameter()/2 < b.getX() + b.getWidth()) {	//if ball hits left side of brick
					ball.invertX();
					ball.setX(b.getX() - ball.getDiameter());
					bricksToRemove.add(b);
					break;
				}
				else if(ball.getX() - ball.getDiameter()/2 < b.getX() + b.getWidth() && ball.getY() - ball.getDiameter()/2 > b.getX()) {	//if ball hits right side of brick
					ball.invertX();
					ball.setX(b.getX() + b.getWidth() + ball.getDiameter());
					bricksToRemove.add(b);
					break;
				}
			}
		}
		
		for(Brick b: bricksToRemove) {		//Removing destroyed bricks.
			bricks.remove(b);
		}
	}
}
