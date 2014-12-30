import org.newdawn.slick.*;

public class OptionButton {
	private Image img;
	private int x, y;
	
	public OptionButton(Image img, int x, int y) throws SlickException {
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public Image getImg() {
		return img;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
