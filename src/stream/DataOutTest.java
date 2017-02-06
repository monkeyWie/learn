package stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataOutTest {
	public static void main(String[] args) {
		DataOutputStream out = new DataOutputStream(new ByteArrayOutputStream());
		try {
			byte[] bts = intToBytes(66666666);
			out.write(bts);
			DataInputStream input = new DataInputStream(new ByteArrayInputStream(bts));
			System.out.println(input.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] intToBytes(int value)   
	{   
	    byte[] src = new byte[4];  
	    src[0] = (byte) ((value>>24) & 0xFF);  
	    src[1] = (byte) ((value>>16)& 0xFF);  
	    src[2] = (byte) ((value>>8)&0xFF);    
	    src[3] = (byte) (value & 0xFF);       
	    return src;  
	}  
}
