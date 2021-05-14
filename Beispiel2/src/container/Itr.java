package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Itr<E> implements Iterator<E> {
	private IContainerElement<E> next;

	public Itr(IContainerElement<E> firstElement) {
		this.next = firstElement;
	}
	
	public E next() {
		if (this.hasNext() == false) {
			throw new NoSuchElementException();
		}
		E v = this.next.getData();
		this.next = this.next.getNextElement();
		return v;
	}

	public boolean hasNext() {
		return (this.next != null);
	}
	
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
