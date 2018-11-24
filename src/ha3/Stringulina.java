
public class Stringulina {
	public static int substringPos(String haystack, String needle) {
		for (int i = 0; i < haystack.length(); i++) {
			boolean falscherBuchstabe = false;
			for (int indexInner = 0; indexInner < needle.length(); indexInner++) {
				if ((indexInner + i) >= haystack.length() ||
						needle.charAt(indexInner) != haystack.charAt(indexInner + i))
						{
					falscherBuchstabe = true;
					break;
				}
			}
			if (!falscherBuchstabe)
				return i;
		}
		
		return -1;
	}
	
	public static int countSubstring(String haystack, String needle) {
		int counter = 0;
		
		for (int i = 0; i < haystack.length(); i++) {
			boolean falscherBuchstabe = false;
			for (int indexInner = 0; indexInner < needle.length(); indexInner++) {
				if ((indexInner + i) >= haystack.length() ||
						needle.charAt(indexInner) != haystack.charAt(indexInner + i))
						{
					falscherBuchstabe = true;
					break;
				}
			}
			if (!falscherBuchstabe)
				counter++;
		}
		
		return counter;
	}
	
	public static boolean correctlyBracketed(String str) {
		int counter = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				counter++;
			} else if (str.charAt(i) == ')') {
				counter--;
				if (counter < 0) {
					return false;
				}
			}
		}
		if (counter == 0)
			return true;
		return false;
	}
	
	private static String strAusPattern(String pattern) {
		String wort = "";
		char lastChar = ' ';
		
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) != '{') {
				wort += pattern.charAt(i); // gleichbbedeutend: wort = wort + pattern.charAt(i);
				lastChar = pattern.charAt(i);
			} else {
				// geschweifte Klammer --> Wdh
				String anzahlStr = "";
				i++;
				// Schleife kopiert Inhalt der geschweiften Klammer
				while (pattern.charAt(i) != '}') {
					anzahlStr += pattern.charAt(i);
					i++;
				}
				int anz = Integer.parseInt(anzahlStr);
				
				for (int index = 0; index < anz - 1; index++) {
					wort += lastChar;
				}
			}
			
			
		}
		
		return wort;
	}
	
	public static boolean matches(String str, String pattern) {
		pattern = strAusPattern(pattern);
		
		if (str.length() != pattern.length()) {
			return false;
		} else {
		
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '.') {
					return false;
				}
			}
			
			return true; 
		}
	}
	
	public static void main(String[] args) {
		System.out.println("wortausPattern: " + Stringulina.strAusPattern("P.{2}ngui{1}."));
		
		/*
		String s = "abcdef";
		String s2 = "abcdef";
		String s3 = "ab..ef";
		String s4 = ".bc.ef";
		String s5 = "abddef";
		String s6 = ".bdd..";
		System.out.println("Matches: " + Stringulina.matches(s, s2));	// true
		System.out.println("Matches: " + Stringulina.matches(s, s3));	// true
		System.out.println("Matches: " + Stringulina.matches(s, s4));	// true
		System.out.println("Matches: " + Stringulina.matches(s, s5));	// false
		System.out.println("Matches: " + Stringulina.matches(s, s6));	// false
		
		*/
	}
	
	/*String str = "Praktikum: PdG";
	String str2 = "PdG";
	String str3 = "GdG";
	System.out.println("Position: " + Stringulina.substringPos(str, str2)); // 11
	System.out.println("Position: " + Stringulina.substringPos(str, str3)); // -1
	String s = "ahaahacaha";
	String s2 = "aha";
	String s3 = "aba";
	String s4 = "aaaa";
	String s5 = "aa";
	System.out.println("Anzahl: " + Stringulina.countSubstring(s, s2));	// 3
	System.out.println("Anzahl: " + Stringulina.countSubstring(s, s3));	// 0
	System.out.println("Anzahl: " + Stringulina.countSubstring(s4, s5));	// 3 
	String klammer1 = "abc(efsdf(yvvx))cxv(asffsdfs)"; // true
	String klammer2 = ")dsfds(()"; // false
	String klammer3 = "()()()()dfsdfsd(safsdsa)dfsdf(dfsd))("; // false
	System.out.println("1: " + Stringulina.correctlyBracketed(klammer1));
	System.out.println("2: " + Stringulina.correctlyBracketed(klammer2));
	System.out.println("3: " + Stringulina.correctlyBracketed(klammer3)); */
}
