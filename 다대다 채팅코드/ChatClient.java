package day0211;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ChatClient extends JFrame implements ActionListener, Runnable {

	private JTextArea jtaTalkDisplay;
	private JTextField jtfServerIp, jtfNickName, jtfTalkInput;
	private JButton jbtnConnectServer, jbtnCapture;

	private JScrollPane jspJtaTalkDisplay;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	private Socket someClient;
	private Thread threadClient;

	public ChatClient() {
		super("::::::::::::: ä�� Ŭ���̾�Ʈ :::::::::::::::::::");
		jtfServerIp = new JTextField("211.63.89.", 8);
		jtfNickName = new JTextField(10);
		jtfTalkInput = new JTextField();

		jbtnConnectServer = new JButton("��������");
		jbtnCapture = new JButton("��ȭ����");

		jtaTalkDisplay = new JTextArea();
		jspJtaTalkDisplay = new JScrollPane(jtaTalkDisplay);

		jtaTalkDisplay.setEditable(false);

		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel("�����ּ�"));
		jpNorth.add(jtfServerIp);
		jpNorth.add(new JLabel("��ȭ��"));
		jpNorth.add(jtfNickName);
		jpNorth.add(jbtnConnectServer);
		jpNorth.add(jbtnCapture);

		jpNorth.setBorder(new TitledBorder("��������"));
		jspJtaTalkDisplay.setBorder(new TitledBorder("��ȭ����"));
		jtfTalkInput.setBorder(new TitledBorder("��ȭ"));

		add("North", jpNorth);
		add("Center", jspJtaTalkDisplay);
		add("South", jtfTalkInput);

		jbtnCapture.addActionListener(this);
		jbtnConnectServer.addActionListener(this);
		jtfTalkInput.addActionListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}// windowClosing

			@Override
			public void windowClosed(WindowEvent we) {
				try {
					if(disReadStream != null) { disReadStream.close();}
					if(dosWriteStream != null) { dosWriteStream.close();}
					if(someClient != null) { someClient.close();}
				}catch(IOException ie) {
					ie.printStackTrace();
				}finally {
					System.exit(JFrame.ABORT);
				}
			}// windowClosed

		});

		setBounds(100, 100, 700, 500);
		setVisible(true);

	}// ChatClient

	@Override
	public void run() {
		//�޽����� ���� ������ �о� ���δ� ( connectToServer()���� start()�� ȣ��)
		try {
			while(true) {
				jtaTalkDisplay.append(disReadStream.readUTF());
				jtaTalkDisplay.append("\n");
				//��ȭ�� ���뿡 ���� ��ũ�� �ٸ� ���� �Ʒ��� ������
				jspJtaTalkDisplay.getVerticalScrollBar().setValue(
						jspJtaTalkDisplay.getVerticalScrollBar().getMaximum());
				
			}
		}catch(IOException ie) {
			JOptionPane.showMessageDialog(this, "������ ����ƽ��ϴ�.");
			ie.fillInStackTrace();
		}
	}// run

	private void talkCapture() throws IOException {
		File directory = new File("e:/javachat/capture/");
		if (!directory.exists()) {
			directory.mkdirs();
		} // end if
		File file = new File(directory.getAbsolutePath() + "/cap_" + System.currentTimeMillis() + ".dat");// �ð��� �༭ ���ϸ�Ȱ�ġ��

		BufferedWriter bw = null;
		try {
			//ĸ�������� �����ϱ� ���� ��Ʈ���� ����
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(jtaTalkDisplay.getText());
			bw.flush();
			JOptionPane.showMessageDialog(this, file.getName()+"�� ��ȭ������ ����Ǿ����ϴ�");
		} finally {
			if (bw != null) {
				bw.close();
			} // end if
		} // end finally
	

	}// talkCapture

	private void connectToServer() throws IOException {
		
			if( someClient != null && someClient.isConnected()) {
				JOptionPane.showMessageDialog(this, "������ ���ӵǾ��ֽ��ϴ�");
				return;
			}
			//������ �����Ͽ� ������ �����ϰ� 
			String severIpAddress = jtfServerIp.getText().replaceAll("","");
			someClient = new Socket(severIpAddress,25000);
		//��Ʈ���� �����Ͽ� �����͸� �аų� ���� �� �ֵ��� �����
			disReadStream = new DataInputStream(someClient.getInputStream());
			dosWriteStream = new DataOutputStream(someClient.getOutputStream());
			dosWriteStream.writeUTF(jtfNickName.getText());
		//�����͸� �о���� �� �ִ� ����(������)
			threadClient = new Thread(this);
			threadClient.start();
			
			jtaTalkDisplay.setText("������ �����Ͽ����ϴ� \n ��ſ� ä�� \n");
			jtfTalkInput.requestFocus();
	}
	
	private void sendMsg() throws IOException{
		String talk = jtfTalkInput.getText();
		String nick = jtfNickName.getText();
		dosWriteStream.writeUTF("["+nick+"]"+talk);
		jtfTalkInput.setText("");
		
	}//sendMsg
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtnCapture) { // ��ȭ���� ����
			try {
				talkCapture();
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch

		} // end if
		if (ae.getSource() == jbtnConnectServer) {
			try {
				connectToServer();
			}catch( UnknownHostException e) {
				JOptionPane.showMessageDialog(this,"������ �������� �ʽ��ϴ�");
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}//end catch
		} // end if
		if (ae.getSource() == jtfTalkInput) {
			try {
				sendMsg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,"������ �������� �ʽ��ϴ�");
				e.printStackTrace();
			}
		} // end if
	}// actionPerformed

	public static void main(String[] args) {
		new ChatClient();
	}// main

}// class
