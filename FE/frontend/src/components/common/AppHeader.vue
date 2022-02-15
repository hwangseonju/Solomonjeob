<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-white main">
    <div class="container-fluid">
      <router-link to="/Home"> <img src="@/assets/solomon_logo2.png" class="img"> </router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
          <ul class="navbar-nav">
            <!-- 로그인 되어 있을 때 -->
              <li class="nav-item">
                <router-link class="nav-link" to="/interview" @click="checkLogin">면접연습하기</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link" to="/question" @click="checkLogin">내질문모음집</router-link>
              </li>
              <!-- <li class="nav-item">
                <a class="nav-link" href="#">고객센터</a>
              </li> -->
              <li class="nav-item" v-if="!isLogin">
                <router-link class="nav-link" to="/login" >로그인</router-link>
              </li>
              <li class="nav-item" v-if="!isLogin">
                <router-link class="nav-link" to="/signup">회원가입</router-link>
              </li>
              <li class="nav-item" v-else>
                <router-link class="nav-link" @click="logout" to="#">로그아웃</router-link>
              </li>

          </ul>
        </div>

    </div>
  </nav>


</template>
<script>
import { mapMutations, mapState } from "vuex";

export default {
  name: 'appheader',
  data()  {
    return {
      // isLogin: false,
    }
  },
  computed: {
    ...mapState(["isLogin"])
  },
  methods: {
    ...mapMutations(["SET_IS_LOGIN"]),
    logout() {
      this.SET_IS_LOGIN(false);
      localStorage.removeItem('jwt-auth-token');
      alert('로그아웃되었습니다');
      this.$router.push('Login')
    },
    checkLogin() {
      if (!this.isLogin) {
				alert("로그인 후 입장해주세요")
				this.$router.push('Home')      }
    }

  },

  created() {
    const token = localStorage.getItem('jwt')
    if (token) {
      this.isLogin = true
    }

  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;500;700&display=swap');
.navbar-nav > li {
  padding-left:25px;
  padding-right:25px;
  /* border-bottom: 1px solid #666; */
  font-size: 120%;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 500;
}
.navbar-nav .nav-item a:hover {
  color: rgb(75, 137, 220);
}


.img {
  padding: 0;
  width: 50%;
  height: 50%;
}
.main {
  padding-left: 15%;
  padding-right: 15%;
  padding-top: 1%;
}
</style>