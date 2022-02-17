<template>
  <app-header></app-header>

  <br>
    <div class="container all_container_style">

  
  <div class="container-fluid">
    <div class="row">
      <div class="col-3">
        <question-answer-form></question-answer-form>
      </div>
      <div class="col-9">
        <router-view :key="$route.fullPath"></router-view>
      </div>
      
      <div class="row">
        <div class="col-3">
          <question-answer-form></question-answer-form>
        </div>
        <div class="col-9">
          <router-view :key="$route.fullPath"></router-view>
        </div>
        
      </div>
    </div>
    <br>
    <br>
    <br>
    <section class="container footer_style">
    <hr>
    <div style="text-align:center; padding-top:4%;">E-mail : solomonjeob207@gmail.com</div>
    <div style="text-align:center;"> Copyright 2022. Solomonjeob all rights reserved.</div>
    <br>
  
    </section>
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

<style scoped>

#QuestionPage {
  font-family: Avenir, Helvetica, Arial, sans-serif;

  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.all_container_style {
  padding-top: 5%;
  padding-bottom: 5%;
  background-color: #f1f0f0;
  /* background-color: #FFF9F9; */
  /* background-color: #dceafc; */

  border-radius: 5px 5px;
  height: 700px;

}
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
.footer_style {
    
    font-family: 'Pretendard-Regular';


}
</style>