import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;

public class HistoryFrame extends JFrame {
	private List<List<Integer>> lottoList = new ArrayList<>();
	private Random random = new Random(); // 랜덤객체 필드
	private JCheckBox[][][] basicBall = new JCheckBox[2][5][6];
	private JCheckBox[][] bonusBall = new JCheckBox[2][5];
	private JPanel[][] basicBallPnl = new JPanel[2][5];
	private JLabel[][] textLbl = new JLabel[2][5];
	private JLabel[][] plusLbl = new JLabel[2][5];
	private Integer[] bonus = new Integer[10];

	public HistoryFrame() {
		super("역대당첨번호");
		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		for (int i = 0; i < 10; i++) {
			List<Integer> bonusList = lotto();
			lottoList.add(bonusList);
			bonus[i] = setBonus(bonusList);

		}

		CardLayout card = new CardLayout();
		JPanel[] cardPnl = new JPanel[2];

		JPanel wrapPnl = new JPanel();

		wrapPnl.setLayout(null);
		wrapPnl.setBackground(Color.white);
		getContentPane().add(wrapPnl);

		JPanel subPnl = new JPanel(card);
		subPnl.setBounds(12, 72, 470, 490);

		cardPnl[0] = new JPanel();
		cardPnl[0].setBounds(12, 72, 470, 490);
		subPnl.add(cardPnl[0], "A");

		cardPnl[1] = new JPanel();
		cardPnl[1].setBounds(12, 72, 470, 490);
		subPnl.add(cardPnl[1], "B");

		JLabel mainlbl = new JLabel("역대 당첨 번호");
		mainlbl.setBounds(0, 0, 494, 62);
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		mainlbl.setOpaque(true);
		wrapPnl.add(mainlbl);
		wrapPnl.add(subPnl);

		BoxLayout box = new BoxLayout(cardPnl[0], BoxLayout.Y_AXIS);
		cardPnl[0].setLayout(box);
		BoxLayout box2 = new BoxLayout(cardPnl[1], BoxLayout.Y_AXIS);
		cardPnl[1].setLayout(box2);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				basicBallPnl[i][j] = new JPanel();

				textLbl[i][j] = new JLabel("제 " + String.format("%02d", (i * 5 + j + 1)) + "회 ");
				textLbl[i][j].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
				basicBallPnl[i][j].add(textLbl[i][j]);

				for (int k = 0; k < 6; k++) {
					basicBall[i][j][k] = new JCheckBox();
					basicBall[i][j][k].setIcon(new ImageIcon(getColorNumber(lottoList.get(i * 5 + j).get(k))));
					basicBallPnl[i][j].add(basicBall[i][j][k]);
				}

				plusLbl[i][j] = new JLabel("+");
				plusLbl[i][j].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
				basicBallPnl[i][j].add(plusLbl[i][j]);

				bonusBall[i][j] = new JCheckBox();
				bonusBall[i][j].setIcon(new ImageIcon(getColorNumber(bonus[j + i * 5])));
				basicBallPnl[i][j].add(bonusBall[i][j]);
				cardPnl[i].add(basicBallPnl[i][j]);
			}
		}

		card.show(subPnl, "A");

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