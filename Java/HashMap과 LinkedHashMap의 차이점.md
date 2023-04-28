# HashMap과 LinkedHashMap 차이점

---

>[참고 사이트1](https://fruitdev.tistory.com/141)

<img src="./images/linkedhashmap1.jfif">

## HashMap

- 데이터의 **삽입 순서**를 **보장하지 않는다.**

---

<img src="./images/linkedhashmap2.jfif">

## LinkedHashMap

- Map의 특징에서 데이터의 **삽입 순서**를 **보장한다.**
- LinkedHashMap은 **HashMap을 확장**한다. 
  - HashMap의 모든 기능을 사용하되, Doubly-Linked List(이중 연결 리스트**)**를 내부에 유지함으로써 입력된 자료의 순서를 보관한다. 
  - 즉, **LinkedHashMap은 기존 HashMap 자료구조에서 LinkedList 자료구조를 섞어놓은 자료구조**이다.


### 등장 이유

- HashMap의 특징인, 저장된 값들을 순회하고자 할때 값을 넣은 순서가 아닌 무작위로 출력되게 되는 것을 보완하기 위해 등장하였다.
  - 따라서,LinkedHashMap은 FIFO (First In First Out) 구조로 데이터를 저장한다.


### HashMap과 비교 

- HashMap과 LinkedHashMap의 최종 속도 차이는 큰차이가 없지만, LinkedHashMap은 순서를 유지하기 때문에 메모리 사용량이 HashMap보다 높다.

---

## 정리

- HashMap은 **key가 set형태**로 관리되어 **순서를 보장하지 않는다**. 
- LinkedHashMap은 **key가 list형식**으로 관리되러 **순서를 보장**한다.