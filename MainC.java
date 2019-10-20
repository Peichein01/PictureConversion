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
		int height = 900, width = 900;
		String type = "jpg";
		(new PictureConversion(width, height, type)).convertPicture();
	}
}
