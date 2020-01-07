package com.sln.stacks_and_queues;

import java.util.NoSuchElementException;

public class MyQueue<T> {

	private static class QueueNode<T> {
		private T data;
		private QueueNode<T> next;

		public QueueNode(T data) {
			this.data = data;
		}
	}

	private QueueNode<T> first;
	private QueueNode<T> last;

	public void add(T item) {
		QueueNode<T> newItem = new QueueNode<T>(item);
		if (last != null)
			last.next = newItem;
		last = newItem;
		if (first == null)
			first = newItem;
	}

	public T remove() {

		if (first == null)
			throw new NoSuchElementException();
		T q = first.data;
		first = first.next;

		if (first == null)
			last = null;

		return q;
	}

	public T peek() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}

	public boolean isEmpty() {

		return first == null;
	}

}
