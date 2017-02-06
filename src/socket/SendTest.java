package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class SendTest {
	public static void main(String[] args) {
		//post("http://127.0.0.1/iclap_gather/v2/common/uploadFile","F:/电影/十二只猴子.mkv",1577033231);
		//post("http://127.0.0.1/iclap_gather/v2/common/uploadFile","D:/t1.jpg",0);
		System.out.println(FileDigest.getFileMD5(new File("d:/1.swf")));
		System.out.println(FileDigest.getFileMD5(new File("d:/2.swf")));
	}
	
	public static void post(String url,String path,long seek){
		//http://127.0.0.1/iclap_gather/v2/common/uploadFile
		String host = url.substring(url.indexOf("http://")+7);
		host = host.substring(0,host.indexOf("/"));
		String[] temp = host.split(":");
		int port = 80;
		if(temp.length>1){
			host = temp[0];
			port = Integer.valueOf(temp[1]);
		}
		try {
			Socket socket = new Socket(host,port);
			StringBuffer buffer = new StringBuffer();
			//要上传的文件
			//File file = new File("D:/IMG_1445.jpg");
			File file = new File(path);
			String md5 = FileDigest.getFileMD5(file);
			//分割标识
			String boundary = "----WebKitFormBoundarybRVyo923rDBLarLpgo";
			StringBuffer fileHead = new StringBuffer();
			//body结束 --boundary--\r\n	最后要加上换行符
			String bodyEnd = "\r\n--" + boundary + "--\r\n";
			fileHead.append("--" + boundary + "\r\n");
			//文件上传head
			fileHead.append("Content-Disposition: form-data; name=\"uploadFile\"; filename=\""+file.getName()+"\"\r\n");
			// fileHead.append("Content-Type: image/jpeg\r\n");	//不传服务器一样能接收
			fileHead.append("\r\n");
			//127.0.0.1/iclap_gather/v2/userFile/queryUserFileList?userId=91577
			//计算Content-Length  如果不传或算不正确服务器端接受的数据则会出错
			long length = fileHead.toString().getBytes().length + file.length()
					+ bodyEnd.getBytes().length;
//			buffer.append("POST http://localhost:8080/Ftp/upload HTTP/1.1\r\n");
			buffer.append("POST "+url+" HTTP/1.1\r\n");
			buffer.append("Host: "+host+"\r\n");
			buffer.append("UserAgent: IE8.0\r\n");
			// buffer.append("Connection: Keep-Alive\r\n");	//请求完后tcp连接不会中断直至超时
			buffer.append("Connection: close\r\n");	//请求完后tcp连接直接中断
			buffer.append("Content-Length: " + length + "\r\n");
			buffer.append("devfilestartposition: "+seek+"\r\n");
			buffer.append("devfiletotalsize: "+file.length()+"\r\n");
			buffer.append("devfilemd5: "+md5+"\r\n");
			buffer.append("Content-Type: multipart/form-data; boundary="+boundary+"\r\n");
			buffer.append("\r\n");
			//这里开始计算body长度
			buffer.append(fileHead);

			socket.getOutputStream().write(buffer.toString().getBytes());
			// Thread.sleep(10000);
			FileInputStream fis = new FileInputStream(file);
			fis.skip(seek);
			byte[] bs = new byte[8192];
			int len = -1;
			while ((len = fis.read(bs)) != -1) {
				socket.getOutputStream().write(bs,0,len);
			}
			socket.getOutputStream().write(bodyEnd.getBytes());

			// --输出服务器传回的消息的头信息
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class FileDigest {
	  /**
	   * 获取单个文件的MD5值！
	   * @param file
	   * @return
	   */
	  public static String getFileMD5(File file) {
	    if (!file.isFile()){
	      return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in=null;
	    byte buffer[] = new byte[10240];
	    int len;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      in = new FileInputStream(file);
	      while ((len = in.read(buffer, 0, 10240)) != -1) {
	        digest.update(buffer, 0, len);
	      }
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
	  }

	}
