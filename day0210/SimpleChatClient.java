package day0210;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SimpleChatClient extends JFrame implements ActionListener {
	
	private JTextArea jtaTalkDisplay;
	private JTextField jtfTalk;
	private JScrollPane jspJtaTalkDisplay;
	
	private Socket client;//서버에 연결
	private DataInputStream disReadStream; //데이터를 읽기
	private DataOutputStream dosWriteStream; // 데이터를 쓰기
	
	public SimpleChatClient(String ip) {
		super(":::::::::채팅클라이언트::::::::::");
		
		jtaTalkDisplay=new JTextArea();
		jspJtaTalkDisplay=new JScrollPane( jtaTalkDisplay );
		jtfTalk=new JTextField();
		
		jtaTalkDisplay.setEditable(false);

		jspJtaTalkDisplay.setBorder(new TitledBorder("대화내용"));
		jtfTalk.setBorder(new TitledBorder("대화"));
		
		add("Center",jspJtaTalkDisplay);
		add("South",jtfTalk);
		
		setBounds(100, 100, 400, 600);
		setVisible(true);
		
		try {
			connectToServer(ip);
			readMsg();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
		jtfTalk.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				
				dispose();
			}//windowClosing
		});
	
	}//SimpleChatServer

	private void connectToServer(String ip) throws IOException,MalformedURLException {
		client = new Socket("211.63.89."+ip,50000); //서버에 연결
		disReadStream = new DataInputStream(client.getInputStream());
		dosWriteStream = new DataOutputStream(client.getOutputStream());
		jtaTalkDisplay.setText("서버에 접속하였습니다\n");
		
	}//connectToServer
	
	/**
	 * 무한루프로 메시지 읽기
	 * @throws IOException
	 */
	private void readMsg() throws IOException{//회사에서는 무조건 public 
		while(true) {
			jtaTalkDisplay.append(disReadStream.readUTF()+"\n");
			setScrollbar();
		}//end while
	}//readMsg
	
	private void sendMsg() throws IOException{
		String msg = jtfTalk.getText();
		StringBuilder sbMsg = new StringBuilder();
		sbMsg.append("황성준 클라이언트 : ").append(msg);
		
		//내 대화창에 메시지를 올리고
		jtaTalkDisplay.append(sbMsg.toString()+"\n");
		//접속자에게 메시지를 보내자
		dosWriteStream.writeUTF(sbMsg.toString());
		dosWriteStream.flush();
		
		//내 대화창 초기화
	jtfTalk.setText("");
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
		}catch(IOException e) {
			e.printStackTrace();
		}//end catch
	}//actionPerformed

	public static void main(String[] args) {
		String ip=JOptionPane.showInputDialog("접속할 IP를 입력해 주세요.\n131,133,134,135,153,140,141,142,143,144,157,147,155,149,150,151,152,148");
		if( ip != null && !ip.isEmpty()) {
		new  SimpleChatClient(ip);
		}
	}//main

}//class
