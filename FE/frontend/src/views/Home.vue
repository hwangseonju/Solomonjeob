<template>

    <!-- <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="10000">
          <img src="@/assets/interview.png" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>문장넣기</h5>
          </div>
        </div>
        <div class="carousel-item" data-bs-interval="10000">
          <img src="@/assets/interview1.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>문장넣기</h5>
          </div>
        </div>
        <div class="carousel-item" data-bs-interval="10000">
          <img src="@/assets/interview2.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h5>문장넣기</h5>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div> -->
  <section>
    <img src="@/assets/interview6.jpg" class="img">
    <img src="@/assets/interview3.jpg" class="img">
    <img src="@/assets/interview2.jpg" class="img">

  </section>

</template>

<script>

export default {
  name: 'Home',
  components: {
    
  },
};
</script>

<style scoped>
  /* 캐러셀(이미지슬라이드) 이미지 크기변경 */
  .carousel-inner{
    width:100%;
    height:790px; /* 이미지 높이 변경 */
  }
  .carousel-item{
    width: 100%;
    height:100%;
  }
  .d-block {
    display:block;
    width: 100%;
    height: 100%;
  }
/* body{
  margin:0;
  height: 2000px;
  background: black;
} */
section{
  height: 100vh;
  /* background-size: cover; */
  background-repeat: no-repeat;
}
.img {
  width:100%;
  height:100%;
  background: linear-gradient(to top, rgb(95, 92, 92), transparent);

}
section:before{
  content: '';
  background: linear-gradient(to top, rgb(95, 92, 92), transparent);
  position: absolute;
  left: 0;
  height: 100%;
  width: 100%;
}


</style>
