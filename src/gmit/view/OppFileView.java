// provide the structure of the friend's file list panel and provide the method to update itself
package gmit.view;

import gmit.File;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.FilesModel;
import java.awt.BorderLayout;
import  javax.swing.*;

public class OppFileView extends JPanel implements Observer {

	private static final long serialVersionUID = 2514100390113208199L;
	private JLabel oppLabel;
	private JList oppFileList;
	
	public OppFileView() {
		JPanel oppPanel = new JPanel();
		BorderLayout oppBorder = new BorderLayout();
		oppPanel.setLayout(oppBorder);
		oppLabel = new JLabel("Opp's Shared Library", SwingConstants.CENTER);
		oppFileList = new JList();  // opposite client file list
		oppFileList.setVisibleRowCount(6);
		oppFileList.setFixedCellHeight(28);
		oppFileList.setFixedCellWidth(330);
		JScrollPane oppScroller = new JScrollPane(oppFileList);
		oppPanel.add(oppLabel, BorderLayout.NORTH);
		oppPanel.add(oppScroller, BorderLayout.CENTER);
		add(oppPanel, BorderLayout.CENTER);
	}

	// update friend's file list and friend's name
	public void update(Model model) {
		FilesModel oFModel = (FilesModel) model;
		oppLabel.setText(oFModel.getFriendName() + "'s Shared Library");
		DefaultListModel dModel = new DefaultListModel(); 
		oppFileList.setModel(dModel);
		for(File file : oFModel.getFiles()) { 
			dModel.addElement(file.getName()); 
		} 
	}
	
	public JList getOppFileList() {
		return oppFileList;
	}
}
