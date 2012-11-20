// initialize and assemble MyFileView, FilesModel and MyFileController. Read file information 
// from database and update FilesModel.
package gmit.module;

import gmit.File;
import gmit.controller.MyFileController;
import gmit.interfaces.Module;
import gmit.model.FilesModel;
import gmit.view.MyFileView;

import java.sql.*;
import java.util.ArrayList;

public class MyFileModule implements Module {

	private MyFileView selfView = null;
	private ArrayList<File> files = new ArrayList<File>();
	private FilesModel mFModel = new FilesModel();
	private MainModule mainModule = null;
	
	public MyFileModule(MainModule mModule, FilesModel mFModel) {
		this.mainModule = mModule;
		this.selfView = mainModule.getMainView().getMyFileView();
		this.mFModel = mFModel;
	}
	
	public void run() {
		mFModel.registerObserver(selfView);
		MyFileController mFController = new MyFileController();
		mFController.setModel(mFModel);
		mainModule.setMFController(mFController);
		try {
			// read file info from database
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:FilesDB.db");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Files");
			while (rs.next()) {
				File file = new File();
				file.setID(rs.getInt("ID"));
				file.setName(rs.getString("Name"));
				file.setPath(rs.getString("Path"));
				files.add(file);
			}
			rs.close();
			conn.close();
			mFController.changeFiles(files);  // update file model
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
