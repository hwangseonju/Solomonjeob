<template>
	<section  class="flex-container flex-end">
        <div class="flex-item text-all">
            <div class="text">면접 연습하고 싶을 때, <br>편하게 연습할 수 있도록 </div>
            <div class="text_1"><h1>당신의 합격을 도와드립니다</h1></div>

        </div>
        <div class="flex-item"><img src="@/assets/developer.svg" alt="Developer" class="img "/></div>

    </section>
    <section class="container container_text">

        <h1 class="container_manual">솔로몬접을 사용해보세요!</h1>
    </section>
	<section  class="container">
        <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/list.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block" >
            <div class="card-body">
                <h5 class="card-title">질문 리스트 만들기</h5>
                <p class="card-text">나만의 면접 질문 리스트를 만들어 보세요</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/interview.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block" >
            <div class="card-body">
                <h5 class="card-title">면접 연습하기</h5>
                <p class="card-text">면접 연습을 해보세요</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/thumbup.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block " >
            <div class="card-body">
                <h5 class="card-title">피드백 받기</h5>
                <p class="card-text">피드백을 받아 부족한 점을 개선해보세요</p>
            </div>
            </div>
        </div>

        </div>
    </section>
    <br>
    <br>
    <br>
	<section  class="flex-container flex-end container_text">
        <div class="flex-item text-all">
    <!-- <section class="container container_text"> -->
            <h1 class="container_manual">솔로몬접은 여러분을 항상 응원합니다!</h1>

        </div>
        
    </section>

	<section  class="flex-container">
        <div class="flex-item"><img src="@/assets/logo_purple.png" style="margin-left:60%; width:10%;"></div>
        <div class="flex-item"><h5 style="padding-top:5%; "> Copyright 2022. Solomonjeob all rights reserved.</h5></div>

    </section>

</template>
<script>
import feather from 'feather-icons';

export default {
	data () {
		return {
			theme: '',
		};
	},
	created() {
		this.theme = localStorage.getItem('theme') || 'light';
	},
	mounted() {
		feather.replace();
		this.theme = localStorage.getItem('theme') || 'light';
	},
	updated() {
		feather.replace();
	},
	methods: {},
};
</script>



<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

@font-face {
    font-family: 'YanoljaYacheR';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/YanoljaYacheR.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'LeferiPoint-BlackA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/LeferiPoint-BlackA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.container {
    margin-top: 10%
}
.container_text {
    font-family: 'YanoljaYacheR';
    color: rgb(75, 137, 220);
    text-align: center;
}
.container_manual{
    font-size: 500%;
}



.img {
    width: 70%;
    height: 70%;
    animation: slide 1.5s ease-out;
    /* object-fit: contain; */
    position: relative;


}
@keyframes slide {
    from{
        right: -100%;
    }
    to{
        right:0%;
    }
} 

.flex-container{
            display: flex;
        }
        .flex-container.flex-end{justify-content: flex-end;}

        .text{
            font-family: 'YanoljaYacheR';
            font-size: 200%;
            padding-left: 35%;
            text-align: center;
            padding-right: 15%;
            vertical-align: middle;
            padding-top: 20%;
            animation: fadein 5s;
            -moz-animation: fadein 5s; /* Firefox */
            -webkit-animation: fadein 5s; /* Safari and Chrome */
            -o-animation: fadein 5s; /* Opera */
            }
        .text_1{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 150%;
            color: rgb(75, 137, 220);
            text-align: right;
            padding-right: 15%;
            vertical-align: middle;
            padding-top: 10%;
            animation: fadeup 3s;
            animation-delay: 2s;
            -moz-animation: fadeup 3s; /* Firefox */
            -webkit-animation: fadeup 3s; /* Safari and Chrome */
            -o-animation: fadeup 3s; /* Opera */
        }
        .box {
            display: inline-flex;
            width: 200px;
            height: 200px;
            border-radius: 10px;
            border: 2px solid black;
            margin-block: 20%;
        }
@keyframes fadeup {
    from {
        opacity: 0;
        transform: translateY(20px);
    }
    to {
        opacity: 1;
        transform: none;
    }
}
main {
    animation: fadeup 0.5s ease-in-out;
}
@keyframes fadein {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}


        .flex-item{
            width: 100%;
            height: 100%;
            padding-top: 5%;

        }
.fade-in-box {
  display: inline-block;
  padding: 10px;
}
</style>
