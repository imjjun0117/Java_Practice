package day0208;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

/**
 * ��ü�� ���ų� �б� ���� Stream
 * @author user
 */
public class UseObjectStream {

	public void writeObject() throws IOException {
		MyData md = new MyData("������",182.5, 70.2); //����ȭ ��� ��ü
		//��ü(ObjectOutputStream)�� ����(FileOutputStream)�� ����� �� �ִ� Stream ����
		ObjectOutputStream oos = null;
		try {
			//1.��Ʈ�� ����
			oos = new ObjectOutputStream(new FileOutputStream(new File("e:/dev/temp/writeObj.dat")));
			//2.��ü ����
			oos.writeObject(md);
			//3.��Ʈ���� ������ ����
			oos.flush();
			JOptionPane.showMessageDialog(null, "��ü�� ��Ʈ���� ����Ͽ����ϴ�");
			
		}finally{
			//4.���� ���´�
			if(oos != null) { oos.close(); } //end if
		}
		
		
	}//writeObject
	
	public void readObject() throws IOException, ClassNotFoundException {
		//1.��Ʈ�� ����
		
		ObjectInputStream ois = null;
		
		try {
			// 1. ��Ʈ�� ����
			ois = new ObjectInputStream(new FileInputStream(new File("e:/dev/temp/writeObj.dat")));
			//2. ��ü �б�
			MyData md = (MyData)ois.readObject();
			//3.�о���� ��ü ���
			StringBuilder readData = new StringBuilder();
			readData.append("�̸� :").append(md.getName()).append("\n");
			readData.append("�̸� :").append(md.getHeight()).append("\n");
			readData.append("�̸� :").append(md.getWeight());
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
			data=JOptionPane.showInputDialog("��ȣ �Է�");
			if( data != null	) {
			switch(data){
			case "1" : try {
					uos.writeObject();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "��ü�� �� �� �����ϴ�.");
					e.printStackTrace();
				}//end catch 
			break;
			case "2" : try {
					uos.readObject();
					
				} catch (IOException e ) {
					JOptionPane.showMessageDialog(null, "��ü�� �� �� �����ϴ�");
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "�о���� ���Ͽ� ��ü�� �������� �ʽ��ϴ�.");
					e.printStackTrace();
				}//end catch
			break;
				
			case "3" : exitFlag = true; break;
			default : JOptionPane.showMessageDialog(null, "�޴��� 1,2,3 �� �� �ϳ��� �Է����ּ���");
			}//end switch
			}//end if
		}while(!exitFlag);
		JOptionPane.showMessageDialog(null, "������ּż� �����մϴ�");
	
		
	}//main

}//class
