package RussiaBricks.shape;

import java.util.List;

public interface IShape {
	
	public void init();
	public List<Point> getPositions();
	public void moveLeft();
	public void moveRight();
	public void moveDown();
	public void rotate();
}
