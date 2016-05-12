package com.newer.xy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 面板
 * 
 * @author 夏雨
 *
 */
public class GamePanel extends JPanel {

	static final int R = 20;
	// 每个格子边长
	static final int SIDE = 30;
	// 棋盘原点坐标

	static final Point START = new Point(30, 20);;

	int n;
	ArrayList<Point> pointList = new ArrayList<>();

	int a[][] = new int[15][15];

	public int[][] getA() {
		return a;
	}

	public ArrayList<Point> getPointList() {
		return pointList;
	}

	public GamePanel(int[][] a) {
		super();
		this.a = a;
	}

	class Listener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);

			// 绘制
			pointList.add(new Point(e.getX(), e.getY()));

			// 重绘
			repaint();
		}
	}

	Image image1;
	Image image2;
	Image image3;

	public GamePanel() {
		URL path1 = GamePanel.class.getResource("/res/4.jpg");
		image1 = new ImageIcon(path1).getImage();
		URL path2 = GamePanel.class.getResource("/res/2.jpg");
		image2 = new ImageIcon(path2).getImage();
		URL path3 = GamePanel.class.getResource("/res/3.jpg");
		image3 = new ImageIcon(path3).getImage();

		// 监听器
		addMouseListener(new Listener());
	}

	/**
	 * 绘制
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(image1, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.BLACK);

		for (int n = 0; n < 15; n++) {
			g.drawLine(30, 20 + n * SIDE, 450, 20 + n * SIDE);
		}

		for (int n = 0; n < 15; n++) {
			g.drawLine(30 + n * SIDE, 20, 30 + n * SIDE, 440);
		}

		// 填充天元和四星
		g.fillOval(7 * SIDE + START.x - R / 4, 7 * SIDE + START.y - R / 4, R / 2, R / 2);
		g.fillOval(3 * SIDE + START.x - R / 4, 3 * SIDE + START.y - R / 4, R / 2, R / 2);
		g.fillOval(3 * SIDE + START.x - R / 4, 11 * SIDE + START.y - R / 4, R / 2, R / 2);
		g.fillOval(11 * SIDE + START.x - R / 4, 11 * SIDE + START.y - R / 4, R / 2, R / 2);
		g.fillOval(11 * SIDE + START.x - R / 4, 3 * SIDE + START.y - R / 4, R / 2, R / 2);

		// 落子的实现
		for (int i = 0; i < pointList.size(); i++) {
			if (i % 2 == 1) {
				setChess(g, image2, pointList.get(i));
			} else {
				setChess(g, image3, pointList.get(i));
			}

		}

	}

	/**
	 * 落子的方法
	 * 
	 * @param g
	 * @param image
	 * @param p
	 */
	// public int[][] ChangePoint (Point p){
	//
	// int nX = (p.x - START.x) / SIDE;
	// int X = (p.x - START.x) % SIDE;
	// int nY = (p.y - START.y) / SIDE;
	// int Y = (p.y - START.y) % SIDE;
	// if ((X <= R / 2) & (Y <= R / 2)){
	// int [][] = nX;
	// p.y = nY;
	// }
	//
	// return p;
	// }

	public void setChess(Graphics g, Image image, Point p) {

		int nX = (p.x - START.x) / SIDE;
		int X = (p.x - START.x) % SIDE;
		int nY = (p.y - START.y) / SIDE;
		int Y = (p.y - START.y) % SIDE;

		if ((X <= R / 2) & (Y <= R / 2)) {
			g.drawImage(image, nX * SIDE + START.x - R / 2, nY * SIDE - R / 2 + START.y, R, R, null);
			// Point point = new Point(nX1 * SIDE + START.x - R / 2, nY1 * SIDE
			// - R / 2 + START.y);
			// pointList.add(point);
			if (pointList.size() % 2 == 1) {
				a[nX][nY] = 1;
			} else {
				a[nX][nY] = -1;
			}
		}
		if ((X > R / 2) & (Y <= R / 2)) {
			g.drawImage(image, (nX + 1) * SIDE + START.x - R / 2, nY * SIDE + START.y - R / 2, R, R, null);
			// Point point = new Point((nX1 + 1) * SIDE + START.x - R / 2, nY1 *
			// SIDE + START.y - R / 2);
			// pointList.add(point);
			if (pointList.size() % 2 == 1) {
				a[nX + 1][nY] = 1;
			} else {
				a[nX + 1][nY] = -1;
			}
		}

		if ((X <= R / 2) & (Y > R / 2)) {
			g.drawImage(image, nX * SIDE + START.x - R / 2, (nY + 1) * SIDE + START.y - R / 2, R, R, null);
			// Point point = new Point(nX1 * SIDE + START.x - R / 2, (nY1 + 1) *
			// SIDE + START.y - R / 2);
			// pointList.add(point);
			// if(pointList.size()%2 == 1){a[][] = }
			if (pointList.size() % 2 == 1) {
				a[nX][nY + 1] = 1;
			} else {
				a[nX][nY + 1] = -1;
			}
		}
		if ((X > R / 2) & (Y > R / 2)) {
			g.drawImage(image, (nX + 1) * SIDE + START.x - R / 2, (nY + 1) * SIDE + START.y - R / 2, R, R, null);
			// Point point = new Point((nX1 + 1) * SIDE + START.x - R / 2, (nY1
			// + 1) * SIDE + START.y - R / 2);
			// pointList.add(point);
			if (pointList.size() % 2 == 1) {
				a[nX + 1][nY + 1] = 1;
			} else {
				a[nX + 1][nY + 1] = -1;
			}
		}

	}
	/**
	 * 			判断输赢的方法
	 * @param x
	 * 			横向格点数
	 * @param y
	 * 			纵向格点数
	 */
	public boolean judge(int x, int y) {
		boolean flag = false;
		int count = 1;
		int topX = x + 4;
		int lowX = x - 4;
		int topY = y + 4;
		int lowY = y + 4;
		//判断横向
		if(lowX<0){
			for(int i=0;i<topX;i++){
				if(a[i][y] == a[x][y]){
					count++;
				}
			}
			}
		return flag;
	}
}
