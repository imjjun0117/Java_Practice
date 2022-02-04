package day0204;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UseFileStream {

	public UseFileStream() throws IOException {
		File file = new File("c:/dev/test/java_read.txt");
		FileInputStream fis = new FileInputStream(file);
		int data = 0;
		
		while((data = fis.read()) != -1) {
			System.out.println((char)data);
		}
		fis.close();
	}//UseFileStream
	public static void main(String[] args) {
		try {
			new UseFileStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//main

}//class
