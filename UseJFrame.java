package day0128_work;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UseJFrame extends JFrame {
	//메뉴아이템 선언
	private JMenuItem jmiNew, jmiClose, jmiFont;
	private JTextArea jta;
	
	
	public UseJFrame() {
		super("메모장");
	
		
		//메뉴바 생성
		JMenuBar jmb = new JMenuBar();
		//메뉴 생성
		JMenu jmFile = new JMenu("파일");
		JMenu jmForm = new JMenu("서식");
		JMenu jmHelp = new JMenu("도움말");
		
		//중복되지 않는 아이템 생성 
		JMenuItem jmiOpen = new JMenuItem("열기");
		JMenuItem jmiSave = new JMenuItem("저장");
		JMenuItem jmiSaveAs = new JMenuItem("다른 이름으로 저장");
		JMenuItem jmiInfo = new JMenuItem("메모장 정보");
		//중복 메뉴 아이템 생성
		jmiNew = new JMenuItem("새 글");
		jmiClose = new JMenuItem("닫기");
		jmiFont = new JMenuItem("글꼴");
		
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		//메뉴아이템 -> 메뉴 
		jmFile.add(jmiNew);
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.add(jmiSaveAs);
		jmFile.addSeparator();
		jmFile.add(jmiClose);
		
		jmForm.add(jmiFont);
		
		jmHelp.add(jmiInfo);
		
		//메뉴 -> 메뉴바
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
