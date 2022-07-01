import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
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
	private JCheckBox[] cbs;
	private Set<Integer> set = new HashSet<>();
	private Consumer consumer = new Consumer();
	private JButton[] confirmRetouchBtns;
	private Integer index = 0;
	private boolean retouchTrue = false;
	private JButton[] confirmRemoveBtns;
	private JButton[] confirmCopyBtns;
	private JLabel[][] confirmLbls;
	private List<Integer> copyList;
	private JButton[] confirmPasteBtns;

	public PurchaseCheckBox() {
		super("구매");
		List<List<Integer>> lottoList = new ArrayList<>();

		JPanel allPnl = new JPanel();

		JPanel leftPnl = new JPanel();

		JPanel purchasePnl = new JPanel();
		JPanel checkboxPnl = new JPanel();
		JPanel bPnl = new JPanel();

		JPanel confirmPnl = new JPanel();

		JPanel rightPnl = new JPanel();

//		배치관리자 설정 부분
// --------------------------------------------------------------------------------
		BoxLayout leftBox = new BoxLayout(leftPnl, BoxLayout.Y_AXIS);
		leftPnl.setLayout(leftBox);

		BoxLayout purchasBox = new BoxLayout(purchasePnl, BoxLayout.Y_AXIS);
		purchasePnl.setLayout(purchasBox);

		BoxLayout confirmBox = new BoxLayout(confirmPnl, BoxLayout.Y_AXIS); // 구매확인 Panel의 배치를 box로
		confirmPnl.setLayout(confirmBox);
// --------------------------------------------------------------------------------

//		왼쪽 버튼들
// --------------------------------------------------------------------------------
		JRadioButton menualRB = new JRadioButton("수동");
		JRadioButton mixRB = new JRadioButton("혼합");
		JRadioButton autoRB = new JRadioButton("자동");
		ButtonGroup group = new ButtonGroup();

		group.add(menualRB);
		group.add(autoRB);
		group.add(mixRB);

		menualRB.setSelected(true);

		menualRB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkboxAllTrue();
				checkboxAllInit();
				set.removeAll(set);
			}
		});

		mixRB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkboxAllTrue();
				checkboxAllInit();
				set.removeAll(set);
			}
		});

		autoRB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkboxAllFalse();
				checkboxAllInit();
				set.removeAll(set);
			}
		});

//		JButton menualBtn = new JButton("혼합");
//		JButton autoBtn = new JButton("자동");
//
//		menualBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				checkboxAllTrue();
//			}
//		});
//
//		autoBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				checkboxAllFalse();
//			}
//		});

// --------------------------------------------------------------------------------		

//		중간 panel (체크박스 부분)
// --------------------------------------------------------------------------------

//		JCheckBox autoCb = new JCheckBox("자동 선택");
		JButton initBtn = new JButton("초기화");
		JButton purchaseBtn = new JButton("구매");
		Integer[] purchaseCount = { 1, 2, 3, 4, 5 };
		JComboBox<Integer> purchaseCombo = new JComboBox<>(purchaseCount);
		JLabel priceLbl = new JLabel("지불 예정 금액: " + 1000 + "원");

		GridLayout grid = new GridLayout(5, 9); // 체크박스 배열 해놓은것

		checkboxPnl.setLayout((grid));
		cbs = new JCheckBox[45];

		for (int i = 0; i < cbs.length; i++) { // 체크박스 값 입력
			cbs[i] = new JCheckBox(String.valueOf(i + 1));
			cbs[i].addActionListener(this);
			checkboxPnl.add(cbs[i]);
		}

		initBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkboxAllInit();
				set.removeAll(set);
			}
		});

		purchaseCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int p = (purchaseCombo.getSelectedIndex() + 1) * 1000;
				priceLbl.setText("지불 예정 금액: " + p + "원");
			}
		});
// --------------------------------------------------------------------------------

