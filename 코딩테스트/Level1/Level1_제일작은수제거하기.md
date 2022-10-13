

# < Level 1 > 

# 제일 작은 수 제거하기 

> 배열의 sort, copy 사용 

---

## 문제설명 

- 정수를 저장한 배열, arr 에서 가장 작은 수를 제거한 배열을 리턴하는 함수, solution을 완성해주세요. 단, 리턴하려는 배열이 빈 배열인 경우엔 배열에 -1을 채워 리턴하세요. 예를들어 arr이 [4,3,2,1]인 경우는 [4,3,2]를 리턴 하고, [10]면 [-1]을 리턴 합니다.


## 제한사항 

- arr은 길이 1 이상인 배열입니다.
- 인덱스 i, j에 대해 i ≠ j이면 arr[i] ≠ arr[j] 입니다.

## 입출력 예

| arr       | return  |
| --------- | ------- |
| [4,3,2,1] | [4,3,2] |
| [10]      | [-1]    |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public int[] solution(int[] arr) {
       int[] answer = {};
       ArrayList<Integer> list = new ArrayList<Integer>();
     	answer = new int[arr.length-1];
       int[] sortarr = new int[arr.length];
       int min = 0;
       int cnt = 0;
   
       if(arr.length <= 1){
           answer = new int[1];
           answer[0] = -1;
           return answer;
       }
     
       sortarr = arr.clone();
       Arrays.sort(sortarr);
       min = sortarr[0];
       System.out.println(min);
   
       for(int i=0; i<arr.length; i++){
           if(arr[i] == min){
               continue;
           }
           answer[cnt] = arr[i];
           cnt++;
       }
   
       return answer;
   }
   ```

2. 짧은 풀이 

   ```java
   public int[] solution(int[] arr) {
         if (arr.length <= 1) return new int[]{ -1 };
         int min = Arrays.stream(arr).min().getAsInt();
         return Arrays.stream(arr).filter(i -> i != min).toArray();
     }
   ```

3. ArrayList 이용풀이 

   ```java
   public int[] solution(int[] arr) {
       if (arr.length == 1) {
           arr[0] = -1;
           return arr;
       } else {
           ArrayList<Integer> arrayList = new ArrayList<Integer>();
           for (int a : arr) {
               arrayList.add(a);
           }
           Integer minimum = Collections.min(arrayList);
           arrayList.remove(minimum);
           int[] resultArray = new int[arr.length - 1];
           for (int i = 0; i < arrayList.size(); ++i) {
               resultArray[i] = arrayList.get(i);
           }
           return resultArray;
       }
   }
   ```

   


---

## 사용된 개념

1. 배열 정렬 , 복사 
   - 정렬
     - Arrays.sort(배열이름)
   - 복사
     - Arrays.copyOf(배열);
     - 배열이름.clone();
