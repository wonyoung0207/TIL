# 태그 투명도 조절

---

>

## 적용방법

1. **투명도(반투명) 배경을 적용**하려면 `style` 속성이나 커스텀 클래스를 활용해서 **background-color에 rgba()** 값을 주면 된다.
2. `0.85`는 85% 불투명, 15% 투명 (값을 줄이면 더 투명해진다.)

```vue
<VCard style="background-color:rgba(255,255,255,0.85)">
  <VCardText>
    이 카드는 배경이 반투명 흰색(85%)으로 표시
  </VCardText>
</VCard>
```

## 컬러 및 투명도 바꾸는 팁

- 완전 흰색: `rgba(255,255,255,1)`
- 80% 흰색: `rgba(255,255,255,0.8)`
- 완전 검정: `rgba(0,0,0,1)`
- 40% 검정: `rgba(0,0,0,0.4)`
- 진한 파랑: `rgba(25,118,210,0.85)` (Vuetify primary)

## blur 효과

```css
.card-blur-bg {
  background-color: rgba(255,255,255,0.8);
  backdrop-filter: blur(8px);
}
```