package com.sln.linked_lists;

import java.util.HashMap;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		LinkedListNode n = createLinkedListNode();
		n.print();
		question2_1_deleteDups(n);
		question2_1_deleteDups_noBuffer(n);
		question2_2_recursive(n, 4);
		System.out.println(question2_2_iterative(n, 4));
		question2_4_partition(n, 5);
		question2_5_sum(createLinkedListNode(new int[] { 7, 1, 6 }), createLinkedListNode(new int[] { 5, 9, 2 }));
		System.out.println(question2_6_stack(createLinkedListNode(new int[] { 1, 2, 3, 3, 2, 1 })));
		System.out.println(question2_8(createLoopLinkedListNode()));
		System.out.println(question2_8(createLinkedListNode(new int[] { 5, 9, 2, 4, 5, 3, 4 })));
	}

	// Remove Dups: Write code to remove duplicates from an unsorted linked list.
	private static LinkedListNode createLinkedListNode() {
		// int[] nodelist = {1,2,3,4,4,5,6,7,8,9,10};
		int[] nodelist = { 6, 3, 5, 5, 8, 5, 10, 2, 1 };

		LinkedListNode n = new LinkedListNode(nodelist[0]);
		LinkedListNode res = n;
		for (int i = 1; i < nodelist.length; i++) {
			n.next = new LinkedListNode(nodelist[i]);
			n = n.next;
		}

		return res;
	}

	private static LinkedListNode createLinkedListNode(int[] nodelist) {
		LinkedListNode n = new LinkedListNode(nodelist[0]);
		LinkedListNode res = n;
		for (int i = 1; i < nodelist.length; i++) {
			n.next = new LinkedListNode(nodelist[i]);
			n = n.next;
		}

		return res;
	}

	private static LinkedListNode createLoopLinkedListNode() {

		LinkedListNode n1 = new LinkedListNode(0);
		LinkedListNode start = n1;
		LinkedListNode n2 = new LinkedListNode(1);
		LinkedListNode n3 = new LinkedListNode(2);
		LinkedListNode n4 = new LinkedListNode(3);
		LinkedListNode n5 = new LinkedListNode(4);
		LinkedListNode n6 = new LinkedListNode(5);
		LinkedListNode n7 = new LinkedListNode(6);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n4;

		return start;
	}

	public static void question2_1_deleteDups(LinkedListNode n) {
		HashMap<Integer, Integer> map = new HashMap<>();
		LinkedListNode prev = null;
		LinkedListNode start = n;
		while (n != null) {
			if (map.get(n.value) != null) {
				prev.next = n.next;
			} else {
				map.put(n.value, n.value);
				prev = n;
			}
			n = n.next;
		}
		start.print();
	}

	public static void question2_1_deleteDups_noBuffer(LinkedListNode n) {
		LinkedListNode prev = null;
		LinkedListNode start = n;
		LinkedListNode current = n;
		while (current != null) {
			LinkedListNode runner = current;
			while (runner != null && runner.next != null) {
				if (runner.next.value == current.value) {
					runner.next = runner.next.next;
				}
				runner = runner.next;
			}
			current = current.next;
		}
		start.print();
	}

	// Return Kth to Last: Implement an algorithm to find the kth to last element of
	// a singly linked list.
	public static int question2_2_recursive(LinkedListNode head, int k) {

		if (head == null)
			return 0;

		int index = question2_2_recursive(head.next, k) + 1;
		if (index == k)
			System.out.println(k + "t h to las t node is " + head.value);
		return index;

	}

	public static String question2_2_iterative(LinkedListNode head, int k) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		for (int i = 0; i < k; i++) {
			if (p1 == null)
				return "No element";
			p1 = p1.next;
		}

		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;

		}
		return String.valueOf(p2.value);

	}

	
	// 2.4 Partition: Write code to partition a linked list around a value x, such
	// that all nodes less than x come
	// before all nodes greater than or equal to x. Ifxis contained within the list,
	// the values of x only need
	// to be after the elements less than x (see below). The partition element x can
	// appear anywhere in the
	// "right partition"; it does not need to appear between the left and right partitions. 

	public static void question2_4_partition(LinkedListNode head, int val) {
		LinkedListNode lessthan = null;
		LinkedListNode lessthan_start = null;
		LinkedListNode greaterthan = null;
		LinkedListNode greaterthan_start = null;

		while (head != null) {
			if (val > head.value) {
				if (lessthan == null) {
					lessthan = head;
					lessthan_start = lessthan;
				} else {
					lessthan.next = head;
					lessthan = lessthan.next;
				}
			} else {
				if (greaterthan == null) {
					greaterthan = head;
					greaterthan_start = greaterthan;
				} else {
					greaterthan.next = head;
					greaterthan = greaterthan.next;
				}

			}
			head = head.next;

		}
		greaterthan.next = null;
		lessthan.next = greaterthan_start;
		lessthan_start.print();

	}

	public static void question2_5_sum(LinkedListNode head, LinkedListNode head2) {

		int first = 0;
		int second = 0;
		int mult = 1;
		while (head != null) {
			first += (head.value * mult);
			mult *= 10;
			head = head.next;
		}
		mult = 1;
		while (head2 != null) {
			second += (head2.value * mult);
			mult *= 10;
			head2 = head2.next;
		}
		System.out.println("Sum is: " + (first + second));

	}

	// 2.6 Palindrome: Implement a function to check if a linked list is a
	// palindrome.
	public static boolean question2_6_reverse(LinkedListNode head) {
		LinkedListNode reverseListNode = reverseLinkedListNode(head);
		while (head != null) {
			if (head.value != reverseListNode.value) {
				return false;
			}
			head = head.next;
			reverseListNode = reverseListNode.next;
		}
		return true;
	}

	private static LinkedListNode reverseLinkedListNode(LinkedListNode head) {
		LinkedListNode start = null;
		while (head != null) {
			LinkedListNode node = new LinkedListNode(head.value);
			node.next = start;
			start = node;
			head = head.next;
		}
		return start;
	}

	public static boolean question2_6_stack(LinkedListNode head) {
		LinkedListNode runner = head;
		LinkedListNode slow = head;
		Stack<Integer> stack = new Stack<>();
		while (runner != null && runner.next != null) {
			stack.push(slow.value);
			slow = slow.next;
			runner = runner.next.next;
		}
		// beacuse odd number of elements
		if (runner != null) {
			slow = slow.next;
		}
		while (slow != null) {
			int top = stack.pop().intValue();
			if (slow.value != top) {
				return false;
			}
			slow = slow.next;
		}
		return true;

	}

	// 2.8 Loop Detection: Given a circular linked list, implement an algorithm that
	// returns the node at the
	// beginning of the loop.

	public static String question2_8(LinkedListNode head) {
		LinkedListNode slow = head;
		LinkedListNode runner = head;
		while (slow != null && runner.next != null) {
			slow = slow.next;
			runner = runner.next.next;
			if (slow == runner) {
				break;
			}

		}
		if (runner.next == null || slow == null) {
			return "There is no loop!";
		}
		slow = head;
		while (slow != runner) {
			slow = slow.next;
			runner = runner.next;
		}
		return String.valueOf("Loop start node: " + runner.value);
	}
}
