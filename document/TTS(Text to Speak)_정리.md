### TTS(Text to Speak)

 : 음성합성 시스템, text를 소리로 읽어준다.



- 목소리 가져오기

```html
var voices = [];
function setVoiceList() {
voices = window.speechSynthesis.getVoices();
}

setVoiceList();

if (window.speechSynthesis.onvoiceschanged !== undefined) {
window.speechSynthesis.onvoiceschanged = setVoiceList;
}
```



- speech() 함수 생성 - 음성 재생 부분을 구현

  브라우저에서 `window.speechSynthesis`을 제공해야만 사용이 가능하므로 브라우저 체크가 필요하다. 크롬이나 파이어폭스는 모바일 버전에서도 지원하지만 OS 내장브라우저는 거의 지원하지 못한다. 따라서 네이버 브라우저나 SNS 프로그램 내에서 웹페이지를 보여주는 웹뷰에선 지원 못할것이다.

  ```html
  if(!window.speechSynthesis) {
  alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
  return;
  }
  ```

  음성읽기에 대한 정보를 담은 객체 생성.

  ```html
  var lang = 'ko-KR';
  var utterThis = new SpeechSynthesisUtterance(txt);
  var voiceFound = false;
  for(var i = 0; i < voices.length ; i++) {
  if(voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
  utterThis.voice = voices[i];
  voiceFound = true;
  }
  }
  if(!voiceFound) {
  alert('voice not found');
  return;
  }
  utterThis.lang = lang;
  utterThis.pitch = 1;
  utterThis.rate = 1; //속도
  ```

  

- test file

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
  <title>CSS Template</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  <body>
  <input id="code_html" type="text" value="삼겹살" autocomplete="off" readonly="">
  <input type="button" value="말하기"/>
  <input id="code_direct" type="text" value="족발" autocomplete="off" readonly="">
  <input type="button" value="말하기"/>
  <input id="code_reddit" type="text" value="수육" autocomplete="off" readonly="">
  <input type="button" value="말하기"/>
  <script>
  var voices = [];
  function setVoiceList() {
  voices = window.speechSynthesis.getVoices();
  }
  setVoiceList();
  if (window.speechSynthesis.onvoiceschanged !== undefined) {
  window.speechSynthesis.onvoiceschanged = setVoiceList;
  }
  function speech(txt) {
  if(!window.speechSynthesis) {
  alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
  return;
  }
  var lang = 'ko-KR';
  var utterThis = new SpeechSynthesisUtterance(txt);
  utterThis.onend = function (event) {
  console.log('end');
  };
  utterThis.onerror = function(event) {
  console.log('error', event);
  };
  var voiceFound = false;
  for(var i = 0; i < voices.length ; i++) {
  if(voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
  utterThis.voice = voices[i];
  voiceFound = true;
  }
  }
  if(!voiceFound) {
  alert('voice not found');
  return;
  }
  utterThis.lang = lang;
  utterThis.pitch = 1;
  utterThis.rate = 1; //속도
  window.speechSynthesis.speak(utterThis);
  }
  document.addEventListener("click", function(e) {
  var t = e.target;
  var input = t.previousElementSibling;
  speech(input.value);
  });
  </script>
  </body>
  </html>
  
  ```



References.

https://developer.mozilla.org/en-US/docs/Web/API/Web_Speech_API/Using_the_Web_Speech_API

