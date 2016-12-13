package ui;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Logic.Logic;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import main.Main;

public class Screen_game extends StackPane {
	protected Canvas canvas, backCanvas, overlayCanvas;
	protected GraphicsContext gc, back, overlay;
	protected Image bg;
	private double height, width;
	private static double mouseX;
	private static double mouseY;
	private boolean isPause;
	private Logic logic;
	private MediaView view;
	private MediaPlayer player;
	private ScheduledExecutorService ses;

	public Screen_game(double width, double height, Logic logic) {
		this.logic = logic;
		this.width = width;
		this.height = height;
		setPrefSize(width, height);
		view = new MediaView();
		overlayCanvas = new Canvas(width, height);
		canvas = new Canvas(width, height);
		backCanvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		back = backCanvas.getGraphicsContext2D();
		overlay = overlayCanvas.getGraphicsContext2D();

		getChildren().add(backCanvas);
		getChildren().add(canvas);
		getChildren().add(overlayCanvas);

		canvas.setFocusTraversable(true);
		back.drawImage(ObjectHolder.getInstance().bg[0], 0, 0);
		// event
		addEvent();
		playSound();

	}

	public void addEvent(){
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!logic.PressingKey.contains(event.getCode())) {
					if (event.getCode() == KeyCode.ENTER) {
						if (!isPause) {
							Main.getInstance().stopUpdate();
							overlay.drawImage(ObjectHolder.getInstance().pause, 0, 0);
							isPause = true;
						} else {
							Main.getInstance().startUpdate();
							overlay.clearRect(0, 0, 1280, 720);
							isPause = false;
						}
					}else if(event.getCode() == KeyCode.ESCAPE && isPause){
						
						Main.getInstance().toMenu();
						for(int i =  ObjectHolder.getInstance().getObject().size()-1; i >= 0; i--){
							ObjectHolder.getInstance().getObject().remove(i);
						}
						
					}else{
						logic.PressingKey.add(event.getCode());
					}
					

				}

			}
		});

		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				if (logic.PressingKey.contains(event.getCode())) {
					logic.PressingKey.remove(event.getCode());

					if (!isPause) {
						if (event.getCode() == KeyCode.UP) {
							logic.hanzo.Jumpable = true;
						} else if (event.getCode() == KeyCode.W) {
							logic.genji.Jumpable = true;
						} else if (event.getCode() == KeyCode.S) {
							logic.genji.setDown(false);
						} else if (event.getCode() == KeyCode.J || event.getCode() == KeyCode.K) {
							logic.genji.attackable = true;
						} else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
							logic.genji.lastDirectX = logic.genji.getDirectX();
						} else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
							logic.hanzo.lastDirectX = logic.hanzo.getDirectX();
						} else if (event.getCode() == KeyCode.DOWN) {
							logic.hanzo.setDown(false);
						}
					}
					

				}
			}
		});

		overlayCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				if (event.getButton() == MouseButton.SECONDARY) {
					System.out.println(event.getSceneX() + " , " + event.getSceneY());
				}
			}
		});

		overlayCanvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY && !isPause) {
					logic.hanzo.attack(0, mouseX, mouseY);

				}

			}
		});

		overlayCanvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {

					mouseX = event.getSceneX();
					mouseY = event.getSceneY();
				}

			}
		});
		overlayCanvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
					mouseX = event.getSceneX();
					mouseY = event.getSceneY();
				}

			}
		});
	}
	
	public void setBackground(int n) {
		back.clearRect(0, 0, width, height);
		back.drawImage(ObjectHolder.getInstance().bg[n], 0, -300);
		back.setFill(Color.LIGHTGREEN);
		back.fillRect(0, 0, 0, 0);
	}

	public void drawObject() {
		gc.clearRect(0, 0, width, height);
		drawHP();
		for (Renderable obj : ObjectHolder.getInstance().getObject()) {
			obj.draw(gc);
		}
	}

	public void drawHP() {
		gc.drawImage(ObjectHolder.getInstance().hpBar, 0, 0);
		gc.drawImage(new WritableImage(ObjectHolder.getInstance().hpGreen.getPixelReader(), 50, 0,
				(int) (441 * ((double) logic.genji.getHP() / 200.0) + 1), 70), 51, 0);
		gc.drawImage(
				new WritableImage(ObjectHolder.getInstance().hpGreen.getPixelReader(),
						789 + (441 - (int) (441 * ((double) logic.hanzo.getHP() / 200.0) + 1)), 0,
						(int) (441 * ((double) logic.hanzo.getHP() / 200.0) + 1), 70),
				789 + (441 - (int) (441 * ((double) logic.hanzo.getHP() / 200.0) + 1)), 0);
	}

	
	public void startGame(){
		ses = Executors.newSingleThreadScheduledExecutor();
		
		ses.scheduleAtFixedRate(new Runnable() {
			int i = 0;
		    @Override
		    public void run() {
		    	overlay.clearRect(0, 0, 1280, 720);
		    	overlay.drawImage(new WritableImage(ObjectHolder.getInstance().battle.getPixelReader(), 0, i*720, 1280, 720), 0, 0);
		    	if(i<=3){
		    		i++;
		    	}
		    	
		    }
		}, 0, 1, TimeUnit.SECONDS);

		
	}
	
	public void playSound(){
		startGame();
		Media media = new Media(new File("media/music/startGame.mp3").toURI().toString());
		player = new MediaPlayer(media);
		player.play();
		player.setVolume(0.1);
		view.setMediaPlayer(player);
		player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
        
                player.stop();
                Media media = new Media(new File("media/music/hanamura.mp3").toURI().toString());
                MediaPlayer player = new MediaPlayer(media);
                player.play();
        		player.setVolume(0.01);
                player.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
                ses.shutdown();
                overlay.clearRect(0, 0, 1280, 720);
                Main.getInstance().startUpdate();
                return;
            }
        });
	}
	
	public void endScreen(){
		Media media = new Media(new File("media/hanzoend.mp4").toURI().toString());
		player = new MediaPlayer(media);
		MediaView view = new MediaView(player);
		getChildren().add(view);
		player.play();
		overlay.drawImage(ObjectHolder.getInstance().victory[0], 0, 0);
	}
}
