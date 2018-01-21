package com.cashier.action;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.cashier.main.MainGUI;
import com.cashier.model.Transaction;
import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;

public class CheckBuyRecordFrm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4034837085093234864L;
	private JTable checkTable;
	private String mobile;
	
	private static UserService userService = new UserServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBuyRecordFrm frame = new CheckBuyRecordFrm();
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
	public CheckBuyRecordFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("消费记录");
		setBounds(100, 100, 905, 729);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("欢迎会员:");
		
		JLabel huiYuanTxt = new JLabel("");
		 mobile = MainGUI.getMobile();
		huiYuanTxt.setText(mobile);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(99)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(huiYuanTxt, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(huiYuanTxt))
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(91))
		);
		
		checkTable = new JTable();
		checkTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237id", "\u7528\u6237\u540D", "\u624B\u673A", "\u5546\u54C1\u540D", "\u8D2D\u4E70\u6570\u91CF", "\u6D88\u8D39\u65F6\u95F4"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3448591756541845707L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(checkTable);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new Transaction());
		
	}
	/**
	 * 初始化表格
	 * @param 
	 */
	private void fillTable(Transaction transaction){
		DefaultTableModel dtm=(DefaultTableModel) checkTable.getModel();//
		// getModel 来接收页表单域传递过来的model 对象，
		dtm.setRowCount(0); // 设置成0行
		
		mobile = MainGUI.getMobile();
		String sql="select * from transaction where mobile= ?";
		try{
			ResultSet rs  = userService.CheckUser( mobile,sql);
			if(rs.next() == false) {
				JOptionPane.showMessageDialog(null,"该用户没有消费记录");
			}
			while(rs.next()){
				Vector<String> v=new Vector<String>();
				v.add(rs.getString("user_id"));
				v.add(rs.getString("username"));
				v.add(rs.getString("mobile"));
				v.add(rs.getString("goodsName"));
				v.add(rs.getString("content"));
				v.add(rs.getString("deal_time"));
				dtm.addRow(v);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
