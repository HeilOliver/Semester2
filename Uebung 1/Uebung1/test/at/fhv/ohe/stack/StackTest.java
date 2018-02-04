package at.fhv.ohe.stack;

import javafx.scene.control.SpinnerValueFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void isEmpty() {
        Stack<Integer> stack = new Stack<>(10);

        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isFull() {
        Stack<Integer> stack = new Stack<>(2);

        assertFalse(stack.isFull());
        stack.push(1);
        assertFalse(stack.isFull());
        stack.push(2);
        assertTrue(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

    @Test
    void push() {
        Stack<String> stack = new Stack<>(2);
        String expectedString0 = "Der erste String";
        String expectedString1 = "Der zweite String";
        String expectedString3 = "Der dritte String";

        assertTrue(stack.push(expectedString0));
        assertTrue(stack.push(expectedString1));
        assertFalse(stack.push(expectedString3));
    }

    @Test
    void pop() {
        Stack<String> stack = new Stack<>(2);
        String expectedString0 = "Der erste String";
        String expectedString1 = "Der zweite String";
        String expectedString2 = "Der dritte String";

        String exceptionMessage = "Stack is Empty";

        assertTrue(stack.push(expectedString0));
        assertTrue(stack.push(expectedString1));
        assertFalse(stack.push(expectedString2));

        assertEquals(expectedString1,stack.pop());
        assertEquals(expectedString0,stack.pop());

        try {
            stack.pop();
            fail("No exception is been thrown");
        } catch (EmptyStackException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception ignore) {
            fail("Wrong exception is been thrown");
        }
    }

    @Test
    void top() {
        Stack<String> stack = new Stack<>(2);
        String expectedString0 = "Der erste String";
        String expectedString1 = "Der zweite String";

        String exceptionMessage = "Stack is Empty";

        assertTrue(stack.push(expectedString0));
        assertTrue(stack.push(expectedString1));

        assertEquals(expectedString1,stack.top());
        assertEquals(expectedString1,stack.top());
        stack.pop();
        stack.pop();

        try {
            stack.top();
            fail("No exception is been thrown");
        } catch (EmptyStackException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception ignore) {
            fail("Wrong exception is been thrown");
        }
    }
}