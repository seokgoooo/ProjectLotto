import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PurchaseCheckBox extends JFrame implements ActionListener {
	private JCheckBox[] cbs;
	private Set<Integer> set = new HashSet<>();
	private String s;

	public PurchaseCheckBox() {
		
		List<Set<Integer>> list = new ArrayList<>();
		JPanel pnlAll = new JPanel();
		JPanel pnl = new JPanel();
		JPanel bPnl = new JPanel();

		JButton btn = new JButton("구매");

		GridLayout grid = new GridLayout(5, 9);
		pnl.setLayout((grid));
		cbs = new JCheckBox[45];
		for (int i = 0; i < cbs.length; i++) {
			cbs[i] = new JCheckBox(String.valueOf(i + 1));
			cbs[i].addActionListener(this);
			pnl.add(cbs[i]);
		}

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (set.size() > 6) {
					JOptionPane.showMessageDialog(pnl, "복권 번호는 6개 까지만 선택 가능합니다.");

				} else if (set.size() < 6) {
					s = "반자동";
					while (set.size() < 6) {
						set.add(new Random().nextInt(45) + 1);
					}
					System.out.println(set);
					list.add(set);
					for (int i = 0; i < cbs.length; i++) {
						cbs[i].setSelected(false);
					}
					set.removeAll(set);
				} else {
					s = "수동";
					list.add(set);
					System.out.println(set);
					for (int i = 0; i < cbs.length; i++) {
						cbs[i].setSelected(false);
					}
					set.removeAll(set);
				}
				System.out.println(list); // list에 set이 담기지않음 
				PurchaseConfirm dialog = new PurchaseConfirm(PurchaseCheckBox.this); 
				dialog.setVisible(true);
				dialog.setList(list);

			}
		});

		bPnl.add(btn);
		pnlAll.add(pnl);
		pnlAll.add(bPnl);
		add(pnlAll);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new PurchaseCheckBox().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < cbs.length; i++) {
			if (e.getSource() == cbs[i]) {
				if (cbs[i].isSelected()) {
					set.add(i + 1);
				} else {
					set.remove(i + 1);					
				}
			}

		}

	}
}
