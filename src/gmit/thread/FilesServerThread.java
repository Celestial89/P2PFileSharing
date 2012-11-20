// send self's file list to friend
package gmit.thread;

import gmit.model.FilesModel;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FilesServerThread extends Thread {

	private FilesModel mFModel = null;
	
	public FilesServerThread(FilesModel mFModel) {
		super();
		this.mFModel = mFModel;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(6869);
			while(true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(mFModel.getFiles());  // send self's file list to friend
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
