package at.fhv.ohe.task1;

import at.fhv.ohe.stack.Stack;

public class PermutationCheck {

    private PermutationCheck() {
    }

    /**
     * Checked of permutation is possible. But is your turn to do it right xD
     *
     * @param permutation a given permutation to check
     * @return true OR false
     */
    public static boolean testPermutation(int[] permutation) {
        Stack<Integer> testStack = new Stack<>(permutation.length);

        int arrPointer 	= 0;
        int input	 	= 0;
        testStack.push(input++);

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
                testStack.push(input++);
            }
        }

        return false;
    }
}
