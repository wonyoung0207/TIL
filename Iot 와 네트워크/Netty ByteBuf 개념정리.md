# Netty ByteBuf 개념정리.md

>

---

## ByteBuf 란? 

1. Netty의 핵심 데이터 구조로, 네트워크 데이터를 효율적으로 읽고 쓰기 위해 설계된 **바이트 버퍼(buffer)**이다. 
2. Java의 기존 `ByteBuffer`보다 더 **성능 최적화**와 **사용성 향상**에 초점을 맞춘 클래스이다. 


## ByteBuf 주요 특징

1. **직접 메모리 관리**
   1. Netty는 직접 메모리(direct memory)를 사용하고 **참조 카운팅**(reference counting)을 통해 메모리를 관리합니다.
      1. 가비지 컬렉터(GC)에 부담을 덜 준다.
      2. 즉, ByteBuf는 GC 를 이용하지 않고 사용자가 직접 버퍼를 관리함으로써 GC의 부담을 최소화해 다른곳에 메모리를 사용할 수 있도록 한다. 
2. 읽기/쓰기 인덱스 분리
   1. 데이터를 읽거나 쓸 때 인덱스를 관리하기 쉽습니다.
3. 슬라이스와 중첩 버퍼
   1. 버퍼를 부분적으로 공유할 수 있어 메모리 효율성이 높습니다.
4. **Zero Copy**
   1. 복사 없이 데이터 처리가 가능하여 성능 최적화.

## ByteBuf vs ByteBuffer

1. ByteBuffer 
   1. 자바에서 사용하는  버퍼 
2. ByteBuf 
   1. Netty에서 사용하는 버퍼 

| 특징             | **ByteBuf**        | **ByteBuffer** |
| ---------------- | ------------------ | -------------- |
| 메모리 관리      | 자동 (참조 카운팅) | 수동           |
| 읽기/쓰기 인덱스 | 분리               | 통합           |
| 성능             | 더 빠름            | 느림           |
| 메모리 풀링      | 지원               | 미지원         |
| Zero Copy        | 지원               | 제한적 지원    |

## 데이터 읽기 / 쓰기 관리 

1. Netty의 ByteBuf는 데이터 읽기와 쓰기를 효율적으로 관리하기 위해 두 개의 인덱스(readerIndex, writerIndex)를 사용한다. 
   1. **`writeByte(int)`로 데이터를 쓰고 `readByte()`로 데이터를 읽는다.** 
2. **readerIndex:** 
   1. 데이터를 **읽을 때 현재 위치**를 나타냅니다. 
   2. 데이터를 읽을 때마다 자동으로 증가합니다.
3. **writerIndex:** 
   1. 데이터를 **쓸 때 현재 위치**를 나타냅니다. 
   2. 데이터를 쓸 때마다 자동으로 증가합니다.
4. **release**
   1. 메모리 해제
   2. 메모리 누수를 방지할 수 있다. 
5. **읽기 범위를 넘어가거나 쓰기 범위를 초과하면 `IndexOutOfBoundsException`이 발생**
   1. 즉, 읽기와 쓰기가 각각의 인덱스를 관리하여 데이터 처리가 충돌하지 않지만 범위 초과하면 안됨 

```java
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufIndexExample {
    public static void main(String[] args) {
        // 10바이트 크기의 ByteBuf 생성
        ByteBuf buffer = Unpooled.buffer(10); // GC 가 관리 
        ByteBuf buffer = Unpooled.buffer(); // 매개변수 생략가능

        // 초기 상태 출력
        System.out.println("Initial readerIndex: " + buffer.readerIndex()); // 0
        System.out.println("Initial writerIndex: " + buffer.writerIndex()); // 0

        // 데이터 쓰기
        buffer.writeByte(10); // 1 바이트 -> 255 숫자 까지 1바이트로 표기 가능 
        buffer.writeByte(20); // 1 바이트

        System.out.println("\nAfter writing 2 bytes:");
        System.out.println("readerIndex: " + buffer.readerIndex()); // 0
        System.out.println("writerIndex: " + buffer.writerIndex()); // 2

        // 데이터 읽기
        byte firstByte = buffer.readByte();
        System.out.println("\nRead first byte: " + firstByte); // 10
        System.out.println("readerIndex after read: " + buffer.readerIndex()); // 1
        System.out.println("writerIndex after read: " + buffer.writerIndex()); // 2

        // 추가 데이터 쓰기
        buffer.writeByte(30);
        System.out.println("\nAfter writing another byte:");
        System.out.println("readerIndex: " + buffer.readerIndex()); // 1
        System.out.println("writerIndex: " + buffer.writerIndex()); // 3
        
        // 읽을 수 있는 데이터 여부를 확인
        while (buffer.isReadable()) {
            System.out.print(buffer.readByte() + " ");
        }
        
        // 버퍼 해제 ( 누수 방지 )
        buffer.release();
    }
}

```

## `writeByte(10)` vs `writeInt(10)`의 차이

1. writeByte()
   1. **1바이트(8비트)** 버퍼 공간 사용
2. writeInt()
   1. **4바이트(32비트)** 버퍼 공간 사용

## ByteBuf 의 패턴 

###### 1. Heap Buffer 패턴

1. `Heap Buffer` 는 **JVM의 힙 공간에 데이터를 저장**합니다. 

2. 이 패턴은 데이터를 힙**(JVM내부 힙)**에 저장하므로 할당과 해제 속도가 빠르다. 

