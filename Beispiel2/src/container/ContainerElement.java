package container;

public class ContainerElement<E> implements IContainerElement<E> {
	private IContainerElement<E> nextElement;
	private E data;
	
	public ContainerElement(E data) {
		this.data = data;
	}
	
	public ContainerElement(E data, IContainerElement<E> next) {
		this.data = data;
		this.nextElement = next;
	}

	public E getData() {
		return this.data;
	}
	
	public void setNextElement(IContainerElement<E> next) {
		this.nextElement = next;
	}
	
	public boolean hasNextElement() {
		return (this.nextElement != null);
	}
	
	public IContainerElement<E> getNextElement() {
		return this.nextElement;
	}
 }
