<template>
  <nav v-if="!selected" id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="list-group ">
        <div v-if="this.questionList.length===0">
          <li class="list-group-item">
            질문모음집이 없습니다.
            <br>
            내질문 모음집을 통해 면접 예상 질문을 등록해보세요 :)
          </li>
        </div>
        <div v-if="this.questionList.length!==0">
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
    <button  @click="submitChecked()">선택완료</button>
  </nav>
  <nav v-if="propSelected" id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="list-group ">
        <li v-for="(sqna,index) in this.selectedQuestionAnswerList" :key="(sqna,index)" class="list-group-item">
          <div class="box" >
            {{ sqna.qnaAnswer }}
            {{ questionStop[index] }}
          </div>
        </li>
        
      </ul>
    </div>
    <button v-if="questionFinish == 0" @click="start">start</button>
    <button v-if="0 < questionFinish & questionFinish < selectedQuestionAnswerList.length" @click="next">next</button>
    <button v-if="questionFinish == selectedQuestionAnswerList.length" @click="next">finish</button>
    <button v-if="questionFinish > selectedQuestionAnswerList.length" >나가기</button>
  </nav>
</template>

<script >
import { instance } from '@/api/index.js'
import {  mapState, mapMutations } from 'vuex'


export default {
  emits:[],
  props:{
    selected: {},
    formattedElapsedTime: {}

  },
    
  
  data () {
    return{
      memberIdx: 0,
      qnasId: 0,
      questionList:[],
      questionAnswerList:[],
      selectedQuestionAnswerList:[],
      selectedQuestionIdx: null,
      propSelected: this.selected,
      propFormattedElapsedTime: this.formattedElapsedTime,
      questionStop:[],
      questionFinish: 0,
      speakIdx: 0,
      voices:[],

      }
  },
  computed : {
      ...mapState(["isLogin", "signinIdx",]),

  },
  methods : {
      ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID",]),
    setVoiceList() {
      this.voices = window.speechSynthesis.getVoices();
      },

    speech(txt) {
      if (window.speechSynthesis.onvoiceschanged !== undefined) {
      window.speechSynthesis.onvoiceschanged = this.setVoiceList()
      }
      if(!window.speechSynthesis) {
        alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
        return;
      }
      // this.voices = window.speechSynthesis.getVoices()
      console.log(this.voices)
      var lang = 'ko-KR';
      var utterThis = new SpeechSynthesisUtterance(txt);
      utterThis.onend = function () {
        console.log('end');
      };
      utterThis.onerror = function(event) {
        console.log('error', event);
      };
      var voiceFound = false;
      for(var i = 0; i < this.voices.length ; i++) {
        if(this.voices[i].lang.indexOf(lang) >= 0 || this.voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
          utterThis.voice = this.voices[i];
          voiceFound = true;
        }
      }
      if(!voiceFound) {
        console.log(this.voices)
        alert('voice not found');
        return;
      }
      console.log(utterThis.voice)
      utterThis.lang = lang;
      utterThis.pitch = 1;
      utterThis.rate = 1; //속도
      window.speechSynthesis.speak(utterThis);
    },
    getToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        'jwt-auth-token': token
      }
      return config
    },
    getMemberIdx() {
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
      } else {
        this.questionAnswerList=[]
      }
    },
    checkedQuestion(qnasId) {
      this.selectedQuestionIdx = qnasId
    },
    submitChecked() {
      if(this.questionList.length!==0){
        if(this.selectedQuestionIdx===null){
          if(confirm("질문모음집을 선택하지않고 입장하시겠습니까?")){
            this.propSelected = true
            this.$emit('changeForm', this.propSelected)
          }else{
            return;
          }
        }else{
          instance({
            method: 'get',
            url: '/api/qna/my/' + this.selectedQuestionIdx,
            headers: this.getToken()
            })
            .then(res => {
              // console.log(res)
              this.selectedQuestionAnswerList = res.data
              console.log(this.selectedQuestionAnswerList)
                      
            })
            this.propSelected = true
            this.$emit('changeForm', this.propSelected)
            // this.firstSpeak()
        }
      }else {
        this.propSelected = true
        this.$emit('changeForm', this.propSelected)
      }
    },
    next() {
      this.questionStop.push(this.formattedElapsedTime);
      this.questionFinish += 1
      if (this.questionFinish  == this.selectedQuestionAnswerList.length + 1) {
        this.$emit('stopWatch')}
      // console.log(text)
      this.speak()
    },
    speak() {
      var speechidx = this.speakIdx
      var text = this.selectedQuestionAnswerList[speechidx].qnaAnswer
      console.log(text)
      this.speech(text)
      
      this.speakIdx += 1
      console.log(this.questionFinish)
    },
    start() {
      this.questionFinish += 1
      this.$emit('startWatch')
      this.speak()
    }
  },
  created() {
    this.getMemberIdx()
    this.getQuestionList()
    this.setVoiceList()
    // console.log(this.voices)
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