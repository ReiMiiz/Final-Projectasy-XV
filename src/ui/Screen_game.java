package ui;

import Logic.Logic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;

public class Screen_game extends StackPane{
	protected Canvas canvas;
	protected GraphicsContext gc;
	protected Image bg;
	
	public Screen_game(double width, double height, Logic logic){
		
		setPrefSize(width, height);
		
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		
		getChildren().add(canvas);
	}
	
	public void setBackground(){
		gc.drawImage(ObjectHolder.getInstance().bg[0], 0, -300);
		
	}
	
}
