package day0128_work;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class UseJDialog extends JDialog {
	private JTextField jtfFont;
	private JTextField jtfStyle;
	private JTextField jtfSize;
	
	private JList<String> jltFont;
	private JList<String> jltStyle;
	private JList<String> jltSize;
	
	private JButton jbtOk;
	private JButton jbtCancel;
	
	private JLabel jlPreview;
	
	
	
	public UseJDialog(UseJFrame ujf) {
	super(ujf,"�۲�",true);

	setLayout(null);
	UseJDialogEvt ujde = new UseJDialogEvt(this,ujf);
	
	JLabel jlFont = new JLabel("�۲�(F):");
	JLabel jlStyle = new JLabel("�۲� ��Ÿ��(Y):");
	JLabel jlSize = new JLabel("ũ��(S):");
	jlFont.setBounds(14,15,100,16);
	jlStyle.setBounds(190,15,100,16);
	jlSize.setBounds(330,15,100,16);
	add(jlFont);
	add(jlStyle);
	add(jlSize);
	
	jtfFont = new JTextField("���� ���");
	jtfStyle = new JTextField("����");
	jtfSize = new JTextField("12");
	jtfFont.setBounds(14,33,160,25);
	jtfStyle.setBounds(190,33,120,25);
	jtfSize.setBounds(330,33,60,25);
	
	jtfFont.addActionListener(ujde);
	jtfStyle.addActionListener(ujde);
	jtfSize.addActionListener(ujde);
	add(jtfFont);
	add(jtfSize);
	add(jtfStyle);
	
	
	String[] font= {"����","���� ���","�ü�ü","Serif","SansSerif"};
	String[] style= {"����","����","����Ӳ�","���� ����Ӳ�"};
	DefaultListModel<String> dlmFont = new DefaultListModel<String>();
	for(int i = 0; i < font.length; i++) {
		dlmFont.addElement(font[i]);
	}//end for
	DefaultListModel<String> dlmStyle = new DefaultListModel<String>();
	for(int i = 0; i < style.length; i++) {
		dlmStyle.addElement(style[i]);
	}//end for
	DefaultListModel<String> dlmSize = new DefaultListModel<String>();
	for(int i = 8; i <81; i++) {
		dlmSize.addElement(String.valueOf(i));
		if(i>9) i++;
	}//end for
	jltFont = new JList<String>(dlmFont);
	jltStyle = new JList<String>(dlmStyle);
	jltSize = new JList<String>(dlmSize);
	//�ʱⰪ ����
	jltFont.setSelectedValue("���� ���", true);
	jltStyle.setSelectedValue("����", true);
	jltSize.setSelectedValue("12", true);
	//���� ����
	jltFont.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	jltStyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	jltSize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	//�̺�Ʈ ó��
	jltFont.addListSelectionListener(ujde);
	jltStyle.addListSelectionListener(ujde);
	jltSize.addListSelectionListener(ujde);
	
	JScrollPane jcpFont = new JScrollPane(jltFont);
	JScrollPane jcpStyle = new JScrollPane(jltStyle);
	JScrollPane jcpSize = new JScrollPane(jltSize);
	
	jcpFont.setBounds(14,58,160,135);
	jcpStyle.setBounds(190,58,120,135);
	jcpSize.setBounds(330,58,60,135);
	add(jcpFont);
	add(jcpStyle);
	add(jcpSize);
	
	jlPreview = new JLabel("AaBbYyZz");
	jlPreview.setBorder(new TitledBorder("����"));
	jlPreview.setBounds(190,210,200,120);
	add(jlPreview);
	
	jbtOk = new JButton("Ȯ��");
	jbtCancel = new JButton("���");
	//�̺�Ʈ ó��
	jbtOk.addActionListener(ujde);
	jbtCancel.addActionListener(ujde);
	
	jbtOk.setBounds(235,450,75,30);
	jbtCancel.setBounds(320,450,75,30);
	
	add(jbtOk);
	add(jbtCancel);
	
	setBounds(ujf.getX()+100,ujf.getY()+50,450,550);
	setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}//UseJDialog
	public JTextField getJtfFont() {
		return jtfFont;
	}
	public JTextField getJtfStyle() {
		return jtfStyle;
	}
	public JTextField getJtfSize() {
		return jtfSize;
	}
	public JList<String> getJltFont() {
		return jltFont;
	}
	public JList<String> getJltStyle() {
		return jltStyle;
	}
	public JList<String> getJltSize() {
		return jltSize;
	}
	public JButton getJbtOk() {
		return jbtOk;
	}
	public JButton getJbtCancel() {
		return jbtCancel;
	}
	public JLabel getJlPreview() {
		return jlPreview;
	}
	

}//class
