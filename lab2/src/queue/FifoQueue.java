package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		size = 0;
		last = null;

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param x
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E x) {
		QueueNode<E> add = new QueueNode<>(x);
		if (size == 0) {
			last = add;
			last.next = add;

		} else if (size > 0) {

			add.next = last.next;
			last.next = add;
			last = add;
		}
		size++;
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		QueueNode<E> temp = last.next;
		last.next = last.next.next;
		size--;
		return temp.element;

	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element;
	}

	public void append(FifoQueue<E> q) {
		if (last == q.last) {
			throw new IllegalArgumentException();
		}
		if (this.last == null && q.last != null) {
			last = q.last;
		} else if (q.last != null && last != null) {
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
			last = q.last;
		}
		size = size + q.size;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		int posN;

		private QueueIterator() {
			if (size > 0) {
				pos = last.next;

			} else {
				pos = null;
			}
			posN = 0;
		}

		public boolean hasNext() {
			if (posN == size) {
				return false;
			}
			return true;
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			QueueNode<E> temp = pos;
			pos = pos.next;
			posN++;

			return temp.element;
		}
	}
}
