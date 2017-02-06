package thread.demo_2_3_1;

public class PrintString implements Runnable{
	private boolean isPrint = true;

	public boolean isPrint() {
		return isPrint;
	}

	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}
	
	public void printStringMethod(){
		while(isPrint){
			System.out.println(11111);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		printStringMethod();
	}
	
	public static void main(String[] args) {
		PrintString printString = new PrintString();
		new Thread(printString).start();
		System.out.println("停");
		printString.setPrint(false);
	}
}
