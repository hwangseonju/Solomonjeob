<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="signup" class="form">
          <div>
              <input id="useremail" placeholder="Email" type="text" v-model="credentials.memberId" @keyup="idchk"/>
          </div>

          <div>
              <input id="password" placeholder="Password" type="password" v-model="credentials.memberPwd" />    
          </div>
          <div>
              <input id="passwordCheck" placeholder="PasswordConfirmation" type="password" v-model="credentials.passwordConfirmation" @keyup.enter="signup"/>    
          </div>
        <button :disabled="(credentials.memberPwd !== credentials.passwordConfirmation)" class="btn" @click.prevent="signup">회원 가입</button>
        <p class="password_alert" v-if="credentials.passwordConfirmation && (credentials.memberPwd !== credentials.passwordConfirmation)">비밀번호가 일치하지 않습니다.</p>
      </form>
    </div>
  </div>

</template>

<script>
import { instance } from '@/api/index.js'

export default {
  name: 'signupForm',
  data() {
      return {
        credentials : {
          memberId: '',
          memberPwd: '',
          passwordConfirmation: '',
        },
        idresult: "",
        resultKey: "FAIL",

      }
    },
  methods: {
    async signup() {
      let msg = "";
      let err = true;

      if (!this.credentials.memberId) {
        msg = "ID를 입력해주세요";
        err = false;
      } else if (!this.credentials.memberPwd) {
        msg = "PW를 입력해주세요";
        err = false;
      } else if (!this.credentials.passwordConfirmation) {
        msg = "PW 확인을 위해 한번 더 입력해주세요";
        err = false;
      }
    if (!err) alert(msg);
    else {
      await instance({
        method: "post",
        url: "/api/members/signup/member",  // API 참고
        data: this.credentials,
      }).then((data) => {
        console.log(data);
      });
      this.$router.push('Home');
      alert("회원가입에 성공하였습니다. 환영합니다 :)");
      }
    },
    idchk() {
      if (this.credentials.memberId) {
        instance({
          method: "get",
          url: "/api/members/check/" + this.credentials.memberId,
        }).then((data) => {
          if (data.data == "success") {
            this.idresult = "사용가능한 아이디 입니다";
          } else {
            this.idresult = "이미 사용중인 아이디입니다..";
          }
        });
      }
    },
    
  }
}    
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

.contents {
  max-width: 1020px;
  margin: 0 auto;
  padding: 0 5px;
  width: 100%;

}
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
  max-width: 500px;
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
  width: 100%;
  border: 1px solid #dae1e7;
  box-shadow: 0 2px 4px 0 rgba(0,0,0,.1);
  padding: 0.5rem 0.75rem;
  margin-bottom: 1rem;
}
/* .btn {
  font-size: 18px;
  padding: 15px 25px;
  font-weight: 700;
  font-family: 'Jua', sans-serif;
  
  border: 3px solid #6667AB;
  color: #6e70ef;
  cursor: pointer;
  background-color: transparent;
  position: relative;
  transition: all 0.4s;
  overflow: hidden;
}
.btn:focus {
  outline: none;
}
.btn::before {
  content: "";
  position: absolute;
  height: 100%;
  width: 100%;
  border-radius: 1px;
  background-image: linear-gradient(to right, #6667AB, #6e70ef);
  top: 100%;
  left: 0;
  transition: all 0.4s;
  z-index: -1;
}
.btn:hover::before {
  transform: translateY(-100%);
}
.btn:hover {
  color: white;
} */
.btn {
  font-weight: bolder;
  color: slateblue;
  width: 100%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}

.btn :hover::before {
  transform: translateY(-100%);
}
.btn:hover {
  font-weight: bolder;
  background-color: slateblue;
  color: white;
}
.password_alert {
  color: red;
  font-family: 'Noto Sans KR', sans-serif;
  text-align: center;
}
</style>