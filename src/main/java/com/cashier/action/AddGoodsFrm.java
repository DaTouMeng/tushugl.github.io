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

public class AddGoodsFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2210586989234532520L;
	private JTextField addGoodsIdTxt;
	private JTextField addGoodsNameTxt;
	private JTextField addGoodsCountTxt;
	private JTextField addGoodsPriceTxt;
	private static UserService userService = new UserServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoodsFrm frame = new AddGoodsFrm();
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
	public AddGoodsFrm() {
		getContentPane().setEnabled(false);
		
		JLabel label = new JLabel("商品编号:");
		
		addGoodsIdTxt = new JTextField();
		addGoodsIdTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("商品名:");
		
		addGoodsNameTxt = new JTextField();
		addGoodsNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("添加数量:");
		
		addGoodsCountTxt = new JTextField();
		addGoodsCountTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("价格:");
		
		addGoodsPriceTxt = new JTextField();
		addGoodsPriceTxt.setColumns(10);
		
		JButton addGoodsBtn = new JButton("添加");
		addGoodsBtn.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		addGoodsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addGoodsMethod();
			}
		});
		
		JButton addGoodsResetBtn = new JButton("重置");
		addGoodsResetBtn.setIcon(new ImageIcon("./src/main/resources/icons/reset.png"));
		addGoodsResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGoodsReset();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(addGoodsCountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(34)
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(addGoodsPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(addGoodsIdTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(addGoodsNameTxt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(75)
							.addComponent(addGoodsBtn, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(addGoodsResetBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(379, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(98)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(addGoodsIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(addGoodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(addGoodsCountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(addGoodsPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addGoodsBtn)
						.addComponent(addGoodsResetBtn))
					.addContainerGap(241, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setClosable(true);
		setIconifiable(true);
		setTitle("商品增加");
		setBounds(100, 100, 588, 429);

	}

	
	protected void addGoodsMethod() {
		String goodsID=this.addGoodsIdTxt.getText();
		String goodsName=this.addGoodsNameTxt.getText();
		String goodsCount=this.addGoodsCountTxt.getText();
		String price=this.addGoodsPriceTxt.getText();
		
		if(StringUtils.isEmpty(goodsID)){
			JOptionPane.showMessageDialog(null, "商品编号不能为空！");
			return;
		}
		if(StringUtils.isEmpty(goodsName)){
			JOptionPane.showMessageDialog(null, "商品名不能为空！");
			return;
		}

		if(StringUtils.isEmpty(goodsCount)){
			JOptionPane.showMessageDialog(null, "商品数量为空！");
			return ;
		}
		if(StringUtils.isEmpty(price)){
			JOptionPane.showMessageDialog(null, "商品价格不能为空！");
			return ;
		}
		
//		Goods goods =new Goods(Integer.parseInt(goodsID),goodsName,goodsCount,Float.parseFloat(price));
		
		try{
			
			if(userService.add_goods(goodsID,goodsName,goodsCount,price)  == 1){
				JOptionPane.showMessageDialog(null, "商品添加成功！");
				addGoodsReset();
			}else{
				JOptionPane.showMessageDialog(null, "商品添加失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "商品添加失败！");
		}
		
	}
	/**
	 * 重置表单
	 */
	private void addGoodsReset(){
		this.addGoodsIdTxt.setText("");
		this.addGoodsNameTxt.setText("");
		this.addGoodsCountTxt.setText("");
		this.addGoodsPriceTxt.setText("");
	}
}
