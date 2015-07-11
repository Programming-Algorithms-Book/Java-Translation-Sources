public class Program {
    public static void main(String[] args) {
        for (int x = 3;;) {
            for (int a = 1; a <= x; a++) {
                for (int b = 1; b <= x; b++) {
                    for (int c = 1; c <= x; c++) {
                        for (int i = 3; i <= x; i++) {
                            if (Math.pow(a, i) + Math.pow(b, i) == Math.pow(c,
                                    i)) {
                                System.out.println("Found");
                                return;
                            }
                        }
                    }
                }
            }

            x++;
        }
    }
}
