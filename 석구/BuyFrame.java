import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BuyFrame extends JFrame implements ActionListener {
	private JCheckBox[] basicNumberBox;
	private JCheckBox[] clickNumberBox;
	private JCheckBox[] basicNumberCopyBox;
	private JCheckBox[] rightNumberBox;
	private Integer[] setNum = { 1, 2, 3, 4, 5 };

	private Consumer consumer = new Consumer();
	private Set<Integer> lottoNumberSet = new HashSet<>();

	private JPanel[] numberPanel = new JPanel[5];
	private JLabel[] rightLabel = new JLabel[5];
	private JButton[] changeNumberButton = new JButton[5];
	private JButton[] deleteNumberButton = new JButton[5];
	private JButton[] copyNumberButton = new JButton[5];

	private boolean changeTrue = false;
	private List<Integer> copyList;

	public BuyFrame() {
		super("로또 구매");
		List<List<Integer>> lottoList = new ArrayList<>();

		Toolkit kit = Toolkit.getDefaultToolkit();
		setSize(1000, 600);
		setBackground(Color.white);

		setResizable(false);
		setLocationRelativeTo(null);

		// 전체 담는 패널
		JPanel wrapPanel = new JPanel();
		wrapPanel.setLayout(null);
		add(wrapPanel);

		// 왼쪽 오른쪽 구간을 구분 지을 패널
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(222, 144, 242, 373);

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(505, 107, 467, 444);
		rightPanel.setLayout(null);

		JLabel mainLabel = new JLabel();
		mainLabel.setBounds(0, 0, 995, 580);

		// 배경화면 image 적용
		URL url = BuyFrame.class.getClassLoader().getResource("back_01.png");
		mainLabel.setIcon(new ImageIcon(kit.getImage(url)));

//		-----------------------------------------------------------------------------------------
//		왼쪽 (구매) Frame 시작
//		왼쪽 라디오 버튼들 (수동, 혼합, 자동)
//		-----------------------------------------------------------------------------------------
		// 수동선택 라디오 버튼
		JRadioButton manualRadioButton = new JRadioButton();
		manualRadioButton.setBounds(45, 107, 138, 136);
		URL manualUrl = BuyFrame.class.getClassLoader().getResource("buyButton_01.png");
		manualRadioButton.setIcon(new ImageIcon(kit.getImage(manualUrl)));
		URL manualSelectedUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_01.png");
		manualRadioButton.setSelectedIcon(new ImageIcon(kit.getImage(manualSelectedUrl)));
		manualRadioButton.setSelected(true);
		wrapPanel.add(manualRadioButton);

		// 혼합선택 라디오 버튼
		JRadioButton semiAutoRadioButton = new JRadioButton();
		semiAutoRadioButton.setBounds(45, 245, 138, 136);
		URL semiAutoUrl = BuyFrame.class.getClassLoader().getResource("buyButton_02.png");
		semiAutoRadioButton.setIcon(new ImageIcon(kit.getImage(semiAutoUrl)));
		URL semiAutoSelectedUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_02.png");
		semiAutoRadioButton.setSelectedIcon(new ImageIcon(kit.getImage(semiAutoSelectedUrl)));
		wrapPanel.add(semiAutoRadioButton);

		// 자동선택 라디오 버튼
		JRadioButton autoRadioButton = new JRadioButton();
		autoRadioButton.setBounds(45, 383, 138, 136);
		URL autoUrl = BuyFrame.class.getClassLoader().getResource("buyButton_03.png");
		autoRadioButton.setIcon(new ImageIcon(kit.getImage(autoUrl)));
		URL autoSelectedUrl = BuyFrame.class.getClassLoader().getResource("selectedBuyButton_03.png");
		autoRadioButton.setSelectedIcon(new ImageIcon(kit.getImage(autoSelectedUrl)));
		wrapPanel.add(autoRadioButton);

		// 라디오 버튼 그룹
		ButtonGroup leftRadioGroup = new ButtonGroup();
		leftRadioGroup.add(manualRadioButton);
		leftRadioGroup.add(semiAutoRadioButton);
		leftRadioGroup.add(autoRadioButton);

		manualRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lottoNumberAllSelected();
				lottoNumberAllReset();
				lottoNumberSet.removeAll(lottoNumberSet);
			}
		});

		semiAutoRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lottoNumberAllSelected();
				lottoNumberAllReset();
				lottoNumberSet.removeAll(lottoNumberSet);
			}
		});

		autoRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lottoNumberAllUnSelected();
				lottoNumberAllReset();
				lottoNumberSet.removeAll(lottoNumberSet);
			}
		});

