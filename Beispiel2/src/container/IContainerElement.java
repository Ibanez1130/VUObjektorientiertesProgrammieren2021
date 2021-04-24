package container;

public interface IContainerElement<E> {
	public void setNextElement(IContainerElement<E> next);
	public boolean hasNextElement();
	public IContainerElement<E> getNextElement();
	public E getData();
}
