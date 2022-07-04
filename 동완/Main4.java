package 동완;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main4 extends JFrame {
	private JCheckBox[][] Rball = new JCheckBox[5][6];
	private JPanel[] ballPnl = new JPanel[5];
	private JLabel[] lblA = new JLabel[5];
	private JLabel[] bonus1 = new JLabel[5];
	private JCheckBox[] bonusBall = new JCheckBox[5];
	
	private JCheckBox[][] Rball2 = new JCheckBox[5][6];
	private JPanel[] ballPnl2 = new JPanel[5];
	private JLabel[] lblA2 = new JLabel[5];
	private JLabel[] bonus2 = new JLabel[5];
	private JCheckBox[] bonusBall2 = new JCheckBox[5];
	
	
	public Main4() {
		super("역대당첨번호");
		CardLayout layout = new CardLayout();
		
		JPanel mainPnl = new JPanel();
		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);
		mainPnl.setBackground(Color.white);
		
		JPanel subPnl = new JPanel(layout);
		subPnl.setBounds(12, 72, 470, 490);
		
		

//		JPanel[] pnlL = new JPanel[2];
//		List<List<Integer>> list;
//		
//		for(int i = 0; i < pnlL.length;i++) {
//			pnlL[i] = new JPanel()
//			for(int j = 0; j < 5;j++) {
//				for(int k = 0)
//			}
//			
//		}
		
		JPanel pnlList = new JPanel();
		pnlList.setBounds(12, 72, 470, 490);
		subPnl.add(pnlList, "A");
		
		JPanel pnlList2 = new JPanel();
		pnlList2.setBounds(12, 72, 470, 490);
		subPnl.add(pnlList2, "B");
		
		
//		JLabel backlbl = new JLabel();
//		backlbl.setBounds(12, 72, 470, 490);
//		backlbl.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\baaak.jpg"));
//		mainPnl.add(backlbl);
//		
		
		
		JLabel mainlbl = new JLabel("역대 당첨 번호");
		mainlbl.setBounds(0, 0, 494, 62);
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setHorizontalAlignment(JLabel.CENTER);
		mainlbl.setForeground(Color.white);
		mainlbl.setBackground(new Color(255, 192, 203));
		mainlbl.setOpaque(true);
		mainPnl.add(mainlbl);
		mainPnl.add(subPnl);
		BoxLayout box = new BoxLayout(pnlList, BoxLayout.Y_AXIS);
		pnlList.setLayout(box);
		BoxLayout box2 = new BoxLayout(pnlList2, BoxLayout.Y_AXIS);
		pnlList2.setLayout(box2);


		
		for (int i = 0; i < Rball.length; i++) {
			ballPnl[i] = new JPanel();
			lblA[i] = new JLabel("제 " + String.format("%02d", i + 1) + "회 번호  ");
			lblA[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
			ballPnl[i].add(lblA[i]);
			for (int j = 0; j < 6; j++) {
				Rball[i][j] = new JCheckBox();
				ballPnl[i].add(Rball[i][j]);
				Rball[i][j].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\only.png"));
			}
			bonus1[i] = new JLabel("+");
			bonus1[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
			ballPnl[i].add(bonus1[i]);
			bonusBall[i] = new JCheckBox();
			ballPnl[i].add(bonusBall[i]);
			bonusBall[i].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\only.png"));
			pnlList.add(ballPnl[i]);
		}
		
		
		for (int i = 0; i < Rball2.length; i++) {
			ballPnl2[i] = new JPanel();
			lblA2[i] = new JLabel("제 " + String.format("%02d", i + Rball.length + 1) + "회 번호  ");
			lblA2[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
			ballPnl2[i].add(lblA2[i]);
			for (int j = 0; j < 6; j++) {
				Rball2[i][j] = new JCheckBox();
				ballPnl2[i].add(Rball2[i][j]);
				Rball2[i][j].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\only.png"));
			}
			bonus2[i] = new JLabel("+");
			bonus2[i].setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
			ballPnl2[i].add(bonus2[i]);
			bonusBall2[i] = new JCheckBox();
			ballPnl2[i].add(bonusBall2[i]);
			bonusBall2[i].setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\only.png"));
			pnlList2.add(ballPnl2[i]);
		}
		layout.show(subPnl, "A");
		
		
		
		
		
		JButton btnNext = new JButton("다음");
		btnNext.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnNext.setBackground(Color.white);
		
		JButton btnPrev = new JButton("이전");
		btnPrev.setFont(new Font("한컴 고딕", Font.BOLD, 13));
		btnPrev.setBackground(Color.white);
		
		
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnPrev);
		pnlSouth.add(btnNext);
		pnlSouth.setBackground(Color.white);
		
		getContentPane().add(pnlSouth, "South");
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("다음")) {
					layout.next(subPnl);   // next 다음 컴포넌트를 보여줌
				} else {
					layout.previous(subPnl);   // previous 이전 컴포넌트를 보여줌
				}
			}
		};
		btnNext.addActionListener(listener);
		btnPrev.addActionListener(listener);
		
		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		
		
		
	}
	public static void main(String[] args) {
		new Main4().setVisible(true);
	}
}
