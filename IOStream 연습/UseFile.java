package day0204;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class UseFile {

	public UseFile() {
		File file = new File("C:/dev/test/java_read.txt");
		System.out.println(file.canExecute());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.exists());
		System.out.println(file.getParent());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		try {
			System.out.println(file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Date date = new Date(file.lastModified());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-EEEE hh:mm:ss");
		System.out.println(sdf.format(date));
		
		File mk = new File("C:/dev/test/temp");
		mk.mkdir();
		
	}//UseFile
	public static void main(String[] args) {
		new UseFile();
		
	}//main

}//class
