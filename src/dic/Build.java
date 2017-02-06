package dic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Build {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("E:/字典/深圳手机号前7位.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("E:/字典/深圳手机号.txt"));
		String line = null;
		while((line=reader.readLine())!=null){
			for (int i = 0; i < 10000; i++) {
				writer.write(line+String.format("%04d",i)+"\n");
			}
		}
		writer.close();
		reader.close();
	}
}
