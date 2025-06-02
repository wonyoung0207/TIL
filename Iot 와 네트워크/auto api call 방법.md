```
#!/bin/bash

while true; do
  echo "send start!!"

  # 1) OccurTime: 현재 시간을 YYYYMMDDHHMMSS 형태로 생성
  OccurTime=$(date "+%Y%m%d%H%M%S")

  # 2) Position: 0.4 ~ 1.0 사이의 난수(소수점 셋째 자리까지)
  Position=$(awk -v min=0.1 -v max=1.5 'BEGIN { srand(); printf "%.3f", min + rand()*(max-min) }')
  # Position=$(awk -v min=0.1 -v max=2.5 'BEGIN { srand(); printf "%.3f", min + rand()*(max-min) }')

  # 3) curl 호출 시 헤더에 동적으로 넣기
  curl -X POST "http://127.0.0.1:8888/event" \
    -H "OccurTime: ${OccurTime}" \
    -H "DEventType: 01" \
    -H "RecVideoUrl: C:\\Users\\DT\\Desktop\\SampleVideo.ia.mp4" \
    -H "Position: ${Position}" \
    -H "VehicleCount: 123"
  sleep 1
  # curl -X POST "http://127.0.0.1:8888/event" \
  #   -H "OccurTime: ${OccurTime}" \
  #   -H "DEventType: 01" \
  #   -H "RecVideoUrl: C:\\Users\\DT\\Desktop\\SampleVideo.ia.mp4" \
  #   -H "Position: ${Position}" \
  #   -H "VehicleCount: 123"

  # sleep 1
done

```

