import java.util.Date;


public class Test {
	
	public static void main(String[] args) {
		Date date = new Date(2099,01,01);
		Long time = date.getTime();
		System.out.println("time4:" + time);
	}

}
