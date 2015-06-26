import java.util.HashSet;

public class Program {
    private static final int N = 3;

    private static int[] Permutation = new int[N];
    private static HashSet<Integer> Used = new HashSet<>();

    public static void main(String[] args) {
        generatePermutations(0);
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            System.out.printf("%d ", Permutation[i] + 1);
        }

        System.out.println();
    }

    private static void generatePermutations(int i) {
        if (i >= N) {
            print();
            return;
        }

        for (int k = 0; k < N; k++) {
            if (!Used.contains(k)) {
                Used.add(k);
                Permutation[i] = k;
                generatePermutations(i + 1);
                Used.remove(k);
            }
        }
    }
}
