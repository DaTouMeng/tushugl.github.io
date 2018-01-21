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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;

public class PayFrm extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6759541282725679880L;
	private JTextField payTxt;
	private static UserService userService = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayFrm frame = new PayFrm();
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
	public PayFrm() {
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 780, 635);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5145\u503C\u7A97\u53E3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(93)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(308, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(177, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("充值金额:");
		
		payTxt = new JTextField();
		payTxt.setColumns(10);
		
		JButton PayBtn = new JButton("充值");
		PayBtn.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		PayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payMethod();
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(payTxt, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(55)
							.addComponent(PayBtn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(461, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(payTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(PayBtn)
					.addContainerGap(158, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	protected void payMethod() {
		String payBalance2 = payTxt.getText();
		String payBalance = payBalance2.trim();

		
		try {
			int payNum = userService.pay(payBalance);
				 if(payNum ==1) {
					 JOptionPane.showMessageDialog(null, "充值成功");
				 }else {
					 JOptionPane.showMessageDialog(null, "充值失败");
				 }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
}
