package com.sln.stacks_and_queues;

import java.util.EmptyStackException;

public class MyStack<T> {

	private static class StackNode<T> {
		private T data;
		private StackNode<T> next;

		public StackNode(T data) {
			this.data = data;
		}
	}

	private StackNode<T> top;

	public T pop() throws Exception {
		if (top == null)
			throw new EmptyStackException();
		T item = top.data;
		top = top.next;
		return item;
	}

	public void push(T item) {
		StackNode<T> newItem = new StackNode<>(item);
		newItem.next = top;
		top = newItem;
	}

	public T peek() throws Exception {
		if (top == null)
			throw new EmptyStackException();
		return top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}
}
