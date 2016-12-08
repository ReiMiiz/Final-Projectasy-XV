package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Noct extends Character{

	public Noct(int x, int y, int z, float dx, float dy, int hp, int atk, int moveSpeed) {
		super(x, y, z, dx, dy, hp, atk, moveSpeed);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillOval(x, y, 50, 100);
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollide() {
		// TODO Auto-generated method stub
		return false;
	}

}
