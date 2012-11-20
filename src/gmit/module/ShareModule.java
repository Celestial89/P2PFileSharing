// initialize and assemble ShareView and ShareController. Read my file information from database 
// and show them in the ShareView.
package gmit.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import gmit.File;
import gmit.controller.MyFileController;
import gmit.controller.ShareController;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.view.MainView;
import gmit.view.ShareView;

public class ShareModule implements Module {

	private ArrayList<File> files = new ArrayList<File>();
	private MyFileController mFController = null;
	private MainView mainView = null;
	
	public ShareModule(MyFileController mFController, MainView mainView) {
		this.mFController = mFController;
		this.mainView = mainView;
	}
	
	public void run() {
		FilesModel sFModel = new FilesModel();
		ShareView sView = new ShareView(mainView, true);
		sFModel.registerObserver(sView);
		ShareController sController = new ShareController(sView, mFController);
		sController.setModel(sFModel);
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:FilesDB.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Files");  // read self's file information form database
			while (rs.next()) {
				File file = new File();
				file.setID(rs.getInt("ID"));
				file.setName(rs.getString("Name"));
				file.setPath(rs.getString("Path"));
				files.add(file);
			}
			rs.close();
			conn.close();
			sController.changeFiles(files);  // update file list of ShareView
		} catch (Exception e) {
			e.printStackTrace();
		}
		sView.setVisible(true);
	}

}
