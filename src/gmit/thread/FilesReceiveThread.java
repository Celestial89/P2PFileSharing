// receive files from friend
package gmit.thread;

import gmit.Friend;
import gmit.interfaces.Module;
import gmit.module.StateModule;
import gmit.view.StateView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FilesReceiveThread extends Thread {

	private Friend friend = null;
	private ArrayList<gmit.File> oFiles = null;
	private Module module = null;
	private StateView stateView = new StateView();
	private int num = 0;
	
	public FilesReceiveThread(Friend friend, ArrayList<gmit.File> files, StateView stateView) {
		this.friend = friend;
		this.oFiles = files;
		this.stateView = stateView;
	}
	
	public void run() {
		File dir = new File("download");
		// check if the "download" folder is exist
		if(!dir.exists()) {
			dir.mkdir();
		}
		Socket socket = null;
		num = 1;  // sign which file is downloading
		for(gmit.File oFile : oFiles) {
			module = new StateModule(stateView, "Downloading...   " + num + "/" + oFiles.size());
			module.run();
			File file = new File("download/" + oFile.getName());  // the save path
			// check if the file which downloading is exist
			if(file.exists()) {
				int result = JOptionPane.showConfirmDialog( null , oFile.getName() + " already exists, do you want to overwrite?\nSelect \"Yes\", overwrite it, select \"No\", skip downloading this file." , "Warning", JOptionPane.YES_NO_OPTION );
				if (result == JOptionPane.YES_OPTION) {
					receive(socket, oFile);  // download file
				}
			}
			else
				receive(socket, oFile);
			num++;
		}
		module = new StateModule(stateView, "Download Complete");
		module.run();
		Thread csThread = new InitStateThread(3000, stateView);
		csThread.start();
	}
	
	// download file method
	private void receive(Socket socket, gmit.File oFile) {
		try {
			socket = new Socket(friend.getIp(), 6870);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(oFile);
			int bufferSize = 8192;  // the size of buffer
			byte[] buf = new byte[bufferSize];  // the buffer
			DataInputStream diStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String savePath = "download\\" + diStream.readUTF();  // get file name
			long len = diStream.readLong();  // the size of file
			long passedLen = 0;  // the size has been download
			DataOutputStream doStream = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(savePath))));
			while(true) {
				int read = 0;
				if (diStream != null) {
					read = diStream.read(buf);  // read file from friend
				}
				passedLen += read;
				if (read == -1) {
					break;
				}
				module = new StateModule(stateView, "Downloading...   "  + (passedLen * 100/ len) + "% Completed   " + num + "/" + oFiles.size());
				module.run();
				doStream.write(buf, 0, read);  // write file to local path
			}
			diStream.close();
			doStream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			module = new StateModule(stateView, "Download Failed");
			module.run();
		}
	}
	
}
