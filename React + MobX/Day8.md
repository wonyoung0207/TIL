# Day8

> 7-1 ~ 7-3 까지의 내용 

---

## MPA vs SPA

### MPA

- 기존 우리가 사용하는 웹 방식으로, Client 가 페이지를 요청하면 Server에 저장되어있는 페이지를 Response 해줘서 보여준다. 
- 따라서 페이지마다 고유 URL  즉, 한 사이트이더라도 여러 html 페이지를 가진다. 

### SPA

- 처음 URL로 서버에 요청을 보내면 그 결과로 index.html 페이지(첫 페이지)를 Response 한다. 
- 이때 리액트가 컴포넌트화 시켜서 변경 내용이 있을 경우 컴포넌트만 변경해서 다른 페이지로 변경된것 처럼 보이게 된다. 
  - 이 현상의 단점은 새로고침시 첫 페이지인 index.html로 다시 돌아가는것과 북마크 ( 지금 보고있는 페이지 ) 설정이 불가능하다는 단점이 있다. 
  - 따라서 이러한 URL 변경에 따른 페이지 변화를 감지하는 역할을 하는것이 **Router**이다. 
- 정리하자면, SPA는 첫 페이지를 이용해 리액트가 컴포넌트를 변경하여 마치 다른페이지인것처럼 나타낸다. ( URL이 하나이다. )

## Router

### 정의

- 고유 URL을 가지는 SPA이 특성을 해결하기 위한 기능 
- 현재 보고있는 페이지를 브라우저의 history와 동기화 하는 역할을 한다. 
- 따라서 Router를 이용해 URL과 컴포넌트를 멥핑할 수 있다. 

### 사용법

- React-Router 라이브러리에 있는 \<Router> 와 \<BrowserRouter> 를 이용해 페이지 URL을 구분하고 설정한다. 
  - \<BrowserRouter>  
    - Routing 할 모든 컴포넌트를 감싸게 되는 컴포넌트
  - \<Router> 
    - 서로 다른 URL 을 나눌때 사용하는 컴포넌트 
    - exact를 사용해서 ' / '  와 완벽히 같은 주소만 해당 Router가 적용되도록 한다. 만약 exact를 사용하지 않는다면  '/about '이런 주소가 오더라도 해당 라우터와 about 라우터가 호출되어 2개의 라우터를 가지는 페이지가 만들어진다.
  - \<Link> 
    - \<a> 태그 대신에 react에서 사용하는 태그이다. 
    - 새로운 주소 (Router) 로 변경이 되었을 때, 페이지를 다시 로딩하는것이 아닌 바뀐부분만 rendering하도록 해주는 컴포넌트 
  - \<NavLink> 
    - NavLink 는 내부링크로, Link컴포넌트와 다르게 선택되는 태그에 active라는 class명이 붙어 사용자가 어느 태그에 위치해 있는지 알 수 있게한다. 

```react
import { BrowserRouter } from 'react-Router';

ReactDOM.render(
    <BrowserRouter><!-- Router를 사용할 모든 컴포넌트를 감싸는 컴포넌트  -->
    	<APP>
        </APP>,
    </BrowserRouter>
    document.getElementById('root')
);
```

```react
import React, {Component} from 'react';
import {Link, NavLink} from 'react-router-dom';

class Nav extends Component {
    render(){
        return (
            <nav className="navtop">
                <h2> Namoosori</h2>
                <ul className='nav-links'>
                    
                    <!-- 기존 사용하던 방법으로 주소 주기-> 모든 내용이 다시 새로고침 됨  -->
                    <li><a href='/'> Main </a></li>
                    <li><a href='/about'> about </a></li>
                    <li><a href='/lessons'> lessons </a></li>      
                    
                    <!-- 새로고침 일어나지 않고 변경되는 컴포넌트만 다시 rendering하는 방법  -->
                    <li><Link to='/'> Main </Link></li>
                    <li><Link to='/about'> about </Link></li>
                    <li><Link to='/lessons'> lessons </Link></li>    
                </ul>
            </nav>
        );
    }
}
export default App;
```

 ```react
 import {Router} from 'react-router-dom';
 
 class App extends Component {
     render(){
         return (
             <div className="App">
             	<Nav>
                     <Switch>
                         <Router exact path='/' component={main}></Router> 
                         <Router path='/about' component={About}></Router>	
                         <Router path='/lessons' component={Lessonns}></Router>                     
                         <Router component={() => <h2> Not Found !! </h2>}></Router>
                     </Switch>
                 </Nav>
             </div>
         );
     }
 }
 export default App; 
 ```