//		구매 확인 panel 구성 부분
//--------------------------------------------------------------------------------
		JButton confirmBtn = new JButton("구매 확정");
		JLabel purchaseConfirmLbl = new JLabel("구매 확인");
		JPanel confirmTopPnl = new JPanel();
		JPanel confirmBottomPnl = new JPanel();
		JButton confirmInitBtn = new JButton("초기화");

		confirmTopPnl.add(purchaseConfirmLbl);
		confirmTopPnl.add(confirmInitBtn);
		confirmPnl.add(confirmTopPnl);

		JPanel[] lottoPnl = new JPanel[5];

		// 로또 번호를 받는 5개의 panel을 생성
		for (int i = 0; i < lottoPnl.length; i++) {
			lottoPnl[i] = new JPanel();
			confirmPnl.add(lottoPnl[i]);
		}

		JLabel countPurchaseLbl = new JLabel("총 구매 횟수: 0");
		JLabel confirmPrice = new JLabel("금액: " + String.valueOf(consumer.getPrice()) + "원");

		confirmBottomPnl.add(countPurchaseLbl);
		confirmBottomPnl.add(confirmPrice);

		confirmLbls = new JLabel[5][7];

		// 구매 확인에서 첫번째 문자 넣어줌
		for (int i = 0; i < lottoPnl.length; i++) {
			char c = (char) ('A' + i);
			confirmLbls[i][0] = new JLabel(String.valueOf(c));
			lottoPnl[i].add(confirmLbls[i][0]);
		}

		// 구매 확인에서 로또 번호들을 나열하기 위해 label들을 생성
		for (int i = 0; i < lottoPnl.length; i++) {
			for (int j = 1; j < confirmLbls[i].length; j++) {
				confirmLbls[i][j] = new JLabel();
				lottoPnl[i].add(confirmLbls[i][j]);
			}

		}

		confirmRetouchBtns = new JButton[5];
		confirmRemoveBtns = new JButton[5];
		confirmCopyBtns = new JButton[5];
		confirmPasteBtns = new JButton[5];

		// 수정, 삭제, 복사, 붙여넣기 버튼 생성
		for (int i = 0; i < confirmRetouchBtns.length; i++) {
			confirmRetouchBtns[i] = new JButton("수정");
			confirmRemoveBtns[i] = new JButton("삭제");
			confirmCopyBtns[i] = new JButton("복사");
			confirmPasteBtns[i] = new JButton("붙여 넣기");

			lottoPnl[i].add(confirmRetouchBtns[i]);
			lottoPnl[i].add(confirmRemoveBtns[i]);
			lottoPnl[i].add(confirmCopyBtns[i]);
			lottoPnl[i].add(confirmPasteBtns[i]);
			confirmRetouchBtns[i].setEnabled(false);
			confirmRemoveBtns[i].setEnabled(false);
			confirmRemoveBtns[i].setEnabled(false);
			confirmPasteBtns[i].setVisible(false);
		}

		// 구매 번호 초기화 버튼
		confirmInitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int price = -(1000 * lottoList.size());
				consumer.setPrice(price);
				confirmPrice.setText("금액: " + String.valueOf(consumer.getPrice()) + "원");
				for (int i = 0; i < confirmLbls.length; i++) {
					for (int j = 1; j < confirmLbls[i].length; j++) {
						confirmLbls[i][j].setText("");
					}
				}
				lottoList.removeAll(lottoList);
				confirmBtnFalse();
				copyBtnInit();
			}
		});

		// 구매 확정 버튼 이벤트
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consumer.setLottoList(lottoList);
				System.out.println(consumer.getList());
				lottoList.removeAll(lottoList);
				countPurchaseLbl.setText("총 구매 횟수: " + String.valueOf(String.valueOf(consumer.getCount()) + "회"));
				for (int i = 0; i < confirmLbls.length; i++) {
					for (int j = 1; j < confirmLbls[i].length; j++) {
						confirmLbls[i][j].setText("");
					}
				}
				lottoList.removeAll(lottoList);
				confirmBtnFalse();
				copyBtnInit();
			}
		});

		// 수정 버튼 이벤트 // 이슈 수정을 했는데 list에 추가가되는 현상
		ActionListener retouchListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < confirmRetouchBtns.length; i++) {
					if (e.getSource() == confirmRetouchBtns[i]) {

						retouchTrue = true;
						menualRB.setSelected(true);
						set.removeAll(set);

						checkboxAllFalse();
						checkboxAllInit();

//						System.out.println(lottoList.get(i));

						for (int j = 0; j < lottoList.get(i).size(); j++) {
							set.add(lottoList.get(i).get(j));
							cbs[lottoList.get(i).get(j) - 1].setEnabled(true);
							cbs[lottoList.get(i).get(j) - 1].setSelected(true);
						}

//						System.out.println(set);

						index = i;
						copyBtnInit();
					}

				}

			}
		};

		// 삭제 버튼 이벤트
		ActionListener removeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < confirmRemoveBtns.length; i++) {
					if (e.getSource() == confirmRemoveBtns[i]) {
						confirmRetouchBtns[lottoList.size() - 1].setEnabled(false);
						confirmRemoveBtns[lottoList.size() - 1].setEnabled(false);
			
						lottoList.remove(i);
						System.out.println(lottoList);
						confirmLblInit();
						for (int j = 0; j < lottoList.size(); j++) {
							for (int k = 0; k < lottoList.get(j).size(); k++) {
								confirmLbls[j][k + 1].setText(String.format("%02d", lottoList.get(j).get(k)));
							}
						}
						consumer.setPrice(-1000);
						confirmPrice.setText("총 금액: " + consumer.getPrice() + "원");
						copyBtnInit();
					}
				}
			}
		};

		// 복사 버튼 이벤트
		ActionListener copyLisener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < confirmCopyBtns.length; i++) {
					if (e.getSource() == confirmCopyBtns[i]) {
						copyList = new ArrayList<>(lottoList.get(i));

						System.out.println(copyList);

						confirmCopyBtns[lottoList.size()].setVisible(false);
						confirmPasteBtns[lottoList.size()].setVisible(true);

					}
				}
			}
		};

		// 붙여넣기 버튼 이벤트
		ActionListener pasteListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < confirmPasteBtns.length; i++) {
					if (e.getSource() == confirmPasteBtns[i]) {
						lottoList.add(copyList);
						System.out.println(lottoList);
						for (int j = 0; j < copyList.size(); j++) {
							confirmLbls[i][j + 1].setText(String.format("%02d", copyList.get(j)));
						}
						consumer.setPrice(1000);
						confirmPrice.setText("총 금액: " + consumer.getPrice() + "원");
						confirmRetouchBtns[lottoList.size() - 1].setEnabled(true);
						confirmRemoveBtns[lottoList.size() - 1].setEnabled(true);
						copyBtnInit();
					}
				}
			}
		};

		for (int i = 0; i < confirmRetouchBtns.length; i++) {
			confirmRetouchBtns[i].addActionListener(retouchListener);
			confirmRemoveBtns[i].addActionListener(removeListener);
			confirmCopyBtns[i].addActionListener(copyLisener);
			confirmPasteBtns[i].addActionListener(pasteListener);
		}
