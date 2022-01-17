## Nested Router(중첩라우팅)

> 중첩라우팅이란?

중첩 라우팅이란 라우팅 맵핑을 최상위 컴포넌트 뿐만 아니라 여러 개의 컴포넌트에 걸쳐서 단계별로 정의하는 라우팅 기법

```
<div id="app">
      <router-view></router-view>
</div>
const User = {
        template: `
          <div>
            User Component
            <router-view></router-view>
          </div>
        `,
      }

      const UserProfile = {
        template: '<p>User Profile Component</p>',
      }
      const UserPost = {
        template: '<p>User Post Component</p>',
      }

      const routes = [
        {
          path: '/user',
          component: User,
          children: [
            {
              path: 'posts',
              component: UserPost,
            },
            {
              path: 'profile',
              component: UserProfile,
            },
          ],
        },
      ]
```

## Names View

```
<div id="app">
      <router-view name="header"></router-view>
      <router-view></router-view>
      <router-view name="footer"></router-view>
</div>
 	  const Body = {
        template: '<div>This is Body</div>',
      }
      const Header = {
        template: '<div>This is Header</div>',
      }
      const Footer = {
        template: '<div>This is Footer</div>',
      }

      const router = new VueRouter({
        routes: [
          {
            path: '/',
            components: {
              default: Body,
              header: Header,
              footer: Footer,
            },
          },
        ],
      })
```