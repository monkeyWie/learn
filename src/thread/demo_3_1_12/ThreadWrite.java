package thread.demo_3_1_12;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadWrite extends Thread{

	private PipedOutputStream outputStream;
	
	public ThreadWrite(PipedOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public PipedOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(PipedOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				outputStream.write(i);
				System.out.print(i+"\t");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				try {
					outputStream.close();
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
			for (int i = 0; i < 20; i++) {
				outputStream.write(i);
				System.out.print(i+"\t");
			}
			System.out.println();
			outputStream.close();
			Thread.sleep(1000);
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
		}
	}
}
