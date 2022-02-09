package day0209;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PORT�� ���� �����ڰ� �����⸦ ��ٸ��� ���α׷�. => Server
 * @author user
 */
public class SimpleServer {
	
	public SimpleServer() throws IOException {
		//1. �������� ����. (PORT�� ����.) PORT�� �����̾ �� Ŭ������ �ι� �����ϸ� ����.
		ServerSocket server=null;
		Socket client=null;
		DataOutputStream dos=null;
//		try {
			server=new ServerSocket(65500);
			System.out.println("�������� ��....");
			//3. �����ڰ� ������ ������ ������ �㰡�Ͽ� �޴´�.
			while( true ) {
				client=server.accept();
				System.out.println("������ ����." + client.getInetAddress());
				
				//�����ڿ��� ������ �޽���
				String sendMsg="�ȳ��ϼ���? �� ������ �������� ���� ����.. ����Ƽ�� -����";
				
				//4.�����ڿ��� �޽�����  ������ ���� ��Ʈ���� �����Ѵ�.
				dos=new DataOutputStream(client.getOutputStream());
				//5.��Ʈ���� ������ ����
				dos.writeUTF(sendMsg);//�ѱ��� ���ڵ��ȴ�.
				//6. �������� ����.
				dos.flush();//��Ʈ���� ������ �������� ����!
			}//end while
//		}finally {
//			if( dos != null ) { dos.close(); }//end if
//			if( client != null ) { client.close(); }//end if
//			if( server != null ) { server.close(); }//end if
//		}//end finally
		
	}//SimpleServer

	public static void main(String[] args) {
		try {
			new SimpleServer();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
