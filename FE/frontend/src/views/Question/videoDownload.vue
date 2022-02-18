<template>
    <div>

    </div>
    <app-header></app-header>
    <div class="video">
        <h1 class="h1_style">면접 연습 다시 보기</h1>
        <video controls width="250" class="video_download">
            
            <source :src="govideo" type="video/mp4">
            <strong>Your browser does not support the video tag.</strong>
        </video>
        <p class="p_style">창을 닫거나 벗어날 시 다시 보기는 제공되지 않습니다.</p>
    </div>

</template>


<script>
import AppHeader from '@/components/common/AppHeader.vue';
export default {
    components: {
    AppHeader
	},
    data () {
        return {
            sessionId : '',
            govideo : ''
        }
    },
    methods : {
        unLoadEvent () {
            alert("페이지를 벗어나면 다시 보기는 제공되지 않습니다.");
        },
    },
    created() {
        let beforeUrl = window.location.pathname;
		let afterUrl = beforeUrl.split('/');
        this.sessionId = afterUrl[2];
        this.govideo = "https://i6c207.p.ssafy.io/openvidu/recordings/" + this.sessionId +"/"+ this.sessionId+".mp4";
    },
    onMounted() {
        window.addEventListener('beforeunload', this.unLoadEvent()); 
    },
    beforeUnmount() { 
        window.removeEventListener('beforeunload', this.unLoadEvent());
    } 
}
</script>
<style scoped>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
.h1_style {
    font-family: 'Pretendard-Regular';
    font-size: 200%;
    padding-bottom: 2%;
}
.video {
    text-align: center;
    padding-top: 3%;
}
.video_download {
    width: 50%;
}
.p_style {
    font-family: 'Pretendard-Regular';
    color: #79a2ff;
    font-size: 120%;
    font-weight: bold;
    padding-top: 1%;
}

</style>