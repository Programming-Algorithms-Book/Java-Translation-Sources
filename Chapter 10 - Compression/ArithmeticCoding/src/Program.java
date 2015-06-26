import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    private static final boolean ShowMore = true;

    public static void main(String[] args) {
        final String Message = "АРИТМЕТИКА";

        System.out.printf("Изходно съобщение: %s\n", Message);
        HashMap<Character, Integer> freqs = getStatistics(Message);
        HashMap<Character, Symbol> symbols = buildModel(Message, freqs);

        if (ShowMore) {
            printModel(symbols);
        }

        double code = arithmeticEncode(Message, symbols);
        System.out.printf("Кодът на съобщението е: %.8f\n", code);
        System.out.println("Декодиране:");
        String decoded = arithmeticDecode(code, Message.length(), symbols);
    }

    /* Намира броя на срещанията на всеки символ */
    private static HashMap<Character, Integer> getStatistics(String msg) {
        HashMap<Character, Integer> result = new HashMap<>();

        for (char character : msg.toCharArray()) {
            if (result.containsKey(character)) {
                result.replace(character, result.get(character) + 1);
            } else {
                result.put(character, 1);
            }
        }

        return result;
    }

    /* Построява модела */
    private static HashMap<Character, Symbol> buildModel(
            String msg,
            HashMap<Character, Integer> freqs)
    {
        double lastHigh = 0.0;
        HashMap<Character, Symbol> symbols = new HashMap<Character, Symbol>();

        List<Character> sortedKeys = new ArrayList<Character>(freqs.keySet());
        Collections.sort(sortedKeys);

        for (char ch : sortedKeys) {
            symbols.put(ch, new Symbol(lastHigh, lastHigh
                    + (freqs.get(ch) / (double) msg.length()), ch));
            lastHigh = symbols.get(ch).getHigh();
        }

        return symbols;
    }

    private static void printModel(HashMap<Character, Symbol> symbols) {
        System.out.println("             ГРАНИЦА");
        System.out.println("СИМВОЛ    ДОЛНА   ГОРНА");

        List<Character> entries = new ArrayList<Character>(symbols.keySet());
        Collections.sort(entries, new Comparator<Character>() {
            public int compare(Character entry1, Character entry2) {
                return Double.compare(symbols.get(entry1).getLow(),
                        (symbols.get(entry2).getLow()));
            }
        });

        for (char ch : entries) {
            System.out.printf("   %s     %.4f  %.4f\n", ch, symbols.get(ch)
                    .getLow(), symbols.get(ch).getHigh());
        }
    }

    /* Извършва аритметично кодиране */
    private static double arithmeticEncode(String msg,
            HashMap<Character, Symbol> symbols) {
        double low = 0.0, high = 1.0;
        for (char ch : msg.toCharArray()) {
            double range = high - low;
            high = low + (range * symbols.get(ch).getHigh());
            low = low + (range * symbols.get(ch).getLow());
            if (ShowMore) {
                System.out.printf("%s    %.9f  %.9f\n", ch, low, high);
            }
        }

        return low;
    }

    private static Symbol getSymbol(double enc,
            HashMap<Character, Symbol> symbols) {
        for (Map.Entry<Character, Symbol> element : symbols.entrySet()) {

            Symbol currentSymbol = element.getValue();
            if (currentSymbol.getLow() <= enc && currentSymbol.getHigh() > enc) {
                return currentSymbol;
            }
        }

        return null;
    }

    /* Извършва декодиране */
    private static String arithmeticDecode(double enc, int length,
            HashMap<Character, Symbol> symbols) {
        double range;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            Symbol symbol = getSymbol(enc, symbols);
            if (ShowMore) {
                System.out.printf("%s    %.9f\n", symbol.getCharacter(), enc);
            }

            sb.append(symbol.getCharacter());
            range = symbol.getHigh() - symbol.getLow();
            enc -= symbol.getLow();
            enc /= range;
        }

        return sb.toString();
    }
}
