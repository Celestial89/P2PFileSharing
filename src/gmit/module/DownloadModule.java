// Get the files that you want to download and send to file receive thread
package gmit.module;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import gmit.File;
import gmit.Friend;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.model.FriendsModel;
import gmit.thread.FilesReceiveThread;
import gmit.view.MainView;

public class DownloadModule implements Module {

	private MainView mainView = null;
	private FriendsModel friendsModel = null;
	private FilesModel ofModel = null;
	
	public DownloadModule(MainView mainView, FriendsModel friendsModel, FilesModel ofModel) {
		this.mainView = mainView;
		this.friendsModel = friendsModel;
		this.ofModel = ofModel;
	}
	
	public void run() {
		// if has selected the files that want to download, send it to file receive thread
		if(!mainView.getOppFileView().getOppFileList().isSelectionEmpty()) {
			// get the friend these files belongs to
			Friend friend = friendsModel.getFriends().get(mainView.getFriendsView().getFriendList().getSelectedIndex());
			// get the files want to download
			int[] selected = mainView.getOppFileView().getOppFileList().getSelectedIndices();
			ArrayList<File> files = new ArrayList<File>();
			for(int num : selected) {
				files.add(ofModel.getFiles().get(num));
			}
			Thread frThread = new FilesReceiveThread(friend, files, mainView.getStateView());
			frThread.start();  // start file receive thread
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select the file you want to download.", "Message", JOptionPane.WARNING_MESSAGE);
		}
	}

}
