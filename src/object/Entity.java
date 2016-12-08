package object;

import javafx.scene.canvas.GraphicsContext;
import ui.Renderable;

public abstract class Entity implements Renderable{

	public int x, y, z;
	public float directX, directY;
	
	
	public Entity(int x, int y, int z, float dx, float dy){
		this.x = x;
		this. y = y;
		this.z = z;
		this.directX = dx;
		this.directY = dy;
	}
	
	//getter
	public int getX(){
		return x;
	}
	public int getY(){
		return x;
	}
	public int getZ(){
		return z;
	}
	public float getDirectX(){
		return directX;
	}
	public float getDirectY(){
		return directY;
	}

	
	//setter
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setZ(int z){
		this.z = z;
	}
	public void setDirectX(float x){
		this.directX = x;
	}
	public void setDirectY(float y){
		this.directY = y;
	}

	
	@Override
	public abstract void draw(GraphicsContext gc);
	public abstract boolean isCollide();
}
