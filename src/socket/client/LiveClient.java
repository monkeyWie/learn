package socket.client;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LiveClient extends JFrame{
	
	private JLabel jl = new JLabel();
	
	public LiveClient(){
		this.setSize(1920,1080);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Live");
		this.setVisible(true);
		this.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e){
				jl.setBounds(0,0,e.getComponent().getWidth()-20,e.getComponent().getHeight()-40);
			}
		});
		this.add(jl);
	}
	
	public void setBackIcon(byte[] image){
		ScaleIcon icon = new ScaleIcon(new ImageIcon(image));
		jl.setIcon(icon);
	}
	
	public static void main(String[] args) {
		
		LiveClient lc = new LiveClient();
		
		
		Socket socket = null;  
        BufferedInputStream bis = null;
        try {  
            //客户端socket指定服务器的地址和端口号  
            socket = new Socket("127.0.0.1",9898);  
            bis = new BufferedInputStream(socket.getInputStream());
            int ei = 0;
            while(true){
    			byte[] size = new byte[4];
    			bis.read(size,0,4);
    			ei = getDataSize(size);
    			byte[] image = new byte[ei];
    			for (int i = 0; i < ei; i++) {
    				image[i] = (byte)bis.read();
    			}
    			lc.setBackIcon(image);
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                bis.close();
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
			
	}
	
	public static int getDataSize(byte[] bytes){
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += (bytes[i]&0xFF)<<8*i;
		}
		return sum;
	}
	
}

class ScaleIcon implements Icon {  
	  
    private Icon icon = null;  
  
    public ScaleIcon(Icon icon) {  
        this.icon = icon;  
    }  
  
    @Override  
    public int getIconHeight() {  
        return icon.getIconHeight();  
    }  
  
    @Override  
    public int getIconWidth() {  
        return icon.getIconWidth();  
    }  
    
    public void paintIcon(Component c, Graphics g, int x, int y) {  
        float wid = c.getWidth();  
        float hei = c.getHeight();  
        int iconWid = icon.getIconWidth();  
        int iconHei = icon.getIconHeight();  
        Graphics2D g2d = (Graphics2D) g;  
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g2d.scale(wid / iconWid, hei / iconHei);  
        icon.paintIcon(c, g2d, 0, 0);  
    }  
}
