package RussiaBricks;

import RussiaBricks.brick.Point;

public class BoardRuleManager {
	private Board board;
	
	public BoardRuleManager(Board board) {
		this.board = board;
	}
	
	public boolean isOKToMoveLeft() {
		for(Point point : board.getCurrentBrick().getLeftBoundary()) {
			if(point.getColumn() == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToMoveDown() {
		for(Point point : board.getCurrentBrick().getBottomBoundary()) {
			if(point.getRow() == Board.ROW - 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToMoveRight() {
		for(Point point : board.getCurrentBrick().getRightBoundary()) {
			if(point.getColumn() == Board.COLUMN - 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOKToDock() {
		for(Point point : board.getCurrentBrick().getBottomBoundary()) {
			if(point.getRow() != Board.ROW - 1) {
				return false;
			}
		}
		return true;
	}
}
