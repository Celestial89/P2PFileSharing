// update the "userInfo.txt" file and self's information
package gmit.controller;

import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.SetNameModel;
import gmit.view.SetNameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class SetNameController implements Controller {

	private SetNameModel meModel = null;
	private SetNameView setNameView = null;
	
	public SetNameController(SetNameView sNameView) {
		this.setNameView = sNameView;
		setNameView.addSNameListener(new addSNameListener());
	}
	
	public void setName(String name) {
		meModel.setName(name);
	}
	
	public void setModel(Model model) {
		this.meModel = (SetNameModel) model;
	}

	class addSNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = setNameView.getField().getText();
			try {
				FileWriter fw=new FileWriter("userInfo.txt");
				fw.write(name);
				fw.close();
				setName(name);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
