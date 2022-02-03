package day0128_work;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UseJFrame extends JFrame {
	//�޴������� ����
	private JMenuItem jmiNew, jmiClose, jmiFont;
	private JTextArea jta;
	
	
	public UseJFrame() {
		super("�޸���");
	
		
		//�޴��� ����
		JMenuBar jmb = new JMenuBar();
		//�޴� ����
		JMenu jmFile = new JMenu("����");
		JMenu jmForm = new JMenu("����");
		JMenu jmHelp = new JMenu("����");
		
		//�ߺ����� �ʴ� ������ ���� 
		JMenuItem jmiOpen = new JMenuItem("����");
		JMenuItem jmiSave = new JMenuItem("����");
		JMenuItem jmiSaveAs = new JMenuItem("�ٸ� �̸����� ����");
		JMenuItem jmiInfo = new JMenuItem("�޸��� ����");
		//�ߺ� �޴� ������ ����
		jmiNew = new JMenuItem("�� ��");
		jmiClose = new JMenuItem("�ݱ�");
		jmiFont = new JMenuItem("�۲�");
		
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		//�޴������� -> �޴� 
		jmFile.add(jmiNew);
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.add(jmiSaveAs);
		jmFile.addSeparator();
		jmFile.add(jmiClose);
		
		jmForm.add(jmiFont);
		
		jmHelp.add(jmiInfo);
		
		//�޴� -> �޴���
		jmb.add(jmFile);
		jmb.add(jmForm);
		jmb.add(jmHelp);
		
		UseJFrameEvt uje = new UseJFrameEvt(this);
		jmiClose.addActionListener(uje);
		jmiNew.addActionListener(uje);
		jmiFont.addActionListener(uje);
		
		setJMenuBar(jmb);
		add(jsp);
		setSize(800,500);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UseJFrame
	
	
	
	
	public JMenuItem getJmiNew() {
		return jmiNew;
	}//getJmiNew


	public JMenuItem getJmiClose() {
		return jmiClose;
	}//getJmiClose


	public JMenuItem getJmiFont() {
		return jmiFont;
	}//getJmiFont


	public JTextArea getJta() {
		return jta;
	}//getJta


	public static void main(String[] args) {
		new UseJFrame();
	
	}//main

}//class
