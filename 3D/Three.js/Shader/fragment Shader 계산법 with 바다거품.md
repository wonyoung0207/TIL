# Fragment Shader의 핵심 역할

---

>

## 역할

- 화면의 **각 픽셀(fragment)** 마다  “이 픽셀은 어떤 색, 밝기, 투명도를 가져야 하는가?” 를 **계산해서 최종 색을 결정**

## 연산 의미

- 모든 연산은 **“빛을 수학으로 표현”**하기 위한 수단이다. 

| 연산           | 그래픽적 의미               |
| -------------- | --------------------------- |
| `+`            | 빛을 더한다 (밝아짐)        |
| `-`            | 빛을 빼거나 감쇠            |
| `*`            | 두 효과 겹치기 (mask, 감쇠) |
| `mix()`        | a→b로 천천히 섞기           |
| `smoothstep()` | 경계선 부드럽게 만들기      |

## 입력 (Input)

- fragment shader는 vertex shader로부터 보간된 값들을 입력받는다. 
  - 즉, “지금 그리고 있는 픽셀의 위치가 어딘가?”, “물이 얼마나 출렁이는가?” 등의 데이터를 받는다. 
- 예시:

```glsl
varying vec3 csm_vPositionW;  // 현재 픽셀의 월드 좌표(modelMatrix)
uniform float uWaterLevel;    // 물의 높이
uniform float uTime;          // 시간 (파도 애니메이션용)
```

## 계산 (Compute)

- 이 입력값들을 이용해 픽셀의 색을 결정하는 여러 계산을 하게 된다. 
  - 예를 들어 terrain의 거품 로직:

```glsl
float sineOffset = sin(uTime * uWaveSpeed) * uWaveAmplitude;
float currentWaterHeight = uWaterLevel + sineOffset;

float stripe = smoothstep(currentWaterHeight + 0.01, currentWaterHeight - 0.01, csm_vPositionW.y)
             - smoothstep(currentWaterHeight + uFoamDepth + 0.01, currentWaterHeight + uFoamDepth - 0.01, csm_vPositionW.y);
```

- 이 단계에서는:
  - `sin()` 으로 시간에 따른 파도 움직임 계산
  - `smoothstep()` 으로 “물과 닿는 높이 범위”를 감지
  - 두 값을 조합(`-`)해서 “얇은 흰색 띠(foam zone)” 만들기
- 즉, fragment shader는 **빛의 세기나 색의 변화, 높이 차이 등 시각적 규칙을 수학으로 계산하는 곳**

## 색상 합성 (Color Blending)

- 계산된 값으로 실제 색을 만든다. 
  - `stripe == 0` → baseColor (원래 지형 색)
  - `stripe == 1` → stripeColor (흰색 거품)
  - `0~1 사이` → 두 색이 자연스럽게 섞임
- 결과적으로 terrain의 물가 근처가 **하얗게 빛나는 거품 띠**로 표현됨.

```glsl
vec3 baseColor = csm_DiffuseColor.rgb;   // 기본 땅 색
vec3 stripeColor = vec3(1.0);            // 흰색 (거품)

vec3 finalColor = mix(baseColor - stripe, stripeColor, stripe);
```

## 출력 (Output)

- 마지막으로 GPU에 “이 픽셀은 이런 색으로 렌더링해라”라고 전달
  - 즉, 계산된 결과를 실제 화면 픽셀 색으로 출력하는 단계.

```glsl
csm_DiffuseColor = vec4(finalColor, 1.0);
```

## 요약 

-  **stripe는 terrain의 y좌표가 파도 높이 근처에 있을 때 1.0이 되는 값**

| 단계                        | 내용                                            | terrain + foam 예제에서의 역할             |
| --------------------------- | ----------------------------------------------- | ------------------------------------------ |
| **1. 입력(Input)**          | vertex shader에서 전달된 위치, 색, 시간 등 받기 | `csm_vPositionW`, `uTime`, `uWaterLevel`   |
| **2. 계산(Compute)**        | 수학적 연산으로 시각적 규칙 정의                | `sin`, `smoothstep`으로 거품 띠 위치 계산  |
| **3. 색상 합성(Color Mix)** | baseColor와 효과(foam, 빛 등) 섞기              | `mix(baseColor, foamColor, stripe)`        |
| **4. 출력(Output)**         | 최종 색상 픽셀에 출력                           | `csm_DiffuseColor = vec4(finalColor, 1.0)` |

## Terrain 에 바다 거품 표출 예제

```glsl
// terrain - fragment Shader
varying vec3 csm_vPositionW;

uniform float uTime;
uniform float uWaterLevel;
uniform float uWaveSpeed;
uniform float uWaveAmplitude;
uniform float uFoamDepth;
uniform vec3 uGrassColor;
uniform vec3 uUnderwaterColor;

void main() {
    
    // Set the current color as the base color
    vec3 baseColor = csm_DiffuseColor.rgb;

    // Darken the base color at lower Y values to simulate wet sand
    float heightFactor = smoothstep(uWaterLevel + 1.0, uWaterLevel, csm_vPositionW.y);
    baseColor = mix(baseColor, baseColor * 0.5, heightFactor);
    
    // Blend underwater color with base planeMesh to add depth to the ocean bottom
    float oceanFactor = smoothstep(min(uWaterLevel - 0.4, 0.2), 0.0, csm_vPositionW.y);
    baseColor = mix(baseColor, uUnderwaterColor, oceanFactor);

    // Add grass to the higher areas of the terrain
    float grassFactor = smoothstep(uWaterLevel + 0.8, max(uWaterLevel + 1.6, 3.0), csm_vPositionW.y);
    baseColor = mix(baseColor, uGrassColor, grassFactor);
    
    // Foam Effect
    // Get the y position based on sine function, oscillating up and down over time
    float sineOffset = sin(uTime * uWaveSpeed) * uWaveAmplitude;

    // The current dynamic water height
    float currentWaterHeight = uWaterLevel + sineOffset;

    float stripe = smoothstep(currentWaterHeight + 0.01, currentWaterHeight - 0.01, csm_vPositionW.y)
                 - smoothstep(currentWaterHeight + uFoamDepth + 0.01, currentWaterHeight + uFoamDepth - 0.01, csm_vPositionW.y);

    vec3 stripeColor = vec3(1.0, 1.0, 1.0); // White stripe

    // Apply the foam strip to baseColor    
    vec3 finalColor = mix(baseColor - stripe, stripeColor, stripe);
    
    // Output the final color
    csm_DiffuseColor = vec4(finalColor, 1.0);
    
}
```

```glsl
varying vec3 csm_vPositionW;

void main() {
  csm_vPositionW = (modelMatrix * vec4(position, 1.0)).xyz;
}
```

