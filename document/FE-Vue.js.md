# Computed 와 watch



### computed 속성

템플릿 내에 표현식을 넣으면 편리하지만, 간단한 연산일 때만 이용하는 것이 좋다.

너무 많은 연산을 템플릿 안에서 하면 코드가 비대해지고 유지보수가 어렵다.

복잡한 로직일수록 computed 속성을 사용해야 한다.



```
<div id="example">
  <p>원본 메시지: "{{ message }}"</p>
  <p>역순으로 표시한 메시지: "{{ reversedMessage }}"</p>
</div>
```

```
var vm = new Vue({
  el: '#example',
  data: {
    message: '안녕하세요'
  },
  computed: {
    // 계산된 getter
    reversedMessage: function () {
      // `this` 는 vm 인스턴스를 가리킵니다.
      return this.message.split('').reverse().join('')
    }
  }
})
```

### computed 속성의 캐싱 vs methods

```
<p>뒤집힌 메시지: "{{ reversedMessage() }}"</p>

```

```
// 컴포넌트 내부
methods: {
  reversedMessage: function () {
    return this.message.split('').reverse().join('')
  }
}
```



computed 속성 대신 메소드와 같은 함수를 정의할 수도 있다.

두 가지 방법의 차이점은 computed 속성은 종속 대상을 따라 저장(캐싱)된다는 것이다.

computed 속성은 해당 속성이 종속된 대상이 변경될때만 함수를 실행한다.

 즉, message 가 변경되지 않는 한, computed 속성인 reversedMessage를 여러번 요청해도 계산을 다시 하지 않고 계산되어 있던 결과를 즉시 반환한다.



이에 비해 methods를 호출하면 렌더링을 다시 할 때마다 항상 함수를 실행한다.

캐싱이 필요한 이유

- 계산에 시간이 많이 걸리는 computed 속성인 A를 가지고 있다고 해보자. 이 속성을 계산하려면 거대한 배열을 반복해서 다루고 많은 계산을 해야 한다. 그런데 A에 의존하는 다른 computed 속성값도 있을 수 있다. 캐싱을 하지 않으면 A의 getter 함수를 꼭 필요한 것보다 더 많이 실행하게 된다. (캐싱을 원하지 않을 경우 methods를 사용한다)



### computed 속성 vs watch 속성

Vue는 Vue 인스턴스의 데이터 변경을 관찰하고 이에 반응하는 보다 일반적인 watch 속성을 제공한다. 다른 데이터 기반으로 변경할 필요가 있는 경우, 특히 AngularJS를 사용하던 경우 watch를 남용하는 경우가 있다. 하지만 명령적인 watch 콜백보다 computed 속성을 사용하는 것이 더 좋다.

watch를 사용할 때

```
<div id="demo">{{ fullName }}</div>

```

```
var vm = new Vue({
  el: '#demo',
  data: {
    firstName: 'Foo',
    lastName: 'Bar',
    fullName: 'Foo Bar'
  },
  watch: {
    firstName: function (val) {
      this.fullName = val + ' ' + this.lastName
    },
    lastName: function (val) {
      this.fullName = this.firstName + ' ' + val
    }
  }
})
```

computed 속성

```
var vm = new Vue({
  el: '#demo',
  data: {
    firstName: 'Foo',
    lastName: 'Bar'
  },
  computed: {
    fullName: function () {
      return this.firstName + ' ' + this.lastName
    }
  }
})
```

