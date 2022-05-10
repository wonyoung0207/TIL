package day08;

public class CompanyApp {

	public static void main(String[] args) {
		Employee e = new Employee("1", "wony", 300);
		System.out.println(e);
		System.out.println(e.year_salary());
		
		Manager m = new Manager("2", "Manager", 300,500);
//		System.out.println(m);
//		System.out.println(m.toString());
		System.out.println(m.year_salary());
		System.out.println("¼¼±Ý : " + m.getBonusTex());
		
		Employee arr_e[] = new Employee[4];
		arr_e[0] = new Employee("1", "a", 300);
		arr_e[1] = new Employee("2", "b", 400);
		arr_e[2] = new Manager("3", "c", 500, 500);
		arr_e[3] = new Manager("4", "d", 600, 800);
		
		for(int i=0; i< arr_e.length; i++) {
			System.out.println(arr_e[i]);
		}
		
		for(int i=0; i< arr_e.length; i++) {
			if(arr_e[i] instanceof Manager) {
				Manager manager = (Manager)arr_e[i];
				System.out.println(m.getBonusTex());
			}
		}
		
		
		
		
	}

}
