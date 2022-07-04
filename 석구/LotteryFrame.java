import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LotteryFrame extends JFrame {
	private JCheckBox[] topBigBall;
	private JCheckBox[][] rightSmallBall = new JCheckBox[5][6];
	private JPanel[] rightBasicBallPnl = new JPanel[5];
	private JLabel[] textLbl = new JLabel[5];
	private JLabel[] resultLbl = new JLabel[5];

	private List<Integer> listFortyFive = new ArrayList<>();
	private List<Integer> lottoList = new ArrayList<>();
	private Integer bonusNumber = 0;
	private Random random = new Random();
	private boolean lotteryEnd = false;
	private List<List<Integer>> purchaseList = new ArrayList<>();

	public LotteryFrame() {
		super("번호추첨");
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		for (int i = 0; i < 45; i++) {
			listFortyFive.add(i + 1);
		}

		for (int i = 0; i < 10; i++) {
			purchaseList.add(lotto());
		}

		URL bigBallUrl = LotteryFrame.class.getClassLoader().getResource("lotteryDefault.png");
		URL gifUrl = LotteryFrame.class.getClassLoader().getResource("lotteryGif.gif");

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
		gifLbl.setIcon(new ImageIcon(gifUrl));
		wrapPnl.add(gifLbl);
// -----------------------------------------------------------------------------------------------------------
//		추첨, 당첨확인 버튼
// -----------------------------------------------------------------------------------------------------------
//		추첨 버튼
		JButton leftBtn = new JButton();
		leftBtn.setBounds(163, 465, 210, 35);
		URL leftBtnUrl = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_01.png");
		URL leftPressedBtnUrl = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_03.png");
		leftBtn.setIcon(new ImageIcon(leftBtnUrl));
		leftBtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				leftBtn.setIcon(new ImageIcon(leftPressedBtnUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (lotteryEnd) {
					JOptionPane.showMessageDialog(wrapPnl, "추첨이 끝났습니다.");
				} else {
					leftBtn.setIcon(new ImageIcon(leftBtnUrl));
					int number = random.nextInt(listFortyFive.size());
					if (lottoList.size() == 6) {
						bonusNumber = number;
						bonusBall.setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						lotteryEnd = true;
						purchaseList.add(0, lottoList);
					} else {
						topBigBall[lottoList.size()]
								.setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						lottoList.add(listFortyFive.get(number));
						listFortyFive.remove(listFortyFive.get(number));
					}

				}
			}
		});
		leftBtn.setBorderPainted(false);
		wrapPnl.add(leftBtn);

//		확인 버튼
		JButton rightBtn = new JButton();
		rightBtn.setBounds(650, 465, 210, 35);
		URL rightBtnUrl = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_04.png");
		URL rightPressedBtnUrl = LotteryFrame.class.getClassLoader().getResource("lotteryBtn_05.png");
		rightBtn.setIcon(new ImageIcon(rightBtnUrl));
		rightBtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(null);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				rightBtn.setIcon(new ImageIcon(rightPressedBtnUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				rightBtn.setIcon(new ImageIcon(rightBtnUrl));
				for (int i = 0; i < rightSmallBall.length; i++) {
					switch (equalCounts(lottoList, purchaseList.get(i))) {
					case 6:
						resultLbl[i].setText("1  등    ");
						for (int j = 0; j < 6; j++) {
							rightSmallBall[i][j].setIcon(new ImageIcon(getColorNumber(purchaseList.get(i).get(j))));
						}
						break;
					case 5:
						if (purchaseList.get(i).contains(bonusNumber)) {
							resultLbl[i].setText("2  등    ");
							for (int j = 0; j < 6; j++) {
								rightSmallBall[i][j].setIcon(new ImageIcon(getColorNumber(purchaseList.get(i).get(j))));
							}
						} else {
							resultLbl[i].setText("3  등    ");
							for (int j = 0; j < 6; j++) {
								if (lottoList.contains(purchaseList.get(i).get(j))) {
									rightSmallBall[i][j]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(i).get(j))));
								}
							}
						}
						break;
					case 4:
						resultLbl[i].setText("4  등    ");
						for (int j = 0; j < 6; j++) {
							if (lottoList.contains(purchaseList.get(i).get(j))) {
								rightSmallBall[i][j].setIcon(new ImageIcon(getColorNumber(purchaseList.get(i).get(j))));
							}
						}
						break;
					case 3:
						resultLbl[i].setText("5  등    ");
						for (int j = 0; j < 6; j++) {
							if (lottoList.contains(purchaseList.get(i).get(j))) {
								rightSmallBall[i][j].setIcon(new ImageIcon(getColorNumber(purchaseList.get(i).get(j))));
							}
						}
						break;
					default:
						resultLbl[i].setText("탈 락    ");
						break;
					}

				}
			}
		});
		rightBtn.setBorderPainted(false);
		wrapPnl.add(rightBtn);
