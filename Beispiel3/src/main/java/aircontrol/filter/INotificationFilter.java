package aircontrol.filter;

public interface INotificationFilter<TYPE> {
	public boolean check(TYPE compareObject);
}
