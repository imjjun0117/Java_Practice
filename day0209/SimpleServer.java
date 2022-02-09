package day0209;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PORT를 열고 접속자가 들어오기를 기다리는 프로그램. => Server
 * @author user
 */
public class SimpleServer {
	
	public SimpleServer() throws IOException {
		//1. 서버소켓 생성. (PORT가 열림.) PORT는 선점이어서 이 클래스를 두번 실행하면 에러.
		ServerSocket server=null;
		Socket client=null;
		DataOutputStream dos=null;
//		try {
			server=new ServerSocket(65500);
			System.out.println("서버가동 중....");
			//3. 접속자가 들어오면 접속자 소켓을 허가하여 받는다.
			while( true ) {
				client=server.accept();
				System.out.println("접속자 있음." + client.getInetAddress());
				
				//접속자에게 보내줄 메시지
				String sendMsg="안녕하세요? 황성준입니다!";
				
				//4.접속자에게 메시지를  보내기 위한 스트림을 연결한다.
				dos=new DataOutputStream(client.getOutputStream());
				//5.스트림에 데이터 쓰기
				dos.writeUTF(sendMsg);//한글이 인코딩된다.
				//6. 목적지로 분출.
				dos.flush();//스트림의 내용을 목적지로 분출!
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