//--------------------------------------------------------------------------------------------------------
//		왼쪽 부분 끝

//		오른쪽 부분 시작
//--------------------------------------------------------------------------------------------------------		
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
//		추첨 확인쪽 공 만들기
//		선택 완료쪽 빈공
		URL defaultBallUrl = LotteryFrame.class.getClassLoader().getResource("buyDefault.png");
		for (int i = 0; i < rightSmallBall.length; i++) {
			rightBasicBallPnl[i] = new JPanel();

			char c = (char) ('A' + i);
			textLbl[i] = new JLabel(String.valueOf(c) + "    ");
			rightBasicBallPnl[i].add(textLbl[i]);

			resultLbl[i] = new JLabel("확인    ");
			rightBasicBallPnl[i].add(resultLbl[i]);

			for (int j = 0; j < 6; j++) {
				rightSmallBall[i][j] = new JCheckBox();
				rightSmallBall[i][j].setIcon(new ImageIcon(getBlackNumber(purchaseList.get(i).get(j))));
				rightBasicBallPnl[i].add(rightSmallBall[i][j]);
			}
			rightPnl.add(rightBasicBallPnl[i]);
		}
//--------------------------------------------------------------------------------------------------------
//		오른쪽 부분 끝

//		위쪽 부분 시작
//--------------------------------------------------------------------------------------------------------		
//		메인 레이블 타이틀	
		JLabel titleLbl = new JLabel("번호추첨");
		titleLbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		titleLbl.setBounds(0, 0, 994, 61);
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		titleLbl.setOpaque(true);
		titleLbl.setForeground(Color.white);
		titleLbl.setBackground(new Color(255, 192, 203));
		setFocusable(true);
		add(titleLbl);

// 		큰공 6개 넣는거
		topBigBall = new JCheckBox[6];
		for (int i = 0; i < topBigBall.length; i++) {
			topBigBall[i] = new JCheckBox();
			topBigBall[i].setIcon(new ImageIcon(bigBallUrl));
			topBallPnl.add(topBigBall[i]);
		}
// -----------------------------------------------------------------------------------------------------------
		JButton homeBtn = new JButton();
		homeBtn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
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
		URL homeUrl = LotteryFrame.class.getClassLoader().getResource("homePink.png");
		homeBtn.setIcon(new ImageIcon(homeUrl));
		homeBtn.setBorderPainted(false);
		titleLbl.add(homeBtn);
	}

// -----------------------------------------------------------------------------------------------------------
	public URL getBigColorNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("NumberBig/" + (i) + ".png");
		return url;
	}

	public URL getBlackNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("NumberBlack/" + (i) + ".png");
		return url;
	}

	public URL getColorNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("NumberColor/" + (i) + ".png");
		return url;
	}

	public List<Integer> lotto() {
		Set<Integer> set = new HashSet<>();
		random = new Random();
		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		return list;
	}

	public Integer equalCounts(List<Integer> list1, List<Integer> list2) {
		List<Integer> listDuplicate = new ArrayList<>();
		listDuplicate.addAll(list1);
		listDuplicate.retainAll(list2);
		return listDuplicate.size();
	}

	public static void main(String[] args) {
		new LotteryFrame().setVisible(true);
	}
}