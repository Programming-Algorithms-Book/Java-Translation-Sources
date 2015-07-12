import java.util.Random;

public class Program {
    private static final int NumberOfArrays = 6;
    private static final int ArraysLength = 12;

    private static Random Rand = new Random();

    public static void main(String[] args) {
        CList head = initializeArray(500);
        System.out.println("Масивите преди сортирането:");
        head = printArrays(head);
        mergeArrays(head);
    }

    private static CList initializeArray(int modul) {
        CList head = null;
        for (int i = 0; i < NumberOfArrays; i++) {
            Element[] data = new Element[ArraysLength];
            initElementsArray(data);
            CList currentList = new CList(0, data, NumberOfArrays);

            currentList.getData()[0].setKey(Rand.nextInt() % modul);
            for (int j = 1; j < ArraysLength; j++) {
                currentList.getData()[j].setKey(currentList.getData()[j - 1]
                        .getKey() + (Rand.nextInt() % modul));
            }

            currentList.setNext(head);
            head = currentList;
        }

        return head;
    }

    private static void initElementsArray(Element[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = new Element(0);
        }
    }

    private static CList printArrays(CList list) {
        while (list != null) {
            for (Element element : list.getData()) {
                System.out.printf("%d ", element.getKey());
            }

            System.out.println();
            return list.getNext();
        }

        return list;
    }

    private static void mergeArrays(CList head) {
        CList currentList = new CList();
        currentList.setNext(head);
        head = currentList;
        for (int i = 0; i < NumberOfArrays * ArraysLength; i++) {
            currentList = head;
            CList minElementList = head.getCopy();
            while (currentList.getNext() != null) {
                Element k1 = currentList.getNext().getData()[currentList
                        .getNext().getPoint()];
                Element k2 = minElementList.getNext().getData()[minElementList
                        .getNext().getPoint()];
                if (k1.getKey() < k2.getKey()) {
                    minElementList = currentList;
                }

                currentList = currentList.getNext();
            }

            System.out
                    .println(minElementList.getNext().getData()[minElementList
                            .getNext().getPoint()].getKey());
            if (minElementList.getNext().getData().length - 1 == minElementList
                    .getNext().getPoint()) {
                CList q = minElementList.getNext();
                minElementList.setNext(minElementList.getNext().getNext());
            } else {
                minElementList.getNext().setPoint(
                        minElementList.getNext().getPoint() + 1);
            }
        }
    }
}
