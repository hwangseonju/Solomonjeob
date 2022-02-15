<template>
  <div v-if="qnaAnswer">
    <button
      @click="startEditing()"
      class="btn-outline-primary border-0 ml-2"
    >
      <span class="fa fa-edit"></span>
    </button>
    <button @click="$emit('removeQuestionAnswerList')" class="btn-outline-danger border-0">
      <span class="fa fa-trash"></span>
    </button>
    <li class="align-items-center list-group-item">
      질문:
      <div
        class=" border-1  shadow-none"
        v-if="!isEditing"
      >
        <span>{{ qnaAnswer }}</span>
      </div>
      <form v-else class="flex-grow-1" @submit.prevent="finishEditing()">
        <input
          type="text"
          class="form-control"
          v-model="newAnswer"
          @blur="finishEditing()"
          ref="newQues"
        />
      </form>
      <hr>
      
      답변:
      <div
        class="box text-left "
        v-if="!isEditing"
        
      >
        <span>{{ qnaContent }}</span>
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
.box {
  
  min-height: 300px;
  
}
</style>