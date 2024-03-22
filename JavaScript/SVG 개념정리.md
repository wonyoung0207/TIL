# SVG

---

>[참고 사이트1](https://paullabworkspace.notion.site/2-SVG-d7e0ab83a51d4e6fa97d29994f526561)

## 정의

1. 확장 가능한 벡터 그래픽(Scalable vetor graphics)으로, XML 기반의 2차원 그래픽이다.
2. HTML 태그의 집합으로 이루어져 있어 CSS와 javascript로 컨트롤 할 수 있다. 

## 장점

1. 벡터 이미지이기 때문에 이미지가 깨지지 않는다. 
2. 이미지를 키워도 용량이 늘어나지 않는다. 

## 단점

1. 코드로 이루어진 이미지이기 때문에 복잡한 이미지일수록 파일 사이즈가 커진다. 
   1. 단순한 모양일수록 효율 좋음 
   2. 주로 로고, 도형 등을 구현할 때 많이사용함  
   3. 단, 코드를 최대한 압축해 용량을 줄일 수 있다. 

## HTML에 SVG 적용 방법 

1. img 태그로 사용 ( 요소 변경 x )

   ```html
   <img src="frog.svg" alt="">
   ```

2. css background로 사용 ( 요소 변경 x )

   ```css
   .cont-svg {
       width: 100vw;
       height: 100vh;
       background: url(frog.svg) no-repeat 0 0;
       background-size: contain;
   }
   ```

3. 인라인으로 구현 ( 요소 변경 o ) 

   ```html
   <svg width="624" height="465" fill="none" xmlns="http://www.w3.org/2000/svg">
           <path
               d="m446.529 308.502 79.386-37.479c-37.824-34.863-111.48-58.521-196.146-58.521-123.264 0-223.191 50.142-223.191 112.002 0 61.857 99.927 112.002 223.191 112.002 94.674 0 175.575-29.586 208.011-71.334l-91.251-56.67Z"
               fill="#00A249" />
   
           .. 중략 ...
   
           <path d="M383 129c0 16.016-13.208 29-29.5 29S324 145.016 324 129s13.208-29 29.5-29 29.5 12.984 29.5 29Z"
               stroke="#000" />
           <ellipse class="eye-right" cx="353.5" cy="129" rx="12.5" ry="12" fill="#000" />
       </svg>
   ```

4. object 태그 사용 ( 요소 변경 o )

   ```html
   <object data="./image.svg" type="image/svg+xml"></object>
   ```

## SVG 이미지 최적화 

1. 구글에 SVG이미지 최적화 검색 또는 다음 링크에서 최적화 할 수 있다. 
2. [SVG 이미지 최적화 사이트](https://jakearchibald.github.io/svgomg/)

### SVG를 CSS로 컨트롤하는 예시 

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>svg</title>
    <style>
        .eye-right {
            animation-duration: 0.5s;
            animation-name: blink;
            animation-direction: alternate;
            animation-iteration-count: infinite;
            transform-origin: 353.5px 129px;
        }

        @keyframes blink {
            to {
                transform: scaleY(0.1);
            }
        }
    </style>
</head>
<body>
    <svg width="624" height="465" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path
            d="m446.529 308.502 79.386-37.479c-37.824-34.863-111.48-58.521-196.146-58.521-123.264 0-223.191 50.142-223.191 112.002 0 61.857 99.927 112.002 223.191 112.002 94.674 0 175.575-29.586 208.011-71.334l-91.251-56.67Z"
            fill="#00A249" />
        <path
            d="m446.529 308.502 79.386-37.479c-37.824-34.863-111.48-58.521-196.146-58.521-123.264 0-223.191 50.142-223.191 112.002 0 61.857 99.927 112.002 223.191 112.002 94.674 0 175.575-29.586 208.011-71.334l-91.251-56.67Z"
            stroke="#1A1919" stroke-width="9" stroke-linecap="round" stroke-linejoin="round" />

        ... 중략 ...

        <path d="M383 129c0 16.016-13.208 29-29.5 29S324 145.016 324 129s13.208-29 29.5-29 29.5 12.984 29.5 29Z"
            fill="#fff" />
        <path d="M383 129c0 16.016-13.208 29-29.5 29S324 145.016 324 129s13.208-29 29.5-29 29.5 12.984 29.5 29Z"
            stroke="#000" />
        <ellipse class="eye-right" cx="353.5" cy="129" rx="12.5" ry="12" fill="#000" />
    </svg>
</body>
</html>
```

## 한국지도 다루기

1. [영상 참고](https://www.youtube.com/watch?v=c0EQWKN5kh4&feature=youtu.be)
2. [각 나라별 지도 다운](https://www.amcharts.com/svg-maps/)



