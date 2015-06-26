public class Program {
    private static String[] Roman1To9 = { "", "A", "AA", "AAA", "AB", "B",
            "BA", "BAA", "BAAA", "AC" };
    private static String[] RomanDigits = { "IVX", "XLC", "CDM", "M" };

    public static void main(String[] args) {
        System.out.printf(
                "Числото MCMLXXXIX в десетична бройна система е %d\n",
                fromRoman("MCMLXXXIX"));
        System.out.printf("Числото 1989 в римски цифри е %s\n", toRoman(1989));
    }

    private static String getRomanDigit(char x, int power) {
        String result = "";
        for (char ab : Roman1To9[x].toCharArray()) {
            result += RomanDigits[power].charAt(ab - 'A');
            ;
        }

        return result;
    }

    private static int fromRoman(String roman) {
        int old = 1000;
        int result = 0;
        for (char ch : roman.toCharArray()) {
            int value = 0;
            switch (ch) {
                case 'I':
                    value = 1;
                    break;
                case 'V':
                    value = 5;
                    break;
                case 'X':
                    value = 10;
                    break;
                case 'L':
                    value = 50;
                    break;
                case 'C':
                    value = 100;
                    break;
                case 'D':
                    value = 500;
                    break;
                case 'M':
                    value = 1000;
                    break;
            }

            result += value;

            if (value > old) {
                result -= 2 * old;
            }

            old = value;
        }

        return result;
    }

    private static String toRoman(int x) {
        String result = "";
        int power = 0;
        while (x > 0) {
            String digit = getRomanDigit((char) (x % 10), power);
            result = digit + result;
            power += 1;
            x /= 10;
        }

        return result;
    }
}
