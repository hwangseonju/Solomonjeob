<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <img src="@/assets/logo_purple.png">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse  justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/Home" style="text-decoration: none">Home</router-link> 
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="#">면접연습하기</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">내질문모음집</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">피드백</a>
          </li>
          <li class="nav-item">
            <router-link to="/login" style="text-decoration: none">로그인</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/signup" style="text-decoration: none">회원가입</router-link>
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

<style></style>