

# < Level 1 > 

# 평균 구하기 

---

## 문제설명 

- 정수를 담고 있는 배열 arr의 평균값을 return하는 함수, solution을 완성해보세요


## 제한사항 

- arr은 길이 1 이상, 100 이하인 배열입니다.
- arr의 원소는 -10,000 이상 10,000 이하인 정수입니다.

## 입출력 예

| arr       | return |
| --------- | :----: |
| [1,2,3,4] |  2.5   |
| [5,5]     |   5    |

## 풀이 

1. 내풀이 

   ```java
   public double solution(int[] arr) {
       double answer = 0;
       int sum = 0;
   
       for(int a : arr) {
           sum += a;
       }
       answer = (double) sum / arr.length;
   
       return answer;
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   public double solution(int[] arr) {
   	return (int) Arrays.stream(arr).average().orElse(0);
       
       // 비슷한 풀이 
       return Arrays.stream(arr).average().getAsDouble();
   }
   ```

   

## 사용된 개념

### 스트림

- 정의

  - 스트림은 Java8에서 추가된 기능으로 컬렉션 데이터를 **선언형**으로 쉽게 처리할수 있습니다. 복잡한 루프문을

    사용하지 않아도 되며 루프문을 중첩해서 사용해야 되는 최악의 경우도 더이상 없어졌습니다.

  - 따라서 스트림은 배열, 컬렉션 데이터를 더욱 쉽게 활용할 수 있는 라이브러리이다. 

- 특징

  - 선언형 : 더 간결하고 가독성이 좋아진다.
  - 함수의 조립 : 유연성이 좋아진다.
  - 병렬화 : 성능이 좋아진다.

- 구조 

  1. 스트림생성
     - Arrays.stream(arr)
       - 여기서 stream(arr) 가 스트림 생성에 해당한다. 
       - Arrays 는 스트림 함수를 가진 객체이다. 

  2. 중개 연산 ( 조건을 거는 함수 )
     - Filter : 조건에 맞는 것만 거른다.
       - names.stream(arr).filter(x -> x.contains("o"));
     - Sorted : 스트림의 요소들을 정렬 
     - Limit : 스트림 갯수 제한 
       - ages.steam(arr).filter( x -> x > 3).limit(3);
       - 3보다 큰 숫자들 중 3개만 출력 

  3. 최종 연산

     - 실제 사용법으로 표기하면 **"Collections같은 객체 집합.스트림생성().중개연산().최종연산();"** 이런식이다.* 계속해서 . 으로 연계할 수 있게 하는 방법을 파이프라인이라고도 한다.

     - count(), min(), max(), sum(), average()

     - reduce : 누적된 값을 계산하는 함수 

       ```java
       List<Integer> ages = new ArrayList<Integer>();
       ages.add(1);ages.add(2);ages.add(3);//1,2,3
       System.out.println(ages.stream().reduce((b,c) -> b+c).get());//1+2+3=6
       ```

-  **파이프라이닝, 내부 반복** 이라는 중요한 특징이 있습니다.

  - 파이프라이닝 (Pipelining)
    - 스트림 연산들은 서로 연결하여 큰 파이프 라인을 구성할수 있도록 스트림 자신을 반환합니다. 데이터 소스에 적용하는 데이터베이스 질의문과 비슷합니다.
  - 내부 반복
    - 반복자를 이용하여 명시적으로 반복하는 컬렉션과 다르게 스트림은 내부 반복 기능을 제공합니다.

