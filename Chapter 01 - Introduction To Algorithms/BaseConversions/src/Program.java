public class Program {
	private static final double EPS = 0.0001;

	public static void main(String[] args) {
		System.out
				.println("!!! Демонстрация на преобразуването между бройни системи !!!");
		System.out.printf("Седмичният запис на 777.777 (10) е %s",
				toStringReal(777.777, 7, 10));
		System.out.printf("Десетичният запис на 11.D873 (16) е: %s",
				toStringReal(toValueReal("11.D873", 16), 10, 10));
	}

	/* Връща символа, съответстващ на n */
	private static char getChar(int n) {
		return (char) ((n < 10) ? n + '0' : n + 'A' - 10);
	}

	/* Връща стойността на символа c */
	private static int getValue(char c) {
		return (c >= '0' && c <= '9') ? c - '0' : c - 'A' + 10;
	}

	private static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	/* Преобразува десетичното реално число n в бройна система с основа base */
	private static String toStringReal(double n, int toBase, int cnt) {
		StringBuilder sb = new StringBuilder();

		/* Намиране на знака */
		if (n < 0) {
			sb.append('-');
			n *= -1;
		}

		double fraction = n % 1;
		int integer = (int) n;

		sb.append(toStringIntegral(integer, toBase));

		/* Поставяне на десетична точка */
		sb.append('.');

		sb.append(toStringFraction(fraction, toBase, cnt));

		return sb.toString();
	}

	/*
	 * Преобразува цялото десетично число n (n >= 0) в бройна система с основа
	 * base
	 */
	private static String toStringIntegral(int n, int toBase) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			sb.append(getChar(n % toBase));
			n /= toBase;
		}

		return reverse(sb.toString());
	}

	/*
	 * Преобразува десетичното число 0 <= n < 1 в бройна система с основа base с
	 * не повече от cnt на брой цифри след десетичната запетая
	 */
	private static String toStringFraction(double n, int toBase, int cnt) {
		StringBuilder sb = new StringBuilder();
		while (cnt > 0) {
			cnt -= 1;
			/* Дали не сме получили 0? */
			if (Math.abs(n) < EPS) {
				break;
			}

			/* Получаване на следващата цифра */
			n *= toBase;
			sb.append(getChar((int) n));
			n -= Math.floor(n);
		}

		return sb.toString();
	}

	/*
	 * Намира стойността на реалното число numb, зададено в бройна система с
	 * основа base
	 */
	private static double toValueReal(String numb, int fromBase) {
		int minus;
		/* Проверка за минус */
		if ('-' == numb.charAt(0)) {
			minus = -1;
			numb = numb.substring(1);
		} else {
			minus = 1;
		}

		int dotIndex = numb.indexOf(".");
		if (dotIndex == -1) {
			return toValueIntegral(numb, fromBase); /* Няма дробна част */
		}

		/* Пресмятане на цялата част */
		double result = toValueIntegral(numb.substring(0, dotIndex), fromBase);

		/* Прибавяне на дробната част */
		result += toValueFraction(numb.substring(dotIndex + 1), fromBase);

		return minus * result;
	}

	/*
	 * Намира стойността на числото numb, зададено в бройна система с основа
	 * base, numb >= 0
	 */
	private static long toValueIntegral(String numb, int fromBase) {
		long result = 0;
		for (char ch : numb.toCharArray()) {
			result = (result * fromBase) + getValue(ch);
		}

		return result;
	}

	/*
	 * Намира стойността на числото numb (0 < numb < 1), зададено в бройна
	 * система с основа base
	 */
	private static double toValueFraction(String numb, int fromBase) {
		double result = 0.0;
		for (char ch : reverse(numb).toCharArray()) {
			result = (result + getValue(ch)) / fromBase;
		}

		return result;
	}
}