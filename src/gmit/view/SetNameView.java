// provide the structure of the set name frame and provide the method to update itself
package gmit.view;

import gmit.interfaces.Model;
import gmit.interfaces.Observer;
import gmit.model.SetNameModel;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SetNameView extends JDialog implements Observer {

	private static final long serialVersionUID = 5224541579382197572L;
	private JTextField textField = null;
	private JButton button = null;
	private JLabel msgLabel = null;
	
	public SetNameView(Frame parent, boolean modal) {
		super(parent, modal);
		this.setTitle("Set Name");
		JLabel label = new JLabel("Please input your name:");
		textField = new JTextField(20);
		button = new JButton("OK");
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(textField);
		panel.add(button);
		msgLabel = new JLabel("", SwingConstants.CENTER);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(msgLabel, BorderLayout.SOUTH);
		this.setSize(300, 150);
		Toolkit toolkit = Toolkit.getDefaultToolkit();   
        Dimension scmSize = toolkit.getScreenSize();  
        // show form in the middle of screen
        this.setLocation(scmSize.width / 2 - (300 / 2), scmSize.height / 2 - (150 /2)); 
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	}
	
	public void addSNameListener(ActionListener aL) {
        button.addActionListener(aL);
    }
	
	public JTextField getField() {
		return textField;
	}

	public void update(Model model) {
		SetNameModel nameModel = (SetNameModel) model;
		if(nameModel.getName().equals("")) {
			msgLabel.setForeground(Color.RED);
			msgLabel.setText("Please input your name.");
			return;
		}
		this.dispose();  // close the frame
	}
}
