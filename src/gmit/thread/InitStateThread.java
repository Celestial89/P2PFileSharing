// change state back to "Welcome to use file sharing application" after a given time
package gmit.thread;

import gmit.interfaces.Module;
import gmit.module.StateModule;
import gmit.view.StateView;

public class InitStateThread extends Thread {

	private int time = 0;
	private StateView stateView = new StateView();
	
	public InitStateThread(int time, StateView stateView) {
		this.time = time;
		this.stateView = stateView;
	}
	
	public void run() {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Module module = new StateModule(stateView, "Welcome to use file sharing application");
		module.run();
	}
}
