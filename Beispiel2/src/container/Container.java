package container;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
import util.searchable.ISearchFilter;
import util.searchable.ISearchableByFilter;

public class Container<E> implements Collection<E>, ISearchableByFilter<E> {
	private IContainerElement<E> firstElement;
	
	public Container() {
		this.firstElement = null;
	}
	
	public boolean add(E e) throws NullPointerException {
		if (e == null) throw new NullPointerException();
		IContainerElement<E> i = this.firstElement;
		IContainerElement<E> a = new ContainerElement<E>(e);
		if (this.contains(a)) return false;
		if (i == null) {
			this.firstElement = a;
			return true;
		}
		while(i.hasNextElement()) {
			i = i.getNextElement();
		}
		i.setNextElement(a);
		return true;
	}
	
	public boolean addAll(Collection<? extends E> c) throws NullPointerException {
		if (c == null) throw new NullPointerException();
		List<Boolean> s = c
				.stream()
				.map(e -> this.add(e))
				.collect(Collectors.toList());
		return !(s.contains(false));
	}
	
	public void clear() {
		this.firstElement = null;
	}
	
	public boolean contains(Object o) {
		if (this.firstElement == null) return false;
		IContainerElement<E> e = this.firstElement;
		if (e.getData().equals(o)) return true;
		while(e.hasNextElement()) {
			e = e.getNextElement();
			if (e.getData().equals(o)) return true;
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> c) {
		List<Boolean> s = c
				.stream()
				.map(e -> this.contains(e))
				.collect(Collectors.toList());
		return !(s.contains(false));
	}
	
	public E get(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index > (this.size() - 1))) throw new IndexOutOfBoundsException();
		int i = 0;
		IContainerElement<E> e = this.firstElement;
		while(i < index) {
			if (!e.hasNextElement()) throw new IndexOutOfBoundsException();
			i++;
			e = e.getNextElement();
		}
		return e.getData();
	}
	
	public boolean isEmpty() {
		return (this.firstElement == null);
	}
	
	public Iterator<E> iterator() {
		return new Itr<E>(this.firstElement);
	}
	
	public boolean remove(Object o) {
		if (o == null || this.firstElement == null) return false;
		if (!this.contains(o)) return false;
		IContainerElement<E> e = this.firstElement;
		if (e.getData().equals(o)) {
			this.firstElement = (e.hasNextElement()) ? e.getNextElement() : null;
			return true;
		}
		while(e.hasNextElement()) {
			IContainerElement<E> t = e.getNextElement();
			if (t.getData().equals(o)) {
				e.setNextElement(t.getNextElement());
				return true;
			}
			e = t;
		}
		return false;
	}
	
	public boolean removeAll(Collection<?> c) {
		if (c == null) return false;
		for (Object obj : c) {
			while (this.remove(obj)) {}
		}
		return true;
	}
	
	public boolean retainAll(Collection<?> c) {
		try {
			throw new UnsupportedOperationException();
		} catch (UnsupportedOperationException e) {
			System.out.println("Class: Container, Method: retainAll, Operation not supported!");
			return false;
		}
	}
	
	public int size() {
		if (this.firstElement == null) return 0;
		int i = 1;
		IContainerElement<E> e = this.firstElement;
		while(e.hasNextElement()) {
			i++;
			e = e.getNextElement();
		}
		return i;
	}
	
	public Object[] toArray() {
		try {
			throw new UnsupportedOperationException();
		} catch (UnsupportedOperationException e) {
			System.out.println("Class: Container, Method: toArray, Operation not supported!");
			return null;
		}
	}
	
	public <T> T[] toArray(T[] a) {
		try {
			throw new UnsupportedOperationException();
		} catch (UnsupportedOperationException e) {
			System.out.println("Class: Container, Method: toArray, Operation not supported!");
			return null;
		}
	}
	
	public String toString() {
		IContainerElement<E> e = this.firstElement;
		if (e == null) return "Container [ ]";
		String s = e.toString();
		while(e.hasNextElement()) {
			e = e.getNextElement();
			s += ", " + e.toString();
		}
		return "Container [ " + s + " ]";
	}
	
	public Collection<E> searchByFilter(ISearchFilter filter, Object filterObject) {
		Collection<E> c = new Container<E>();
		if (filter == null) return c;
		if (this.firstElement == null) return c;
		IContainerElement<E> e = this.firstElement;
		if (filter.searchFilterFunction(e.getData(), filterObject)) {
			try {
				c.add(e.getData());
			} catch (NullPointerException ex) {
				System.out.println("A NullPointerException has been thrown while adding an element to the Container.");
			}
		}
		while(e.hasNextElement()) {
			e = e.getNextElement();
			if (filter.searchFilterFunction(e.getData(), filterObject)) {
				try {
					c.add(e.getData());
				} catch (NullPointerException ex) {
					System.out.println("A NullPointerException has been thrown while adding an element to the Container.");
				}
			}
		}
		return c;
	}
}
