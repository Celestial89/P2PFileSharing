// provide the method to update self's information
package gmit.model;

import gmit.Friend;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;

import java.util.ArrayList;

public class SetNameModel implements Model {
	
	private Friend me = new Friend();
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	
	public SetNameModel(Friend me) {
		this.me = me;
	}
	
	public void setName(String name) {
		this.me.setName(name);
		notifyObservers();
	}
	
	public String getName() {
		return this.me.getName();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	private void notifyObservers() { 
		for(Observer observer : observers) { 
			observer.update(this); 
		} 
	} 
}
