package gmit;

import gmit.interfaces.Module;
import gmit.model.*;
import gmit.module.*;

public class Client {
	public static void main(String[] args) {
		Friend me = new Friend();  // information of self
		FriendsModel fModel = new FriendsModel();  // information of friends who is connected
		FilesModel mfModel = new FilesModel();  // information of self's files
		FilesModel ofModel = new FilesModel();  // information of friend's files
		
		MainModule mainModule = new MainModule(me, fModel, ofModel);
		mainModule.run();
		Module initModule = new InitInfoModule(mainModule.getMainView(), me);
		initModule.run();
		OppFileModule ofModule = new OppFileModule(ofModel, mainModule.getMainView().getOppFileView());
		ofModule.run();
		FriendsModule fModule = new FriendsModule(fModel, ofModule.getOppFileController(), mainModule.getMainView());
		fModule.run();
		Module conModule = new ConnectModule(me, fModule.getFriendsController(), mainModule.getMainView().getConView(), mainModule.getMainView().getStateView());
		conModule.run();
		MyFileModule mFModule = new MyFileModule(mainModule, mfModel);
		mFModule.run();
		Module tModule = new ThreadModule(me, fModel, mfModel, fModule, mainModule);
		tModule.run();
	}
}
