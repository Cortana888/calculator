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
import javax.swing.JTextField; //�������⣺  Enter��ݼ�
import javax.swing.KeyStroke;

public class UI extends JFrame implements ActionListener {
	//����
	private double GpaNumber;
	//�ɼ�
	private double GradeNumber;
	//ѧ��
	private double CreditNumber;
	//��ѧ��*����
	private double AllGradeNumber;
	//��ѧ��
	private double AllCreditNumber;
	JTextField GpaView = new JTextField("0.000", 6);
	JTextField GradeView = new JTextField(6);
	JTextField CreditView = new JTextField(6);
	JLabel j1 = new JLabel("����:");
	JLabel j2 = new JLabel("�ɼ�:");
	JLabel j3 = new JLabel("ѧ��:");
	JButton SubmitButton = new JButton("�ύ");
	JButton ResetButton = new JButton("����");
	JLabel GradeNumberMessage = new JLabel("");
	JLabel CreditNumberMessage = new JLabel("");

	public UI() {
		GpaNumber = 0;
		GradeNumber = 0;
		CreditNumber = 0;
		AllGradeNumber = 0;
		AllCreditNumber = 0;
		// �������
		JPanel p = new JPanel();
		// �������Ĳ���
		p.setLayout(null);
		// ������
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
		// �����������
		BackgroundPanel bgp = new BackgroundPanel(new ImageIcon("res/girl.jpg").getImage());
		bgp.setBounds(0, 0, 450, 250);
		p.add(bgp);
		// Ϊ��ť����¼�����
		SubmitButton.addActionListener(this);
		ResetButton.addActionListener(this);
		
		//SubmitButton.addKeyListener(this);
		SubmitButton.registerKeyboardAction(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
            {
            	// ����������ʽ
    			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
    			if (!GradeView.getText().matches(regex1)) {
    				GradeNumberMessage.setText("����ĳɼ���0-100֮��");
    				GradeView.setText("");
    				GradeView.requestFocus();
    				return;
    			} else {
    				GradeNumberMessage.setText("");
    			}
    			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
    			if (!CreditView.getText().matches(regex2)) {
    				CreditNumberMessage.setText("�����ѧ����0-10֮��");
    				CreditView.setText("");
    				CreditView.requestFocus();
    				return;
    			} else {
    				CreditNumberMessage.setText("");
    			}

    			// ��ȡ�û�����
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
    			// ��ȡ���
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
    			// ��ȡ���
    			GradeView.requestFocus();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		// ���ñ���
		this.setTitle("�򺽼��������-By Mxj");
		// ���ô����С
		this.setSize(400, 250);
		// ���ô�������Ļ����
		this.setLocationRelativeTo(null);
		// ���ò����Ե������ڴ�С
		setResizable(false);
		// ���ô���logo
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("res/computer.jpg"));
		// ���ô��ڿɼ�
		this.setVisible(true);
		// �û��������ڵĹرհ�ťʱ����ִ�н�������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(p, BorderLayout.CENTER);
		// ��ȡ���
		this.GradeView.requestFocus();
	}

	// ���������¼�ʱ��ִ�еķ���
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SubmitButton) {

			// ����������ʽ
			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
			if (!GradeView.getText().matches(regex1)) {
				this.GradeNumberMessage.setText("����ĳɼ���0-100֮��");
				this.GradeView.setText("");
				this.GradeView.requestFocus();
				return;
			} else {
				this.GradeNumberMessage.setText("");
			}
			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
			if (!CreditView.getText().matches(regex2)) {
				this.CreditNumberMessage.setText("�����ѧ����0-10֮��");
				this.CreditView.setText("");
				this.CreditView.requestFocus();
				return;
			} else {
				this.CreditNumberMessage.setText("");
			}

			// ��ȡ�û�����
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
			// ��ȡ���
			this.GradeView.requestFocus();
		} else if (e.getSource() == ResetButton) {
			// ��������
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
			// ��ȡ���
			this.GradeView.requestFocus();
		}
	}

/*	// ���»س�
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			// ����������ʽ
			String regex1 = "^100$|^(\\d|[1-9]\\d)(\\.\\d+)*$";
			if (!GradeView.getText().matches(regex1)) {
				this.GradeNumberMessage.setText("����ĳɼ���0-100֮��");
				this.GradeView.setText("");
				this.GradeView.requestFocus();
				return;
			} else {
				this.GradeNumberMessage.setText("");
			}
			String regex2 = "^(\\d(\\.\\d{1,2})?|10)$";
			if (!CreditView.getText().matches(regex2)) {
				this.CreditNumberMessage.setText("�����ѧ����0-10֮��");
				this.CreditView.setText("");
				this.CreditView.requestFocus();
				return;
			} else {
				this.CreditNumberMessage.setText("");
			}

			// ��ȡ�û�����
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
			// ��ȡ���
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
			// ��ȡ���
			this.GradeView.requestFocus();
		}
	}

	// ����
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// ���ַ�����
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
*/
	class BackgroundPanel extends JPanel {
		Image ico;

		public BackgroundPanel(Image image) {
			this.ico = image;
			// ���ÿؼ���͸��,����false,��ô����͸��
			this.setOpaque(true);
		}

		// Draw the background again,�̳���Jpanle,��Swing�ؼ���Ҫ�̳�ʵ�ֵķ���
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension d = this.getParent().getSize();
			// ����ָ��ͼ���е�ǰ���õ�ͼ��ͼ������Ͻ�λ�ڸ�ͼ������������ռ�� (x, y)��
			// ͼ���е�͸�����ز�Ӱ��ô��Ѵ��ڵ�����
			g.drawImage(ico, 0, 0, d.width, d.height, this);
		}
	}
}
