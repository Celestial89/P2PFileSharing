// provide the structure of the connect frame and provide the method to update itself
package gmit.view;

import gmit.interfaces.Model;
import gmit.interfaces.Observer;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.PlainDocument;

public class ConnectView extends JPanel implements Observer{

	private static final long serialVersionUID = 7819154476051550748L;
	private JButton conButton = null;
	private JTextField conField = null;
	
	//initialize the connect panel
	public ConnectView() {
		JPanel conPanel = new JPanel();
		conField = new JTextField(20);
		conButton = new JButton("Connect");
		conPanel.add(conField);
		conPanel.add(conButton);
		conPanel.setPreferredSize(new Dimension(340, 52));
		add(conPanel, BorderLayout.CENTER);
	}
	
	// add listener method
	public void addConnectListener(ActionListener aL) {
        conButton.addActionListener(aL);
    }
	
	// set input rule method
	public void setFieldDocument(PlainDocument doc) {
		conField.setDocument(doc);
	}
	
	public JTextField getField() {
		return this.conField;
	}

	public void update(Model model) {
		conField.setText("");
	}
}
