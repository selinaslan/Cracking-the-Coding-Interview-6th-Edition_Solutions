package com.sln.stacks_and_queues;

public class Main {

	public static void main(String[] args) {
		StackWithMin stack1 = new StackWithMin();
		StackWithMinStack stack2 = new StackWithMinStack();

		stack1.push(5);
		stack1.push(6);
		stack1.push(3);
		stack1.push(7);

		stack2.push(5);
		stack2.push(6);
		stack2.push(3);
		stack2.push(7);

		System.out.println(stack1.min() + "  " + stack2.min());
		stack1.pop();
		stack2.pop();
		System.out.println(stack1.min() + "  " + stack2.min());
		stack1.pop();
		stack2.pop();
		System.out.println(stack1.min() + "  " + stack2.min());
		stack1.pop();
		stack2.pop();
		System.out.println(stack1.min() + "  " + stack2.min());
		stack1.pop();
		stack2.pop();
		System.out.println(stack1.min() + "  " + stack2.min());
		stack1.pop();
		stack2.pop();

	}

}
