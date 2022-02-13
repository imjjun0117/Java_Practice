package day0211;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

/**
 * ������ ������ �޾�, �޽����� ������ �б� ���� Stream�� �����ϰ�
 * �޽����� ������ �Ͱ� ���ÿ� �����ϱ� ���ؼ� Thread�� �����Ͽ� �ڵ��Ѵ�
 * @author user
 */
public class ChatHelper extends Thread {

	private Socket someClient;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	
	private DefaultListModel<String> dlmServerMonitor;
	private int count;
	JScrollPane jspSeverMonitor;
	
	String nickName;
	public ChatHelper(Socket client, DefaultListModel<String> dlmServerMonitor,int cnt
			,JScrollPane jspServerMonitor)throws IOException {
		//������ ������ �ް�
		someClient = client;
		this.dlmServerMonitor = dlmServerMonitor;
		count = cnt;
		this.jspSeverMonitor = jspServerMonitor;
		//��Ʈ���� �����Ͽ� ��ȭ�� �о� ���� �� �ִ� ���¸� �����
		disReadStream = new DataInputStream(someClient.getInputStream());
		dosWriteStream = new DataOutputStream(someClient.getOutputStream());
		//client����� ���ÿ� nick name�� �о�鿩�´�(�� ��)
		nickName = disReadStream.readUTF();
		//������ ����Ϳ� �����ڰ� �������� �����ش�
		dlmServerMonitor.addElement(nickName+"���� �����ϼ̽��ϴ�");
		dlmServerMonitor.addElement(nickName+"���� ���� ["+someClient.getInetAddress()+"]");
		//��� �����ڿ��� ���� �����ڰ� �������� �˷��ش�
		broadcast(nickName+"���� �����ϼ̽��ϴ�");
		setScrollBar();
		
	}//ChatHelper
	
	private void setScrollBar() {
		jspSeverMonitor.getVerticalScrollBar().setValue(
				jspSeverMonitor.getVerticalScrollBar().getMaximum());
		
	}
	
	/**
	 * �޽����� ���ѷ����� �о�鿩 ��� �����ڵ鿡�� �����ش�
	 */
	@Override
	public void run() {// ChatServer�� run() �ȿ��� start() ȣ��
		
		String revMsg="";
//		Thread t = null;
		try {
		while(true) {
//			 t = new Thread( () -> {
//				try {
//					nick = disReadStream.readUTF().substring(disReadStream.readUTF().indexOf("[")+1,disReadStream.readUTF().indexOf("]"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}//end catch
//			});
//			 t.start();
			revMsg = disReadStream.readUTF(); // �޽����� �о�鿩
			 broadcast(revMsg); //��� �����ڿ��� ������
			}//end while
		} catch (IOException ie) {
			//�޽����� �о������ ���ϴ� ���´� �����ڰ� ������ ������ ����
			//�����ڸ� �����ϴ� ����Ʈ���� ���� ��ü(helper)�� �����ϰ�,
			ChatServer.connectList.remove(this);// ��� �����ڰ� �������� ���
			//���� â�� �����ڰ� �������� �˷��ش�
			dlmServerMonitor.addElement(nickName+"���� �����ϼ̽��ϴ�");
			setScrollBar();
			//��� �����ڿ��� �������� �˷��ش�
			try {
				broadcast(nickName+"���� �����ϼ˽��ϴ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch
			ie.printStackTrace();
		}//end catch
		
	}//run

	/**
	 * �Է¹��� �޽����� ��� �����ڿ��� ������ ��
	 * @param msg �����ڵ鿡�� ���� �޽���
	 * @throws IOException
	 */
	public synchronized void broadcast(String msg) throws IOException{
		for(ChatHelper ch : ChatServer.connectList) {
			ch.dosWriteStream.writeUTF(msg);
		}//end for
	}
}//class
