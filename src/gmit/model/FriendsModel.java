// store the information of friends and provide the method to update friends
package gmit.model;

import gmit.Friend;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;

import java.util.ArrayList;

public class FriendsModel implements Model {

	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	
	public ArrayList<Friend> getFriends() {
		return friends;
	}
	
	public void addFriend(Friend friend) {
		friends.add(friend);
		notifyObservers();
	}
	
	public void removeFriend(Friend friend) {
		friends.remove(friend);
		notifyObservers();
	}
	
	public void removeFriends(ArrayList<Friend> removeFriends) {
		friends.removeAll(removeFriends);
		notifyObservers();
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
