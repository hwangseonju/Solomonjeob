<template>
	<div class="footer">
        <br>
        <div class="notice">
            <ul>

            </ul>
            <div>
                <img src="@/assets/logo_purple.png" class="img" alt="">
            </div>


        </div>
    </div>
</template>

<script>

export default {
	
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

.footer{
    position: relative;
    width: 100%;
    min-height: 150px;
    margin-top: 100%;
    text-align: left;
    background-color: #222426;
}
.notice {
    padding: 28px 0 0 40px;
}
.h4 {
  color: white;
  font-size: 80%;
}
.img {
    width: 70px;
    height: 70px;
}
.img:hover {
    transform: skew(-10deg);
}
</style>