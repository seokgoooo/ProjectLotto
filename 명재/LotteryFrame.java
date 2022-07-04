import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

public class LotteryFrame extends JFrame {
	private Consumer consumer = new Consumer();
	private JCheckBox[] ball;
	private JCheckBox[][][] Rball;
	private JLabel[][] lblA;
	private JLabel[][] lblWin;
	private List<Integer> listFortyFive = new ArrayList<>();
	private List<Integer> lottoList = new ArrayList<>();
	private Integer bonusNumber = 0;
	private Random random = new Random();
	private boolean raffleEnd = false;
	private List<List<Integer>> purchaseList = new ArrayList<>();

	
	public Integer getBonusNumber() {
		return bonusNumber;
	}

	public void setBonusNumber(Integer bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
		System.out.println(this.consumer.getLottoList());
	}

	public List<Integer> getLottoList() {
		return lottoList;
	}

	public void setLottoList(List<Integer> lottoList) {
		this.lottoList = lottoList;
	}

	public LotteryFrame() {
		super("번호추첨");

		CardLayout card = new CardLayout();
		JPanel fullPnl = new JPanel();
		JPanel subPnl = new JPanel(card);

		for (int i = 0; i < 45; i++) {
			listFortyFive.add(i + 1);
		}

		for (int i = 0; i < consumer.getLottoList().size(); i++) {
			purchaseList.add(consumer.getLottoList().get(i));
		}
//		purchaseList.add(lotto());

		int index = (int) Math.ceil(purchaseList.size() / 5.0);

		Rball = new JCheckBox[index][5][6];
		lblA = new JLabel[index][5];
		lblWin = new JLabel[index][5];

		JPanel mainPnl = new JPanel();
		mainPnl.setBounds(0, 59, 994, 512);
		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);

		JPanel pnlBall = new JPanel();
		pnlBall.setBounds(25, 10, 647, 120);
		mainPnl.add(pnlBall);

		JPanel pnlBonus = new JPanel();
		pnlBonus.setBounds(692, 10, 272, 120);
		mainPnl.add(pnlBonus);
		pnlBonus.setLayout(null);

		JCheckBox bonus = new JCheckBox(); // 보너스 공
		bonus.setBounds(127, 0, 120, 110);
		URL bigImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryDefault.png");
		bonus.setIcon(new ImageIcon(bigImgUrl));
		pnlBonus.add(bonus);

		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("휴먼엑스포", Font.PLAIN, 96));
		lblPlus.setBounds(12, 26, 64, 68);
		lblPlus.setHorizontalAlignment(JLabel.CENTER);
		pnlBonus.add(lblPlus);

		JLabel lblreal = new JLabel(); // 퇴사짤 레이블
		lblreal.setBounds(45, 140, 460, 312);
		URL outImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryGif.gif");
		lblreal.setIcon(new ImageIcon(outImgUrl));
		mainPnl.add(lblreal);

//-------------------------------------------------------------------------------------------------------- 오른쪽 부분		
		fullPnl.setBounds(509, 120, 455, 350);
		mainPnl.add(fullPnl);

		// 오른쪽 큰 패널 안에 가로 작은패널
		JPanel pnlRball = new JPanel();
		pnlRball.setBounds(0, 0, 453, 70);
		pnlRball.setLayout(null);
// ----------------------------------------------------------------------------------------------------- 큰공 6개 넣는거
		ball = new JCheckBox[6]; // 추첨 공 6개
		for (int i = 0; i < ball.length; i++) {
			ball[i] = new JCheckBox();
			pnlBall.add(ball[i]);
			ball[i].setIcon(new ImageIcon(bigImgUrl));
		}
