<template>
  <form @submit.prevent="submitForm">
      <div>
          <input id="useremail" placeholder="email" type="text" v-model="useremail" />    
      </div>
      <div>
          <input id="password" placeholder="password" type="password" v-model="password" />    
      </div>
    <button type="submit" class="btn">회원 가입</button>
    <p>{{ logMessage }}</p>
  </form>
</template>

<script>
import { registerUser } from '@/api/index'

export default {
    data() {
        return {
            useremail: '',
            password: '',
            logMessage: ''
        }
    },
    methods: {
        async submitForm() {
            const userData = { 
                useremail: this.useremail,
                password: this.password,
            }
            const { data } = await registerUser(userData)
            console.log(data.useremail)
            this.logMessage = `${data.useremail} 님이 가입되었습니다`
            this.initForm()
        },
        initForm() {
            this.useremail = ''
            this.password = ''
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