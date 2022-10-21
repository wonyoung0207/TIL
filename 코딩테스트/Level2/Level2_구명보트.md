

# < Level 2 > 

# 구명보트

> 

---

## 문제설명 

- 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 **2명**씩 밖에 탈 수 없고, 무게 제한도 있습니다.

  예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.

  구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.

  사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.


## 제한사항 

- 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
- 각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.

## 입출력 예

| people           | limit | return |
| ---------------- | ----- | ------ |
| [70, 50, 80, 50] | 100   | 3      |
| [70, 80, 50]     | 100   | 3      |

## 풀이 

1. 좋은 풀이 

   ```java
   int answer = 0;
   
   //무게 순으로 정렬
   Arrays.sort(people);
   
   int min_index = 0; //가장 가벼운 사람 위치
   int max_index = people.length - 1; //가장 무거운 사람 위치
   
   		/*
   		 양 끝부터 탐색을 시작해 가장 무거운 사람과 가장 가벼운 사람의 무게 합이
   		 limit를 넘지 않으면 정답 카운트 +하고, 인덱스를 한칸씩 안쪽으로 이동
   		 limit를 넘으면 정답 카운트 +, max_index만 이동
   		 모든 원소를 탐색할 때 까지 반복
   		 */
   while(max_index >= min_index) {
       if(people[max_index] + people[min_index] <= limit) {
           answer++;
           max_index--;
           min_index++;
       }
       else {
           answer++;
           max_index--;
       }
   }
   
   return answer;
   ```

2. 좋아요 많은 풀이 

   ```java
   Arrays.sort(people);
   int i = 0, j = people.length - 1;
   for (; i < j; --j) {
       if (people[i] + people[j] <= limit)
           ++i;
   }
   return people.length - i;
   ```

   


---

## 사용된 개념

1. 
