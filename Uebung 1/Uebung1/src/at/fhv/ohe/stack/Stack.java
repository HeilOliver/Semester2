package at.fhv.ohe.stack;

/**
 * An ordinary stack with basic methods.
 *
 * @param <T> The element Type of this Stack
 */
public class Stack<T> {
    private T[] stack;
    private int stackPointer;


    /**
     * Creates an Stack with the given Type and the max possible Size
     *
     * @param size Size of the Stack. Must be greater than 0
     */
    @SuppressWarnings("unchecked")
    public Stack (int size) {
        if (size <= 0 ) throw new IllegalArgumentException("Stack size must be greater than zero");

        stack = (T[])new Object[size];
        stackPointer = -1;
    }

    /**
     * Check of Stack is Empty
     *
     * @return true = is Empty; false = is not Empty
     */
    public boolean isEmpty() {
        return stackPointer == -1;
    }

    /**
     * Check of Stack is full
     *
     * @return true = is full; false = is not full
     */
    public boolean isFull() {
        return stackPointer >= stack.length - 1;
    }

    /**
     * Add a new item on Stack.
     *
     * @param newElement The new top element on the stack
     * @return true = done; false = Stack is Full
     */
    public boolean push(T newElement) {
        if (isFull()) return false;

        ++stackPointer;
        stack[stackPointer] = newElement;
        return true;
    }

    /**
     * Reads the top element from the stack and
     * delete it.
     *
     * @return Value from Top of Stack or 0 if Stack is empty
     * @throws at.fhv.ohe.stack.EmptyStackException Throws an {@code EmptyStackException} when the stack is empty
     */
    public T pop() {
        T item = top();
        --stackPointer;
        return item;
    }

    /**
     * Read the top element from the stack.
     *
     * @return Value from Top of Stack
     * @throws at.fhv.ohe.stack.EmptyStackException Throws an {@code EmptyStackException} when the stack is empty
     */
    public T top() {
        if (isEmpty()) throw new at.fhv.ohe.stack.EmptyStackException("Stack is Empty");
        return stack[stackPointer];
    }
}
