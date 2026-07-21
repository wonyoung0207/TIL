#### RigidBody (강체 / 영혼)

- **역할**: 중력을 받고, 힘을 받으면 날아가고, 속도를 가진다. 
- **특징**: 형태(모양)가 없기 때문에, RigidBody만 만들어 두면 **아무것도 부딪히지 못하고 유령처럼 서로 통과하여 바닥 아래로 끝없이 추락**한다. 
- **RigidBodyDesc**: 이 영혼을 태어나게 하기 위해 "무게는 5kg으로 하고, 시작 위치는 (0, 10, 0)으로 하자"라고 정해주는 **설계도**

#### Collider (충돌체 / 육체)

- **역할**: "이 영역에 닿으면 부딪힌다"라는 물리적 크기와 표면 성질(마찰력, 통통 튀는 탄성)을 가진다. 
- **특징**: 스스로 움직이거나 중력을 받을 수 없습니다. 반드시 **RigidBody(영혼)에 장착(Attach)되어야만** 영혼을 따라 같이 움직인다. 

1. **RigidBodyDesc(설계도)로 RigidBody(영혼) 생성**:

   ```typescript
   // "위치는 여기고, 중력 영향 받는 동적 강체로 만든다!"
   physical.body = core.physicsWorld.createRigidBody(rigidBodyDesc)
   ```

2. **ColliderDesc로 Collider(육체)를 생성하여 RigidBody에 붙이기**:

   ```typescript
   // "가로/세로/높이가 1인 상자 모양 피부를 만들어서 위에서 만든 body에 씌운다!"
   const collider = core.physicsWorld.createCollider(colliderDescription, physical.body)
   ```

## 정리 

- 즉, rigibody 는 속성이고 해당 속성끼리 상호작용할 수 있게 만들기 위해선 Collider 가 필요하다 . 
- Rigibody + Collider 가 되어야 물리 작용이 제대로 이루어진다. 