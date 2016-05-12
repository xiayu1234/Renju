package com.newer.xy;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Renju {

	public static void main(String[] args) {

		JFrame frame = new JFrame("窗口");

		// 大小
		frame.setSize(500, 500);

		// 屏幕居中
		frame.setLocationRelativeTo(null);

		// 关闭是退出程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// 添东西
		// frame.getContentPane().add(new JButton("北"), BorderLayout.NORTH);
		// frame.getContentPane().add(new JButton("东"), BorderLayout.EAST);
		// JLabel label = new JLabel("标签");
		// frame.getContentPane().add(label, BorderLayout.SOUTH);
		// frame.getContentPane().add(new JButton("西"), BorderLayout.WEST);

		frame.getContentPane().add(new GamePanel(), BorderLayout.CENTER);

		frame.setVisible(true);

	}
}
