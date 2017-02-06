package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
	public static void main(String[] args) throws Exception {
		/*FileInputStream from = new FileInputStream("d:/1.txt");
		FileChannel fromChannel = from.getChannel();
		FileOutputStream to = new FileOutputStream("d:/2.txt");
		FileChannel toChannel = to.getChannel();
		fromChannel.transferTo(0l,fromChannel.size(),toChannel);*/
		//400 500
		long time = System.currentTimeMillis();
		copyFile();
		System.out.println(System.currentTimeMillis()-time);
	}
	
	static void copyFile() throws Exception{
		URI uri = new URI("http://www.baidu.com?asd=%25");
		System.out.println(uri.toASCIIString());
		BufferedInputStream input = new BufferedInputStream(new FileInputStream("d:/1.txt"));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d:/2.txt"));
		byte[] b = new byte[8192];
		int len = -1;
		while((len=input.read(b))!=-1){
			out.write(b, 0, len);
		}
		out.close();
		input.close();
	}
	
	static void copyFileNio() throws Exception{
		FileInputStream input = new FileInputStream("d:/1.txt");
		FileOutputStream out = new FileOutputStream("d:/2.txt");
		FileChannel inputChannel = input.getChannel();
		FileChannel outChannel = out.getChannel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
		while(inputChannel.read(buffer)!=-1){
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		outChannel.close();
		inputChannel.close();
	}
}