//		-----------------------------------------------------------------------------------------
		// 왼쪽 번호 부분 배치와 정렬
		GridLayout grid = new GridLayout(9, 5);
		leftPanel.setLayout(grid); // 패널안에서 정렬

		// default 값으로 흑백 번호 image 를 담을 배열
		basicNumberBox = new JCheckBox[45];
		for (int i = 0; i < basicNumberBox.length; i++) {
			basicNumberBox[i] = new JCheckBox();
			URL blackUrl = BuyFrame.class.getClassLoader().getResource("NumberBlack/" + (i + 1) + ".png");
			basicNumberBox[i].setIcon(new ImageIcon(kit.getImage(blackUrl)));
			basicNumberBox[i].addActionListener(this);
			leftPanel.add(basicNumberBox[i]);
		}

		// 클릭했을 때 바뀌는 컬러 번호 image 를 담을 배열
		clickNumberBox = new JCheckBox[45];
		for (int i = 0; i < clickNumberBox.length; i++) {
			clickNumberBox[i] = new JCheckBox();
			URL colorUrl = BuyFrame.class.getClassLoader().getResource("NumberColor/" + (i + 1) + ".png");
			clickNumberBox[i].setIcon(new ImageIcon(kit.getImage(colorUrl)));
		}

		// 2번 클릭했을 때 다시 원상태로 복귀하는 흑백 번호 image 를 담을 배열
		basicNumberCopyBox = new JCheckBox[45];
		for (int i = 0; i < basicNumberCopyBox.length; i++) {
			basicNumberCopyBox[i] = new JCheckBox();
			URL copyUrl = BuyFrame.class.getClassLoader().getResource("NumberBlack/" + (i + 1) + ".png");
			basicNumberCopyBox[i].setIcon(new ImageIcon(kit.getImage(copyUrl)));
		}

//		-----------------------------------------------------------------------------------------

		// 번호 초기화 버튼
		JButton leftResetButton = new JButton();
		leftResetButton.setBounds(280, 522, 125, 30);
		URL leftResetUrl = BuyFrame.class.getClassLoader().getResource("buyButton_05.png");
		leftResetButton.setIcon(new ImageIcon(kit.getImage(leftResetUrl)));
		wrapPanel.add(leftResetButton);

		leftResetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lottoNumberAllReset();
				lottoNumberSet.removeAll(lottoNumberSet);
			}
		});

		// 수량 정하는 콤보박스
		JComboBox<Integer> purchaseCombo = new JComboBox<>(setNum);
		purchaseCombo.setBounds(45, 530, 40, 20);
		wrapPanel.add(purchaseCombo);

		JLabel priceLabel = new JLabel("지불 예정 금액 : " + 1000 + "원");
		priceLabel.setBounds(250, 100, 200, 50);
		priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		wrapPanel.add(priceLabel);

		purchaseCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p = (purchaseCombo.getSelectedIndex() + 1) * 1000;
				priceLabel.setText("지불 예정 금액 : " + p + "원");
			}
		});

		// 확인 버튼
		JButton leftCheckInButton = new JButton();
		leftCheckInButton.setBounds(90, 530, 93, 20);
		URL leftCheckInUrl = BuyFrame.class.getClassLoader().getResource("buyButton_04.png");
		leftCheckInButton.setIcon(new ImageIcon(kit.getImage(leftCheckInUrl)));
		wrapPanel.add(leftCheckInButton);

		leftCheckInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		wrapPanel.add(leftPanel);
		wrapPanel.add(rightPanel);
		wrapPanel.add(mainLabel);

//		-----------------------------------------------------------------------------------------
//		왼쪽 (구매) Frame 끝

