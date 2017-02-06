package nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NioServer {
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) throws IOException {
		ByteBuffer head = ByteBuffer.allocate(256);
		ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//设置为非阻塞方式
        ssc.socket().bind(new InetSocketAddress(8989));
        ssc.register(selector, SelectionKey.OP_ACCEPT);//注册监听的事件
		int i = 0;
        while (selector.select()>0) {
            Set selectedKeys = selector.selectedKeys();//取得所有key集合
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();//接受到服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.read(head);
                    byte[] b = new byte[256];
                    head.flip();
                    head.get(b);
                    head.clear();
                    String fileName = new String(b).trim();
                    System.out.println("fileName:"+fileName);
                    File file = new File("E:/nio/"+fileName);
                    if(!file.exists()){
                    	file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    FileChannel fc = fos.getChannel();
                    while(sc.read(buffer)!=-1){
                    	buffer.flip();
                    	fc.write(buffer);
                    	buffer.clear();
                    }
                   /* while (true) {
                        int n = sc.read(buffer);//读取数据
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();
                    	byte[] b = new byte[n];
						buffer.get(b);
						System.out.println(new String(b));
                    }*/
                    fc.close();
                    sc.close();
                    it.remove();
                }
            }
        }
	}
}
