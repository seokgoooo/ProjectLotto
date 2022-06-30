import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuyFrame extends JFrame implements ActionListener {
	private JCheckBox[] basicNumber;
	private JCheckBox[] clickNumber;
	private JCheckBox[] basicNumberCopy;
	private Integer[] setNum = { 1, 2, 3, 4, 5 };
	private Set<Integer> lottoNumberSet = new HashSet<>();

	public BuyFrame() {
		super("로또 구매");
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
		JLabel mainLabel = new JLabel();

		// 배경화면 image 적용
		mainLabel.setBounds(0, 0, 995, 580);
		URL url = BuyFrame.class.getClassLoader().getResource("back_01.png");
		mainLabel.setIcon(new ImageIcon(kit.getImage(url)));

		// 수동선택 버튼
		JButton firstButton = new JButton();
		firstButton.setBounds(45, 107, 138, 136);
		URL url1 = BuyFrame.class.getClassLoader().getResource("buyButton_01.png");
		firstButton.setIcon(new ImageIcon(kit.getImage(url1)));
		wrapPanel.add(firstButton);

		// 혼합선택 버튼
		JButton secondButton = new JButton();
		secondButton.setBounds(45, 245, 138, 136);
		URL url2 = BuyFrame.class.getClassLoader().getResource("buyButton_02.png");
		secondButton.setIcon(new ImageIcon(kit.getImage(url2)));
		wrapPanel.add(secondButton);

		// 자동선택 버튼
		JButton thirdButton = new JButton();
		thirdButton.setBounds(45, 383, 138, 136);
		URL url3 = BuyFrame.class.getClassLoader().getResource("buyButton_03.png");
		thirdButton.setIcon(new ImageIcon(kit.getImage(url3)));
		wrapPanel.add(thirdButton);

		// 수량 정하는 콤보박스
		JComboBox comboBox = new JComboBox(setNum);
		comboBox.setBounds(45, 530, 40, 20);
		wrapPanel.add(comboBox);

		// 확인 버튼
		JButton checkInButton = new JButton();
		checkInButton.setBounds(90, 530, 93, 20);
		URL url4 = BuyFrame.class.getClassLoader().getResource("buyButton_04.png");
		checkInButton.setIcon(new ImageIcon(kit.getImage(url4)));
		wrapPanel.add(checkInButton);

		// 초기화 버튼
		JButton resetButton = new JButton();
		resetButton.setBounds(280, 522, 125, 30);
		URL url5 = BuyFrame.class.getClassLoader().getResource("buyButton_05.png");
		resetButton.setIcon(new ImageIcon(kit.getImage(url5)));
		wrapPanel.add(resetButton);

		// 왼쪽 번호 부분 배치와 정렬
		GridLayout grid = new GridLayout(9, 5);
		leftPanel.setLayout(grid); // 패널안에서 정렬

		// default 값으로 흑백 번호 image 를 담을 배열
		basicNumber = new JCheckBox[45];
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i] = new JCheckBox();
			URL urlBlack = BuyFrame.class.getClassLoader().getResource("NumberBlack/" + (i + 1) + ".png");
			basicNumber[i].setIcon(new ImageIcon(kit.getImage(urlBlack)));
			basicNumber[i].addActionListener(this);
			leftPanel.add(basicNumber[i]);
		}

		// 클릭했을 때 바뀌는 컬러 번호 image 를 담을 배열
		clickNumber = new JCheckBox[45];
		for (int i = 0; i < clickNumber.length; i++) {
			clickNumber[i] = new JCheckBox();
			URL urlColor = BuyFrame.class.getClassLoader().getResource("NumberColor/" + (i + 1) + ".png");
			clickNumber[i].setIcon(new ImageIcon(kit.getImage(urlColor)));
		}

		// 2번 클릭했을 때 다시 원상태로 복귀하는 흑백 번호 image 를 담을 배열
		basicNumberCopy = new JCheckBox[45];
		for (int i = 0; i < basicNumberCopy.length; i++) {
			basicNumberCopy[i] = new JCheckBox();
			URL urlCopy = BuyFrame.class.getClassLoader().getResource("NumberBlack/" + (i + 1) + ".png");
			basicNumberCopy[i].setIcon(new ImageIcon(kit.getImage(urlCopy)));
		}
		wrapPanel.add(leftPanel);
		wrapPanel.add(rightPanel);
		wrapPanel.add(mainLabel);
	}

	// 1 ~ 45 번호를 클릭해 구매자 로또번호 Set에 저장하는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < basicNumber.length; i++) {
			if (e.getSource() == basicNumber[i]) {
				if (basicNumber[i].isSelected()) {
					basicNumber[i].setIcon(clickNumber[i].getIcon());
					lottoNumberSet.add(i + 1);
				} else {
					basicNumber[i].setIcon(basicNumberCopy[i].getIcon());
					lottoNumberSet.remove(i + 1);
				}
			}

			if (lottoNumberSet.size() == 6) {
				for (int j = 0; j < basicNumber.length; j++) {
					if (!basicNumber[j].isSelected()) {
						basicNumber[j].setEnabled(false);
					}
				}
			} else {
				lottoNumberAllSelected();
			}
		}
	}

	// 로또번호를 모두 활성화
	public void lottoNumberAllSelected() {
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i].setEnabled(true);
		}
	}

	// 로또번호를 모두 비활성화
	public void lottoNumberAllUnSelected() {
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i].setEnabled(false);
		}
	}

	// 로또번호를 모두 초기화
	public void lottoNumberAllReset() {
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i].setSelected(false);
		}
	}
}