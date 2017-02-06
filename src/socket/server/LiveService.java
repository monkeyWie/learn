package socket.server;

import java.awt.AWTException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import socket.util.LiveUtil;

public class LiveService {
	
	public static byte[] image = null;
	public static Map<Long,Boolean> flagMap = new HashMap<Long, Boolean>();
	
	public static void main(String[] args) {
		
		ServerSocket s = null;
        try {  
            //设定服务端的端口号  
            s = new ServerSocket(9898);
            //屏幕录制线程
            new ServerHandle().start();
            //客户端请求
            while(true){
            	new ClientHandle(s.accept()).start();
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                s.close();  
            } catch (Exception e2) {  
            	e2.printStackTrace();
            }  
        }  
	}
}

class ServerHandle extends Thread{
	
	public void run(){
		while(true){
			try {
				LiveService.image = LiveUtil.getInstance().buildProtocol(LiveUtil.getInstance().getScreen());
				for(Long k : LiveService.flagMap.keySet()){
					if(!LiveService.flagMap.get(k)){
						LiveService.flagMap.put(k,true);
					}
				}
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class ClientHandle extends Thread{
	private Socket socket;
	
	public ClientHandle(Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		LiveService.flagMap.put(Thread.currentThread().getId(),false);
		//用于接收客户端发来的请求  
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
			while(true){
				if(LiveService.image!=null&&LiveService.flagMap.get(Thread.currentThread().getId())){
					bos.write(LiveService.image);
					LiveService.flagMap.put(Thread.currentThread().getId(),false);
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				bos.close();
				socket.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
    	
	}
	
}
