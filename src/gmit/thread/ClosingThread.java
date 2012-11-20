// delete friend from the friend list
package gmit.thread;

import gmit.Friend;
import gmit.controller.FriendsController;
import gmit.interfaces.Module;
import gmit.model.FriendsModel;
import gmit.module.StateModule;
import gmit.view.StateView;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClosingThread extends Thread {

	private Friend friend = null;
	private ArrayList<Friend> removeFriends = new ArrayList<Friend>(); // remove friends list
	private FriendsModel friendsModel = null;
	private FriendsController fController = null;
	private StateView stateView = new StateView();
	
	public ClosingThread(FriendsModel friendsModel, FriendsController fController, StateView stateView) {
		super();
		this.friendsModel = friendsModel;
		this.fController = fController;
		this.stateView = stateView;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(6871);
			while(true) {
				Socket socket = serverSocket.accept();
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				friend = (Friend) inputStream.readObject();  // get information of friend who need to delete
				for(Friend f : friendsModel.getFriends()) {
					if(f.getIp().equals(friend.getIp())) {
						removeFriends.add(f);  // add friend to remove list
					}
				}
				fController.removeFriends(removeFriends);  // delete friend from friend list
				Module module = new StateModule(stateView, friend.getName() + " is off-line");
				module.run();
				Thread isThread = new InitStateThread(2000, stateView);
				isThread.start();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
