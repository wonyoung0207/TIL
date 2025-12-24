# Kafka 동작 이해 

---

>

## patition 분할 

1. 1개의 Topic 은 여러개의 patition 로 저장될 수 있다. 
2. 이때 각각의 patition 은 Broker 서버에 나뉘어 저장된다. 
3. 즉, 1개의 Topic 에 데이터가 들어올 때 patition 에 나눠 저장이 되는데 patition 은 여러 Broker 에 나뉘어저 저장된다. 

## 순서보장

1. kafka 는 순서보장을 patition 단위로만 보장한다. 
2. 즉, 데이터 순서를 보장받기 위해선 patition 을 1개만사용해야한다. (patition 늘리면 병렬처리 되서 성능 좋아지지만 순서 보장 x )

## Consumer Grop 

1. Consumer Group 은 여러개의 Consumer 로 이루어 질 수 있다. 

2. 하나의 Partition은 Consumer Group 안에서 동시에 하나의 Consumer에게만 할당된다 (Consumer 수 = Patition 수)

   ```
   Partition 0
    ├─ Group G1 → Consumer 1
    ├─ Group G2 → Consumer 1
    └─ Group G3 → Consumer 1
   ```

## 데이터 소비

1. patition 에 들어온 데이터의 소비는 Consumer Group의 갯수만큼 소비된다. 

2. 1개의 Topic 을 Sub 하고 있는 Consumer Group의 수가 3명이라면, Patition 에 들어온 데이터는 3번 소비된다. 

   1. 데이터는 **1번만 저장**
   2. 각 Group은
      - **자기 offset** 기준으로
      - **독립적으로 fetch**
   3. Broker는 같은 로그를 **3번 읽어줌**

3. 예시

   - **Consumer Group 이 3개인 경우 A, B는 데이터 정상 소비, C는 데이터 비정상 소비한 경우 데이터 처리 예시** 

     ```
     Topic: T
     Partition: 1 (순서 보장 위해)
     
     Consumer Groups:
      - Group A
      - Group B
      - Group C
     ```

   - Group A, B

     1. 정상 동작
     2. **Group C 문제와 무관**
     3. 영향 X

   - Group C

     - offset이 안 움직임
     - **Lag 증가**

## 데이터 유지 

- Kafka 에서 Consumer Group 이 데이터 소비를 했다고 해서 데이터 지워지지 않는다. 
- 설정한 정책에 따라 Data 가 유지되고 삭제된다. 
  - **데이터 소비 유무는 Offset 의 수와 Lag 수를 보면 알 수 있다.** 