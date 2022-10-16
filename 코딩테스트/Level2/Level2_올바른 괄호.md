

# < Level 2 > 

# 올바른 괄호

> Stack, Queue 개념, Stack 의 pop(), push()

---

## 문제설명 

- 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어

  - "()()" 또는 "(())()" 는 올바른 괄호입니다.
  - ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.

  '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.


## 제한사항 

- 문자열 s의 길이 : 100,000 이하의 자연수
- 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.

## 입출력 예

| s        | answer |
| -------- | ------ |
| "()()"   | true   |
| "(())()" | true   |
| ")()("   | false  |
| "(()("   | false  |

##### 입출력 예 설명

입출력 예 #1,2,3,4
문제의 예시와 같습니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   boolean solution(String s) {
       boolean answer = true;
       Stack<Character> stk = new Stack<Character>();
       char c;
   
   
       for(int i=0; i< s.length(); i++){
           c = s.charAt(i);
           if(c == ')'){//pop()
               if(stk.isEmpty()){
                   return false;
               }
   
               stk.pop();
           }else{// ( 일 경우 push() 로 넣어줌 
               stk.push('(');
           }
       }
   
       if(!stk.isEmpty()){// stack 이 다 비워지지 않은 경우 실행 
           return false;
       }
       return answer;
   }
   ```

2. switch case로 풀이 

   ```java
   boolean solution(String s) {
       Stack<Character> stack = new Stack<>();
   
       for (int i = 0; i < s.length(); i++) {
           switch (s.charAt(i)) {
               case '(':
                   stack.add(s.charAt(i));
                   break;
               case ')':
                   if (stack.isEmpty()) {
                       return false;
                   }
   
                   if (stack.peek() == '(') {
                       stack.pop();
                   } else {
                       return false;
                   }
                   break;
           }
       }
       return stack.isEmpty();
   }
   ```

3. 스텍 안쓰고 쉬운 풀이 

   ```java
   boolean solution(String s) {
       boolean answer = false;
       int count = 0;
       for(int i = 0; i<s.length();i++){
           if(s.charAt(i) == '('){
               count++;
           }
           if(s.charAt(i) == ')'){
               count--;
           }
           if(count < 0){
               break;
           }
       }
       if(count == 0){
           answer = true;
       }
       return answer;
   }
   ```

4. toCharArray() 사용한 방법

   ```java
   boolean solution(String s) {
       boolean answer = true;
       char[] carray = s.toCharArray();
       int cnt=0;
       for(char c : carray){
           if(c=='('){
               cnt++;
           }else if(c==')'){
               if(cnt>0){
                   cnt--;
               }else {
                   answer=false;
                   break;
               }
           }
       }
       if(cnt!=0){
           answer=false;
       }
       return answer;
   }
   ```

   



---

## 사용된 개념

1. Stack

   - 정의 : 사전적 의미로는 '쌓다', '더미'라는 뜻이 있습니다. 스택을 흔히 후입선출(선출후입), LIFO 라고 부르는

   - 사용함수

     ```java
     public Element push(Element item); // 데이터 추가
     public Element pop(); // 최근에 추가된(Top) 데이터 삭제
     public Element peek(); // 최근에 추가된(Top) 데이터 조회
     public boolean empty(); // stack의 값이 비었는지 확인, 비었으면 true, 아니면 false
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

2. Queue

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

   
