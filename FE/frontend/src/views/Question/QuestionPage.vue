<template>
  <div> 
    <div class="row">
      <div class="col-3">
        <question-collection-list></question-collection-list>
      </div>
      <div class="col-9">
        <QuestionAnswerList></QuestionAnswerList>
      </div>
    </div>
  </div>
</template>

<script>
// import "./assets/css/main.css";

import QuestionCollectionList from "@/components/QuestionCollectionList";
import QuestionAnswerList from '@/components/QuestionAnswerList.vue';
// import QuestionAnswerList from "@components/QuestionAnswerList";

export default {
  components: {
    QuestionCollectionList,
    QuestionAnswerList,
    // QuestionAnswerList
  },

};
</script>

<style>

</style>
