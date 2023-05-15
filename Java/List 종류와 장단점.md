### List 종류 

1. ArrayList
   - 가변 크기의 배열로 구현된 List로, 데이터의 삽입, 삭제, 접근에 효율적이다. 
2. LinkedList
   - 연결 리스트로 구현된 List로, 데이터의 삽입, 삭제에 효율적이며, 순차적인 접근보다는 임의 접근에 적합하다. 
3. Vector
   - 동기화된(ArrayList와 유사한) 가변 크기의 배열로 구현된 List이다. 
   - 멀티스레드 환경에서 안전하게 사용할 수 있다. 
4. Stack
   - 후입선출(LIFO) 구조로 구현된 List로, 주로 스택 자료구조로 사용된다. 

### 삽입, 삭제에 가장 좋은 LIst 

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList to store integers
        List<Integer> numbers = new ArrayList<>();

        // Add elements to the ArrayList
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        // Retrieve elements from the ArrayList
        int firstNumber = numbers.get(0);
        int lastNumber = numbers.get(numbers.size() - 1);

        System.out.println("First Number: " + firstNumber);
        System.out.println("Last Number: " + lastNumber);
    }
}

```