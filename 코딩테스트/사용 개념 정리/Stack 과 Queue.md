# Stack

- 정의 : 사전적 의미로는 '쌓다', '더미'라는 뜻이 있습니다. 스택을 흔히 후입선출(선출후입), LIFO 라고 부르는

- 사용함수

  ```java
  public Element push(Element item); // 데이터 추가
  public Element pop(); // 최근에 추가된(Top) 데이터 삭제
  public Element peek(); // 최근에 추가된(Top) 데이터 조회
  public boolean empty(); // stack의 값이 비었는지 확인, 비었으면 true, 아니면 false
  public int size(); // stack의 길이를 리턴해줌 
  public int seach(Object o); // 인자값으로 받은 데이터의 위치 반환, 그림으로 설명하겠음
  ```

- 사용예시

  ```java
  Stack<Integer> stack = new Stack<>();
          for (int i = 0; i < 5; i++) {
              stack.push(i + 1);
              System.out.println(stack.peek());
          } // 1, 2, 3, 4, 5 가 현재 들어가 있음
          stack.pop(); // 1, 2, 3, 4
          System.out.println(stack.peek()); // 4
          System.out.println(stack.search(1)); // 4
          System.out.println(stack.empty()); // false
  ```

---

# Queue

- 정의 : 

  - 큐 메모리 구조는 선형 메모리 공간에 데이터를 저장하면서 선입선출(FIFO)의 시멘틱을 따르는 자료 구조이다. 
  - 즉, 가장 먼저 저장된(push) 데이터가 가장 먼저 인출(pop)되는 구조

- 용어

  - enqueue : Queue에 데이터 추가하기
  - Dequeue : Queue에서 데이터 추출하기
  - peek : Queue에서 데이터 확인하기

- 사용 함수 

  - Enqueue : **offer**, **add**
  - Dequeue : **poll**, **remove**
  - peek : **peek**, **element**

  ```java
  Queue<String> q = new LinkedList<String>();
  q.offer("강감찬");  // Queue에 데이터 추가							
  q.offer("홍길동");  // Queue에 데이터 추가
  System.out.println(q.size());  // Queue사이즈 확인하기
  
  System.out.println(q.peek()); // Queue에서 데이터 확인하기 (데이터가 Queue에서 꺼내지는 않는다.)
  System.out.println(q.poll()); // Queue에서 데이터 추출하기 (데이터가 Queue에서 꺼내져, Queue에서는 존재하지 않는다.)
  System.out.println(q.size());
  
  q.offer("이순신");
  q.offer("김구");
  System.out.println(q.size());
  
  // Queue에서 전체 데이터 추출하기 
  while(!q.isEmpty()) {
      System.out.println(q.poll());
  }
  ```

