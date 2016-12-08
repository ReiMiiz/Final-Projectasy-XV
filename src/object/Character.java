package object;

public abstract class Character extends Entity {
	
	public boolean isAttacked, isDestroyed;
	public int hp, atk, moveSpeed;

	public Character(int x, int y, int z, float dx, float dy, int hp, int atk, int moveSpeed) {
		super(x, y, z, dx, dy);

		this.hp = hp;
		this.atk = atk;
		this.moveSpeed = moveSpeed;
		isDestroyed = false;
		isAttacked = false;
		
	}
	
	// setter
	public void Destroyed() {
		isDestroyed = true;
	}

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

	public boolean isDestroyed() {
		return isDestroyed;
	}

	// other
	public void reduceHP(int dmg) {
		if (hp - dmg > 0) {
			hp = hp - dmg;
		}else{
			hp = 0;
			Destroyed();
		}
	}
	
	//Absract
	public abstract void move();
	public abstract void attack();
	
}
