package RussiaBricks.brick;

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
	}

	@Override
	public void rotate() {

	}

	@Override
	public int getLeftBoundary() {
		return point1.getColumn();
	}

	@Override
	public int getRightBoundary() {
		return point2.getColumn();
	}

	@Override
	public int getBottomBoundary() {
		return point3.getRow();
	}
}