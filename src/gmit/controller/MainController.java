// provide the listener to the buttons in the MainView
package gmit.controller;

import gmit.Friend;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.model.FriendsModel;
import gmit.module.ClosingModule;
import gmit.module.DownloadModule;
import gmit.module.SetNameModule;
import gmit.module.ShareModule;
import gmit.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainController {
	
	private MainView mainView;
	private Friend me;
	private FriendsModel friendsModel;
	private FilesModel ofModel;
	private MyFileController mFController;
	
	public MainController(MainView mView, Friend me, FriendsModel friendsModel, FilesModel ofModel) {
		this.mainView = mView;
		this.me = me;
		this.friendsModel = friendsModel;
		this.ofModel = ofModel;
	}
	
	public void init() {
		mainView.addSetNameListener(new addSetNameListener());
		mainView.addShareListener(new addShareListener());
		mainView.addRefreshListener(new addRefreshListener());
		mainView.addDownloadListener(new addDownloadListener());
		mainView.addWindowListener(new winAdapter());
	}
	
	public void setMFController(MyFileController mFController) {
		this.mFController = mFController;
	}
	
	// set the method running when the program is closing
	class winAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent windowEvent) {
			Module cModule = new ClosingModule(me, friendsModel);
			cModule.run();
			System.exit(0);
		}
	}


	class addSetNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Module module = new SetNameModule(mainView, me);
			module.run();
		}
	}
	
	class addShareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Module module = new ShareModule(mFController, mainView);
			module.run();
		}
	}
	
	class addRefreshListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainView.getFriendsView().getFriendList().clearSelection();
		}
	}
	
	class addDownloadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Module module = new DownloadModule(mainView, friendsModel, ofModel);
			module.run();
		}
	}
}
