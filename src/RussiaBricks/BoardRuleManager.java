package RussiaBricks;

import RussiaBricks.brick.Point;
import RussiaBricks.brick.constants.IBrickConstants;

public class BoardRuleManager {
	private Board board;
	
	public BoardRuleManager(Board board) {
		this.board = board;
	}
	
	public boolean isOKToMoveLeft() {
		for(Point point : board.getCurrentBrick().getLeftBoundary()) {
			if(point.getColumn() == 0 
					|| board.getPointStatus(point.getRow(), point.getColumn() - 1) == IBrickConstants.POINT_OCCUPIED) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToMoveDown() {
		for(Point point : board.getCurrentBrick().getBottomBoundary()) {
			if(point.getRow() == Board.ROW - 1
					|| board.getPointStatus(point.getRow() + 1, point.getColumn()) == IBrickConstants.POINT_OCCUPIED) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToMoveRight() {
		for(Point point : board.getCurrentBrick().getRightBoundary()) {
			if(point.getColumn() == Board.COLUMN - 1
					|| board.getPointStatus(point.getRow(), point.getColumn() + 1) == IBrickConstants.POINT_OCCUPIED) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToDock() {
		return !this.isOKToMoveDown();
	}
	
	public boolean isFullRow(int row) {
		return false;
	}
}
