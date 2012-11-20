// delegate StateModel's method which change state
package gmit.controller;

import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.StateModel;

public class StateController implements Controller {
	
	private StateModel stateModel = new StateModel();
	
	public void setText(String text) {
		this.stateModel.setText(text);
	}

	public void setModel(Model model) {
		this.stateModel = (StateModel) model;
	}

}
