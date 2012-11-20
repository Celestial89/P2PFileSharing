// delegate FilesModel's method which change my file list
package gmit.controller;

import gmit.File;
import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.FilesModel;

import java.util.ArrayList;

public class MyFileController implements Controller {

	private FilesModel myFileModel = new FilesModel();
	
	// delegate
	public void changeFiles(ArrayList<File> files) {
		myFileModel.changeFiles(files);
	}
	
	public void setModel(Model model) {
		this.myFileModel = (FilesModel) model;
	}

}
