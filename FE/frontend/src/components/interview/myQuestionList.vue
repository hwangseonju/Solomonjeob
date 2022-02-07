<template>
  <nav v-if="!selected" id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="list-group ">
        <div>
          <li v-for="qnas in this.questionList" :key="qnas" class="list-group-item">
            <div class="box" >
              <input class="form-check-input"  type="radio" name="radio" @click="checkedQuestion(qnas.qnasId)">
                {{ qnas.qnasTitle }}
              <button type="button" @click="getQuestionAnswerList(qnas.qnasId)" class="btn-light">o</button>
            </div>
            <div v-if="qnas.qnasId == this.qnasId">
              <div v-for="qna in this.questionAnswerList" :key="qna" class="list-group-item">
                {{ qna.qnaAnswer }}
              </div>
            </div>
          </li>
        </div>    
      </ul>
    </div>
    <button  @click="submitChecked()">시작하기</button>
  </nav>
  <nav v-if="propSelected" id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="list-group ">
        <li v-for="sqna in this.selectedQuestionAnswerList" :key="sqna" class="list-group-item">
          <div class="box" >
            {{ sqna.qnaAnswer }}
          </div>
        </li>
      </ul>
    </div>
  </nav>
    

</template>

<script >
import { instance } from '@/api/index.js'             
export default {
  props:['selected'],
  data () {
    return{
      memberIdx: 0,
      qnasId: 0,
      questionList:[],
      questionAnswerList:[],
      selectedQuestionAnswerList:[],
      selectedQuestionIdx: null,
      propSelected: this.selected
      
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
        // this.qnasId = 
        this.questionList = res.data
        // this.getQuestionAnswerList()
        
      })
    },
    getQuestionAnswerList(qnasId) {
      if (this.questionAnswerList.length == 0){
      instance({
        method: 'get',
        url: '/api/qna/my/' + qnasId,
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.questionAnswerList = res.data
        this.qnasId = qnasId
        
      })
      }
      else {
        this.questionAnswerList=[]
      }
    },
    checkedQuestion(qnasId) {
      this.selectedQuestionIdx = qnasId
    },
    submitChecked() {
      instance({
      method: 'get',
      url: '/api/qna/my/' + this.selectedQuestionIdx,
      headers: this.getToken()
      })
      .then(res => {
        // console.log(res)
        this.selectedQuestionAnswerList = res.data
                
      })
      this.propSelected = true
      this.$emit('changeForm', this.propSelected)
    }
  },
  created() {
    this.getMemberIdx()
    this.getQuestionList()
    // this.getQuestionAnswerList()
    
  }
}
</script>

<style scoped>
.box {
  display: flex;
}
button {
  margin-left: auto;
}
</style>