// ----------------------------------------------------------------------------------------------------- 
		// 번호 추첨
		JButton confirmClick = new JButton();
		JButton btnClick = new JButton();

		btnClick.setBounds(163, 465, 210, 35);
		mainPnl.add(btnClick);

		URL winImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_01.png");
		btnClick.setIcon(new ImageIcon(winImgUrl));
		URL win3ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_03.png");
		btnClick.addMouseListener(new MouseAdapter() {
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
				btnClick.setIcon(new ImageIcon(win3ImgUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (raffleEnd) {
					JOptionPane.showMessageDialog(mainPnl, "추첨이 끝났습니다.");
				} else {
					btnClick.setIcon(new ImageIcon(winImgUrl));
					int number = random.nextInt(listFortyFive.size());
					if (lottoList.size() == 6) {
						bonusNumber = number;
						bonus.setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						purchaseList.remove(0);
						purchaseList.add(0, lottoList);
						confirmClick.setVisible(true);
						raffleEnd = true;
					} else {

//					ball[lottoList.size()].setText(String.valueOf(listFortyFive.get(number)));
						ball[lottoList.size()].setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						lottoList.add(listFortyFive.get(number));
						listFortyFive.remove(listFortyFive.get(number));
					}
				}
			}
		});
		btnClick.setBorderPainted(false);

//-----------------------------------------------------------------------------------------------
		JPanel[] cardPnl = new JPanel[index];
		JPanel[] cardInPnl = new JPanel[5];
		JPanel pnlEast = new JPanel();

		JButton btnPrev = new JButton("이전");
		btnPrev.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnPrev.setBackground(Color.white);
		pnlEast.add(btnPrev);

		JButton btnNext = new JButton("다음");
		btnNext.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnNext.setBackground(Color.white);
		pnlEast.add(btnNext);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("다음")) {
					card.next(subPnl); // next 다음 컴포넌트를 보여줌
				} else {
					card.previous(subPnl); // previous 이전 컴포넌트를 보여줌
				}
			}
		};
		btnNext.addActionListener(listener);
		btnPrev.addActionListener(listener);

		fullPnl.add(subPnl);
		fullPnl.add(pnlEast, "East");

		int same = 0;
		for (int i = 0; i < cardPnl.length; i++) {
			cardPnl[i] = new JPanel();
			BoxLayout cardBox = new BoxLayout(cardPnl[i], BoxLayout.Y_AXIS);
			cardPnl[i].setLayout(cardBox);
			char c = (char) ('A' + i);

			subPnl.add(cardPnl[i], String.valueOf(c));

			for (int j = 0; j < 5; j++) {
				if (same == purchaseList.size()) {
					break;
				}
				cardInPnl[j] = new JPanel();
				char alpa = (char) ('A' + j);
				lblA[i][j] = new JLabel(String.valueOf(alpa) + "    ");
				lblWin[i][j] = new JLabel("확인    ");

				cardInPnl[j].add(lblA[i][j]);
				cardInPnl[j].add(lblWin[i][j]);

				for (int k = 0; k < 6; k++) {
					Rball[i][j][k] = new JCheckBox();
					cardInPnl[j].add(Rball[i][j][k]);
					Rball[i][j][k].setIcon(new ImageIcon(getBlackNumber(purchaseList.get(same).get(k))));
				}
				cardPnl[i].add(cardInPnl[j]);
				same++;
			}

		}

		card.show(subPnl, "A");
