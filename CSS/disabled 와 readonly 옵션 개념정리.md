# disabled 와 readonly 옵션 개념정리

---

>

## `disabled` 속성

- 입력 필드가 비활성화되어 사용자가 입력할 수 없다.

- 특징 

  - 상호작용: 
    - 완전히 비활성화되어 사용자 입력을 받을 수 없다.
  - 스타일: 
    - 기본적으로 비활성화된 스타일을 적용받는다 (회색 배경 등).
  - 폼 제출: 
    - **비활성화된 입력 요소는 폼 데이터로 제출되지 않는다.**
  - 포커스: 
    - **입력 요소는 포커스를 받을 수 없다.**

- 예시

  ```HTML
  <input type="text" value="Cannot edit or interact" disabled>
  
  // disable 요소에 스타일 적용
  <style>
      input:disabled {
        background-color: #e9ecef;
        color: #6c757d;
      }
  </style>
  ```

  

## `readonly` 속성

- 입력 필드가 읽기 전용이 되어 사용자가 값을 수정할 수 없다.

- 특징

  - 상호작용: 
    - 읽기 전용 상태로 입력 값을 수정할 수 없다.
  - 스타일: 
    - 스타일은 보통 일반 입력 요소와 동일하지만, CSS를 통해 차별화할 수 있다.
  - 폼 제출: 
    - **읽기 전용 입력 요소는 폼 데이터로 제출된다.**
  - 포커스: 
    - **입력 요소는 포커스를 받을 수 있다.**

- 예시

  ```html
  <input type="text" value="Can read but not edit" readonly>
  
  // readonly 요소에 스타일 적용
  <style>
      input[readonly] {
        background-color: #f8f9fa;
        color: #495057;
      }
  </style>
  ```

  



