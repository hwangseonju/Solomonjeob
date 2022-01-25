<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="submitForm" class="form">
        <div>
          <input id="useremail" type="text" placeholder="email" v-model="useremail">
        </div>
        <div> 
          <input id="password" type="password" placeholder="password" v-model="password">
        </div>
        <div>
          <button :disabled="!isUseremailValid || !password" type="submit" class="btn">
          로그인
          </button>
        </div>
        <br>
        <router-link to="/signup" class="btn" style="text-decoration: none">회원가입</router-link>
        
        <p>{{ logMessage }}</p>
      </form>
    </div>
  </div>

</template>

<script>
import { loginUser } from '@/api/index'
import { validateEmail } from '@/utils/validation'

export default {
  data() {
    return {
      useremail: '',
      password: '',
      logMessage: '',
    }
  },
  computed: {
    isUseremailValid() {
      return validateEmail(this.useremail)
    }
  },
  methods: {
    async submitForm() {
      try {
        const userData = {
          useremail: this.useremail,
          password: this.password,
        }
        const { data } = await loginUser(userData)
        console.log(data.user.useremail)
        this.logMessage = `${data.user.useremail} 님 환영합니다.`
      } catch (error) {
        console.log(error.response.data)
        this.logMessage = error.response.data
      } finally{
        this.initForm()
      }
    },
    initForm() {
      this.useremail = ''
      this.password = ''
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