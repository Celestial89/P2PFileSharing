// provide the structure of the state panel and provide the method to update itself
package gmit.view;

import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.StateModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class StateView extends JPanel implements Observer {

	private static final long serialVersionUID = 1495361964789414177L;
	private JLabel stateLabel;
	
	public StateView() {
		JPanel statePanel = new JPanel();
		stateLabel = new JLabel("Welcome to use file sharing application", SwingConstants.CENTER);
		statePanel.add(stateLabel);
		add(stateLabel, BorderLayout.CENTER);
	}

	public void update(Model model) {
		String s = ((StateModel) model).getText();
		if(s.equals("Connecting...") || s.equals("Updating friend's file list...") || s.substring(0, 14).equals("Downloading..."))
			stateLabel.setForeground(Color.BLUE);
		else if(s.equals("Connected Failed") || s.equals("Friend's file list update failed") || s.equals("Download Failed") || s.substring(s.length() - 11).equals("is off-line"))
			stateLabel.setForeground(Color.RED);
		else if(s.substring(s.length() - 17).toLowerCase().equals("connected success") || s.equals("friend's file list has updated") || s.equals("One friend connected success") || s.equals("Download Complete"))
			stateLabel.setForeground(Color.GREEN);
		else
			stateLabel.setForeground(Color.BLACK);
		stateLabel.setText(s);
	}

}
