// provide the listener to friend list and delegate the method of FriendsModel
package gmit.controller;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gmit.Friend;
import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.FriendsModel;
import gmit.thread.OppFileThread;
import gmit.view.FriendsView;
import gmit.view.StateView;

public class FriendsController implements Controller {
	
	private FriendsModel friendsModel = null;
	private FriendsView friendView = null;
	private OppFileController ofController = null;
	private StateView stateView = null;
	
	public FriendsController(OppFileController ofController, FriendsView fView, StateView stateView) {
		this.friendView = fView;
		this.ofController = ofController;
		this.stateView = stateView;
		friendView.addFriendListener(new addFriendListener());
	}
	
	public void addFriend(Friend friend) {
		friendsModel.addFriend(friend);
	}
	
	public void removeFriend(Friend friend) {
		friendsModel.removeFriend(friend);
	}
	
	public void removeFriends(ArrayList<Friend> removeFriends) {
		friendsModel.removeFriends(removeFriends);
	}

	public void setModel(Model model) {
		this.friendsModel = (FriendsModel) model;
	}

	class addFriendListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent evt) {
			Thread ofThread = new OppFileThread(friendView, friendsModel, ofController, stateView);
			ofThread.start();
		}
	}
}
