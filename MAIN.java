package particleSystem;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class MAIN extends Application{

	@Override
	public void start(Stage stage)  {
		//setup Canvas in Group LayoutContainer
		Group root = new Group();
		Canvas canvas = new Canvas(800, 800);
		root.getChildren().add(canvas);
		
		//GAMELOOP
		GameLoop loop = new GameLoop(canvas, 8000);
		loop.startLoop();
		
		//setup of the Window
		Scene scene = new Scene(root, 800, 800);
		stage.setScene(scene);
		stage.show();
	}
	
	//MAIN method
	public static void main(String[] args)	{
		launch(args);
	}
	
}
