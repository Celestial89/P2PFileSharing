// provide self's information. Send self's information to whom that want to connect, and get
// his information. Then add the information to friend list
package gmit.thread;

import gmit.Friend;
import gmit.controller.FriendsController;
import gmit.interfaces.Module;
import gmit.module.StateModule;
import gmit.view.StateView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class NameServerThread extends Thread {
	
	private Friend me = null;
	private Friend friend = null;
	private FriendsController fController = null;
	private StateView stateView = new StateView();
	
	public NameServerThread(Friend me, FriendsController fController, StateView stateView) {
		super();
		this.me = me;
		this.fController = fController;
		this.stateView = stateView;
	}

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(6868);
			while(true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(me);  // send self's information
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				friend = (Friend) inputStream.readObject(); // get information from whom that want to connect
				fController.addFriend(friend);  // add the information to friend list
				Module module = new StateModule(stateView, friend.getName() + " connected success");
				module.run();
				Thread csThread = new InitStateThread(2000, stateView);
				csThread.start();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
