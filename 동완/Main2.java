package 동완;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class Main2 extends JFrame implements ActionListener {
	
	private JCheckBox[] cbs;
	private JCheckBox[] cbnews;
	private JCheckBox[] cbolds;
	private String[] setNum = { "1", "2", "3", "4", "5" };
	private JCheckBox[] ball;
	
	
	
	public Main2() {
		super("추첨프로그램");

		JFrame frm = new JFrame();
		JPanel mainPnl = new JPanel();

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBounds(222, 144, 242, 373);
		getContentPane().setBackground(Color.white);
		JPanel pnlRight = new JPanel();
		pnlRight.setBounds(505, 107, 467, 444);
		JLabel mainlbl = new JLabel();
		mainlbl.setBounds(0, 0, 995, 580);
		mainPnl.setLayout(null);
		
		JButton btn1 = new JButton(); // 수동선택 버튼
		btn1.setBounds(45, 107, 138, 136);
		btn1.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\btn-06.png"));
		mainPnl.add(btn1);
		btn1.setEnabled(false);
		
		
		JButton btn2 = new JButton(); // 혼합선택 버튼
		btn2.setBounds(45, 245, 138, 136);
		btn2.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\btn-01.png"));
		mainPnl.add(btn2);
		
		JButton btn3 = new JButton(); // 자동선택 버튼
		btn3.setBounds(45, 383, 138, 136);
		btn3.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\btn-02.png"));
		mainPnl.add(btn3);
		
		JComboBox comboBox = new JComboBox(setNum);  // 수량 정하는 콤보박스
		comboBox.setBounds(45, 530, 40, 20);
		mainPnl.add(comboBox);
		
		JButton btnEnd = new JButton();  // 확인버튼
		btnEnd.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\btn-04.png"));
		btnEnd.setBounds(90, 530, 93, 20);
		mainPnl.add(btnEnd);
		
		JButton btnReset = new JButton();  // 초기화 버튼
		btnReset.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\btn-05.png"));
		btnReset.setBounds(280, 522, 125, 30);
		mainPnl.add(btnReset);

//		ImageIcon icon = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\back-01.png");  // 배경이미지
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(995, 580, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);

		mainlbl.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\back-01.png"));
		

		GridLayout grid = new GridLayout(9, 5);
		pnlLeft.setLayout(grid); // 패널안에서 정렬

		cbs = new JCheckBox[45];  // 색깔없는 기본

		for (int i = 0; i < cbs.length; i++) {
			cbs[i] = new JCheckBox();
			pnlLeft.add(cbs[i]);
			ImageIcon icon1 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\검-" + (i + 1) + ".png");
			Image img1 = icon1.getImage();
			Image changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon1 = new ImageIcon(changeImg1);
			cbs[i].setIcon(changeIcon1);
			cbs[i].addActionListener(this);
		}

		cbnews = new JCheckBox[45]; // 색깔있는 그림

		for (int i = 0; i < cbnews.length; i++) {
			cbnews[i] = new JCheckBox();

			ImageIcon icon2 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\번호작은거-" + (i + 1) + ".png");
			Image img2 = icon2.getImage();
			Image changeImg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon2 = new ImageIcon(changeImg2);
			cbnews[i].setIcon(changeIcon2);
		}

		cbolds = new JCheckBox[45]; // 원래 색으로 돌아오기 위한 그림

		for (int i = 0; i < cbnews.length; i++) {
			cbolds[i] = new JCheckBox();

			ImageIcon icon2 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\검-" + (i + 1) + ".png");
			Image img2 = icon2.getImage();
			Image changeImg2 = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon2 = new ImageIcon(changeImg2);
			cbolds[i].setIcon(changeIcon2);
		}

//		cb[0].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(cb[0].isSelected());
//				if (cb[0].isSelected()) {
//					cb[0].setIcon(cbnew[0].getIcon());
//				} else {
//					cb[0].setIcon(cb[0].getIcon());
//				}
//			}
//		});

		cbs[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (cbs[0].isSelected()) {
					cbs[0].setIcon(cbnews[0].getIcon());
				} else {
					cbs[0].setIcon(cbolds[0].getIcon());
				}
			}
		});
		
		JLabel lblinput = new JLabel("선택금액");
		lblinput.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblinput.setBounds(222, 111, 242, 23);
		lblinput.setBackground(Color.darkGray);
		mainPnl.add(lblinput);
		lblinput.setHorizontalAlignment(JLabel.CENTER);
		
		

		
		
		
		getContentPane().add(mainPnl);
		mainPnl.add(pnlLeft);
		mainPnl.add(pnlRight);
		pnlRight.setLayout(null);
		
		JLabel lblCheck = new JLabel("선택번호 확인");
		lblCheck.setBounds(25, 15, 120, 30);
		lblCheck.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		pnlRight.add(lblCheck);
		
		JButton btnRestet2 = new JButton();
		btnRestet2.setBounds(390, 15, 60, 30);
		btnRestet2.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\btn-07.png"));
		pnlRight.add(btnRestet2);
		
		JPanel pnl1 = new JPanel();
		pnl1.setBounds(0, 67, 467, 64);
		pnlRight.add(pnl1);
		pnl1.setLayout(null);
		
	
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblA.setBounds(25, 26, 19, 15);
		pnl1.add(lblA);
		
		ball = new JCheckBox[30];
		for(int i = 0; i < ball.length; i++) {
			ball[i] = new JCheckBox();
			pnl1.add(ball[i]);
		}
		
		
		JButton btnA = new JButton("번호복사");
		btnA.setBounds(375, 22, 75, 23);
		btnA.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnA.setBackground(Color.white);
		pnl1.add(btnA);
		
		JButton btnAdelet = new JButton("삭제");
		btnAdelet.setBounds(310, 22, 60, 23);
		btnAdelet.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnAdelet.setBackground(Color.white);
		pnl1.add(btnAdelet);
		
		JButton btnAchange = new JButton("수정");
		btnAchange.setBounds(245, 22, 60, 23);
		btnAchange.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnAchange.setBackground(Color.white);
		pnl1.add(btnAchange);
		
		JPanel pnl2 = new JPanel();
		pnl2.setBounds(0, 130, 467, 64);
		pnlRight.add(pnl2);
		pnl2.setLayout(null);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblB.setBounds(25, 27, 18, 15);
		pnl2.add(lblB);
		
		JButton btnB = new JButton("번호복사");
		btnB.setBounds(375, 19, 75, 23);
		btnB.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnB.setBackground(Color.white);
		pnl2.add(btnB);
		
		JButton btnBdelet = new JButton("삭제");
		btnBdelet.setBounds(310, 19, 60, 23);
		btnBdelet.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnBdelet.setBackground(Color.white);
		pnl2.add(btnBdelet);
		
		JButton btnBchange = new JButton("수정");
		btnBchange.setBounds(245, 18, 60, 23);
		btnBchange.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnBchange.setBackground(Color.white);
		pnl2.add(btnBchange);
		
		JPanel pnlball = new JPanel();
		pnlball.setBounds(55, -63, 178, 317);
		pnl2.add(pnlball);
					
		JPanel pnl3 = new JPanel();
		pnl3.setBounds(0, 193, 467, 64);
		pnlRight.add(pnl3);
		pnl3.setLayout(null);
		
		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblC.setBounds(25, 27, 19, 15);
		pnl3.add(lblC);
		
		JButton btnC = new JButton("번호복사");
		btnC.setBounds(375, 23, 75, 23);
		btnC.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnC.setBackground(Color.white);
		pnl3.add(btnC);
		
		JButton btnCdelet = new JButton("삭제");
		btnCdelet.setBounds(310, 23, 60, 23);
		btnCdelet.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnCdelet.setBackground(Color.white);
		pnl3.add(btnCdelet);
		
		JButton btnCchange = new JButton("수정");
		btnCchange.setBounds(245, 23, 60, 23);
		btnCchange.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnCchange.setBackground(Color.white);
		pnl3.add(btnCchange);
		
		JPanel pnl4 = new JPanel();
		pnl4.setBounds(0, 257, 467, 64);
		pnlRight.add(pnl4);
		pnl4.setLayout(null);
		
		JLabel lblD = new JLabel("D");
		lblD.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblD.setBounds(25, 23, 18, 15);
		pnl4.add(lblD);
		
		JButton btnD = new JButton("번호복사");
		btnD.setBounds(375, 19, 75, 23);
		btnD.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnD.setBackground(Color.white);
		pnl4.add(btnD);
		
		JButton btnDdelet = new JButton("삭제");
		btnDdelet.setBounds(310, 19, 60, 23);
		btnDdelet.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnDdelet.setBackground(Color.white);
		pnl4.add(btnDdelet);
		
		JButton btnDchange = new JButton("수정");
		btnDchange.setBounds(245, 19, 60, 23);
		btnDchange.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnDchange.setBackground(Color.white);
		pnl4.add(btnDchange);
		
		JPanel pnl5 = new JPanel();
		pnl5.setBounds(0, 320, 467, 64);
		pnlRight.add(pnl5);
		pnl5.setLayout(null);
		
		JLabel lblE = new JLabel("E");
		lblE.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		lblE.setBounds(25, 25, 17, 15);
		pnl5.add(lblE);
		
		JButton btnE = new JButton("번호복사");
		btnE.setBounds(375, 21, 75, 23);
		btnE.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnE.setBackground(Color.white);
		pnl5.add(btnE);
		
		JButton btnEdelet = new JButton("삭제");
		btnEdelet.setBounds(310, 21, 60, 23);
		btnEdelet.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnEdelet.setBackground(Color.white);
		pnl5.add(btnEdelet);
		
		JButton btnEchange = new JButton("수정");
		btnEchange.setBounds(245, 21, 60, 23);
		btnEchange.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
		btnEchange.setBackground(Color.white);
		pnl5.add(btnEchange);
		
		JLabel lbltotal = new JLabel("결제금액 : ");
		lbltotal.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbltotal.setBounds(158, 388, 120, 50);
		pnlRight.add(lbltotal);
		mainPnl.add(mainlbl);
		
		JLabel lbltotal2 = new JLabel("2000원");
		lbltotal2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lbltotal2.setBounds(260, 388, 120, 50);
		pnlRight.add(lbltotal2);
		
		JButton btnBuy = new JButton();
		btnBuy.setBounds(340, 390, 120, 50);
		btnBuy.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼\\buy-07.png"));
		pnlRight.add(btnBuy);
		mainPnl.add(mainlbl);
		
		
		// 수정중
		setResizable(false);
		setSize(1000, 600);

						
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < cbs.length; i++) {
			if (e.getSource() == cbs[i]) {
//				System.out.println(cbs[i]); //확인용
				if (cbs[i].isSelected()) {
					cbs[i].setIcon(cbnews[i].getIcon());
				} else {
					cbs[i].setIcon(cbolds[i].getIcon());
				}
			}
		}
	}
	public static void main(String[] args) {
		new Main2().setVisible(true);
	}
}
