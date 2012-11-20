// provide the structure of the share frame and provide the method to update itself
package gmit.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import gmit.File;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.FilesModel;

public class ShareView extends JDialog implements Observer {

	private static final long serialVersionUID = 3091776806394453740L;
	private JList filesList = null;
	private JButton addButton = null;
	private JButton delButton = null;
	private JButton finishButton = null;
	
	public ShareView(Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle("Share");
		filesList = new JList();
		filesList.setVisibleRowCount(8);
		filesList.setFixedCellHeight(30);
		filesList.setFixedCellWidth(300);
		filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane filesScroller = new JScrollPane(filesList);
		addButton = new JButton("Add");  // add button
		delButton = new JButton("Del");  // delete button
		finishButton = new JButton("Finish");  // finish button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(300, 60));
		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		buttonPanel.add(finishButton);
		this.getContentPane().add(filesScroller, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		this.setSize(400, 300);
		Toolkit toolkit = Toolkit.getDefaultToolkit();   
        Dimension scmSize = toolkit.getScreenSize();  
        // show form in the middle of screen
        this.setLocation(scmSize.width / 2 - (400 / 2), scmSize.height / 2 - (300 /2)); 
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	}
	
	public void addAListener(ActionListener aL) {
        addButton.addActionListener(aL);
    }
	
	public void addDListener(ActionListener aL) {
        delButton.addActionListener(aL);
    }
	
	public void addFListener(ActionListener aL) {
        finishButton.addActionListener(aL);
    }
	
	public void update(Model model) {
		DefaultListModel dModel = new DefaultListModel(); 
		filesList.setModel(dModel);
		for(File file : ((FilesModel)model).getFiles()) { 
			dModel.addElement(file.getName()); 
		} 
	}
	
	public JList getList() {
		return this.filesList;
	}
	
	// close ShareView
	public void close() {
		this.dispose();
	}
}
