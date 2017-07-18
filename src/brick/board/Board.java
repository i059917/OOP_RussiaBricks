package brick.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import brick.IBrick;
import brick.Point;
import brick.constants.IBrickConstants;
import brick.factory.BrickFactory;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	private Button startButton;
	private Button endButton;
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
		this.startButton = new Button("Start");
		this.endButton = new Button("End");
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
		this.initLeftBar();
		this.initEventHandler();
	}
	
	@Override
	public void stop() {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.init();
		//this.accept(BrickFactory.createBrick());
		
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
		this.pointStatusMap = new TreeMap<Point, Integer>();
		
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
		bottomBar.getChildren().addAll(this.moveLeftButton, this.moveDownButton, this.moveRightButton, this.dockButton);
		this.borderPane.setBottom(bottomBar);
	}
	
	private void initLeftBar() {
		VBox leftBar = new VBox();
		leftBar.setAlignment(Pos.CENTER);
		leftBar.getChildren().addAll(this.startButton, this.endButton);
		this.borderPane.setLeft(leftBar);
	}
	
	private void initEventHandler() {
		this.startButton.setOnAction(event -> {
			this.setGridStyle(IBrickConstants.DEFAULT_CELL_STYLE);
			this.setGridStatus(IBrickConstants.POINT_EMPTY);
			this.accept(BrickFactory.createBrick());
		});
		
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
		//setBrickStyle(IBrickConstants.BG_COLOR_STYLE_BLACK);
		this.setBrickStatus(this.currentBrick, IBrickConstants.POINT_OCCUPIED);

		int topFullRow = Board.ROW;
		int fullRowCount = 0;
		for(Point point : this.currentBrick.getAllPoints()) {
			if(ruleManager.isFullRowOccupied(point.getRow())) {
				this.setRowStatus(point.getRow(), IBrickConstants.POINT_EMPTY);
				if(topFullRow > point.getRow()) {
					topFullRow = point.getRow();
				}
				fullRowCount++;
			}
		}
		if(fullRowCount > 0) {
			List<Point> abovePoints = this.getAboveOccupiedPointsByRow(topFullRow);
			for(Point abovePoint : abovePoints) {
				this.setPointStatus(abovePoint, IBrickConstants.POINT_EMPTY);
				this.setPointStatus(new Point(abovePoint.getRow() + fullRowCount, abovePoint.getColumn()), IBrickConstants.POINT_OCCUPIED);
			}
		}
		
		
		this.redrawGrid();
		this.accept(BrickFactory.createBrick());
	}
	
	private void redrawBrick() {
		setBrickStyle(IBrickConstants.BG_COLOR_STYLE_BLUE);
	}
	
	private void redrawGrid() {
		ObservableList<Node> nodeList = this.gridPane.getChildren();
		for(Node node : nodeList) {
			Point point = new Point(GridPane.getRowIndex(node), 	GridPane.getColumnIndex(node));
			int pointStatus = this.getPointStatus(point);
			if(pointStatus == IBrickConstants.POINT_EMPTY) {
				this.setPointStyle(point, IBrickConstants.DEFAULT_CELL_STYLE);
			} else {
				this.setPointStyle(point, IBrickConstants.BG_COLOR_STYLE_BLACK);
			}
		}
	}
	
	private void clearBrick() {
		setBrickStyle(IBrickConstants.DEFAULT_CELL_STYLE);
	}

	private void setPointStyle(Point point, String style) {
		if(point.getRow() < 0 || point.getRow() >= Board.ROW) {
			return;
		}
		if(point.getColumn() < 0 || point.getColumn() >= Board.COLUMN) {
			return;
		}
		ObservableList<Node> nodeList = this.gridPane.getChildren();
		for(Node node : nodeList) {
			if(GridPane.getRowIndex(node) == point.getRow() &&
					GridPane.getColumnIndex(node) == point.getColumn()) {
				node.setStyle(style);
			}
		}
	}
	
	private void setBrickStyle(String style) {
		if(this.currentBrick != null) {
			for(Point point : this.currentBrick.getAllPoints()) {
				this.setPointStyle(point, style);
			}
		}
	}
	
	private void setGridStyle(String style) {
		ObservableList<Node> nodeList = this.gridPane.getChildren();
		for(Node node : nodeList) {
			node.setStyle(style);
		}
	}
	
	private void setPointStatus(Point point, int status) {
		if(point.getRow() < 0 || point.getRow() >= Board.ROW) {
			return;
		}
		if(point.getColumn() < 0 || point.getColumn() >= Board.COLUMN) {
			return;
		}
		pointStatusMap.put(point, status);
	}
	
	private void setBrickStatus(IBrick brick, int status) {
		for(Point point : brick.getAllPoints()) {
			this.setPointStatus(point, status);
		}
	}
	
	private void setRowStatus(int row, int status) {
		for(Point point : pointStatusMap.keySet()) {
			if(point.getRow() == row) {
				this.setPointStatus(point, status);
			}
		}
	}
	
	private void setGridStatus(int status) {
		for(Point point : pointStatusMap.keySet()) {
			this.setPointStatus(point, status);
		}
	}
	
	public int getPointStatus(Point point) {
		Integer status = pointStatusMap.get(point);
		if(status == null) {
			return IBrickConstants.POINT_OCCUPIED;
		} else {
			return status;
		}
	}
	
	private List<Point> getAboveOccupiedPointsByRow(int row) {
		List<Point> abovePoints = new ArrayList<Point>();
		if(row > 0 && row < Board.ROW) {
			for(Point point : this.pointStatusMap.keySet()) {
				if(point.getRow() < row && this.getPointStatus(point) == IBrickConstants.POINT_OCCUPIED) {
					abovePoints.add(point);
				}
			}
		}
		return abovePoints;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
