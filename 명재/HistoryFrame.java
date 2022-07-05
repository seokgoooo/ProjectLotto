import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.JPanel;

public class HistoryFrame extends JFrame {
	private List<List<Integer>> lottoList = new ArrayList<>();
	private Random random = new Random(); // 랜덤객체 필드
	private JCheckBox[][][] basicBall;
	private JCheckBox[][] bonusBall;
	private JPanel[][] basicBallPnl;
	private JLabel[][] textLbl;
	private JLabel[][] plusLbl;
	private List<Integer> currentLotto = new ArrayList<>();
	private Integer currentBonus = 0;
	private List<Integer> bonus = new ArrayList<>();
	private URL defaultBallImg = BuyFrame.class.getClassLoader().getResource("buyDefault.png");
	private ImageIcon defaultBall = new ImageIcon(defaultBallImg);
	private JPanel subPnl;
	private CardLayout card;
	private JPanel[] cardPnls;
	private JPanel wrapPnl;
	private int paintCount = 0;
	private int currentIndex = 0;

	public List<List<Integer>> getLottoList() {
		return lottoList;
	}

	public void setLottoList(List<List<Integer>> lottoList) {
		this.lottoList = lottoList;
	}

	public List<Integer> getCurrentLotto() {
		return currentLotto;
	}

	public void setCurrentLotto(List<Integer> currentLotto) {
		this.currentLotto = currentLotto;
		lottoList.add(currentLotto);

	}

	public Integer getCurrentBonus() {
		return currentBonus;
	}

	public void setCurrentBonus(Integer currentBonus) {
		this.currentBonus = currentBonus;
		bonus.add(currentBonus);

	}

