<template>
  <div class="container-fluid">
	
    <div class="row">
      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 ">
          <div id="main-container" class="container">
            <div id="session" v-if="session && !selected">
              <div id="video-container" class="col-md-6">
                <user-video :stream-manager="publisher" @click="setMainVideoStream(publisher)" :audioDetect="audioDetect"/>
                <!-- <user-video :stream-manager="publisher" @click="setMainVideoStream(publisher)" v-else class="speakoff"/> -->
				<div>
					<!-- <span v-if="this.audioDetect=''"> - </span> -->
					<span v-if="this.audioDetect">{{ this.myUserName }} 이 말하는 중입니다.</span>
					<span v-else-if="!this.audioDetect">조용하네요</span>
				</div>

                <user-video v-for="(sub, index) in subscribers" :key="index" :stream-manager="sub" @click="setMainVideoStream(sub)"/>
                <div>

					<span v-if="this.checkVideo"><i @click="this.togglepublisherVideo" class="fas fa-video-slash"></i></span>
					<span v-else><i @click="this.togglepublisherVideo" class="fas fa-video"></i></span>

					<br>
					<span v-if="this.checkAudio"><i @click="this.togglepublisherAudio" class="fas fa-volume-up"></i></span>
					<span v-else><i @click="this.togglepublisherAudio" class="fas fa-volume-mute"></i></span>

				</div>


              </div>
            </div>
						<div v-if="selected">
							<img src="@/assets/director.png" alt="">
							<p>{{formattedElapsedTime}}</p>
						</div>
          </div>
        </div>
      </main>
      <my-question-list
			:selected="selected"
			:formattedElapsedTime="formattedElapsedTime"
			@changeForm="changeForm"
			@stopWatch="stopWatch"
			@startWatch="startWatch"
			>
			</my-question-list>
			
    </div>
  </div>
</template>

<script>
import myQuestionList from '@/components/interview/myQuestionList.vue';
import axios from 'axios';
// import stopWatch from '@/components/interview/stopWatch.vue';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/interview/UserVideo.vue';
import {  mapMutations, mapState } from 'vuex';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {

	components: {
		UserVideo,
		myQuestionList,
		// stopWatch
	},
	data () {
		return {
			questionStop:[],
			selected : false,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			// mySessionId: 'SessionA',
			mySessionId: '',
			// myUserName: 'Participant' + Math.floor(Math.random() * 100),
			myUserName: 'gonu',
			checkVideo: true,
			checkAudio: true,
			audioDetect: false,
			elapsedTime: 0,
			timer: undefined

		}
	},
	computed: {
		...mapState(["isLogin", "signinIdx",]),
		formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
	},

	methods: {
		...mapState(["isLogin", "signinIdx", "vidieoActive", "audioActive", "audioDetect", "session"]),
		...mapMutations(["SET_VIDEO", "SET_AUDIO", "toggleVideo", "toggleAudio", "SET_AUDIO_DETECT", "SET_SESSION"]),
		changeForm(propSelected){
			this.selected= propSelected
		},
		speechDetect() {
			this.session.on('publisherStartSpeaking', () => {
				this.audioDetect = true
			});

			this.session.on('publisherStopSpeaking', () => {
				this.audioDetect = false

			});

		},
		togglepublisherAudio() {   // 토글 시도
			this.audioActive = !this.audioActive;
			this.publisher.publishAudio(this.audioActive);
			this.checkAudio = !this.checkAudio
			this.speechDetect()
		},
		togglepublisherVideo() {   // 토글 시도
			this.videoActive = !this.videoActive;
			this.publisher.publishVideo(this.videoActive);
			this.checkVideo = !this.checkVideo
		},


		joinSession () {
			if (this.isLogin) {
				// --- Get an OpenVidu object ---
				const OV = new OpenVidu();
				// --- Init a session ---
				this.session = OV.initSession();
				// --- Specify the actions when events take place in the session ---
				// On every new Stream received...
				this.session.on('streamCreated', ({ stream }) => {
					this.subscribers.push(this.session.subscribe(stream));
				});
				// On every Stream destroyed...
				this.session.on('streamDestroyed', ({ stream }) => {
					const index = this.subscribers.indexOf(stream.streamManager, 0);
					if (index >= 0) {
						this.subscribers.splice(index, 1);
					}
				});
				// --- Connect to the session with a valid user token ---
				// 'getToken' method is simulating what your server-side should do.
				// 'token' parameter should be retrieved and returned by your own backend
				this.getToken(this.mySessionId).then(token => {
					this.session.connect(token, { clientData: this.myUserName })
						.then(() => {
							// --- Get your own camera stream with the desired properties ---
							this.publisher = OV.initPublisher(undefined, {
								audioSource: undefined, // The source of audio. If undefined default microphone
								videoSource: undefined, // The source of video. If undefined default webcam
								publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
								publishVideo: false,  	// Whether you want to start publishing with your video enabled or not
								resolution: '1200x850',  // The resolution of your video
								frameRate: 30,			// The frame rate of your video
								insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
								mirror: false,       	// Whether to mirror your local video or not
							});
							this.mainStreamManager = this.publisher;
							// --- Publish your stream ---
							this.session.publish(this.publisher);
							console.log(this.publisher)

						})
						.catch(error => {
							console.log('There was an error connecting to the session:', error.code, error.message);
						});
				});
				window.addEventListener('beforeunload', this.leaveSession)
			} else {
				alert("로그인 후 입장해주세요")
				this.$router.push('Home')

			}
		
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			window.removeEventListener('beforeunload', this.leaveSession);
		},
		setMainVideoStream (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream
		},

    
		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a session in OpenVidu Server	(POST /api/sessions)
		 *   2) Generate a token in OpenVidu Server		(POST /api/tokens)
		 *   3) The token must be consumed in Session.connect() method
		 */
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-apisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-apitokens
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/api/tokens`, JSON.stringify({
						session: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

		startWatch() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    // next() {
    //   this.questionStop.push(this.formattedElapsedTime);
			
    // },
		stopWatch() {
      clearInterval(this.timer);
    },
		
	},

	created() {
		this.mySessionId = localStorage.getItem('signidx')
		this.joinSession()
	}

}
</script>

<style scoped>
.btn_interview {
  font-weight: bolder;
  color: blueviolet;
  width: 50%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}
.btn_interview:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}
.btn_home {
  font-weight: bolder;
  color: blueviolet;
  width: 10%;
  box-sizing: border-box;
  border: 3px solid slateblue;
}
.btn_home:hover {
  font-weight: bolder;
  background-color: blueviolet;
  color: white;
}

</style>