package day0210;

/**
 * loading . . . . . . . . . . . . . .finish를 찍는다
 * 단"."는 20개이고 최소 0ms ~ 900ms의 차등화된 시간으로 출력한다
 * @author user
 */
//1.Runnable 구현
public class Homework implements Runnable {
	//2. run method Override
	@Override
	public void run() {
		//3. Thread 동작 코드 정의
		try {
		for(int i = 0; i < 20; i++) {
			System.out.print(" . ");
			// 지정 시간을 0ms ~ 900ms 까지 랜덤으로 sleep()
				Thread.sleep((long) (Math.random()*901));
			}//end for
			System.out.print("finish");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch
	}//run

	public static void main(String[] args) {
		//4.객체화
		Homework hw = new Homework();
		//5.has a 관계 
		Thread t = new Thread(hw);
		System.out.print("loading");
		//6. start() 호출
		t.start();
		
	}//main

}//class
