package day0210;

/**
 * loading . . . . . . . . . . . . . .finish�� ��´�
 * ��"."�� 20���̰� �ּ� 0ms ~ 900ms�� ����ȭ�� �ð����� ����Ѵ�
 * @author user
 */
//1.Runnable ����
public class Homework implements Runnable {
	//2. run method Override
	@Override
	public void run() {
		//3. Thread ���� �ڵ� ����
		try {
		for(int i = 0; i < 20; i++) {
			System.out.print(" . ");
			// ���� �ð��� 0ms ~ 900ms ���� �������� sleep()
				Thread.sleep((long) (Math.random()*901));
			}//end for
			System.out.print("finish");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch
	}//run

	public static void main(String[] args) {
		//4.��üȭ
		Homework hw = new Homework();
		//5.has a ���� 
		Thread t = new Thread(hw);
		System.out.print("loading");
		//6. start() ȣ��
		t.start();
		
	}//main

}//class
