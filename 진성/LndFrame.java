package 진성;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;



public class LndFrame extends JFrame {
	private JCheckBox[] ball;
	private JCheckBox[][] Rball = new JCheckBox[5][6];
	private JPanel[] ballPnl = new JPanel[5];
	private JLabel[] lblA = new JLabel[5];
	private JScrollPane sp = new JScrollPane();
	
	public LndFrame() {
		super("역대 번호");
		
		JPanel mainpnl = new JPanel();
		getContentPane().add(mainpnl);
		mainpnl.setLayout(null);
		
		JPanel pnllist = new JPanel();
		pnllist.setBounds(12, 94, 570, 415);
		mainpnl.add(pnllist);
		
		JLabel mainlbl = new JLabel("역대 당첨 번호");
		mainlbl.setBounds(0, 0, 594, 84);
		mainlbl.setHorizontalAlignment(JLabel.CENTER);// 글짜 가운데정렬
		mainlbl.setForeground(Color.white);
		mainlbl.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		mainlbl.setBackground(new Color(255, 192, 203));
		mainlbl.setOpaque(true); //컬러 넣을려면 이거넣어야함
		mainpnl.add(mainlbl);
		
		//BoxLayout box = new BoxLayout(pnllist, BoxLayout.Y_AXIS);
		//pnllist.setLayout(box);
		
		
		for (int i = 0; i < Rball.length; i++) {
			ballPnl[i] = new JPanel();
			lblA[i] = new JLabel("제" + (i + 1) + "회 번호");
			ballPnl[i].add(lblA[i]);
			for (int j = 0; j < 6; j++) {
				Rball[i][j] = new JCheckBox();
				ballPnl[i].add(Rball[i][j]);
				Rball[i][j].setIcon(new ImageIcon("D:\\seongjavaeclipse\\TeamProject\\only.png"));
			}
			pnllist.add(ballPnl[i]);
		}
		//sp.setViewportView(pnllist);
		
		mainpnl.add(sp);
	
		
		
		setSize(600, 600);
		setResizable(false); //창크기 조절못하게
		setLocationRelativeTo(null); // 중앙으로
	}

	

	public static void main(String[] args) {
		new LndFrame().setVisible(true);

	}
}