//--------------------------------------------------------------------------------

//			구매 버튼 클릭 이벤트
// --------------------------------------------------------------------------------
		purchaseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (retouchTrue) { // 수정 버튼을 누른 상태일때
//					System.out.println(index);

					lottoList.remove(lottoList.get(index)); // index에 해당하는 배열을 지운다.

//					System.out.println("123" + lottoList.size());

					List<Integer> list = new ArrayList<Integer>(set); // 새로 수정한 lotto set을 list로 만든다.
					Collections.sort(list); // 정렬
					lottoList.add(index, list); // 삭제한 index에 정렬한 list를 추가한다.

//					System.out.println(lottoList.size());
					checkboxAllInit(); // checkbox를 모두 초기화 시킨다.
					checkboxAllTrue(); // checkbox를 모두 활성화 시킨다.
					set.removeAll(set); // set을 지워준다.

					for (int j = 0; j < lottoList.get(index).size(); j++) {
						confirmLbls[index][j + 1].setText(String.format("%02d", lottoList.get(index).get(j)));
					}

					retouchTrue = false; // 다시 수정 버튼을 누르기 전으로 돌아간다.

				} else {
					if (lottoList.size() + purchaseCombo.getSelectedIndex() + 1 > 5) { // 5장 넘게 구매X
						JOptionPane.showMessageDialog(purchasePnl, "복권은 한번에 5장까지 구매 가능합니다.");

					} else {
						if (mixRB.isSelected()) {
//							System.out.println(set);
							Integer[] arr = new Integer[set.size()];
//							System.out.println(arr.length);
							int countArr = 0;

							for (Integer number : set) {
								arr[countArr] = number;
								countArr++;
							}

							for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
								for (int j = 0; j < arr.length; j++) {
									set.add(arr[j]);
								}
								while (set.size() < 6) {
									set.add(new Random().nextInt(45) + 1);
								}

								List<Integer> list = new ArrayList<Integer>(set);
								Collections.sort(list);

								lottoList.add(list);

								checkboxAllInit();

								set.removeAll(set); // set을 초기화
								consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
							}

						} else if (menualRB.isSelected()) {
							if (set.size() == 6) {
								List<Integer> list = new ArrayList<Integer>(set);
								Collections.sort(list);

								for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
									lottoList.add(list);

								}

								checkboxAllInit();
								set.removeAll(set);
								consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
							} else {
								JOptionPane.showMessageDialog(purchasePnl, "번호 6개를 선택해 주세요");

							}
						} else {
							for (int i = 0; i < purchaseCombo.getSelectedIndex() + 1; i++) {
								while (set.size() < 6) {
									set.add(new Random().nextInt(45) + 1);
								}

								List<Integer> list = new ArrayList<Integer>(set);
								Collections.sort(list);

								lottoList.add(list);

								checkboxAllInit();

								set.removeAll(set); // set을 초기화
								consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
							}
						}
						// 구매 확인창에 번호를 보내줌
						for (int i = 0; i < lottoList.size(); i++) {
							confirmRetouchBtns[i].setEnabled(true);
							confirmRemoveBtns[i].setEnabled(true);
							confirmCopyBtns[i].setEnabled(true);
							for (int j = 0; j < lottoList.get(i).size(); j++) {
								confirmLbls[i][j + 1].setText(String.format("%02d", lottoList.get(i).get(j)));
							}
						}
						if (autoRB.isSelected()) {
							checkboxAllFalse();
						} else {
							checkboxAllTrue();
						}
						System.out.println(lottoList.size());
