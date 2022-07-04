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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private List<Integer> listFortyFive = new ArrayList<>();
	private List<Integer> lottoList = new ArrayList<>();
	private Integer bonusNumber = 0;
	private Random random = new Random();
	private boolean raffleEnd = false;
	private List<List<Integer>> purchaseList = new ArrayList<>();

	public Main3() {
		super("번호추첨");

		for (int i = 0; i < 45; i++) {
			listFortyFive.add(i + 1);
		}

		for (int i = 0; i < 10; i++) {
			purchaseList.add(lotto());
		}
		System.out.println(purchaseList);

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
		URL bigImgUrl = Main3.class.getClassLoader().getResource("resources/big.png");
		bonus.setIcon(new ImageIcon(bigImgUrl));
		pnlBonus.add(bonus);

		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("휴먼엑스포", Font.PLAIN, 96));
		lblPlus.setBounds(12, 26, 64, 68);
		lblPlus.setHorizontalAlignment(JLabel.CENTER);
		pnlBonus.add(lblPlus);

		JLabel lblreal = new JLabel(); // 퇴사짤 레이블
		lblreal.setBounds(45, 140, 460, 312);
		URL outImgUrl = Main3.class.getClassLoader().getResource("resources/out.gif");
		lblreal.setIcon(new ImageIcon(outImgUrl));
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
// ----------------------------------------------------------------------------------------------------- 큰공 6개 넣는거
		ball = new JCheckBox[6]; // 추첨 공 6개
		for (int i = 0; i < ball.length; i++) {
			ball[i] = new JCheckBox();
			pnlBall.add(ball[i]);
			ball[i].setIcon(new ImageIcon(bigImgUrl));
		}
// ----------------------------------------------------------------------------------------------------- 
		// 번호 추첨
		JButton btnClick = new JButton();
		btnClick.setBounds(163, 465, 210, 35);
		mainPnl.add(btnClick);
		URL winImgUrl = Main3.class.getClassLoader().getResource("resources/win.png");
		btnClick.setIcon(new ImageIcon(winImgUrl));
		URL win3ImgUrl = Main3.class.getClassLoader().getResource("resources/win3.png");
		btnClick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnClick.setIcon(new ImageIcon(win3ImgUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (raffleEnd) {
					JOptionPane.showMessageDialog(mainPnl, "추첨이 끝났습니다.");
				} else {
					btnClick.setIcon(new ImageIcon(winImgUrl));
					int number = random.nextInt(listFortyFive.size());
					if (lottoList.size() == 6) {
						bonusNumber = number;
						bonus.setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						raffleEnd = true;
					} else {

//					ball[lottoList.size()].setText(String.valueOf(listFortyFive.get(number)));
						ball[lottoList.size()].setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						lottoList.add(listFortyFive.get(number));
						listFortyFive.remove(listFortyFive.get(number));
					}
				}
			}
		});
		btnClick.setBorderPainted(false);

//-----------------------------------------------------------------------------------------------추첨 확인쪽 공 만들기
		URL onlyImgUrl = Main3.class.getClassLoader().getResource("resources/only.png");
		// 선택 완료쪽 빈공
		for (int i = 0; i < Rball.length; i++) {
			ballPnl[i] = new JPanel();
			char c = (char) ('A' + i);
			lblA[i] = new JLabel(String.valueOf(c) + "    ");
			ballPnl[i].add(lblA[i]);
			lblWin[i] = new JLabel("확인    ");
			ballPnl[i].add(lblWin[i]);
			for (int j = 0; j < 6; j++) {
				Rball[i][j] = new JCheckBox();
				ballPnl[i].add(Rball[i][j]);
				Rball[i][j].setIcon(new ImageIcon(getBlackNumber(purchaseList.get(i).get(j))));
			}
//			lblWin[0].setText("1  등    ");
			pnlRight.add(ballPnl[i]);
		}

		URL me1ImgUrl = Main3.class.getClassLoader().getResource("resources/me1.png");
		URL me2ImgUrl = Main3.class.getClassLoader().getResource("resources/me2.png");
		JButton confirmClick = new JButton();
		confirmClick.setBounds(650, 465, 210, 35);
		mainPnl.add(confirmClick);
		confirmClick.setIcon(new ImageIcon(me1ImgUrl));
		confirmClick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				confirmClick.setIcon(new ImageIcon(me2ImgUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				confirmClick.setIcon(new ImageIcon(me1ImgUrl));
				for (int i = 0; i < Rball.length; i++) {
					System.out.println(equalCounts(lottoList, purchaseList.get(i)));

				}
			}
		});
		confirmClick.setBorderPainted(false);
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
		URL home2ImgUrl = Main3.class.getClassLoader().getResource("resources/home2.png");
		btnHome.setIcon(new ImageIcon(home2ImgUrl));
		btnHome.setBorderPainted(false);

	}

	public URL getBigColorNumber(int i) {
		URL url = Main3.class.getClassLoader().getResource("resources/BigColor/" + (i) + ".png");
		return url;
	}

	public URL getBlackNumber(int i) {
		URL url = Main3.class.getClassLoader().getResource("resources/NumberBlack/" + (i) + ".png");
		return url;
	}

	public URL getColorNumber(int i) {
		URL url = Main3.class.getClassLoader().getResource("resources/NumberColor/" + (i) + ".png");
		return url;
	}

	public List<Integer> lotto() {
		Set<Integer> set = new HashSet<>();
		random = new Random();

		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}

		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		return list;
	}

	public Integer equalCounts(List<Integer> list1, List<Integer> list2) {
		List<Integer> listDuplicate = new ArrayList<>();
		listDuplicate.addAll(list1);
		listDuplicate.retainAll(list2);

		return listDuplicate.size();
	}
//	// 교집합
//	Set<Integer> setDuplicate = new HashSet<>();
//	setDuplicate.addAll(setA);
//	setDuplicate.retainAll(setB);
//	System.out.println(setDuplicate);

	public static void main(String[] args) {
		new Main3().setVisible(true);
	}
}
