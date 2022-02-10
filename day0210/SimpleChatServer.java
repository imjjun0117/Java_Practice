package day0210;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SimpleChatServer extends JFrame implements ActionListener {

	private JTextArea jtaTalkDisplay;
	private JTextField jtfTalk;
	private JScrollPane jspJtaTalkDisplay;

	private ServerSocket server; // PORT ����, ������ ������ �ް� ����
	private Socket client; // �����ڰ� ������ ����
	private DataInputStream disReadStream; // �������� �޽����� �б����� ��Ʈ��
	private DataOutputStream dosWriteStream; // �����ڿ��� �޽����� ���������� ��Ʈ��

	public SimpleChatServer() {
		super(":::::::::ä�ü���::::::::::");

		jtaTalkDisplay = new JTextArea();
		jspJtaTalkDisplay = new JScrollPane(jtaTalkDisplay);
		jtfTalk = new JTextField();

		jtaTalkDisplay.setEditable(false);

		jspJtaTalkDisplay.setBorder(new TitledBorder("��ȭ����"));
		jtfTalk.setBorder(new TitledBorder("��ȭ"));

		add("Center", jspJtaTalkDisplay);
		add("South", jtfTalk);

		setBounds(100, 100, 400, 600);
		setVisible(true);
		
		try {
			openServer();
			readMsg();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//end catch
		

	
	
		jtfTalk.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}// windowClosing

			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (disReadStream != null) {
						disReadStream.close();
					} // end if
					if (dosWriteStream != null) {
						dosWriteStream.close();
					} // end if
					if (client != null) {
						client.close();
					} // end if
					if (server != null) {
						server.close();
					} // end if
				} catch (IOException ie) {
					ie.printStackTrace();
				} finally {
					System.exit(JFrame.ABORT);
				} // end finally
			}// windowComboBox
		});
	}// SimpleChatServer
		
	private void openServer() throws IOException {
		if (server == null) {
			server = new ServerSocket(50000);
			jtaTalkDisplay.setText("���� ���� ��...\n�����ڰ� �����⸦ ��ٸ�...\n");
			
			client = server.accept();//�����ڰ� ������ �����ڸ� �޴´�
			jtaTalkDisplay.append("������ ������\n");
			//��ȭ�� �����ų� ���� �� �ֵ��� ���Ͽ��� ��Ʈ���� ����
			disReadStream = new DataInputStream(client.getInputStream());
			dosWriteStream = new DataOutputStream(client.getOutputStream());
			jtaTalkDisplay.append("��ſ� ä�õǼ���/\n");
		} // end if
	}// openServer

	/**
	 * �����ڰ� �������� �޽����� ���ѷ����� �о�鿩 J.T.A�� ��� 
	 */
	public void readMsg() {
		try {
		while(true) {
//			try { //while �ȿ� catch�� ��� ����Ǿ� ���ܸ� �������� ���
			jtaTalkDisplay.append(disReadStream.readUTF()+"\n");
			setScrollbar();
//			}catch(IOException ie) {
//				ie.printStackTrace();
//			}
		}//end  while
		}catch(IOException ie) {
			jtaTalkDisplay.append("��ȭ��밡 ä���� �����Ͽ����ϴ�");
			ie.printStackTrace();
		}//end catch
	}//readMsg
	
	public void sendMsg() throws IOException{
		String msg = jtfTalk.getText();
		dosWriteStream.writeUTF("Ȳ���� ���� : "+msg); // �����ڿ��� �޽����� ������
		dosWriteStream.flush();
		jtaTalkDisplay.append("Ȳ���� ���� : "+msg+"\n");//�� ��ȭâ���� �޽����� �ø���
		jtfTalk.setText(""); // ��ȭ�Է�â�� �ʱ�ȭ�Ѵ�
		setScrollbar();
	}//sendMsg
	
	/**
	 * ä���� ���� �� ��ũ���� ���γ�� �� ����
	 */
	private void setScrollbar() {
	jspJtaTalkDisplay.getVerticalScrollBar().setValue(jspJtaTalkDisplay.getVerticalScrollBar().getMaximum());	
	//���� �ְ� ��ũ�ѹٸ� �������ְڴ�
	}//setScrollbar
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			sendMsg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
	}// actionPerformed

	public static void main(String[] args) {
		new SimpleChatServer();
	}// main

}// class
