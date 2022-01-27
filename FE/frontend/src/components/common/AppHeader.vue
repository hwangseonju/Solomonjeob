<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container-fluid">
      <router-link to="/Home"> <img src="@/assets/logo_purple.png" class="img"> </router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <!-- <li class="nav-item">
            <router-link to="/Home" class="nav-link">Home</router-link> 
          </li> -->
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="#">면접연습하기</a>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/question">질문모음집</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">피드백</a>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/login" >로그인  </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/signup">회원가입</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- <header>
  </header> -->
</template>

<script>
export default {};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');
.navbar-nav > li {
  padding-left:25px;
  padding-right:25px;
  /* border-bottom: 1px solid #666; */
  font-size: 120%;
  font-family: 'Noto Sans KR', sans-serif;
}
.navbar-nav .nav-item a:hover {
  color: blueviolet;
}
/* .nav-link:hover {
  color: red;
} */

.img {
  padding: 0;
  width: 50%;
  height: 50%;
}

</style>