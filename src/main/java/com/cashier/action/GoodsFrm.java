package com.cashier.action;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;

public class GoodsFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -422250412247645223L;
	private JPanel contentPane;
	private JDesktopPane table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsFrm frame = new GoodsFrm();
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
	public GoodsFrm() {
		setTitle("自助收银系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1079, 1022);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("商品操作");
		menu.setIcon(new ImageIcon(GoodsFrm.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-more-details@2x.png")));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("商品上架");
		menuItem.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoodsFrm AddGoodsFrm=new AddGoodsFrm();
				AddGoodsFrm.setVisible(true);
				table.add(AddGoodsFrm);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("商品展示销售");
		menuItem_1.setIcon(new ImageIcon(GoodsFrm.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent-rtl.png")));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowGoodsFrm ShowGoodsFrm=new ShowGoodsFrm();
				ShowGoodsFrm.setVisible(true);
				table.add(ShowGoodsFrm);
			}
		});
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("商品下架");
		menuItem_2.setIcon(new ImageIcon("./src/main/resources/icons/delete.png"));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelGoodsFrm DelGoodsFrm=new DelGoodsFrm();
				DelGoodsFrm.setVisible(true);
				table.add(DelGoodsFrm);
			}
		});
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("商品修改");
		menuItem_3.setIcon(new ImageIcon("./src/main/resources/icons/modify.png"));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifiGoodsFrm ModifiGoodsFrm=new ModifiGoodsFrm();
				ModifiGoodsFrm.setVisible(true);
				table.add(ModifiGoodsFrm);
			}
		});
		menu.add(menuItem_3);
		
		JMenu menu_1 = new JMenu("用户中心");
		menu_1.setIcon(new ImageIcon("./src/main/resources/icons/userName.png"));
		menuBar.add(menu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("消费查询");
		mntmNewMenuItem.setIcon(new ImageIcon("./src/main/resources/icons/search.png"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckBuyRecordFrm checkBuyRecordFrm=new CheckBuyRecordFrm();
				checkBuyRecordFrm.setVisible(true);
				table.add(checkBuyRecordFrm);
			}
		});
		menu_1.add(mntmNewMenuItem);
		
		JMenuItem menuItem_4 = new JMenuItem("余额充值");
		menuItem_4.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PayFrm fayFrm=new PayFrm();
				fayFrm.setVisible(true);
				table.add(fayFrm);
			}
		});
		menu_1.add(menuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		 table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
		// 设置JFrame居中显示
					this.setLocationRelativeTo(null);
	}
}
