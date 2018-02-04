package at.fhv.ohe.task1;

public class RunMe {

    public static void main(String[] args) {
        int[] permutation = {1, 3, 5, 4, 2};
        boolean result = PermutationCheck.testPermutation(permutation);


        System.out.println("Permutation Test");
        System.out.print("Sequence: \t");
        StringBuilder builder = new StringBuilder();
        for (int i : permutation) {
            builder.append(", ");
            builder.append(i);
        }

        System.out.print(builder.substring(2));
        System.out.print("\nResult: \t");
        System.out.print(result);

    }

}
