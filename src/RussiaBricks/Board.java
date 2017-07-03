package RussiaBricks;

import RussiaBricks.brick.IBrick;
import RussiaBricks.brick.Point;
import RussiaBricks.brick.Square;
import RussiaBricks.brick.constants.IBrickConstants;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Board extends Application {
	
	private static final int ROW = 10;
	private static final int COLUMN = 10;

	private BorderPane borderPane;
	private GridPane gridPane;
	private Button moveDownButton;
	private Button dockButton;
	private IBrick currentBrick;
	
	public Board() {
		this.borderPane = new BorderPane();
		this.gridPane = new GridPane();
		this.moveDownButton = new Button("Down");
		this.dockButton = new Button("Dock");
	}
	
	@Override
	public void init() {
		this.initCenterGrid();
		this.initBottomBar();
		this.initEventHandler();
	}
	
	@Override
	public void stop() {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.init();
		this.accept(new Square());
		
		primaryStage.setTitle("Russia Bricks");
        Scene scene = new Scene(this.borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void accept(IBrick brick) {
		if(brick == null) {
			return;
		}
		
		this.redrawBrick(brick);
		
		this.currentBrick = brick;
	}
	
	private void initCenterGrid() {
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COLUMN; j++) {
				Button button = new Button();
				button.setMinSize(30, 30);
				this.gridPane.add(button, j, i);
			}
		}
		this.borderPane.setCenter(this.gridPane);
	}

	private void initBottomBar() {
		HBox bottomBar = new HBox();
		bottomBar.setAlignment(Pos.BASELINE_CENTER);
		bottomBar.getChildren().addAll(this.moveDownButton, this.dockButton);
		this.borderPane.setBottom(bottomBar);
	}
	
	private void initEventHandler() {
		this.moveDownButton.setOnAction(event -> {
			this.clearBrick(this.currentBrick);
			this.currentBrick.moveDown();
			this.redrawBrick(this.currentBrick);
		});
	}
	
	private void redrawBrick(IBrick brick) {
		setBrickStyle(brick, IBrickConstants.BG_COLOR_STYLE_BLUE);
	}
	
	private void clearBrick(IBrick brick) {
		setBrickStyle(brick, IBrickConstants.DEFAULT_CELL_STYLE);
	}
	
	private void setBrickStyle(IBrick brick, String style) {
		if(brick != null) {
			ObservableList<Node> nodeList = this.gridPane.getChildren();
			for(Point pos : brick.getPositions()) {
				for(Node node : nodeList) {
					if(GridPane.getRowIndex(node) == pos.getRow() &&
							GridPane.getColumnIndex(node) == pos.getColumn()) {
						node.setStyle(style);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
