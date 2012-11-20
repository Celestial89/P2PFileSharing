// send the close message to the friends
package gmit.module;

import java.io.ObjectOutputStream;
import java.net.Socket;
import gmit.Friend;
import gmit.interfaces.Module;
import gmit.model.FriendsModel;

public class ClosingModule implements Module {

	private Friend me = null ;  // information of self
	private FriendsModel friendsModel = null;  // friend list
	
	public ClosingModule(Friend me, FriendsModel friendsModel) {
		this.me = me;
		this.friendsModel = friendsModel;
	}
	
	public void run() {
		try {
			Socket socket = null;
			String fIp = null;
			for(Friend f : friendsModel.getFriends()) {
				fIp = f.getIp();
				socket = new Socket(fIp, 6871);  // connect to friend
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(me);  // send close message to friend
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
