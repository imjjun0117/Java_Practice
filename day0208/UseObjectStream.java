package day0208;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

/**
 * 객체를 쓰거나 읽기 위한 Stream
 * @author user
 */
public class UseObjectStream {

	public void writeObject() throws IOException {
		MyData md = new MyData("김현우",182.5, 70.2); //직렬화 대상 객체
		//객체(ObjectOutputStream)를 파일(FileOutputStream)에 출력할 수 있는 Stream 생성
		ObjectOutputStream oos = null;
		try {
			//1.스트림 생성
			oos = new ObjectOutputStream(new FileOutputStream(new File("e:/dev/temp/writeObj.dat")));
			//2.객체 쓰고
			oos.writeObject(md);
			//3.스트림의 내용을 분출
			oos.flush();
			JOptionPane.showMessageDialog(null, "객체를 스트림에 기록하였습니다");
			
		}finally{
			//4.연결 끊는다
			if(oos != null) { oos.close(); } //end if
		}
		
		
	}//writeObject
	
	public void readObject() throws IOException, ClassNotFoundException {
		//1.스트림 연결
		
		ObjectInputStream ois = null;
		
		try {
			// 1. 스트림 연결
			ois = new ObjectInputStream(new FileInputStream(new File("e:/dev/temp/writeObj.dat")));
			//2. 객체 읽기
			MyData md = (MyData)ois.readObject();
			//3.읽어들인 객체 사용
			StringBuilder readData = new StringBuilder();
			readData.append("이름 :").append(md.getName()).append("\n");
			readData.append("이름 :").append(md.getHeight()).append("\n");
			readData.append("이름 :").append(md.getWeight());
			JOptionPane.showMessageDialog(null, readData.toString());
		}finally {
			if(ois != null) ois.close();
		}
				
		
	}//readObject
	
	public static void main(String[] args) {
		UseObjectStream uos = new UseObjectStream();
		boolean exitFlag = false;
		String data = "";
		do {
			data=JOptionPane.showInputDialog("번호 입력");
			if( data != null	) {
			switch(data){
			case "1" : try {
					uos.writeObject();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "객체를 쓸 수 없습니다.");
					e.printStackTrace();
				}//end catch 
			break;
			case "2" : try {
					uos.readObject();
					
				} catch (IOException e ) {
					JOptionPane.showMessageDialog(null, "객체를 쓸 수 없습니다");
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "읽어들인 파일에 객체가 존재하지 않습니다.");
					e.printStackTrace();
				}//end catch
			break;
				
			case "3" : exitFlag = true; break;
			default : JOptionPane.showMessageDialog(null, "메뉴는 1,2,3 번 중 하나만 입력해주세요");
			}//end switch
			}//end if
		}while(!exitFlag);
		JOptionPane.showMessageDialog(null, "사용해주셔서 감사합니다");
	
		
	}//main

}//class
