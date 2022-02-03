<template>

  <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
      <ul class="list-group">
        <li v-for="qnas in this.questionList" :key="qnas" class="list-group-item">
          <input class="form-check-input me-1" type="checkbox" value="" aria-label="...">
          {{ qnas.qnasTitle }}
        </li>
      </ul>


    </div>
  </nav>
</template>

<script >
import { instance } from '@/api/index.js'             
export default {

  
  data () {
    return{
      memberIdx: 0,
      disabled: 0,
      questionList:[],
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
        // console.log(res)
        this.questionList = res.data
        
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

</style>