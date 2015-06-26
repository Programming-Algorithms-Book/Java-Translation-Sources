public class Program {
    public static void main(String[] args) {
        final String Message = "LLLLLLALABALANICAAAABABABBABABABABAAABABALLLLAABB";

        System.out.println("Входно съобщение:");
        System.out.println(Message);

        int[] code = lpcEncode(Message);
        System.out.println("Кодирано съобщение:");

        for (int i = 0; i < code.length; i++) {
            System.out.printf("%1$4s", code[i]);
        }

        System.out.println();
        String decoded = lpcDecode(code);
        System.out.println("Декодирано съобщение:");
        System.out.println(decoded);
    }

    /* Извършва LPC кодиране на съобщението */
    private static int[] lpcEncode(String msg) {
        if (msg.equals("")) {
            /* Празно входно съобщение */
            return new int[0];
        }

        int[] code = new int[msg.length()];
        double exp = code[0] = msg.charAt(0);
        for (int i = 1; i < msg.length(); i++) {
            System.out.println(Math.ceil(exp - msg.charAt(i)));
            code[i] = (int) Math.ceil(exp - msg.charAt(i));
            exp = ((exp * i) + msg.charAt(i)) / (i + 1);
        }

        return code;
    }

    /* Извършва LPC декодиране */
    private static String lpcDecode(int[] code) {
        if (code.length == 0) {
            return "";
        }

        double exp = code[0];
        StringBuilder msg = new StringBuilder();
        msg.append((char) code[0]);
        for (int i = 1; i < code.length; i++) {
            msg.append((char) Math.ceil(exp - code[i]));
            exp = ((exp * i) + msg.charAt(i)) / (i + 1);
        }

        return msg.toString();
    }
}
