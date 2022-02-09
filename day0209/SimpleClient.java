package day0209;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * ������ ip�ּҿ� ��Ʈ�� ������ ������ ã�� �����ϴ� ��. => client
 * @author user
 */
public class SimpleClient {
	public SimpleClient() throws UnknownHostException, IOException {
		Socket client=null;
		DataInputStream dis=null;
		try {
		String ip=JOptionPane.showInputDialog("�����ǳ־� �ּ���");
			//2.������ �����Ͽ� ������ ���� �õ�
		client=new Socket("192.168.219."+ip, 65500);
		System.out.println("������ ���� �õ��Ͽ� ���Ӽ���.");
		//4.  �������� �������� �޽����� �б����� ��Ʈ���� ����
		dis=new DataInputStream( client.getInputStream() );
		//5. �޽����� decode�Ͽ� �о���δ�.
		String revMsg=dis.readUTF();
		System.out.println("�������� �� �޽��� : "+ revMsg);
		}finally {
			if( dis != null ) { dis.close();}//end if
			if( client != null ) { client.close();}//end if
		}//end finally
	}//SimpleClient

	public static void main(String[] args) {
		try {
			new SimpleClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//main

}//class
