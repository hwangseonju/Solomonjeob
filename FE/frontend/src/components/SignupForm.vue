<template>
  <form @submit.prevent="submitForm">
      <div>
          <label for="useremail">email: </label>
          <input id="useremail" type="text" v-model="useremail" />    
      </div>
      <div>
          <label for="password">password: </label>
          <input id="password" type="text" v-model="password" />    
      </div>
    <button type="submit">회원 가입</button>
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

<style>

</style>