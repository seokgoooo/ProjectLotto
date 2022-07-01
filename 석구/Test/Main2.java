package incomplete;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class Main2 extends JFrame implements ActionListener {
	private JCheckBox[] basicNumber;
	private JCheckBox[] clickNumber;
	private JCheckBox[] basicNumberCopy;
	private Integer[] setNum = { 1, 2, 3, 4, 5 };
	private JCheckBox[] ball;

	public Main2() {
		super("추첨프로그램");


	}
}
