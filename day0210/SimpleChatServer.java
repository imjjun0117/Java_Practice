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

	private ServerSocket server; // PORT 열기, 접속자 소켓을 받고 관리
	private Socket client; // 접속자가 생성한 소켓
	private DataInputStream disReadStream; // 접속자의 메시지를 읽기위한 스트림
	private DataOutputStream dosWriteStream; // 접속자에게 메시지를 보내기위한 스트림

	public SimpleChatServer() {
		super(":::::::::채팅서버::::::::::");

		jtaTalkDisplay = new JTextArea();
		jspJtaTalkDisplay = new JScrollPane(jtaTalkDisplay);
		jtfTalk = new JTextField();

		jtaTalkDisplay.setEditable(false);

		jspJtaTalkDisplay.setBorder(new TitledBorder("대화내용"));
		jtfTalk.setBorder(new TitledBorder("대화"));

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
			jtaTalkDisplay.setText("서버 가동 중...\n접속자가 들어오기를 기다림...\n");
			
			client = server.accept();//접속자가 들어오면 접속자를 받는다
			jtaTalkDisplay.append("접속자 들어왔음\n");
			//대화를 보내거나 읽을 수 있도록 소켓에세 스트림을 연결
			disReadStream = new DataInputStream(client.getInputStream());
			dosWriteStream = new DataOutputStream(client.getOutputStream());
			jtaTalkDisplay.append("즐거운 채팅되세요/\n");
		} // end if
	}// openServer

	/**
	 * 접속자가 보내오는 메시지를 무한루프로 읽어들여 J.T.A에 출력 
	 */
	public void readMsg() {
		try {
		while(true) {
//			try { //while 안에 catch가 계속 실행되어 예외를 무한으로 출력
			jtaTalkDisplay.append(disReadStream.readUTF()+"\n");
			setScrollbar();
//			}catch(IOException ie) {
//				ie.printStackTrace();
//			}
		}//end  while
		}catch(IOException ie) {
			jtaTalkDisplay.append("대화상대가 채팅을 종료하였습니다");
			ie.printStackTrace();
		}//end catch
	}//readMsg
	
	public void sendMsg() throws IOException{
		String msg = jtfTalk.getText();
		dosWriteStream.writeUTF("황성준 서버 : "+msg); // 접속자에게 메시지를 보내고
		dosWriteStream.flush();
		jtaTalkDisplay.append("황성준 서버 : "+msg+"\n");//내 대화창에도 메시지를 올리고
		jtfTalk.setText(""); // 대화입력창을 초기화한다
		setScrollbar();
	}//sendMsg
	
	/**
	 * 채팅이 쌓일 때 스크롤이 따로노는 걸 방지
	 */
	private void setScrollbar() {
	jspJtaTalkDisplay.getVerticalScrollBar().setValue(jspJtaTalkDisplay.getVerticalScrollBar().getMaximum());	
	//현재 최고 스크롤바를 세팅해주겠다
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
