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
		int res = choose.showOpenDialog(null);//�����ļ�ѡ��Ի���,����ֵ���������ж��û�����Ǵ򿪻���ȡ��
		System.out.println(res);
		if (res==JFileChooser.OPEN_DIALOG){
			//����û�ѡ�е��ļ�·��
			File selectedFile = choose.getSelectedFile();//File 
			
			//1��ImageIO.read(������ȡͼƬ
			try {
				BufferedImage sourceImage =  ImageIO.read(selectedFile);
				
				//2����һ���µ� ָ����С�� BufferedImage 
				BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				
				//3��Դͼ(sourceImage)ת����Ŀ��ͼƬ(targetImage)��
				   //�õ�Ŀ��ͼƬ�Ļ��ʶ���
				Graphics g =  targetImage.getGraphics();
				//ͨ��g��drawImage������Դͼ����Ŀ��ͼƬ��
				g.drawImage(sourceImage, 0, 0, width, height, null);
				
				//4ͨ�� ImageIO.write������targetImage�����ָ����IO��
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
				ImageIO.write(targetImage, format, bos);//��ͼƬת����ByteArrayOutputStream�������
				
				byte[] imgeBuf= bos.toByteArray();//���ͼƬ�Ķ���������
				
				//��ͼƬ�Ķ���������ת�����ַ���
				BASE64Encoder encoder = new BASE64Encoder();
				String imageStr =   encoder.encode(imgeBuf);
				System.out.println("ת�����");
				
				Date date = new Date();
				String fileName = String.valueOf(date.getTime());
				//-----------------------------------------
				FileOutputStream fos = new FileOutputStream(destination + "\\"+ fileName + "." + format);//ת���õ���ͼƬ�ŵ�����
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
