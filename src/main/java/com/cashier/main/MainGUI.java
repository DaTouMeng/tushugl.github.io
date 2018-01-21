package com.cashier.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.cashier.action.GoodsFrm;
import com.cashier.model.User;
import com.cashier.service.UserService;
import com.cashier.service.impl.UserServiceImpl;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5437666955734385035L;
	private JPanel contentPane;
	private JTextField input;
    private JLabel msgLabel;
    private static String mobile;
    private JPanel panel;
    private static MainGUI frame;
	

	// 手机号码校验正则表达式[中国大陆]
	public static final String M_P = "^((13[0-9])|(14[5-8])|(15[^4,\\D])|(166)|(17[^4,\\D])|(18[0-9])|(19[8,9]))\\d{8}$";
	public static boolean matchPattern(final String regex, final String value) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();  //如果此方法返回true，当且仅当该字符串指定的正则表达式匹配
	}
	
	private static UserService userService = new UserServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new MainGUI();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 取消窗口默认关闭事件，是WindowListener事件起作用
					frame.addWindowListener(new WindowAdapter() {// 监听窗口关闭操作
						@Override
						public void windowClosing(WindowEvent e) {  //----windowClosed()是处理窗口关闭后这个事件，当窗口一旦消失，这个事件被触发。
							int option = JOptionPane.showConfirmDialog(null, "是否要关闭当前窗口?", "关闭操作", JOptionPane.YES_NO_OPTION);
							if (JOptionPane.YES_OPTION == option) {
								System.exit(0);  
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setResizable(false);
		setTitle("自助收银系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		 panel = new JPanel();
		 panel.setBackground(Color.GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
					.addGap(156))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(335, Short.MAX_VALUE))
		);
		
		 msgLabel = new JLabel("");
		 msgLabel.setBackground(SystemColor.controlShadow);
		msgLabel.setToolTipText("");
		msgLabel.setForeground(Color.RED);
		
		input = new JTextField();
		input.setColumns(10);
		input.setText("13232700111");
		
		JButton memberBtn = new JButton("进入会员");
		memberBtn.setIcon(new ImageIcon("./src/main/resources/icons/login.png"));
		
		memberBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					EnterMembers();
				}
			
		});
		
		JButton registerBtn = new JButton("新增会员");
		registerBtn.setIcon(new ImageIcon("./src/main/resources/icons/add.png"));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NewMembers();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addComponent(memberBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(registerBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(input)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(msgLabel, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
									.addGap(12)))))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msgLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(input, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(memberBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		// 设置JFrame居中显示
		this.setLocationRelativeTo(null);
	} 

	protected void NewMembers() {     //新增会员
		 mobile = input.getText();
		
		 
		String sql =  "select count(0) from user where mobile = ?";
		if (mobile == null || mobile.length() == 0) {
			msgLabel.setText("*请输入合法的手机号");
			msgLabel.setVisible(true);
			return;
		}
		if (!matchPattern(M_P, mobile)) {
			msgLabel.setText("*输入的手机号码不正常");
			msgLabel.setVisible(true);
			return;
		}
		if (userService.isExist(mobile,sql)) {
			JOptionPane.showMessageDialog(null, "当前会员已存在");   //提示模态对话框。
			return;
		}
		if (userService.register(mobile) > 0) {
			input.setText("");
			JOptionPane.showMessageDialog(null, "新增会员成功"); 
		} else {
			JOptionPane.showMessageDialog(null, "新增会员失败");
		}
		
	}

	protected void EnterMembers() {       //进入会员
	     mobile = input.getText();
		if (mobile == null || mobile.length() == 0) {
			msgLabel.setText("*请输入合法的手机号");
			msgLabel.setVisible(true);   //----是用来显示/隐藏一个GUI组件的。
			return;
		}
		if (!matchPattern(M_P, mobile)) {
			msgLabel.setText("*输入的手机号码不正常");
			msgLabel.setVisible(true);
			return;
		}
		User user = userService.getUser(mobile);
		if(user != null) {
			dispose();
			new GoodsFrm().setVisible(true);   //跳转到MainFrm窗口
		}else if(user == null) {
			msgLabel.setText("会员不存在");
			msgLabel.setVisible(true);
		}
		//JOptionPane.showMessageDialog(null, user.toString()); //显示一个带有OK 按钮的模态对话框。
	}
	public static  String getMobile() {
		return mobile;
	}
	
	
	
	
}
