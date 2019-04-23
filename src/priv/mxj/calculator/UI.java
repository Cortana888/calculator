package priv.mxj.calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField; //存在问题：  Enter快捷键
import javax.swing.KeyStroke;

public class UI extends JFrame implements ActionListener {
	//绩点
	private double GpaNumber;
	//成绩
	private double GradeNumber;
	//学分
	private double CreditNumber;
	//总学风*绩点
	private double AllGradeNumber;
	//总学分
	private double AllCreditNumber;
	JTextField GpaView = new JTextField("0.000", 6);
	JTextField GradeView = new JTextField(6);
	JTextField CreditView = new JTextField(6);
	JLabel j1 = new JLabel("绩点:");
	JLabel j2 = new JLabel("成绩:");
	JLabel j3 = new JLabel("学分:");
	JButton SubmitButton = new JButton("提交");
	JButton ResetButton = new JButton("重置");
	JLabel GradeNumberMessage = new JLabel("");
	JLabel CreditNumberMessage = new JLabel("");

	public UI() {
		GpaNumber = 0;
		GradeNumber = 0;
		CreditNumber = 0;
		AllGradeNumber = 0;
		AllCreditNumber = 0;
		// 创建面板
		JPanel p = new JPanel();
		// 设置面板的布局
		p.setLayout(null);
		// 添加组件
		j1.setBounds(80, 20, 80, 30);
		p.add(j1);
		GpaView.setBounds(120, 20, 200, 30);
		p.add(GpaView);
		j2.setBounds(80, 70, 80, 30);
		p.add(j2);
		GradeView.setBounds(120, 70, 200, 30);
		p.add(GradeView);
		j3.setBounds(80, 120, 80, 30);
		p.add(j3);
		CreditView.setBounds(120, 120, 200, 30);
		p.add(CreditView);
		SubmitButton.setBounds(90, 170, 80, 30);
		p.add(SubmitButton);
		ResetButton.setBounds(240, 170, 80, 30);
		p.add(ResetButton);
		GradeNumberMessage.setBounds(120, 50, 150, 20);
		p.add(GradeNumberMessage);
		CreditNumberMessage.setBounds(120, 100, 150, 20);
		p.add(CreditNumberMessage);
		// 创建背景面板
		BackgroundPanel bgp = new BackgroundPanel(new ImageIcon("res/girl.jpg").getImage());
		bgp.setBounds(0, 0, 450, 250);
		p.add(bgp);
		// 为按钮添加事件监听
		SubmitButton.addActionListener(this);
		ResetButton.addActionListener(this);
		
		//SubmitButton.addKeyListener(this);
		SubmitButton.registerKeyboardAction(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
            {
            	// 定义正则表达式
    			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
    			if (!GradeView.getText().matches(regex1)) {
    				GradeNumberMessage.setText("输入的成绩在0-100之间");
    				GradeView.setText("");
    				GradeView.requestFocus();
    				return;
    			} else {
    				GradeNumberMessage.setText("");
    			}
    			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
    			if (!CreditView.getText().matches(regex2)) {
    				CreditNumberMessage.setText("输入的学分在0-10之间");
    				CreditView.setText("");
    				CreditView.requestFocus();
    				return;
    			} else {
    				CreditNumberMessage.setText("");
    			}

    			// 获取用户输入
    			GradeNumber = Double.parseDouble(GradeView.getText());
    			CreditNumber = Double.parseDouble(CreditView.getText());
    			AllCreditNumber += CreditNumber;
    			if (GradeNumber < 60) {
    				AllGradeNumber += 0;
    			} else {
    				AllGradeNumber += (2 + 0.2 * (GradeNumber - 60)) * CreditNumber;
    			}
    			GpaNumber = AllGradeNumber * 1.0 / AllCreditNumber;
    			GpaView.setText(String.valueOf(String.format("%.3f", GpaNumber)));
    			GradeView.setText("");
    			CreditView.setText("");
    			// 获取光标
    			GradeView.requestFocus();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		//ResetButton.addKeyListener(this);
		ResetButton.registerKeyboardAction(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	GradeNumberMessage.setText("");
    			CreditNumberMessage.setText("");
    			GpaView.setText("0.000");
    			GradeView.setText("");
    			CreditView.setText("");
    			GpaNumber = 0;
    			GradeNumber = 0;
    			CreditNumber = 0;
    			AllGradeNumber = 0;
    			AllCreditNumber = 0;
    			// 获取光标
    			GradeView.requestFocus();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		// 设置标题
		this.setTitle("沈航绩点计算器-By Mxj");
		// 设置窗体大小
		this.setSize(400, 250);
		// 设置窗口于屏幕中央
		this.setLocationRelativeTo(null);
		// 设置不可以调整窗口大小
		setResizable(false);
		// 设置窗口logo
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("res/computer.jpg"));
		// 设置窗口可见
		this.setVisible(true);
		// 用户单击窗口的关闭按钮时程序执行结束操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(p, BorderLayout.CENTER);
		// 获取光标
		this.GradeView.requestFocus();
	}

	// 触发动作事件时，执行的方法
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SubmitButton) {

			// 定义正则表达式
			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
			if (!GradeView.getText().matches(regex1)) {
				this.GradeNumberMessage.setText("输入的成绩在0-100之间");
				this.GradeView.setText("");
				this.GradeView.requestFocus();
				return;
			} else {
				this.GradeNumberMessage.setText("");
			}
			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
			if (!CreditView.getText().matches(regex2)) {
				this.CreditNumberMessage.setText("输入的学分在0-10之间");
				this.CreditView.setText("");
				this.CreditView.requestFocus();
				return;
			} else {
				this.CreditNumberMessage.setText("");
			}

			// 获取用户输入
			GradeNumber = Double.parseDouble(GradeView.getText());
			CreditNumber = Double.parseDouble(CreditView.getText());
			AllCreditNumber += CreditNumber;
			if (GradeNumber < 60) {
				AllGradeNumber += 0;
			} else {
				AllGradeNumber += (2 + 0.2 * (GradeNumber - 60)) * CreditNumber;
			}
			GpaNumber = AllGradeNumber * 1.0 / AllCreditNumber;
			GpaView.setText(String.valueOf(String.format("%.3f", GpaNumber)));
			GradeView.setText("");
			CreditView.setText("");
			// 获取光标
			this.GradeView.requestFocus();
		} else if (e.getSource() == ResetButton) {
			// 清零重置
			GradeNumberMessage.setText("");
			CreditNumberMessage.setText("");
			GpaView.setText("0.000");
			GradeView.setText("");
			CreditView.setText("");
			GpaNumber = 0;
			GradeNumber = 0;
			CreditNumber = 0;
			AllGradeNumber = 0;
			AllCreditNumber = 0;
			// 获取光标
			this.GradeView.requestFocus();
		}
	}

/*	// 按下回车
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			// 定义正则表达式
			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
			if (!GradeView.getText().matches(regex1)) {
				this.GradeNumberMessage.setText("输入的成绩在0-100之间");
				this.GradeView.setText("");
				this.GradeView.requestFocus();
				return;
			} else {
				this.GradeNumberMessage.setText("");
			}
			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
			if (!CreditView.getText().matches(regex2)) {
				this.CreditNumberMessage.setText("输入的学分在0-10之间");
				this.CreditView.setText("");
				this.CreditView.requestFocus();
				return;
			} else {
				this.CreditNumberMessage.setText("");
			}

			// 获取用户输入
			GradeNumber = Double.parseDouble(GradeView.getText());
			CreditNumber = Double.parseDouble(CreditView.getText());
			AllCreditNumber += CreditNumber;
			if (GradeNumber < 60) {
				AllGradeNumber += 0;
			} else {
				AllGradeNumber += (2 + 0.2 * (GradeNumber - 60)) * CreditNumber;
			}
			GpaNumber = AllGradeNumber * 1.0 / AllCreditNumber;
			GpaView.setText(String.valueOf(String.format("%.2f", GpaNumber)));
			GradeView.setText("");
			CreditView.setText("");
			// 获取光标
			this.GradeView.requestFocus();
		} else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			GradeNumberMessage.setText("");
			CreditNumberMessage.setText("");
			GpaView.setText("0.00");
			GradeView.setText("");
			CreditView.setText("");
			GpaNumber = 0;
			GradeNumber = 0;
			CreditNumber = 0;
			AllGradeNumber = 0;
			AllCreditNumber = 0;
			// 获取光标
			this.GradeView.requestFocus();
		}
	}

	// 弹起
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// 有字符输入
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
*/
	class BackgroundPanel extends JPanel {
		Image ico;

		public BackgroundPanel(Image image) {
			this.ico = image;
			// 设置控件不透明,若是false,那么就是透明
			this.setOpaque(true);
		}

		// Draw the background again,继承自Jpanle,是Swing控件需要继承实现的方法
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension d = this.getParent().getSize();
			// 绘制指定图像中当前可用的图像。图像的左上角位于该图形上下文坐标空间的 (x, y)。
			// 图像中的透明像素不影响该处已存在的像素
			g.drawImage(ico, 0, 0, d.width, d.height, this);
		}
	}
}
