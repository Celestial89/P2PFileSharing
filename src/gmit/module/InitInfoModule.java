package gmit.module;

import gmit.Friend;
import gmit.interfaces.Module;
import gmit.view.MainView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;

public class InitInfoModule implements Module {
	
	private Friend me = null;
	private MainView mView;
	
	public InitInfoModule(MainView mainView, Friend me) {
		this.me = me;
		this.mView = mainView;
	}

	public void run() {
		try {
			me.setIp(InetAddress.getLocalHost().getHostAddress()); // get local ip address
			File file = new File("userInfo.txt");
			BufferedReader reader;
			String name;
			if(file.exists()) {
				reader = new BufferedReader(new FileReader(file));
				name = reader.readLine();  // get name
				if(name != null){
					me.setName(name);
				}
				else
				{
					Module module = new SetNameModule(mView, me);  // set name
					module.run();
				}
			}
			else
			{
				Module module = new SetNameModule(mView, me);
				module.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
