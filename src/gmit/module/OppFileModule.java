// initialize and assemble OppFileView and OppFileController
package gmit.module;

import gmit.controller.OppFileController;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.view.OppFileView;

public class OppFileModule implements Module {
	
	private FilesModel ofModel = null;
	private OppFileView ofView = null;
	private OppFileController ofController = null;
	
	public OppFileModule(FilesModel ofModel, OppFileView ofView) {
		this.ofModel = ofModel;
		this.ofView = ofView;
	}
	
	public void run() {
		ofController = new OppFileController();
		ofModel.registerObserver(ofView);
		ofController.setModel(ofModel);
	}
	
	public OppFileController getOppFileController() {
		return this.ofController;
	}

}
