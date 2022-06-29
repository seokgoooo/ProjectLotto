package 동완;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main2 extends JFrame {
	public Main2() {
		super("추첨프로그램");
		
		JFrame frm = new JFrame();
		JPanel mainPnl = new JPanel();
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBounds(43, 29, 161, 204);
		JPanel pnlRight = new JPanel();
		pnlRight.setBounds(224, 29, 198, 204);
		JLabel mainlbl = new JLabel();
		mainlbl.setBounds(204, 10, 0, 0);
		
//		ImageIcon icon = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\back-01.png");
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(995, 580, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		
		GridLayout grid = new GridLayout(9, 5);
		pnlLeft.setLayout(grid);   // 패널안에서 
		
		JCheckBox[] cb = new JCheckBox[45];

		for (int i = 0; i < cb.length; i++) {
			cb[i] = new JCheckBox();
		    pnlLeft.add(cb[i]);
			ImageIcon icon1 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\검-" + (i + 1) + ".png");
			Image img1 = icon1.getImage();
			Image changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon1 = new ImageIcon(changeImg1);
			cb[i].setIcon(changeIcon1);
		}
		//mainPnl.setLayout(null);
		
		
				
		
		add(mainPnl);
		mainPnl.add(pnlLeft);
		mainPnl.add(pnlRight);
		mainPnl.add(mainlbl);
		
		
		setSize(1000,600);
		
	}

	public static void main(String[] args) {
		new Main2().setVisible(true);
	}
}
