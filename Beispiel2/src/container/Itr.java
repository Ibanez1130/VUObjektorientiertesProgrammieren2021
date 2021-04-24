package container;

import java.util.Iterator;

public class Itr<E> implements Iterator<E> {
	private IContainerElement<E> next;

	public Itr(IContainerElement<E> firstElement) {
		this.next = firstElement.getNextElement();
	}
	
	public E next() {
		return this.next.getData();
	}

	public boolean hasNext() {
		return (this.next != null);
	}
	
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
