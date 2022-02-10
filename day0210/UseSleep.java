package day0210;

//1. Runnable 
public class UseSleep implements Runnable{

	//2. run method�� Override 
	@Override
	public void run() {
		//3. Thread�� �����ؾ��� �ڵ� ����
		try {
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
			//������ �ð����� block ���·� ����
				Thread.sleep((60*60*24)*1000);  //�����ٸ��� ���� 
		}//end for
			} catch (InterruptedException e) { // ���ᰡ ���������� interrupt ���� �߻�
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}//run

	public static void main(String[] args) {
		//4.��üȭ
		UseSleep us = new UseSleep();
		//5. Thread Ŭ������ has a ����
		Thread t = new Thread(us);
		//6.start() ȣ��
		t.start();
	}//main

}//class
