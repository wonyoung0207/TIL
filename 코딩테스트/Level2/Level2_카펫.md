

# < Level 2 > 

# 카펫

> ArrayList, 

---

## 문제설명 

- Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

- Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

  Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.


## 제한사항 

- 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
- 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
- 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.

## 입출력 예

| brown | yellow | return |
| ----- | ------ | ------ |
| 10    | 2      | [4, 3] |
| 8     | 1      | [3, 3] |
| 24    | 24     | [8, 6] |

## 풀이 

1. 내가 푼 풀이 ( 실패 ) => 3가지 테스트 케이스에서 실패나옴 

   ```java
   
   public int[] solution(int brown, int yellow) {
       int[] answer = new int[2];
       ArrayList<Integer> list = new ArrayList<Integer>();
       //공약수 구하기 
       int sum = brown + yellow;
       int temp = 0;
   
       for(int i = 1; i <= Math.sqrt(sum); i++){//Math.sqrt(sum)
           if(sum % i == 0){
               list.add(i);
               System.out.println(i);
           }
       }
   
       // System.out.println( "size : " + list.size());
       // System.out.println( "sum/list : " + (sum / list.get(list.size()-1)));
       answer[0] = list.get(list.size()-1);
       answer[1] = sum / list.get(list.size()-1);
   
       //System.out.println(answer[0] + " + " + answer[1]);
       //Arrays.sort(answer,Collections.reverseOrder()); // Collections.reverseOrder() 사용하려면 배열의 타입이 기본타입이라면 래퍼클래스로 만들어야한다. 
       if(answer[0] < answer[1]){
           temp = answer[0];
           answer[0] = answer[1];
           answer[1] = temp;
       }
   
   
       return answer;
   }
   ```

2. 다른 사람 풀이 

   ```java
   /*
       가로 a, 세로 b일 때
       테두리의 타일 개수: 2 * a + (b-2) * 2 = 2 *a + 2 * b - 4 = brown
       가운데 노란색의 타일 개수 = a * b - brown = yellow
       i * b - (2*i + 2*b -4) = yellow
       (i - 2) * b = yellow + 2*i -4
   
       정리하면, 
       a * b - brown = yellow
       a * b = brown + yellow
   */
       for(int i=1; i<=5000; i++){//가로의 길이
           for(int j=1; j<=i; j++){
               if(2 * i + 2 * j - 4 == brown){
                   if(i * j - brown == yellow){
                       answer.push_back(i);
                       answer.push_back(j);
                       return answer;
                   }
               }
           }
       }
   ```

3. 공약수 구해서 푼 풀이 ( 나랑 가장 비슷 )

   ```java
   int n = brown + red;
   List<Integer> list = new ArrayList<Integer>();
   
   for(int i = 2; i <= Math.sqrt(n); i++){
       if(n % i == 0){
           list.add(i);
           int quotient = n/i;
           if(i != quotient) list.add(quotient); 
       }
   }
   
   Collections.sort(list);
   
   int col = 0;
   int row = 0;
   
   for(int i : list)
       if((col = i) >= (row = (n/i))){
           if((col + col + row - 2 + row - 2) == brown) break;
       }
   
   return new int[]{col, row};
   ```

   



---

## 사용된 개념

1. Arrays.sort(arr,Collections.reverseOrder());
