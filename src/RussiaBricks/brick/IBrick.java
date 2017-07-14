package RussiaBricks.brick;

import java.util.List;

public interface IBrick {
	
	public void init();
	public List<Point> getPositions();
	public void moveLeft();
	public void moveRight();
	public void moveDown();
	public void rotate();
	public int getLeftBoundary();
	public int getRightBoundary();
	public int getBottomBoundary();
}