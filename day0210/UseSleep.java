package day0210;

//1. Runnable 
public class UseSleep implements Runnable{

	//2. run method를 Override 
	@Override
	public void run() {
		//3. Thread로 동작해야할 코드 정의
		try {
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
			//지정한 시간동안 block 상태로 이전
				Thread.sleep((60*60*24)*1000);  //스케줄링이 가능 
		}//end for
			} catch (InterruptedException e) { // 종료가 되지않으면 interrupt 예외 발생
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}//run

	public static void main(String[] args) {
		//4.객체화
		UseSleep us = new UseSleep();
		//5. Thread 클래스와 has a 관계
		Thread t = new Thread(us);
		//6.start() 호출
		t.start();
	}//main

}//class
