import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LotteryFrame extends JFrame {
	private JCheckBox[] topBigBall;
	private JCheckBox[][] rightSmallBall = new JCheckBox[5][6];
	private JPanel[] rightBasicBallPnl = new JPanel[5];
	private JLabel[] textLbl = new JLabel[5];
	private JLabel[] resultLbl = new JLabel[5];

	public LotteryFrame() {
		super("번호추첨");
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		URL bigBallUrl = LotteryFrame.class.getClassLoader().getResource("lotteryDefault.png");
		URL gif = LotteryFrame.class.getClassLoader().getResource("lotteryGif.gif");

		JPanel wrapPnl = new JPanel();
		wrapPnl.setBounds(0, 59, 994, 512);
		wrapPnl.setLayout(null);
		add(wrapPnl);

		JPanel topBallPnl = new JPanel();
		topBallPnl.setBounds(25, 10, 647, 120);
		wrapPnl.add(topBallPnl);

		JPanel topBonusPnl = new JPanel();
		topBonusPnl.setBounds(692, 10, 272, 120);
		topBonusPnl.setLayout(null);
		wrapPnl.add(topBonusPnl);

		JCheckBox bonusBall = new JCheckBox(); // 보너스 공
		bonusBall.setBounds(127, 0, 120, 110);
		bonusBall.setIcon(new ImageIcon(bigBallUrl));
		topBonusPnl.add(bonusBall);

		JLabel topPlusLbl = new JLabel("+");
		topPlusLbl.setFont(new Font("휴먼엑스포", Font.PLAIN, 96));
		topPlusLbl.setBounds(12, 26, 64, 68);
		topPlusLbl.setHorizontalAlignment(JLabel.CENTER);
		topBonusPnl.add(topPlusLbl);

		JLabel gifLbl = new JLabel(); // 퇴사짤 레이블
		gifLbl.setBounds(45, 140, 460, 312);
		gifLbl.setIcon(new ImageIcon(gif));
		wrapPnl.add(gifLbl);
// ---------------------------------------------------------------------------------------- 추첨버튼이랑 당첨확인버튼	
		JButton leftBtn = new JButton();
		leftBtn.setBounds(163, 465, 210, 35);
		leftBtn.setBorderPainted(false);
		URL leftLotteryBtn = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_01.png");
		leftBtn.setIcon(new ImageIcon(leftLotteryBtn));
		leftBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				URL leftLotteryPressedBtn = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_02.png");
				leftBtn.setIcon(new ImageIcon(leftLotteryPressedBtn));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				leftBtn.setIcon(new ImageIcon(leftLotteryBtn));
			}
		});
		wrapPnl.add(leftBtn);

		JButton rightBtn = new JButton();
		rightBtn.setBounds(650, 465, 210, 35);
		rightBtn.setBorderPainted(false);
		URL rightLotteryBtn = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_04.png");
		rightBtn.setIcon(new ImageIcon(rightLotteryBtn));
		rightBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				URL rightLotteryPressedBtn = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_05.png");
				rightBtn.setIcon(new ImageIcon(rightLotteryPressedBtn));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				rightBtn.setIcon(new ImageIcon(rightLotteryBtn));
			}
		});
		wrapPnl.add(rightBtn);

//-------------------------------------------------------------------------------------------------------- 오른쪽 부분		
		JPanel rightPnl = new JPanel(); // 오른쪽 패널
		rightPnl.setBounds(509, 120, 455, 350);
		BoxLayout box = new BoxLayout(rightPnl, BoxLayout.Y_AXIS);
		rightPnl.setLayout(box);
		wrapPnl.add(rightPnl);

		// 오른쪽 큰 패널 안에 가로 작은패널
		JPanel rightBallPnl = new JPanel();
		rightBallPnl.setBounds(0, 0, 453, 70);
		rightBallPnl.setLayout(null);
		rightPnl.add(rightBallPnl);

//-----------------------------------------------------------------------------------------------추첨 확인쪽 공 만들기
		// 선택 완료쪽 빈공
		URL smallBallUrl = LotteryFrame.class.getClassLoader().getResource("buyDefault.png");
		for (int i = 0; i < rightSmallBall.length; i++) {
			rightBasicBallPnl[i] = new JPanel();
			textLbl[i] = new JLabel("A   ");
			rightBasicBallPnl[i].add(textLbl[i]);
			resultLbl[i] = new JLabel("1등    ");
			rightBasicBallPnl[i].add(resultLbl[i]);

			for (int j = 0; j < 6; j++) {
				rightSmallBall[i][j] = new JCheckBox();
				rightBasicBallPnl[i].add(rightSmallBall[i][j]);
				rightSmallBall[i][j].setIcon(new ImageIcon(smallBallUrl));
			}
			rightPnl.add(rightBasicBallPnl[i]);
		}

//------------------------------------------------------------------------------------------------ 메인 레이블 타이틀	

		JLabel mainLbl = new JLabel("번호추첨");
		mainLbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainLbl.setBounds(0, 0, 994, 61);
		mainLbl.setHorizontalAlignment(JLabel.CENTER);
		mainLbl.setOpaque(true);
		mainLbl.setForeground(Color.white);
		mainLbl.setBackground(new Color(255, 192, 203));
		setFocusable(true);
		add(mainLbl);
// ----------------------------------------------------------------------------------------------------- 큰공 6개 넣는거
		topBigBall = new JCheckBox[6]; // 추첨 공 6개
		for (int i = 0; i < topBigBall.length; i++) {
			topBigBall[i] = new JCheckBox();
			topBigBall[i].setIcon(new ImageIcon(bigBallUrl));
			topBallPnl.add(topBigBall[i]);
		}
// -----------------------------------------------------------------------------------------------------------

		JButton homeBtn = new JButton();
		homeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭중");
			}
		});
		homeBtn.setBounds(30, 5, 50, 50);
		URL home = LotteryFrame.class.getClassLoader().getResource("homePink.png");
		homeBtn.setIcon(new ImageIcon(home));
		homeBtn.setBorderPainted(false);
		mainLbl.add(homeBtn);
	}

	public static void main(String[] args) {
		new LotteryFrame().setVisible(true);
	}
}