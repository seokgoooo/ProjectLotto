package incomplete;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PurchaseCheckBox extends JFrame implements ActionListener {
	public PurchaseCheckBox() {
		super("구매");
// --------------------------------------------------------------------------------		
//		구매 확인 panel 구성 부분
//--------------------------------------------------------------------------------
		JButton confirmButton = new JButton("구매 확정");
		JLabel purchaseConfirmLabel = new JLabel("구매 확인");
		JButton leftResetButton = new JButton("초기화");

		// 로또 번호를 받는 5개의 panel을 생성
		chagneNumberButton = new JButton[5];
