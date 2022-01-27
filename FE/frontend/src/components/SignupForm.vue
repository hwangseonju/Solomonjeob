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
        // if (!this.credentials.useremail) {
        //   msg = "Email을 입력해주세요";
        //   err = false;
        // } else if (!this.credentials.password) {
        //   msg = "password를 입력해주세요";
        //   err = false;
        // } else if (!this.credentials.passwordConfirmation) {
        //   msg = "password를 한번 더 입력해주세요";
        //   err = false;
        // }
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
  color: blueviolet;
  width: 100%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}

.btn :hover::before {
  transform: translateY(-100%);
}
.btn:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}
</style>