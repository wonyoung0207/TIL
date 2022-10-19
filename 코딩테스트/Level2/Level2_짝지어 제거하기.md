

# < Level 2 > 

# 짝지어 제거하기 

> 

---

## 문제설명 

- 짝지어 제거하기는, 알파벳 소문자로 이루어진 문자열을 가지고 시작합니다. 먼저 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾습니다. 그다음, 그 둘을 제거한 뒤, 앞뒤로 문자열을 이어 붙입니다. 이 과정을 반복해서 문자열을 모두 제거한다면 짝지어 제거하기가 종료됩니다. 문자열 S가 주어졌을 때, 짝지어 제거하기를 성공적으로 수행할 수 있는지 반환하는 함수를 완성해 주세요. 성공적으로 수행할 수 있으면 1을, 아닐 경우 0을 리턴해주면 됩니다.

  예를 들어, 문자열 S = `baabaa` 라면

  b *aa* baa → *bb* aa → *aa* →

  의 순서로 문자열을 모두 제거할 수 있으므로 1을 반환합니다.


## 제한사항 

- 문자열의 길이 : 1,000,000이하의 자연수
- 문자열은 모두 소문자로 이루어져 있습니다.

## 입출력 예

| s      | result |
| ------ | ------ |
| baabaa | 1      |
| cdcd   | 0      |

##### 입출력 예 설명

입출력 예 #1
위의 예시와 같습니다.
입출력 예 #2
문자열이 남아있지만 짝지어 제거할 수 있는 문자열이 더 이상 존재하지 않기 때문에 0을 반환합니다.

## 풀이 

1. 내가 푼 풀이 ( 효율성 테스트 통과 못함 )

   ```java
   public int solution(String s)
   {
       int answer = -1;
       char[] ch = s.toCharArray();
       ArrayList<Character> list = new ArrayList<Character>();
   
       for(char c : ch){
           list.add(c);
   
       }
   
       for(int i=1; i< list.size(); i++){
           if(list.get(i-1) == list.get(i)){// 이전 값과 같다면 지우기 
               //System.out.println("remove : " + list.get(i) + ", " + list.get(i-1)); 
               list.remove(i);
               list.remove(i-1);
               //System.out.println("ArrayList : " + list.toString()); 
               i = 0;
   
           }
           if(list.isEmpty()){
               return 1;
           }
       }
       //System.out.println("ArrayList : " + list.toString()); 
       return 0;
   }
   ```

2. 내가 푼 풀이 ( Stack 이용 )

   ```java
   public int solution(String s)
   {
       int answer = -1;
       Stack<Character> stk = new Stack<Character>();
       char c;
   
       for(int i=0; i < s.length(); i++){
           c = s.charAt(i);
           if(!stk.isEmpty() && stk.peek() == c){
               stk.pop();
           }else{
               stk.push(c);
           }
       }
   
       answer = (stk.size() == 0) ? 1 : 0;
   
       return answer;
   }
   ```

   

---

## 사용된 개념

1. Stack
   - 후입선출의 특징을 가진다. 따라서 이전에 들어간 값과 현재값을 비교하여 같다면 이전값을 pop 해주고 , 다른 경우라면 push 해준다. 
