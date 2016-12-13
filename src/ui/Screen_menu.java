package ui;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import main.Main;

public class Screen_menu extends StackPane{
	
	private ClassLoader loader = ClassLoader.getSystemClassLoader();
	private Canvas canvas;
	private GraphicsContext gc;
	private Button playBt;
	private GridPane overlay;
	private MediaPlayer player, soundPlayer;
	
	public Screen_menu(){
		
		setPrefSize(1280, 720);
		setAlignment(Pos.CENTER);
		overlay = new GridPane();
		canvas = new Canvas(1280, 720);
		gc = canvas.getGraphicsContext2D();
		playBt = new Button();
		playBt.setGraphic(new ImageView(new WritableImage(ObjectHolder.getInstance().playBt.getPixelReader(),0, 0, 300, 100)));	
		
		setBackground();
		overlay.getColumnConstraints().add(new ColumnConstraints(100));
		overlay.setAlignment(Pos.CENTER_LEFT);
		getChildren().add(overlay);
		drawButton();
		
		playBt.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
					goToLoadingScreen();
				}
			}
			
		});
		
	}
	
	public void drawButton(){
		
		playBt.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(event.getEventType() == MouseEvent.MOUSE_ENTERED){
					playBt.setGraphic(new ImageView(new WritableImage(ObjectHolder.getInstance().playBt.getPixelReader(),0, 100, 300, 100)));	
				}
			}
			
		});
		playBt.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(event.getEventType() == MouseEvent.MOUSE_EXITED){
					playBt.setGraphic(new ImageView(new WritableImage(ObjectHolder.getInstance().playBt.getPixelReader(),0, 0, 300, 100)));	
				}
			}
			
		});
		
		playBt.setStyle("-fx-background-color: transparent;");
		overlay.add(playBt, 1, 0);
	}
	public void setBackground(){
		Group root = new Group();
		Media media = new Media(new File(new File("media/hanzo_bg.mp4").getAbsolutePath()).toURI().toString());
		Media bgSound = new Media(new File(new File("media/music/main.mp3").getAbsolutePath()).toURI().toString());
		player = new MediaPlayer(media);
		soundPlayer = new MediaPlayer(bgSound);
		MediaView view = new MediaView(player);
		MediaView soundView = new MediaView(soundPlayer);
		root.getChildren().add(view);
		root.getChildren().add(new ImageView(ObjectHolder.getInstance().title));
		
		view.setFitHeight(720);
		getChildren().add(root);
		player.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		soundPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		player.play();
		soundPlayer.play();
		
		player.setVolume(0);
		soundPlayer.setVolume(0.1);
		
	}
	
	public void goToLoadingScreen(){
		
		Main.getInstance().toGame();
		player.stop();
		soundPlayer.stop();
		
	}
}
