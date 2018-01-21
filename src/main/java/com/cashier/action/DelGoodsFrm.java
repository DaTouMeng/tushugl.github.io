package com.cashier.action;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;
import com.cashier.utils.StringUtils;

public class DelGoodsFrm extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8367146232138596660L;
	private JTextField delGoodsTxt;
	private static UserService userService = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelGoodsFrm frame = new DelGoodsFrm();
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
	public DelGoodsFrm() {
		getContentPane().setEnabled(false);
		setTitle("商品下架");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 555, 330);

		JLabel lblNewLabel = new JLabel("请输入商品编号进行商品删除");

		JLabel label = new JLabel("商品编号:");

		delGoodsTxt = new JTextField();
		delGoodsTxt.setColumns(10);

		JButton delGoodsBtn = new JButton("删除");
		delGoodsBtn.setIcon(new ImageIcon("./src/main/resources/icons/delete.png"));
		delGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					delGoodsMethod();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(43)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addComponent(label)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(delGoodsTxt, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 369,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(57).addComponent(delGoodsBtn,
										GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(127, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(48)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
						delGoodsTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(31).addComponent(delGoodsBtn).addContainerGap(107, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}

	protected void delGoodsMethod() throws Exception {
	
		String goodsID = delGoodsTxt.getText();
		String sql = "select * from add_goods where goodsID=?";

		if (StringUtils.isEmpty(goodsID)) {
			JOptionPane.showMessageDialog(null, "请输入商品编号");
			return;
		}
		if (StringUtils.isNotEmpty(goodsID)) {
			if (userService.isExist(goodsID , sql) == false) {   
				JOptionPane.showMessageDialog(null, "此商品记录不存在");
			}
			if(userService.isExist(goodsID , sql)== true) {
				Object[] options = { "确定", "不" };
				int n = JOptionPane.showOptionDialog(null, "确定要删除该记录吗", "提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (n == 0) {
					try {
						if (userService.delGoods(goodsID) > 0) {
							JOptionPane.showMessageDialog(null, "删除成功");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		}

	}
}
