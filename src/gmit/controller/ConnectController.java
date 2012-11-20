// add connect listener, set input rule to the connect view
package gmit.controller;

import gmit.Friend;
import gmit.thread.ConnectThread;
import gmit.view.ConnectView;
import gmit.view.StateView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ConnectController {

	private Friend me;
	private ConnectView conView;
	private StateView stateView;
	private FriendsController fController;
	
	public ConnectController(Friend me, FriendsController fController, ConnectView cView, StateView stateView) {
		this.me = me;
		this.conView = cView;
		this.stateView = stateView;
		this.fController = fController;
	}
	
	public void init() {
		this.conView.addConnectListener(new addConnectListener());
		LimitedDocument ld = new LimitedDocument();
		ld.setAllowChar("1234567890.");  // set the characters that allow input 
		this.conView.setFieldDocument(ld);  // set input rule
	}
	
	class addConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(conView.getField().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please input the ip address.", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Thread conThread = new ConnectThread(me, fController, conView, stateView);
			conThread.start();  // start connect
		}
	}
	
	class LimitedDocument extends PlainDocument { 
		private static final long serialVersionUID = 6132268062954790530L;
		private String allowChar = null; 

		public LimitedDocument(){ 
			super(); 
		} 
		// process the input characters
		public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException{ 
			if(str == null) { 
				return; 
			} 
			if(allowChar != null && str.length() == 1) { 
				if(allowChar.indexOf(str) == -1){ 
					return; 
				} 
			} 
			super.insertString(offset, str, attrSet); 
		} 
		//set the characters that allow input
		public void setAllowChar(String str) { 
			allowChar = str; 
		} 
	}
}
