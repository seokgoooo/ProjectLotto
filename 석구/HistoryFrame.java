import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HistoryFrame extends JFrame {
	private JCheckBox[][] basicBall = new JCheckBox[5][6];
	private JCheckBox[] bonusBall = new JCheckBox[5];
	private JPanel[] basicBallPnl = new JPanel[5];
	private JLabel[] textLbl = new JLabel[5];
	private JLabel[] plusLbl = new JLabel[5];

	private JCheckBox[][] basicBall2 = new JCheckBox[5][6];
	private JCheckBox[] bonusBall2 = new JCheckBox[5];
	private JPanel[] basicBallPnl2 = new JPanel[5];
	private JLabel[] textLbl2 = new JLabel[5];
	private JLabel[] plusLbl2 = new JLabel[5];

	private URL defaultBallImg = HistoryFrame.class.getClassLoader().getResource("buyDefault.png");

	public HistoryFrame() {
		super("역대당첨번호");
		Lotto js = new Lotto();
		js.setSet();
		js.setBonus();
		List<Integer> sort = js.SetNum(js.getSet());

		Random random = new Random();

		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		CardLayout card = new CardLayout();
		JPanel wrapPnl = new JPanel();
		wrapPnl.setLayout(null);
		wrapPnl.setBackground(Color.white);
		add(wrapPnl);

		JPanel cardPnl = new JPanel(card);
		cardPnl.setBounds(12, 72, 470, 490);

		JPanel cardPnlA = new JPanel();
		cardPnlA.setBounds(12, 72, 470, 490);
		cardPnl.add(cardPnlA, "A");

		JPanel cardPnlB = new JPanel();
		cardPnlB.setBounds(12, 72, 470, 490);
		cardPnl.add(cardPnlB, "B");

		JLabel mainlbl = new JLabel("역대 당첨 번호");
		mainlbl.setBounds(0, 0, 494, 62);
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		mainlbl.setOpaque(true);
		wrapPnl.add(mainlbl);
		wrapPnl.add(cardPnl);

		BoxLayout box = new BoxLayout(cardPnlA, BoxLayout.Y_AXIS);
		cardPnlA.setLayout(box);
		BoxLayout box2 = new BoxLayout(cardPnlB, BoxLayout.Y_AXIS);
		cardPnlB.setLayout(box2);

		for (int i = 0; i < basicBall.length; i++) {
			basicBallPnl[i] = new JPanel();

			textLbl[i] = new JLabel("제 " + (i + 1) + "회 ");
			textLbl[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
			basicBallPnl[i].add(textLbl[i]);

			for (int j = 0; j < 6; j++) {
				basicBall[i][j] = new JCheckBox();

				basicBall[0][j].setIcon(new ImageIcon(getColorNumber(sort.get(j))));
//				basicBall[i][j].setText(String.valueOf(js.LottoRound().get(i)));
				basicBallPnl[i].add(basicBall[i][j]);
			}

			plusLbl[i] = new JLabel("+");
			plusLbl[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
			basicBallPnl[i].add(plusLbl[i]);

			bonusBall[i] = new JCheckBox();
			bonusBall[i].setIcon(new ImageIcon(getColorNumber(js.getBonus())));
			basicBallPnl[i].add(bonusBall[i]);
			cardPnlA.add(basicBallPnl[i]);
		}

		for (int i = 0; i < basicBall2.length; i++) {
			basicBallPnl2[i] = new JPanel();
			textLbl2[i] = new JLabel("제 " + (i + basicBall.length + 1) + "회 ");
			textLbl2[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
			basicBallPnl2[i].add(textLbl2[i]);
			for (int j = 0; j < 6; j++) {
				basicBall2[i][j] = new JCheckBox();
				basicBall2[i][j].setIcon(new ImageIcon(getColorNumber(sort.get(i))));
				basicBallPnl2[i].add(basicBall2[i][j]);
			}
			plusLbl2[i] = new JLabel("+");
			plusLbl2[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
			basicBallPnl2[i].add(plusLbl2[i]);
			bonusBall2[i] = new JCheckBox();
			basicBallPnl2[i].add(bonusBall2[i]);
			bonusBall2[i].setIcon(new ImageIcon(defaultBallImg));
			cardPnlB.add(basicBallPnl2[i]);
		}
		card.show(cardPnl, "A");

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
					card.next(cardPnl); // next 다음 컴포넌트를 보여줌
				} else {
					card.previous(cardPnl); // previous 이전 컴포넌트를 보여줌
				}
			}
		};
		nextBtn.addActionListener(cardListener);
		prevBtn.addActionListener(cardListener);
	}

	public static void main(String[] args) {
		new HistoryFrame().setVisible(true);
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