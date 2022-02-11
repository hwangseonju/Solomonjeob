<template>
  <app-header></app-header>
  <div class="container-fluid">
    <div class="row">
      <question-answer-form></question-answer-form>
      <router-view></router-view>
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
  components: {
    // QuestionCollectionList,
    // QuestionAnswerList,
    QuestionAnswerForm,
    AppHeader
  },

};
</script>

<style scoped>


</style>
