package lotto;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	public Main() {
		super("로또 추첨");
		JPanel mainPnl = new JPanel();
		
		
		getContentPane().add(mainPnl);
		
		JButton btnBuy = new JButton();
		btnBuy.setBounds(0, 416, 250, 145);
		//btnBuy.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-01.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		mainPnl.setLayout(null);
		btnBuy.setIcon(changeIcon);
		mainPnl.add(btnBuy);
		
		JButton btnNum = new JButton();
		btnNum.setBounds(246, 416, 250, 145);
		ImageIcon icon2 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-02.png");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		btnNum.setIcon(changeIcon2);
		mainPnl.add(btnNum);
		
		JButton btnWin = new JButton();
		btnWin.setBounds(496, 415, 250, 146);
		ImageIcon icon3 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-03.png");
		Image img3 = icon3.getImage();
		Image changeImg3 = img3.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeIcon3 = new ImageIcon(changeImg3);
		btnWin.setIcon(changeIcon3);
		mainPnl.add(btnWin);
		
		JButton btnCheck = new JButton();
		btnCheck.setBounds(734, 416, 250, 145);
		ImageIcon icon4 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\버튼-04.png");
		Image img4 = icon4.getImage();
		Image changeImg4 = img4.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeIcon4 = new ImageIcon(changeImg4);
		btnCheck.setIcon(changeIcon4);
		mainPnl.add(btnCheck);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 67);
		mainPnl.add(panel);
		panel.setLayout(null);
		
		JLabel mainTitle = new JLabel("메인 타이틀");
		mainTitle.setBounds(292, 16, 424, 34);
		panel.add(mainTitle);
		mainTitle.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		mainTitle.setHorizontalAlignment(JLabel.CENTER); //JLabel 가운데 정렬
		
		
		
		JPanel imagePnl = new JPanel();
		imagePnl.setBounds(0, 65, 984, 352);
		mainPnl.add(imagePnl);
		imagePnl.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 984, 352);
		imagePnl.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\로또 번호\\메인.jpg"));
		
		
		
		setSize(1000, 600);
		
		
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
