// provide the method to update state
package gmit.model;

import gmit.interfaces.Model;
import gmit.interfaces.Observer;

import java.util.ArrayList;

public class StateModel implements Model{
	
	private String text = "";
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	
	public void setText(String text) {
		this.text = text;
		notifyObservers();
	}
	
	public String getText() {
		return this.text;
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
