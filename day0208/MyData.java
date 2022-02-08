package day0208;

import java.io.Serializable;

/**
 * Stream을 타고 JVM 외부로 나갈 수 있는 클래스
 * @author user
 */

public class MyData implements Serializable {
	/**
	 * 이 번호는 객체를 식별하기위한 번호이고 1년 후에는 다른 번호로  변경됩니다.
	 */
	private static final long serialVersionUID = 7865162799135629193L;
	//transient : 직렬화 방지 키워드, 기본형데이터형, 참조형 데이터형이 직렬화되지 못하도록 
	// 막을 때 사용
	private /*transient*/ String name;
	private /*transient*/ double height;
	private double weight;
	
	public MyData() {
	}//MyData

	
	public MyData(String name, double height, double weight) {
		this.name = name;
		this.height = height;
		this.weight = weight;
	}//MyData


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	
}//class