//		오른쪽 (구매확인) Frame 시작
//		-----------------------------------------------------------------------------------------
		JLabel inputLabel = new JLabel("선택금액");
		inputLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		inputLabel.setBounds(222, 111, 242, 23);
		inputLabel.setBackground(Color.darkGray);
		inputLabel.setHorizontalAlignment(JLabel.CENTER);
		wrapPanel.add(inputLabel);

		JLabel checkNumberLabel = new JLabel("선택번호 확인");
		checkNumberLabel.setBounds(25, 15, 120, 30);
		checkNumberLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		rightPanel.add(checkNumberLabel);

		JButton smallResetButton = new JButton();
		smallResetButton.setBounds(390, 15, 60, 30);
		URL smallResetUrl = BuyFrame.class.getClassLoader().getResource("buyButton_07.png");
		smallResetButton.setIcon(new ImageIcon(kit.getImage(smallResetUrl)));
		rightPanel.add(smallResetButton);

		for (int i = 0; i < 5; i++) {
			numberPanel[i] = new JPanel();
			changeNumberButton[i] = new JButton();
			deleteNumberButton[i] = new JButton();
			copyNumberButton[i] = new JButton();
			numberPanel[i].setLayout(null);

			char c = (char) ('A' + i);
			rightLabel[i] = new JLabel(String.valueOf(c));
			numberPanel[i].add(rightLabel[i]);
			rightLabel[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
			changeNumberButton[i] = new JButton("수정");
			changeNumberButton[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			changeNumberButton[i].setBackground(Color.white);

			numberPanel[i].add(changeNumberButton[i]);
			deleteNumberButton[i] = new JButton("삭제");
			deleteNumberButton[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			deleteNumberButton[i].setBackground(Color.white);

			numberPanel[i].add(deleteNumberButton[i]);
			copyNumberButton[i] = new JButton("번호복사");
			copyNumberButton[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 10));
			copyNumberButton[i].setBackground(Color.white);
			numberPanel[i].add(copyNumberButton[i]);
			rightPanel.add(numberPanel[i]);
		}

		numberPanel[0].setBounds(0, 67, 467, 64);
		numberPanel[1].setBounds(0, 130, 467, 64);
		numberPanel[2].setBounds(0, 193, 467, 64);
		numberPanel[3].setBounds(0, 257, 467, 64);
		numberPanel[4].setBounds(0, 320, 467, 64);

		rightLabel[0].setBounds(25, 26, 19, 15);
		rightLabel[1].setBounds(25, 27, 18, 15);
		rightLabel[2].setBounds(25, 27, 19, 15);
		rightLabel[3].setBounds(25, 23, 18, 15);
		rightLabel[4].setBounds(25, 25, 17, 15);

		rightNumberBox = new JCheckBox[30];
		for (int i = 0; i < rightNumberBox.length; i++) {
			rightNumberBox[i] = new JCheckBox();
			numberPanel[0].add(rightNumberBox[i]);
		}

		changeNumberButton[0].setBounds(245, 22, 60, 23);
		changeNumberButton[1].setBounds(245, 18, 60, 23);
		changeNumberButton[2].setBounds(245, 23, 60, 23);
		changeNumberButton[3].setBounds(245, 19, 60, 23);
		changeNumberButton[4].setBounds(245, 21, 60, 23);

		deleteNumberButton[0].setBounds(310, 22, 60, 23);
		deleteNumberButton[1].setBounds(310, 19, 60, 23);
		deleteNumberButton[2].setBounds(310, 23, 60, 23);
		deleteNumberButton[3].setBounds(310, 19, 60, 23);
		deleteNumberButton[4].setBounds(310, 21, 60, 23);

		copyNumberButton[0].setBounds(375, 22, 75, 23);
		copyNumberButton[1].setBounds(375, 19, 75, 23);
		copyNumberButton[2].setBounds(375, 23, 75, 23);
		copyNumberButton[3].setBounds(375, 19, 75, 23);
		copyNumberButton[4].setBounds(375, 21, 75, 23);

		JPanel rightNumberPanel = new JPanel();
		rightNumberPanel.setBounds(55, -63, 178, 317);
		numberPanel[1].add(rightNumberPanel);

		JLabel rightTotalTextLabel = new JLabel("결제금액 : ");
		rightTotalTextLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		rightTotalTextLabel.setBounds(158, 388, 120, 50);
		rightPanel.add(rightTotalTextLabel);

		JLabel rightTotalPriceLabel = new JLabel("2000원");
		rightTotalPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		rightTotalPriceLabel.setBounds(260, 388, 120, 50);
		rightPanel.add(rightTotalPriceLabel);

		JButton rightBuyButton = new JButton();
		rightBuyButton.setBounds(340, 390, 120, 50);
		URL buyUrl = BuyFrame.class.getClassLoader().getResource("buyButton_04.png");
		rightBuyButton.setIcon(new ImageIcon(kit.getImage(buyUrl)));
		rightPanel.add(rightBuyButton);
	}

	// 1 ~ 45 번호를 클릭해 구매자 로또번호 Set에 저장하는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < basicNumberBox.length; i++) {
			if (e.getSource() == basicNumberBox[i]) {
				if (basicNumberBox[i].isSelected()) {
					basicNumberBox[i].setIcon(clickNumberBox[i].getIcon());
					lottoNumberSet.add(i + 1);
				} else {
					basicNumberBox[i].setIcon(basicNumberCopyBox[i].getIcon());
					lottoNumberSet.remove(i + 1);
				}
			}

			if (lottoNumberSet.size() == 6) {
				for (int j = 0; j < basicNumberBox.length; j++) {
					if (!basicNumberBox[j].isSelected()) {
						basicNumberBox[j].setEnabled(false);
					}
				}
			} else {
				lottoNumberAllSelected();
			}
		}
	}

	// 로또번호를 모두 활성화
	public void lottoNumberAllSelected() {
		for (int i = 0; i < basicNumberBox.length; i++) {
			basicNumberBox[i].setEnabled(true);
		}
	}

	// 로또번호를 모두 비활성화
	public void lottoNumberAllUnSelected() {
		for (int i = 0; i < basicNumberBox.length; i++) {
			basicNumberBox[i].setEnabled(false);
		}
	}

	// 로또번호를 모두 초기화
	public void lottoNumberAllReset() {
		for (int i = 0; i < basicNumberBox.length; i++) {
			basicNumberBox[i].setSelected(false);
		}
	}
}