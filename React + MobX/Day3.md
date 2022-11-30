# Day3

> 4-1 ~ 4-5 까지의 내용 

---

## State

- 정의

  - 컴포넌트에서 변경 가능한 데이터를 관리하기 위해 사용하는 객체 
  - props는 컴포넌트의 입장에서는 불변의 데이터이다. ( 상위로부터 받은데이터이기 때문에 변경 불가 )

- 사용방법

  - 초기화는 객체 필드의 선언부 혹은 생성자에서 구현한다. 

    - 생성자는 클래스가 인스턴스화 될 때 한번 호출되는 특수한 메서드 이다. 

    - 이때 state 초기화를 위해 생성자를 정의할 때에는 super 생성자를 호출하여 부모 클래스에 props를 전달해야한다. ( super() 로 props를 넘겨야 this를 사용할 수 있다. )

      ```react
      class StateApp extends Component{
          constructor(props){ // 생성자 
              super(props);
              this.state = {
                  books : Books,
                  selectedBook : Books[0]
              }
          }
          searchByTitle(title){
              let updateList = Books;
              updateList = updateList.filter(book => {
                  return book.title.toLowerCase().search(title.toLowerCase()) != -1; // 해당 문자를 포함하고 있는 book 만 리턴함 
              });
              this.setState({// state 값 변경 
                  books : updateList
              })
              
          }
      }
      ```

      

  - state의 값 변경은 setState() 메소드를 통해 할 수 있다. 

  

## Arrow Function

- 함수를 보다 쉽고 간단하게 사용하기 위한 방법 

- 사용예시

  - 기존함수 

    ```react
    function add(first, second){
        return first + second;
    }
    
    var add = function(first, second){
        return first + second;
    };
    ```

  - arrow 함수

    ```react
    let add = (first,second) => {    // () 안에 파라미터
        return first + second;         // first 더하기 second 바환
    };
    
    let add = (first,second) => first + second;   //바로 반환
    
    //  객체 반환
    let addAndMultiple = (first,second) => ({ add: first + second, multiply: first * second});
    
    
    Function2 =( num1, num2 )=> { // 매개변수 num1, num2
        let num3 = num1+num2;
        console.log(num3 + '. Arrow Function : ' + this.state.arrowFunc); // return 값 
    }
    ```

  - **()** 안에는 기존 함수에서 사용하던 파라미터를, => 다음 **{}** 안에는 return하고 싶은 내용을 적으면 된다

  

## Map 함수에 이용 

- 정의

  - 값을 여러 개 가지고 있는 배열에서 모든 값을 꺼내고자 할 때 사용된다.
  - 반복되는 컴포넌트를 렌더링하기 위해서 사용한다. 

- 사용법 

  - 일반 함수 

    ```react
    const numbers = [1, 3, 5];
    const listItems = numbers.map(function(number){
        console.log(number);
        return number + 1;
    });
    
    console.log(listItems);
    // 결과값 : 
    1
    3
    5
    {2,4,6}
    ```

  - es6문법 사용

    ```react
    const numbers = [1, 3, 5];
    const listItems = numbers.map((number, idx) => {
        console.log(number);
    	return number + 1;
    });
    
    console.log(listItems);
    // 결과값 : 
    1
    3
    5
    {2,4,6}
    ```

    

## Variant   속성

- 사용되는 곳 

  -  Material UI에서는 \<Typography/> 라는 하나의 컴포넌트를 사용해서 자유자재로 다양한 스타일의 텍스트를 연출할 수 있다. 
  -  해당 컴포넌트 안에 있는 prop(속성) 중 하나가 variant 이다. 

- 해당 속성은 color, size와 같은 prop으로, 태그의 속성 중 하나이다. 

  - 영어로는 변종, 이형이라는 뜻을 가진다. 
  - variant prop은 단순히 텍스트의 크기 뿐만 아니라 HTML 태그도 결정을 합니다. 즉, variant="h1" 을 사용하면 \<h1/> 태그로 마크업이 된다. 

- 사용방법 

  ```react
  <Typography variant="h6" color="primary"></Typography> 
  <Typography variant="body1">Hello Material</Typography> 
  ```
  


