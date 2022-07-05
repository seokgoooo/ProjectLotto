import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	private JButton buyBtn;
	private JButton lotteryBtn;
	private JButton historyBtn;
	private JButton nextBtn;
	private BuyFrame buyFrame;
	private LotteryFrame lotteryFrame;
	private HistoryFrame historyFrame;
	private Consumer consumer = new Consumer();
	private JLabel countLotto;
	private int historyCount = 0;
	private int lotteryCount = 0;
	private JPanel wrapPnl;

	public MainFrame() {
		super("로또 추첨");
		buyFrame = new BuyFrame();
		lotteryFrame = new LotteryFrame();
		historyFrame = new HistoryFrame(); // 다음 카드 패널을 추가 할때 에러

		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 전체 담는 패널
		wrapPnl = new JPanel();
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

		countLotto = new JLabel(String.valueOf(historyFrame.getLottoList().size()) + "회차");
		countLotto.setBounds(800, 16, 424, 34);
		countLotto.setFont(new Font("HY헤드라인M", Font.BOLD, 30));
		titlePnl.add(countLotto);

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

		// 메인 화면 다음 회차 버튼 구현
		nextBtn = new JButton();
		nextBtn.setBounds(750, 416, 245, 155);
		URL checkUrl = MainFrame.class.getClassLoader().getResource("mainButton_04.png");
		nextBtn.setIcon(new ImageIcon(checkUrl));
		nextBtn.setEnabled(true);
		wrapPnl.add(nextBtn);

		buyBtn.addActionListener(this);
		lotteryBtn.addActionListener(this);
		historyBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}

	// 버튼 클릭할 때 마다 동작하는 메소드 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == buyBtn) {
//			ToDo : 구매하기 버튼을 누르면 구매하기 창으로 이동
			buyFrame.allInit();
			buyFrame.setVisible(true);

		} else if (click == lotteryBtn) {
//			ToDo : 번호추첨 버튼을 누르면 번호추첨 창으로 이동

			if (buyFrame.getConsumer().getLottoList().size() != 0) {
				if (lotteryCount == 0) {
					lotteryFrame.resetLottery();
					consumer = buyFrame.getConsumer();
					lotteryFrame.setConsumer(consumer);
					lotteryFrame.repaintLotteryFrame();
					buyBtn.setEnabled(false);
					lotteryCount++;
				}
				lotteryFrame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(wrapPnl, "구매를 먼저 진행해주세요");
			}
		} else if (click == historyBtn) {
//			ToDo : 역대당첨번호 버튼을 누르면 역대당첨번호 창으로 이동
			if (historyCount == 0) {
				if (lotteryFrame.getLottoList().size() == 0) {

				} else {
					historyFrame.setCurrentLotto(lotteryFrame.getLottoList());
					historyFrame.setCurrentBonus(lotteryFrame.getBonusNumber());
					historyFrame.repaintHistoryFrame();
					historyCount++;
				}
			}
			historyFrame.setVisible(true);

		} else if (click == nextBtn) {
//			ToDo : 다음 회차 버튼을 누르면 다음 회차 
			if (lotteryFrame.getLottoList().size() == 6 && lotteryFrame.getBonusNumber() != 0) {
				if (historyCount == 0) {
					historyFrame.setCurrentLotto(lotteryFrame.getLottoList());
					historyFrame.setCurrentBonus(lotteryFrame.getBonusNumber());
					historyFrame.repaintHistoryFrame();
				}
				buyFrame.allInit();
				buyBtn.setEnabled(true);
				buyFrame.setConsumer(new Consumer());
				historyCount = 0;
				lotteryCount = 0;
				lotteryFrame.resetLottery();
				countLotto.setText(historyFrame.getLottoList().size() + "회차");
			} else {
				JOptionPane.showMessageDialog(wrapPnl, "아직 이번 회차 추첨이 진행되지 않았습니다.");
			}

		}
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
