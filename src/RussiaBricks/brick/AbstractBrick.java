package RussiaBricks.brick;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBrick implements IBrick {
	
	protected Point point1;
	protected Point point2;
	protected Point point3;
	protected Point point4;
	protected List<Point> ceilBoundary = new ArrayList<Point>();
	protected List<Point> bottomBoundary = new ArrayList<Point>();
	protected List<Point> leftBoundary = new ArrayList<Point>();
	protected List<Point> rightBoundary = new ArrayList<Point>();
	protected List<Point> pointList = new ArrayList<Point>();
	
	@Override
	public List<Point> getAllPoints() {
		return pointList;
	}

	@Override
	public void moveLeft() {
		for(Point pos : pointList) {
			pos.setColumn(pos.getColumn() - 1);
		}
	}
	
	@Override
	public void moveRight() {
		for(Point pos : pointList) {
			pos.setColumn(pos.getColumn() + 1);
		}
	}
	
	@Override
	public void moveDown() {
		for(Point pos : pointList) {
			pos.setRow(pos.getRow() + 1);
		}
	}
}