//-----------------------------------------------------------------------------------------------추첨 확인쪽 공 만들기

		URL me1ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_04.png");
		URL me2ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_05.png");

		confirmClick.setBounds(650, 465, 210, 35);
		confirmClick.setVisible(false);
		mainPnl.add(confirmClick);
		confirmClick.setIcon(new ImageIcon(me1ImgUrl));
		confirmClick.addMouseListener(new MouseAdapter() {
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
				confirmClick.setIcon(new ImageIcon(me2ImgUrl));
			}

			int same = 0;

			@Override
			public void mouseReleased(MouseEvent e) {
				confirmClick.setIcon(new ImageIcon(me1ImgUrl));
				for (int i = 0; i < index; i++) {
					for (int j = 0; j < 5; j++) {
						if (same == purchaseList.size()) {
							break;
						}
						switch (equalCounts(lottoList, purchaseList.get(same))) {
						case 6:
							lblWin[i][j].setText("1  등    ");
							for (int k = 0; k < 6; k++) {
								Rball[i][j][k].setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
							}
							same++;
							break;
						case 5:
							if (purchaseList.get(i).contains(bonusNumber)) {
								lblWin[i][j].setText("2  등    ");
								for (int k = 0; k < 6; k++) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							} else {
								lblWin[i][j].setText("3  등    ");
								for (int k = 0; k < 6; k++) {
									if (lottoList.contains(purchaseList.get(same).get(k))) {
										Rball[i][j][k]
												.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
									}
								}
							}
							same++;
							break;
						case 4:
							lblWin[i][j].setText("4  등    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						case 3:
							lblWin[i][j].setText("5 등    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						default:
							lblWin[i][j].setText("탈락    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						}

					}
				}
			}
		});
		confirmClick.setBorderPainted(false);
//------------------------------------------------------------------------------------------------ 메인 레이블 타이틀	
		JLabel mainlbl = new JLabel("번호추첨");
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setBounds(0, 0, 994, 61);
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setOpaque(true);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		add(mainlbl);
		setFocusable(true);
// -----------------------------------------------------------------------------------------------------------
		JButton btnHome = new JButton();
		btnHome.addMouseListener(new MouseAdapter() {
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
				dispose();
			}
		});
		btnHome.setBounds(30, 5, 50, 50);
		mainlbl.add(btnHome);
		URL home2ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/homePink.png");
		btnHome.setIcon(new ImageIcon(home2ImgUrl));
		btnHome.setBorderPainted(false);

		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

	}

	public LotteryFrame(Consumer consumer) {
		super("번호추첨");

		this.consumer = consumer;

		CardLayout card = new CardLayout();
		JPanel fullPnl = new JPanel();
		JPanel subPnl = new JPanel(card);

		for (int i = 0; i < 45; i++) {
			listFortyFive.add(i + 1);
		}

		for (int i = 0; i < consumer.getLottoList().size(); i++) {
			purchaseList.add(consumer.getLottoList().get(i));
		}
//		purchaseList.add(lotto());

		int index = (int) Math.ceil(purchaseList.size() / 5.0);

		Rball = new JCheckBox[index][5][6];
		lblA = new JLabel[index][5];
		lblWin = new JLabel[index][5];

		JPanel mainPnl = new JPanel();
		mainPnl.setBounds(0, 59, 994, 512);
		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);

		JPanel pnlBall = new JPanel();
		pnlBall.setBounds(25, 10, 647, 120);
		mainPnl.add(pnlBall);

		JPanel pnlBonus = new JPanel();
		pnlBonus.setBounds(692, 10, 272, 120);
		mainPnl.add(pnlBonus);
		pnlBonus.setLayout(null);

		JCheckBox bonus = new JCheckBox(); // 보너스 공
		bonus.setBounds(127, 0, 120, 110);
		URL bigImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryDefault.png");
		bonus.setIcon(new ImageIcon(bigImgUrl));
		pnlBonus.add(bonus);

		JLabel lblPlus = new JLabel("+");
		lblPlus.setFont(new Font("휴먼엑스포", Font.PLAIN, 96));
		lblPlus.setBounds(12, 26, 64, 68);
		lblPlus.setHorizontalAlignment(JLabel.CENTER);
		pnlBonus.add(lblPlus);

		JLabel lblreal = new JLabel(); // 퇴사짤 레이블
		lblreal.setBounds(45, 140, 460, 312);
		URL outImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryGif.gif");
		lblreal.setIcon(new ImageIcon(outImgUrl));
		mainPnl.add(lblreal);

//-------------------------------------------------------------------------------------------------------- 오른쪽 부분		
		fullPnl.setBounds(509, 120, 455, 350);
		mainPnl.add(fullPnl);

		// 오른쪽 큰 패널 안에 가로 작은패널
		JPanel pnlRball = new JPanel();
		pnlRball.setBounds(0, 0, 453, 70);
		pnlRball.setLayout(null);
// ----------------------------------------------------------------------------------------------------- 큰공 6개 넣는거
		ball = new JCheckBox[6]; // 추첨 공 6개
		for (int i = 0; i < ball.length; i++) {
			ball[i] = new JCheckBox();
			pnlBall.add(ball[i]);
			ball[i].setIcon(new ImageIcon(bigImgUrl));
		}
// ----------------------------------------------------------------------------------------------------- 
		// 번호 추첨
		JButton confirmClick = new JButton();
		JButton btnClick = new JButton();

		btnClick.setBounds(163, 465, 210, 35);
		mainPnl.add(btnClick);

		URL winImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_01.png");
		btnClick.setIcon(new ImageIcon(winImgUrl));
		URL win3ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_03.png");
		btnClick.addMouseListener(new MouseAdapter() {
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
				btnClick.setIcon(new ImageIcon(win3ImgUrl));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (raffleEnd) {
					JOptionPane.showMessageDialog(mainPnl, "추첨이 끝났습니다.");
				} else {
					btnClick.setIcon(new ImageIcon(winImgUrl));
					int number = random.nextInt(listFortyFive.size());
					if (lottoList.size() == 6) {
						bonusNumber = number;
						bonus.setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						purchaseList.remove(0);
						purchaseList.add(0, lottoList);
						confirmClick.setVisible(true);
						raffleEnd = true;
					} else {

//					ball[lottoList.size()].setText(String.valueOf(listFortyFive.get(number)));
						ball[lottoList.size()].setIcon(new ImageIcon(getBigColorNumber(listFortyFive.get(number))));
						lottoList.add(listFortyFive.get(number));
						listFortyFive.remove(listFortyFive.get(number));
					}
				}
			}
		});
		btnClick.setBorderPainted(false);
