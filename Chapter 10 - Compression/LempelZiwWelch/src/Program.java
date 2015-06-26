import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final int CharacterCount = 256;
    private static final int DictionarySize = 4096;
    private static final int NotFoun = -1;

    public static void main(String[] args) {
        final String Message = "abracadabragabramabracadabragabraLALALALALALALALALALALALALALALA";

        System.out.println("Входно съобщение за кодиране:");
        System.out.println(Message);
        System.out.printf("Дължина: %d\n", Message.length());

        List<Integer> encoded = lzwEncode(Message);
        printEncodedMsg(encoded);
        System.out.printf("Дължина: %d\n", encoded.size());

        String decoded = lzwDecode(encoded);
        System.out.println("Декодирано съобщение:");
        System.out.println(decoded);
    }

    /* Инициализира таблицата */
    private static List<String> initTable() {
        List<String> dict = new ArrayList<String>(DictionarySize);
        for (int i = 0; i < CharacterCount; i++) {
            dict.add(Character.toString(((char) i)));
        }

        return dict;
    }

    /* Търси индекс в таблицата */
    private static int findIndex(List<String> dict, String s) {
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).equals(s)) {
                return i;
            }
        }

        return NotFoun;
    }

    /* Извършва кодиране по LZW */
    private static List<Integer> lzwEncode(String msg) {
        List<String> dict = initTable();
        List<Integer> encoded = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append(msg.charAt(0));
        int lastIndex = findIndex(dict, sb.toString());
        for (char ch : msg.substring(1).toCharArray()) {
            sb.append(ch);
            String newStr = sb.toString();
            int dictIndex = findIndex(dict, newStr);
            if (dictIndex == -1) {
                encoded.add(lastIndex);
                dict.add(newStr);
                sb.setLength(0);
                sb.append(ch);
                lastIndex = findIndex(dict, sb.toString());
            } else {
                lastIndex = dictIndex;
            }
        }

        encoded.add(findIndex(dict, sb.toString()));
        return encoded;
    }

    /* Извършва декодиране по LZW */
    private static String lzwDecode(List<Integer> encoded) {
        List<String> dict = initTable();
        int ind = 0;
        int oldCode = encoded.get(ind++);

        StringBuilder sb = new StringBuilder();
        sb.append(dict.get(oldCode));
        while (ind < encoded.size()) {
            int code = encoded.get(ind++);
            String str;
            if (code >= dict.size()) {
                str = dict.get(oldCode);
                str += str.charAt(0);
            } else {
                str = dict.get(code);
            }

            sb.append(str);
            String str2 = dict.get(oldCode);
            str2 += str.charAt(0);
            dict.add(str2);
            oldCode = code;
        }

        return sb.toString();
    }

    private static void printEncodedMsg(List<Integer> encoded) {
        System.out.println("Кодирано съобщение:");
        for (int code : encoded) {
            System.out.printf("%d ", code);
        }

        System.out.println();
    }
}
