// class who implements this interface is a model
package gmit.interfaces;

public interface Model {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
}
