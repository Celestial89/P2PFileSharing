// delegate FilesModel's method which change friend's file list
package gmit.controller;

import java.util.ArrayList;

import gmit.File;
import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.FilesModel;

public class OppFileController implements Controller {

	private FilesModel oppFileModel = null;
	
	public void changeFiles(ArrayList<File> files, String friendName) {
		oppFileModel.changeFiles(files, friendName);
	}
	
	public void setModel(Model model) {
		this.oppFileModel = (FilesModel) model;
	}
}