//-----------------------------------------------------------------------------------------------
		JPanel[] cardPnl = new JPanel[index];
		JPanel[] cardInPnl = new JPanel[5];
		JPanel pnlEast = new JPanel();

		JButton btnPrev = new JButton("이전");
		btnPrev.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnPrev.setBackground(Color.white);
		pnlEast.add(btnPrev);

		JButton btnNext = new JButton("다음");
		btnNext.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnNext.setBackground(Color.white);
		pnlEast.add(btnNext);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("다음")) {
					card.next(subPnl); // next 다음 컴포넌트를 보여줌
				} else {
					card.previous(subPnl); // previous 이전 컴포넌트를 보여줌
				}
			}
		};
		btnNext.addActionListener(listener);
		btnPrev.addActionListener(listener);

		fullPnl.add(subPnl);
		fullPnl.add(pnlEast, "East");

		int same = 0;
		for (int i = 0; i < cardPnl.length; i++) {
			cardPnl[i] = new JPanel();
			BoxLayout cardBox = new BoxLayout(cardPnl[i], BoxLayout.Y_AXIS);
			cardPnl[i].setLayout(cardBox);
			char c = (char) ('A' + i);

			subPnl.add(cardPnl[i], String.valueOf(c));

			for (int j = 0; j < 5; j++) {
				if (same == purchaseList.size()) {
					break;
				}
				cardInPnl[j] = new JPanel();
				char alpa = (char) ('A' + j);
				lblA[i][j] = new JLabel(String.valueOf(alpa) + "    ");
				lblWin[i][j] = new JLabel("확인    ");

				cardInPnl[j].add(lblA[i][j]);
				cardInPnl[j].add(lblWin[i][j]);

				for (int k = 0; k < 6; k++) {
					Rball[i][j][k] = new JCheckBox();
					cardInPnl[j].add(Rball[i][j][k]);
					Rball[i][j][k].setIcon(new ImageIcon(getBlackNumber(purchaseList.get(same).get(k))));
				}
				cardPnl[i].add(cardInPnl[j]);
				same++;
			}

		}

		card.show(subPnl, "A");
//-----------------------------------------------------------------------------------------------추첨 확인쪽 공 만들기

		URL me1ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_04.png");
		URL me2ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/lotteryBtn_05.png");

		confirmClick.setBounds(650, 465, 210, 35);
		confirmClick.setVisible(false);
		mainPnl.add(confirmClick);
		confirmClick.setIcon(new ImageIcon(me1ImgUrl));
		confirmClick.addMouseListener(new MouseAdapter() {
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
				confirmClick.setIcon(new ImageIcon(me2ImgUrl));
			}

			int same = 0;

			@Override
			public void mouseReleased(MouseEvent e) {
				confirmClick.setIcon(new ImageIcon(me1ImgUrl));
				for (int i = 0; i < index; i++) {
					for (int j = 0; j < 5; j++) {
						if (same == purchaseList.size()) {
							break;
						}
						switch (equalCounts(lottoList, purchaseList.get(same))) {
						case 6:
							lblWin[i][j].setText("1  등    ");
							for (int k = 0; k < 6; k++) {
								Rball[i][j][k].setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
							}
							same++;
							break;
						case 5:
							if (purchaseList.get(i).contains(bonusNumber)) {
								lblWin[i][j].setText("2  등    ");
								for (int k = 0; k < 6; k++) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							} else {
								lblWin[i][j].setText("3  등    ");
								for (int k = 0; k < 6; k++) {
									if (lottoList.contains(purchaseList.get(same).get(k))) {
										Rball[i][j][k]
												.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
									}
								}
							}
							same++;
							break;
						case 4:
							lblWin[i][j].setText("4  등    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						case 3:
							lblWin[i][j].setText("5 등    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						default:
							lblWin[i][j].setText("탈락    ");
							for (int k = 0; k < 6; k++) {
								if (lottoList.contains(purchaseList.get(same).get(k))) {
									Rball[i][j][k]
											.setIcon(new ImageIcon(getColorNumber(purchaseList.get(same).get(k))));
								}
							}
							same++;
							break;
						}

					}
				}
			}
		});
		confirmClick.setBorderPainted(false);
//------------------------------------------------------------------------------------------------ 메인 레이블 타이틀	
		JLabel mainlbl = new JLabel("번호추첨");
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setBounds(0, 0, 994, 61);
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setOpaque(true);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		add(mainlbl);
		setFocusable(true);
// -----------------------------------------------------------------------------------------------------------
		JButton btnHome = new JButton();
		btnHome.addMouseListener(new MouseAdapter() {
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
				dispose();
			}
		});
		btnHome.setBounds(30, 5, 50, 50);
		mainlbl.add(btnHome);
		URL home2ImgUrl = LotteryFrame.class.getClassLoader().getResource("resources/homePink.png");
		btnHome.setIcon(new ImageIcon(home2ImgUrl));
		btnHome.setBorderPainted(false);

		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

	}

	public URL getBigColorNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("resources/NumberBig/" + (i) + ".png");
		return url;
	}

	public URL getBlackNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("resources/NumberBlack/" + (i) + ".png");
		return url;
	}

	public URL getColorNumber(int i) {
		URL url = LotteryFrame.class.getClassLoader().getResource("resources/NumberColor/" + (i) + ".png");
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
}