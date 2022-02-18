<template>
    <input id="code_html" type="text" value="삼겹살" autocomplete="off" readonly="">
    <input type="button" @click="speech(this.hello)" value="말하기"/>
    <!-- <button @click="checkevent(e)">말하기</button> -->
    <input id="code_direct" type="text" value="족발" autocomplete="off" readonly="">
    <input type="button" value="말하기"/>
    <input id="code_reddit" type="text" value="수육" autocomplete="off" readonly="">
    <input type="button" value="말하기"/>


</template>

<script>
export default {
    data()  {
        return {
            voices : [],
            hello: '안녕하세요'
            // t : '',
            // i : '',

        }
    },

    methods: {
        
        setVoiceList() {
            this.voices = window.speechSynthesis.getVoices();

            },

        speech(txt) {

            if (window.speechSynthesis.onvoiceschanged !== undefined) {
                window.speechSynthesis.onvoiceschanged = this.setVoiceList()
            }
            if(!window.speechSynthesis) {
                alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
                return;
            }
            var lang = 'ko-KR';
            var utterThis = new SpeechSynthesisUtterance(txt);
            utterThis.onend = function () {
                console.log('end');
            };
            utterThis.onerror = function(event) {
                console.log('error', event);
            };
            var voiceFound = false;
            for(var i = 0; i < this.voices.length ; i++) {
                if(this.voices[i].lang.indexOf(lang) >= 0 || this.voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
                    utterThis.voice = this.voices[i];
                    voiceFound = true;
                }
            }
            if(!voiceFound) {
                console.log(this.voices)
                alert('voice not found');
                return;
            }
            utterThis.lang = lang;
            utterThis.pitch = 1;
            utterThis.rate = 1; //속도
            window.speechSynthesis.speak(utterThis);


        },



    }
            
    
        

}
</script>

<style>

</style>