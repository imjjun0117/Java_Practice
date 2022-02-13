package day0211;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ChatServer extends JFrame implements ActionListener, Runnable {

	private JButton jbtnOpenServer, jbtnClose;
	
	private JScrollPane jspServerMonitor;
	private JList<String> jlServerMonitor;
	private DefaultListModel<String> dlmServerMonitor;
		
	private Thread serverThread; // 접속자 소켓을 윈도우 디자인과 동시에 받기 위한 Thread
	public static List<ChatHelper> connectList; //접속자를 받기위한 List
	private ServerSocket server;
	
	public ChatServer() {
		super(":::::::채팅방 관리자::::::::::");
		
		connectList = new ArrayList<ChatHelper>();
		
		 jbtnOpenServer=new JButton("서버가동");
		 jbtnClose=new JButton("서버 종료");
			
		dlmServerMonitor=new DefaultListModel<String>();
		
		jlServerMonitor=new JList<String>( dlmServerMonitor );
		jspServerMonitor=new JScrollPane( jlServerMonitor);
				
		jspServerMonitor.setBorder(new TitledBorder("접속자 정보"));

		JPanel jpSouth=new JPanel();
		
		jpSouth.add( jbtnOpenServer);
		jpSouth.add( jbtnClose);
		
		add("Center",jspServerMonitor);
		add("South", jpSouth);
		
		jbtnClose.addActionListener(this);
		jbtnOpenServer.addActionListener(this);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}//windowClosing

			@Override
			public void windowClosed(WindowEvent we) {
				try {
					if(server != null) { 
					server.close();
					System.exit(JFrame.ABORT);
					}
				}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//windowClosed
			}
		});
		
		setBounds(100,100,500, 800);
		setVisible(true);
		
	}//ChatServer
	
	/**
	 * 접속자 소켓을 받고 헬퍼에 넣고 접속자가 보내오는 메시지를 읽을 수 있는 상태로 만든다
	 */
	@Override
	public void run() {
		try {
			server = new ServerSocket(25000); // PORT열기
			dlmServerMonitor.addElement("서버가 가동 중입니다");
			ChatHelper helper = null;
			for(int cnt = 0; connectList.size() < 20 ; cnt++) { //들어오는 접속자의 수(접속자 수를 제한할수도 있음)
				helper = new ChatHelper(server.accept(), dlmServerMonitor, cnt,jspServerMonitor);
				connectList.add(helper);
				//생성된 helper를 대화를 읽어들여, 보내줄 수 있는 상태로 만든다 -> Thread를 돌린다
				helper.start();
			}//end for
			
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//run

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jbtnClose) {
		dispose();	
		}//end if 
		
		if(ae.getSource() == jbtnOpenServer) {
			if( serverThread != null) {
				JOptionPane.showMessageDialog(this, "서버가 이미 가동 중 입니다");
				return; // 반환형이 없을 때 return : 아랫줄의 코드를 실행하지않고 호출 한 곳으로 돌아가 earyl retun
						  // 아랫줄은 else에 대한 코드를 작성한다
			}//end if
			
			// else를 작성하지 않고 else 역학을 하는 코드를 작성하면 된다
			serverThread = new Thread(this);
			serverThread.start(); // start -> run
			
			
		}//end if
		
	}//actionPerformed

	public static void main(String[] args) {
		new ChatServer();
	}//main

}//class
