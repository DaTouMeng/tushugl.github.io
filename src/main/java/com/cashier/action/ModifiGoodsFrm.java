package com.cashier.action;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;
import com.cashier.utils.StringUtils;

public class ModifiGoodsFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8097834810020180472L;
	private JTextField modifiIdTxt;
	private JTextField modifiedNameTxt;
	private JTextField modifiCountTxt;
	private JTextField modifiPriceTxt;
	private JTextField seaIdTxt;

	private static UserService userService = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifiGoodsFrm frame = new ModifiGoodsFrm();
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
	public ModifiGoodsFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("商品修改");
		setBounds(100, 100, 764, 482);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(55)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE).addGap(23)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap(71, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE).addGap(40)));

		JLabel lblNewLabel = new JLabel("商品编号:");

		modifiIdTxt = new JTextField();
		modifiIdTxt.setColumns(10);

		JLabel label = new JLabel("商品名称:");

		modifiedNameTxt = new JTextField();
		modifiedNameTxt.setColumns(10);

		JLabel label_1 = new JLabel("商品数量:");

		modifiCountTxt = new JTextField();
		modifiCountTxt.setColumns(10);

		JLabel label_2 = new JLabel("价格:");

		modifiPriceTxt = new JTextField();
		modifiPriceTxt.setColumns(10);

		JButton modifiBtn = new JButton("修改");
		modifiBtn.setIcon(new ImageIcon("./src/main/resources/icons/modify.png"));
		modifiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifiGoodsMohted();
			}
		});

		JButton resetBtn = new JButton("重置");
		resetBtn.setIcon(new ImageIcon("./src/main/resources/icons/reset.png"));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifiGoodsReset();

			}
		});

		JLabel label_3 = new JLabel("商品编号: ");

		seaIdTxt = new JTextField();
		seaIdTxt.setColumns(10);

		JButton seaBtn = new JButton("搜索");
		seaBtn.setSelectedIcon(new ImageIcon("E:\\javaSE\\JDBC\\cashier\\src\\main\\resources\\icons\\search.png"));
		seaBtn.setIcon(new ImageIcon("E:\\javaSE\\JDBC\\cashier\\src\\main\\resources\\icons\\search.png"));
		seaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seaGoodsMethed();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(80).addComponent(modifiBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(43).addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(51)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
										.addComponent(label_1).addComponent(label_3))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(modifiCountTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(36).addComponent(label_2).addGap(18)
												.addComponent(modifiPriceTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(modifiIdTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(seaIdTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(21)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(seaBtn)
														.addGroup(gl_panel.createSequentialGroup().addComponent(label)
																.addGap(18).addComponent(modifiedNameTxt,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))))))
				.addContainerGap(211, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(43)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
								.addComponent(seaIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(seaBtn))
						.addGap(64)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(modifiedNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel).addComponent(modifiIdTxt, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(modifiCountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2).addComponent(modifiPriceTxt, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(47).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(modifiBtn)
								.addComponent(resetBtn))
						.addGap(49)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	protected void seaGoodsMethed() { //
		String goodsID = this.seaIdTxt.getText();
		if (StringUtils.isEmpty(goodsID)) {
			JOptionPane.showMessageDialog(null, "请输入商品编号！");
			return;
		}
		String sql = "select * from add_goods where goodsID=?";
		String s_sql = "select * from add_goods where goodsID=?";
		try {
			if (userService.isExist(goodsID, s_sql) == false) {
				JOptionPane.showMessageDialog(null, "此商品不存在");
			}
			if (userService.isExist(goodsID, s_sql) == true) {
				ResultSet rs = userService.CheckUser(goodsID, sql);
				while (rs.next()) {
					int goodsID2 = rs.getInt(2);
					String goodsName = rs.getString(3);
					String goodsCount = rs.getString(4);
					String price = rs.getString(5);
					this.modifiIdTxt.setText(String.valueOf(goodsID2));
					this.modifiedNameTxt.setText(goodsName);
					this.modifiCountTxt.setText(goodsCount);
					this.modifiPriceTxt.setText(price);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void modifiGoodsMohted() {
		String goodsID = this.modifiIdTxt.getText();
		String goodsID2 = this.seaIdTxt.getText();
		String goodsName = this.modifiedNameTxt.getText();
		String goodsCount = this.modifiCountTxt.getText();
		String price = this.modifiPriceTxt.getText();

		try {
			if (StringUtils.isNotEmpty(goodsID2) == true) {
					if (userService.modifi_goods(goodsID, goodsName, goodsCount, price, goodsID2) == 1) {
						JOptionPane.showMessageDialog(null, "商品修改成功！");
						modifiGoodsReset();
					} else {
						JOptionPane.showMessageDialog(null, "商品修改失败！");
					}
			} else {
				JOptionPane.showMessageDialog(null, "请输入搜索商品编号！！");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "商品修改失败！");
		}

	}

	/**
	 * 重置表单
	 */
	private void modifiGoodsReset() {
		this.modifiIdTxt.setText("");
		this.modifiedNameTxt.setText("");
		this.modifiCountTxt.setText("");
		this.modifiPriceTxt.setText("");
	}
}
