

# < Level 2 > 

# 영어 끝말잇기  

> 사용 개념 간소화 

---

## 문제설명 

- 1부터 n까지 번호가 붙어있는 n명의 사람이 영어 끝말잇기를 하고 있습니다. 영어 끝말잇기는 다음과 같은 규칙으로 진행됩니다.

  1. 1번부터 번호 순서대로 한 사람씩 차례대로 단어를 말합니다.
  2. 마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작합니다.
  3. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 합니다.
  4. 이전에 등장했던 단어는 사용할 수 없습니다.
  5. 한 글자인 단어는 인정되지 않습니다.

  다음은 3명이 끝말잇기를 하는 상황을 나타냅니다.

  tank → kick → know → wheel → land → dream → mother → robot → tank

  위 끝말잇기는 다음과 같이 진행됩니다.

  - 1번 사람이 자신의 첫 번째 차례에 tank를 말합니다.
  - 2번 사람이 자신의 첫 번째 차례에 kick을 말합니다.
  - 3번 사람이 자신의 첫 번째 차례에 know를 말합니다.
  - 1번 사람이 자신의 두 번째 차례에 wheel을 말합니다.
  - (계속 진행)

  끝말잇기를 계속 진행해 나가다 보면, 3번 사람이 자신의 세 번째 차례에 말한 tank 라는 단어는 이전에 등장했던 단어이므로 탈락하게 됩니다.

  사람의 수 n과 사람들이 순서대로 말한 단어 words 가 매개변수로 주어질 때, 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지를 구해서 return 하도록 solution 함수를 완성해주세요.


## 제한사항 

- 끝말잇기에 참여하는 사람의 수 n은 2 이상 10 이하의 자연수입니다.
- words는 끝말잇기에 사용한 단어들이 순서대로 들어있는 배열이며, 길이는 n 이상 100 이하입니다.
- 단어의 길이는 2 이상 50 이하입니다.
- 모든 단어는 알파벳 소문자로만 이루어져 있습니다.
- 끝말잇기에 사용되는 단어의 뜻(의미)은 신경 쓰지 않으셔도 됩니다.
- 정답은 [ 번호, 차례 ] 형태로 return 해주세요.
- 만약 주어진 단어들로 탈락자가 생기지 않는다면, [0, 0]을 return 해주세요.

## 입출력 예

| n    | words                                                        | result |
| ---- | ------------------------------------------------------------ | ------ |
| 3    | ["tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"] | [3,3]  |
| 5    | ["hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"] | [0,0]  |
| 2    | ["hello", "one", "even", "never", "now", "world", "draw"]    | [1,3]  |

3명의 사람이 끝말잇기에 참여하고 있습니다.

- 1번 사람 : tank, wheel, mother
- 2번 사람 : kick, land, robot
- 3번 사람 : know, dream, `tank`

와 같은 순서로 말을 하게 되며, 3번 사람이 자신의 세 번째 차례에 말한 `tank`라는 단어가 1번 사람이 자신의 첫 번째 차례에 말한 `tank`와 같으므로 3번 사람이 자신의 세 번째 차례로 말을 할 때 처음 탈락자가 나오게 됩니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   public int[] solution(int n, String[] words) {
       int[] answer = {0,0};
       int words_len = 0;
       int cnt= 1;// i값을 넣을 곳 
       boolean flag = true;// 걸린사람 존재를 판별 -> true면 안걸린거
       ArrayList<String> list = new ArrayList<>();
       list.add(words[0]);
   
       if( words.length % n != 0){// 나누어 떨어지지 않으면 무조건 끝말잇기 걸린사람 존재 
           flag = false;
       }
   
       for(int i=1; i < words.length; i++){
           words_len = words[i-1].length();
           cnt++;
           if(words[i-1].charAt(words_len-1) != words[i].charAt(0) || list.indexOf(words[i]) > -1){
               System.out.println("찾음" + cnt);
               flag = false;
               answer[0] = (i % n) + 1; // 나머지가 0이면 마지막사람 
               answer[1] = (i / n) + 1; // 몇번째 돌았을 떄 걸렸는지 체크 
               break;
           }
           list.add(words[i]);
       }
       System.out.println(list.toString()); 
       if(flag){// flag true인경우 실행 => 다 돌았을 때 까지 안걸린 경우 
           answer[0] = 0;
           answer[1] = 0;
           return answer;
       }
       return answer;
   }
   ```

2. 더 좋은 풀이 

   ```java
   public int[] solution(int n, String[] words) {
       int[] answer = {0, 0};
   
       //단어 중복 체크를 위한 맵
       Map<String, Integer> map = new HashMap<>();
   
       for(int i = 0; i < words.length; i++) {
           if(i != 0) {
               String s1 = words[i - 1]; // 이전 단어
               String s2 = words[i]; // 현재 단어
   
               char last = s1.charAt(s1.length() - 1); // 이전 단어의 마지막 철자
               char first = s2.charAt(0); // 현재 단어의 처음 철자
   
               if(map.containsKey(s2) || last != first) { // 단어가 중복이거나 철자가 이어지지 않는 경우
                   answer[0] = (i % n) + 1; // 번호
                   answer[1] = (i / n) + 1; // 차례
   
                   return answer;
               }
           }
   
           map.put(words[i], 1); // 사용한 단어 맵에 저장
       }
   
       return answer;
   }
   ```


---

## 사용된 개념

### 문자열 찾기 

1. 문자열에서 문자열 찾기 
   - str.contain(); => 문자열이 있으면 true, 없으면 false 를 리턴한다. 
   - str.indexOf(); => 있으면 index를, 없으면 -1 을 리턴한다. 
2. 배열에서 문자열 찾기
   - Arrays.binarySearch(arr, "문자열");
   - Arrays.asList(data).contains("문자열")
3. 리스트에서 문자열 찾기
   - list.indexOf("apple");
4. Map에서 문자열 찾기 
   - map.containsKey(s2)
