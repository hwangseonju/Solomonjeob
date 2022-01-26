<template>
  <form @submit.prevent="submitForm">
      <div>
          <input id="useremail" placeholder="email" type="text" v-model="credentials.useremail" />    
      </div>
      <div>
          <input id="password" placeholder="password" type="password" v-model="credentials.password" />    
      </div>
      <div>
          <input id="password" placeholder="passwordConfirmation" type="password" v-model="credentials.passwordConfirmation" @keyup.enter="signup"/>    
      </div>
    <button type="submit" class="btn" @click="signup">회원 가입</button>
    <p>{{ credentials.logMessage }}</p>
  </form>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
          credentials: {
            useremail: '',
            password: '',
            passwordConfirmation: '',
            logMessage: ''
          }

        }
    },
    methods: {
      
      signup() {
        const msg = '';
        const err = true;
        if (!this.credentials.useremail) {
          msg = "Email을 입력해주세요";
          err = false;
        } else if (!this.credentials.password) {
          msg = "password를 입력해주세요";
          err = false;
        } else if (!this.credentials.passwordConfirmation) {
          msg = "password를 한번 더 입력해주세요";
          err = false;
        }
        if (!err) alert(msg);
        else {
          axios({
            method: 'post',
            data: this.credentials
          }).then(() => {
            this.login()
          })
          .catch(() => {
            if (this.credentials.password === this.credentials.passwordConfirmation) {
            alert('중복된 아이디입니다.')
            } else {
            alert('비밀번호가 일치하지 않습니다.')
            }
          })
      }
    }
  }
}
</script>

<style scoped>
.btn {
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
}
</style>