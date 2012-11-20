// provide the structure of the my file list panel and provide the method to update itself
package gmit.view;

import gmit.File;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.FilesModel;

import java.awt.BorderLayout;

import javax.swing.*;

public class MyFileView extends JPanel implements Observer {

	private static final long serialVersionUID = 2067488137404131144L;
	private JList selfFileList = null;
	
	public MyFileView() {
		JPanel selfPanel = new JPanel();
		BorderLayout selfBorder = new BorderLayout();
		selfPanel.setLayout(selfBorder);
		JLabel selfLabel = new JLabel("My Shared Library", SwingConstants.CENTER);
		selfFileList = new JList();  // self file list
		selfFileList.setVisibleRowCount(6);
		selfFileList.setFixedCellHeight(28);
		selfFileList.setFixedCellWidth(330);
		JScrollPane selfScroller = new JScrollPane(selfFileList);
		selfPanel.add(selfLabel, BorderLayout.NORTH);
		selfPanel.add(selfScroller, BorderLayout.CENTER);
		add(selfPanel, BorderLayout.CENTER);
	}
	
	// update my file list
	public void update(Model model) {
		DefaultListModel dModel = new DefaultListModel(); 
		selfFileList.setModel(dModel);
		for(File file : ((FilesModel)model).getFiles()) { 
			dModel.addElement(file.getName()); 
		} 
	}
}
