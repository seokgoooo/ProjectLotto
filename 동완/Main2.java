package 동완;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main2 extends JFrame implements ActionListener {
	
	private JCheckBox[] cbs;
	private JCheckBox[] cbnews;
	private JCheckBox[] cbolds;

	public Main2() {
		super("추첨프로그램");

		JFrame frm = new JFrame();
		JPanel mainPnl = new JPanel();

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBounds(222, 117, 242, 426);
		getContentPane().setBackground(Color.white);
		JPanel pnlRight = new JPanel();
		pnlRight.setBounds(505, 103, 467, 448);
		JLabel mainlbl = new JLabel();

		ImageIcon icon = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\back-01.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(995, 580, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);

		mainlbl.setIcon(new ImageIcon(changeImg));
		mainlbl.setBounds(0, 0, 995, 580);
		

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

		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);
		mainPnl.add(pnlLeft);
		mainPnl.add(pnlRight);
		mainPnl.add(mainlbl);
		setResizable(false);
		setSize(1000, 600);

	}

	public static void main(String[] args) {
		new Main2().setVisible(true);
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

}
