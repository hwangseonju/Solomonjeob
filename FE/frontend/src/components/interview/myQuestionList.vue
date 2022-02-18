<template>
  <div v-if="!selected" class="col-12">
    <h3 class="text-center">질문 모음집</h3>
    <div style="height: 472px; overflow: auto; background-color:#dde1eb;">
      <div class="list-group qnasList" v-for="qnas in this.questionList" :key="qnas">
        <div class="btn-group">
          <button type="button" 
            :class="{'list-group-item list-group-item-action': selectedQuestionIdx!==qnas.qnasId
                    ,'list-group-item list-group-item-action active': selectedQuestionIdx===qnas.qnasId}" aria-current="true" @click="checkedQuestion(qnas.qnasId)">
            <i :class="{'fas fa-caret-down':qnasId===qnas.qnasId ,'fas fa-caret-right':qnasId!==qnas.qnasId}"></i> <b id="qnaTitle">{{qnas.qnasTitle}}</b>
          </button>
        </div>
        <ul class="list-group" v-if="qnas.qnasId == this.qnasId">
          <li class="list-group-item" v-for="qna in this.questionAnswerList" :key="qna">
            &nbsp;&nbsp;{{qna.qnaAnswer}}
          </li>
        </ul>
      </div>
    </div>
    <br>
    <div v-if="isLogin" class="text-center">
      <div class="d-flex justify-content-center">
        <button class="selectbtn button button1 col-10" type="button" @click="submitChecked()">
          선택 완료
        </button>
      </div>
    </div>  
  </div>

  <div v-if="selected" id="sidebarMenu" class="col-6 col-md-3" >
    <h3 class="text-center">질문</h3>
    <div style="height: 600px; overflow: auto; background-color:#dde1eb;">
      <ul class="list-group">
        <li :class="{'list-group-item':questionFinish-1!==index,'list-group-item active':questionFinish-1===index}" aria-current="true" v-for="(sqna,index) in this.selectedlist" :key="(sqna,index)" >
            {{ sqna.qnaAnswer }}
            <div id="time">
              {{ questionStop[index]}}
            </div>
        </li>
      </ul>
    </div>
    <br>
    <div v-if="isLogin" class="text-center">
        <button v-if="questionFinish == 0" @click="start" class="selectbtn button button1 col-10">
          연습 시작
        </button>
        <button v-if="0 < questionFinish & questionFinish < selectedlist.length" @click="next" class="selectbtn button button2 col-10">
          다음 질문 <img src="@/assets/next.png" style="width:25px;height:25px;">
        </button>
        <button v-if="questionFinish == selectedlist.length" @click="next" class="selectbtn button button1 col-10">
          마지막 질문!
        </button>
    </div>
    
  </div>
</template>

<script >
import { instance } from '@/api/index.js'
import {  mapState, mapMutations } from 'vuex'


export default {
  emits:[],
  props:{
    formattedElapsedTime: {}
  },
    
  
  data () {
    return{
      memberIdx: 0,
      qnasId: 0,
      questionList:[],
      questionAnswerList:[],
      //selectedQuestionAnswerList:[],
      selectedQuestionIdx: null,
      propFormattedElapsedTime: this.formattedElapsedTime,
      questionStop:[],
      questionFinish: 0,
      speakIdx: 0,
      voices:[],

      }
  },
  computed : {
      ...mapState(["isLogin", "signinIdx", "selected", "selectedlist"]),

  },
  methods : {
      ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID", "jwtToken","SET_SELECTED_LIST"]),
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
        console.log(res)
        // this.qnasId = 
        this.questionList = res.data
        // this.getQuestionAnswerList()
        
      })
    },
    getQuestionAnswerList(qnasId) {
      if (this.qnasId!==qnasId){
        instance({
          method: 'get',
          url: '/api/qna/my/' + qnasId,
          headers: {'jwt-auth-token': this.jwtToken}
        })
        .then(res => {
          console.log(res)
          this.questionAnswerList = res.data
          this.qnasId = qnasId
          
        })
      } else {
         this.questionAnswerList=[];
         this.qnasId = 0;
      }
    },
    checkedQuestion(qnasId) {
      this.selectedQuestionIdx = qnasId
      this.getQuestionAnswerList(qnasId);
    },
    submitChecked() {
      if(this.questionList.length!==0){
        if(this.selectedQuestionIdx===null){
          if(confirm("질문모음집을 선택하지않고 입장하시겠습니까?")){
            this.$emit('changeForm')
          }else{
            return;
          }
        }else{
          instance({
            method: 'get',
            url: '/api/qna/my/' + this.selectedQuestionIdx,
            headers: {'jwt-auth-token': this.jwtToken}
            })
            .then(res => {
              // console.log(res)
              this.$store.commit("SET_SELECTED_LIST", res.data)
            })
            this.propSelected = true
            this.$emit('changeForm', this.propSelected)
            // this.firstSpeak()
        }
      }else {
        this.$emit('changeForm')
      }
    },
    next() {
      this.questionStop.push(this.formattedElapsedTime);
      this.questionFinish += 1
      if (this.questionFinish  == this.selectedlist.length + 1) {
        this.$emit('stopWatch')
      }
      // console.log(text)
      this.speak()
    },
    speak() {
      var speechidx = this.speakIdx
      var text = this.selectedlist[speechidx].qnaAnswer
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
.list-group-item.active {
  background-color: #b8d1ff;
  border-color: #b8d1ff;
  color: black;
}
#qnaTitle {
  font-size: 16px;
}
.w-btn {
    position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}
.w-btn-blue {
    background-color: #6aafe6;
    color: white;
}
.button {
    background-color: #5fbae8; /* Blue */
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
    border-radius: 15px;
}
.button1 {
    background-color: #5fbae8;
    color: white;
    padding: auto;
    font-size: 18px;
}
.button1:hover {
    background-color: white; 
    color: black; 
    border: 2px solid #5fbae8;
}
.button2 {
    background-color: white; 
    color: black; 
    border: 2px solid #5fbae8;
}
.button2:hover {
    background-color: #5fbae8;
    color: white;
    padding: auto;
    font-size: 18px;
}
#time {
  display: inline;
  float: right;
}
</style>