3. 하지만 Netty같은 네트워크 작업에서는 직접 메모리**(JVM 외부 힙)**를 사용하는 것이 더 효율적이다. 

   ```java
   ByteBuf heapBuf = Unpooled.buffer(256);
   heapBuf.writeBytes(new byte[]{1, 2, 3, 4});
   ```

###### Direct Buffer 패턴

1. `Direct Buffer`는 **JVM이 메모리를 할당**하고, **네트워크 작업에서 직접 사용하는 방식**입니다. 

2. 이 패턴은 힙 버퍼보다 할당과 해제 비용이 더 크지만, 네트워크 I/O 작업에서 더 빠르고 효율적입니다.

   ```java
   ByteBuf directBuf = Unpooled.directBuffer(256);
   directBuf.writeBytes(new byte[]{1, 2, 3, 4});
   ```

###### 3. 복합 버퍼 (Composite Buffer) 패턴

1. 복합 버퍼는 **여러 개의 버퍼를 하나의 버퍼처럼 다루는 패턴**입니다. 

2. 이를 통해 여러 작은 버퍼를 하나로 병합하여 메모리 복사 없이 데이터를 처리할 수 있습니다.

3. 예를 들어, HTTP 메시지의 헤더와 본문을 별도로 처리할 수 있습니다.

   ```java
   ByteBuf headerBuf = Unpooled.buffer(128);
   ByteBuf bodyBuf = Unpooled.buffer(256);
   
   CompositeByteBuf compositeBuf = Unpooled.compositeBuffer();
   compositeBuf.addComponents(true, headerBuf, bodyBuf);
   ```

##### 4. 풀링 (Pooling) 패턴

1. 메모리를 해제하지 않고 **풀(Pool)에 반환**하여 재사용

   1. 풀링된 객체가 다시 필요할 때 **retain()**을 호출하여 참조 카운트를 증가
   2. **release()** : 풀로 반환할 시점에 호출해 풀로 다시 반환 

2. 이를 통해 메모리 사용을 줄이고 GC 부담을 줄일 수 있습니다. 

   ```java
   ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(10);
   pooledBuf.writeBytes(new byte[]{1, 2, 3, 4});
   pooledBuf.release(); // 버퍼 반환
   ```

##### 5. 참조 카운팅 (Reference Counting)

1. `ByteBuf`는 참조 카운팅을 사용하여 버퍼의 사용을 추적합니다.

   1.  **JVM의 가비지 컬렉터(GC)**에 의존하지 않고 직접 메모리를 관리하는 방식

   2. GC에 부담을 줄일 수 있다 (고성능,대용량 데이터 처리에 좋음)

2. 카운팅 방법 

   1. **`retain()` 메서드**를 사용하여 버퍼의 **참조 카운트를 증가**

   2. 참**`release()`** 메서드를 사용하여 **참조 카운트를 감소** 

   3. 참조 카운트가 0이 되면 **버퍼가 풀로 반환되거나 삭제**


   ```java
   import io.netty.buffer.ByteBuf;
   import io.netty.buffer.Unpooled;
   
   public class ReferenceCountingExample {
       public static void main(String[] args) {
           // ByteBuf 생성
           ByteBuf buffer = Unpooled.buffer(10);
           
           // 초기 참조 카운트 출력 (기본 값은 1)
           System.out.println("Initial ref count: " + buffer.refCnt());
           
           // 참조 카운트 증가
           buffer.retain();
           System.out.println("After retain: " + buffer.refCnt());
           
           // 참조 카운트 감소
           buffer.release();
           System.out.println("After first release: " + buffer.refCnt());
           
           // 최종 참조 카운트 감소 (0이 되면 메모리 해제)
           buffer.release();
           System.out.println("After second release: " + buffer.refCnt());
       }
   }
   ```
## 가장 효율적인 버퍼 관리  wrappedBuffer()

1. `Zero-Copy 패턴 & Adapter 패턴` 을  사용한다. 
   1. `Zero-Copy 패턴` 
      1. 사용중인 메모리 버퍼를 이용해 copy가 일어나지 않는다. 
   2. `Adapter 패턴` 
      1. 기존 데이터(`byte[]`)는 Netty가 직접 처리할 수 없음
      2. `wrappedBuffer()`는 **byte[]를 ByteBuf로 감싸 Netty 호환 데이터로 변환 ** 하여 `ByteBuf` 기반의 API를 사용할 수 있게 한다. 
2. `Unpooled.wrappedBuffer()`
   1. **기존 데이터 배열을 래핑(wrap)**하여 새로운 `ByteBuf` 객체를 생성하는 유틸리티이다. 
   2. **데이터 복사 없이** 기존 데이터의 메모리 공간을 그대로 활용하므로 **메모리 효율성이 뛰어나다**.
   3. 즉, 매개변수로 받은 변수의 메모리를 그대로 재사용해 처리 속도가 최적화된다. 
3. 주의사항
   1. 기존 배열(`data`)이 변경되면 `ByteBuf` 내용도 변경
   2. **메모리 해제인 release()가 필요 없음**

```java
@Override
protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> list) throws Exception {
    if(msg instanceof MessageLite) {
            byteBuf = Unpooled.wrappedBuffer(((MessageLite)msg).toByteArray());
            list.add(new BinaryWebSocketFrame(byteBuf));
        }
}
```

| 메서드            | 메모리 복사 | 특징             |
| ----------------- | ----------- | ---------------- |
| `buffer()`        | 복사 O      | 새로운 버퍼 할당 |
| `wrappedBuffer()` | 복사 X      | 기존 배열 참조   |