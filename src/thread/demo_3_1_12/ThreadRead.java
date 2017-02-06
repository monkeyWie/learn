package thread.demo_3_1_12;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadRead extends Thread{

	private PipedInputStream inputStream;
	
	public ThreadRead(PipedInputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void run() {
		try {
			int length = -1;
			byte[] bts = new byte[6];
			while((length=inputStream.read(bts))!=-1){
				for (int i = 0; i < length; i++) {
					System.out.print(bts[i]+"\t");
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		PipedInputStream inputStream = null;
		PipedOutputStream outputStream = null;
		try {
			inputStream = new PipedInputStream();
			outputStream = new PipedOutputStream(inputStream);
			//inputStream.connect(outputStream);
			//outputStream.connect(inputStream);
			new ThreadRead(inputStream).start();
			Thread.sleep(1000);
			new ThreadWrite(outputStream).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
