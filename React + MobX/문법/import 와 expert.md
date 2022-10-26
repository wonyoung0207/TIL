# Import 와 Export

---

## 차이점 

- import 와 expert 는 javaScript 의 ES6 문법이다. 
- import 는 모듈을 가져올 때 사용하고, expert는 내보낼 때 사용된다. 
- ES6모듈로써 전용키워드를 사용하기 때문에 가독성이 좋다. 
- 또한 비동기 방식으로 작동하고 모듈에서 실제로 쓰이는 부분만 불러오기 때문에 성능과 메모리 부분에서 유리한 측면이 있다. 

## export

- 모듈을 내보낼때 사용한다. 

- export default라는 키워드를 사용하게 되면, 특정 변수를 import하지 않아도 export default에 저장한 변수를 import할 수 있습니다.

  ```react
  const exportValues = {
      message: '안녕하세요';
  }
  
  const codingAppleReact = 'good';
  
  // 단순한 export
  export { exportValues, codingAppleReact };
  
  // 기본 export
  export default exportValues;
  ```

- 따라서 export 를 사용하는 방법은 크게 두가지 이다. 

  1. export로 선언해서 import 시 from으로 라이브러리의 형태로 모듈을 사용하는 경우
  2. export default 로 선언해서 모듈 이름으로 바로 사용할 수 있는 경우  

## import 

- 모듈을 불러올 때 사용하는 키워드 이다. 

  

  ```react
  const react = require('react');
  // ES6
  import react from 'react';
  ```

- 여기서 require 은 모듈을 불러오는 키워드이다. 

  - 이때 모듈의 경로가 아닌 이름만으로 불러와 사용할 수 있다. 

  - 이름으로만 사용할 수 있는 이유는 react의 특징을 살펴보면 알 수 있다. 

    

- react의 모듈들은 모두 node_modules에 저장된다. 이때 이름으로 해당 모듈이 저장되기 때문에 자바와 다르게 경로를 지정하지 않아도 된다. 

  ```react
  // ES6 import
  import IAmDefault, { exportValues, codingAppleReact } from './export';
  
  // Result
  /* 
      {
          message: '안녕하세요';
      }
  */
  console.log(IAmDefault);
  
  /* 
      {
          message: '안녕하세요';
      }
  */
  console.log(exportValues); 
  
  // 'good'
  console.log(codingAppleReact);
  ```

- 특정 변수를 지정해서 import한 exportValues와 codingAppleReact는 export.js에서 선언한 변수를 특정해서 가져오게 됩니다.

  

  ```react
  import {sayHi as hi, sayBye as bye} from './say.js';
  ```

- import 를사용해 모듈을 불러올 때 as 키워드를 사용해서 모듈의 이름을 변경해서 가져올 수 있다. 