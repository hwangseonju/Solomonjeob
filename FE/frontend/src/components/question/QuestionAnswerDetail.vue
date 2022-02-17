<template>
  <div v-if="qnaId">

    <li class="align-items-center list-group-item form_style">
      
      <div class=" border-1  shadow-none question_answer" v-if="!isEditing">
        <span class="title_style">질문 : {{ qnaAnswer }}</span>
      </div>
      <form v-else class="flex-grow-1" @submit.prevent="finishEditing()">
        <input
          type="text"
          class="form-control"
          v-model="newAnswer"
          @enter="finishEditing()"
          ref="newQues"
        />
      </form>
      <hr>
      
    
      <div
        class="box text-left "
        v-if="!isEditing"
        
      >
        <span class="content_style">답변 : {{ qnaContent }}</span>
      </div>
      <form v-else class=" flex-grow-1" @submit.prevent="finishEditing()">
        <textarea   type="textarea"
          class="box form-control"
          v-model="newContent"
          @blur="finishEditing()"
          ref="newQues">
       
        </textarea>
      </form>
    </li>
    <br>
    <div align="right">
      <button  @click="$emit('removeQuestionAnswerList')" class="remove_button btn-sm">
        삭제
      </button>
      <button @click="startEditing()" class="edit_button btn-sm">
        수정
      </button>

    </div>
  </div>
</template>

<script>
import { instance } from '@/api/index.js'  
import { mapState } from 'vuex';
export default {
  data() {
    return {
      isEditing: false,
      newAnswer: "",
      newContent: "",
      // questionId : this.$route.params.questionId,
      // qnaId : this.$route.params.qnasId,
      // qnaAnswer :  this.$route.params.qnaAnswer,
      // qnaContent :  this.$route.params.qnaContent
    };
  },
  props: {
    qnaId: {},
    qnaAnswer: {},
    qnaContent :{}

  },
  computed : {
    ...mapState(["isLogin", "signinIdx", "jwtToken"]),

  },
  methods : {
    startEditing() {
      if (this.isEditing) {
        this.finishEditing();
      } else {
        this.newAnswer = this.qnaAnswer;
        this.newContent = this.qnaContent
        this.isEditing = true;
        // this.$nextTick(() => this.$refs.newQues.focus());
      }
    },
    finishEditing() {
      this.isEditing = false;
      this.$emit("editQuestionAnswer",[this.newAnswer,this.newContent]);

      // console.log(1)
    },

    // finishEditing() {
    //   this.isEditing = false;
    //   this.editQuestionAnswer()
    //   // console.log(1)
    // },
    editQuestionAnswer() {
      instance({
        method: 'put',
        url: '/api/qna/' + this.qnaId  ,
        data: {qnaId: this.qnaId,
          qnaAnswer: this.newAnswer,
          qnaContent: this.newContent
          // qnasMemberId:this.memberIdx
          },
          headers: {'jwt-auth-token': this.jwtToken}
        })
        .then(() => {
          this.$router.go()
        })
        .catch(err => {
          console.log(err)
          alert('실패')
          })        
    },
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
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
.title_style {
  font-family: 'Pretendard-Regular';

}
.content_style {
  font-family: 'Pretendard-Regular';
}
.box {
  
  min-height: 300px;
  
}
.edit_button {
  border: 1px solid;
  background-color: grey;
  color: white;
  font-family: 'Noto Sans KR', sans-serif;

}
.edit_button:hover {
  background-color: rgb(75, 137, 220);
}
.remove_button {
  border: 1px solid;
  background-color: grey;
  color: white;
  font-family: 'Noto Sans KR', sans-serif;

}
.remove_button:hover {
  background-color: red;
}

</style>