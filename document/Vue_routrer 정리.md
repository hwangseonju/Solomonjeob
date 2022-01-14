## View router

- 라우팅은 웹 페이지 간의 이동 방법

```
  <div id="app">
      <h1>뷰 라우터 예제</h1>
      <p>
        <router-link to="/main">Main</router-link>
        <router-link to="/login">Login</router-link>
      </p>
      <router-view></router-view>
    </div>
 const Main = {
        template: '<div>main</div>',
      }
      const Login = {
        template: '<div>login</div>',
      }

      const routes = [
        {
          path: '/main',
          component: Main,
        },
        {
          path: '/login',
          component: Login,
        },
      ]

      const router = new VueRouter({
        routes,
      })

      const app = new Vue({
        router,
      }).$mount('#app')
```



>  $mount() API

- el 속성과 동일하게 인스턴스를 화면에 붙이는 역할을 한다
- 강제로 인스턴스를 화면에 붙인다
- 뷰 라우터 공식 문서에서 사용하는 방식이다