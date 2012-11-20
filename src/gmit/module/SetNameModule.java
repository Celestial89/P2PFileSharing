// initialize and assemble SetNameView, SetNameModel and SetNameController
package gmit.module;

import gmit.Friend;
import gmit.controller.SetNameController;
import gmit.interfaces.Module;
import gmit.model.SetNameModel;
import gmit.view.MainView;
import gmit.view.SetNameView;

public class SetNameModule implements Module{

	private MainView mainView;
	private Friend me;
	
	public SetNameModule(MainView mainView, Friend me) {
		this.mainView = mainView;
		this.me = me;
	}
	
	public void run() {
		SetNameView nameView = new SetNameView(mainView, true);
		SetNameModel nameModel = new SetNameModel(me);
		nameModel.registerObserver(nameView);
		SetNameController nameController = new SetNameController(nameView);
		nameController.setModel(nameModel);
		nameView.setVisible(true);
	}
}
