<template>
  
  <div class="container-fluid">
    <div class="row">
      <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
        <div class="position-sticky pt-3">
          <ul class="nav flex-column">
            <button @click="insertQuestionList">+</button>
            <li v-for="question in this.questionList" :key="question" class="nav-item" >
              <div>          
                <input class="quesinput" type="text" :disabled="disabled != question.qnasId" v-model="question.qnasTitle" >
                
                <button type="button" @click="editQuestion(question.qnasId)">o</button>
                <button>x</button>
              </div> 
              
               
            </li>
          </ul>
        </div>
      </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">질문과 답변</h1>
          <div class="btn-toolbar mb-2 mb-md-0">
            <div class="btn-group me-2">
              <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
              <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
            </div>
            <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
              <span data-feather="calendar"></span>
              This week
            </button>
          </div>
        </div>

        <!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"> -->
          <ul>
            <li v-for="question in this.questionAnswerList" :key="question" >
              <!-- {{ question[0] }} -->
              <textarea placeholder="질문을 입력해주세요" name="" id="" cols="130" rows="10">
              </textarea>
              <!-- {{ question[1] }} -->
              <textarea placeholder="답변을 입력해주세요" name="" id="" cols="130" rows="10">
              </textarea>
            </li>
          </ul>
        <!-- </canvas> -->
        
      </main>
    </div>
  </div>
</template>

<script >
import { instance } from '@/api/index.js'             
export default {

  
  data () {
    return{
      insertQuestion:{
        qnasMemberId : 1,
        qnasTitle: '질문모음집',
      },
      question: {
        qnasTitle: ''
        },
      memberIdx: 0,
      disabled: 0,
      questionList:[],
      questiontitle: '',
      questionAnswerList:[],
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
    getMemberIdx() {
      this.memberIdx = localStorage.getItem('memberIdx')
      
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
        console.log(res)
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
    // editQues(event) {
    //   var updatedText = event.target.value
    //   this.qnasTitle = updatedText
    // },
    editQuestion(number) {
      if (this.disabled != number){
        this.disabled = number}
        
      else {
        instance({
        method: 'put',
        url: '/api/qnas/' + number  ,
        data: {qnasCode:number,
          qnasTitle: this.question.qnasTitle,
          qnasMemberId:this.memberIdx},
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.disabled = 0
        // this.$router.go()
      })
      .catch(err => {
        console.log(err)
        alert('실패')
      })        
        
      }

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