<template>
  <app-header></app-header>
  
  <div class="container-fluid">
    <div class="row">
      <div class="col-3">
        <question-answer-form></question-answer-form>
      </div>
      <div class="col-9">
        <router-view :key="$route.fullPath"></router-view>
      </div>
      
    </div>
  </div>
</template>

<script>
// import "./assets/css/main.css";

// import QuestionCollectionList from "@/components/QuestionCollectionList";
// import QuestionAnswerList from '@/components/QuestionAnswerList.vue';
import QuestionAnswerForm from '@/components/QuestionAnswerForm.vue'
import AppHeader from '@/components/common/AppHeader.vue';
// import QuestionAnswerList from "@components/QuestionAnswerList";

export default {
  name: "QuestionPage",
  components: {
    // QuestionCollectionList,
    // QuestionAnswerList,
    QuestionAnswerForm,
    AppHeader
  },

};
</script>

<style>
#QuestionPage {
  font-family: Avenir, Helvetica, Arial, sans-serif;

  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>