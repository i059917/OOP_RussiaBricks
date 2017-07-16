package RussiaBricks.brick;

public class Point {
	private int row;
	private int column;
	
	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public int hashCode() {
		return (row * 2) + (column * 3) + (row + column);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point point = (Point)obj;
			return (point.row == this.row) && (point.column == this.column);
		}
		return false;
	}
}
