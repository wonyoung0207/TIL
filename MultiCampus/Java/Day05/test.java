package day05;

public class test {

	public static void main(String[] args) {
		for (int i = 2; i < 10; i++) {
			if(i % 2 == 0) {
				continue;
			}
			System.out.println(i + "´Ü");

			for (int j = 1; j < 10; j++) {
				if((i*j) == 28) {
					break;
				}
				System.out.printf("%d x %d = %d \t", i,j,i*j);
				
			}
			
		}
		

	}

}
