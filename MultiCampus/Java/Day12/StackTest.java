import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Stack<String> st = new Stack<String>();
		st.push("a");
		st.push("b");
		st.push("c");
		st.push("d");
		
		
		for(String s : st) {
			System.out.print(s + ", ");
		}
		System.out.println();
		
		while(!st.isEmpty()) {
			String s = st.pop();
			System.out.print(s + " ,");
		}
		
		
	}

}
