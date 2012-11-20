// get friend's file list and update it
package gmit.thread;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import gmit.File;
import gmit.Friend;
import gmit.controller.OppFileController;
import gmit.interfaces.Module;
import gmit.model.FriendsModel;
import gmit.module.StateModule;
import gmit.view.FriendsView;
import gmit.view.StateView;

public class OppFileThread extends Thread {
	
	private Friend friend = new Friend();
	private FriendsModel friendsModel = null;
	private OppFileController ofController = null;
	private StateView stateView = null;
	private FriendsView friendView = null;
	private ArrayList<File> files = new ArrayList<File>();
	
	public OppFileThread(FriendsView friendView, FriendsModel friendsModel, OppFileController ofController, StateView stateView) {
		super();
		this.friendView = friendView;
		this.friendsModel = friendsModel;
		this.ofController = ofController;
		this.stateView = stateView;
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		Module module = null;
		friendView.getFriendList().setEnabled(false);
		try {
			int choosedNum = friendView.getFriendList().getSelectedIndex();  
			if(choosedNum >= 0) {
				module = new StateModule(stateView, "Updating friend's file list...");
				module.run();
				friend = friendsModel.getFriends().get(choosedNum);  // get friend
				Socket socket = new Socket(friend.getIp(), 6869);
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				files = (ArrayList<File>) inputStream.readObject();  // get friend's file list
				ofController.changeFiles(files, friend.getName()); // update friend's file list
				socket.close();
				module = new StateModule(stateView, "friend's file list has updated");
				module.run();
			}
			else {
				friend.setName("Opp");
				ofController.changeFiles(files, friend.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			module = new StateModule(stateView, "Friend's file list update failed");
			module.run();
		}
		friendView.getFriendList().setEnabled(true);
		Thread csThread = new InitStateThread(2000, stateView);
		csThread.start();
	}
}
