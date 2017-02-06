package util;

public class ByteUtil {
	public static void mask(byte[] key,byte[] data){
		for (int i = 0; i < data.length; i++) {
			//int mod = i%4;
			int mod = i&3;
			data[i] = (byte) (data[i]^key[mod]);
		}
	}
	
	public static byte[] toByteArray(String data){
		byte[] array = new byte[data.length()/2];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.valueOf(data.substring(i*2,i*2+2),16).byteValue();
		}
		return array;
	}
	
	public static String toByteString(byte[] data){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			String str = Integer.toHexString(data[i]&0xFF);
			str = str.length()==1?"0"+str:str;
			sb.append(str); 
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(8^2);
		System.out.println(0^0);
		System.out.println(0^1);
	}
}
