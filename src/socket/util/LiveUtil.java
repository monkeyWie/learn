package socket.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class LiveUtil {
	
	private int width;
	private int height;
	
	public LiveUtil(int width, int height) {
		this.width = width;
		this.height = height;
	}

	private static LiveUtil liveUtil;
	
	public static LiveUtil getInstance(){
		if(liveUtil==null){
			liveUtil = new LiveUtil(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		}
		return liveUtil;
	}
	
	/**
	 * 屏幕截屏转成字节流
	 * @return
	 * @throws AWTException
	 * @throws IOException
	 */
	public byte[] getScreen() throws AWTException, IOException{
		BufferedImage bi = new Robot().createScreenCapture(new Rectangle(0,0,width,height));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
        
        encoder.encode(bi);
        return baos.toByteArray();
		/*System.out.println(bi.getData().getDataBuffer().getSize());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi,"jpg",out);
		System.out.println(System.currentTimeMillis()-time);
		return out.toByteArray();*/
	}
	
	public byte[] buildProtocol(byte[] bytes){
		byte[] temp = new byte[bytes.length+4];
		for(int i=0;i<4;i++){
			temp[i] = (byte)(bytes.length>>8*i&0xFF);
		}
		for (int i = 4; i < temp.length; i++) {
			temp[i] = bytes[i-4];
		}
		return temp;
	}
	
	public int getDataSize(byte[] bytes){
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += (bytes[i]&0xFF)<<8*i;
		}
		return sum;
	}
	
	
	public static void main(String[] args) throws AWTException, IOException {
		System.out.println(3<<8);
		/*byte a = (byte)129;
		System.out.println(a<<8);
		System.out.println(-127<<8);*/
		/*byte[] temp = LiveUtil.getInstance().buildProtocol(LiveUtil.getInstance().getScreen());
		System.out.println(getInstance().getDataSize(temp));*/
		
		/*FileOutputStream fos = new FileOutputStream("d:/1.png");
		fos.write(LiveUtil.getInstance().getScreen());
		fos.close();*/
	}
}
