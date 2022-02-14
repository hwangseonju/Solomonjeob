<template>
    <AppHeader></AppHeader>
    <section class="fighting" style="position:relative; text-align:center;">
        <div >
            <p class="fighting_style" style="align:center">이메일 인증 후 로그인해주세요</p>

        </div>

        <div><img src="@/assets/bluebackground.png" alt=""></div>
        <div class="clock"><img src="@/assets/clock.png" alt="" style="width:40%"></div>
        <div class="mic"><img src="@/assets/mic.png" alt="" style="width:40%"></div>
        <div class="handshake"><img src="@/assets/handshake.png" alt="" style="width:40%"></div>
        <div class="laptop"><img src="@/assets/laptop.png" alt="" style="width:40%"></div>
        <div class="book"><img src="@/assets/book.png" alt="" style="width:40%"></div>
        <div class="light"><img src="@/assets/light.png" alt="" style="width:40%"></div>
        <div class="growup"><img src="@/assets/growup.png" alt="" style="width:40%"></div>



    </section>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
// import { instance } from '@/api/index.js'

export default {
    component: {
        AppHeader,
    }
}
</script>

<style scoped>
@font-face {
    font-family: 'YanoljaYacheR';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/YanoljaYacheR.woff') format('woff');
    font-weight: normal;
    font-style: normal;
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
</style>