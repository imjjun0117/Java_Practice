package day0204;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UseFileReader {

	public UseFileReader() throws IOException {
		File file = new File("c:/dev/test/java_read.txt");
		FileReader fr = new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		String data = "";
		
		while((data=br.readLine()) != null) {
			System.out.println(data);
		}
		
		
		
		
	}//UseFileReader
	
	public static void main(String[] args) {
		try {
			new UseFileReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
