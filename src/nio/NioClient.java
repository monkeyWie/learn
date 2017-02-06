package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NioClient {
	public static void main(String[] args) throws IOException {
//		Pattern pattern = Pattern.compile("^.*(\\d*)(\\..*)!$");
//    	Matcher matcher = pattern.matcher("入职培训资料20150708.zip");
//    	if(matcher.find()){
//    		System.out.println(matcher.group(1));
//    	}
		new ClientThread("F:/入职培训资料20150708.zip").start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new ClientThread("F:/jQuery1.11.0_api.chm").start();
	}
	
}

class ClientThread extends Thread{
	
	private String fileName;
	
	public ClientThread(String fileName){
		this.fileName = fileName;
	}
	
	public void run(){
		SocketChannel sc = null;
		try {
			sc = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
			File file = new File(fileName);
			ByteBuffer head = ByteBuffer.allocate(256);
			ByteBuffer buffer = ByteBuffer.allocate(2048);
			head.put(file.getName().getBytes());
			head.clear();
			sc.write(head);
			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();
			while (fc.read(buffer)!=-1) {
				//System.out.println(1111);
				buffer.flip();
				sc.write(buffer);
				buffer.clear();
			}
			System.out.println(2222);
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}