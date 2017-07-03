package RussiaBricks.brick;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBrick implements IBrick {
	
	protected Point point1;
	protected Point point2;
	protected Point point3;
	protected Point point4;
	protected List<Point> positionList = new ArrayList<Point>();
	
	@Override
	public List<Point> getPositions() {
		return positionList;
	}

	@Override
	public void moveLeft() {
		for(Point pos : positionList) {
			pos.setColumn(pos.getColumn() - 1);
		}
	}
	
	@Override
	public void moveRight() {
		for(Point pos : positionList) {
			pos.setColumn(pos.getColumn() + 1);
		}
	}
	
	@Override
	public void moveDown() {
		for(Point pos : positionList) {
			pos.setRow(pos.getRow() + 1);
		}
	}
}