<template>
  <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="nav flex-column">
        <button @click="insertQuestionList">+</button>
        <li v-for="question in this.questionList" :key="question" class="nav-item" >
          <div>          
            <input @click="moveQuestionAnswerList(question.qnasId)" class="quesinput" type="text" :disabled="disabled != question.qnasId" v-model="question.qnasTitle">
            
            <button  @click="editQuestion(question.qnasId, question.qnasTitle)">o</button>
            <button @click="removeQuestionList(question.qnasId)">x</button>
          </div> 
        </li>
      </ul>
    </div>
  </nav>


</template>

<script >
import { instance } from '@/api/index.js'
import {  mapState, mapMutations } from 'vuex'

export default {

  
  data () {
    return{

      memberIdx: 0,
      disabled: 0,
      questionList:[],

    }
  },
  computed : {
      ...mapState(["isLogin", "signinIdx"]),

  },
  methods : {
      ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID"]),

    getToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        'jwt-auth-token': token
      }
      return config
    },
    getMemberIdx() {
      // this.memberIdx = localStorage.getItem('memberIdx')
      this.memberIdx = this.signinIdx

      
    },
     getQuestionList() {
      const memberIdx = this.getMemberIdx
      instance({
        method: 'get',
        url: '/api/qnas/my/' + this.memberIdx,
        data: {qnasMemberId:memberIdx , qnasTitle:'질문모음집'},
        headers: this.getToken()
      })
      .then(res => {
        // console.log(res)
        this.questionList = res.data
        
      })
    },
    insertQuestionList() {
      instance({
        method: 'post',
        url: '/api/qnas',
        data: {qnasMemberId:this.memberIdx , qnasTitle:'질문모음집'},
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

    editQuestion(number,text) {
      if (this.disabled != number){
        this.disabled = number}
        
      else {
        instance({
        method: 'put',
        url: '/api/qnas/' + number  ,
        data: {qnasCode:number,
          qnasTitle: text,
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
    removeQuestionList(number) {
      instance({
      method: 'delete',
      url: 'api/qnas/' + number,
      headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.$router.go()
      })
    },
    moveQuestionAnswerList(qnasId) {
      this.$router.push({
        name: 'QuestionAnswer',
        params: {qnasId:qnasId}
      })
      console.log('hi')
    }
 

  },
  created() {
    this.getMemberIdx()
    this.getQuestionList()
    
  }
}
</script>

<style scoped>
.quesinput{
  width: 75%;
}
</style>