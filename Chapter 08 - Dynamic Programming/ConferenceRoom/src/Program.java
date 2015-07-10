import java.util.Arrays;
import java.util.Comparator;

public class Program {
    ////private static final int MAX_QUERIES = 5000; /* Максимален брой заявки */
    private static final int MAX_DAYS = 365; /* Максимален брой дни */

    private static BlueRed[] B = new BlueRed[MAX_DAYS + 1];
    private static BlueRed[] R = new BlueRed[MAX_DAYS + 1];

    private static BeginEnd[] BlueOrders = { new BeginEnd(1, 2),
            new BeginEnd(12, 20) };

    private static BeginEnd[] RedOrders = { new BeginEnd(2, 10),
            new BeginEnd(6, 11), new BeginEnd(15, 25), new BeginEnd(26, 30) };

    private static int BlueOrdersCount = BlueOrders.length; /* Брой сини заявки */
    private static int RedOrdersCount = RedOrders.length; /* Брой червени заявки */

    public static void main(String[] args) {
        Arrays.sort(BlueOrders, new Comparator<BeginEnd>() {
            @Override
            public int compare(BeginEnd rb1, BeginEnd rb2) {
                return Integer.compare(rb1.getEnd(), rb2.getBegin());
            }
        });

        Arrays.sort(RedOrders, new Comparator<BeginEnd>() {
            @Override
            public int compare(BeginEnd rb1, BeginEnd rb2) {
                return Integer.compare(rb1.getEnd(), rb2.getBegin());
            }
        });

        initBlueRedArray(B);
        initBlueRedArray(R);
        solveDynamic();
        printResult();
    }

    /* Решава задачата с динамично оптимиране */
    private static void solveDynamic() {
        int d, bb, be, blueIndex, redIndex;
        /* Инициализация */
        B[0].setBlueCount(0);
        B[0].setRedCount(0);
        R[0].setBlueCount(0);
        R[0].setRedCount(0);

        blueIndex = redIndex = 1;
        /* Пресмятане на B[1..MAXD], R[1..MAXD] */
        for (d = 1; d <= MAX_DAYS; d++) {
            /* Пресмятане на B[d] */
            B[d] = B[d - 1];
            for (blueIndex = 0; blueIndex < BlueOrdersCount; blueIndex++) {
                if (BlueOrders[blueIndex].getEnd() > d) {
                    break;
                } else {
                    bb = BlueOrders[blueIndex].getBegin();
                    be = BlueOrders[blueIndex].getEnd();
                    if (R[bb - 1].getBlueCount() + R[bb - 1].getRedCount()
                            + (be - bb + 1) > B[d].getBlueCount()
                            + B[d].getRedCount()) {
                        B[d].setBlueCount(R[bb - 1].getBlueCount()
                                + (be - bb + 1));
                        B[d].setRedCount(R[bb - 1].getRedCount() + 0);
                    }
                }
            }

            /* Пресмятане на R[d]: аналогично на B[d] */
            R[d] = R[d - 1];
            for (redIndex = 0; redIndex < RedOrdersCount; redIndex++) {
                if (RedOrders[redIndex].getEnd() > d) {
                    break;
                } else {
                    bb = RedOrders[redIndex].getBegin();
                    be = RedOrders[redIndex].getEnd();
                    if (B[bb - 1].getBlueCount() + B[bb - 1].getRedCount()
                            + (be - bb + 1) > R[d].getBlueCount()
                            + R[d].getRedCount()) {
                        R[d].setBlueCount(B[bb - 1].getBlueCount());
                        R[d].setRedCount(B[bb - 1].getRedCount()
                                + (be - bb + 1));
                    }
                }
            }
        }
    }

    /* Извежда резултата на екрана */
    private static void printResult() {
        if (B[MAX_DAYS].getBlueCount() + B[MAX_DAYS].getRedCount() > R[MAX_DAYS]
                .getBlueCount() + R[MAX_DAYS].getRedCount()) {
            System.out.printf("Заетост на залата (дни): %d\n",
                    B[MAX_DAYS].getBlueCount() + B[MAX_DAYS].getRedCount());
            System.out.printf("Брой дни за червените: %d\n",
                    B[MAX_DAYS].getRedCount());
            System.out.printf("Брой дни за сините: %d\n",
                    B[MAX_DAYS].getBlueCount());
        } else {
            System.out.printf("Заетост на залата (дни): %d\n",
                    R[MAX_DAYS].getBlueCount() + R[MAX_DAYS].getRedCount());
            System.out.printf("Брой дни за червените: %d\n",
                    R[MAX_DAYS].getRedCount());
            System.out.printf("Брой дни за сините: %d\n",
                    R[MAX_DAYS].getBlueCount());
        }
    }
    
    private static void initBlueRedArray(BlueRed[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = new BlueRed(0, 0);
        }
    }
}
