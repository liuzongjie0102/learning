package servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drowimg extends HttpServlet {

	private static final int width = 120;
	private static final int height = 30;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1�����ڴ��д���һ��ͼƬ
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//2.�õ�ͼƬ
		Graphics g = bi.getGraphics();
		//3.����ͼƬ�ı�Ӱɫ
		setBackGround(g);
		//4.����ͼƬ�ı߿�
		setBorder(g);
		//5.��ͼƬ�ϻ�������
		drawRandowLine(g);
		//6.д��ͼƬ�������
		String random = drawRandomNum((Graphics2D)g);
		//7.�����������session��
		req.getSession().setAttribute("checkcode", random);
		//8.������Ӧͷ֪ͨ�������ͼƬ����ʽ��
		resp.setContentType("image/jpeg");//��ͬ��resp.setHeader("Conten-Type","image/jpeg");
		//9.������Ӧͷ�����������Ҫ����
		resp.setDateHeader("expries", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		//10.��ͼƬд�������
		ImageIO.write(bi, "jpg", resp.getOutputStream());
	}

	private String drawRandomNum(Graphics2D g) {
		
		String baseNumberLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
		
		return createRandomChar(g,baseNumberLetter);
	}

	private String createRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
		int x = 5;
		String ch = "";
		
		for(int i = 0; i < 4 ; i++)
		{
			int degree = new Random().nextInt();
			ch =  baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
			sb.append(ch);
			
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
				
		return sb.toString();
	}

	private void drawRandowLine(Graphics g) {
		g.setColor(Color.GREEN);
		
		for(int i = 0; i < 5 ; i ++)
		{
			int x1 = new Random().nextInt(width);
			int y1 = new Random().nextInt(height);
			int x2 = new Random().nextInt(width);
			int y2 = new Random().nextInt(height);
			
			g.drawRect(x1, y1, x2, y2);
		}
	}

	private void setBorder(Graphics g) {
		g.setColor(Color.BLUE);
		
		g.drawRect(1, 1, width - 2, height - 2);
	}

	private void setBackGround(Graphics g) {
		g.setColor(Color.WHITE);
		
		g.fillRect(0, 0, width, height);
	}
}
