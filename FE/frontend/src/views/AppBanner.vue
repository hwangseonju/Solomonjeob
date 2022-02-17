<template>
	<section  class="flex-container flex-end">
        <div class="flex-item">
            <div class="left_place">
                <div class="text">
                    <p class="solo_style">SOLO </p> 
                    <p>&nbsp; 준비해서</p>

                </div>
                <div class="text">
                    <p class="solo_style">&nbsp;솔로몬</p> 
                    <p>&nbsp;처럼 답변말하기</p>
                </div>
                <div class="btn_place"><button class="scroll_style w-btn" @click="scrolldown">이용방법</button></div>
            </div>


        </div>
        <div class="flex-item"><img src="@/assets/developer.svg" alt="Developer" class="img "/></div>
    </section>


	<section  class="container_text manual_style">
        <h1 class="container_manual">솔로몬접을 이용해보세요!</h1>
        <br>
        <br>
        <div class="row all_img">
        <div class="col-4">
            <div class="card card_first">
            <img src="@/assets/list.gif" class="card-img-top" style="height: 50%; width:70%; padding: 10%; margin-left: auto; margin-right: auto; display: block" >
            <br>
            <div class="card-body kopub_text">
                <h5 class="card_title">질문 리스트 만들기</h5>
                <br>
                <p class="card_content">상단의 내질문모음집을 클릭하여 <br> 나만의 질문 리스트를 만들어보세요</p>
            </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card card_second">
            <img src="@/assets/interview.gif" class="card-img-top" style="height: 50%; width:70%; padding: 10%; margin-left: auto; margin-right: auto; display: block" >
            <br>
            <div class="card-body kopub_text">
                <h5 class="card_title">면접 연습하기</h5>
                <br>
                <p class="card_content">상단의 면접연습하기를 클릭하여 <br>  나만의 면접 연습을 해보세요</p>
            </div>
            </div>
        </div>
        <div class="col-4">
            <div class="card card_third">
            <img src="@/assets/thumbup.gif" class="card-img-top" style="height: 50%; width:70%; padding: 10%; margin-left: auto; margin-right: auto; display: block " >
            <br>
            <div class="card-body kopub_text">
                <h5 class="card_title">피드백 받기</h5>
                <br>
                <p class="card_content">면접 연습에 참여할 지인을 초대하여 <br> 면접 연습 후 피드백을 받아보세요</p>
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
           window.scroll({ top: 1000, left: 0, behavior: 'smooth' });
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
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
.footer_style {
    font-family: 'Pretendard-Regular';


}


.container_grid {
    display: grid;
}
.container {
    margin-top: 5%
}
.container_text {
    /* background-image: radial-gradient(73% 147%, #EADFDF 59%, #ECE2DF 100%), radial-gradient(91% 146%, rgba(255,255,255,0.50) 47%, rgba(0,0,0,0.50) 100%);
    background-blend-mode: screen; */
    background-color: #cfe2fc;
    font-family: 'Pretendard-Regular';
    color: black;
    text-align: center;
    padding-right: 10%;
    padding-left: 10%;
}
.container_manual{
    font-size: 300%;
    padding-top: 9%;
    font-weight: bolder;
    font-family: 'Pretendard-Regular';
}
.manual_style {
    padding-bottom: 10%;
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
            /* background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%); */
            /* background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%); */
            /* background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%); */
            /* background-image: linear-gradient(to top, #e6e9f0 0%, #eef1f5 100%); */

            padding-bottom: 8%;
        }
        .flex-container.flex-end{justify-content: flex-end;}
        .text{
            display: inline-flex;
            font-family: 'Pretendard-Regular';
            font-size: 160%;
            padding-left: 30%;
            text-align: right;
            padding-right: 10%;
            vertical-align: middle;
            animation: fadein 5s;
            -moz-animation: fadein 5s; /* Firefox */
            -webkit-animation: fadein 5s; /* Safari and Chrome */
            -o-animation: fadein 5s; /* Opera */
            }
        .text_1{
            font-family: 'Pretendard-Regular';
            
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
        .text_2 {
            
            font-family: 'Pretendard-Regular';
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
        .text_3 {
            
            font-family: 'Pretendard-Regular';
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
            font-family: 'Pretendard-Regular';
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

.w-btn {
    font-family: 'Pretendard-Regular';
    position: relative;
    background-color: white;
    border: 2px solid #6974FF;
    display: inline-block;
    padding: 10px 20px;
    border-radius: 15px;
    text-decoration: none;
    font-weight: bolder;
    transition: 0.25s;
    color: #6974FF;
    font-size: 100%;
    
}
.scroll_style:hover {
    background-color: #6974FF;
    color: white;
}

.down_style {
    color: rgb(53,126,255);
    cursor: pointer;
}
.scroll_style {
    font-family: 'Pretendard-Regular';
    color: #6974FF;
    animation: fadeup 3s;
    animation-delay: 2s;
    -moz-animation: fadeup 3s; /* Firefox */
    -webkit-animation: fadeup 3s; /* Safari and Chrome */
    -o-animation: fadeup 3s; /* Opera */
}
.btn_place {
    padding-top: 5%;
    padding-left: 18%;
}
.solo_style {
    font-weight: bolder;
    color: #6974FF;

}
.left_place {
    padding-top: 15%;
    text-align: center;
}
.all_img {
    padding:0%;
    width: 100%;
    margin-left: auto;
    margin-right: auto;
}
.card_style {
    height: 200%;
}
.card_first {
    width: 80%;
    height: 110%;
    border-radius: 10px;
    margin-left: auto;
    
}
.card_second {
    width: 80%;
    height: 110%; 
    border-radius: 10px;
    margin-left: auto;
    margin-right: auto;

 
}
.card_third {
    width: 80%;
    height: 110%;  
    border-radius: 10px;

}
.card_first:hover {
    transform: scale( 1.1 );
    transition: 0.5s;
    box-shadow: 5px 5px grey;

}
.card_second:hover {
    transform: scale( 1.1 );
    transition: 0.5s;
    box-shadow: 5px 5px grey;
}
.card_third:hover {
    transform: scale( 1.1 );
    transition: 0.5s;
    box-shadow: 5px 5px grey;

}
.card_title {
    font-family: 'Pretendard-Regular';
    text-align: center;
    font-weight: bolder;
    font-size: 170%;
}
.card_content {
    font-family: 'Pretendard-Regular';
    text-align: center;
    font-size: 120%;
    padding-top: 5%;
    

}
</style>
