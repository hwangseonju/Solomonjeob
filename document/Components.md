



# Components

components 설계를 제대로 안하면

1. 전반적인 코드의 가독성과 유지관리 효율성 저하
2. 컴포넌트 구조의 복잡성 증가
   1. props와 watch를 최소화하자
3. 독립적인 컴포넌트로의 변이



Vue.js Component

- `Vue.js Component`은 HTML Element를 확장하고 재사용 가능한 형태로 구현하는 것을 말한다. Vue.js에서 사용된 모든 컴포넌트는 하나하나가 Vue.js의 인스턴스이기도 하다. 컴포넌트의 생성 과정은 단순히 **등록 -> 사용** 패턴으로만 이루어진다.



전역등록(Global Registration)

global-component.vue

```
<template>
  <div>
    <button @click="showTitle">Click</button>
    <span>{{ title }}</span>
  </div>
</template>

<script>
  export default {
    name: 'global-component',
    data () {
      return {
        title: void 0
      }
    },
    methods: {
      showTitle () {
        this.title = 'Global Component!!'
      }
    }
  }
</script>

<style scoped>
  div {
    padding: 20px
  }

  div span {
    margin: 20px
  }
</style>
```

main.js

```
import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import GlobalComponent from './components/global-component'

Vue.component(GlobalComponent.name, GlobalComponent)

new Vue({
  render: h => h(App),
}).$mount('#app')
```

App.vue

```
<template>
  <div id="app">
    <global-component></global-component>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>
```



지역등록(Local Registration)

local-component.vue

```
<template>
  <div>
    <button @click="showTitle">Click</button>
    <span>{{ title }}</span>
  </div>
</template>

<script>
  export default {
    name: 'local-component',
    data () {
      return {
        title: void 0
      }
    },
    methods: {
      showTitle () {
        this.title = 'Local Component!!'
      }
    }
  }
</script>

<style scoped>
  div {
    padding: 20px
  }

  div span {
    margin: 20px
  }
</style>
```

App.vue

```
<template>
  <div id="app">
    <global-component></global-component>
    <local-component></local-component>
  </div>
</template>

<script>
  import LocalComponent from './components/local-component'

  export default {
    name: 'app',
    components: { LocalComponent }
  }
</script>
```



component의 기본 작성

부모 -> 자식  = props

자식 -> 부모 = events($emit)



parent-component.vue

```
<template>
  <div>
    <child-component :parent-message="msg"
                     @print="val => printMessage(val)"></child-component>
  </div>
</template>

<script>
  import ChildComponent from './components/child-component'

  export default {
    name: 'parent-component',
    components: { ChildComponent },
    data () {
      return {
        msg: '안녕하세요.'
      }
    },
    methods: {
      printMessage (val) {
        console.log(val)
      }
    }
  }
</script>
```

child-component.vue

```
<template>
  <div>
    <button @click="sendEvent()">Print</button>
  </div>
</template>

<script>
  export default {
    name: 'child-component',
    props: {
      parentMessage: {
        type: String,
        default: void 0
      }
    },
    methods: {
      sendEvent () {
        this.$emit('print', this.parentMessage)
      }
    }
  }
</script>
```

`parent-component`에서 `child-component`를 바인딩하고 `parent-message`라는 속성을 `v-bind (:)`를 통해 전달한다. 이 관계가 부모에서 자식으로 데이터를 보낼 경우이다. 그리고 `v-on (@)`을 통해 `child-component`에서 전달되는 `print`라는 이벤트를 수신하고 있다.

이제 `child-component`를 보자. 여기서는 `props`인 `parentMessage` 통해 이미 부모에서 전달된 값을 받고 있으며, `$emit`을 통해 부모에게 다시 이벤트를 전달하고 있다. Vue.js 컴포넌트에서는 이러한 관계가 구성이 되어야 하고 만약 `parent-component`에 `props`라는 속성이 있고 받는 값이 있다면 `parent-component` 역시 자식 컴포넌트가 되게 된다.

이러한 구조에서 조심해야 할 사항은 바로 `props`인데 Vue.js 전체적으로 본다면 양방향 바인딩이지만 이 `props`는 단방향 바인딩을 형성한다. 상위 속성 즉 `parent-component`에서 보내는 `message`가 업데이트되면 하위로 흐르는 `props` 역시 업데이트가 되지만 반대로 `props`인 `parentMessage`를 변경한다고 상위 속성이 업데이트되지는 않는다. 오히려 `props`는 변경하지 않고 **원시 데이터**로 사용하는 것을 추천하며 만약 변경 시 Vue.js의 경고를 보게 될 것이다.

여기까지 해서 Vue.js에서 가장 핵심적인 개념인 컴포넌트를 알아보고 생성해 보았다. 위에서 언급했듯이 컴포넌트는 `부모-자식` 관계를 가지고 부모가 자식이 될 수도 있다. 이러한 구조로 인해 최초에 말했던 설계가 가장 중요하다는 것이다. 아무리 컴포넌트의 개념을 이해하고 적합한 컴포넌트를 생성한다 하더라도 복잡성이 높아지면 결과적으로 효율적이지 않은 컴포넌트가 생성된다.