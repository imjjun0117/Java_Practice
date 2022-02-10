package day0210;

/**
 * Runnable interface를 구현한 클래스
 * @author user
 */
//1. Runnable 구현 
public class UseRannable implements Runnable {

	//2. run method Override
	@Override
	public void run() {
		//3. 동시에 실행되어야할 코드 Thread로 동작해야할 코드 정의
		for(int i = 0 ; i < 500; i++) {
			System.out.println("run i =======>"+i);
		}//end for
	}//run
	public void work() {
		
		for(int i = 0 ; i < 500; i++) {
			System.out.println("work i ------------->"+i);
		}//end for
	}//work 
	public static void main(String[] args) {
		//4. Thread로 동작할 코드를 가진 클래스를 객체화
		UseRannable ur = new UseRannable();
		//5. Thread 클래스와 has a 관계 설정
		Thread t = new Thread(ur);
		//6. Thread 클래스의 start() 호출
		t.start(); //start()에 의해 run() 호출
		System.out.println("---------------------------------------------------");
		ur.work();
	}//main

}//class
