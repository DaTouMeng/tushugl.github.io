package com.cashier.action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.cashier.main.MainGUI;
import com.cashier.model.Goods;
import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;
import com.cashier.utils.MyException;
import com.cashier.utils.StringUtils;

public class ShowGoodsFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6542624302537411279L;
	private JTable goodsTable;
	private JTextField showGoodsNameTxt;
	public JTextField buyIdTxt;
	public JTextField buyNameTxt;
	public JTextField buyCountTxt;
	public JTextField buyPriceTxt;
	private JTextField buyGoodsCountTxt;
	private static UserService userService = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowGoodsFrm frame = new ShowGoodsFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowGoodsFrm() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setEnabled(false);
		setTitle("商品导购");
		setBounds(100, 100, 736, 721);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u5546\u54C1\u641C\u7D22", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u70B9\u51FB\u5546\u54C1\u8FDB\u884C\u9009\u8D2D", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(60, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 608, GroupLayout.PREFERRED_SIZE).addGap(52))
				.addGroup(groupLayout.createSequentialGroup().addGap(74)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
						.addGap(94))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(284, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addGap(41)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addGap(31)));

		JLabel lblNewLabel_2 = new JLabel("欢迎会员:");

		JLabel huiYuanTxt = new JLabel("");
		String mobile = MainGUI.getMobile();
		huiYuanTxt.setText(mobile);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(huiYuanTxt, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(87, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
								.addComponent(huiYuanTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(19, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JLabel lblNewLabel = new JLabel("商品编号:");

		buyIdTxt = new JTextField();
		buyIdTxt.setHorizontalAlignment(SwingConstants.CENTER);
		buyIdTxt.setForeground(Color.RED);
		buyIdTxt.setEnabled(false);
		buyIdTxt.setColumns(10);

		JLabel label_1 = new JLabel("商品名称:");

		buyNameTxt = new JTextField();
		buyNameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		buyNameTxt.setEnabled(false);
		buyNameTxt.setColumns(10);

		JLabel label_2 = new JLabel("购买数量:");

		buyCountTxt = new JTextField();
		buyCountTxt.setHorizontalAlignment(SwingConstants.CENTER);
		buyCountTxt.setColumns(10);

		JLabel label_3 = new JLabel("单价:");

		buyPriceTxt = new JTextField();
		buyPriceTxt.setHorizontalAlignment(SwingConstants.CENTER);
		buyPriceTxt.setEnabled(false);
		buyPriceTxt.setColumns(10);

		JButton buyBtn = new JButton("购买");
		buyBtn.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyGoodsMethod();
			}
		});

		JButton buyReset = new JButton("重置");
		buyReset.setIcon(new ImageIcon("./src/main/resources/icons/reset.png"));
		buyReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyResetMothed();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("库存:");

		buyGoodsCountTxt = new JTextField();
		buyGoodsCountTxt.setHorizontalAlignment(SwingConstants.CENTER);
		buyGoodsCountTxt.setEnabled(false);
		buyGoodsCountTxt.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addGap(43)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(buyBtn, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_3).addGap(18)
										.addComponent(buyPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(23).addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(buyIdTxt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(buyNameTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(buyGoodsCountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_panel_1.createSequentialGroup()
										.addComponent(buyReset, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)
										.addGap(76))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(label_2)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(buyCountTxt,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(43)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1
						.createSequentialGroup().addGap(
								23)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(buyIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1)
								.addComponent(buyNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1).addComponent(buyGoodsCountTxt, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(label_3)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(buyPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2).addComponent(buyCountTxt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(44).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(buyBtn)
								.addComponent(buyReset))
						.addContainerGap(42, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JLabel label = new JLabel("商品名称:");

		showGoodsNameTxt = new JTextField();
		showGoodsNameTxt.setColumns(10);

		JButton showGoodsBtn = new JButton("搜索");
		showGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent met) {
				showGoodsBtnMothod();
			}
		});
		showGoodsBtn.setIcon(new ImageIcon("./src/main/resources/icons/search.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panel.createSequentialGroup().addContainerGap().addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(showGoodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(123).addComponent(showGoodsBtn).addContainerGap(128, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(showGoodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(showGoodsBtn))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		goodsTable = new JTable();
		goodsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				goodsTableMousePressed(e);
			}
		});
		goodsTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u5546\u54C1\u7F16\u53F7",
				"\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u5E93\u5B58", "\u4EF7\u683C" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2606466761091056052L;
			/**
			 * 
			 */

			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		goodsTable.getColumnModel().getColumn(0).setPreferredWidth(115);
		goodsTable.getColumnModel().getColumn(1).setPreferredWidth(116);
		goodsTable.getColumnModel().getColumn(2).setPreferredWidth(124);
		scrollPane.setViewportView(goodsTable);
		getContentPane().setLayout(groupLayout);

		fillTable(new Goods());
	}

	@SuppressWarnings("unused")
	private void buyGoodsMethod() { // 购买结算
		String goodsID = this.buyIdTxt.getText();
		String goodsName = this.buyNameTxt.getText();
		String goodsCount = this.buyGoodsCountTxt.getText();
		String goodsPrice = this.buyPriceTxt.getText();
		String buyCount = this.buyCountTxt.getText(); // -----------------

		if (StringUtils.isEmpty(buyCount)) {
			JOptionPane.showConfirmDialog(null, "选购数量为空");
		}

		boolean b = StringUtils.isNumeric(buyCount); // -----判断用户输入的是不是合法的正整数
		if (b == false) {
			JOptionPane.showConfirmDialog(null, "请输入正确的正整数(如1或2)");
			buyResetMothed();
			return;
		}

		else {
			try {
				int u = 0;
				try {
					u = userService.buy(goodsID, goodsName, goodsCount, goodsPrice, buyCount);
				} catch (MyException e) {

					JOptionPane.showMessageDialog(null, e.getMessage());  
				}
				if (u == 1) {
					JOptionPane.showMessageDialog(null, "购买成功");
				} else {
					JOptionPane.showMessageDialog(null, "购买失败");
				}

			} catch (RuntimeException | SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 重置
	 */
	public void buyResetMothed() {
		this.buyIdTxt.setText("");
		this.buyNameTxt.setText("");
		this.buyGoodsCountTxt.setText("");
		this.buyPriceTxt.setText("");
		this.buyCountTxt.setText("");
	}

	/**
	 * 点击表单进行数据填充
	 * 
	 * @param e
	 * 
	 *            设置成setEnable（true）才能获取到该行------ 作用是使那个调用此方法的控件对象可用或者不可用
	 */
	protected void goodsTableMousePressed(MouseEvent e) {
		int row = this.goodsTable.getSelectedRow();
		if (row != -1) {
			this.buyIdTxt.setText((String) goodsTable.getValueAt(row, 0));
			this.buyNameTxt.setText((String) goodsTable.getValueAt(row, 1));
			this.buyGoodsCountTxt.setText((String) goodsTable.getValueAt(row, 2));
			this.buyPriceTxt.setText((String) goodsTable.getValueAt(row, 3));
		} else {
			JOptionPane.showMessageDialog(null, "row获取失败");
		}
	}

	/**
	 * 搜索商品
	 */
	protected void showGoodsBtnMothod() {
		String goodsName = this.showGoodsNameTxt.getText();
		Goods goods = new Goods(goodsName);
		this.fillTable(goods);

	}

	/**
	 * 初始化表格
	 * 
	 * @param bookType
	 */
	private void fillTable(Goods goods) {
		DefaultTableModel dtm = (DefaultTableModel) goodsTable.getModel();//
		// getModel 来接收页表单域传递过来的model 对象，
		dtm.setRowCount(0); // 设置成0行
		try {
			ResultSet rs = userService.fillTable(goods);
			if (rs == null) {
				JOptionPane.showMessageDialog(null, "商家倒闭了,没东西卖");
			}
			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("goodsID"));
				v.add(rs.getString("goodsName"));
				v.add(rs.getString("goodsCount"));
				v.add(rs.getString("price"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
