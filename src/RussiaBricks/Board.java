package RussiaBricks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private Map<Point, Integer> pointStatusMap;

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

	public Map<Point, Integer> getPointStatusMap() {
		return pointStatusMap;
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
		this.pointStatusMap = new HashMap<Point, Integer>();
		
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COLUMN; j++) {
				Button button = new Button();
				button.setMinSize(30, 30);
				this.gridPane.add(button, j, i);
				
				Point point = new Point(i , j);
				pointStatusMap.put(point, IBrickConstants.POINT_EMPTY);
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
		for(Point point : this.currentBrick.getAllPoints()) {
			this.setPointStatus(point, IBrickConstants.POINT_OCCUPIED);
		}
		for(Point point : this.currentBrick.getAllPoints()) {
			if(ruleManager.isFullRowOccupied(point.getRow())) {
				this.setRowStyle(point.getRow(), IBrickConstants.DEFAULT_CELL_STYLE);
			}
		}
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
			for(Point point : this.currentBrick.getAllPoints()) {
				this.setPointStyle(point, style);
			}
		}
	}

	private void setRowStyle(int row, String style) {
		ObservableList<Node> nodeList = this.gridPane.getChildren();
		for(Node node : nodeList) {
			if(GridPane.getRowIndex(node) == row) {
				this.setPointStyle(new Point(row, GridPane.getColumnIndex(node)), style);
			}
		}
	}

	private void setPointStyle(Point point, String style) {
		ObservableList<Node> nodeList = this.gridPane.getChildren();
		for(Node node : nodeList) {
			if(GridPane.getRowIndex(node) == point.getRow() &&
					GridPane.getColumnIndex(node) == point.getColumn()) {
				node.setStyle(style);
			}
		}
	}
	
	private List<Point> getAboveOccupiedPointsByRow(int row) {
		//for()
		// TODO 
		return null;
	}
	
	private void setPointStatus(Point point, int status) {
		pointStatusMap.put(point, status);
	}
	
	public int getPointStatus(Point point) {
		Integer status = pointStatusMap.get(point);
		if(status == null) {
			return IBrickConstants.POINT_OCCUPIED;
		} else {
			return status;
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
