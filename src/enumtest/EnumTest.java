package enumtest;

public class EnumTest {
	public static void main(String[] args) {
		System.out.println(Test.MAN);
	}
}

enum Test{
	MAN("M");
	
	private String value;
	Test(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}