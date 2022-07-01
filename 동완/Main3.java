package 동완;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;
import org.omg.PortableServer.AdapterActivator;
import org.omg.PortableServer.POA;

import javax.swing.JButton;

public class Main3 extends JFrame {
	private JCheckBox[] ball;
	private JCheckBox[][] Rball = new JCheckBox[5][6];
	private JPanel[] ballPnl = new JPanel[5];
	private JLabel[] lblA = new JLabel[5];
	private JLabel[] lblWin = new JLabel[5];
	
	
	public Main3() {
		super("번호추첨");
		JPanel mainPnl = new JPanel();
		mainPnl.setBounds(0, 59, 994, 512);
		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);

		JPanel pnlBall = new JPanel();
		pnlBall.setBounds(25, 10, 647, 120);
		mainPnl.add(pnlBall);

		JPanel pnlBonus = new JPanel();
		pnlBonus.setBounds(692, 10, 272, 120);
		mainPnl.add(pnlBonus);
		pnlBonus.setLayout(null);

		JCheckBox bonus = new JCheckBox(); // 보너스 공
		bonus.setBounds(127, 0, 120, 110);
		bonus.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\big.png"));
		pnlBonus.add(bonus);

		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("휴먼엑스포", Font.PLAIN, 96));
		lblPlus.setBounds(12, 26, 64, 68);
		lblPlus.setHorizontalAlignment(JLabel.CENTER);
		pnlBonus.add(lblPlus);

		JLabel lblreal = new JLabel(); // 퇴사짤 레이블
		lblreal.setBounds(45, 140, 460, 312);
		lblreal.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\out.gif"));
		mainPnl.add(lblreal);

//-------------------------------------------------------------------------------------------------------- 오른쪽 부분		
		JPanel pnlRight = new JPanel(); // 오른쪽 패널
		pnlRight.setBounds(509, 120, 455, 350);
		mainPnl.add(pnlRight);
//		pnlRight.setLayout(null);
		BoxLayout box = new BoxLayout(pnlRight, BoxLayout.Y_AXIS);
		pnlRight.setLayout(box);

		// 오른쪽 큰 패널 안에 가로 작은패널
		JPanel pnlRball = new JPanel();
		pnlRball.setBounds(0, 0, 453, 70);
		pnlRight.add(pnlRball);
		pnlRball.setLayout(null);
		
		JButton btnClick = new JButton();
		btnClick.setBounds(163, 465, 210, 35);
		mainPnl.add(btnClick);
		btnClick.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\win.png"));
		btnClick.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnClick.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\win3.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnClick.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\win.png"));
			}
		});
		btnClick.setBorderPainted(false);
//-----------------------------------------------------------------------------------------------추첨 확인쪽 공 만들기
		// 선택 완료쪽 빈공
		for (int i = 0; i < Rball.length; i++) {
			ballPnl[i] = new JPanel();
			lblA[i] = new JLabel("A   ");
			ballPnl[i].add(lblA[i]);
			lblWin[i] = new JLabel("1등    ");
			ballPnl[i].add(lblWin[i]);
			for (int j = 0; j < 6; j++) {
				Rball[i][j] = new JCheckBox();
				ballPnl[i].add(Rball[i][j]);
				Rball[i][j].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\only.png"));
			}
			pnlRight.add(ballPnl[i]);
		}
		
//------------------------------------------------------------------------------------------------ 메인 레이블 타이틀	


		JLabel mainlbl = new JLabel("번호추첨");
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setBounds(0, 0, 994, 61);
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setOpaque(true);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		getContentPane().add(mainlbl);
		setFocusable(true);
// ----------------------------------------------------------------------------------------------------- 큰공 6개 넣는거
		ball = new JCheckBox[6]; // 추첨 공 6개
		for (int i = 0; i < ball.length; i++) {
			ball[i] = new JCheckBox();
			pnlBall.add(ball[i]);
			ball[i].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\big.png"));
		}
// -----------------------------------------------------------------------------------------------------------
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		getContentPane().setLayout(null);

		JButton btnHome = new JButton();
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭중");
			}
		});
		btnHome.setBounds(30, 5, 50, 50);
		mainlbl.add(btnHome);
		btnHome.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\home2.png"));
		btnHome.setBorderPainted(false);

	}

	public static void main(String[] args) {
		new Main3().setVisible(true);
	}
}
