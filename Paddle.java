import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Paddle {
	private float x, y;
	private float width, height;
	private int paddleSpeed;
	
	public Paddle() {
		width = Game.width/8.0f;
		height = Game.height/75.0f;
		y = Game.height - height;
		x = Game.width/2.0f - width/2.0f;
		paddleSpeed = 10;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public int getPaddleSpeed() {
		return paddleSpeed;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, width, height);
	}
	
	public void move(Input input) {
		if(input.isKeyDown(Input.KEY_RIGHT)) {		//Move right
			if(x + width < Game.rightBound) {
				x += paddleSpeed;
			}
		}
		else if(input.isKeyDown(Input.KEY_LEFT)) {		//Move left
			if(x > Game.leftBound) {
				x -= paddleSpeed;
			}
		}
	}
}
