package com.sln.linked_lists;

public class LinkedListNode {

	public int value;

	public LinkedListNode next;

	public LinkedListNode(int value) {
		this.value = value;
	}

	public void print() {

		LinkedListNode temp = this;
		System.out.print(" \n"+temp.value);
		while (temp.next != null) {
			temp = temp.next;
			System.out.print(" -> " + temp.value);
		}
	}

}
