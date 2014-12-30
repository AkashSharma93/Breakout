import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Brick {
	private float x, y;
	private float height, width;
	private Color color;
	
	public Brick(float x, float y) {
		this.x = x;
		this.y = y;
		height = Game.height/50.0f;
		width = (float) (Game.rightBound - Game.leftBound)/(Game.brickRows);
		color = new Color(100000 + ((int) (Math.random() * 10000000))); 	//Colors with values above 100000 appear bright enough.
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
