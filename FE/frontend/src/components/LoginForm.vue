<template>
  <h1 class="h1_style">로그인</h1>

  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form class="form">
        <div>
          <input id="useremail" type="text" placeholder="이메일" v-model="credentials.memberId">
        </div>
        <p class="confirm_style" v-show="!isUseremailValid && credentials.memberId" >이메일 형식이 올바르지 않습니다.</p>
        
        <div> 
          <input id="password" type="password" placeholder="비밀번호" v-model="credentials.memberPwd">
        </div>
        <div>
          <button :disabled="!isUseremailValid || !credentials.memberPwd" @click.prevent="login" class="btn">
          로그인
          </button>
        </div>
        <br>
      </form>
        <div class="snsbtn">
          <a href="https://kauth.kakao.com/oauth/authorize?client_id=e35ccc21d2cf1759f526eef14ea4b921&redirect_uri=http://localhost:8080/api/members/login/oauth_kakao&response_type=code">
            <img  src="@/assets/kakao.png">
          </a>
        </div>
    </div>
  </div>

</template>

<script>
// import { instance } from '@/api/index.js'
import { validateEmail } from '@/utils/validation'
import { mapActions, mapState, mapMutations } from 'vuex'
// import axios from 'axios'
export default {

  data() {
    return {
      credentials: {
        memberId: null,
        memberPwd: null,
      },
      signinIdx: 0
    }
  },
  computed: {
    isUseremailValid() {
      return validateEmail(this.credentials.memberId)
    },
    ...mapState(["isLogin", "signinIdx"]),
  },
  methods: {
      ...mapMutations(["SET_IS_LOGIN", "SET_GET_USER_ID", ]),
      ...mapActions(["userConfirm"]),
      async login() {
        await this.userConfirm(this.credentials);
        if (this.isLogin) {
          alert('환영합니다')
          this.$router.push('Home')
        } else {
          alert( "회원정보가 올바르지 않습니다")
      
      }      

    }
  },


}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;700&display=swap');

.h1_style{
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 700;
  padding-top: 5%;

}
.confirm_style {
  color: red;
}
.snsbtn{
  text-align: center;
}
.contents {
  max-width: 1020px;
  margin: 0 auto;
  padding: 0 5px;
  width: 100%;  
}
/*--- HEADER ---*/
.page-header {
  font-size: 45px;
	font-weight: 600;
	color: #456155;
	padding: 30px 5px 15px;
}

/*--- FORM ---*/
.form-container {
  display: flex;
  align-items: center;
}
.form-wrapper {
  background: white;
  -webkit-box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  border-radius: 3px;
  padding: 15px 15px;
}
.form-wrapper.form-wrapper-sm {
  max-width: 440px;
  margin: 40px auto;
}
.form-wrapper-sm .page-header {
  padding: 0px 0 20px;
}
.form {
	width: 460px;
	height: 100%;
}


.form input,
.form textarea {
  font-family: inherit;
  font-size: 100%;
  width: 89%;
  border: 1px solid #dae1e7;
  box-shadow: 0 2px 4px 0 rgba(0,0,0,.1);
  padding: 0.5rem 0.75rem;
  margin-bottom: 1rem;
  border-radius: 2px 2px 2px 2px;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 100;
}
.form input:focus {
  outline: 2px solid rgb(75, 137, 220);
}
.router {
  color: blueviolet;
}
.btn {
  font-weight: bolder;
  color: rgb(75, 137, 220);
  width: 89%;
  color: white;
  box-sizing: border-box;
  background-color: black;
  font-family: 'Noto Sans KR', sans-serif;

}
.btn:hover {
  font-weight: bolder;
  background-color: rgb(75, 137, 220);
  color: white;
}


</style>