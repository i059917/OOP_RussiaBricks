package RussiaBricks;

import RussiaBricks.brick.IBrick;
import RussiaBricks.brick.Point;
import RussiaBricks.brick.Square;
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
	
	private static int ROW = 10;
	private static int COLUMN = 10;
	
	private BorderPane borderPane;
	private GridPane gridPane;
	private Button moveDownButton;
	private Button dockButton;
	private IBrick currentBrick;
	
	public Board() {
		
	}
	
	@Override
	public void init() {
		this.borderPane = new BorderPane();
		this.gridPane = new GridPane();
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COLUMN; j++) {
				Button button = new Button();
				button.setMinSize(30, 30);
				this.gridPane.add(button, j, i);
			}
		}
		this.borderPane.setCenter(this.gridPane);
		
		HBox bottomBar = new HBox();
		this.moveDownButton = new Button("Down");
		this.dockButton = new Button("OK");
		bottomBar.setAlignment(Pos.BASELINE_CENTER);
		bottomBar.getChildren().addAll(this.moveDownButton, this.dockButton);
		this.borderPane.setBottom(bottomBar);
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
		
		ObservableList<Node> nodeList = gridPane.getChildren();
		for(Point pos : brick.getPositions()) {
			for(Node node : nodeList) {
				if(GridPane.getRowIndex(node) == pos.getRow() &&
						GridPane.getColumnIndex(node) == pos.getColumn()) {
					node.setStyle("-fx-background-color: #0000ff");
				}
			}
		}
		this.currentBrick = brick;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
