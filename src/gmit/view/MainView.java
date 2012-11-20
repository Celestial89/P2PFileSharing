// compose the sub-view to a whole view, and provide the method to get these sub-views
package gmit.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MainView extends JFrame{	

	private static final long serialVersionUID = 6811702939447868427L;
	private JFrame mainFrame = null;
	private JButton nameButton = null;
	private StateView statePanel = null;
	private ConnectView conPanel = null;
	private FriendsView friendsPanel = null;
	private MyFileView selfPanel = null;
	private OppFileView oppPanel = null;
	private JButton shareButton = null;
	private JButton refreshButton = null;
	private JButton downButton = null;
	
	public MainView() {
		mainFrame = new JFrame("P2P File Sharing Client");
		JPanel mainPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		mainPanel.setLayout(flow);
		JPanel leftPanel = new JPanel();
		BorderLayout leftBorder = new BorderLayout();
		leftPanel.setLayout(leftBorder);
		oppPanel = new OppFileView();  // OppFileView
		selfPanel = new MyFileView();  // MyFileView
		downButton = new JButton("Download");  // download button
		JPanel leftBtonPanel = new JPanel();
		leftBtonPanel.add(downButton);
		leftPanel.add(oppPanel, BorderLayout.NORTH);
		leftPanel.add(leftBtonPanel, BorderLayout.CENTER);
		leftPanel.add(selfPanel, BorderLayout.SOUTH);
		JPanel rightPanel = new JPanel();
		BorderLayout rightBorder = new BorderLayout();
		rightPanel.setLayout(rightBorder);
		friendsPanel = new FriendsView();
		JPanel actionPanel = new JPanel();
		BorderLayout actionBorder = new BorderLayout();
		actionPanel.setLayout(actionBorder);
		JPanel rightBtonPanel = new JPanel();
		nameButton = new JButton("SetName");  // setName button
		shareButton = new JButton("Share");  // share button
		refreshButton = new JButton("Refresh");  // refresh button
		rightBtonPanel.add(nameButton);
		rightBtonPanel.add(shareButton);
		rightBtonPanel.add(refreshButton);
		rightBtonPanel.setPreferredSize(new Dimension(100, 45));
		conPanel = new ConnectView();
		statePanel = new StateView();
		actionPanel.add(rightBtonPanel, BorderLayout.NORTH);
		actionPanel.add(conPanel, BorderLayout.CENTER);
		actionPanel.add(statePanel, BorderLayout.SOUTH);
		rightPanel.add(friendsPanel, BorderLayout.CENTER);
		rightPanel.add(actionPanel, BorderLayout.SOUTH);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainFrame.setSize(720, 500);
		Toolkit toolkit = Toolkit.getDefaultToolkit();   
        Dimension scmSize = toolkit.getScreenSize();  
        // show form in the middle of screen
        mainFrame.setLocation(scmSize.width / 2 - (720 / 2), scmSize.height / 2 - (500 /2)); 
        mainFrame.setVisible(true);
	}
	
	public void addWindowListener(WindowListener wL) {
		mainFrame.addWindowListener(wL);
	}

	public void addSetNameListener(ActionListener aL) {
        nameButton.addActionListener(aL);
    }
	
	public void addShareListener(ActionListener aL) {
        shareButton.addActionListener(aL);
    }
	
	public void addRefreshListener(ActionListener aL) {
        refreshButton.addActionListener(aL);
    }
	
	public void addDownloadListener(ActionListener aL) {
		downButton.addActionListener(aL);
	}
	
	public JButton getDownButton() {
		return this.downButton;
	}
	
	public StateView getStateView() {
		return this.statePanel;
	}

	public ConnectView getConView() {
		return this.conPanel;
	}
	
	public FriendsView getFriendsView() {
		return this.friendsPanel;
	}
	
	public MyFileView getMyFileView() {
		return this.selfPanel;
	}
	
	public OppFileView getOppFileView() {
		return this.oppPanel;
	}
}
