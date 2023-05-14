package 메타빌드코딩테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader1 {
	
	public static void main(String args[]) {
		
//		String path = System.getProperty("user.dir");// 상대경로 사용시 
		String filePath = "C:/Users/wony/OneDrive/바탕 화면/B형/"; // 불러올 초기 파일 위치
		String saveFilepath = "C:/Users/wony/OneDrive/바탕 화면/B형/ip파일저장/"; // 저장할 파일 위치 


		fileInputOutput(filePath, saveFilepath); // 파일 정보 읽어와 ip별로 .txt 파일 나누기 
		
		

	}
	
	public static void fileInputOutput(String filePath, String saveFilePath) {
		try {
			File file = new File(filePath + "sample.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "";

			while((line=br.readLine()) != null) {
				if(line.charAt(0) != '#') { // 앞에 # 붙은 내용 추가하지 않기 위해 
					ArrayList<String> list_split = strsplit(line); // 데이터 정제 : 한줄 정보를 가져가서 ip와 필요한 정보만을 추출함 
					System.out.println("ip : " + list_split.get(0) + " text : " + list_split.get(1));
					
					fileOutput(list_split.get(0), list_split.get(1), saveFilePath); // 정제한 데이터로 파일 입력 
					
				}
				System.out.println("");
			}
			
			br.close();
			
		} catch(IOException e) {
			e.getStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void fileOutput(String ip, String text, String saveFilePath) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveFilePath + ip + ".txt", true));
			// 파일안에 문자열 쓰기
			bw.write(text + "\r\n"); // 파일추가시 줄나눔 하기 위해 추가 
			bw.flush();

			// 객체 닫기
			bw.close(); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static ArrayList<String> strsplit(String oneLine) { // 하나의 라인을 가져와 ip 내용과 불필요한 내용을 분리 
		String[] oneline_split = oneLine.split(" ", 12); //  11개로 잘라 해당 부분만 사용하기 위해 . ( 불필요한 뒷내용 버리기 )  
		String result = "";
		ArrayList<String> list_split = new ArrayList<>();
		
		for(int i=0; i < 11; i++) {
			result += oneline_split[i] + " "; 
			
		}
		list_split.add(oneline_split[3]); // ip 내용
		list_split.add(result); // 불필요한 내용 제거한 내용 
		
		
		return list_split;
		
	}

}
