# 5일차

---

> 조건문과 반복문에 대해 학습한다. 
>
> 2중 반복문을 학습한다. 
>
> 변수와 객체의 차이를 학습한다. 
>
> 배열의 사용법과 2차원 배열을 학습한다. 



## 1. 2중for문
   +  구구단 만들기 
     + printf( "%d x %d  = %d \n", i , j , i*j);
     + \t :  '탭'을 뜻한다. 
     + \0 : 빈칸을 생성
     + \r : 줄의 맨 처음으로 

## 2. printf( ) 에서 % 출력하는 방법
   + printf( "이자율 %d  %% 입니다." , num)
     + %% 로 쓰면 %가 출력된다. 

## 3. String (객체)
   + 한번 만들어진 String 객체는 변경할 수 없다. 
   + 따라서 새로 만들기 위해서는 new 를 사용해야 한다. 
   + String 을 그냥 생성하면 **String pool** 에 생성되어 같은 값이 들어오면 주소를 공유하게 된다. 
   + str.indexOf("a") : a의 index 위치를 알려줌
   + str.substring(1 , 5) : 1부터 5 전까지 내용을 자름 
   + str.trim() : 공백을 지워줌 

## 4. Array (배열)
   + int [] arr     ==    int arr[] 

   + 배열은 null 로 초기화 한다. 

   + **Arrays.toString(arr1)** => 배열의 내용을 출력할 수 있다.

   + 2차원 배열

     + 행이 먼저 만들어지고, 열이 만들어진다. 

     + 초기화 하는 방법 

       1. ``` java
          int[][] arr = {{100,98,80,70},{98,91,83,72},{89,96,82,75}};
          ```

       2. ```java
          int ar[][] = new int[3][];
          ar [0] = new int[4];
          ar [1] = new int[4];
          ar [2] = new int[4];
          
          int ar1[] = {100,99,80,70};
          int ar2[] = {98,91,83,72};
          int ar3[] = {89,96,82,75};
          ```