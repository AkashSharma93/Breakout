import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


public class Ball {
	private float x, y, incrX, incrY;
	private float diameter;
	private boolean moving;
	
	public Ball(Paddle p) {
		diameter = Game.height/50.0f;
		incrX = 5.0f;
		incrY = -5.0f;
		reset(p);
	}
	
	public void reset(Paddle p) {
		x = p.getX() + p.getWidth()/2.0f;
		y = p.getY() - diameter/2.0f;
		moving = false;
	}
	
	public float getDiameter() {
		return diameter;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void invertY() {
		incrY = -incrY;
	}
	
	public void invertX() {
		incrX = -incrX;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
	}
	
	public void move(Input input, Paddle p) {
		if(input.isKeyDown(Input.KEY_SPACE)) {		//To start the game.
			moving = true;
		}
		
		if(!moving) {								//This lets the ball stick to the paddle before the start of the game.
			reset(p);
		}
		else {
			checkBounds();
			x += incrX;
			y += incrY;
		}
	}
	
	public void checkBounds() {
		if(x - diameter/2 <= Game.leftBound || x + diameter/2 >= Game.rightBound) {
			incrX = -incrX;
		}
		if(y - diameter/2 <= Game.upperBound) {
			incrY = -incrY;
		}
	}
}
