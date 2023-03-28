package 메타빌드코딩테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class fileReader2 {
	public static void main(String args[]) {
		ArrayList<String> fileList = new ArrayList<>();
		ArrayList<OneLineInfo> summaryList = new ArrayList<>();

		//		String path = System.getProperty("user.dir");// 상대경로 사용시 
		String filePath = "C:/Users/wony/OneDrive/바탕 화면/B형/"; // 불러올 초기 파일 위치
		String saveFilepath = "C:/Users/wony/OneDrive/바탕 화면/B형/ip파일저장/"; // 저장할 파일 위치 

		fileList = getfileList();
		System.out.println("++++++++++++++++++++ 파일 목록 표사 시작 ++++++++++++++++++++");
		printList(fileList);
		System.out.println("++++++++++++++++++++ 파일 목록 표시 끝  ++++++++++++++++++++");
		
		for(String filename : fileList) { // 파일 목록만큼 반복 
			fileInputOutput(filename, saveFilepath, summaryList); // 파일 정보 읽어와 ip별로 .txt 파일 나누기 
			System.out.println(" 다음 파일 넘어감 ");
		}


	}
	
	public static void printList(ArrayList list) {
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}


	public static ArrayList<String> getfileList() {
		File dir = new File("C:/Users/wony/OneDrive/바탕 화면/B형/ip파일저장/");
		ArrayList<String> fileList = new ArrayList<>();
		
		String[] filenames = dir.list();// ip.txt 로된 파일들의 리스트를 가져옴 
		for (String filename : filenames) {
			fileList.add(filename);
			
		}
		
		return fileList;
	}

		public static void fileInputOutput(String fileName, String saveFilePath, ArrayList<OneLineInfo> summaryList) {
			try {
				File file = new File(saveFilePath + fileName);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				ArrayList<String> list_split = null;
				OneLineInfo info = new OneLineInfo(fileName);
				int count_get = 0;
				int count_post= 0;
				int count_put = 0;
				int count_delete = 0;
				int all_count = 0;
				int err_count = 0;
				
				String line = "";
	
				while((line=br.readLine()) != null) {
					if(line.charAt(0) != '#') { // 앞에 # 붙은 내용 추가하지 않기 위해 
						list_split = strsplit(line); // 데이터 정제 : 한줄 정보가져와 데이터 정제 
						
						// 데이터 처리 
						cate_method(list_split.get(8) ,info); // 메소드별 요청 카운드 추가
						
						if(list_split.get(4).equals("201")) { // 에러체크 
							err_count++;
						}
						
						all_count ++; // 메소드 전체 요청 카운트 추가
						
						
					}
					System.out.println("");
				}
				
				// summary 한줄 생성 
				String ip = list_split.get(3);
//				double err_rate = (err_count / info.getAllSum()) * 100;
				info = new OneLineInfo(ip, fileName, all_count, count_get,count_post,count_put,count_delete, 0 , 0);
				
				
				// 파일에 쓰기 
				summaryList.add(info);
				// 이어쓰기 
				
				fileOutput(info.toString(),saveFilePath); // 정제한 데이터로 파일 입력
				
				br.close();
				
			} catch(IOException e) {
				e.getStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
	
		}
		
		
		public static ArrayList<String> strsplit(String oneLine) { // 하나의 라인을 가져와 ip 내용과 불필요한 내용을 분리 
			String[] oneline_split = oneLine.split(" "); //  11개로 잘라 해당 부분만 사용하기 위해 . ( 불필요한 뒷내용 버리기 )  
			String result = "";
			ArrayList<String> list_split = new ArrayList<>();
			
			for(int i=0; i < oneline_split.length; i++) {
				list_split.add(oneline_split[i]);
				
			}
			
			return list_split;
			
		}
		
		public static void cate_method(String methdo, OneLineInfo info) { // method 분류 
			switch (methdo) {
			case "GET" : info.setMethod_get(info.getMethod_get() + 1);
				break;
			case "POST" : info.setMethod_post(info.getMethod_post() + 1);
				break;
			case "PUT" : info.setMethod_put(info.getMethod_put() + 1);
				break;
			case "DELETE" : info.setMethod_delete(info.getMethod_delete() + 1);
				break;
			}
			
		}

		
		public static void fileOutput(String text, String saveFilePath) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(saveFilePath + "summary.txt", true)); // 파일 있으면 이어쓰기 
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
		
}
