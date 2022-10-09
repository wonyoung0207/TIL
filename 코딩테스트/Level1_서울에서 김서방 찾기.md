

# < Level 1 > 

# 서울에서 김서방 찾기 

> new ArrayList<>() 와 Arrays.asList 의 차이점 

---

## 문제설명 

- String형 배열 seoul의 element중 "Kim"의 위치 x를 찾아, "김서방은 x에 있다"는 String을 반환하는 함수, solution을 완성하세요. seoul에 "Kim"은 오직 한 번만 나타나며 잘못된 값이 입력되는 경우는 없습니다.


## 제한사항 

- seoul은 길이 1 이상, 1000 이하인 배열입니다.
- seoul의 원소는 길이 1 이상, 20 이하인 문자열입니다.
- "Kim"은 반드시 seoul 안에 포함되어 있습니다.

## 입출력 예

| seoul           | return              |
| --------------- | ------------------- |
| ["Jane", "Kim"] | "김서방은 1에 있다" |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public String solution(String[] seoul) {
       String answer = "";
   
       for(int i = 0; i < seoul.length; i++){
           if(seoul[i].equals("Kim")){
               answer = "김서방은 " + i + "에 있다";
               return answer;
           }
       }
   
       return answer;
   }
   ```

2. 특이한 풀이 

   ```java
   public String findKim(String[] seoul){
       //x에 김서방의 위치를 저장하세요.
       int x = Arrays.asList(seoul).indexOf("Kim");
   
       return "김서방은 "+ x + "에 있다";
   }
   ```

3. 향상된 for문 사용

   ```java
   for (String name : seoul) {
       if(name.equals("Kim"))
           break;
       x++;
   }
   ```

   


---

## 사용된 개념

1. Arrayslist 만들기 

   - List를 만드는 방법 

     1. new ArrayList<>()
     2. Arrays.asList()

   - 차이점 

     1. 리턴값 

        1. `new ArrayList<>()`는 **ArrayList**를
        2. `Arrays.asList()`는 **Arrays**의 정적 클래스인 **ArrayList**를 리턴

     2. 원소의 추가 삭제 

        1. `new ArrayList<>()`는 할 수 있고, `Arrays.asList()` 할 수 없다.

        - 이유는 new ArrayList<>() 로 생성하는건 ArrayList를 만드는 것이고, Arrays.asList()는 정적인 상태로 특정한 배열에 의해 백업된 고정 크기 List를 반환받는것이기 때문이다 
        - 말 그대로 `Arrays.asList()`는 고정된 List다. List 원소를 **추가** 할 수도, **삭제**할 수도 없다.

   
