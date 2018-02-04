package at.fhv.ohe.uebung2_b;

import java.io.IOException;

/**
 * The Stack class represents a last-in-first-out stack of integers.
 * 
 * @author      Oliver Heil - fhv.at
 * @version     1.1
 * @since   	2017-03-20
 */
public class Stack {
	private int[] 	_stack;
	private int		_stackPointer;
	
	
	/**
	 * Creates a Stack Object
	 * 
	 * @param size Size of the Stack. Must be greater than 0
	 */
	public Stack (int size) {
		if (size > 0) {
			_stack = new int[size];
			_stackPointer = -1;
		} 
	}
	
	/**
	 * Check of Stack is Empty
	 * 
	 * @return true = is Empty; false = is not Empty
	 */
	public boolean isEmpty() {
		if (_stackPointer <= -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check of Stack is full
	 * 
	 * @return true = is full; false = is not full
	 */
	public boolean isFull() {
		if (_stackPointer < _stack.length) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Add a new item on Stack.
	 * 
	 * @param newElement The new item 
	 * @return true = done; false = Stack is Full
	 * @throws IOException 
	 */
	public void push(int newElement) throws IOException  {
		if (isFull()) {
			throw new IOException("Stack is full"); 
		}
		++_stackPointer;
		_stack[_stackPointer] = newElement;
	}
	
	/**
	 * Read the top item from stack and
	 * delete it.
	 * 
	 * @return Value from Top of Stack or 0 if Stack is empty
	 * @throws IOException 
	 */
	public int pop() throws IOException {
		int item = top();
		--_stackPointer;
		return item;
	}
	
	/**
	 * Read the top item from stack and don't delete it.
	 * 
	 * @return Value from Top of Stack or 0 if Stack is empty
	 * @throws IOException 
	 */
	public int top() throws IOException{
		if (isEmpty()) {
			throw new IOException("No item on Stack");
		}  
		return _stack[_stackPointer];
	}
	
	/**
	 * Checked of permutation is possible
	 * 
	 * @param permutation a given permutation to check
	 * @return true OR false
	 */
	public static boolean testPermutation(int[] permutation) {
		Stack testStack = new Stack(permutation.length);
		int arrPointer 	= 0;
		int input	 	= 0;
		try {
			while (input <= permutation.length) {
				if (testStack.top() == permutation[arrPointer]) {
					testStack.pop();
					arrPointer++;
					if (arrPointer >= permutation.length) {
						return true;
					}
				} else {
					if (input > permutation.length) {
						return false;
					}
					input++;
					testStack.push(input);
					
				}
			}
		return false;
		
		} catch (Exception e) {
			return false;
		}
	}	
}
