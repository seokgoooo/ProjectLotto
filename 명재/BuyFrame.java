import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BuyFrame extends JFrame implements ActionListener {
	private JCheckBox[] numberBox;
	private Integer[] comboNumber = { 1, 2, 3, 4, 5 };
	private Consumer consumer = new Consumer();
	private Set<Integer> lottoSet = new HashSet<>();

	private JPanel[] rightSetPnl = new JPanel[5];
	// 오른쪽 Frame 번호 보여줄 Label
	private JLabel[][] rightLbl = new JLabel[5][7];
	private JButton[] changeBtn = new JButton[5];
	private JButton[] deleteBtn = new JButton[5];
	private JButton[] copyBtn = new JButton[5];
	private JButton[] pasteBtn = new JButton[5];

	private boolean changeTrue = false;
	private int index = 0;
	private List<Integer> copyList;
	private URL defaultBallImg = BuyFrame.class.getClassLoader().getResource("buyDefault.png");
	private ImageIcon defaultBall = new ImageIcon(defaultBallImg);
	private JLabel rightBottomTextLbl;
	private JButton rightBuyBtn;
	private JButton rightResetBtn;
	private JRadioButton autoRBtn;
	private JRadioButton semiAutoRBtn;
	private JRadioButton manualRBtn;
	private List<List<Integer>> lottoList;
	private JComboBox<Integer> purchaseCombo;

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public BuyFrame() {
		super("로또 구매");
		lottoList = new ArrayList<>();
		setSize(1000, 600);
		setBackground(Color.white);
		setResizable(false);
		setLocationRelativeTo(null);

//		-----------------------------------------------------------------------------------------
		// 전체 담는 패널
		JPanel wrapPnl = new JPanel();
		wrapPnl.setLayout(null);
		add(wrapPnl);

		JButton homeBtn = new JButton();
		homeBtn.setBounds(30, 15, 50, 50);
		URL homeBtnUrl = BuyFrame.class.getClassLoader().getResource("homeBlack.png");
		homeBtn.setIcon(new ImageIcon(homeBtnUrl));
		homeBtn.setBorderPainted(false);
		wrapPnl.add(homeBtn);

		JLabel titleImgLbl = new JLabel();
		titleImgLbl.setBounds(0, 0, 1000, 75);
		URL titleImgUrl = BuyFrame.class.getClassLoader().getResource("buyTitle.png");
		titleImgLbl.setIcon(new ImageIcon(titleImgUrl));
		wrapPnl.add(titleImgLbl);

		// 왼쪽 번호 보여주는 패널
		JPanel leftPnl = new JPanel();
		leftPnl.setBounds(222, 144, 242, 373);
		wrapPnl.add(leftPnl);

		// 오른쪽 창 패널
		JPanel rightPnl = new JPanel();
		rightPnl.setBounds(505, 107, 467, 444);
		rightPnl.setLayout(null);
		wrapPnl.add(rightPnl);

//		-----------------------------------------------------------------------------------------
//		왼쪽 (구매) Frame 시작
//		왼쪽 라디오 버튼들 (수동, 혼합, 자동) 구현 / 동작
//		-----------------------------------------------------------------------------------------

		// 수동선택 라디오 버튼

		manualRBtn = new JRadioButton();
		manualRBtn.setBounds(45, 115, 138, 136);
		URL manualUrl = BuyFrame.class.getClassLoader().getResource("buyButton_01.png");
		manualRBtn.setIcon(new ImageIcon(manualUrl));
		URL manualSUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_01.png");
		manualRBtn.setSelectedIcon(new ImageIcon(manualSUrl));
		manualRBtn.setSelected(true);
		wrapPnl.add(manualRBtn);

		semiAutoRBtn = new JRadioButton();
		semiAutoRBtn.setBounds(45, 263, 138, 136);
		URL semiAutoUrl = BuyFrame.class.getClassLoader().getResource("buyButton_02.png");
		semiAutoRBtn.setIcon(new ImageIcon(semiAutoUrl));
		URL semiAutoSUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_02.png");
		semiAutoRBtn.setSelectedIcon(new ImageIcon(semiAutoSUrl));
		wrapPnl.add(semiAutoRBtn);

		autoRBtn = new JRadioButton();
		autoRBtn.setBounds(45, 411, 138, 136);
		URL autoUrl = BuyFrame.class.getClassLoader().getResource("buyButton_03.png");
		autoRBtn.setIcon(new ImageIcon(autoUrl));
		URL autoSUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_03.png");
		autoRBtn.setSelectedIcon(new ImageIcon(autoSUrl));

		wrapPnl.add(autoRBtn);

		// 라디오 버튼 그룹
		ButtonGroup leftRGroup = new ButtonGroup();
		leftRGroup.add(manualRBtn);
		leftRGroup.add(semiAutoRBtn);
		leftRGroup.add(autoRBtn);

		manualRBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballAllSelected();
				ballAllReset();
				lottoSet.removeAll(lottoSet);
			}
		});

		semiAutoRBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballAllSelected();
				ballAllReset();
				numberBoxAllBlack(); // new
				lottoSet.removeAll(lottoSet);
			}
		});

		autoRBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballAllUnSelected();
				ballAllReset();
				lottoSet.removeAll(lottoSet);
			}
		});

		// 수량 정하는 콤보박스
		purchaseCombo = new JComboBox<>(comboNumber);
		purchaseCombo.setBounds(317, 524, 50, 25);
		wrapPnl.add(purchaseCombo);

		// 왼쪽 확인 버튼
		JButton leftCheckBtn = new JButton();
		leftCheckBtn.setBounds(223, 522, 90, 30);
		URL leftCheckUrl = BuyFrame.class.getClassLoader().getResource("buyButton_04.png");
		leftCheckBtn.setIcon(new ImageIcon(leftCheckUrl));
		wrapPnl.add(leftCheckBtn);

		// 왼쪽 번호 초기화 버튼
		JButton leftResetBtn = new JButton();
		leftResetBtn.setBounds(373, 522, 90, 30);
		URL leftResetUrl = BuyFrame.class.getClassLoader().getResource("buyButton_05.png");
		leftResetBtn.setIcon(new ImageIcon(leftResetUrl));
		wrapPnl.add(leftResetBtn);

		leftResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballAllReset();
				if (autoRBtn.isSelected()) {
					ballAllUnSelected();
				} else {
					ballAllSelected();
				}
				numberBoxAllBlack(); // new
				lottoSet.removeAll(lottoSet);
			}
		});

		JLabel leftPriceLbl = new JLabel("선택금액 : " + 1000 + "원");
		leftPriceLbl.setBounds(240, 100, 200, 50);
		leftPriceLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		leftPriceLbl.setBackground(Color.DARK_GRAY);
		leftPriceLbl.setHorizontalAlignment(JLabel.CENTER);
		wrapPnl.add(leftPriceLbl);

		purchaseCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lottoList.size() + purchaseCombo.getSelectedIndex() + 1 > 5) {
					JOptionPane.showMessageDialog(leftPnl, "한번에 5장 까지만 구매 가능합니다.");
					purchaseCombo.setSelectedIndex(0);
				} else {
					int p = (purchaseCombo.getSelectedIndex() + 1) * 1000;
					leftPriceLbl.setText("선택금액 : " + p + "원");
				}
			}
		});

		// 배경화면 image 적용
		JLabel mainBgLbl = new JLabel();
		mainBgLbl.setBounds(0, 0, 995, 580);
		URL url = BuyFrame.class.getClassLoader().getResource("back_01.png");
		mainBgLbl.setIcon(new ImageIcon(url));
		wrapPnl.add(mainBgLbl);
