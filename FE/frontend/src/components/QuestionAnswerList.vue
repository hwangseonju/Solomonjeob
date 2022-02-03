<template>
  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      <h1 class="h2">질문과 답변</h1>
      <div class="btn-toolbar mb-2 mb-md-0">
        <div class="btn-group me-2">
          <button @click="insertQuestionAnswer" type="button" class="btn btn-sm btn-outline-secondary">질문,답변추가</button>
        </div>
      </div>
    </div>

    <!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"> -->
      <ul>
        <li v-for="qna in this.questionAnswerList" :key="qna" class="nav-item" >
          <div>          
            질문:<input class="quesinput" type="text" :disabled="disabled != qna.qnaId" v-model="qna.qnaAnswer">
            답변:<textarea :disabled="disabled != qna.qnaId" v-model="qna.qnaContent">
            </textarea>
            <button  @click="editQuestionAnswer(qna.qnaId, qna.qnaAnswer, qna.qnaContent)">o</button>
            <button @click="removeQuestionAnswerList(qna.qnaId)">x</button>
          </div> 
        </li>
        <!-- <li v-for="question in this.questionAnswerList" :key="question" >
          <textarea placeholder="질문을 입력해주세요" name="" id="" cols="130" rows="10">
          </textarea>
          <textarea placeholder="답변을 입력해주세요" name="" id="" cols="130" rows="10">
          </textarea>
        </li> -->
      </ul>
    <!-- </canvas> -->
    
  </main>

</template>

<script>
import { instance } from '@/api/index.js'    

export default {

  data() {
    return{
      qnasId : this.$route.params.qnasId,
      questionAnswerList: [],
      disabled: 0
    }
  },
    methods : {
    getToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        'jwt-auth-token': token
      }
      return config
    },
    // getMemberIdx() {
    //   this.memberIdx = localStorage.getItem('memberIdx')
      
    // },
    getQuestionAnswerList() {
      instance({
        method: 'get',
        url: '/api/qna/my/' + this.$route.params.qnasId,
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.questionAnswerList = res.data
        
      })
    },
    insertQuestionAnswer() {
      instance({
        method: 'post',
        url: '/api/qna/',
        data: {qnaAnswer:'질문', qnacontent:'답변', qnasCode:this.qnasId},
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.$router.go()
      })
      .catch(err => {
        console.log(err)
        alert('실패')
      })
    },

    editQuestionAnswer(qnaId,qnaAnswer, qnaContent) {
      if (this.disabled != qnaId){
        this.disabled = qnaId}
        
      else {
        instance({
        method: 'put',
        url: '/api/qna/' + qnaId  ,
        data: {qnaId: qnaId,
          qnaAnswer: qnaAnswer,
          qnaContent: qnaContent
          // qnasMemberId:this.memberIdx
          },
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.disabled = 0
        this.$router.go()
      })
      .catch(err => {
        console.log(err)
        alert('실패')
        })        
      }
    },
    removeQuestionAnswerList(qnaId) {
      instance({
      method: 'delete',
      url: 'api/qna/' + qnaId,
      headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.$router.go()
      })
    },
    moveQuestionAnswerList(qnasid) {
      this.$router.push({
        name: 'QuestionAnswer',
        params: {questionid:qnasid}
      })

    }
 

  },
  created() {
    this.getQuestionAnswerList()
    // console.log(this.$route.params.qnasId)
    
  }
  

}
</script>

<style>

</style>