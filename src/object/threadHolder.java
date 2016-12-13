package object;

import java.util.ArrayList;
import java.util.List;

public class threadHolder {
	private static final threadHolder instance = new threadHolder();
	public List<Thread> threads;
	
	public threadHolder(){
		threads = new ArrayList<Thread>();
	}
	
	public threadHolder getInstance(){
		return instance;
	}
	public List<Thread> getThreads(){
		return threads;
	}
}
