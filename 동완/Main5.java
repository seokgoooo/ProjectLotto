package 동완;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main5 extends JFrame implements ActionListener {
	private int[] game = new int[6];
	private JCheckBox box1, box2, box3, box4, box5, box6, box7;
	private ImageIcon[] icon = new ImageIcon[6];
	private int num1;
	private Map<Integer, ImageIcon> map1 = new HashMap<>();
	private JCheckBox[] boxs;
	private Random ran = new Random();

	ImageIcon icon1 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\box.png");
	ImageIcon icon2 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut2.png");
//	ImageIcon icon4 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut4.png");
//	ImageIcon icon5 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut5.png");
//	ImageIcon icon6 = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut6.png");
	
	public Main5() {
		super("미니게임");

		for(int i = 0; i < 6; i++) {
			icon[i] = new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut" + (i + 1) + ".png");
			map1.put(i, icon[i]);
		}
		
		JPanel mainPnl = new JPanel();
		getContentPane().add(mainPnl);
		mainPnl.setLayout(null);

		box1 = new JCheckBox();
		box1.setBounds(66, 249, 59, 59);
		box1.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\nut2.png"));
		mainPnl.add(box1);

		box2 = new JCheckBox();
		box2.setBounds(184, 218, 59, 59);
		box2.setIcon(icon1);
		mainPnl.add(box2);

		box3 = new JCheckBox();
		box3.setBounds(281, 218, 59, 59);
		box3.setIcon(icon1);
		mainPnl.add(box3);

		box4 = new JCheckBox();
		box4.setBounds(381, 218, 59, 59);
		box4.setIcon(icon1);
		mainPnl.add(box4);
		
		box5 = new JCheckBox();
		box5.setBounds(184, 324, 59, 59);
		box5.setIcon(icon1);
		mainPnl.add(box5);

		box6 = new JCheckBox();
		box6.setBounds(281, 324, 59, 59);
		box6.setIcon(icon1);
		mainPnl.add(box6);


		box7 = new JCheckBox();
		box7.setBounds(381, 324, 59, 59);
		box7.setIcon(icon1);
		mainPnl.add(box7);
		
		
		boxs = new JCheckBox[] { box2, box3, box4, box5, box6, box7 };
		
		
		for (int i = 0; i < boxs.length; i++) {
			boxs[i].addActionListener(this);
		}
//		
//		box2.addActionListener(new ActionListener() {
//			int count = 0;
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box2.setIcon(map1.get(ran.nextInt(6)));	
//				count++;
//				}
//				
//			}
//		});
//		
//		box3.addActionListener(new ActionListener() {
//			int count = 0;
//			Random ran1 = new Random();
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box3.setIcon(map1.get(ran1.nextInt(6)));	
//				count++;
//				}
//				
//			}
//		});
//		
//		box4.addActionListener(new ActionListener() {
//			int count = 0;
//			Random ran2 = new Random();
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box4.setIcon(map1.get(ran2.nextInt(6)));	
//				count++;
//				}
//				
//			}
//		});
//		
//		box5.addActionListener(new ActionListener() {
//			int count = 0;
//			Random ran3 = new Random();
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box5.setIcon(map1.get(ran3.nextInt(6)));	
//				count++;
//				}
//				
//			}
//		});
//		
//		box6.addActionListener(new ActionListener() {
//			int count = 0;
//			Random ran4 = new Random();
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box6.setIcon(map1.get(ran4.nextInt(6)));	
//				count++;
//				}
//				
//			}
//		});
//		
//		box7.addActionListener(new ActionListener() {
//			int count = 0;
//			Random ran5 = new Random();
//			@Override
//			public void actionPerformed(ActionEvent e) {  // 체크가 되었나 안되었나도 하나의 이벤트
//				if(count == 0) {
//				box7.setIcon(map1.get(ran5.nextInt(6)));	
//				count++;
//				}
//				if(box2.isSelected() && box3.isSelected() && box4.isSelected() && box5.isSelected() && box6.isSelected() && box7.isSelected()) {
//					if(box2.getIcon() == box3.getIcon() && box2.getIcon() == box4.getIcon() && box3.getIcon() == box4.getIcon()) {
//						System.out.println("합격");
//						System.out.println(box2.isSelected());
//						
//					}
//				}
//			}
//		});
		System.out.println(box2.isSelected());
		
		
		

	

		
		JLabel mainlbl = new JLabel();
		mainlbl.setBounds(0, 0, 600, 450);
		mainPnl.add(mainlbl);
		mainlbl.setIcon(new ImageIcon("D:\\Wani\\Mogu\\lotto\\image\\speed.png"));

		setSize(600, 465);
		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		setResizable(false);

	}

	

	public static void main(String[] args) {
		new Main5().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < boxs.length;i++) {
			if(e.getSource() == boxs[i]) {
				if((boxs[i].getIcon() == icon1)) {
					boxs[i].setIcon(map1.get(ran.nextInt(6)));
				}
			}
			
		}
		if(boxs[0].isSelected() && boxs[1].isSelected() && boxs[2].isSelected() && boxs[3].isSelected() && boxs[4].isSelected() && boxs[5].isSelected()) {
			if(boxs[0].getIcon() == boxs[1].getIcon() && boxs[1].getIcon() == boxs[2].getIcon() && boxs[0].getIcon() == icon2) {
				JOptionPane.showMessageDialog(Main5.this, "당첨 되셨습니다!!");
				
			} else if (boxs[3].getIcon() == boxs[4].getIcon() && boxs[4].getIcon() == boxs[5].getIcon() && boxs[3].getIcon() == icon2) {
				JOptionPane.showMessageDialog(Main5.this, "당첨 되셨습니다!!");
			} else {
				JOptionPane.showMessageDialog(Main5.this, "아쉽지만 다음기회에!!");
			}
		}
	}
}
