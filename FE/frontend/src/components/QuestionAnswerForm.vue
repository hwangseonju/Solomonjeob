<template>
  <div class="container container_style">
    <div class="row mb-3">
      <p class="p_style">질문모음집</p>
      <div class="btn_place">
        <button type="button" class="btn btn-light listplus" @click="insertQuestionList"><i class="fas fa-plus"></i></button>
      </div>

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
import {  mapState, mapMutations, mapActions } from 'vuex'
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
      ...mapState(["isLogin", "signinIdx", "jwtToken", 'memberIdx', 'questionList']),

  },
  methods : {
    ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID", "SET_JWT_TOKEN"]),
    ...mapActions(['getQuestionList', 'insertQuestionList', 'editQuestion', 'removeQuestionList']),
    getMemberIdx() {
      this.memberIdx = this.signinIdx
    },
    
    getQuestionList() {
      const memberIdx = this.getMemberIdx

      instance({
        method: 'get',
        url: '/api/qnas/my/' + this.memberIdx,
        data: {qnasMemberId:memberIdx , qnasTitle:'제목'},
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
        data: {qnasMemberId:this.memberIdx , qnasTitle:'제목'},
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
        this.getQuestionList()
        this.$router.go()

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
    },
    
 

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
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
/* .quesinput{
  width: 70%;
} */
.p_style {
  font-family: 'Pretendard-Regular';
  font-weight: bolder;
  text-align: center;
  font-size: 120%;

}
.listplus {
  font-family: 'Pretendard-Regular';
  font-weight: bolder;
  width: 100%;
  

}
.listplus:hover {
  color: rgb(75, 137, 220);
}

.question_style {
  font-family: 'Pretendard-Regular';
}

.question_style:hover {
  background-color: rgb(90, 156, 241);
  /* background-color: #1572A1; */
  cursor: pointer;
  color: white;
}
.question_style:focus {
  background-color: rgb(90, 156, 241);
  color: white;
  font-size: 110%;
}
.li_style {
  padding-right: 8%;
}
.container_style {
  border-radius: 5px 5px;
  padding-bottom: 10%;
  padding-top: 10%;
  background-color: #EEEEEE;
  /* background-color: lightgrey; */
  /* background-color: #9AD0EC; */

}



</style>