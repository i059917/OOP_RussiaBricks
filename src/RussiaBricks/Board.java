package RussiaBricks;

import RussiaBricks.brick.IBrick;
import RussiaBricks.brick.Point;
import RussiaBricks.brick.Square;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board extends Application {
	
	private static int ROW = 10;
	private static int COLUMN = 10;
	
	private GridPane gridPane;
	
	public Board() {
		
	}
	
	@Override
	public void init() {
		this.gridPane = new GridPane();
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COLUMN; j++) {
				Button button = new Button();
				button.setMinSize(30, 30);
				gridPane.add(button, j, i);
			}
		}
	}
	
	@Override
	public void stop() {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.init();
		this.accept(new Square());
		
		primaryStage.setTitle("Russia Bricks");
        Scene scene = new Scene(this.gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void accept(IBrick shape) {
		if(shape == null) {
			return;
		}
		
		ObservableList<Node> nodeList = gridPane.getChildren();
		for(Point pos : shape.getPositions()) {
			for(Node node : nodeList) {
				if(GridPane.getRowIndex(node) == pos.getRow() &&
						GridPane.getColumnIndex(node) == pos.getColumn()) {
					node.setStyle("-fx-background-color: #0000ff");
				}
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
