package pictureConversion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import sun.misc.BASE64Encoder;

public class MainC {

	public static void main(String[] args) {
		/*
		 * 在这里设置你想要的图片的像素和格式
		 */
		int height = 1200, width = 1800;
		String format = "png";
		String destination = "C:\\Users\\12290\\Desktop";
		(new PictureConversion(width, height, format, destination)).convertPicture();
	}
}
