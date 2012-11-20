// initialize MainController
package gmit.module;

import gmit.Friend;
import gmit.controller.MainController;
import gmit.controller.MyFileController;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.model.FriendsModel;
import gmit.view.MainView;

public class MainModule implements Module {

	private Friend me = null;
	private FriendsModel friendsModel = null;
	private FilesModel ofModel = null;
	private MainView mView = new MainView();
	private MainController mController = null;
	
	public MainModule(Friend me, FriendsModel friendsModel, FilesModel ofModel) {
		this.me = me;
		this.friendsModel = friendsModel;
		this.ofModel = ofModel;
	}
	
	public void run() {
		mController = new MainController(mView, me, friendsModel, ofModel);
		mController.init();
	}

	public MainView getMainView() {
		return this.mView;
	}
	
	public void setMFController(MyFileController mFController) {
		this.mController.setMFController(mFController);
	}
}
