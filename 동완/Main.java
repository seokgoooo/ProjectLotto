import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public MainFrame() {
		super("로또 추첨");
		Toolkit kit = Toolkit.getDefaultToolkit();
		setSize(1000, 600);
		setLocationRelativeTo(null);

		JPanel mainWrapPanel = new JPanel();
		mainWrapPanel.setLayout(null);
		getContentPane().add(mainWrapPanel);

		// 메인 화면 타이틀 문구
		JPanel mainTitlePanel = new JPanel();
		mainTitlePanel.setBounds(0, 0, 984, 67);
		mainTitlePanel.setLayout(null);
		mainTitlePanel.setBackground(Color.WHITE);
		mainWrapPanel.add(mainTitlePanel);

		JLabel mainTitleText = new JLabel("퇴사를 꿈꾸며 Let's Lotto!");
		mainTitleText.setBounds(292, 16, 424, 34);
		mainTitleText.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		mainTitleText.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		mainTitlePanel.add(mainTitleText);
		//

		// 메인 화면 이미지 GUI 구현
		JPanel mainImagePanel = new JPanel();
		mainImagePanel.setBounds(0, 65, 984, 352);
		mainWrapPanel.add(mainImagePanel);
		mainImagePanel.setLayout(null);

		JLabel mainImageLabel = new JLabel();
		mainImageLabel.setBounds(0, 0, 984, 352);
		mainImagePanel.add(mainImageLabel);
		URL mainImageUrl = MainFrame.class.getClassLoader().getResource("main_01.png");
		ImageIcon mainImageIcon = new ImageIcon(kit.getImage(mainImageUrl));
		Image mainImage = mainImageIcon.getImage();
		Image changeMainImage = mainImage.getScaledInstance(1000, 350, Image.SCALE_SMOOTH);
		ImageIcon changeMainIcon = new ImageIcon(changeMainImage);
		mainImageLabel.setIcon(changeMainIcon);
		//

		// 메인 화면 구매하기 버튼 구현
		JButton mainBuyButton = new JButton();
		mainBuyButton.setBounds(0, 416, 250, 145);
		URL mainBuyUrl = MainFrame.class.getClassLoader().getResource("mainButton_01.png");
		ImageIcon mainBuyIcon = new ImageIcon(kit.getImage(mainBuyUrl));
		Image mainBuyImage = mainBuyIcon.getImage();
		Image changeMainBuyImage = mainBuyImage.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeMainBuyIcon = new ImageIcon(changeMainBuyImage);
		mainBuyButton.setIcon(changeMainBuyIcon);
		mainWrapPanel.add(mainBuyButton);

		// 메인 화면 번호추첨 버튼 구현
		JButton mainNumberLotteryButton = new JButton();
		mainNumberLotteryButton.setBounds(250, 416, 250, 145);
		URL mainNumberLotteryUrl = MainFrame.class.getClassLoader().getResource("mainButton_02.png");
		ImageIcon mainNumberLotteryIcon = new ImageIcon(kit.getImage(mainNumberLotteryUrl));
		Image mainNumberLotteryImage = mainNumberLotteryIcon.getImage();
		Image changeMainNumberLotteryImage = mainNumberLotteryImage.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeMainNumberLotteryIcon = new ImageIcon(changeMainNumberLotteryImage);
		mainNumberLotteryButton.setIcon(changeMainNumberLotteryIcon);
		mainWrapPanel.add(mainNumberLotteryButton);

		// 메인 화면 역대당첨번호 버튼 구현
		JButton mainWinnerHistoryButton = new JButton();
		mainWinnerHistoryButton.setBounds(500, 416, 250, 146);
		URL mainWinnerHistoryUrl = MainFrame.class.getClassLoader().getResource("mainButton_03.png");
		ImageIcon mainWinnerHistoryIcon = new ImageIcon(kit.getImage(mainWinnerHistoryUrl));
		Image mainWinnerHistoryImage = mainWinnerHistoryIcon.getImage();
		Image changeMainWinnerHistoryImage = mainWinnerHistoryImage.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeMainWinnerHistoryIcon = new ImageIcon(changeMainWinnerHistoryImage);
		mainWinnerHistoryButton.setIcon(changeMainWinnerHistoryIcon);
		mainWrapPanel.add(mainWinnerHistoryButton);

		// 메인 화면 당첨확인 버튼 구현
		JButton mainCheckWinButton = new JButton();
		mainCheckWinButton.setBounds(734, 416, 250, 145);
		URL mainCheckWinUrl = MainFrame.class.getClassLoader().getResource("mainButton_04.png");
		ImageIcon mainCheckWinIcon = new ImageIcon(kit.getImage(mainCheckWinUrl));
		Image mainCheckWinImage = mainCheckWinIcon.getImage();
		Image changeMainCheckWinImage = mainCheckWinImage.getScaledInstance(250, 145, Image.SCALE_SMOOTH);
		ImageIcon changeMainCheckWinIcon = new ImageIcon(changeMainCheckWinImage);
		mainCheckWinButton.setIcon(changeMainCheckWinIcon);
		mainWrapPanel.add(mainCheckWinButton);

		mainBuyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ToDo : 구매하기 버튼을 누르면 구매하기 창으로 이동
//				new BuyFrame().setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