	public HistoryFrame() {
		super("역대당첨번호");
		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		for (int i = 0; i < 10; i++) {
			List<Integer> bonusList = lotto();
			lottoList.add(bonusList);
			bonus.add(setBonus(bonusList));
		}

		lottoList.add(currentLotto);
		bonus.add(currentBonus);

		repaintHistoryFrame();

		JLabel mainlbl = new JLabel("역대 당첨 번호");
		mainlbl.setBounds(0, 0, 494, 62);
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		mainlbl.setOpaque(true);
		wrapPnl.add(mainlbl);
		wrapPnl.add(subPnl);

		JButton nextBtn = new JButton(">>");
		nextBtn.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		nextBtn.setBackground(Color.white);

		JButton prevBtn = new JButton("<<");
		prevBtn.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		prevBtn.setBackground(Color.white);

		JPanel southPnl = new JPanel();
		southPnl.add(prevBtn);
		southPnl.add(nextBtn);
		southPnl.setBackground(Color.white);
		add(southPnl, "South");

		ActionListener cardListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals(">>")) {
					card.next(subPnl); // next 다음 컴포넌트를 보여줌
				} else {
					card.previous(subPnl); // previous 이전 컴포넌트를 보여줌
				}
			}
		};
		nextBtn.addActionListener(cardListener);
		prevBtn.addActionListener(cardListener);

	}

	public void repaintHistoryFrame() {
		int index = (int) Math.ceil(lottoList.size() / 5.0);
		if (paintCount == 0) {
			basicBall = new JCheckBox[index][5][6];
			bonusBall = new JCheckBox[index][5];
			basicBallPnl = new JPanel[index][5];
			textLbl = new JLabel[index][5];
			plusLbl = new JLabel[index][5];

			card = new CardLayout();
			cardPnls = new JPanel[index];

			wrapPnl = new JPanel();

			wrapPnl.setLayout(null);
			wrapPnl.setBackground(Color.white);
			getContentPane().add(wrapPnl);

			subPnl = new JPanel(card);
			subPnl.setBounds(12, 72, 470, 490);

			for (int i = 0; i < cardPnls.length; i++) {
				cardPnls[i] = new JPanel();
				BoxLayout box = new BoxLayout(cardPnls[i], BoxLayout.Y_AXIS);
				cardPnls[i].setLayout(box);
				cardPnls[i].setBounds(12, 72, 470, 490);
				subPnl.add(cardPnls[i], String.valueOf((int) ('A' + i)));
			}

			for (int i = 0; i < index; i++) {
				for (int j = 0; j < 5; j++) {
					if (i * 5 + j == lottoList.size()) {
						break;
					}
					basicBallPnl[i][j] = new JPanel();

					textLbl[i][j] = new JLabel("제 " + String.format("%02d", (i * 5 + j + 1)) + "회 ");
					textLbl[i][j].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
					basicBallPnl[i][j].add(textLbl[i][j]);

					for (int k = 0; k < 6; k++) {
						basicBall[i][j][k] = new JCheckBox();
						if (lottoList.get(i * 5 + j).size() == 0) {
							basicBall[i][j][k].setIcon(defaultBall);
							currentIndex = j;
						} else {
							basicBall[i][j][k].setIcon(new ImageIcon(getColorNumber(lottoList.get(i * 5 + j).get(k))));
						}
						basicBallPnl[i][j].add(basicBall[i][j][k]);
					}

					plusLbl[i][j] = new JLabel("+");
					plusLbl[i][j].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
					basicBallPnl[i][j].add(plusLbl[i][j]);

					bonusBall[i][j] = new JCheckBox();
					if (lottoList.get(i * 5 + j).size() == 0) {
						bonusBall[i][j].setIcon(defaultBall);
					} else {
						bonusBall[i][j].setIcon(new ImageIcon(getColorNumber(bonus.get(j + i * 5))));
					}
					basicBallPnl[i][j].add(bonusBall[i][j]);
					cardPnls[i].add(basicBallPnl[i][j]);
				}
			}

			card.show(subPnl, "A");
			paintCount++;
		} else {
			if (currentLotto.size() == 0) {

			} else {
				if (lottoList.size() % 5 == 1) {
					for (int j = 0; j < 6; j++) {
						basicBall[index - 2][currentIndex][j]
								.setIcon(new ImageIcon(getColorNumber(currentLotto.get(j))));
					}

					bonusBall[index - 2][currentIndex].setIcon(new ImageIcon(getColorNumber(this.currentBonus)));
				} else {
					for (int j = 0; j < 6; j++) {
						basicBall[index - 1][currentIndex][j]
								.setIcon(new ImageIcon(getColorNumber(currentLotto.get(j))));
					}

					bonusBall[index - 1][currentIndex].setIcon(new ImageIcon(getColorNumber(this.currentBonus)));
				}
				System.out.println(basicBallPnl.length);
				if (currentIndex == 4) {
					cardPnls = Arrays.copyOf(cardPnls, cardPnls.length + 1);
					
					basicBallPnl = Arrays.copyOf(basicBallPnl, basicBallPnl.length + 1);
					basicBallPnl[index - 1] = new JPanel[5];
					
					basicBall = Arrays.copyOf(basicBall, basicBall.length + 1);
					basicBall[index - 1] = new JCheckBox[5][6];
					
					textLbl = Arrays.copyOf(textLbl, textLbl.length + 1);
					textLbl[index - 1] = new JLabel[5];
					
					plusLbl = Arrays.copyOf(plusLbl, plusLbl.length + 1);
					plusLbl[index - 1] = new JLabel[5];
					
					bonusBall = Arrays.copyOf(bonusBall, bonusBall.length + 1);
					bonusBall[index - 1] = new JCheckBox[5];

					cardPnls[index - 1] = new JPanel();
					BoxLayout box = new BoxLayout(cardPnls[index - 1], BoxLayout.Y_AXIS);
					cardPnls[index - 1].setLayout(box);
					cardPnls[index - 1].setBounds(12, 72, 470, 490);
					subPnl.add(cardPnls[index - 1], String.valueOf((int) ('A' + (index - 1))));

					currentIndex = 0;
				} else {
					currentIndex++;
				}

				basicBallPnl[index - 1][currentIndex] = new JPanel();
				textLbl[index - 1][currentIndex] = new JLabel("제 " + String.format("%02d", lottoList.size()) + "회 ");
				textLbl[index - 1][currentIndex].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
				basicBallPnl[index - 1][currentIndex].add(textLbl[index - 1][currentIndex]);

				for (int j = 0; j < 6; j++) {
					basicBall[index - 1][currentIndex][j] = new JCheckBox();
					basicBall[index - 1][currentIndex][j].setIcon(defaultBall);
					basicBallPnl[index - 1][currentIndex].add(basicBall[index - 1][currentIndex][j]);
				}

				plusLbl[index - 1][currentIndex] = new JLabel("+");
				plusLbl[index - 1][currentIndex].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
				basicBallPnl[index - 1][currentIndex].add(plusLbl[index - 1][currentIndex]);

				bonusBall[index - 1][currentIndex] = new JCheckBox();

				bonusBall[index - 1][currentIndex].setIcon(defaultBall);

				basicBallPnl[index - 1][currentIndex].add(bonusBall[index - 1][currentIndex]);
				cardPnls[index - 1].add(basicBallPnl[index - 1][currentIndex]);
			}
			card.show(subPnl, "A");
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
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

	public int setBonus(List<Integer> list) {
		int bonus = 0;
		while (true) {
			bonus = random.nextInt(45) + 1;
			if (!list.contains(bonus)) {
				break;
			}
		}
		return bonus;
	}

	public URL getBlackNumber(int i) {
		URL url = HistoryFrame.class.getClassLoader().getResource("NumberBlack/" + (i) + ".png");
		return url;
	}

	public URL getColorNumber(int i) {
		URL url = HistoryFrame.class.getClassLoader().getResource("NumberColor/" + (i) + ".png");
		return url;
	}
}