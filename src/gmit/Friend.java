package gmit;

import java.io.Serializable;

public class Friend implements Serializable {

	private static final long serialVersionUID = 154218972249165610L;
	private String name;  // friend's name
	private String ip;  // friend's ip
	
	// get and set methods
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIp() {
		return this.ip;
	}
}
