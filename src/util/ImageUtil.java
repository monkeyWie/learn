package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static void binaryImage(String filePath,String outPath) throws IOException {
		File file = new File(filePath);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_BINARY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}

		File newFile = new File(outPath);
		ImageIO.write(grayImage, "bmp", newFile);
	}

	public static void grayImage(String filePath,String outPath) throws IOException {
		File file = new File(filePath);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}

		File newFile = new File(outPath);
		ImageIO.write(grayImage, "bmp", newFile);
	}

	public static void main(String[] args) throws IOException {
		binaryImage("d:/tmp/test.jpg","d:/tmp/test_b.jpg");
		binaryImage("d:/tmp/test.jpg","d:/tmp/test_g.jpg");
	}

}
