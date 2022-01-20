# 질문모음집 code예시

front end 부분의 질문모음집 페이지로 수정할 부분이 많아 바로 push 하지 않고 md 파일에 올립니다.



> app.vue

```vue
<template>
  <div>
    
    <QuestionCollectionAdd v-on:add="addQuestionCollection"></QuestionCollectionAdd>
    <!-- <todo-list v-bind:프롭스 속성 이름="상위 컴포넌트의 데이터 이름"></todo-list> -->
    <QuestionCollectionList 
      v-bind:propsdata="QuestionCollectionLists"
      v-on:remove="removeItem"
    ></QuestionCollectionList>
    <!-- <todo-footer v-on:하위에서 발생한 이벤트 이름="상위에서 실행할 메서드 이름"></todo-footer> -->
    <QuestionCollectionClear v-on:clear="clearItems"></QuestionCollectionClear>
    <!-- <TodoFooter></TodoFooter> -->
  </div>
</template>

<script>
// import 컴포넌트 이름 from '컴포넌트 파일 경로';

import QuestionCollectionAdd from './components/QuestionCollectionAdd.vue';
import QuestionCollectionList from './components/QuestionCollectionList.vue';
import QuestionCollectionClear from './components/QuestionCollectionClear.vue';

export default {
  components: {
    // '컴포넌트 이름': 컴포넌트 내용,

    QuestionCollectionAdd,
    QuestionCollectionList,
    QuestionCollectionClear,
  },
  data() {
    return {
      QuestionCollectionLists: []
    }
  },
  methods: {
    // fetchTodoItems: function() {
    //   // var arr = [];
    //   for (var i = 0; i < localStorage.length; i++) {
    //     var value = localStorage.key(i);
    //     this.todoItems.push(value);
    //   }
    //   // return arr;
    // },
    clearItems: function() {
      this.QuestionCollectionLists = [];
      localStorage.clear();
    },
    addQuestionCollection: function(QuestionCollection) {
      this.QuestionCollectionLists.push(QuestionCollection);
      localStorage.setItem(QuestionCollection, QuestionCollection);
    },
    removeItem: function(QuestionCollection, index) {
      // 배열 변경
      this.QuestionCollectionLists.splice(index, 1);
      // 브라우저 저장소(DB)에서 삭제
      localStorage.removeItem(QuestionCollection);
    }
  },
  // created: function() {
  //   this.fetchTodoItems();
  // }
}
</script>

<style>
body {
  text-align: center;
  background-color: #F6F6F6;
}
input {
  border-style: groove;
  width: 200px;
}
button {
  border-style: groove;
}
.shadow {
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03);
}
</style>
```





