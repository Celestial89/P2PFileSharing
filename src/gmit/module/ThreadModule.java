package gmit.module;

import gmit.Friend;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.model.FriendsModel;
import gmit.thread.ClosingThread;
import gmit.thread.FilesSendThread;
import gmit.thread.FilesServerThread;
import gmit.thread.NameServerThread;

public class ThreadModule implements Module {

	private Friend me = null;
	private FriendsModel fModel = null;
	private FilesModel mfModel = null;
	private FriendsModule fModule = null;
	private MainModule mainModule = null;
	
	public ThreadModule(Friend me, FriendsModel fModel, FilesModel mfModel, FriendsModule fModule, MainModule mainModule) {
		this.me = me;
		this.fModel = fModel;
		this.mfModel = mfModel;
		this.fModule = fModule;
		this.mainModule = mainModule;
	}
	
	public void run() {
		Thread nsThread = new NameServerThread(me, fModule.getFriendsController(), mainModule.getMainView().getStateView());
		nsThread.start();
		Thread fsThread = new FilesServerThread(mfModel);
		fsThread.start();
		Thread fsdThread = new FilesSendThread();
		fsdThread.start();
		Thread cThread = new ClosingThread(fModel, fModule.getFriendsController(), mainModule.getMainView().getStateView());
		cThread.start();
	}

}
