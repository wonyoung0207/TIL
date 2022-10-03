

# < Level 1 > 

# 같은 숫자는 싫어   

> ArrayList 이용 

---

## 문제설명 

- 배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,

  - arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
  - arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.

- 배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.

## 제한사항 

- 배열 arr의 크기 : 1,000,000 이하의 자연수
- 배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수

## 입출력 예

| arr             | answer    |
| --------------- | --------- |
| [1,1,3,3,0,1,1] | [1,3,0,1] |
| [4,4,4,3,3]     | [4,3]     |

## 풀이 

1. 내가 푼 풀이 

   ```java
   import java.util.*;
   
   public class Solution {
       public int[] solution(int []arr) {
           int []answer = {};
   	    int[] arr2 = new int[arr.length];
   	    int a = arr[0]; // 비교하기 위해서 필요  
   	    int cnt = 1;	    
   
   	    for(int i=0; i<arr.length; i++){
   	    	if(i == 0) {
   	    		arr2[0] = arr[0];
   	    	}
   	    	
   	        if(arr[i] != a ) {// 다음수가 같지 않다면 arr2에 입력됨 
   	        	a = arr[i];
   	        	arr2[cnt] = a;
   	        	
   	        	cnt++;
   	        }
   	    }
   	    
   	    answer = Arrays.copyOfRange(arr2, 0, cnt);
   	    
           return answer;
       }
   }
   ```

2. 공감 많이 받은 풀이 

   ```java
   public class Solution {
       public int[] solution(int []arr) {
           ArrayList<Integer> tempList = new ArrayList<Integer>();
           int preNum = 10;
           for(int num : arr) {
               if(preNum != num)
                   tempList.add(num);
               preNum = num;
           }       
           int[] answer = new int[tempList.size()];
           for(int i=0; i<answer.length; i++) {
               answer[i] = tempList.get(i).intValue();
           }
           return answer;
       }
   }
   ```

3. 가장 많이 푼 풀이 

   ```java
   List<Integer> list = new ArrayList<Integer>();
   list.add(arr[0]);
   
   for (int i = 1; i < arr.length; i++) {
   
       if (arr[i] != arr[i - 1])
           list.add(arr[i]);
   }
   
   int[] answer = new int[list.size()];
   
   for (int i = 0; i < list.size(); i++)
       answer[i] = list.get(i);
   
   return answer;
   ```

   


---

## 사용된 개념

1. Arrays.toString();

   - 배열을 출력하는 함수 

2. Arrays.copyOfRange(배열, 시작점, 길이 )

   - 배열을 원하는 길이만큼 잘라서 복사 

3. ArrayList

   |          |                         Array                         |                          ArrayList                           |
   | :------: | :---------------------------------------------------: | :----------------------------------------------------------: |
   |  사이즈  |         초기화시 고정 int[] arr = new int[3];         | 초기화시 사이즈를 표시하지 않음.  크기가 가변적임 ArrayList<Integer> arrList = new ArrayList<>(); |
   |   속도   | 초기화시 메모리에 할당되어  ArrayList보다 속도가 빠름 | 데이터 추가 삭제시 메모리를 재할당하기 때문에 속도가 Array보다 느림 |
   | 크기변경 |                   사이즈 변경 불가                    |             추가, 삭제 가능 ( add(), remove() )              |
   |  다차원  |        int[][][] multiArr = new int[3][3][3];         |                            불가능                            |

   - 일반적인 배열과 같은 순차리스트이며 인덱스로 내부의 객체를 관리한다는점 등이 유사하지만 **한번 생성되면 크기가 변하지 않는 배열과는 달리 ArrayList는 객체들이 추가되어 저장 용량을 초과한다면 자동으로 부족한 크기만큼 저장 용량이 늘어난다는 특징**을 가지고 있다
