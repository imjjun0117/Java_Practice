package day0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Scanner;

public class UseScanner {
	public UseScanner() throws IOException {
		
		System.out.println("당신의 나이를 입력해주세요");
//		//나이를 입력하면 태어난 해를 구하여 출력
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String value = br.readLine();
//		try {
//			int age = Integer.parseInt(value);
//			Calendar cal = Calendar.getInstance();
//			System.out.println("태어난 해는 : "+(cal.get(Calendar.YEAR)-age+1));
//		}catch (NumberFormatException nfe) {
//			System.err.println("나이는 숫자입니다");
//		}//end catch
	
			Scanner scan = new Scanner(System.in);
			int age = scan.nextInt(); // 원하는 데이터형으로 입력받을 수 있음
			Calendar cal = Calendar.getInstance();
			System.out.println("태어난 해는 : "+(cal.get(Calendar.YEAR)-age+1));
		
		
	}//UseScanner
	
	public static void main(String[] args) {

		try {
			new UseScanner();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
