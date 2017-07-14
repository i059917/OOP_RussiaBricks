package RussiaBricks.brick;

import java.util.List;

public class Square extends AbstractBrick {
	
	public Square() {
		this.init();
	}

	@Override
	public void init() {
		positionList.add(point1 = new Point(0, 4));
		positionList.add(point2 = new Point(0, 5));
		positionList.add(point3 = new Point(1, 4));
		positionList.add(point4 = new Point(1, 5));
		
		ceilBoundary.add(point1);
		ceilBoundary.add(point2);
		bottomBoundary.add(point3);
		bottomBoundary.add(point4);
		leftBoundary.add(point1);
		leftBoundary.add(point3);
		rightBoundary.add(point2);
		rightBoundary.add(point4);
	}

	@Override
	public void rotate() {

	}

	@Override
	public List<Point> getLeftBoundary() {
		return this.leftBoundary;
	}

	@Override
	public List<Point> getRightBoundary() {
		return this.rightBoundary;
	}
	
	@Override
	public List<Point> getCeilBoundary() {
		return this.ceilBoundary;
	}

	@Override
	public List<Point> getBottomBoundary() {
		return this.bottomBoundary;
	}
}