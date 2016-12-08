import Logic.Logic;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ObjectHolder;
import ui.Screen_game;

public class Main extends Application{

	Scene gameScene;
	Scene menuScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ObjectHolder.getInstance().LoadRes();
		Group root = new Group();
		Logic logic = new Logic();
		Screen_game game = new Screen_game(1280,720, logic);

		game.setBackground();
		root.getChildren().add(game);
		
		gameScene = new Scene(root, 1280, 720);
		
		primaryStage.setScene(gameScene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}

}
