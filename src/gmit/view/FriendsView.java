// provide the structure of the friend panel. Provide the method to update itself, add listener 
// to friend list.
package gmit.view;

import gmit.Friend;
import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.FriendsModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class FriendsView extends JPanel implements Observer {

	private static final long serialVersionUID = 9169065273852413430L;
	private JList friendList = null;
	
	public FriendsView() {
		JPanel conPanel = new JPanel();
		BorderLayout conBorder = new BorderLayout();
		conPanel.setLayout(conBorder);
		JLabel conLabel = new JLabel("Connected Friends", SwingConstants.CENTER);
		friendList = new JList();  // connected clients list
		friendList.setVisibleRowCount(9);
		friendList.setFixedCellHeight(28);
		friendList.setFixedCellWidth(330);
		friendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane flScroller = new JScrollPane(friendList);
		JPanel nullPanel = new JPanel();
		nullPanel.setPreferredSize(new Dimension(300, 15));
		conPanel.add(conLabel, BorderLayout.NORTH);
		conPanel.add(flScroller, BorderLayout.CENTER);
		conPanel.add(nullPanel, BorderLayout.SOUTH);
		add(conPanel, BorderLayout.CENTER);
	}
	
	// update friend list
	public void update(Model model) {
		DefaultListModel dModel = new DefaultListModel(); 
		for(Friend friend : ((FriendsModel)model).getFriends()) { 
			String f = "[" + friend.getIp() + "]" + "   " + friend.getName();
			dModel.addElement(f); 
		} 
		friendList.setModel(dModel);
	}

	// add listener
	public void addFriendListener(ListSelectionListener lsL) {
		friendList.addListSelectionListener(lsL);
    }
	
	// get friend list
	public JList getFriendList() {
		return friendList;
	}
}
