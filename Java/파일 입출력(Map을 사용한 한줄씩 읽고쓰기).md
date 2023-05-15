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

## 