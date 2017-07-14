package RussiaBricks.brick;

import java.util.List;

public interface IBrick {
	
	public void init();
	public List<Point> getAllPoints();
	public void moveLeft();
	public void moveRight();
	public void moveDown();
	public void rotate();

	public List<Point> getLeftBoundary();
	public List<Point> getRightBoundary();
	public List<Point> getCeilBoundary();
	public List<Point> getBottomBoundary();

}