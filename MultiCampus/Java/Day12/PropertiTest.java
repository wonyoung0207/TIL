package day12;

import java.util.Properties;

public class PropertiTest {

	public static void main(String[] args) {
		Properties pr = new Properties();
		
		String path = PropertiTest.class.getResource("dataTest.properties.txt").getPath();//���� ������ ��θ� �����Ѵ�. 
		
//		pr.load(new FileReader(path));
		
//		String url = pr.getProperty("url");
		
		
		System.out.println(path);
		
		

	}

}
