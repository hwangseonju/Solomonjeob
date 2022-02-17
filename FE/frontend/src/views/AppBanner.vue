<template>
	<section  class="flex-container flex-end">
        <div class="flex-item text-all">
            <div class="text">면접 연습하고 싶을 때, <br>편하게 연습할 수 있도록 </div>
            <div class="text_1" ><h1>당신의 면접을 도와드립니다</h1></div>
            <div class="btn_place"><button class="scroll_style w-btn" @click="scrolldown">이용방법</button></div>

        </div>
        <div class="flex-item"><img src="@/assets/developer.svg" alt="Developer" class="img "/></div>
    </section>


    <section class="container container_text">
        <h1 class="container_manual">솔로몬접을 이용해보세요!</h1>
    </section>
	<section  class="container manual_style">
        <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/list.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block" >
            <div class="card-body kopub_text">
                <h5 >질문 리스트 만들기</h5>
                <p >나만의 면접 질문 리스트를 만들어 보세요</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/interview.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block" >
            <div class="card-body kopub_text">
                <h5>면접 연습하기</h5>
                <p>내가 작성한 질문으로 면접 연습을 해보세요</p>
            </div>
            </div>
        </div>
        <div class="col">
            <div class="card h-100">
            <img src="@/assets/thumbup.gif" class="card-img-top" style="width:60%; margin-left: auto; margin-right: auto; display: block " >
            <div class="card-body kopub_text">
                <h5 class="card-title">피드백 받기</h5>
                <p class="card-text">초대한 사람에게 피드백을 받아 부족한 점을 개선해보세요</p>
            </div>
            </div>
        </div>
        </div>
        <br>
        <br>

    </section>

 
    <section class="container footer_style">
        <hr>
        <div style="text-align:center; padding-top:4%;">E-mail : solomonjeob207@gmail.com</div>
        <div style="text-align:center;"> Copyright 2022. Solomonjeob all rights reserved.</div>
        <br>
        

    </section>


</template>
<script>
// import feather from 'feather-icons';

export default {
	data () {
		return {
			// theme: '',
		};
	},
    methods : {
       scrolldown() {
           window.scroll({ top: 800, left: 0, behavior: 'smooth' });
       },
       scrollup() {
           window.scroll({ top: 0, left: 0, behavior: 'smooth' });
       },


    }


};
</script>



<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');

@font-face {
    font-family: 'KoPubDotumMedium';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'YanoljaYacheR';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/YanoljaYacheR.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.footer_style {
    font-family: 'KoPubDotumMedium';


}
.fighting {
    padding-top: 20%;
}
.fighting_style {
    top: 60%;
    left: 20%;
    right: 20%;
    color: white;
    position:absolute;
    z-index: 0;
    font-family: YanoljaYacheR;
    font-size: 500%;
}
.clock {
    top: 30%;
    left:10%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: up-down 2s infinite ease-in-out alternate;

}
.growup {
    top: 80%;
    left:35%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: shake 2s infinite ease-in-out alternate;

}
.mic{
    top: 40%;
    left:30%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: shake 2s infinite ease-in-out alternate;

}
.handshake {
    top: 70%;
    left:15%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: up-down 2s infinite ease-in-out alternate;

}
.laptop {
    top: 25%;
    left:45%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: up-down 2s infinite ease-in-out alternate;

}
.book{
    top: 40%;
    left:60%;
    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: shake 2s infinite ease-in-out alternate;

}
.light {
    top: 70%;
    left:55%;

    position:absolute;
    /* display: block; */
    z-index: 1;
    animation: up-down 2s infinite ease-in-out alternate;

}
.man {
    top: 30%;
    right:10%;
    position:absolute;
    /* display: block; */
    z-index: 1;
}
.woman {
    top: 32%;
    left:3%;
    position:absolute;
    /* display: block; */
    z-index: 1;

}
@keyframes up-down {
    from{
        transform: translatey(0px);
    }
    to{
        transform: translatey(-20px);
    }
}
@keyframes shake {
    20% {
        transform: rotate(8deg);
    }
    40% {
        transform: rotate(-6deg);
    }
    60% {
        transform: rotate(3deg);
    }
    80% {
        transform: rotate(-3deg);
    }
    100% {
        transform: rotate(0deg);
    }
}


.container_grid {
    display: grid;
}
.container {
    margin-top: 5%
}
.container_text {
    font-family: 'KoPubDotumMedium';
    color: rgb(75, 137, 220);
    text-align: center;
}
.container_manual{
    font-size: 300%;
    padding-top: 9%;
    font-family: 'Noto Sans KR', sans-serif;

}
.kopub_text {
    font-family: 'Noto Sans KR', sans-serif;
    text-align: center;

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
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 200%;
            padding-left: 30%;
            text-align: right;
            padding-right: 10%;
            vertical-align: middle;
            padding-top: 15%;
            animation: fadein 5s;
            -moz-animation: fadein 5s; /* Firefox */
            -webkit-animation: fadein 5s; /* Safari and Chrome */
            -o-animation: fadein 5s; /* Opera */
            }
        .text_1{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 150%;
            font-weight: bolder;
            color: rgb(75, 137, 220);
            text-align: right;
            padding-right: 3%;
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
        .btn_style {
            background-color: rgb(97, 97, 235);
            color: white;
            font-family: 'Noto Sans KR', sans-serif;
            border: 0px;
  
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
.manual_style {
    padding-bottom: 10%;
}
.w-btn {
    font-family: 'Noto Sans KR', sans-serif;
    position: relative;
    border: 3px solid rgb(53,126,255);
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
    color: rgb(53,126,255);
    font-size: 110%;
    
    /* background-color: #6aafe6;
    color: white; */
}
.scroll_style:hover {
    background-color: rgb(53,126,255);
    color: white;
}

.down_style {
    color: rgb(53,126,255);
    cursor: pointer;
}
.scroll_style {
    font-family: 'Noto Sans KR', sans-serif;
    float: right;
    color: rgb(53,126,255);
}
.btn_place {
    padding-top: 5%;
    padding-right: 22%;
}
</style>
