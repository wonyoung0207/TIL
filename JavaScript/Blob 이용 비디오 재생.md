## Blob 이용 영상 재생

- Blob 재생 시 `Content-Type`만 정확히 지정하면 재생은 가능. 그리고 video 태그에 연결시키면 자동으로 content-range 설정해서 컨트롤 가능

```js
async showVideoFromByteArray(byteArrayBase64) {
      // Base64 디코드
      const binary = atob(byteArrayBase64);
      const len = binary.length;
      const bytes = new Uint8Array(len);
      for (let i = 0; i < len; i++) {
        bytes[i] = binary.charCodeAt(i);
      }

      // Blob 생성 시 Content-Type 반드시 지정!
      const blob = new Blob([bytes], { type: 'video/mp4' }); // 또는 video/x-matroska 등
      this.videoUrl = URL.createObjectURL(blob);
    }
```

