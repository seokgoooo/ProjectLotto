import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements ActionListener {
	private JButton buyButton;
	private JButton numberLotteryButton;
	private JButton winnerHistoryButton;
	private JButton checkWinButton;
	private BuyFrame buyFrame;

	public MainFrame() {
		super("로또 추첨");
		buyFrame = new BuyFrame();

		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 전체 담는 패널
		JPanel wrapPanel = new JPanel();
		wrapPanel.setLayout(null);
		add(wrapPanel);

		// 메인 화면 타이틀 문구
		JPanel mainTitlePanel = new JPanel();
		mainTitlePanel.setBounds(0, 0, 1000, 67);
		mainTitlePanel.setLayout(null);
		mainTitlePanel.setBackground(Color.WHITE);
		wrapPanel.add(mainTitlePanel);

		JLabel mainTitleText = new JLabel("퇴사를 꿈꾸며 Let's Lotto!");
		mainTitleText.setBounds(292, 16, 424, 34);
		mainTitleText.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		mainTitleText.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		mainTitlePanel.add(mainTitleText);
		//

		// 메인 화면 이미지 GUI 구현
		JPanel mainImagePanel = new JPanel();
		mainImagePanel.setBounds(0, 65, 994, 352);
		mainImagePanel.setLayout(null);
		wrapPanel.add(mainImagePanel);

		URL mainImageUrl = MainFrame.class.getClassLoader().getResource("main_01.png");
		JLabel mainImageLabel = new JLabel(new ImageIcon(mainImageUrl));
		mainImageLabel.setBounds(0, 0, 994, 352);
		mainImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainImagePanel.add(mainImageLabel);

		// 화면이 나오는 속도 저하 이슈
//		1. getScaledInstance 메소드로 image 해상도를 줄이는 작업이
//		프로그램 실행시키는데 있어서 시간소요가 많이 되어 이 작업을 없애고
//		이미지 자체 해상도를 줄이는 방법으로 해결

//		ImageIcon mainImageIcon = new ImageIcon("resources/main_01.png");
//		Image mainImage = mainImageIcon.getImage().getScaledInstance(1000, 350, Image.SCALE_SMOOTH);
//		ImageIcon changeMainIcon = new ImageIcon(mainImage);
//		mainImageLabel.setIcon(changeMainIcon);
		//

		// 메인 화면 구매하기 버튼 구현
		buyButton = new JButton();
		buyButton.setBounds(0, 416, 245, 155);
		URL mainBuyUrl = MainFrame.class.getClassLoader().getResource("mainButton_01.png");
		buyButton.setIcon(new ImageIcon(mainBuyUrl));
		wrapPanel.add(buyButton);

		// 메인 화면 번호추첨 버튼 구현
		numberLotteryButton = new JButton();
		numberLotteryButton.setBounds(250, 416, 245, 155);
		URL mainNumberLotteryUrl = MainFrame.class.getClassLoader().getResource("mainButton_02.png");
		numberLotteryButton.setIcon(new ImageIcon(mainNumberLotteryUrl));
		wrapPanel.add(numberLotteryButton);

		// 메인 화면 역대당첨번호 버튼 구현
		winnerHistoryButton = new JButton();
		winnerHistoryButton.setBounds(500, 416, 245, 155);
		URL mainWinnerHistoryUrl = MainFrame.class.getClassLoader().getResource("mainButton_03.png");
		winnerHistoryButton.setIcon(new ImageIcon(mainWinnerHistoryUrl));
		wrapPanel.add(winnerHistoryButton);

		// 메인 화면 당첨확인 버튼 구현
		checkWinButton = new JButton();
		checkWinButton.setBounds(750, 416, 245, 155);
		URL mainCheckWinUrl = MainFrame.class.getClassLoader().getResource("mainButton_04.png");
		checkWinButton.setIcon(new ImageIcon(mainCheckWinUrl));
		wrapPanel.add(checkWinButton);

		buyButton.addActionListener(this);
		numberLotteryButton.addActionListener(this);
		winnerHistoryButton.addActionListener(this);
		checkWinButton.addActionListener(this);
	}

	// 버튼 클릭할 때 마다 동작하는 메소드 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == buyButton) {
//			ToDo : 구매하기 버튼을 누르면 구매하기 창으로 이동
			buyFrame.setVisible(true);
		} else if (click == numberLotteryButton) {
//			ToDo : 번호추첨 버튼을 누르면 번호추첨 창으로 이동

		} else if (click == winnerHistoryButton) {
//			ToDo : 역대당첨번호 버튼을 누르면 역대당첨번호 창으로 이동

		} else if (click == checkWinButton) {
//			ToDo : 당첨확인 버튼을 누르면 당첨확인 창으로 이동

		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
