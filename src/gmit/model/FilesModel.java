// store the information of files
package gmit.model;

import gmit.File;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;

import java.io.Serializable;
import java.util.ArrayList;

public class FilesModel implements Model, Serializable {

	private static final long serialVersionUID = 8925387013714223244L;
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private ArrayList<File> files = new ArrayList<File>();
	private String friendName = null;
	
	public void changeFiles(ArrayList<File> files) {
		this.files = files;
		notifyObservers();
	}
	
	public void changeFiles(ArrayList<File> files, String friendName) {
		this.files = files;
		this.friendName = friendName;
		notifyObservers();
	}
	
	public String getFriendName() {
		return this.friendName;
	}
	
	public ArrayList<File> getFiles() {
		return this.files;
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
