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
	
	public static final int ROW = 10;
	public static final int COLUMN = 10;

	private BorderPane borderPane;
	private GridPane gridPane;
	private Button moveLeftButton;
	private Button moveDownButton;
	private Button moveRightButton;
	private Button dockButton;
	private IBrick currentBrick;
	
	private BoardRuleManager ruleManager;

	public Board() {
		this.borderPane = new BorderPane();
		this.gridPane = new GridPane();
		this.moveLeftButton = new Button("Left");
		this.moveDownButton = new Button("Down");
		this.moveRightButton = new Button("Right");
		this.dockButton = new Button("Dock");
	}
	
	public IBrick getCurrentBrick() {
		return currentBrick;
	}
	
	@Override
	public void init() {
		this.ruleManager = new BoardRuleManager(this);
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
	
	private void accept(IBrick brick) {
		if(brick == null) {
			return;
		}
		this.currentBrick = brick;
		this.redrawBrick();
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
		bottomBar.getChildren().addAll(this.moveLeftButton,
				this.moveDownButton, this.moveRightButton, this.dockButton);
		this.borderPane.setBottom(bottomBar);
	}
	
	private void initEventHandler() {
		this.moveLeftButton.setOnAction(event -> {
			if(ruleManager.isOKToMoveLeft()) {
				this.clearBrick();
				this.currentBrick.moveLeft();;
				this.redrawBrick();
			}
		});
		
		this.moveDownButton.setOnAction(event -> {
			if(ruleManager.isOKToMoveDown()) {
				this.clearBrick();
				this.currentBrick.moveDown();
				this.redrawBrick();
			}
		});
		
		this.moveRightButton.setOnAction(event -> {
			if(ruleManager.isOKToMoveRight()) {
				this.clearBrick();
				this.currentBrick.moveRight();
				this.redrawBrick();
			}
		});
		
		this.dockButton.setOnAction(event -> {
			if(ruleManager.isOKToDock()) {
				dockBrick();
			}
		});
	}
	
	private void dockBrick() {
		setBrickStyle(IBrickConstants.BG_COLOR_STYLE_BLACK);
		this.accept(new Square());
	}
	
	private void redrawBrick() {
		setBrickStyle(IBrickConstants.BG_COLOR_STYLE_BLUE);
	}
	
	private void clearBrick() {
		setBrickStyle(IBrickConstants.DEFAULT_CELL_STYLE);
	}
	
	private void setBrickStyle(String style) {
		if(this.currentBrick != null) {
			ObservableList<Node> nodeList = this.gridPane.getChildren();
			for(Point pos : this.currentBrick.getPositions()) {
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
