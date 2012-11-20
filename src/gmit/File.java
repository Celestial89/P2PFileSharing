package gmit;

import java.io.Serializable;

public class File implements Serializable{

	private static final long serialVersionUID = 7481927628445985643L;
	private int ID;  // file id
	private String name;  // file name
	private String path;  // file path
	
	// get and set methods
	public void setID(int id) {
		this.ID = id;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}

}
