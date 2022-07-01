import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	private JButton mainBuyButton;
	private JButton mainNumberLotteryButton;
	private JButton mainWinnerHistoryButton;
	private JButton mainCheckWinButton;
	private BuyFrame buyFrame;

	public MainFrame() {
		super("로또 추첨");
		Toolkit kit = Toolkit.getDefaultToolkit();
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
		mainTitlePanel.setBounds(0, 0, 984, 67);
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
		mainImagePanel.setBounds(0, 65, 984, 352);
		mainImagePanel.setLayout(null);
		wrapPanel.add(mainImagePanel);

		URL mainImageUrl = MainFrame.class.getClassLoader().getResource("main_01.png");
		JLabel mainImageLabel = new JLabel(new ImageIcon(kit.getImage(mainImageUrl)));
		mainImageLabel.setBounds(0, 0, 984, 352);
		mainImagePanel.add(mainImageLabel);

		// 화면이 나오는 속도 저하 이슈
//		1. getScaledInstance 메소드로 image 해상도를 줄이는 작업이 시간소요가 많이 되어 이 작업을 없애고
//		이미지 자체 해상도를 줄이는 방법으로 해결

//		2. 기존 URL 방법보다 버퍼에 미리 image 파일들을 저장시켜서 그 파일들을 불러오는 방법으로 속도 개선

//		ImageIcon mainImageIcon = new ImageIcon("resources/main_01.png");
//		Image mainImage = mainImageIcon.getImage().getScaledInstance(1000, 350, Image.SCALE_SMOOTH);
//		ImageIcon changeMainIcon = new ImageIcon(mainImage);
//		mainImageLabel.setIcon(changeMainIcon);
		//

		// 메인 화면 구매하기 버튼 구현
		mainBuyButton = new JButton();
		mainBuyButton.setBounds(0, 416, 250, 145);
		URL mainBuyUrl = MainFrame.class.getClassLoader().getResource("mainButton_01.png");
		mainBuyButton.setIcon(new ImageIcon(kit.getImage(mainBuyUrl)));
		wrapPanel.add(mainBuyButton);

		// 메인 화면 번호추첨 버튼 구현
		mainNumberLotteryButton = new JButton();
		mainNumberLotteryButton.setBounds(250, 416, 250, 145);
		URL mainNumberLotteryUrl = MainFrame.class.getClassLoader().getResource("mainButton_02.png");
		mainNumberLotteryButton.setIcon(new ImageIcon(kit.getImage(mainNumberLotteryUrl)));
		wrapPanel.add(mainNumberLotteryButton);

		// 메인 화면 역대당첨번호 버튼 구현
		mainWinnerHistoryButton = new JButton();
		mainWinnerHistoryButton.setBounds(500, 416, 250, 146);
		URL mainWinnerHistoryUrl = MainFrame.class.getClassLoader().getResource("mainButton_03.png");
		mainWinnerHistoryButton.setIcon(new ImageIcon(kit.getImage(mainWinnerHistoryUrl)));
		wrapPanel.add(mainWinnerHistoryButton);

		// 메인 화면 당첨확인 버튼 구현
		mainCheckWinButton = new JButton();
		mainCheckWinButton.setBounds(734, 416, 250, 145);
		URL mainCheckWinUrl = MainFrame.class.getClassLoader().getResource("mainButton_04.png");
		mainCheckWinButton.setIcon(new ImageIcon(kit.getImage(mainCheckWinUrl)));
		wrapPanel.add(mainCheckWinButton);

		mainBuyButton.addActionListener(this);
		mainNumberLotteryButton.addActionListener(this);
		mainWinnerHistoryButton.addActionListener(this);
		mainCheckWinButton.addActionListener(this);
	}

	// 버튼 클릭할 때 마다 동작하는 메소드 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == mainBuyButton) {
//			ToDo : 구매하기 버튼을 누르면 구매하기 창으로 이동
			buyFrame.setVisible(true);
		} else if (click == mainNumberLotteryButton) {
//			ToDo : 번호추첨 버튼을 누르면 번호추첨 창으로 이동

		} else if (click == mainWinnerHistoryButton) {
//			ToDo : 역대당첨번호 버튼을 누르면 역대당첨번호 창으로 이동

		} else if (click == mainCheckWinButton) {
//			ToDo : 당첨확인 버튼을 누르면 당첨확인 창으로 이동

		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
