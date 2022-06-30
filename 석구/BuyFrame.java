import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuyFrame extends JFrame implements ActionListener {
	private JCheckBox[] basicNumber;
	private JCheckBox[] clickNumber;
	private JCheckBox[] basicNumberCopy;
	private Set<Integer> set = new HashSet<>();

	public BuyFrame() {
		super("로또 구매");
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
		leftPanel.setBounds(222, 117, 242, 426);
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(505, 103, 467, 448);
		JLabel mainLabel = new JLabel();

		// 배경화면 image 적용
		try {
			File file = new File("resources/back_01.png");
			BufferedImage img = ImageIO.read(file);
			mainLabel.setBounds(0, 0, 995, 580);
			mainLabel.setIcon(new ImageIcon(img));
		} catch (IOException e) {

		}

		// 왼쪽 번호 부분 배치와 정렬
		GridLayout grid = new GridLayout(9, 5);
		leftPanel.setLayout(grid); // 패널안에서 정렬

		// default 값으로 흑백 번호 image 를 담을 배열
		basicNumber = new JCheckBox[45];
		for (int i = 0; i < basicNumber.length; i++) {
			try {
				basicNumber[i] = new JCheckBox();
				File file = new File("resources/NumberBlack/" + (i + 1) + ".png");
				BufferedImage img = ImageIO.read(file);
				basicNumber[i].setIcon(new ImageIcon(img));
				basicNumber[i].addActionListener(this);
				leftPanel.add(basicNumber[i]);
			} catch (IOException e) {

			}
		}

		// 클릭했을 때 바뀌는 컬러 번호 image 를 담을 배열
		clickNumber = new JCheckBox[45];
		for (int i = 0; i < clickNumber.length; i++) {
			clickNumber[i] = new JCheckBox();
			ImageIcon icon = new ImageIcon("resources/NumberColor/" + (i + 1) + ".png");
			Image img = icon.getImage();
			ImageIcon changeIcon = new ImageIcon(img);
			clickNumber[i].setIcon(changeIcon);
		}

		// 2번 클릭했을 때 다시 원상태로 복귀하는 흑백 번호 image 를 담을 배열
		basicNumberCopy = new JCheckBox[45];
		for (int i = 0; i < basicNumberCopy.length; i++) {
			basicNumberCopy[i] = new JCheckBox();
			ImageIcon icon = new ImageIcon("resources/NumberBlack/" + (i + 1) + ".png");
			Image img = icon.getImage();
			ImageIcon changeIcon2 = new ImageIcon(img);
			basicNumberCopy[i].setIcon(changeIcon2);
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
					set.add(i + 1);
				} else {
					basicNumber[i].setIcon(basicNumberCopy[i].getIcon());
					set.remove(i + 1);
				}
			}

			if (set.size() == 6) {
				for (int j = 0; j < basicNumber.length; j++) {
					if (!basicNumber[j].isSelected()) {
						basicNumber[j].setEnabled(false);
					}
				}
			} else {
				checkboxAllTrue();
			}
		}
	}

	// checkbox를 모두 활성화
	public void checkboxAllTrue() {
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i].setEnabled(true);
		}
	}

	// checkbox를 모두 비활성화
	public void checkboxAllFalse() {
		for (int i = 0; i < basicNumber.length; i++) {
			basicNumber[i].setEnabled(false);
		}
	}
}