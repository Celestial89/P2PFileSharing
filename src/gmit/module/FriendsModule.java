// initialize and assemble FriendsView, FriendsModel and FriendsController. Provide the method
// to get FriendsController.
package gmit.module;

import gmit.controller.FriendsController;
import gmit.controller.OppFileController;
import gmit.interfaces.Module;
import gmit.model.FriendsModel;
import gmit.view.ConnectView;
import gmit.view.FriendsView;
import gmit.view.MainView;
import gmit.view.StateView;

public class FriendsModule implements Module {
	
	private FriendsModel fModel = null;
	private FriendsView fView = null;
	private ConnectView conView = null;
	private OppFileController ofController = null;
	private FriendsController fController = null;
	private StateView stateView = null;
	
	public FriendsModule(FriendsModel fModel, OppFileController ofController, MainView mainView) {
		this.fModel = fModel;
		this.fView = mainView.getFriendsView();
		this.conView = mainView.getConView();
		this.ofController = ofController;
		this.stateView = mainView.getStateView();
	}

	public void run() {
		fModel.registerObserver(fView);  // register Observer
		fModel.registerObserver(conView);
		fController = new FriendsController(ofController, fView, stateView);
		fController.setModel(fModel);
	}

	// return FriendsController
	public FriendsController getFriendsController() {
		return this.fController;
	}
}
