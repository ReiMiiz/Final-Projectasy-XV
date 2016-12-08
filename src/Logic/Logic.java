package Logic;

import object.Noct;
import ui.ObjectHolder;

public class Logic {
	
	private ObjectHolder objectList = new ObjectHolder();
	public Noct noct;
	
	public Logic(){
		noct = new Noct(500, 500, 0, 0, 0, 100, 20, 10);
		objectList.addSort(noct);
		
		
	}
	
}
