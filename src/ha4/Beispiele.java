import java.util.Arrays;

public class Beispiele {
	public static void main(String[] args) {
		/*
		WordCountsArray wcArray = new WordCountsArray(10);
		for (int i = 0; i < 12; i++) {
			wcArray.add("Test " + i , 10 + i);
		}
		
		for (int i = 0; i < 12; i++) {
			System.out.println(wcArray.getWord(i) + ": " + wcArray.getCount(i));
		}
		
		for (int i = 0; i < 12; i++) {
			wcArray.setCount(i, -6 + i);
		}
		
		for (int i = 0; i < 12; i++) {
			System.out.println(wcArray.getWord(i) + ": " + wcArray.getCount(i));
		}
		
		System.out.println(wcArray.getWord(13) + ": " + wcArray.getCount(23));
		System.out.println(wcArray.getWord(-5) + ": " + wcArray.getCount(0));
		*/
		String test = "Hallo dies ist ein String";
		System.out.println(Arrays.toString(Document.tokenize(test)));
	}
}
