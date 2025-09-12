## Material 종류 요약

| Material 이름        | 조명 반응 | 용도/특징                                                    | 성능        |
| -------------------- | --------- | ------------------------------------------------------------ | ----------- |
| MeshBasicMaterial    | ❌ No      | 색상/텍스처만 표시, 조명 무시 (항상 밝음)                    | ✅ 가장 빠름 |
| MeshStandardMaterial | ✅ Yes     | PBR(물리기반 렌더링), 사실적인 표현, roughness/metalness 지원 | ❗️중간       |
| MeshPhysicalMaterial | ✅ Yes     | `MeshStandardMaterial` 확장 → 투명도, 반사, 굴절 등 고급 표현 | ❌ 무거움    |
| MeshLambertMaterial  | ✅ Yes     | 빠른 조명 지원 (난반사), 부드러운 그림자 없음                | ✅ 빠름      |
| MeshPhongMaterial    | ✅ Yes     | 하이라이트(광택) 표현 가능, 기본 광택 조명                   | ⚠️ 약간 느림 |
| MeshToonMaterial     | ✅ Yes     | 셀 셰이딩(카툰 스타일)                                       | 중간        |
| MeshDepthMaterial    | ❌ No      | 깊이 기반 렌더링 (z-buffer 시각화 등)                        | 빠름        |
| MeshNormalMaterial   | ❌ No      | 표면 법선 방향 시각화 (디버깅 용도)                          | 빠름        |
| ShadowMaterial       | ✅ (특수)  | 그림자만 투명하게 표시 (plane 등에서 사용)                   | 중간        |
| PointsMaterial       | ❌ No      | `Points` 객체용 (입자 시스템)                                | 빠름        |
| LineBasicMaterial    | ❌ No      | 라인용 기본 재질 (조명 없음)                                 | 빠름        |
| LineDashedMaterial   | ❌ No      | 점선 표현용 (조명 없음)                                      | 빠름        |

## 가장 성능 좋은 Material

| 이름                                  | 이유                                                 |
| ------------------------------------- | ---------------------------------------------------- |
| `MeshBasicMaterial`                   | 조명 계산이 없어서 **GPU 부담 최소** — **가장 빠름** |
| `MeshLambertMaterial`                 | 조명 사용시 가장 빠름                                |
| `PointsMaterial`, `LineBasicMaterial` | 특수 객체용이지만 연산 가볍고 빠름                   |

## 상황별 Material 

| 상황                               | 추천 Material          |
| ---------------------------------- | ---------------------- |
| 단순 색상/텍스처 표현              | `MeshBasicMaterial`    |
| 사실적인 표현 (금속, roughness 등) | `MeshStandardMaterial` |
| 물, 유리, 굴절 등 고급 표현        | `MeshPhysicalMaterial` |
| 셀 셰이딩/만화 스타일              | `MeshToonMaterial`     |
| 조명 반응 필요한데 성능도 중요     | `MeshLambertMaterial`  |
| 반짝이는 금속 느낌 표현            | `MeshPhongMaterial`    |