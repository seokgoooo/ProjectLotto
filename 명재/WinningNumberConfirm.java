import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;

public class WinningNumberConfirm extends JFrame {
	private List<Integer> lottoList;
	private Set<Integer> set;
	private List<List<Integer>> list = new ArrayList<>();
	private Random random;
	private Integer bonus;

	public WinningNumberConfirm() {
		super("당첨 확인");

		JPanel allPnl = new JPanel();
		JPanel topPnl = new JPanel();
		
		JPanel myLottoNumberPnl = new JPanel();
		myLottoNumberPnl.setFont(new Font("굴림", Font.PLAIN, 20));
		JScrollPane myLottoScrollPane = new JScrollPane();

		BoxLayout allBox = new BoxLayout(allPnl, BoxLayout.Y_AXIS);
		allPnl.setLayout(allBox);

		BoxLayout myLottoBox = new BoxLayout(myLottoNumberPnl, BoxLayout.Y_AXIS);
		myLottoNumberPnl.setLayout(myLottoBox);

		winningNumber();
		list.add(lottoList);
		listMake();
		System.out.println(lottoList);
		System.out.println(list.size());
		
//		topPnl 부분
// --------------------------------------------------------------------------------
		JLabel titleLbl = new JLabel("제 **** 회차 번호 ");
		JLabel[] lottoNumberLbl = new JLabel[8];

		topPnl.add(titleLbl);

		for (int i = 0; i < lottoList.size(); i++) {
			lottoNumberLbl[i] = new JLabel(String.format("%02d", lottoList.get(i)));
			topPnl.add(lottoNumberLbl[i]);
		}

		lottoNumberLbl[6] = new JLabel("Bonus");
		topPnl.add(lottoNumberLbl[6]);
		lottoNumberLbl[7] = new JLabel(String.format("%02d", bonus));
		topPnl.add(lottoNumberLbl[7]);

// --------------------------------------------------------------------------------

// 		myLottoNumberPnl 부분
// --------------------------------------------------------------------------------	

		JPanel[] myLottoPnl = new JPanel[list.size()];
		JLabel[][] myLottoLbl = new JLabel[myLottoPnl.length][3];

		for (int i = 0; i < myLottoPnl.length; i++) {
			myLottoPnl[i] = new JPanel();
//			BoxLayout myLottoPnlBox = new BoxLayout(myLottoPnl[i], BoxLayout.X_AXIS);
//			myLottoPnl[i].setLayout(myLottoPnlBox);
			
			for (int j = 0; j < myLottoLbl[i].length; j++) {
				myLottoLbl[i][j] = new JLabel();
				myLottoPnl[i].add(myLottoLbl[i][j]);
			}
			myLottoNumberPnl.add(myLottoPnl[i]);

		}
		
		for(int i = 0; i < myLottoPnl.length;i++) {
			char c = (char) ('A' + i);
			myLottoLbl[i][0].setText(String.valueOf(c));
		}
		
		for(int i = 0; i < myLottoPnl.length;i++) {
			
			myLottoLbl[i][2].setText(String.valueOf(list.get(i)));
			
			List<Integer> listDuplicate = new ArrayList<>();
			listDuplicate.addAll(lottoList);
			listDuplicate.retainAll(list.get(i));
			if(listDuplicate.size() == 6) {
				myLottoLbl[i][1].setText("1등");
			} else if(listDuplicate.size() == 5) {
				if(list.get(i).contains(bonus)) {
					myLottoLbl[i][1].setText("2등");
				} else {
					myLottoLbl[i][1].setText("3등");
				}
			} else if(listDuplicate.size() == 4) {
				myLottoLbl[i][1].setText("4등");
			} else if(listDuplicate.size() == 3) {
				myLottoLbl[i][1].setText("5등");
			} else {
				myLottoLbl[i][1].setText("아쉽지만 다음 기회에");
			}
			
		}
		
		myLottoScrollPane.setViewportView(myLottoNumberPnl);

// --------------------------------------------------------------------------------

		
		
// --------------------------------------------------------------------------------

		allPnl.add(topPnl);
		allPnl.add(myLottoScrollPane);
		getContentPane().add(allPnl);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Set<Integer> lotto() {
		Set<Integer> set = new HashSet<>();
		random = new Random();

		while (set.size() < 6) {
			set.add(random.nextInt(45) + 1);
		}

		return set;
	}

	public void winningNumber() {
		Set<Integer> lottoSet = new HashSet<>(); // 확인을 위한 lotto번호 실제로는 값을 가져 와서 비교
		random = new Random();

		lottoSet = lotto();

		while (true) {
			int number = random.nextInt(45) + 1;
			if (!lottoSet.contains(number)) {
				bonus = number;
				break;
			}
		}
		lottoList = new ArrayList<>(lottoSet);
		Collections.sort(lottoList);
	}

	public void listMake() {
		for (int i = 0; i < 100; i++) {
			List<Integer> loList = new ArrayList<>(lotto());
			Collections.sort(loList);
			list.add(loList);
		}
	}

	public static void main(String[] args) {
		new WinningNumberConfirm().setVisible(true);
	}
}
