// send file to whom that want to download
package gmit.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FilesSendThread extends Thread {

	gmit.File iFile = null;
	File oFile = null;
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(6870);
			while(true) {
				Socket socket = serverSocket.accept();
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				iFile = (gmit.File) inputStream.readObject();  // get file that want to download
				oFile = new File(iFile.getPath());
	            DataInputStream diStream = new DataInputStream(new BufferedInputStream(new FileInputStream(iFile.getPath())));
	            DataOutputStream doStream = new DataOutputStream(socket.getOutputStream());
	            doStream.writeUTF(oFile.getName());  // send file name
                doStream.flush();
                doStream.writeLong((long) oFile.length());  // send file size
                doStream.flush();
                int bufferSize = 8192;  // size of buffer
                byte[] buf = new byte[bufferSize];  // buffer
                while (true) {
                    int read = 0;
                    if (diStream != null) {
                        read = diStream.read(buf);  // read file from local path
                    }
                    if (read == -1) {
                      break;
                    }
                    doStream.write(buf, 0, read);  // send file to friend
                }
                doStream.flush();                
                diStream.close();
                doStream.close();
                socket.close();                
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