//						consumer.setPrice((purchaseCombo.getSelectedIndex() + 1) * 1000);
						confirmPrice.setText("총 금액: " + consumer.getPrice() + "원");
						purchaseCombo.setSelectedIndex(0);
					}

//					System.out.println(lottoList);
//					System.out.println(lottoList.size());

				}
			}
		});
// --------------------------------------------------------------------------------

// --------------------------------------------------------------------------------
		leftPnl.add(menualRB);
		leftPnl.add(mixRB);
		leftPnl.add(autoRB);

//		leftPnl.add(menualBtn);
//		leftPnl.add(autoBtn);

		bPnl.add(initBtn);
		bPnl.add(purchaseBtn);
		bPnl.add(purchaseCombo);
//		bPnl.add(autoCb);

		purchasePnl.add(priceLbl);
		purchasePnl.add(checkboxPnl);
		purchasePnl.add(bPnl);

		confirmPnl.add(confirmBottomPnl);
		confirmPnl.add(confirmBtn);

		allPnl.add(leftPnl);
		allPnl.add(purchasePnl);
		allPnl.add(confirmPnl);
		allPnl.add(rightPnl);

		getContentPane().add(allPnl);
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new PurchaseCheckBox().setVisible(true);
	}

	// checkbox를 모두 활성화
	public void checkboxAllTrue() {
		for (int i = 0; i < cbs.length; i++) {
			cbs[i].setEnabled(true);
		}
	}

	// checkbox를 모두 비활성화
	public void checkboxAllFalse() {
		for (int i = 0; i < cbs.length; i++) {
			cbs[i].setEnabled(false);
		}
	}

	// checkbox를 모두 초기화
	public void checkboxAllInit() {
		for (int i = 0; i < cbs.length; i++) {
			cbs[i].setSelected(false);
		}
	}

	// 번호 확인 label을 초기화
	public void confirmLblInit() {
		for (int i = 0; i < confirmLbls.length; i++) {
			for (int j = 1; j < confirmLbls[i].length; j++) {
				confirmLbls[i][j].setText("");
			}
		}
	}

	// 붙여넣기 버튼을 안보이게 설정 하고 복사 버튼을 보이게 설정 (기본값)
	public void copyBtnInit() {
		for (int i = 0; i < confirmCopyBtns.length; i++) {
			confirmPasteBtns[i].setVisible(false);
			confirmCopyBtns[i].setVisible(true);
		}
	}
	
	
	public void confirmBtnFalse() {
		for(int i = 0; i < confirmRetouchBtns.length;i++) {
			confirmRetouchBtns[i].setEnabled(false);
			confirmRemoveBtns[i].setEnabled(false);
		}
	}

	// 구매하기 버튼 이벤트 set에 값을 넣는 과정
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
			if (set.size() == 6) {
				for (int j = 0; j < cbs.length; j++) {
					if (!cbs[j].isSelected()) {
						cbs[j].setEnabled(false);
					}
				}
			} else {
				checkboxAllTrue();
			}
		}

	}
}