//		-----------------------------------------------------------------------------------------
//		1 ~ 45 번호 image 구현
//		-----------------------------------------------------------------------------------------
		// 왼쪽 번호 부분 배치와 정렬
		GridLayout grid = new GridLayout(9, 5);
		leftPnl.setLayout(grid); // 패널안에서 정렬

		// default 값으로 흑백 번호 image 를 담을 배열
		numberBox = new JCheckBox[45];
		for (int i = 0; i < numberBox.length; i++) {
			numberBox[i] = new JCheckBox();
			numberBox[i].setIcon(new ImageIcon(getBlackNumber(i)));
			numberBox[i].addActionListener(this);
			leftPnl.add(numberBox[i]);
		}
//		-----------------------------------------------------------------------------------------
//		1 ~ 45 번호 image 부분 끝
//		번호 image 아래 콤보박스 확인 초기화 버튼 구현 / 동작
//		-----------------------------------------------------------------------------------------

//		-----------------------------------------------------------------------------------------
//		왼쪽 (구매) Frame 끝

//		오른쪽 (구매확인) Frame 시작
//		-----------------------------------------------------------------------------------------
		JLabel rightTopPriceLbl = new JLabel("선택금액");
		rightTopPriceLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		rightTopPriceLbl.setBounds(222, 111, 242, 23);
		rightTopPriceLbl.setBackground(Color.darkGray);
		rightTopPriceLbl.setHorizontalAlignment(JLabel.CENTER);
		wrapPnl.add(rightTopPriceLbl);

		JLabel rightTopTextLbl = new JLabel("선택번호 확인");
		rightTopTextLbl.setBounds(25, 15, 120, 30);
		rightTopTextLbl.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		rightPnl.add(rightTopTextLbl);

		rightResetBtn = new JButton();
		rightResetBtn.setBounds(390, 15, 60, 30);
		URL rightResetUrl = BuyFrame.class.getClassLoader().getResource("buyButton_06.png");
		rightResetBtn.setIcon(new ImageIcon(rightResetUrl));
		rightPnl.add(rightResetBtn);

		for (int i = 0; i < rightSetPnl.length; i++) {
			rightSetPnl[i] = new JPanel();
			changeBtn[i] = new JButton();
			deleteBtn[i] = new JButton();
			copyBtn[i] = new JButton();
			pasteBtn[i] = new JButton();

			char c = (char) ('A' + i);
			rightLbl[i][0] = new JLabel(String.valueOf(c));
			rightLbl[i][0].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
			rightSetPnl[i].add(rightLbl[i][0]);

			// 구매 확인에서 로또 번호들을 나열하기 위해 label들을 생성
			for (int j = 1; j < rightLbl[i].length; j++) {
				rightLbl[i][j] = new JLabel();
				rightLbl[i][j].setIcon(defaultBall);
				rightSetPnl[i].add(rightLbl[i][j]);
			}

			changeBtn[i] = new JButton("수정");
			changeBtn[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			changeBtn[i].setBackground(Color.white);
			changeBtn[i].setEnabled(false);
			rightSetPnl[i].add(changeBtn[i]);

			deleteBtn[i] = new JButton("삭제");
			deleteBtn[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			deleteBtn[i].setBackground(Color.white);
			deleteBtn[i].setEnabled(false);
			rightSetPnl[i].add(deleteBtn[i]);

			copyBtn[i] = new JButton("번호복사");
			copyBtn[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			copyBtn[i].setBackground(Color.white);
			copyBtn[i].setEnabled(false);
			rightSetPnl[i].add(copyBtn[i]);

			pasteBtn[i] = new JButton("붙여넣기");
			pasteBtn[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			pasteBtn[i].setBackground(Color.white);
			pasteBtn[i].setVisible(false);
			rightSetPnl[i].add(pasteBtn[i]);

			rightPnl.add(rightSetPnl[i]);
		}

		int k = 0;
		for (int i = 0; i < rightLbl.length; i++) {
			for (int j = 1; j < rightLbl[i].length; j++) {
				rightLbl[0][j].setBounds(40 + k, 25, 15, 15);
				rightLbl[1][j].setBounds(50 + k, 50, 15, 15);
				rightLbl[2][j].setBounds(30 + k, 75, 15, 15);
				rightLbl[3][j].setBounds(30 + k, 100, 15, 15);
				rightLbl[4][j].setBounds(30 + k, 125, 15, 15);
				k += 25;
			}
		}

		rightSetPnl[0].setBounds(0, 67, 467, 64);
		rightSetPnl[1].setBounds(0, 130, 467, 64);
		rightSetPnl[2].setBounds(0, 193, 467, 64);
		rightSetPnl[3].setBounds(0, 257, 467, 64);
		rightSetPnl[4].setBounds(0, 320, 467, 64);

		rightLbl[0][0].setBounds(25, 26, 19, 15);
		rightLbl[1][0].setBounds(25, 27, 18, 15);
		rightLbl[2][0].setBounds(25, 27, 19, 15);
		rightLbl[3][0].setBounds(25, 23, 18, 15);
		rightLbl[4][0].setBounds(25, 25, 17, 15);

		changeBtn[0].setBounds(245, 22, 60, 23);
		changeBtn[1].setBounds(245, 18, 60, 23);
		changeBtn[2].setBounds(245, 23, 60, 23);
		changeBtn[3].setBounds(245, 19, 60, 23);
		changeBtn[4].setBounds(245, 21, 60, 23);

		deleteBtn[0].setBounds(310, 22, 60, 23);
		deleteBtn[1].setBounds(310, 19, 60, 23);
		deleteBtn[2].setBounds(310, 23, 60, 23);
		deleteBtn[3].setBounds(310, 19, 60, 23);
		deleteBtn[4].setBounds(310, 21, 60, 23);

		copyBtn[0].setBounds(375, 22, 75, 23);
		copyBtn[1].setBounds(375, 19, 75, 23);
		copyBtn[2].setBounds(375, 23, 75, 23);
		copyBtn[3].setBounds(375, 19, 75, 23);
		copyBtn[4].setBounds(375, 21, 75, 23);

		rightBottomTextLbl = new JLabel("결제금액 : " + consumer.getPrice() + "원(현재: " + 0 + "장)");
		rightBottomTextLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		rightBottomTextLbl.setBounds(50, 388, 300, 50);
		rightPnl.add(rightBottomTextLbl);

//		----------------------------------------------------------------------------------
		// 아직 미구현 구매횟수
//		JLabel countLbl = new JLabel("총 구매 횟수: 0");
//		----------------------------------------------------------------------------------

		// 오른쪽 초기화 버튼 이벤트
		rightResetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int price = -(1000 * lottoList.size());
				consumer.setPrice(price);
				rightBottomTextLbl
						.setText("결제금액: " + consumer.getPrice() + "원(현재: " + (consumer.getPrice() / 1000) + "장)");
				for (int i = 0; i < rightLbl.length; i++) {
					for (int j = 1; j < rightLbl[i].length; j++) {
						rightLbl[i][j].setIcon(defaultBall);
					}
				}
				lottoList.removeAll(lottoList);
				pasteBtnFalse();
				copyBtnReset();
			}
		});

		// 오른쪽 삭제 버튼 이벤트
		ActionListener removeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < deleteBtn.length; i++) {

					if (e.getSource() == deleteBtn[i]) {
						changeBtn[lottoList.size() - 1].setEnabled(false);
						deleteBtn[lottoList.size() - 1].setEnabled(false);
						copyBtn[lottoList.size() - 1].setEnabled(false);
						lottoList.remove(i);
						rightLblReset(); // reset을 defaultball로 new
						for (int j = 0; j < lottoList.size(); j++) {
							for (int k = 0; k < lottoList.get(j).size(); k++) {
								rightLbl[j][k + 1].setIcon(new ImageIcon(getColorNumber(lottoList.get(j).get(k) - 1))); // new
							}
							copyBtn[j].setEnabled(true);
						}
						consumer.setPrice(-1000);
						rightBottomTextLbl.setText(
								"결제금액: " + consumer.getPrice() + "원(현재: " + (consumer.getPrice() / 1000) + "장)");
						ballAllReset();
					}
				}
			}
		};

		// 복사 버튼 이벤트
		ActionListener copyListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < copyBtn.length; i++) {
					changeBtn[i].setEnabled(false);
					deleteBtn[i].setEnabled(false);
					copyBtn[i].setEnabled(false);
					if (e.getSource() == copyBtn[i]) {
						copyList = new ArrayList<>(lottoList.get(i));
						copyBtn[lottoList.size()].setVisible(false);
						pasteBtn[lottoList.size()].setVisible(true);
					}
				}
			}
		};

		// 붙여넣기 버튼 이벤트
		ActionListener pasteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < lottoList.size(); i++) {
					changeBtn[i].setEnabled(true);
					deleteBtn[i].setEnabled(true);
					copyBtn[i].setEnabled(true);
				}
				for (int i = 0; i < pasteBtn.length; i++) {

					if (e.getSource() == pasteBtn[i]) {
						lottoList.add(copyList);
						for (int j = 0; j < copyList.size(); j++) {
							rightLbl[i][j + 1].setIcon(new ImageIcon(getColorNumber(copyList.get(j) - 1))); // new
						}
						consumer.setPrice(1000);
						rightBottomTextLbl.setText(
								"총 금액: " + consumer.getPrice() + "원(현재: " + (consumer.getPrice() / 1000) + "장)");
						changeBtn[lottoList.size() - 1].setEnabled(true);
						deleteBtn[lottoList.size() - 1].setEnabled(true);
						copyBtn[lottoList.size() - 1].setEnabled(true);
						copyBtnReset();
					}
				}
			}
		};

		rightBuyBtn = new JButton();
		rightBuyBtn.setBounds(340, 390, 120, 50);
		URL rightBuyUrl = BuyFrame.class.getClassLoader().getResource("buyButton_07.png");
		rightBuyBtn.setIcon(new ImageIcon(rightBuyUrl));
		rightPnl.add(rightBuyBtn);

		rightBuyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consumer.setLottoList(lottoList);
				lottoList.removeAll(lottoList);
