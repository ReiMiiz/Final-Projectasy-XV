package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;




public class ObjectHolder {
	private static final ObjectHolder instance = new ObjectHolder();
	
	private List<Renderable> object;
	private Comparator<Renderable> comparator;
	
	Image[] bg;
	
	public ObjectHolder(){
		
		object = new ArrayList<Renderable>();
		comparator = (Renderable o1, Renderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};

	}
	
	public static ObjectHolder getInstance() {
		return instance;
	}
	public void addSort(Renderable entity) {
		add(entity);
		sort();
	}
	public void add(Renderable entity) {
		object.add(entity);
		sort();
	}
	public void sort(){
		Collections.sort(object, comparator);
	}
	public List<Renderable> getObject() {
		return object;
	}
	
	public void LoadRes(){
		
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		bg = new Image[4];
		
		bg[0] = new Image(loader.getResourceAsStream("background0.png"));
		
	}
	
}
