package minesweeper;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper extends JFrame{
	
	private static final int CELL_WIDTH = 20;
	private static final int CELL_HEIGHT = 20;
	private static final String IAMGE_M1 = "m1.bmp";
	private static final String IAMGE_M2 = "m2.bmp";
	private static final String IAMGE_Q = "q.bmp";
	private int flag = 0;	//标识是否左右键一起按下
	
	private int width;
	private int height;
	private Cell[][] cells; 
	private double difficulty;	//难度系数 雷数占总格子的比例 越大越难 默认为0.1
	private int flagCount = 0;	//插旗的数量
	private int winFlag = 0;	//是否胜利	1为胜利
	
	public Minesweeper(int width,int height){
		this.width = width;
		this.height = height;
		this.difficulty = 0.05;
		cells = new Cell[width][height];
		this.setSize(width*CELL_WIDTH+6, height*CELL_HEIGHT+33);
		iniGraph();
		iniMine();
	}
	
	public Minesweeper(int width,int height,double difficulty){
		this.width = width;
		this.height = height;
		this.difficulty = difficulty;
		cells = new Cell[width][height];
		this.setSize(width*CELL_WIDTH+6, height*CELL_HEIGHT+33);
		iniGraph();
		iniMine();
	}
	
	public static void main(final String[] args) throws IOException {
		/*Minesweeper mw = new Minesweeper(20,20,0.15);
		mw.setLayout(null);
		mw.setLocationRelativeTo(null);
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.setTitle("扫雷");
		mw.setVisible(true);
		mw.setResizable(false);*/
		ImageIcon icon = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("image/m1.bmp")));
		ImageIO.write((BufferedImage)icon.getImage(),"png",new File("E:/out.ico"));
		
	}
	
	//生成地雷
	private void iniMine(){
		int count = (int) (this.width*this.height*this.difficulty);	//雷数
		for(int i = 0;i<count;i++){
			buildMine();
		}
	}
	
	//通过随机格子的坐标，生成地雷
	private void buildMine(){
		//生成X坐标
		int rw = (int)(Math.random()*this.width);
		//生成Y坐标
		int rh = (int)(Math.random()*this.height);
		if(this.cells[rw][rh].getFlag()==1){
			buildMine();
		}else{
			this.cells[rw][rh].setFlag(1);
		}
	}
	
	//初始化界面
	private void iniGraph(){
		for(int i = 0;i<this.height;i++){
			for(int j =0;j<this.width;j++){
				add(buildCell(j, i));
			}
		}
	}
	
	private Cell buildCell(int x,int y){
		Cell c = new Cell();
		c.setSize(CELL_WIDTH, CELL_HEIGHT);
		c.setLocation(x*CELL_WIDTH, y*CELL_HEIGHT);
		c.addMouseListener(new MouseListener() {
			int count = 0;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(flag==0){
					//判断为左键
					if(e.getButton()==MouseEvent.BUTTON1){
						gameLogic((Cell) e.getComponent());
					//判断为右键
					}else if(e.getButton()==MouseEvent.BUTTON3){
						addFlag((Cell) e.getComponent());
					}
				}else{
					gameHelp((Cell) e.getComponent(),flag);
					count++;
					if(count==2){
						flag = 0;
						count = 0;
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//判断左右键同时按下
				if(e.getModifiersEx()==(MouseEvent.BUTTON3_DOWN_MASK + MouseEvent.BUTTON1_DOWN_MASK)) {
					gameHelp((Cell) e.getComponent(),flag);
					flag = 1;
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		this.cells[x][y] = c;
		return c;
	}
	
	private void addFlag(Cell cell){
		int index_x = (int) (cell.getLocation().getX()/CELL_WIDTH);
		int index_y = (int) (cell.getLocation().getY()/CELL_HEIGHT);
		//标记为红旗
		if(cells[index_x][index_y].getTag()==0){
			cells[index_x][index_y].setTag(2);
			cells[index_x][index_y].setIcon(getIcon(IAMGE_Q));
			flagCount++;
			//判断是否胜利
			gameWin();
		//取消标记
		}else if(cells[index_x][index_y].getTag()==2){
			cells[index_x][index_y].setTag(0);
			cells[index_x][index_y].setIcon(null);
			flagCount--;
		}
	}
	
	private void gameLogic(Cell cell){
		int index_x = (int) (cell.getLocation().getX()/CELL_WIDTH);
		int index_y = (int) (cell.getLocation().getY()/CELL_HEIGHT);
		//如果插了红旗 则不让左击
		if(cells[index_x][index_y].getTag()!=2){
			//标记为已点击
			cells[index_x][index_y].setTag(1);
			//判断这个格子是否是雷
			if(cells[index_x][index_y].getFlag()==1){
				showAll(index_x,index_y);
			}else{
				int sum = eachCells(index_x, index_y,0);
				//如果这个格子不是雷，附近也没有雷，则遍历点击附近所有的格子
				if(sum==0){
					for(int j = index_y-1;j<index_y+2;j++){
						for(int i = index_x-1;i<index_x+2;i++){
							if(i>=0&&j>=0&&i<width&&j<height&&(i!=index_x||j!=index_y)&&cells[i][j].getTag()==0){
								gameLogic(cells[i][j]);
							}
						}
					}
				}
			}
			gameWin();
		}
	}
	
	private void gameHelp(Cell cell,int flag){
		int index_x = (int) (cell.getLocation().getX()/CELL_WIDTH);
		int index_y = (int) (cell.getLocation().getY()/CELL_HEIGHT);
		int sum1 = 0;	//雷的数量
		int sum2 = 0;	//插旗的数量
		for(int j = index_y-1;j<index_y+2;j++){
			for(int i = index_x-1;i<index_x+2;i++){
				if(i>=0&&j>=0&&i<width&&j<height){
					if(flag==0){
						if(cells[i][j].getTag()==0){
							cells[i][j].setIcon(getIcon("0.bmp"));
						}
						if(cells[i][j].getFlag()==1){
							sum1++;
						}
						if(cells[i][j].getTag()==2){
							sum2++;
						}
					}else{
						if(cells[i][j].getTag()==0){
							cells[i][j].setIcon(null);
						}
					}
				}
			}
		}
		if(sum1>0&&sum1==sum2){
			for(int j = index_y-1;j<index_y+2;j++){
				for(int i = index_x-1;i<index_x+2;i++){
					if(i>=0&&j>=0&&i<width&&j<height&&cells[i][j].getTag()==0){
						gameLogic(cells[i][j]);
					}
				}
			}
		}
	}
	
	//flag标识是否是游戏结束 1为结束
	private int eachCells(int x,int y,int flag){
		//遍历周围的格子是否有雷
		int sum = 0;	//附近雷的数量
		for(int j = y-1;j<y+2;j++){
			for(int i = x-1;i<x+2;i++){
				//格子在角落或者在边上或者是本身的话，排除掉不用判断的格子
				if(i>=0&&j>=0&&i<width&&j<height&&(i!=x||j!=y)){
					if(cells[i][j].getFlag()==1){
						sum++;
						if(flag==1){
							cells[i][j].setIcon(getIcon(IAMGE_M1));
						}
					}
				}
			}
		}
		cells[x][y].setIcon(getIcon(sum+".bmp"));
		return sum;
	}
	
	//失败或胜利时遍历全部格子	失败
	private void showAll(int x,int y){
		for(int i = 0;i<height;i++){
			for(int j = 0;j<width;j++){
				if(cells[j][i].getTag()==0){
					eachCells(j,i,1);
				}
			}
		}
		cells[x][y].setIcon(getIcon(IAMGE_M2));
		JOptionPane.showMessageDialog(null, "GAME OVER");
	}
	
	//胜利
	private void showAll(){
		for(int i = 0;i<height;i++){
			for(int j = 0;j<width;j++){
				if(cells[j][i].getTag()==0){
					eachCells(j,i,1);
				}
			}
		}
	}
	
	//游戏胜利 1.把雷都插上了红旗 2.把不是雷的方块都点开了
	private void gameWin(){
		if(winFlag==0){
			//判断红旗是否都插对了
			if(flagCount==(int)(width*height*difficulty)){
				int count = 0;
				for(int i = 0;i<height;i++){
					for(int j = 0;j<width;j++){
						if(cells[j][i].getFlag()==1&&cells[j][i].getTag()==2){
							count++;
						}
					}
				}
				if(flagCount==count){
					JOptionPane.showMessageDialog(null, "GAME WIN");
					winFlag=1;
					showAll();
				}
			}else{
				//判断是不是把不是雷的方块都点开了
				for(int i = 0;i<height;i++){
					for(int j = 0;j<width;j++){
						if(cells[j][i].getFlag()==0){
							if(cells[j][i].getTag()==0){
								return;
							}
						}
					}
				}
				JOptionPane.showMessageDialog(null, "GAME WIN");
				winFlag=1;
				showAll();
			}
		}
	}
	
	//取得背景图片
	private ImageIcon getIcon(String imageName){
		try {
			return new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("image/"+imageName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Cell extends JButton{
	private int flag;	//标记是否是雷 不是雷为0 是雷为1
	private int tag;	//标记是否已点击 未点击为0 已点击为1  棋子为2
	
	public Cell(){
		super();
	}
	
	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