//				----------------------------------------------------------------------------------
//				countLbl.setText("총 구매 횟수: " + consumer.getCount() + "회");
//				----------------------------------------------------------------------------------
				for (int i = 0; i < rightLbl.length; i++) {
					for (int j = 1; j < rightLbl[i].length; j++) {
						rightLbl[i][j].setIcon(defaultBall);
					}
				}
				lottoList.removeAll(lottoList);
				pasteBtnFalse();
				copyBtnReset();
			}
		});

//		-----------------------------------------------------------------------------------------
//		오른쪽 (구매확인) Frame 끝
//		-----------------------------------------------------------------------------------------
		// 왼쪽 확인 버튼 이벤트
		leftCheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeTrue) {
					if (lottoSet.size() < 6) {
						JOptionPane.showMessageDialog(BuyFrame.this, "번호 6개를 선택해 주세요");
					} else {
						lottoList.remove(lottoList.get(index)); // index에 해당하는 배열을 지운다.
						List<Integer> list = new ArrayList<Integer>(lottoSet); // 새로 수정한 lotto set을 list로 만든다.
						Collections.sort(list); // 정렬
						lottoList.add(index, list); // 삭제한 index에 정렬한 list를 추가한다.
						ballAllReset(); // checkbox를 모두 초기화 시킨다.
						ballAllSelected(); // checkbox를 모두 활성화 시킨다.
						lottoSet.removeAll(lottoSet); // set을 지워준다.
						for (int j = 0; j < lottoList.get(index).size(); j++) {
							rightLbl[index][j + 1]
									.setIcon(new ImageIcon(getColorNumber(lottoList.get(index).get(j) - 1))); // new
						}
						numberBoxAllBlack(); // new
						semiAutoRBtn.setEnabled(true);
						autoRBtn.setEnabled(true);
						purchaseCombo.setEnabled(true);
						rightResetBtn.setEnabled(true);
						rightBuyBtn.setEnabled(true);
						for (int i = 0; i < lottoList.size(); i++) {
							changeBtn[i].setEnabled(true);
							deleteBtn[i].setEnabled(true);
							copyBtn[i].setEnabled(true);
						}
						changeTrue = false; // 다시 수정 버튼을 누르기 전으로 돌아간다.
					}
				} else {
					if (lottoList.size() + purchaseCombo.getSelectedIndex() + 1 > 5) { // 5장 넘게 구매X
						JOptionPane.showMessageDialog(BuyFrame.this, "복권은 한번에 5장까지 구매 가능합니다.");
					} else {
						if (semiAutoRBtn.isSelected()) {
							Integer[] arr = new Integer[lottoSet.size()];
							int countArr = 0;
							for (Integer number : lottoSet) {
								arr[countArr] = number;
								countArr++;
							}
							for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
								for (int j = 0; j < arr.length; j++) {
									lottoSet.add(arr[j]);
								}
								while (lottoSet.size() < 6) {
									lottoSet.add(new Random().nextInt(45) + 1);
								}
								List<Integer> list = new ArrayList<Integer>(lottoSet);
								Collections.sort(list);
								lottoList.add(list);
								ballAllReset();
								lottoSet.removeAll(lottoSet); // set을 초기화
							}
							consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
						} else if (manualRBtn.isSelected()) {
							if (lottoSet.size() == 6) {
								List<Integer> list = new ArrayList<Integer>(lottoSet);
								Collections.sort(list);
								for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
									lottoList.add(list);
								}
								ballAllReset();
								lottoSet.removeAll(lottoSet);
								consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
							} else {
								JOptionPane.showMessageDialog(BuyFrame.this, "번호 6개를 선택해 주세요");
							}
						} else {
							for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
								while (lottoSet.size() < 6) {
									lottoSet.add(new Random().nextInt(45) + 1);
								}
								List<Integer> list = new ArrayList<Integer>(lottoSet);
								Collections.sort(list);
								lottoList.add(list);
								ballAllReset();
								lottoSet.removeAll(lottoSet); // set을 초기화

							}
							consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
						}
						// 구매 확인창에 번호를 보내줌
						if (lottoList.size() == 5) {
							for (int i = 0; i < copyBtn.length; i++) {
								copyBtn[i].setEnabled(false);
							}
						} else {
							for (int i = 0; i < lottoList.size(); i++) {
								copyBtn[i].setEnabled(true);
							}
						}
						for (int i = 0; i < lottoList.size(); i++) {
							changeBtn[i].setEnabled(true);
							deleteBtn[i].setEnabled(true);
							for (int j = 0; j < lottoList.get(i).size(); j++) {
								rightLbl[i][j + 1].setIcon(new ImageIcon(getColorNumber(lottoList.get(i).get(j) - 1)));
							}
						}
						if (autoRBtn.isSelected()) {
							ballAllUnSelected();
						} else {
							ballAllSelected();
						}
						rightBottomTextLbl.setText(
								"결제금액: " + consumer.getPrice() + "원(현재: " + (consumer.getPrice() / 1000) + "장)");
						purchaseCombo.setSelectedIndex(0);
						numberBoxAllBlack();
					}
				}
			}
		});

		// 오른쪽 수정 버튼 이벤트
		ActionListener changeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < changeBtn.length; i++) {
					changeBtn[i].setEnabled(false);
					deleteBtn[i].setEnabled(false);
					copyBtn[i].setEnabled(false);
					if (e.getSource() == changeBtn[i]) {
						changeTrue = true;
						manualRBtn.setSelected(true);
						semiAutoRBtn.setEnabled(false);
						autoRBtn.setEnabled(false);
						purchaseCombo.setEnabled(false);
						rightResetBtn.setEnabled(false);
						rightBuyBtn.setEnabled(false);

						lottoSet.removeAll(lottoSet);
						ballAllUnSelected();
						ballAllReset();
						for (int j = 0; j < lottoList.get(i).size(); j++) {
							lottoSet.add(lottoList.get(i).get(j));
							numberBox[lottoList.get(i).get(j) - 1].setEnabled(true);
							numberBox[lottoList.get(i).get(j) - 1].setSelected(true);
							numberBox[lottoList.get(i).get(j) - 1]
									.setIcon(new ImageIcon(getColorNumber(lottoList.get(i).get(j) - 1))); // new

						}
						index = i;
						copyBtnReset();
					}
				}
			}
		};

		for (int i = 0; i < changeBtn.length; i++) {
			changeBtn[i].addActionListener(changeListener);
			deleteBtn[i].addActionListener(removeListener);
			copyBtn[i].addActionListener(copyListener);
			pasteBtn[i].addActionListener(pasteListener);
		}

		// 홈버튼
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
	}

	// 모든걸 초기화
	public void allInit() {

		int price = -(1000 * lottoList.size());
		consumer.setPrice(price);
		rightBottomTextLbl.setText("결제금액: " + consumer.getPrice() + "원(현재: " + (consumer.getPrice() / 1000) + "장)");
		for (int i = 0; i < rightLbl.length; i++) {
			for (int j = 1; j < rightLbl[i].length; j++) {
				rightLbl[i][j].setIcon(defaultBall);
			}
		}
		lottoList.removeAll(lottoList);
		lottoSet.removeAll(lottoSet);
		ballAllReset();
		ballAllSelected();
		numberBoxAllBlack();
		pasteBtnFalse();
		copyBtnReset();
		purchaseCombo.setEnabled(true);
		rightResetBtn.setEnabled(true);
		semiAutoRBtn.setEnabled(true);
		autoRBtn.setEnabled(true);
		manualRBtn.setSelected(true);
		rightBuyBtn.setEnabled(true);
		changeTrue = false;
	}

	public URL getBlackNumber(int i) {
		URL url = BuyFrame.class.getClassLoader().getResource("NumberBlack/" + (i + 1) + ".png");
		return url;
	}

	public URL getColorNumber(int i) {
		URL url = BuyFrame.class.getClassLoader().getResource("NumberColor/" + (i + 1) + ".png");
		return url;
	}

	// 1 ~ 45 번호를 클릭해 구매자 로또번호 Set에 저장하는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < numberBox.length; i++) {
			if (e.getSource() == numberBox[i]) {
				if (numberBox[i].isSelected()) {
					numberBox[i].setIcon(new ImageIcon(getColorNumber(i)));
					lottoSet.add(i + 1);
				} else {
					numberBox[i].setIcon(new ImageIcon(getBlackNumber(i)));
					lottoSet.remove(i + 1);
				}
			}
		}
		if (lottoSet.size() == 6) {
			for (int j = 0; j < numberBox.length; j++) {
				if (!numberBox[j].isSelected()) {
					numberBox[j].setEnabled(false);
				}
			}
		} else {
			ballAllSelected();
		}
	}

	// numberBox를 모두 검은색으로 new
	public void numberBoxAllBlack() {
		for (int i = 0; i < numberBox.length; i++) {
			numberBox[i].setIcon(new ImageIcon(getBlackNumber(i)));
		}
	}

	// 로또번호를 모두 활성화
	public void ballAllSelected() {
		for (int i = 0; i < numberBox.length; i++) {
			numberBox[i].setEnabled(true);
		}
	}

	// 로또번호를 모두 비활성화
	public void ballAllUnSelected() {
		for (int i = 0; i < numberBox.length; i++) {
			numberBox[i].setEnabled(false);
		}
	}

	// 로또번호를 모두 초기화
	public void ballAllReset() {
		for (int i = 0; i < numberBox.length; i++) {
			numberBox[i].setSelected(false);
		}
	}

	// 붙여넣기 버튼을 안보이게 설정 하고 복사 버튼을 보이게 설정 (기본값)
	public void copyBtnReset() {
		for (int i = 0; i < copyBtn.length; i++) {
			pasteBtn[i].setVisible(false);
			copyBtn[i].setVisible(true);
		}
	}

	public void pasteBtnFalse() {
		for (int i = 0; i < changeBtn.length; i++) {
			changeBtn[i].setEnabled(false);
			deleteBtn[i].setEnabled(false);
			copyBtn[i].setEnabled(false);
		}
	}

	// 번호 확인 label을 초기화
	public void rightLblReset() {
		for (int i = 0; i < rightLbl.length; i++) {
			for (int j = 1; j < rightLbl[i].length; j++) {
				rightLbl[i][j].setIcon(defaultBall);
			}
		}
	}
}