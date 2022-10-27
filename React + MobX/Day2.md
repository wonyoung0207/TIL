# Day2

> 3-5까지의 내용 

---

## React Component

1. 정의
- 리액트로 구성된 UI의 가장 기본적인 단위  
   - 컴포넌트는 리액트 엘리먼트로 이루어져 있다. 

## Props

1. 정의

   - 상위 컴포넌트로부터 전달받은 데이터가 들어가는 곳 
   - 리액트는 컴포넌트로 구성되며 컴포넌트 안에 컴포넌트가 존재하는 트리구조이다. 따라서 컴포넌트들 사이에 데이터를 전달해야하는데 이때 사용되는것이 props이다. 

2. 사용방법

   ```react
   import Books from './BooksData' // 책정보를 담고있는 BooksData 의 정보를 가져온다. 
   import React, {Component} from 'react'// react 에서 React를 가져오고 , export에서 Component 모듈의 이름을 가진 데이터도 가져온다. 
   
   class App extends Component{
       render(){// 컴포넌트는 무조건  render() 함수를 사용해야하며 render함수는 return 하는 값이 무조건 존재해야한다. 
           return (
               <BookList books={Books}></BookList>// BookList라는 컴포넌트로 books라는 변수의 데이터를 보낸다. 이제 BookList에서 props를 이용해 Books의 데이터를 가져올 수 있다 .
           )
       }
   }
   ```

   ```react
   class Customer extends React.Component{
       render(){ 
           const {id, name, orders} = this.props; // 구조분해할당 문법을 사용해서 3개의 변수에 상위 App으로 부터 전달받은 데이터인 props의 정보를 넣어준다. 
           return (
           )
       }
   }
   ```

3. props를 검증하는 방법

   - PropsTypes 를 통해 상위 컴포넌트로부터 전달받은 props를 검증할 수 있다. 

   - 에러가 발생해도 컴파일과 실행에 문제가 없을 수 있다. 이때 어디서 어떤 에러가 발생했는지 알기위해 사용하는 것이 PropsTypes 이다. 

     ```react
     class Customer extends Component {
         static propTypes = {
             id : PropTypes.string.isRequired,
             name : PropTypes.string.isRequired,
             orders : PropTypes.array.isRequired
         }
         render() {
             const { id, name, orders } = this.props;
             return (
             )
         }
     }
     ```

     

