// initialize and assemble StateView, StateModel and StateController
package gmit.module;

import gmit.controller.StateController;
import gmit.interfaces.Module;
import gmit.model.StateModel;
import gmit.view.StateView;

public class StateModule implements Module {
	
	private StateController sController = null;
	private String s = null;
	private StateView stateView = null;
	
	public StateModule(StateView stateView, String s) {
		this.s = s;
		this.stateView = stateView;
	}

	public void run() {
		StateModel stateModel = new StateModel();
		stateModel.registerObserver(stateView);
		sController = new StateController();
		sController.setModel(stateModel);
		sController.setText(s);
	}

}
