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

public class MainFrame extends JFrame implements ActionListener {
	private JButton buyBtn;
	private JButton lotteryBtn;
	private JButton historyBtn;
	private JButton checkBtn;
	private BuyFrame buy;
	private LotteryFrame lottery;
	private HistoryFrame history;
	private Consumer consumer = new Consumer();

	public Consumer getConsumer() {
		return consumer;
	}

	public MainFrame() {
		super("로또 추첨");
		buy = new BuyFrame();
		lottery = new LotteryFrame();
		history = new HistoryFrame();
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 전체 담는 패널
		JPanel wrapPnl = new JPanel();
		wrapPnl.setLayout(null);
		add(wrapPnl);

		// 메인 화면 타이틀 문구
		JPanel titlePnl = new JPanel();
		titlePnl.setBounds(0, 0, 1000, 67);
		titlePnl.setLayout(null);
		titlePnl.setBackground(Color.WHITE);
		wrapPnl.add(titlePnl);

		JLabel titleTxt = new JLabel("퇴사를 꿈꾸며 Let's Lotto!");
		titleTxt.setBounds(292, 16, 424, 34);
		titleTxt.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
//		mainTitleText.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데 정렬
		titlePnl.add(titleTxt);
		//

		// 메인 화면 이미지 GUI 구현
		JPanel imgPnl = new JPanel();
		imgPnl.setBounds(0, 65, 994, 352);
		imgPnl.setLayout(null);
		wrapPnl.add(imgPnl);

		URL imgUrl = MainFrame.class.getClassLoader().getResource("main_01.png");
		JLabel imgLbl = new JLabel(new ImageIcon(imgUrl));
		imgLbl.setBounds(0, 0, 994, 352);
//		mainImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgPnl.add(imgLbl);

		// 화면이 나오는 속도 저하 이슈
//		1. getScaledInstance 메소드로 image 해상도를 줄이는 작업이
//		프로그램 실행시키는데 있어서 시간소요가 많이 되어 이 작업을 없애고
//		이미지 자체 해상도를 줄이는 방법으로 해결

//		ImageIcon mainImageIcon = new ImageIcon("main_01.png");
//		Image mainImage = mainImageIcon.getImage().getScaledInstance(1000, 350, Image.SCALE_SMOOTH);
//		ImageIcon changeMainIcon = new ImageIcon(mainImage);
//		mainImageLabel.setIcon(changeMainIcon);
		//

		// 메인 화면 구매하기 버튼 구현
		buyBtn = new JButton();
		buyBtn.setBounds(0, 416, 245, 155);
		URL buyUrl = MainFrame.class.getClassLoader().getResource("mainButton_01.png");
		buyBtn.setIcon(new ImageIcon(buyUrl));
		wrapPnl.add(buyBtn);

		// 메인 화면 번호추첨 버튼 구현
		lotteryBtn = new JButton();
		lotteryBtn.setBounds(250, 416, 245, 155);
		URL lotteryUrl = MainFrame.class.getClassLoader().getResource("mainButton_02.png");
		lotteryBtn.setIcon(new ImageIcon(lotteryUrl));
		wrapPnl.add(lotteryBtn);

		// 메인 화면 역대당첨번호 버튼 구현
		historyBtn = new JButton();
		historyBtn.setBounds(500, 416, 245, 155);
		URL historyUrl = MainFrame.class.getClassLoader().getResource("mainButton_03.png");
		historyBtn.setIcon(new ImageIcon(historyUrl));
		wrapPnl.add(historyBtn);

		// 메인 화면 당첨확인 버튼 구현
		checkBtn = new JButton();
		checkBtn.setBounds(750, 416, 245, 155);
		URL checkUrl = MainFrame.class.getClassLoader().getResource("mainButton_04.png");
		checkBtn.setIcon(new ImageIcon(checkUrl));
		checkBtn.setEnabled(false);
		wrapPnl.add(checkBtn);

		buyBtn.addActionListener(this);
		lotteryBtn.addActionListener(this);
		historyBtn.addActionListener(this);
		checkBtn.addActionListener(this);
	}

	// 버튼 클릭할 때 마다 동작하는 메소드 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == buyBtn) {
//			ToDo : 구매하기 버튼을 누르면 구매하기 창으로 이동
			buy.setVisible(true);

		} else if (click == lotteryBtn) {
//			ToDo : 번호추첨 버튼을 누르면 번호추첨 창으로 이동
			consumer = buy.getConsumer(); // 저장된 값 불러오기
			lottery.setConsumer(consumer);
			System.out.println(lottery.getConsumer().getLottoList());
			buyBtn.setEnabled(false);
			lottery.setVisible(true);
		} else if (click == historyBtn) {
//			ToDo : 역대당첨번호 버튼을 누르면 역대당첨번호 창으로 이동
			history.setVisible(true);
		} else if (click == checkBtn) {
//			ToDo : 당첨확인 버튼을 누르면 당첨확인 창으로 이동

		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
