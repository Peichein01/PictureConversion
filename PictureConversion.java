package pictureConversion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import sun.misc.BASE64Encoder;

public class PictureConversion {
	private int width;
	private int height;
	private String format;
	private String destination;
	/*set default pixel as 50*50
	 * default format is jpg
	 */
	public PictureConversion() {
		this.width = 50;
		this.height = 50;
		this.format = "jpg";
	}
	public PictureConversion(int w, int h, String format, String destination) {
		this.width = w;
		this.height = h;
		this.format = format;
		this.destination = destination;
	}
	public void setPixel(int w, int h) {
		this.width = w;
		this.height = h;
	}
	public void setType(String format) {
		this.format = format;
	}
	/*
	 * 
	 */
	public void convertPicture() {
		JFileChooser  choose = new JFileChooser();
		int res = choose.showOpenDialog(null);//弹出文件选择对话框,返回值可以用于判断用户点击是打开还是取消
		System.out.println(res);
		if (res==JFileChooser.OPEN_DIALOG){
			//获得用户选中的文件路径
			File selectedFile = choose.getSelectedFile();//File 
			
			//1用ImageIO.read(方法读取图片
			try {
				BufferedImage sourceImage =  ImageIO.read(selectedFile);
				
				//2构造一个新的 指定大小的 BufferedImage 
				BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				
				//3把源图(sourceImage)转换到目标图片(targetImage)上
				   //得到目标图片的画笔对像
				Graphics g =  targetImage.getGraphics();
				//通过g的drawImage方法把源图画到目标图片上
				g.drawImage(sourceImage, 0, 0, width, height, null);
				
				//4通过 ImageIO.write方法将targetImage输出到指定的IO流
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
				ImageIO.write(targetImage, format, bos);//把图片转换到ByteArrayOutputStream输出流中
				
				byte[] imgeBuf= bos.toByteArray();//获得图片的二进制数据
				
				//把图片的二进制数据转换成字符串
				BASE64Encoder encoder = new BASE64Encoder();
				String imageStr =   encoder.encode(imgeBuf);
				System.out.println("转换完成");
				
				Date date = new Date();
				String fileName = String.valueOf(date.getTime());
				//-----------------------------------------
				FileOutputStream fos = new FileOutputStream(destination + "\\"+ fileName + "." + format);//转换得到的图片放到桌面
				ImageIO.write(targetImage, format, fos);
				fos.close();
				bos.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
