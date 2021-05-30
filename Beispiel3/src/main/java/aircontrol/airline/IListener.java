package aircontrol.airline;

public interface IListener<TYPE> {
	void notifyChange(TYPE object);
}
