package day0204;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class UseFileWriter extends JFrame {

	public void fileReader() throws IOException {
		String msg = "�� �̸��� Ȳ�����Դϴ�.";
		// �����ϴ� �����̸� confirm dialog�� �̿��� ����� ��-����� �ƴϿ�,��� - ����� �ʴ´�
		File file = new File("c:/dev/test/java_read2.txt");
		FileWriter fw = null;
		boolean flag = false;
		if (file.exists()) {
			int confirm = JOptionPane.showConfirmDialog(this, file.getAbsoluteFile() + "�� �����մϴ�. ������?");
			switch (confirm) {
			case JOptionPane.OK_OPTION:
				flag = true;
			}// end switch
		} else {
			flag = true;
		} // end else

		if (flag) {
			try {
				fw = new FileWriter(file);
				fw.write(msg);
				fw.flush();
			} finally {
				if (fw != null)
					fw.close();
			} // end finally
		} // end if
	}// fileReader

	public static void main(String[] args) {
		UseFileWriter ufw = new UseFileWriter();
		try {
			ufw.fileReader();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
	}// main

}// class
