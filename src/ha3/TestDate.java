
public class TestDate {
	public static void main(String[] args) {
		Date t = new Date(29, 2, 2000);
		System.out.println("" + t.toString());
		t.setYear(1999);
		System.out.println("" + t.toString());
	}
}	
