<template>
  <main >
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      <h1 class="h2">질문과 답변</h1>
      <div class="btn-toolbar mb-2 mb-md-0">
        <div class="btn-group me-2">
          <button @click="insertQuestionAnswer" type="button" class="btn btn-sm btn-outline-secondary">질문,답변추가</button>
        </div>
      </div>
    </div>
      <div>
        <ul class="d-flex">
          <li class="item" v-for="(qna,index) in this.questionAnswerList" :key="(qna,index)"  >
            <button @click="moveQuestionAnswerDetail(qna.qnaAnswer,qna.qnaContent, qna.qnaId)">{{ index + 1 }}</button>
          </li>
        </ul>
      </div>
    <!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"> -->
      <question-answer-detail
      :qnaId="qnaId"
      :qnaAnswer="qnaAnswer"
      :qnaContent="qnaContent"
      @editQuestionAnswer="editQuestionAnswer($event)"
      @removeQuestionAnswerList="removeQuestionAnswerList()"
      />
    <!-- </canvas> -->
  </main>

</template>

<script>
import { instance } from '@/api/index.js'   
import {  mapState } from 'vuex' 
import QuestionAnswerDetail from '@/components/question/QuestionAnswerDetail.vue'
export default {
  components: {
    QuestionAnswerDetail
  },
  data() {
    return{
      qnasId : this.$route.params.qnasId,
      questionAnswerList: [],
      qnaAnswer: '',
      qnaContent: '',
      qnaId: ''
  
    }
  },
    computed : {
      ...mapState(["isLogin", "signinIdx", "jwtToken"]),

  },
  methods : {
    getQuestionAnswerList() {
      instance({
        method: 'get',
        url: '/api/qna/my/' + this.$route.params.qnasId,
        headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(res => {
        // console.log(res)
        this.questionAnswerList = res.data
        
      })
    },
    insertQuestionAnswer() {
      instance({
        method: 'post',
        url: '/api/qna/',
        data: {qnaAnswer:'질문을 작성해주세요', qnaContent:'답변을 작성해주세요', qnasCode:this.qnasId},
        headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(() => {
        // console.log(res)
        // this.$router.go()
        this.getQuestionAnswerList()

      })
      .catch(err => {
        console.log(err)
        alert('실패')
      })
    },
    editQuestionAnswer(newvalue) {
      console.log(newvalue[0])
      console.log(newvalue[1])
      instance({
        method: 'put',
        url: '/api/qna/' + this.qnaId  ,
        data: {qnaId: this.qnaId,
          qnaAnswer: newvalue[0],
          qnaContent: newvalue[1]
          // qnasMemberId:this.memberIdx
          },
          headers: {'jwt-auth-token': this.jwtToken}
        })
        .then((res) => {
          console.log(res)
          this.qnaAnswer = res.data.qnaAnswer
          this.qnaContent =res.data.qnaContent
        })
        .catch(err => {
          console.log(err)
          alert('실패')
          })        
    },

    // editQuestionAnswer(qnaId,qnaAnswer, qnaContent) {
    //   if (this.disabled != qnaId){
    //     this.disabled = qnaId}
        
    //   else {
    //     instance({
    //     method: 'put',
    //     url: '/api/qna/' + qnaId  ,
    //     data: {qnaId: qnaId,
    //       qnaAnswer: qnaAnswer,
    //       qnaContent: qnaContent
    //       // qnasMemberId:this.memberIdx
    //       },
    //     headers: {'jwt-auth-token': this.jwtToken}
    //   })
    //   .then(() => {
    //     // console.log(res)
    //     this.disabled = 0
    //     // this.$router.go()
    //     this.getQuestionAnswerList()

    //   })
    //   .catch(err => {
    //     console.log(err)
    //     alert('실패')
    //     })        
    //   }
    // },
    removeQuestionAnswerList() {
      instance({
      method: 'delete',
      url: 'api/qna/' + this.qnaId,
      headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(() => {
        // console.log(res)
        this.$router.go()
        // this.getQuestionAnswerList()


      })
    },
    moveQuestionAnswerDetail(qnaAnswer, qnaContent, qnaId) {
      this.qnaAnswer = qnaAnswer
      this.qnaContent = qnaContent
      this.qnaId = qnaId
      // console.log(this.qnaAnswer)
      // console.log(this.qnaContent)
      // console.log(this.qnaId)
    }
 

  },
  created() {
    this.getQuestionAnswerList()
    // console.log(this.$route.params.qnasId)
    
  }
  

}
</script>

<style scoped>
ul {
  list-style: none;
}

</style>