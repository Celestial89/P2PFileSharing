//Initialize connect controller
package gmit.module;

import gmit.Friend;
import gmit.controller.ConnectController;
import gmit.controller.FriendsController;
import gmit.interfaces.Module;
import gmit.view.ConnectView;
import gmit.view.StateView;

public class ConnectModule implements Module {

	private Friend me = null;
	private FriendsController fController = null;  // friend controller
	private ConnectView conView = null;
	private StateView stateView = null;
	
	public ConnectModule(Friend me, FriendsController fController, ConnectView conView, StateView stateView) {	
		this.me = me;
		this.fController = fController;
		this.conView = conView;
		this.stateView = stateView;
	}
	
	public void run() {
		ConnectController conController = new ConnectController(me, fController, conView, stateView);
		conController.init();
	}

}
