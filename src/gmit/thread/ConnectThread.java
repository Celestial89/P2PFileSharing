// connect to friend. Send your information to friend and get your friend's information. Add
// friend to friend list and show connect message
package gmit.thread;

import gmit.Friend;
import gmit.controller.FriendsController;
import gmit.interfaces.Module;
import gmit.module.StateModule;
import gmit.view.ConnectView;
import gmit.view.StateView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ConnectThread extends Thread {
	
	private Friend me = new Friend();
	private Friend friend = new Friend();
	private FriendsController fController = null;
	private ConnectView conView = new ConnectView();
	private StateView stateView = new StateView();
	private Module sModule = null;
	
	public ConnectThread(Friend me, FriendsController fController, ConnectView conView, StateView stateView) {
		super();
		this.me = me;
		this.fController = fController;
		this.conView = conView;
		this.stateView = stateView;
	}

	public void run() {
		String conIp = conView.getField().getText();
		// not allow to connect self
		if(!conIp.equals(me.getIp())) {
			try {
				sModule = new StateModule(stateView, "Connecting...");
				sModule.run();
				Socket socket = new Socket(conIp, 6868);
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(me);  // send self information
				friend = (Friend) inputStream.readObject();  // get friend information
				fController.addFriend(friend);  // add friend to friend list
				socket.close();
				sModule = new StateModule(stateView, "Connected Success");
				sModule.run();
			} catch (Exception e) {
				e.printStackTrace();
				sModule = new StateModule(stateView, "Connected Failed");
				sModule.run();
			}
			Thread csThread = new InitStateThread(3000, stateView);
			csThread.start();
		}
		else{
			JOptionPane.showMessageDialog(null, "Cannot connect to yourself!", "Message", JOptionPane.WARNING_MESSAGE);
		}
	}
}
