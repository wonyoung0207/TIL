package day05;

// �ΰ��� �ֻ����� ���� ���� 8�϶��� ����Ѵ�. 
public class Dice {
	
	public static void main(String[] args) {
		for(int i=1; i<= 6; i++) {
			for(int j=1; j <= 6; j++) {
				if((i + j) == 8) {
					System.out.printf("%d %d \t",i,j);
				}
			}
		}
		System.out.println("");

	}

}
