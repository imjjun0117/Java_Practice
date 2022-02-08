package day0208;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FileCopy extends JFrame {
	
	private JLabel jlblOutput;
	private FileCopy fc;
	
	public FileCopy() {
		super("파일 복사");
		
		jlblOutput = new JLabel("출력");
		JButton jbtnFileChoose = new JButton("파일 선택");
		
		jlblOutput.setFont(new Font("궁서체",Font.BOLD,20));
		fc = this;
		jbtnFileChoose.addActionListener(new BtnEventProcess());
		
		//배치
		JPanel jpCenter = new JPanel();
		jpCenter.add( jbtnFileChoose );
		
		add("Center", jpCenter);
		add("South", jlblOutput);
		
		setBounds(100,100,400,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//FileCopy
	
	////////////////////////////////////////////////////버튼에 이벤트를 처리하기 위한 Inner class 시작//////////////////////////
	public class BtnEventProcess implements ActionListener{
		
		public void copyFile(File originalFile) throws IOException{
			//스트림을 연결한다
			FileInputStream fis = null;
			FileOutputStream fos = null;
			
			try {
				//1. 원본 파일에 스트림 연결
				fis = new FileInputStream(originalFile);
				//2. 복사할 파일명 설정 : 원본파일명_copy로 이름 설정
				StringBuilder copyFileName = new StringBuilder(originalFile.getAbsolutePath());
				copyFileName.insert(copyFileName.lastIndexOf("."),"_copy");
				
				File copyFile = new File(copyFileName.toString());
				
				//3. 출력스트림 생성
				fos = new FileOutputStream(copyFile);
				//4.HDD에서 한번에 읽어들인 데이터를 저장할 ㅂ빈 배열 생성
				byte[] data = new byte[512];
				
				//5.데이터 읽기
				int dataSize = 0;
				while((dataSize = fis.read(data)) != -1) {
					//6. 배열에 저장된 데이터의 크기까지를 스트림에 기록
					fos.write(data,0,dataSize);
				}//end while
	
				///////////////////////////성능 저하///////////////////////////////
//				//4. 원본 파일에서 1byte씩 읽어들여
//				int readData = 0;
//				while((readData = fis.read()) != -1) { //데이터가 존재한다면
//					//5. 복사할 파일에 쓴다. ( 출력할 파일에 연결된 스트림에 기록 )
//					fos.write(readData);
//				}//end while
				
				///////////////////////////HDD를 고려하지 않는 코드 성능 저하///////////////////////////////
				//6. 스트림에 남아있는 데이터를 분출
				fos.flush();
				JOptionPane.showMessageDialog(fc , copyFile.getName()+"으로 복사되었습니다.");
				jlblOutput.setText(copyFile.getAbsolutePath());
				
			}finally {
				if( fis != null) { fis.close(); } // end if
				if( fos != null) { fos.close(); } // end if
			}//end finally
		}//copyFile
		
		
		public void copyFile16(File originalFile) throws IOException{
			//스트림을 연결한다
			BufferedReader br = null;
			FileWriter fw = null;
			
			try {
				//1. 원본 파일에 스트림 연결
				br = new BufferedReader(new FileReader(originalFile));
				//2. 복사할 파일명 설정 : 원본파일명_copy로 이름 설정
				StringBuilder copyFileName = new StringBuilder(originalFile.getAbsolutePath());
				copyFileName.insert(copyFileName.lastIndexOf("."),"_copy");
				
				File copyFile = new File(copyFileName.toString());
				
				//3. 출력스트림 생성
				fw = new FileWriter(copyFile);
				
				//4. 원본 파일에서 1byte씩 읽어들여
				String readData = "";
				while((readData = br.readLine()) != null) { //데이터가 존재한다면
					//5. 복사할 파일에 쓴다. ( 출력할 파일에 연결된 스트림에 기록 )
					fw.write(readData);
				}//end while
				//6. 스트림에 남아있는 데이터를 분출
				fw.flush();
				JOptionPane.showMessageDialog(fc , copyFile.getName()+"으로 복사되었습니다.");
				jlblOutput.setText(copyFile.getAbsolutePath());
				
			}finally {
				if( br != null) { br.close(); } // end if
				if( fw != null) { fw.close(); } // end if
			}//end finally
		}//copyFile
		
		
		
		public void openDialog() {
			FileDialog fdOpen = new FileDialog(fc,"파일선택",FileDialog.LOAD);
			fdOpen.setVisible(true);
			
			String path = fdOpen.getDirectory();
			String name = fdOpen.getFile();
			
			File originalFile = new File(path+name); // 선택한 파일로 파일 객체를 생성하고
			
			if( originalFile.exists() ) { // 파일이 존재한다면
				try {
					copyFile( originalFile );  // 복사 작업 수행
				}catch(IOException e) {
					JOptionPane.showMessageDialog(fc,"ㅈㅅ");
					e.printStackTrace();
				}
			}
		}//openDialog
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			openDialog();
		
		}//actionPerformed
		
	}
	////////////////////////////////////////////////////버튼에 이벤트를 처리하기 위한 Inner class 끝//////////////////////////
	
	public static void main(String[] args) {
		new FileCopy();
	}//main

}//class
