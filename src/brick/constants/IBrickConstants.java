package brick.constants;

import javafx.scene.control.Button;

public interface IBrickConstants {
	public static final String BG_COLOR_STYLE_BLUE = "-fx-background-color: #0000ff";
	public static final String BG_COLOR_STYLE_WHITE = "-fx-background-color: #ffffff";
	public static final String BG_COLOR_STYLE_BLACK = "-fx-background-color: #000000";
	public static final String DEFAULT_CELL_STYLE = new Button().getStyle();
	
	public static final int POINT_EMPTY = 0;
	public static final int POINT_OCCUPIED = 1;
}