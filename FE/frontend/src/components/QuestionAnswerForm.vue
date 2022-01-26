<template>
  
  <div class="container-fluid">
    <div class="row">
      <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
        <div class="position-sticky pt-3">
          <ul class="nav flex-column">
            <li v-for="index in this.questionlist" :key="index" class="nav-item">
              <div>        
                <!-- <input type="text"  :disabled="validated == 1" id="quescol" value="{{ index }}">
                <button >o</button>
                <button >x</button> -->

                <div id="app">
                
                <input type="text" :disabled="disabled == 1" value="{{ index }}">
                <button @click="disabled = (disabled + 1) % 2">o</button>
                <button>x</button>
                </div>
              </div> 
              
            </li>
          </ul>

          <!-- <ul class="nav flex-column ">
            <li class="nav-item">
              <a class="nav-link" href="#">
                <span data-feather="file-text"></span>
                Current month
              </a>
            </li>
          </ul> -->
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
            <li v-for="question in this.quesanswerlist" :key="question" >
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
             
export default {
  
  data () {
    return{
      questionlist: ['질문모음집1', '질문모음집2', '질문모음집3','질문모음집4' ],
      quesanswerlist : [
        ['질문1','답변1'],
        ['질문2','답변2'],
        ['질문3','답변3'],
        ['질문4','답변4'],
        ['질문5','답변5'],
        ['질문6','답변6'],
        ['질문7','답변7'],
        ['질문8', '답변8']        
      ],
      edit: {
        text:""
      },
      disabled: 0
    }
  },
  methods : {
    editQuestion(index) {
      this.edit.text = index
    },

  }
}
</script>

<style scoped>

</style>