# 파일 입출력
---
## 1. 파일 입력

```java
Stream<String> stream = Files.lines(Paths.get(loadFilePath + loadFileName));
			stream.forEach(line -> { // 파일 한줄씩 읽어오기 . 
				ff.oneLineAnalyze(line); // 조건에 맞게 한줄씩 필터링하는 함수 
			});  

```

## 2. 파일 출력

```java
// 1. Map에 데이터 저장 후 데이터를 한번에 출력 
// 파일이 있는지 체크 
try {
    FileWriter fw = new FileWriter( projectFilePath + saveFilePath + "ResultFile.log", true);
    BufferedWriter  bw = new BufferedWriter (fw);

    for (Map.Entry<Integer, FileContent> entry : sortedMap.entrySet()) {
        String line = entry.getValue().toString() + "\r\n";
        bw.write(line);
    }
    bw.flush();
    bw.close();

} catch (IOException e1) {
    e1.printStackTrace();
}


// 2. 그때그때 정보를 파일로 출력 
// 파일이 있는지 체크 
try {
    FileWriter fw = new FileWriter( projectFilePath + saveFilePath + "ResultFile.log", true);
    BufferedWriter  bw = new BufferedWriter (fw);

//			 다른 방법 파일 출력. -> 더 느림 
//			Files.write(Paths.get(projectFilePath + saveFilePath + "ResultFile.log"),(fc.toString() + "\r\n").getBytes() , StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    bw.write(fc.toString() + "\r\n");
    bw.flush();
    bw.close();

    // 파일 쓰고 필요 없는 데이터 삭제 
    map.remove(pid);

} catch (IOException e1) {
    e1.printStackTrace();
}
```

### Stream 이용 LinkedHashMap

```java
LinkedHashMap<Integer, FileContent> sortedMap = resultMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.comparing( FileContent ::getStartTime)))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                LinkedHashMap::new));
```

1. **`(oldValue, newValue) -> oldValue`**은 `Collectors.toMap()` 메서드의 네 번째 매개변수로 전달되는 **람다 표현식**이다. 
   - 이 람다 표현식은 **키 충돌**이 발생했을 때 **이전 값(`oldValue`)을 유지**하도록 지정하는 역할을 한다. 
2. `LinkedHashMap::new`는 `Collectors.toMap()` 메서드의 다섯 번째 매개변수로 전달되는 매개변수 생성자 참조이다. 
   - 이 부분은 수집된 맵의 구현체로서 **`LinkedHashMap`을 생성하는 역할**을 한다. 
   - `LinkedHashMap`은 요소의 삽입 순서를 유지하기 때문에, 최종 맵에도 원본 순서가 유지된다. 
3. 따라서, `Collectors.toMap()` 메서드의 네 번째 매개변수와 다섯 번째 매개변수를 사용하여 **키 충돌이 발생했을 때 이전 값으로 유지**하며, **순서가 유지되는 `LinkedHashMap`을 생성**한다. 

