package aircontrol.airline;

public interface IObservable<TYPE> {
	public void registerListener(IListener<TYPE> listener);
	public void unregisterListener(IListener<TYPE> listener);
}
