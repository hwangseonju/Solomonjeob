<template>
  <!-- <nav >
    <div class="position-sticky pt-3">
      <ul class="nav flex-column">
        <button type="button" class="btn btn-light" @click="insertQuestionList">질문추가</button>
        <li v-for="question in this.questionList" :key="question" class="nav-item row">
          <div @click="moveQuestionAnswerList(question.qnasId)">          
            <input class="quesinput" type="text" :disabled="disabled != question.qnasId" v-model="question.qnasTitle">
            <button  @click="editQuestion(question.qnasId, question.qnasTitle)">
              <span class="fa fa-edit"></span>
            </button>
            <button @click="removeQuestionList(question.qnasId)">
              <span class="fa fa-trash"></span>
            </button>
          </div> 
        </li>
      </ul>
    </div>
  </nav> -->
  <div class="container">
    <div class="row mb-3">
      <button type="button" class="btn btn-light listplus" @click="insertQuestionList">질문모음집추가</button>
    </div>
    <div class="col-12 li_style">
      <ul class="list-group">
        <question-answer
          v-for="question in questionList"
          class="question_style"
          :key="question"
          :qnasTitle="question.qnasTitle"
          @removeQuestionList="checkremoveQuestionList(question.qnasId)"
          @editQuestion="editQuestion(question.qnasId, $event)"
          @moveQuestionAnswerList="moveQuestionAnswerList(question.qnasId, question.qnasTitle)"
        />
      </ul>
    </div>
  </div>

</template>

<script >
import { instance } from '@/api/index.js'
import {  mapState, mapMutations } from 'vuex'
import QuestionAnswer from '@/components/question/QuestionAnswer.vue'
export default {

  components:{
    QuestionAnswer 
    },  
  data () {
    return{

      memberIdx: 0,
      
      questionList:[],

    }
  },
  computed : {
      ...mapState(["isLogin", "signinIdx", "jwtToken"]),

  },
  methods : {
      ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID", "SET_JWT_TOKEN"]),

    getMemberIdx() {
      this.memberIdx = this.signinIdx
    },
    
    getQuestionList() {
      const memberIdx = this.getMemberIdx

      instance({
        method: 'get',
        url: '/api/qnas/my/' + this.memberIdx,
        data: {qnasMemberId:memberIdx , qnasTitle:'질문모음집'},
        headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(res => {
        this.questionList = res.data
      })
    },
    insertQuestionList() {
      instance({
        method: 'post',
        url: '/api/qnas',
        data: {qnasMemberId:this.memberIdx , qnasTitle:'질문모음집'},
        headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(() => {
        this.getQuestionList()
      })
      .catch(err => {
        console.log(err)
        alert('실패')
      })
    },
    editQuestion(number,text) {
        instance({
        method: 'put',
        url: '/api/qnas/' + number  ,
        data: {qnasCode:number,
          qnasTitle: text,
          // qnasMemberId:this.memberIdx
          },
        headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(() => {
        this.getQuestionList()

      })
      .catch(err => {
        console.log(err)
        alert('실패')
        })        
     
    },

    removeQuestionList(number) {
      instance({
      method: 'delete',
      url: 'api/qnas/' + number,
      headers: {'jwt-auth-token': this.jwtToken}
      })
      .then(() => {
        // console.log(res)
        // this.$router.go()
        this.getQuestionList()
      })
    },
    checkremoveQuestionList(number) {
      const check = confirm('삭제하시겠습니까?')
      if (check) {
        this.removeQuestionList(number);
      } else {
        return false;
      }
    },
    moveQuestionAnswerList(qnasId, qnasTitle) {
      this.$router.push({
        name: 'QuestionAnswer',
        params: {qnasId:qnasId, qnasTitle:qnasTitle}
      })
    }
 

  },
  created() {
    this.getMemberIdx()
    this.getQuestionList()
    
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

@font-face {
    font-family: 'KoPubDotumMedium';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'YanoljaYacheR';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/YanoljaYacheR.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
/* .quesinput{
  width: 70%;
} */
.listplus {
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: bolder;
  

}
.listplus:hover {
  color: rgb(75, 137, 220);
}
.question_style {
  font-family: 'Noto Sans KR', sans-serif;
}

.question_style:hover {
  background-color: rgb(75, 137, 220);
  cursor: pointer;
  color: white;
  font-size: 110%;
}
.question_style:focus {
  background-color: rgb(75, 137, 220);
  color: white;
  font-size: 110%;
}
.li_style {
  padding-right: 8%;
}
</style>