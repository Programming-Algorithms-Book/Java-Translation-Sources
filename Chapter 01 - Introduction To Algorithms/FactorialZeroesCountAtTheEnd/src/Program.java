public class Program {
    private static final int N = 10;

    public static void main(String[] args) {
        int zeroesCount = 0;
        int p = 5;
        while (N >= p) {
            zeroesCount += N / p;
            p *= 5;
        }

        System.out.printf("Броят на нулите в края на %d! е %d\n", N,
                zeroesCount);
    }
}
