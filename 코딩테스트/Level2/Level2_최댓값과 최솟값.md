

# < Level 2 > 

# 최대값과 최솟값

> split , parseInt, Collections.sort

---

## 문제설명 

- 문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다. str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
  예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.


## 제한사항 

- s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.

## 입출력 예

| s             | return  |
| ------------- | :-----: |
| "1 2 3 4"     |  "1 4"  |
| "-1 -2 -3 -4" | "-4 -1" |
| "-1 -1"       | "-1 -1" |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public String solution(String s) {
       String answer = "";
       ArrayList<Integer> arr = new ArrayList<Integer>();
       String[] sarr = s.split(" ");
       int cnt = 0;
   
   
       for(String a : sarr){
           //System.out.println(a + ",");
           arr.add(Integer.parseInt(a));
           cnt++;
       }
   
       Collections.sort(arr);
       answer = arr.get(0) + " " + arr.get(cnt-1);// list.size() 사용해도 됨 
   
   
       return answer;
   }
   ```

2. 비슷한 풀이 

   ```java
   public String getMinMaxString(String str) {
       String[] arr = str.split(" ");
       int[] arrInt = new int[arr.length];
   
       for (int i = 0; i < arrInt.length; i++) {
           arrInt[i] = Integer.parseInt(arr[i]);
       }
   
       Arrays.sort(arrInt);
       String result = arrInt[0] + " " + arrInt[arrInt.length - 1];
   
       return result;
   }
   ```

3. 함수 사용 안한 풀이 

   ```java
   public String getMinMaxString(String str) {
       String[] tmp = str.split(" ");
       int min, max, n;
       min = max = Integer.parseInt(tmp[0]);
       for (int i = 1; i < tmp.length; i++) {
           n = Integer.parseInt(tmp[i]);
           if(min > n) min = n;
           if(max < n) max = n;
       }
   
       return min + " " + max;
   
   }
   ```

   


---

## 사용된 개념

1. 문자열 Split하기 
   - split() 함수를 사용하면 String 배열 형태로 값을 리턴해 준다. 
   - 따라서 String[] arr = s.split(" ") 의 형태로 받아야한다. 
2. Math.minI()   Math.max() 함수
   - 해당 함수들은 숫자를 비교할 수 있는 함수이다. (배열 한번에 불가 )
   - 따라서 Math.min( 숫자1, 숫자2) 의 형식으로 비교해야한다. 
3. ArrayList 의 길이 
   - length 함수가 아닌 size() 함수로 길이를 구할 수 있다. 
