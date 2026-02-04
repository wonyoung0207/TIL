# Local 과 Physic world 개념정리

---

>

## 이론 

- Physics World는 “진짜 세계”고, Three.js Local World는 “그걸 따라 그려주는 세계”이다. 
- Physics World를 **step()**으로 움직이고, 그 결과 좌표를 그대로 Local World(Three.js)에 복사한다.
- **Local World가 Physics World를 “따라간다”**

## Physics World (Rapier)

- 숫자 계산용 세계
- 충돌, 중력, 관성
- **절대 직접 눈에 안 보임**

## Local / Visual World (Three.js)

- 렌더링용 세계
- Mesh, Camera, Light
- **물리는 모름**

```
[ Physics World ]  →  계산만 함
        ↓
[ Three.js World ] →  그려만 줌

둘은 완전히 분리된 세계임
```

## Physical world 움직임 반영 

```js
// 중력 생성 
core.physicsWorld = new RAPIER.World(gravity)

// entity : (물리세계 물질, 현실 Threejs 물질)
function addEntity(_physicalDescription: any = null, _visual: any = null) {
  const entity = {
      physical: getPhysical(_physicalDescription),
      visual: _visual
  }
  entitiesKey++
  entities.set(entitiesKey, entity)

  return entity
}

// Timer (프래임단위 호출)
function update(deltaTime: number) {
  if (!core.physicsWorld) return

  core.physicsWorld.timestep = deltaTime
  core.physicsWorld.step() // 모든 rigibody 에 대해 중력 재계산 

    // 물리 세계 object 를 이용해 현실 세계 업데이트 
  entities.forEach((_entity) => {
      if(_entity.visual && _entity.physical && _entity.physical.body) {
          const translation = _entity.physical.body.translation()
          const rotation = _entity.physical.body.rotation()
          _entity.visual.position.copy(translation)
          _entity.visual.quaternion.copy(rotation)
      }
  })
}
```

##### `physicsWorld.step()` 의 의미

- 모든 RigidBody에 대해
  - 중력 적용, 충돌 계산, Joint 제약 계산
  - 결과로 새로운 position, 새로운 rotation 가 계산됨
- **Physics World 내부에서만 일어나는 일**

## Three.js 반영 시점 

- Physics World의 결과를 “복사”할 때

```js
// 물리 세계 
const translation = entity.physical.body.translation()
const rotation = entity.physical.body.rotation()


// local 세계 적용 
entity.visual.position.copy(translation)
entity.visual.quaternion.copy(rotation)
```

**Physics → Local(World) 동기화**