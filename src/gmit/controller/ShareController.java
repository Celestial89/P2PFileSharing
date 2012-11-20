// provide listener to the buttons in the ShareView
package gmit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gmit.File;
import gmit.interfaces.Controller;
import gmit.interfaces.Model;
import gmit.model.FilesModel;
import gmit.view.ShareView;

public class ShareController implements Controller {

	private FilesModel filesModel = null;
	private ShareView shareView = null;
	private MyFileController mFController = null;
	
	public ShareController(ShareView sView, MyFileController mFController) {
		this.shareView = sView;
		this.mFController = mFController;
		shareView.addAListener(new addAListener());
		shareView.addDListener(new addDListener());
		shareView.addFListener(new addFListener());
	}
	
	public void changeFiles(ArrayList<File> files) {
		filesModel.changeFiles(files);
	}
	
	public void setModel(Model model) {
		this.filesModel = (FilesModel) model;
	}
	
	class addAListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fChooser = new JFileChooser();
			fChooser.setDialogTitle("Select File");
			int result = fChooser.showOpenDialog(null);  
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = new File();
				file.setName(fChooser.getSelectedFile().getName());
				file.setPath(fChooser.getSelectedFile().getAbsolutePath());
				filesModel.getFiles().add(file);
				try {
					Class.forName("org.sqlite.JDBC");
					Connection conn = DriverManager.getConnection("jdbc:sqlite:FilesDB.db");
					Statement stmt = conn.createStatement();
					// add new file information to database
					stmt.executeUpdate("INSERT INTO Files (Name, Path) VALUES ('" + file.getName() + "', '" + file.getPath() + "')");
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				changeFiles(filesModel.getFiles());
			}
		}
	}
	
	class addDListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!shareView.getList().isSelectionEmpty()) {
				File file = filesModel.getFiles().get(shareView.getList().getSelectedIndex());
				try {
					Class.forName("org.sqlite.JDBC");
					Connection conn = DriverManager.getConnection("jdbc:sqlite:FilesDB.db");
					Statement stmt = conn.createStatement();
					// delete file from database
					stmt.executeUpdate("DELETE FROM Files where ID = " + file.getID());
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				filesModel.getFiles().remove(file);
				changeFiles(filesModel.getFiles());
			}
			else {
				JOptionPane.showMessageDialog(null, "Please select the file you want to delete.", "Message", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	class addFListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<File> files = filesModel.getFiles();
			mFController.changeFiles(files);  // update self's file list
			shareView.close();  // close frame
		}
	}
}
