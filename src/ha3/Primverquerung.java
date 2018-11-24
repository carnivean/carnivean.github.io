
public class Primverquerung {
	public static int querPrim(int n) {
		int summe = 0;
		for (int p = 2; p < n; p++) {
			if (isPrime(p) && isQSgerade(p)) {
				summe += p;
			}
		}
		return summe;
	}

	private static boolean isQSgerade(int p) {
		int qs = 0;
		while (p > 9) {
			qs = qs + (p % 10);
			p = p / 10;
		}
		qs += p;
		
		if (qs % 2 == 0)
			return true;
		else
			return false;
	}

	private static boolean isPrime(int p) {
		for (int i = 2; i < p; i++) {
			if (p % i == 0) {
				return false;
			}
		}
		return true;
	}
}
