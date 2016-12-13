package object;

public abstract class Character extends Entity {
	
	public boolean isAttacked;
	public int hp, moveSpeed, lastDirectX;
	public boolean isMoving, Jumpable, attackable, isDown, isJump, isMove, isAir, isAtk;

	public Character(double x, double y, double z, int dx, int dy, int hp, int moveSpeed) {
		super(x, y, z, dx, dy);

		this.hp = hp;
		this.moveSpeed = moveSpeed;
		isDestroyed = false;
		isAttacked = false;
		Jumpable = true;
		attackable = true;
		lastDirectX = dx;
		
	}
	
	// setter

	public void setAttacked(boolean atked) {
		isAttacked = atked;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}
	
	// getter
	public boolean isAttacked() {
		return isAttacked;
	}
	public int getHP(){
		return hp;
	}


	// other
	public void reduceHP(int dmg) {
		if (hp - dmg > 0) {
			hp = hp - dmg;
		}else{
			hp = 0;
			
		}
	}
	
	//Absract
	public abstract void attack();
	
}
