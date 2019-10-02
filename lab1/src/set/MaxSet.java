package set;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;

	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}

	/**
	 * Returns the currently largest element in this set. pre: the set is not
	 * empty post: the set is unchanged
	 * 
	 * @return the currently largest element in this set
	 * @throws NoSuchElementException
	 *             if this set is empty
	 */
	public E getMax() {
		// Iterator<E> itr = iterator();
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		// maxElement = itr.next();
		// while (itr.hasNext()) {
		// E temp = itr.next();
		// if (maxElement.compareTo(temp) < 0) {
		// maxElement = temp;
		// }
		// }

		return maxElement;

	}

	/**
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * 
	 * @param x
	 *            the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		// Iterator<E> itr = iterator();
		if (isEmpty()) {
			maxElement = x;
			return super.add(x);
		}
		boolean added = super.add(x);

		if (maxElement.compareTo(x) < 0) {
			maxElement = x;
		} // else {
			// maxElement = itr.next();
			// }
		/*
		 * Iterator<E> itr = super.iterator(); if (isEmpty()) { maxElement = x;
		 * } while (itr.hasNext()) { E a = itr.next(); if
		 * (a.compareTo(maxElement) > 0) { maxElement = a; } }
		 */
		return added;
	}

	/**
	 * Removes the specified element from this set if it is present. post: x is
	 * removed if it was present
	 * 
	 * @param x
	 *            the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		boolean removed = super.remove(x);
		Iterator<E> itr = iterator();
		if (maxElement.equals(x)) {
			if (!isEmpty()) {
				maxElement = itr.next();
				while (itr.hasNext()) {
					E temp = itr.next();
					if (maxElement.compareTo(temp) < 0) {
						maxElement = temp;
					}
				}
			}
		}
		/*
		 * Iterator<E> itr = super.iterator(); boolean removed =
		 * super.remove(x); if (maxElement.compareTo((E) x) == 0) { maxElement =
		 * itr.next(); } while (itr.hasNext()) { E a = itr.next(); if
		 * (a.compareTo(maxElement) > 0) { maxElement = a; } }
		 */
		return removed;
	}

	/**
	 * Adds all of the elements in the specified set, for which it is possible,
	 * to this set. post: all elements, for which it is possible, in the
	 * specified set are added to this set.
	 * 
	 * @return true if this set changed as a result of the call
	 */
/*	public boolean addAll(SimpleSet<? extends E> c) {
		boolean addedAll = super.addAll(c);
		Iterator<E> itr = iterator();
		while (itr.hasNext()) {
			E temp = itr.next();
			if (temp.compareTo(maxElement) > 0) {
				maxElement = temp;
			}
		}

		return addedAll;

	}
*/
}