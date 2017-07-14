package RussiaBricks;

public class BoardRuleManager {
	private Board board;
	
	public BoardRuleManager(Board board) {
		this.board = board;
	}
	
	public boolean isOKToMoveLeft() {
		return true;
	}
	
	public boolean isOKToMoveDown() {
		if(board.getCurrentBrick().getBottomBoundary() == Board.ROW - 1) {
			return false;
		}
		return true;
	}
	
	public boolean isOKToMoveRight() {
		return true;
	}
}
