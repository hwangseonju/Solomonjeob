<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="submitForm" class="form">
        <div>
          <input id="useremail" type="text" placeholder="email" v-model="credentials.useremail">
        </div>
        <p v-show="!isUseremailValid">이메일 형식이 올바르지 않습니다.</p>
        
        <div> 
          <input id="password" type="password" placeholder="password" v-model="credentials.password">
        </div>
        <div>
          <button :disabled="!isUseremailValid || !credentials.password" type="submit" class="btn">
          로그인
          </button>
        </div>
        <br>
<<<<<<< HEAD
        <router-link to="/signup" class="btn" style="text-decoration: none">회원가입</router-link>
        
        <p>{{ logMessage }}</p>
=======
>>>>>>> feature/fe_login
      </form>
        <div class="snsbtn">
          <a href="https://accounts.kakao.com/login?continue=https%3A%2F%2Fkauth.kakao.com%2Foauth%2Fauthorize%3Fresponse_type%3Dcode%26redirect_uri%3Dhttp%253A%252F%252Flocalhost%253A8181%252Fapi%252Fmember%252Flogin%252Foauth_kakao%26client_id%3De35ccc21d2cf1759f526eef14ea4b921">
            <img  src="@/assets/kakao.png">
          </a>
        </div>
    </div>
  </div>

</template>

<script>
import { validateEmail } from '@/utils/validation'
import axios from 'axios'
export default {
  data() {
    return {
      credentials: {
        useremail: null,
        password: null,
      }

    }
  },
  computed: {
    isUseremailValid() {
      return validateEmail(this.credentials.useremail)
    }
  },
  methods: {
      login: function () {
      axios({
        method: 'post',
        url: '',
        data: this.credentials,
      })
        .then(res => {
          console.log(res)
          localStorage.setItem('jwt', res.data.token)
          // this.$emit('login')
          this.$router.push({ name: 'Home' })
        })
        .catch(err => {
          console.log(err)
          alert('회원정보가 올바르지 않습니다.')
        })
    }

  }
}
</script>

<<<<<<< HEAD
<style scoped>
=======
<style>
.snsbtn{
  justify-content: center;
}
>>>>>>> feature/fe_login
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
.router {
  color: blueviolet;
}
.btn {
  font-weight: bolder;
  color: blueviolet;
  width: 100%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}
.btn:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}


</style>