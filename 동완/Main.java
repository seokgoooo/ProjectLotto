package 동완;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Main extends JFrame{
	public Main() {
		super("로또 추첨");
		JPanel mainPnl = new JPanel();
		
		
		getContentPane().add(mainPnl);
		
		JButton btnBuy = new JButton();
		btnBuy.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-01.png"));
		btnBuy.setBounds(0, 416, 245, 155);
		mainPnl.add(btnBuy);
		mainPnl.setLayout(null);
		//btnBuy.setVerticalTextPosition(SwingConstants.BOTTOM);
//		ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-01.png");
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(247, 155, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);
//		btnBuy.setIcon(changeIcon);
		
		JButton btnNum = new JButton();
		btnNum.setBounds(250, 416, 245, 155);
		btnNum.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-02.png"));
		mainPnl.add(btnNum);
//		ImageIcon icon2 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-02.png");
//		Image img2 = icon2.getImage();
//		Image changeImg2 = img2.getScaledInstance(247, 155, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		
		JButton btnWin = new JButton();
		btnWin.setBounds(500, 416, 245, 155);
		btnWin.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-03.png"));
		mainPnl.add(btnWin);
//		ImageIcon icon3 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-03.png");
//		Image img3 = icon3.getImage();
//		Image changeImg3 = img3.getScaledInstance(247, 155, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon3 = new ImageIcon(changeImg3);
		
		JButton btnCheck = new JButton();
		btnCheck.setBounds(750, 416, 245, 155);
		btnCheck.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-04.png"));
		mainPnl.add(btnCheck);
		btnCheck.setEnabled(false);
//		ImageIcon icon4 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-04.png");
//		Image img4 = icon4.getImage();
//		Image changeImg4 = img4.getScaledInstance(247, 155, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon4 = new ImageIcon(changeImg4);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 1000, 67);
		mainPnl.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(Color.white);
		
		JLabel mainTitle = new JLabel("퇴사를 꿈꾸며 Let's Lotto!");
		mainTitle.setBounds(292, 16, 424, 34);
		titlePanel.add(mainTitle);
		mainTitle.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		
		mainTitle.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		JPanel imagePnl = new JPanel();
		imagePnl.setBounds(0, 65, 994, 352);
		mainPnl.add(imagePnl);
		imagePnl.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\Main-01.png"));
		lblNewLabel.setBounds(0, 0, 994, 352);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imagePnl.add(lblNewLabel);
//		ImageIcon icon5 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\Main-01.png");
//		Image img5 = icon5.getImage();
//		Image changeImg5 = img5.getScaledInstance(1000, 445, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon5 = new ImageIcon(changeImg5);
//		lblNewLabel.setIcon(changeIcon5);
		
		
		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
