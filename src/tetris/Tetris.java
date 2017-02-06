package tetris;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JFrame{
	
	private int width;
	private int height;
	private int cellWidth = 20;
	private byte[][] cells;
	private int type;
	private int cellX;
	private int cellY;
	private int cellW;
	private int cellH;
	private int rotation;
//	private byte[][] cell;
	private JPanel jPanel;
	private Vector<JPanel> shapeJpanel = new Vector<JPanel>();;
	private Map<Integer,List<byte[][]>> shapes;
	
	public Tetris(int w,int h){
		this.width=w;
		this.height=h;
		this.setLayout(null);
		this.setSize(width*cellWidth+6,height*cellWidth+28);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Tetris");
		this.setVisible(true);
		this.setResizable(false);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				//System.out.println(e.getKeyCode());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println(e.getKeyCode());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//空格
				if(e.getKeyCode()==32){
					rotation();
				}
				//左
				if(e.getKeyCode()==37){
					/*cellX=cellX-1<0?0:cellX-1;
					draw();*/
					left();
				}
				//右
				if(e.getKeyCode()==39){
					/*cellX=cellX+1>width-cellW?width-cellW:cellX+1;
					draw();*/
					right();
				}
			}
		});
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.white);
		this.setContentPane(jPanel);
		cells=new byte[height][width];
	}
	
	public void buildCell(){
		type = (int)(Math.random()*6);
		rotation = (int)(Math.random()*this.shapes.get(type).size());
		cellW=getShape2()[0].length;
		cellH=getShape2().length;
		cellX=(int)(Math.random()*(width-cellW));
		cellY=0;
	}
	
	public byte[][] getShape2(){
		return shapes.get(type).get(rotation);
	}
	
	public void buildShapes(){
		shapes = new HashMap<Integer,List<byte[][]>>();
		byte[][][] baseShape = {{{1,1},{1,1}},{{1,0},{1,0},{1,1}},{{0,1},{0,1},{1,1}},{{1},{1},{1},{1}},{{1,0},{1,1},{0,1}},{{0,1},{1,1},{1,0}}};
		for(int i = 0;i<baseShape.length;i++){
			List<byte[][]> list = new ArrayList<byte[][]>();
			list.add(baseShape[i]);
			byte[][] tempShape;
			int j = 0;
			while(nq((tempShape = rotation(list.get(j))),list.get(0))){
				list.add(tempShape);
				j++;
			}
			shapes.put(i,list);
		}
	}
	
	public void draw(){
		jPanel.removeAll();
		int h = cellY+1>=cellH?cellH:cellY+1;
		for(int i=cellH-1,t=0;t<h;i--,t++){
			for(int j=0;j<cellW;j++){
				if(shapes.get(type).get(rotation)[i][j]==1){
					buildJpanel(j+cellX,cellY-t);
				}
			}
		}
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(cells[i][j]==1){
					buildJpanel(j,i);
				}
			}
		}
		jPanel.repaint();
	}
	
	public void rotation(){
		rotation = rotation+1>=shapes.get(type).size()?0:rotation+1;
	}
	
	public byte[][] rotation(byte[][] shape){
		byte[][] newShape = new byte[shape[0].length][shape.length];
		for(int i=0;i<newShape.length;i++){
			for(int j=0;j<newShape[i].length;j++){
				newShape[i][j]=shape[shape.length-j-1][i];
			}
		}
		return newShape;
	}
	
	public boolean nq(byte[][] shape1,byte[][] shape2){
		if(shape1.length==shape2.length&&shape1[0].length==shape2[0].length){
			for(int i=0;i<shape1.length;i++){
				for(int j=0;j<shape1[0].length;j++){
					if(shape1[i][j]!=shape2[i][j]){
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
	
	public void down(){
		//到底了
		if(cellY+1>=height){
			for(int i=cellH-1;i>=0;i--){
				for(int j=0;j<cellW;j++){
					if(shapes.get(type).get(rotation)[i][j]==1){
						cells[cellY-cellH+i+1][cellX+j]=1;
					}
				}
			}
			buildCell();
		}else{
			//判断是否碰到下面的方块
			for(int i=cellH-1,t=0;i>=0;i--,t++){
				for(int j=0;j<cellW;j++){
					//碰到了
					if(cellY+1-t>=0&&shapes.get(type).get(rotation)[i][j]==1&&cells[cellY+1-t][cellX+j]==1){
						//方块添加到容器里
						for(int k=cellH-1;k>=0;k--){
							for(int l=0;l<cellW;l++){
								if(shapes.get(type).get(rotation)[k][l]==1){
									cells[cellY-cellH+k+1][cellX+l]=1;
								}
							}
						}
						buildCell();
						return;
					}
				}
			}
			cellY++;
		}
		
	}
	
	public void left(){
		for(int i=0;i<cellW;i++){
			for(int j=cellH-1,k=0;j>=0;j--,k++){
				//碰到了
				if(cellY+k<height&&cellX-1-i>0&&shapes.get(type).get(rotation)[j][i]==1&&cells[cellY+k][cellX-1-i]==1){
					return;
				}
			}
		}
		cellX=cellX-1<0?0:cellX-1;
	}
	
	public void right(){
		for(int i=cellW-1;i>=0;i--){
			for(int j=cellH-1,k=0;j>=0;j--,k++){
				//碰到了
				if(cellY+k<height&&cellX+1+i<width&&shapes.get(type).get(rotation)[j][i]==1&&cells[cellY+k][cellX+1+i]==1){
					return;
				}
			}
		}
		cellX=cellX+1>=width-cellW?width-cellW:cellX+1;
	}
	
	public void drawShape(){
		for (JPanel j : shapeJpanel) {
			this.jPanel.remove(j);
		}
		shapeJpanel.clear();
		for(int i=cellH-1,t=0;i>=0;i--,t++){
			for(int j=0;j<cellW;j++){
				if(shapes.get(type).get(rotation)[i][j]==1){
					shapeJpanel.add(buildJpanel(j+cellX,t+cellY));
				}
			}
		}
		repaint();
	}
	
	
	public JPanel buildJpanel(int x,int y){
		JPanel jp = new JPanel();
		jp.setBounds(x*cellWidth,y*cellWidth,cellWidth,cellWidth);
		jp.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		this.jPanel.add(jp);
		return jp;
	}
	
	public void start(){
		buildShapes();
		buildCell();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					draw();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		
		while(true){
			try {
				Thread.sleep(500);
				down();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public int cellW{
		return shapes.get(type).get(rotation)[0].length;
	}
	
	public int cellH{
		return shapes.get(type).get(rotation).length;
	}
	*/
	public static void main(String[] args) {
		Tetris t = new Tetris(16,20);
		t.start();
	}
	
}
