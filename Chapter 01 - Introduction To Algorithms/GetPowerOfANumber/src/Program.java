public class Program {
	private static final double NUMBER = 10.0;
	private static final int POWER = 3;

	public static void main(String[] args) {
		double result = getPower(NUMBER, POWER);
		System.out.printf("%f повдигнато на степен %d e %f", NUMBER, POWER,
				result);
	}

	private static double getPower(double number, int power) {
		double result = number;
		for (int i = 1; i < power; i++) {
			result *= number;
		}

		return result;
	}
}
