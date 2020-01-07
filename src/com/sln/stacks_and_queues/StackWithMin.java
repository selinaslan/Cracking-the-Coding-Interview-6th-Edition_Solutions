package com.sln.stacks_and_queues;

import java.util.Stack;

public class StackWithMin extends Stack<NodeWithMin> {

	
	public void push(int value) {
		int minVal = Math.min(value,min());
		super.push(new NodeWithMin(value,minVal));
	}
		
	public int min() {
		if(this.isEmpty()) {
			return Integer.MAX_VALUE; //error
		}else return peek().min;
	}

}

class NodeWithMin {
	public int value;
	public int min;

	public NodeWithMin(int v, int m) {
		value = v;
		min = m;
	}

}
