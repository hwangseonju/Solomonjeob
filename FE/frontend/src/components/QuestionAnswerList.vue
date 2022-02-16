<template>
  <main >
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      <h1 class="h2_style">{{ this.qnasTitle }}</h1>
      <!-- <div class="btn-toolbar mb-2 mb-md-0">
        <div class="btn-group me-2">
          <button @click="insertQuestionAnswer" type="button" class="btn btn-sm plus_style">추가</button>
        </div>
      </div> -->
    </div>
      <div class="flex-container">
        <ul class="d-flex d-flex_style">
          <li class="item" v-for="(qna,index) in this.questionAnswerList" :key="(qna,index)"  >
            <button class="button_style" @click="moveQuestionAnswerDetail(qna.qnaAnswer,qna.qnaContent, qna.qnaId)">{{ index + 1 }}</button>
          </li>
        </ul>
        <div>
          <div class="btn-group me-2">
            <button @click="insertQuestionAnswer" type="button" class="btn btn-sm plus_style"><i class="fas fa-plus"></i></button>
          </div>
        </div>
      </div>
      <br>
    <!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"> -->
      <question-answer-detail
      :qnaId="qnaId"
      :qnaAnswer="qnaAnswer"
      :qnaContent="qnaContent"
      @editQuestionAnswer="editQuestionAnswer($event)"
      @removeQuestionAnswerList="checkremove()"
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
      qnasTitle: this.$route.params.qnasTitle,
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
    checkremove() {
        const check = confirm('삭제하시겠습니까?')
        if (check) {
          this.removeQuestionAnswerList();
        } else {
          return false;
        }
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
ul {
  list-style: none;
}
.flex-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
.h2_style {
  font-family: 'Noto Sans KR', sans-serif;
}
.plus_style {
  border: 0.1px solid;
  /* background-color: #F8F9FA; */
  font-family: 'Noto Sans KR', sans-serif;
  background-color: grey;
  color: white;
  padding: 0%;
  width: 31px;
  height: 29px;

}
.plus_style:hover {
  background-color: rgb(75, 137, 220);
  color: white;
}
.plus_style:hover::after {
  background-color: rgb(75, 137, 220);
  color: white;

}
.d-flex_style {
  padding-left: 0%;
  flex-flow: row wrap;
}
.button_style {
  background-color: white;
  border: 1px solid grey;
  min-width: 32.25px;
  max-width: 100%;
  font-family: 'Noto Sans KR', sans-serif;
}
.button_style:hover {
  background-color: rgb(75, 137, 220);
  color: white;
}
.button_style:focus {
  background-color: rgb(75, 137, 220);
  color: white;
}


</style>